package com.example.DigioProgrammingTask.Controllers;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DigioProgrammingTask.HelperFiles.AnalyserHelper;
import com.example.DigioProgrammingTask.ModelClasses.LogResults;
import com.example.DigioProgrammingTask.ModelClasses.LogResultsStorage;
import com.example.DigioProgrammingTask.Repositories.LogResultsRepository;

/*
 * Controller Class
 * Can be extended in future
 */
@RestController
@RequestMapping("/api/v1")
public class ApplicationRestControllers {

	@Autowired
	LogResultsRepository LogResultsRepository;

	@CrossOrigin
	@GetMapping("/analyzeLogs/{logFileName}")
	public LogResults analyzeLogs(@PathVariable String logFileName) {
		// Initiating a empty response object
		LogResults logResults = new LogResults();

		// Get the files from resource folder based on input log file name
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(logFileName);

		// ArrayList of strings to store Ip addresses and URLs from log files
		ArrayList<String> ipAddressesFromLogFile = new ArrayList<String>();
		ArrayList<String> urlsFromLogFile = new ArrayList<String>();
		
		// Creating a Reader to read the log file, check for null before
		if (inputStream != null) {
			try (InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
					BufferedReader reader = new BufferedReader(streamReader)) {

				String line;

				// Read line by line on the given log file
				while ((line = reader.readLine()) != null) {

					// Split "-", splits the current reading line and gets the IP addresses
					String[] parts = line.split("-");
					ipAddressesFromLogFile.add(parts[0]);

					// Getting the URL string from the line currently read
					urlsFromLogFile.add(StringUtils.substringBetween(line, "\"GET", "HTTP/1.1").trim());
				}

			} catch (IOException e) {
				// Catch exception if something goes wrong
				e.printStackTrace();
			}
		}

		/*
		 * AnalyserHelper class created to handle and fetch the required data from the
		 * log file
		 */
		logResults.setLogFileName(logFileName);
		logResults.setNoOfUniqueIp(AnalyserHelper.getNumberOfUniqueIpAddresses(ipAddressesFromLogFile));
		logResults.setMostActiveIpAddresss(AnalyserHelper.getTopThreeActiveIpAddresses(ipAddressesFromLogFile));
		logResults.setMostVisitedUrls(AnalyserHelper.getTopThreeVisitedUrls(urlsFromLogFile));

		// Logging the results into the h2 Database
		logAnalysedResultsIntoTable(logResults);

		return logResults;
	}

	private void logAnalysedResultsIntoTable(LogResults logResult) {
		LogResultsStorage logResultsStorage = new LogResultsStorage();

		logResultsStorage.setCreationDate(LocalDate.now().toString());

		// Checking here if actual data exists before logging
		if (logResult.getNoOfUniqueIp() > 0) {
			logResultsStorage.setLogFileName(logResult.getLogFileName());
			logResultsStorage.setNoOfUniqueIp(logResult.getNoOfUniqueIp());
			logResultsStorage.setThreeMostActiveIp(logResult.getMostActiveIpAddresss().toString());
			logResultsStorage.setTopThreeURL(logResult.getMostVisitedUrls().toString());
		} else {
			// If no data just log "No data available"
			logResultsStorage.setLogFileName(logResult.getLogFileName());
			logResultsStorage.setNoOfUniqueIp(0);
			logResultsStorage.setThreeMostActiveIp("No Log Data Available");
			logResultsStorage.setTopThreeURL("No Log Data Available");
		}

		LogResultsRepository.save(logResultsStorage);

	}

}