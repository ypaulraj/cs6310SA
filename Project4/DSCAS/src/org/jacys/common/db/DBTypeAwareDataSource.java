package org.jacys.common.db;

/**
 * Identifies the Database Type of the data source.
 */
public interface DBTypeAwareDataSource {

    public String getDbType();

}
