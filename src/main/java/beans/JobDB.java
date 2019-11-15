package beans;

import java.util.Date;

public class JobDB {
	private int ID;
	private String jobID;
	private String jobName;
	private String jobDescription;
	private String jobSubmitterId;
	private String statusCode;
	private Date createDate;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getJobID() {
		return jobID;
	}
	public void setJobID(String jobID) {
		this.jobID = jobID;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public String getJobSubmitterId() {
		return jobSubmitterId;
	}
	public void setJobSubmitterId(String jobSubmitterId) {
		this.jobSubmitterId = jobSubmitterId;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
