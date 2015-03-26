package org.jacys.dscas.persist.jdbc;

import org.jacys.common.db.DataAccessException;
import org.jacys.common.db.JdbcDao;
import org.jacys.common.db.SpringJdbc;
import org.jacys.dscas.model.Student;
import org.jacys.dscas.persist.StudentDao;

import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * JDBC implementation of StudentDao.
 * @param <StudentJdbcDao>
 */
public class StudentJdbcDao extends JdbcDao implements StudentDao {

    public static final String TABLE_NAME = "STUDENT";

    private SpringJdbc<Student> springJdbc;

	protected RowMapper<Student> mapper = new RowMapper<Student>() {
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            Student Student = new Student();
            Student.setStudentId(rs.getInt("ID"));
            Student.setName(rs.getString("Student_NAME"));
            return Student;
        }
    };

    public StudentJdbcDao(DataSource dataSource) {
        this.dataSource = dataSource;
        this.springJdbc = new SpringJdbc<Student>(dataSource, mapper,
                qualifyTableName(TABLE_NAME), new String[]{"DIVISION_ID", "Student_ID", "Student_NAME", "Student_START_DATE",
                        "SCHED_SHIFT1_LENGTH", "SCHED_SHIFT2_LENGTH", "SCHED_SHIFT3_LENGTH",
                        "USE_MCP", "USE_MCP_PROJECTIONS", "MCP_PROJECTION_DAYS"});
    }

    /**
     * Find Student with student id.
     *
     * @param studentId
     * @return
     * @throws DataAccessException
     */
    @Override
    public Student find(int studentId) throws DataAccessException {
        throw new DataAccessException("Not implemented...Use StudentCacheDao instead.");
    }

    /**
     * Get list of Student ordered by student id (ascending).
     *
     * @return list of Student objects
     */
    public List<Student> findAll() throws DataAccessException {
        try {
            List<Student> list = springJdbc.selectAll("ORDER BY id");
            return list;
        } catch (CannotGetJdbcConnectionException ex) {
            throw new DataAccessException("Unable to get database connection");
        }
    }

}