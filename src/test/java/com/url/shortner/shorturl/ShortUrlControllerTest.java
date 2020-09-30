package com.url.shortner.shorturl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.url.shortner.shorturl.dto.UrlReqDTO;
import com.url.shortner.shorturl.dto.UrlResponseDTO;

public class ShortUrlControllerTest extends ShortUrlApplicationTests {
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void redirectApi() throws Exception {
		String uri = "api/cadd6Zk3V0K?operation=redirect";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		UrlResponseDTO responseDto = super.mapFromJson(content, UrlResponseDTO.class);
		assertTrue(responseDto != null);
	}

	@Test
	public void generateShortUrlApi() throws Exception {
		String uri = "api/%20?operation=generate&url=https://www.google.com/";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		UrlResponseDTO responseDto = super.mapFromJson(content, UrlResponseDTO.class);
		assertTrue(responseDto != null);

	}

	@Test
	public void generateShortUrlPost() throws Exception {
		String uri = "/generate";
		UrlReqDTO urldto = new UrlReqDTO("https://www.google.com/");
		String inputJson = super.mapToJson(urldto);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

		String content = mvcResult.getResponse().getContentAsString();
		UrlResponseDTO responseDto = super.mapFromJson(content, UrlResponseDTO.class);
		assertTrue(responseDto != null);

	}

}