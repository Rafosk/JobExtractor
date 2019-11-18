package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import beans.Job;
import beans.JobDB;
import dao.IMainDAO;
import utils.Utils;

@Service
public class MainService implements IMainService {

	@Autowired
	private Environment env;
	
	@Autowired
	private IMainDAO mainDAO;	
	
	@Override
	public List<Job> getAllJobs() {
		
		HttpEntity<String> entity = new HttpEntity<String>(Utils.setHeaders(MediaType.APPLICATION_JSON, true, env.getProperty("auth.login")));
		
		RestTemplate rest = new RestTemplate();
		ResponseEntity<List<Job>> exchange = rest.exchange(
				env.getProperty("rest.allJobsURL"), HttpMethod.GET,
				entity, new ParameterizedTypeReference<List<Job>>() {});
		return exchange.getBody();
	}

	@Override
	public Job getJob(String idJob) {
		
		HttpEntity<String> entity = new HttpEntity<String>(Utils.setHeaders(MediaType.APPLICATION_JSON, true, env.getProperty("auth.login")));

		String url = env.getProperty("rest.job") + idJob + "?fetchType=full";
		
		RestTemplate rest = new RestTemplate();
		ResponseEntity<Job> exchange = rest.exchange(
				url, HttpMethod.GET, entity, Job.class);
		return exchange.getBody();
	}
	
	public List<JobDB> getAllDBJobs() {
		
		return mainDAO.getDBJobs();
	}

	@Override
	public String deleteJob(String id) {

		String result = mainDAO.deleteJob(id);
				
		return result;
	}

	@Override
	public String saveJobToDatabase(Job job) {
		
		String result = mainDAO.insertJob(job);
		
		return result;
	}
}
