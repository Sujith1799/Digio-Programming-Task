package com.example.DigioProgrammingTask.HelperFiles;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

/*
 * Log Results Model class,
 * to return the log analysis
 * results
 */
public class AnalyserHelper {

	/*
	 * Function to get the no of unique Ip addresses
	 */
	public static long getNumberOfUniqueIpAddresses(ArrayList<String> inputListOfIpAddress) {
		// HashSet to store only the unique Ip addresses
		Set<String> uniqueIpAddressesCount = new HashSet<String>();
		for (String ip : inputListOfIpAddress) {
			// Remove any trailing whitespaces
			uniqueIpAddressesCount.add(ip.trim());

		}
		return uniqueIpAddressesCount.size();
	}

	/*
	 * Function to get the top 3 most visited URLs
	 */
	public static ArrayList<String> getTopThreeVisitedUrls(ArrayList<String> inputListOfUrls) {

		// String ArrayList to store filteredUrls
		ArrayList<String> filteredUrls = new ArrayList<>();

		for (String url : inputListOfUrls) {
			// For URLs like http & https
			if (!url.startsWith("/") && !url.isEmpty()) {
				// Only get the root of the url
				String[] getRootOfUrl = url.split("(?<!\\/)\\/(?!\\/)");
				filteredUrls.add(getRootOfUrl[0]);
			}

			// For URLs like /assets.js/.. and /assets.js/page1
			// Just consider the root which will be /assets
			else if (url.startsWith("/") && url.length() > 1) {
				String[] getRootOfUrl = url.split("/");
				filteredUrls.add("/"+getRootOfUrl[1]);
			}

			// For all other url types
			else {
				filteredUrls.add(url);
			}
		}

		// Creating a map to group elements and get their no.of occurrences
		Map<String, Long> urlMap = filteredUrls.stream().collect(Collectors.groupingBy(w -> w, Collectors.counting()));

		// Sorting the elements, will get first 10 most popular entries in the stream
		List<Map.Entry<String, Long>> urlResult = urlMap.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(10).collect(Collectors.toList());

		// Getting the top 3 most visited URLs
		ArrayList<String> mostActiveUrlList = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			if (urlResult.size() > i && urlResult.get(i) != null) {
				mostActiveUrlList.add(urlResult.get(i).getKey());
			}
		}

		return mostActiveUrlList;
	}

	/*
	 * Function to get the top 3 most Active IP Addresses
	 */
	public static ArrayList<String> getTopThreeActiveIpAddresses(ArrayList<String> inputListOfIpAddress) {

		// Creating a map of Ip addresses and their no.of occurrences
		Map<String, Long> map = inputListOfIpAddress.stream()
				.collect(Collectors.groupingBy(w -> w, Collectors.counting()));

		// Sorting the elements, will get the first top 10 popular IP addresses
		List<Map.Entry<String, Long>> result = map.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(10).collect(Collectors.toList());

		// Return the first top 3 active IPs, can be increased in future
		ArrayList<String> mostActiveIpList = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			if (result.size() > i && result.get(i) != null) {
				mostActiveIpList.add(result.get(i).getKey().trim());
			}
		}
		
		return mostActiveIpList;

	}

}