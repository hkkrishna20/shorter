package com.url.shortner.shorturl.dto;

import java.util.Date;

public class UrlResponseDTO {
	private String url;
	private String shorturl;
	private Date expiresin;

	public UrlResponseDTO(String url, String shorturl, Date expiresin) {
		super();
		this.url = url;
		this.shorturl = shorturl;
		this.expiresin = expiresin;
	}

	public Date getExpiresin() {
		return expiresin;
	}

	public void setExpiresin(Date expiresin) {
		this.expiresin = expiresin;
	}

	@Override
	public String toString() {
		return "UrlResponseDTO [url=" + url + ", shorturl=" + shorturl + ", expiresin=" + expiresin + "]";
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public UrlResponseDTO(String url, String shorturl) {
		super();
		this.url = url;
		this.shorturl = shorturl;
	}

	public UrlResponseDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getShorturl() {
		return shorturl;
	}

	public void setShorturl(String shorturl) {
		this.shorturl = shorturl;
	}

}
