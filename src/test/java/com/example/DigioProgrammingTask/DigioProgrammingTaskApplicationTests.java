package com.example.DigioProgrammingTask;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.DigioProgrammingTask.Controllers.ApplicationRestControllers;
import com.example.DigioProgrammingTask.HelperFiles.AnalyserHelper;
import com.example.DigioProgrammingTask.ModelClasses.LogResults;

@SpringBootTest
class DigioProgrammingTaskApplicationTests {

	@Test
	final void testUniqueIPAddressCount() {
		// Case 1 - 1 unique IP
		ArrayList<String> ipAddressList = new ArrayList<>();
		ipAddressList.add("11.11.11.11");
		long uniqueIpCount = AnalyserHelper.getNumberOfUniqueIpAddresses(ipAddressList);
		assertEquals(1, uniqueIpCount);

		// Case 2 - 2 unique IP
		ipAddressList.add("11.11.11.11");
		ipAddressList.add("22.22.22.22");
		uniqueIpCount = AnalyserHelper.getNumberOfUniqueIpAddresses(ipAddressList);
		assertEquals(2, uniqueIpCount);

		// Case 3 - 3 unique IP
		ipAddressList.add("11.11.11.11");
		ipAddressList.add("22.22.22.22");
		ipAddressList.add("33.2.1.0");
		uniqueIpCount = AnalyserHelper.getNumberOfUniqueIpAddresses(ipAddressList);
		assertEquals(3, uniqueIpCount);

		// Case 4 - No unique IP
		ipAddressList.clear();
		uniqueIpCount = AnalyserHelper.getNumberOfUniqueIpAddresses(ipAddressList);
		assertEquals(0, uniqueIpCount);
	}
	
	@Test
	final void testGetMostVisitedUrls() {
		// Case 1 - Only 1 URL
		ArrayList<String> inputURLList = new ArrayList<>();
		inputURLList.add("/assets");
		ArrayList<String> topThreeURLs = AnalyserHelper.getTopThreeVisitedUrls(inputURLList);
		assertEquals(1, topThreeURLs.size());
		assertEquals("/assets", topThreeURLs.get(0));

		// Case 2 - 2 unique URLs
		inputURLList.add("https://www.google.com");
		inputURLList.add("https://www.google.com");
		topThreeURLs = AnalyserHelper.getTopThreeVisitedUrls(inputURLList);
		assertEquals(2, topThreeURLs.size());
		assertEquals("https://www.google.com", topThreeURLs.get(0));

		// Case 3 - 3 unique IP
		inputURLList.add("https://www.google.com/page1");
		inputURLList.add("https://www.google.com/page2");
		inputURLList.add("/assets/page1");
		topThreeURLs = AnalyserHelper.getTopThreeVisitedUrls(inputURLList);
		assertEquals(2, topThreeURLs.size());
		assertEquals("https://www.google.com", topThreeURLs.get(0));

		// Case 4 - Check empty 
		inputURLList.clear();
		topThreeURLs = AnalyserHelper.getTopThreeVisitedUrls(inputURLList);
		assertEquals(0, topThreeURLs.size());
	}
	
	@Test
	final void testGetMostActiveIpAddresses() {
		// Case 1 - Only 1 Top IP
		ArrayList<String> ipAddressList = new ArrayList<>();
		ipAddressList.add("11.11.11.11");
		ArrayList<String> topThreeIps = AnalyserHelper.getTopThreeActiveIpAddresses(ipAddressList);
		assertEquals(1, topThreeIps.size());
		assertEquals("11.11.11.11", topThreeIps.get(0));

		// Case 2 - 2 Top IP
		ipAddressList.add("11.11.11.11");
		ipAddressList.add("12.21.12");
		topThreeIps = AnalyserHelper.getTopThreeActiveIpAddresses(ipAddressList);
		assertEquals(2, topThreeIps.size());
		//Returns 11.11.11.11 as it has received twice 
		assertEquals("11.11.11.11", topThreeIps.get(0));
		assertEquals("12.21.12", topThreeIps.get(1));


		// Case 3 - 3 Top IP
		ipAddressList.add("11.11.11.11");
		ipAddressList.add("12.21.12");
		ipAddressList.add("33.33.33");
		topThreeIps = AnalyserHelper.getTopThreeActiveIpAddresses(ipAddressList);
		assertEquals(3, topThreeIps.size());
		//Returns in the order of most received
		assertEquals("11.11.11.11", topThreeIps.get(0));
		assertEquals("12.21.12", topThreeIps.get(1));
		assertEquals("33.33.33", topThreeIps.get(2));

		// Case 4 - No IPs, empty log file check
		ipAddressList.clear();
		topThreeIps = AnalyserHelper.getTopThreeActiveIpAddresses(ipAddressList);
		assertEquals(0, topThreeIps.size());
	}
	
}
