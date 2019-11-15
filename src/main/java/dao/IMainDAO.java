package dao;

import java.util.List;

import org.springframework.stereotype.Service;

import beans.JobDB;

public interface IMainDAO {

	public List<JobDB> getDBJobs();
}
