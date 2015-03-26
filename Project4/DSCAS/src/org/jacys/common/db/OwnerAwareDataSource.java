package org.jacys.common.db;

/**
 * Created by IntelliJ IDEA.
 * User: e8pigke
 * Date: Sep 21, 2010
 * Time: 1:40:24 PM
 * To change this template use File | Settings | File Templates.
 */
public interface OwnerAwareDataSource {

    public String getOwnerName();

    /**
     * Appends owner name to table name.
     *
     * @param tableName
     *
     * @return {owner}.tableName
     */
    public String qualify(String tableName);

}
