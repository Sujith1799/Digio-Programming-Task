package com.example.DigioProgrammingTask.ModelClasses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * Log Results Table class,
 * to store the log analysis
 * results
 */
@Entity
@Table(name = "LOG_RESULTS_STORAGE")
public class LogResultsStorage {
	/*
	 * @param Auto generating Id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	/*
	 * @param To store the log file name
	 */
	@Column(name = "logFileName")
	private String logFileName;

	/*
	 * @param To store the no of uniqueIp
	 */
	@Column(name = "topThreeURL")
	private String topThreeURL;

	/*
	 * @param To store the list of top Active IPs
	 */
	@Column(name = "threeMostActiveIp")
	private String threeMostActiveIp;

	/*
	 * @param To store the list of top three URLS
	 */
	@Column(name = "noOfUniqueIp")
	private long noOfUniqueIp;
	
	/*
	 * @param To store the Creation date
	 * of the new log results row 
	 * in the table
	 */
	@Column(name = "creationDate")
	private String creationDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogFileName() {
		return logFileName;
	}

	public void setLogFileName(String logFileName) {
		this.logFileName = logFileName;
	}

	public String getTopThreeURL() {
		return topThreeURL;
	}

	public void setTopThreeURL(String topThreeURL) {
		this.topThreeURL = topThreeURL;
	}

	public String getThreeMostActiveIp() {
		return threeMostActiveIp;
	}

	public void setThreeMostActiveIp(String threeMostActiveIp) {
		this.threeMostActiveIp = threeMostActiveIp;
	}

	public long getNoOfUniqueIp() {
		return noOfUniqueIp;
	}

	public void setNoOfUniqueIp(long noOfUniqueIp) {
		this.noOfUniqueIp = noOfUniqueIp;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

}
