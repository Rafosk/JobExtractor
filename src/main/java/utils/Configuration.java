package utils;

public class Configuration {

	public final String auth = "";

	public final String allJobsURL = "https://content-api.lionbridge.com/v1/jobs?fetchType=full&includeArchived=false";
	
	public final String jobURL = "https://content-api.lionbridge.com/v1/jobs/";
	
	public String getJobURL() {
		return jobURL;
	}

	public String getAllJobsURL() {
		return allJobsURL;
	}

	public String getAuth() {
		return auth;
	}
}
