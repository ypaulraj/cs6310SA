/**
 * Copyright 2005. Per-Se Technologies, Inc. All rights reserved.
 *
 * Proprietary & Confidential
 *
 * This source file and its contents ("this document") are the confidential and
 * trade secret property of PST Products, LLC d/b/a "Per-Se Technologies" or
 * "Per-Se."  Receipt of this document in any form does not convey any right to
 * possess, use, copy, reproduce, translate, transmit or disclose its contents
 * - or to manufacture or sell anything described within this document - in
 * whole or in part, without Per-Se's written consent.  This document is only
 * for use of Per-Se employees in performance of their jobs, and of authorized
 * parties in performance of their jobs under the terms of a written agreement
 * with Per-Se.  If you have received this document and are not a Per-Se
 * employee or authorized party, please call the telephone number below to
 * arrange for return of this document.
 *
 * Per-Se Technologies, 1145 Sanctuary Parkway, Suite 200,
 * Alpharetta, Georgia, 30004, USA.  (800)442-6767.
 */

package org.jacys.common.db;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
public abstract class JdbcDao {

    protected static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

    protected DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean sToB(String stringValue) {
        boolean value = false;
        if (stringValue != null && (stringValue.equals("1") || stringValue.toUpperCase().equals("T") || stringValue.toUpperCase().equals("Y"))) {
            value = true;
        }
        return value;
    }

    public String bToS(boolean value) {
        String stringValue = "0";
        if (value) {
            stringValue = "1";
        }
        return stringValue;
    }

    /**
     * Converts String to char.
     *
     * @param string a string containing a single character
     * @return first character of string or '\u0000' if null
     */
    public char toChar(String string) {
        char character = '\u0000';
        if (string != null && string.length() > 0) {
            character = string.charAt(0);
        }
        return character;

    }

    /**
     * If datasource is an instance of OwnerAwareDataSource, the owner name
     * will be appended to the table name.
     *
     * @param tableName
     * @return qualified table name w/owner appended if applicable
     */
    public String qualifyTableName(String tableName) {
        if (dataSource instanceof OwnerAwareDataSource) {
            return ((OwnerAwareDataSource) dataSource).getOwnerName() + "." +
                    tableName;
        } else {
            return tableName;
        }
    }

    protected String previewSql(String sql, Object[] parameters) {
        for (Object parameter : parameters) {
            String value;
            if (parameter instanceof Date) {
                value = "'" + new SimpleDateFormat(DEFAULT_DATE_PATTERN).format(parameter) + "'";
            } else {
                value = (parameter instanceof String ? "'" : "") + parameter.toString() + (parameter instanceof String ? "'" : "");
            }

            sql = sql.replaceFirst("\\?", value);
        }
        return sql;
    }
}
