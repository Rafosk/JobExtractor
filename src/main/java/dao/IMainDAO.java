package dao;

import java.util.List;

import org.springframework.stereotype.Service;

import beans.Job;
import beans.JobDB;

public interface IMainDAO {

	public List<JobDB> getDBJobs();

	public String deleteJob(String id);

	public String insertJob(Job job);
}
