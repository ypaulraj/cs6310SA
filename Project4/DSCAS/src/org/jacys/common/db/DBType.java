package org.jacys.common.db;

/**
 * Created by IntelliJ IDEA.
 * User: e8pigke
 * Date: Sep 21, 2010
 * Time: 1:38:59 PM
 * To change this template use File | Settings | File Templates.
 */
public enum DBType {

    SQLSERVER(
          "com.microsoft.sqlserver.jdbc.SQLServerDriver",
          "jdbc:sqlserver://${HOST}:${PORT};DatabaseName=${DBNAME}",
          "select 1"),
    ORACLE(
          "oracle.jdbc.OracleDriver",
          "jdbc:oracle:thin:@//${HOST}:${PORT}/${SERVICE}",
          "select 'validationQuery' from dual");

    private String driverClassName;
    private String urlTemplate;
    private String validationQuery;

    DBType(String driverClassName, String urlTemplate, String validationQuery) {
        this.driverClassName = driverClassName;
        this.urlTemplate = urlTemplate;
        this.validationQuery = validationQuery;
    }


    public String getDriverClassName() {
        return driverClassName;
    }

    public String getUrlTemplate() {
        return urlTemplate;
    }

    public String getUrl(
          String host, String port, String service, String dbName) {
        String url = getUrlTemplate();
        url = url.replace("${HOST}", host);
        url = url.replace("${PORT}", port);

        switch (this) {
            case ORACLE:
                url = url.replace("${SERVICE}", service);
                break;
            case SQLSERVER:
                url = url.replace("${DBNAME}", dbName);
                break;
        }

        return url;
    }

    public String getValidationQuery() {
        return validationQuery;
    }
}
