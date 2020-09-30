package com.url.shortner.shorturl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UrlShortEvent {
	@Id
	@Column(name="url_id")
	private Long urlId;
	@Column(name="url_text")
	private String urlText;
	@Column(name="shorturl")
	private String shorturl;
	@Column(name="expiry_date")
	private Date expiryDate;

	public Long getUrlId() {
		return urlId;
	}

	public void setUrlId(Long urlId) {
		this.urlId = urlId;
	}

	public String getUrlText() {
		return urlText;
	}

	public void setUrlText(String urlText) {
		this.urlText = urlText;
	}

	public String getShorturl() {
		return shorturl;
	}

	public void setShorturl(String shorturl) {
		this.shorturl = shorturl;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public String toString() {
		return "UrlShortEvent [urlId=" + urlId + ", urlText=" + urlText + ", shorturl=" + shorturl + ", expiryDate="
				+ expiryDate + "]";
	}

	public UrlShortEvent() {
		super();
	}

	public UrlShortEvent(Long urlId, String urlText, String shorturl, Date expiryDate) {
		super();
		this.urlId = urlId;
		this.urlText = urlText;
		this.shorturl = shorturl;
		this.expiryDate = expiryDate;
	}

}
