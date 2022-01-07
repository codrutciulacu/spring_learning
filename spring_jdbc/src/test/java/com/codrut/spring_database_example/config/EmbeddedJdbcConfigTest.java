package com.codrut.spring_database_example.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class EmbeddedJdbcConfigTest {
    private static final Logger logger = LogManager.getLogger(DbConfigTest.class);

    @Test
    void shouldHaveSuccessfulConnectionWithH2EmbeddedDatasource() throws SQLException {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(EmbeddedJdbcConfig.class);

        DataSource dataSource = ctx.getBean("dataSource", DataSource.class);
        assertNotNull(dataSource);

        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT 1");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int mockVal = resultSet.getInt('1');
                assertEquals(1, mockVal);
            }
            statement.close();
        } catch (Exception e) {
            logger.debug("Something unexpected happened.", e);
        } finally {
            if(connection != null)
                connection.close();
        }

        ctx.close();
    }
}