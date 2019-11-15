package services;

import java.util.List;

import org.springframework.stereotype.Service;

import beans.Job;
import beans.JobDB;

public interface IMainService {
	
	public List<Job> getAllJobs(); 
	
	public Job getJob(String idJob);
	
	public List<JobDB> getAllDBJobs();
}
