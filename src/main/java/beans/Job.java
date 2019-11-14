package beans;

import java.util.Date;


public class Job {
	  private String jobId;
	  private String jobName;
	  private String description;
	  private StatusCode statusCode;
	  private String hasError;
	  private String submitterId;
	  private String creatorId;
	  private String providerId;
	  private Integer poReference;
	  private Date dueDate;
	  private Date createdDate;
	  private Date modifiedDate;
	  private String archived;
	  
	  public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public StatusCode getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(StatusCode statusCode) {
		this.statusCode = statusCode;
	}
	public String getHasError() {
		return hasError;
	}
	public void setHasError(String hasError) {
		this.hasError = hasError;
	}
	public String getSubmitterId() {
		return submitterId;
	}
	public void setSubmitterId(String submitterId) {
		this.submitterId = submitterId;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public String getProviderId() {
		return providerId;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	public Integer getPoReference() {
		return poReference;
	}
	public void setPoReference(Integer poReference) {
		this.poReference = poReference;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getArchived() {
		return archived;
	}
	public void setArchived(String archived) {
		this.archived = archived;
	}
	public String getShouldQuote() {
		return shouldQuote;
	}
	public void setShouldQuote(String shouldQuote) {
		this.shouldQuote = shouldQuote;
	}
	private String shouldQuote;
}
