package com.JdbcTemplate.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JdbcTemplate.model.Novel;
import com.JdbcTemplate.repository.JdbcNovelRepository;

@Service
public class NovelService {
	@Autowired
	private JdbcNovelRepository jdbcnovelrepository;

	public String SaveNovel(Novel novel) {
		jdbcnovelrepository.SaveNovel(novel);
		return "Novel save with id" + novel.getId();

	}

	public List<Novel> getAllNovel(int id) {
		return jdbcnovelrepository.findById(id);

	}

	public int deleteById(int id) {
		jdbcnovelrepository.deleteById(id);
		return jdbcnovelrepository.deleteAll();

	}

	public List<Novel> findAll() {

		return jdbcnovelrepository.findAll();
	}

	public List<Novel> findByTitleContaining(String title) {
		jdbcnovelrepository.findByTitleContaining(title);

		return jdbcnovelrepository.findAll();
	}

	public List<Novel> findById(int id) {
		return jdbcnovelrepository.findById(id);
	}

	public void update(Novel novel) {
		jdbcnovelrepository.update(novel);
	}

	public int deleteAll() {
		return jdbcnovelrepository.deleteAll();

	}

	public List<Novel> findByPublished(boolean b) {
		return jdbcnovelrepository.findByPublished(b);

	}

}
