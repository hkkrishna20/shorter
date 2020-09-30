package com.url.shortner.shorturl.repo.template;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.url.shortner.shorturl.model.UrlShortEvent;

@Transactional
@Repository
public class UrlShortEventDaoImpl {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<UrlShortEvent> getAll() {
		String query = "SELECT * from url_short_event";
		RowMapper<UrlShortEvent> rowMapper = new UrlShortEventRowMapper();
		List<UrlShortEvent> list = jdbcTemplate.query(query, rowMapper);

		return list;
	}

	public List<UrlShortEvent> findById(String id) {
		String query = "SELECT * FROM url_short_event WHERE url_text = ?";
		RowMapper<UrlShortEvent> rowMapper = new UrlShortEventRowMapper();
		List<UrlShortEvent> UrlShortEvent = jdbcTemplate.query(query, rowMapper, id);
		return UrlShortEvent;
	}

	public void add(UrlShortEvent UrlShortEvent) {
		String query = "INSERT INTO url_short_event(url_text, shorturl, expiry_date)" + " VALUES(?, ?, ?)";

		jdbcTemplate.update(query, UrlShortEvent.getUrlText(), UrlShortEvent.getShorturl(),
				UrlShortEvent.getExpiryDate());

	}

	public void update(UrlShortEvent UrlShortEvent) {
		String query = "UPDATE url_short_event SET url_text=?, shorturl=?, expiry_date=? WHERE shorturl=?";
		jdbcTemplate.update(query, UrlShortEvent.getUrlText(), UrlShortEvent.getShorturl(),
				UrlShortEvent.getExpiryDate(), UrlShortEvent.getShorturl());
	}

	public void delete(UrlShortEvent id) {
		String query = "DELETE FROM url_short_event WHERE shorturl=?";
		jdbcTemplate.update(query, id.getShorturl());
	}

	public Object findLatest() {
		// TODO Auto-generated method stub
        String query = ("SELECT * FROM url_short_event ORDER BY url_id DESC LIMIT 1;");
        RowMapper<UrlShortEvent> rowMapper = new UrlShortEventRowMapper();
        UrlShortEvent url = jdbcTemplate.queryForObject(query, rowMapper);
		return url;
	}

}