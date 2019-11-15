package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import beans.Job;
import beans.JobDB;
import dao.MainDAO;
import utils.Configuration;
import utils.Utils;

@Service
public class MainService implements IMainService {

	@Autowired
	private MainDAO mainDAO;
	
	private static Configuration conf = new Configuration();
	
	@Override
	public List<Job> getAllJobs() {

		HttpEntity<String> entity = new HttpEntity<String>(Utils.setHeaders(MediaType.APPLICATION_JSON, true));
		
		RestTemplate rest = new RestTemplate();
		ResponseEntity<List<Job>> exchange = rest.exchange(
				conf.getAllJobsURL(), HttpMethod.GET,
				entity, new ParameterizedTypeReference<List<Job>>() {});
		return exchange.getBody();
	}

	@Override
	public Job getJob(String idJob) {
		
		HttpEntity<String> entity = new HttpEntity<String>(Utils.setHeaders(MediaType.APPLICATION_JSON, true));

		String url = conf.getJobURL() + idJob + "?fetchType=full";
		
		RestTemplate rest = new RestTemplate();
		ResponseEntity<Job> exchange = rest.exchange(
				url,
				HttpMethod.GET, entity, Job.class);
		return exchange.getBody();
	}

	public List<JobDB> getAllDBJobs() {
		
		return mainDAO.getDBJobs();
	}
}
