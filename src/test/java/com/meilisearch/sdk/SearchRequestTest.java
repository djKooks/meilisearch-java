package com.meilisearch.sdk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchRequestTest {

	@Test
	void getQuery() {
		SearchRequest classToTest = new SearchRequest("This is a Test");
		assertEquals("?q=This%20is%20a%20Test&offset=0&limit=20&attributesToRetrieve=*&cropLength=200&matches=false", classToTest.getQuery());
		classToTest = new SearchRequest("This is a Test", 200);
		assertEquals("?q=This%20is%20a%20Test&offset=200&limit=20&attributesToRetrieve=*&cropLength=200&matches=false", classToTest.getQuery());
		classToTest = new SearchRequest("This is a Test", 200, 900);
		assertEquals("?q=This%20is%20a%20Test&offset=200&limit=900&attributesToRetrieve=*&cropLength=200&matches=false", classToTest.getQuery());
		classToTest = new SearchRequest("This is a Test", 200, 900, new String[]{"bubble"});
		assertEquals("?q=This%20is%20a%20Test&offset=200&limit=900&attributesToRetrieve=bubble&cropLength=200&matches=false", classToTest.getQuery());
		classToTest = new SearchRequest("This is a Test", 200, 900, new String[]{"bubble"}, new String[]{"crop"}, 900, new String[]{"highlight"}, "test", true);
		assertEquals("?q=This%20is%20a%20Test&offset=200&limit=900&attributesToRetrieve=bubble&cropLength=900&matches=true&attributesToCrop=crop&attributesToHighlight=highlight&filters=test", classToTest.getQuery());

		assertEquals("This%20is%20a%20Test", classToTest.getQ());
		assertEquals(200, classToTest.getOffset());
		assertEquals(900, classToTest.getLimit());
		assertEquals("bubble", classToTest.getAttributesToRetrieve()[0]);
		assertEquals("highlight", classToTest.getAttributesToHighlight()[0]);
		assertEquals("crop", classToTest.getAttributesToCrop()[0]);
		assertEquals("test", classToTest.getFilters());
		assertEquals(900, classToTest.getCropLength());
	}
}
