package com.JdbcTemplate.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.JdbcTemplate.model.Novel;

public class RowMapperImpl implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Novel novel=new Novel();
		novel.setId(rs.getInt(1));
		novel.setTitle(rs.getString(2));
		novel.setDescription(rs.getString(3));
		novel.setPublished(rs.getBoolean(4));
		return novel;
	}

}
