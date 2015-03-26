package org.jacys.common.db;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

/**
 * Provides some simple select helper methods.
 */
public class SpringJdbc<T> {
    private Log logger = LogFactory.getLog(this.getClass());

    protected DataSource dataSource;
    protected RowMapper<T> mapper;
    protected String tableName;
    protected String[] selectColumns;

    protected SpringJdbc() {
    }

    public SpringJdbc(DataSource dataSource, RowMapper<T> mapper, String tableName, String[] selectColumns) {
        this.dataSource = dataSource;
        this.mapper = mapper;
        this.tableName = tableName;
        this.selectColumns = selectColumns;
    }

    /**
     * Count rows matching selection criteria or all rows if no selection criteria is provided.
     *
     * @param whereColumns array of database column names
     * @param values       one or more value (should be same number of values as column names)
     * @return count of rows matching selection criteria
     */
    public long count(String[] whereColumns, Object... values) throws DataAccessException {
        return selectLong("count(*)", whereColumns, values);
    }

    /**
     * Retrieve all records.
     *
     * @return list of populated domain objects if found; empty list otherwise
     */
    public List<T> selectAll() throws DataAccessException {
        return selectAll(null);
    }

    /**
     * Retrieve all records.
     *
     * @param orderByClause order by clause will be appended to select statement as is (e.g. order by SYSTEM_RECORD_ID DSND)
     * @return list of populated domain objects if found; empty list otherwise
     */
    public List<T> selectAll(String orderByClause) throws DataAccessException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = buildSelect(this.selectColumns, (String) null, orderByClause);
        try {
            return jdbcTemplate.query(sql, mapper);
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Perform select of all columns expecting to result in selection of one record.
     *
     * @param whereColumns array of database column names
     * @param values       one or more values (should be same number of values as where columns)
     * @return populated domain object if found; null otherwise
     */
    public T selectOne(String[] whereColumns, Object... values) throws DataAccessException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = buildSelect(this.selectColumns, whereColumns, null);
        try {
            return (T) jdbcTemplate.queryForObject(sql, mapper, values);
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Perform prepared where clause (i.e. where {column} > ?)
     * expecting to result in selection of one record.
     *
     * @param whereClause where clause with value placeholders in where clause (i.e. ?)
     * @param values      one or more values (should be same number of values as where columns)
     * @return populated domain object if found; null otherwise
     */
    public T selectOne(String whereClause, Object... values) throws DataAccessException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = buildSelect(this.selectColumns, whereClause, null);
        try {
            return (T) jdbcTemplate.queryForObject(sql, mapper, values);
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Perform select of all columns expecting to result in selection of one record.
     *
     * @param whereColumns names of database columns
     * @param values       one or more value (should be same number of values as <code>this.selectColumns</code>)
     * @param values       one or more values (should be same number of values as where columns)
     * @return list of populated domain objects if found; null otherwise
     */
    public List<T> select(String[] whereColumns, Object... values) throws DataAccessException {
        return select(whereColumns, null, values);
    }

    /**
     * Perform select of all columns expecting to result in selection of one record.
     *
     * @param whereColumns names of database columns
     * @param values       one or more value (should be same number of values as <code>this.selectColumns</code>)
     * @param values       one or more values (should be same number of values as where columns)
     * @return list of populated domain objects if found; null otherwise
     */
    public List<T> select(String[] whereColumns, String orderByClause, Object... values) throws DataAccessException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = buildSelect(selectColumns, whereColumns, null);
        try {
            return (List<T>) jdbcTemplate.query(sql, mapper, values);
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Perform select of all columns expecting to result in selection of one record.
     *
     * @param whereClause sql where clause with value placeholders in where clause (i.e. ?)
     * @param values      one or more values (should be same number of values as where columns)
     * @return list of populated domain objects if found; null otherwise
     */
    public List<T> select(String whereClause, String orderByClause, Object... values) throws DataAccessException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = buildSelect(selectColumns, whereClause, orderByClause);

        logger.debug("select() statement=" + sql);
        //System.out.println("select() statement=" + sql);

        try {
            return (List<T>) jdbcTemplate.query(sql, mapper, values);
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Perform prepared sql statement (i.e. select * TOP 1 from {table} where {column} > ?)
     * expecting to result in selection of one or more records.
     *
     * @param whereClause sql where clause with value placeholders in where clause (i.e. ?)
     * @param values      one or more values (should be same number of values as where columns)
     * @return list of populated domain objects if found; null otherwise
     */
    public List<T> select(String whereClause, Object... values) throws DataAccessException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = buildSelect(this.selectColumns, whereClause, null);
        try {
            return (List<T>) jdbcTemplate.query(sql, mapper, values);
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Perform select expecting result to be one row containing an int value.
     *
     * @param selectColumn name of database column to select
     * @param whereColumns array of database column names
     * @param values       one or more values (should be same number of values as where columns)
     * @return an int value or 0 if no rows selected
     */
    public int selectInt(String selectColumn, String[] whereColumns, Object... values) throws DataAccessException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = buildSelect(new String[]{selectColumn}, whereColumns, null);
        try {
            return jdbcTemplate.queryForObject(sql, values, Integer.class);
        }
        catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    /**
     * Perform select expecting result to be one row containing an int value.
     *
     * @param selectColumn name of database column to select
     * @param whereClause  sql where clause with value placeholders in where clause (i.e. ?)
     * @param values       one or more values (should be same number of values as where columns)
     * @return an int value or 0 if no rows selected
     */
    public int selectInt(String selectColumn, String whereClause, Object... values) throws DataAccessException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = buildSelect(new String[]{selectColumn}, whereClause, null);
        try {
            return jdbcTemplate.queryForObject(sql, values, Integer.class);
        }
        catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    /**
     * Perform select expecting result to be one row containing an long value.
     *
     * @param selectColumn name of database column to select
     * @param whereColumns array of database column names
     * @param values       one or more values (should be same number of values as where columns)
     * @return an long value or 0 if no rows selected
     */
    public long selectLong(String selectColumn, String[] whereColumns, Object... values) throws DataAccessException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = buildSelect(new String[]{selectColumn}, whereColumns, null);
        try {
            return jdbcTemplate.queryForObject(sql, values, Long.class);
        }
        catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    /**
     * Perform select expecting result to be one row containing an long value.
     *
     * @param selectColumn name of database column to select
     * @param whereClause  sql where clause with value placeholders in where clause (i.e. ?)
     * @param values       one or more values (should be same number of values as where columns)
     * @return an long value or 0 if no rows selected
     */
    public long selectLong(String selectColumn, String whereClause, Object... values) throws DataAccessException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = buildSelect(new String[]{selectColumn}, whereClause, null);
        try {
            return jdbcTemplate.queryForObject(sql, values, Long.class);
        }
        catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    // ----------------------------- SQL Statement Builder methods -----------------------------------

    protected String buildSelect(String[] selectColumns, String[] whereColumns, String orderByClause) {
        return _select(selectColumns) + _where(whereColumns) + _orderBy(orderByClause);
    }

    protected String buildSelect(String[] selectColumns, String whereClause, String orderByClause) {
        return _select(selectColumns) + _whereClause(whereClause) + _orderBy(orderByClause);
    }

    protected String _select(String[] selectColumns) {
        StringBuffer sqlbuf = new StringBuffer();

        String buf = "select ";

        if (selectColumns != null) {
            for (String selectColumn : selectColumns) {
                sqlbuf.append(buf + selectColumn);
                buf = ",";
            }
        } else {
            sqlbuf.append(buf + " *");
        }

        sqlbuf.append(" from " + tableName);

        return sqlbuf.toString();
    }

    protected String _where(String[] whereColumns) {

        if (whereColumns != null) {
            StringBuffer sqlbuf = new StringBuffer();
            String buf = " where ";
            for (String whereColumn : whereColumns) {
                sqlbuf.append(buf + whereColumn + " = ?");
                buf = " and ";
            }
            return sqlbuf.toString();
        } else {
            return "";
        }
    }

    protected String _whereClause(String whereClause) {

        if (whereClause != null) {
            return " " + whereClause;
        } else {
            return "";
        }
    }

    protected String _orderBy(String orderByClause) {

        if (orderByClause != null) {
            return " " + orderByClause;
        } else {
            return "";
        }
    }

}
