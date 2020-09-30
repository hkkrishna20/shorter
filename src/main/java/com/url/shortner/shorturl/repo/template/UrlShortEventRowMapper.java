package com.url.shortner.shorturl.repo.template;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.url.shortner.shorturl.model.UrlShortEvent;

public class UrlShortEventRowMapper implements RowMapper<UrlShortEvent> {

	@Override
	public UrlShortEvent mapRow(ResultSet rs, int rowNum) throws SQLException {
		UrlShortEvent url = new UrlShortEvent();
		url.setUrlId(rs.getLong("url_id"));
		url.setExpiryDate(rs.getDate("expiry_date"));
		url.setShorturl(rs.getString("shorturl"));
		url.setUrlText(rs.getString("url_text"));
		return url;
	}

}