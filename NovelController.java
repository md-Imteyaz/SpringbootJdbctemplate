package com.JdbcTemplate.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.JdbcTemplate.Service.NovelService;
import com.JdbcTemplate.model.Novel;
import com.JdbcTemplate.repository.JdbcNovelRepository;

//@CrossOrigin(origins = "http://localhost:9091")
@RestController
@RequestMapping("/api")
public class NovelController {
	@Autowired
	private NovelService novelservice;

	@GetMapping("/allnovel")
	public ResponseEntity<List<Novel>> getAllNovel(@RequestParam(required = true) String title) {
		try {
			List<Novel> novel = new ArrayList<Novel>();
			if (title == null)
				novelservice.findAll().forEach(novel::add);
			else
				novelservice.findByTitleContaining(title).forEach(novel::add);
			if (novel.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(novel, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/novel/{id}")
	public ResponseEntity<List<Novel>> getNovelById(@PathVariable("id") int id) {
		List<Novel> novel = novelservice.findById(id);
		if (novel != null) {
			return new ResponseEntity<List<Novel>>(novel, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Novel>>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/novel")
	public ResponseEntity<String> SaveNovel(@RequestBody Novel novel) {
		try {
			novelservice.SaveNovel(new Novel(novel.getTitle(), novel.getDescription(), novel.isPublished()));
			return new ResponseEntity<>("Novel was updated successfully.", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/novel/{id}")
	public ResponseEntity<String> updatenovel(@PathVariable("id") int id, @RequestBody Novel novel) {
		List<Novel> novels = novelservice.findById(id);
		if (novel != null) {
			novel.setId(id);
			novel.setTitle(novel.getTitle());
			novel.setDescription(novel.getDescription());
			novel.setPublished(novel.isPublished());
			novelservice.update(novel);
			return new ResponseEntity<>("novel was updated successfully.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Cannot find novel with id=" + id, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/novel/{id}")
	public ResponseEntity<String> deleteNovel(@PathVariable("id") int id) {
		try {
			int result = novelservice.deleteById(id);
			if (result == 0) {
				return new ResponseEntity<>("Cannot find Novel with id=" + id, HttpStatus.OK);
			}
			return new ResponseEntity<>("Novel was deleted successfully.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Cannot delete novel.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/novel")
	public ResponseEntity<String> deleteAllNovels() {
		try {
			int numRows = novelservice.deleteAll();
			return new ResponseEntity<>("Deleted " + numRows + " Tutorial(s) successfully.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Cannot delete novel", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/novel/published")
	public ResponseEntity<List<Novel>> findByPublished() {
		try {
			List<Novel> novel = novelservice.findByPublished(true);
			if (novel.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(novel, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
