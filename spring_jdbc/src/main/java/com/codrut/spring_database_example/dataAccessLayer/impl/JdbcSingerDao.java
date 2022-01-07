package com.codrut.spring_database_example.dataAccessLayer.impl;

import com.codrut.spring_database_example.dataAccessLayer.SingerDao;
import com.codrut.spring_database_example.domain.Singer;
import lombok.Setter;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Types;
import java.util.List;
import java.util.Optional;

import static com.codrut.spring_database_example.util.DateConverter.convertDateToLocalDate;

@Setter
public class JdbcSingerDao implements SingerDao, InitializingBean {

    private JdbcTemplate jdbcTemplate;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (jdbcTemplate == null) {
            throw new BeanCreationException("Must set template on SingerDao");
        }
    }

    @Override
    public List<Singer> findAll() {
        var query = "SELECT id, first_name, last_name, birth_date FROM singer";

        return jdbcTemplate.query(query, (rs, rowNum) -> {
            var singer = new Singer();
            singer.setId(rs.getLong("id"));
            singer.setFirstName(rs.getString("first_name"));
            singer.setLastName(rs.getString("last_name"));
            singer.setBirthDate(convertDateToLocalDate(rs.getDate("birth_date")));
            return singer;
        });
    }

    @Override
    public List<Singer> findByFirstName(String firstName) {
        var query = "SELECT id, first_name, last_name, birth_date FROM singer WHERE first_name=?";

        return jdbcTemplate.query(query, new Object[]{firstName}, new int[]{Types.VARCHAR}, (rs, rowNum) -> {
            var singer = new Singer();
            singer.setId(rs.getLong("id"));
            singer.setFirstName(rs.getString("first_name"));
            singer.setLastName(rs.getString("last_name"));
            singer.setBirthDate(convertDateToLocalDate(rs.getDate("birth_date")));
            return singer;
        });
    }

    @Override
    public Optional<String> findLastNameById(Long id) {
        String query = "SELECT last_name FROM singer WHERE id = ?";
        var result = jdbcTemplate.queryForObject(query, new Object[]{id}, new int[]{Types.INTEGER}, String.class);
        return Optional.ofNullable(result);
    }

    @Override
    public Optional<String> findFirstNameById(Long id) {
        String query = "SELECT first_name FROM singer WHERE id = ?";
        var result = jdbcTemplate.queryForObject(query, new Object[]{id}, new int[]{Types.INTEGER}, String.class);
        return Optional.ofNullable(result);
    }

    @Override
    public void insert(Singer singer) {

    }

    @Override
    public void update(Singer singer) {

    }

    @Override
    public void delete(Long singerId) {

    }
}
