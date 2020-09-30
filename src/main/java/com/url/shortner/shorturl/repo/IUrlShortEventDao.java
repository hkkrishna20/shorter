package com.url.shortner.shorturl.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.url.shortner.shorturl.model.UrlShortEvent;

@Transactional
@Repository
public interface IUrlShortEventDao extends JpaRepository<UrlShortEvent, Long> {

	UrlShortEvent findByShorturl(String shortUrl);

}
