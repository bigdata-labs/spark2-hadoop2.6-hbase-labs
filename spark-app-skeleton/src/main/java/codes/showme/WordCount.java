package codes.showme;

import codes.showme.config.Configuration;
import codes.showme.config.PropertiesConfig;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.Tuple2;

import java.io.Serializable;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;


/**
 * Created by jack on 12/2/16.
 */
public class WordCount implements Serializable{

    private static final Logger logger = LoggerFactory.getLogger(WordCount.class);
    private static final long serialVersionUID = 7513786688130749007L;

    private static Configuration configuration = new PropertiesConfig();

    public static void main(String[] args) throws InterruptedException {

        SparkConf conf = new SparkConf()
                .setAppName(configuration.getAppName())
                .setMaster(configuration.getSparkMasterURL());

        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> textFile = sc.textFile(configuration.getWordFileHDFSPath());

        JavaRDD<String> words = textFile.flatMap(new FlatMapFunction<String, String>() {
            public Iterator<String> call(String s) { return Arrays.asList(s.split(" ")).iterator(); }
        });
        JavaPairRDD<String, Integer> pairs = words.mapToPair(new PairFunction<String, String, Integer>() {
            public Tuple2<String, Integer> call(String s) { return new Tuple2<String, Integer>(s, 1); }
        });
        JavaPairRDD<String, Integer> counts = pairs.reduceByKey(new Function2<Integer, Integer, Integer>() {
            public Integer call(Integer a, Integer b) { return a + b; }
        });

        counts.saveAsTextFile(configuration.getWordCountResultHDFSPath() + "" + new Date().getTime());

        sc.close();
    }


}
