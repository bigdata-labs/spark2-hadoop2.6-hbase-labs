package codes.showme;

import codes.showme.config.PropertiesConfig;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jack on 1/15/17.
 */
public class HbaseExample implements Serializable {


    private static final long serialVersionUID = 3588307045769608350L;
    private static codes.showme.config.Configuration configuration = new PropertiesConfig();
    private static final String USER_INFO_COLUMN_NAME = "info";
    private static final String USER_ACCOUNT_COLUMN_NAME = "account";
    private static final String USERS_TABLE_NAME = "users";

    public static void main(String[] args) throws IOException {

        SparkConf conf = new SparkConf()
                .setAppName("hbaseexample")
                .setMaster(configuration.getSparkMasterURL());

        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> textFile = sc.textFile(configuration.getUsersDataHDFSPath());
        textFile.cache();

////        //用于执行操作
//        HBaseAdmin admin = new HBaseAdmin(hbaseConfig);
//        System.out.println("++++++++++++++++++++++++ admin" + admin);
//
//        HTableDescriptor desc = new HTableDescriptor(USERS_TABLE_NAME);
//        desc.addFamily(new HColumnDescriptor(USER_INFO_COLUMN_NAME));
//        desc.addFamily(new HColumnDescriptor(USER_ACCOUNT_COLUMN_NAME));
//        //判断表格是否存在
//        if (admin.tableExists(USERS_TABLE_NAME)) {
//            System.out.println(USERS_TABLE_NAME + " exists");
//            System.exit(0);
//        } else {
//            admin.createTable(desc);
//            System.out.println("create table success");
//        }
//        admin.close();


        textFile.map(line -> line.split(","))
                .filter(columns -> columns.length == 7)
                .foreachPartition((VoidFunction<Iterator<String[]>>) (Iterator<String[]> lineIterator) -> {
                    Configuration hbaseConfig = HBaseConfiguration.create();
                    hbaseConfig.set("hbase.zookeeper.quorum", configuration.getZooKeeperUrls());
                    hbaseConfig.set("hbase.zookeeper.property.clientPort", configuration.getZooKeeperClientPort());

                    try (Connection connection = ConnectionFactory.createConnection(hbaseConfig)) {

                        List<Put> puts = new ArrayList<>();
                        while (lineIterator.hasNext()) {
                            String[] columns = lineIterator.next();
                            String rowKey = columns[0];
                            Put put = new Put(Bytes.toBytes(rowKey));
                            put.add(Bytes.toBytes(USER_INFO_COLUMN_NAME), Bytes.toBytes("firstname"), Bytes.toBytes(columns[1]));
                            put.add(Bytes.toBytes(USER_INFO_COLUMN_NAME), Bytes.toBytes("lastname"), Bytes.toBytes(columns[2]));
                            put.add(Bytes.toBytes(USER_INFO_COLUMN_NAME), Bytes.toBytes("email"), Bytes.toBytes(columns[3]));
                            put.add(Bytes.toBytes(USER_INFO_COLUMN_NAME), Bytes.toBytes("gender"), Bytes.toBytes(columns[4]));
                            put.add(Bytes.toBytes(USER_ACCOUNT_COLUMN_NAME), Bytes.toBytes("username"), Bytes.toBytes(columns[5]));
                            put.add(Bytes.toBytes(USER_ACCOUNT_COLUMN_NAME), Bytes.toBytes("password"), Bytes.toBytes(columns[6]));
                            puts.add(put);
                        }
                        Object[] results = new Object[puts.size()];

                        try (Table hTable = connection.getTable(TableName.valueOf(USERS_TABLE_NAME))) {
                            hTable.batch(puts, results);
                        }
                        for (Object result : results) {
                            System.out.println(result);
                        }
                    }
                });

        sc.close();
    }

}
