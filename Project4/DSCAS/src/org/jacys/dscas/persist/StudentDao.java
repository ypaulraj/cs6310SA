package org.jacys.dscas.persist;

import org.jacys.common.db.DataAccessException;
import org.jacys.dscas.model.Student;

/**
 * Defines Student access methods required.
 */
public interface StudentDao {

    /**
     * Find Student by id.
     * 
     * @param studentId
     * @return student record
     */
    public Student find(int studentId) throws DataAccessException;
}
