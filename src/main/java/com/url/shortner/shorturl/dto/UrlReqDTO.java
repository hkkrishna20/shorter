package com.url.shortner.shorturl.dto;

public class UrlReqDTO {
	private String url;

	public String getUrl() {
		return url;
	}

	public UrlReqDTO(String url) {
		super();
		this.url = url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "UrlReqDTO [url=" + url + "]";
	}

}
