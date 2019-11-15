package services;

import java.util.List;

import beans.Job;


public interface IMainService {

	public List<Job> getAllJobs(); 
	
	public Job getJob(String idJob);
}
