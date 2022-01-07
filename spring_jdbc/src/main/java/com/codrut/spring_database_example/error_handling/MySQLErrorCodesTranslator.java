package com.codrut.spring_database_example.error_handling;

import org.springframework.dao.CleanupFailureDataAccessException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

import java.sql.SQLException;

public class MySQLErrorCodesTranslator extends SQLErrorCodeSQLExceptionTranslator {
    @Override
    protected DataAccessException customTranslate(String task, String sql, SQLException sqlEx) {
        return switch (sqlEx.getErrorCode()) {
            case -12345 -> new DeadlockLoserDataAccessException(task, sqlEx);
            case 12345-> new CleanupFailureDataAccessException(task, sqlEx);
            default -> null;
        };
    }
}
