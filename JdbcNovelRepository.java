package com.JdbcTemplate.repository;

import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.JdbcTemplate.RowMapper.RowMapperImpl;
import com.JdbcTemplate.model.Novel;

@Repository
public class JdbcNovelRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired(required = false)
	private HashMap<String, String> queries;

	private SimpleJdbcInsert simplejdbcInsert;

	private JdbcTemplate JdbcTemplate;
	@Autowired
	private DataSource dataSource;

//	@PostConstruct
//	private void postconstruct() {
//		JdbcTemplate = new JdbcTemplate(dataSource);
//		simplejdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("novel").withSchemaName("public")
//				.usingGeneratedKeyColumns("quantity");
//	}

	public List<Novel> findAll() {
		return jdbcTemplate.query("SELECT * from novel", BeanPropertyRowMapper.newInstance(Novel.class));
	}

	public List<Novel> findById(int id) {
		try {
			Novel novel = jdbcTemplate.queryForObject("SELECT * FROM novel WHERE id=?",
					BeanPropertyRowMapper.newInstance(Novel.class), id);
			return (List<Novel>) novel;
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}
	}

	public int SaveNovel(Novel novel) {
		return jdbcTemplate.update("INSERT INTO novel (title, description, published) VALUES(?,?,?)",
				new Object[] { novel.getTitle(), novel.getDescription(), novel.isPublished() });
	}

	public int update(Novel novel) {
		return jdbcTemplate.update("UPDATE novel SET title=?, description=?, published=? WHERE id=?",
				new Object[] { novel.getTitle(), novel.getDescription(), novel.isPublished(), novel.getId() });
	}

	public int deleteById(int id) {
		return jdbcTemplate.update("DELETE FROM novel WHERE id=?", id);
	}

	public List<Novel> findByPublished(boolean published) {
		return jdbcTemplate.query("SELECT * from novel WHERE published=?",
				BeanPropertyRowMapper.newInstance(Novel.class), published);
	}

	public List<Novel> findByTitleContaining(String title) {
		String q = "SELECT * from tutorials WHERE title ILIKE '%" + title + "%'";
		return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Novel.class));
	}

	public int deleteAll() {
		return jdbcTemplate.update("DELETE from Novel");
	}

	public Novel getNovel(int id) {
		String query = "select * from Student where id=?";
		RowMapper<Novel> rowmapper = new RowMapperImpl();
		Novel novel = this.jdbcTemplate.queryForObject(query, rowmapper, id);
		return novel;

	}

}
