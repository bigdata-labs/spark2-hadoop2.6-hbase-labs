package codes.showme.config;

import codes.showme.exception.LoadConfigFromPropertiesException;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

/**
 * Created by jack on 11/13/16.
 */
public class PropertiesConfig implements Configuration, Serializable{

    private static final long serialVersionUID = 5346106334271819144L;
    private Properties properties = new Properties();

    public PropertiesConfig() {
        InputStream in = getClass().getResourceAsStream("/env.properties");
        try {
            properties.load(in);
        } catch (IOException e) {
            throw new LoadConfigFromPropertiesException(e);
        }
    }

    public String getAppName() {
        return properties.getProperty("app_name");
    }

    public String getSparkMasterURL() {
        return properties.getProperty("spark_master_url");
    }

    private String getHDFSURL() {
        return properties.getProperty("hdfs_url", "");
    }

    @Override
    public String getWordFileHDFSPath() {
        return getHDFSURL() + properties.getProperty("wordcount_file_name", "");
    }

    @Override
    public String getWordCountResultHDFSPath() {
        return getHDFSURL() + properties.getProperty("wordcount_result_directory", "");
    }

    @Override
    public String getUsersDataHDFSPath() {
        return getHDFSURL() + properties.getProperty("users_file_name", "");
    }

    @Override
    public String getZooKeeperUrls() {
        return properties.getProperty("hbase_zookeeper_urls", "");
    }

    @Override
    public String getZooKeeperClientPort() {
        return properties.getProperty("hbase_zookeeper_client_port", "2181");
    }

    public Properties getProperties() {
        return properties;
    }
}
