package com.codrut.spring_database_example.config;

import com.codrut.spring_database_example.dataAccessLayer.SingerDao;
import com.codrut.spring_database_example.dataAccessLayer.impl.JdbcSingerDao;
import com.codrut.spring_database_example.error_handling.MySQLErrorCodesTranslator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class EmbeddedJdbcConfig {
    private static Logger logger = LogManager.getLogger(EmbeddedJdbcConfig.class);

    @Bean
    public DataSource dataSource() {
        try {
            EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
            return builder.setType(EmbeddedDatabaseType.H2).addScripts("classpath:db/h2/schema.sql",
                    "classpath:db/h2/test-data.sql").build();
        }catch (Exception e) {
            logger.error("Embedded DataSource bean cannot be created!", e);
            return null;
        }
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        var template = new JdbcTemplate();

        var translator = new MySQLErrorCodesTranslator();
        translator.setDataSource(dataSource());

        template.setDataSource(dataSource());
        template.setExceptionTranslator(translator);
        return template;
    }

    @Bean
    public SingerDao singerDao() {
        var dao = new JdbcSingerDao();
        dao.setJdbcTemplate(jdbcTemplate());
        return dao;
    }
}
