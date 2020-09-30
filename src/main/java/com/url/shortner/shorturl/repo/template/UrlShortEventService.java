package com.url.shortner.shorturl.repo.template;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.url.shortner.shorturl.model.UrlShortEvent;

@Service
public class UrlShortEventService {

	@Autowired
	UrlShortEventDaoImpl dao;

	public List<UrlShortEvent> getAll() {
		return dao.getAll();
	}

	public List<UrlShortEvent> findById(String id) {

		return dao.findById(id);
	}

	public void add(UrlShortEvent url) {
		dao.add(url);
	}

	public Object update(UrlShortEvent url) {
		dao.update(url);
		return url;
	}

	public Object delete(UrlShortEvent id) {
		dao.delete(id);
		return id;
	}
	public Object latest() {
		return dao.findLatest();
	}

}
