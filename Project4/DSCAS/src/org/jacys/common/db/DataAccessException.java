package org.jacys.common.db;

/**
 * Identifies an error condition related to accessing data. Not limited to use with JDBC. Thrown by any DAO
 * implementation that encounters an error during process the data request.
 */
public class DataAccessException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataAccessException(String message) {
        super(message);
    }

    public DataAccessException(String message, Exception e) {
        super(e);
    }
}
