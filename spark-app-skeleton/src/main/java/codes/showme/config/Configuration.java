package codes.showme.config;

/**
 * Created by jack on 11/13/16.
 */
public interface Configuration {
    String getAppName();
    String getSparkMasterURL();

    String getWordFileHDFSPath();

    String getWordCountResultHDFSPath();

    String getUsersDataHDFSPath();

    String getZooKeeperUrls();

    String getZooKeeperClientPort();
}
