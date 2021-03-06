package org.jacys.common.db;

import org.apache.commons.dbcp.BasicDataSource;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * BasicDataSource that support pooling of connections via dbcp package.
 */
public class ConnectionPoolingDataSource extends BasicDataSource
      implements OwnerAwareDataSource, DBTypeAwareDataSource {

    /**
     * Return the parent Logger of all the Loggers used by this data source. This
     * should be the Logger farthest from the root Logger that is
     * still an ancestor of all of the Loggers used by this data source. Configuring
     * this Logger will affect all of the log messages generated by the data source.
     * In the worst case, this may be the root Logger.
     *
     * @return the parent Logger for this data source
     * @throws java.sql.SQLFeatureNotSupportedException
     *          if the data source does not use <code>java.util.logging<code>.
     * @since 1.7
     */
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return LogManager.getLogManager().getLogger(this.getClass().getName());
    }

    /**
     * Called by spring after properties have been set.
     *  
     * If connection url has not already been set creates the url from the
     * url template as defined in DBType. HOST, PORT, SERVICE and DBNAME strings in
     * url template will be replaced with actual values.
     * @throws RuntimeException if all required values have not been set
     */
    public void init() {
        if (getDbType() != null) {
            DBType type = DBType.valueOf(getDbType());

            if (getDriverClassName() == null) {
                setDriverClassName(type.getDriverClassName());
            }

            if (getUrl() == null) {
                String url = type.getUrl(
                      getHost(), getPort(), getService(), getDbName());
                setUrl(url);
            }

            setValidationQuery(type.getValidationQuery());
        }
    }

    // ----------- PROPERTIES -----------
    private String dbType;
    private String host;
    private String port;
    private String service;
    private String dbName;
    private String ownerName;

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        dbType = dbType.replaceAll(" ", "");
        dbType = dbType.toUpperCase();
        this.dbType = dbType;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getOwnerName() {
        return ownerName;
    }

    /**
     * Appends owner name to table name.
     *
     * @param tableName name of table
     *
     * @return {owner}.tableName
     */
    public String qualify(String tableName) {
        return getOwnerName() + "." + tableName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    /**
     * Overridden from org.apache.commons.dbcp.BasicDataSource
     * org.apache.commons.dbcp.BasicDataSource uses the this.password
     * instead of getPassword when creating and connecting to the
     * data source.  This routine ensures we connect using the correct
     * version of the password.
     * (see http://www.docjar.com/html/api/org/apache/commons/dbcp/BasicDataSource.java.html
     * for source code)
     *
     * @return DataSource
     */
    protected synchronized javax.sql.DataSource createDataSource()
          throws SQLException {
        return super.createDataSource();
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    /**
     * Returns an object that implements the given interface to allow access to
     * non-standard methods, or standard methods not exposed by the proxy.
     *
     * If the receiver implements the interface then the result is the receiver
     * or a proxy for the receiver. If the receiver is a wrapper
     * and the wrapped object implements the interface then the result is the
     * wrapped object or a proxy for the wrapped object. Otherwise return the
     * the result of calling <code>unwrap</code> recursively on the wrapped object
     * or a proxy for that result. If the receiver is not a
     * wrapper and does not implement the interface, then an <code>SQLException</code> is thrown.
     *
     * @param iface A Class defining an interface that the result must implement.
     *
     * @return an object that implements the interface. May be a proxy for the actual implementing object.
     *
     * @throws java.sql.SQLException If no object found that implements the interface
     * @since 1.6
     */
    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    /**
     * Returns true if this either implements the interface argument or is directly or indirectly a wrapper
     * for an object that does. Returns false otherwise. If this implements the interface then return true,
     * else if this is a wrapper then return the result of recursively calling <code>isWrapperFor</code> on the wrapped
     * object. If this does not implement the interface and is not a wrapper, return false.
     * This method should be implemented as a low-cost operation compared to <code>unwrap</code> so that
     * callers can use this method to avoid expensive <code>unwrap</code> calls that may fail. If this method
     * returns true then calling <code>unwrap</code> with the same argument should succeed.
     *
     * @param iface a Class defining an interface.
     *
     * @return true if this implements the interface or directly or indirectly wraps an object that does.
     *
     * @throws java.sql.SQLException if an error occurs while determining whether this is a wrapper
     * for an object with the given interface.
     * @since 1.6
     */
    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false; 
    }

}
