package com.example.DigioProgrammingTask.ModelClasses;

import java.util.ArrayList;

/*
 * Log Results Model class,
 * to return the log analysis
 * results
 */
public class LogResults {
	/*
	 * @param To store the log file name
	 */
	String logFileName;
	/*
	 * @param To store the no of uniqueIp
	 */
	long noOfUniqueIp;
	/*
	 * @param To store the list of top three URLS
	 */
	ArrayList<String> mostVisitedUrls;
	/*
	 * @param To store the list of top three ActiveIp adresses
	 */
	ArrayList<String> mostActiveIpAddresss;

	public long getNoOfUniqueIp() {
		return noOfUniqueIp;
	}

	public void setNoOfUniqueIp(long noOfUniqueIp) {
		this.noOfUniqueIp = noOfUniqueIp;
	}

	public ArrayList<String> getMostVisitedUrls() {
		return mostVisitedUrls;
	}

	public void setMostVisitedUrls(ArrayList<String> mostVisitedUrls) {
		this.mostVisitedUrls = mostVisitedUrls;
	}

	public ArrayList<String> getMostActiveIpAddresss() {
		return mostActiveIpAddresss;
	}

	public void setMostActiveIpAddresss(ArrayList<String> mostActiveIpAddresss) {
		this.mostActiveIpAddresss = mostActiveIpAddresss;
	}

	public String getLogFileName() {
		return logFileName;
	}

	public void setLogFileName(String logFileName) {
		this.logFileName = logFileName;
	}

}
