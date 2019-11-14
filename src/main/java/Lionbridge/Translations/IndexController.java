package Lionbridge.Translations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import beans.Job;
import services.MainService;
import utils.Configuration;

import java.sql.*;
import java.util.Arrays;
import java.util.List;


@Controller
public class IndexController {
	
	@Autowired
	private MainService mainSerice;
	
	private static Configuration conf = new Configuration();

	public static void main(String[] args) {

		Job job = getJob();
		//List<Job> allJobs = getAllJobs();
		
		System.out.println(job.getHasError());

	}

	@GetMapping("/allJobs")
	public String alljobs(Model model) {

		List<Job> jobsList = mainSerice.getAllJobs();
		
		for (Job job : jobsList) {
			System.out.println(job.getJobId());
		}
		
		model.addAttribute("jobsList", jobsList);
		
		return "allJobs";
	}
	 
	@RequestMapping(value = { "/allJobs/" }, method = RequestMethod.POST)
	public String alljobs(@RequestParam(required = false) String id_job, Model model) {

		List<Job> jobsList = mainSerice.getAllJobs();
		
		
		model.addAttribute("status", "JOB ADDED");
		model.addAttribute("jobsList", jobsList);
		
		return "allJobs";
	}
	
	@GetMapping("/greeting")
	public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		Connection test = getConnection();
		String test2 = getTestData(test);
		System.out.println("test1");
		System.out.println("test2");

		model.addAttribute("name", test2);

		HttpEntity<String> entity = new HttpEntity<String>(setHeaders(MediaType.APPLICATION_JSON, true));
		RestTemplate rest = new RestTemplate();
		ResponseEntity<List<Job>> exchange = rest.exchange("https://bitpay.com/api/rates", HttpMethod.GET, entity,
				new ParameterizedTypeReference<List<Job>>() {
				});
		List<Job> rates = exchange.getBody();

		return "greeting";

	}

	private static List<Job> getAllJobs() {

		HttpEntity<String> entity = new HttpEntity<String>(setHeaders(MediaType.APPLICATION_JSON, true));
		RestTemplate rest = new RestTemplate();
		ResponseEntity<List<Job>> exchange = rest.exchange(
				"https://content-api.lionbridge.com/v1/jobs?fetchType=full&includeArchived=false", HttpMethod.GET,
				entity, new ParameterizedTypeReference<List<Job>>() {});
		return exchange.getBody();
	}

	private static Job getJob() {

		HttpEntity<String> entity = new HttpEntity<String>(setHeaders(MediaType.APPLICATION_JSON, true));

		RestTemplate rest = new RestTemplate();
		ResponseEntity<Job> exchange = rest.exchange(
				"https://content-api.lionbridge.com/v1/jobs/c95cf357-a825-4311-a81d-0976133a5a4f?fetchType=full",
				HttpMethod.GET, entity, Job.class);
		return exchange.getBody();

	}

	public static String callGet() {

		HttpEntity<String> entity = new HttpEntity<String>(setHeaders(MediaType.APPLICATION_JSON, true));

		RestTemplate rest = new RestTemplate();
		ResponseEntity<String> exchange = rest.exchange(
				"https://content-api.lionbridge.com/v1/jobs/c95cf357-a825-4311-a81d-0976133a5a4f?fetchType=full",
				HttpMethod.GET, entity, String.class);
		return exchange.getBody();
	}

	public static HttpHeaders setHeaders(MediaType mediaType, boolean noCache) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Connection", "Close");
		if (mediaType != null) {
			headers.setContentType(mediaType);
			headers.setAccept(Arrays.asList(mediaType));
		}
		if (noCache) {
			headers.setCacheControl("no-cache");
		}
		
		headers.add("Authorization", conf.getAuth());
		return headers;
	}

	public String getTestData(Connection conn) {

		Statement stmt = null;
		String query = "SELECT TOP 1 j.[jobName]\r\n"
				+ "  FROM Job as j inner join request as r on j.jobId = r.jobId\r\n"
				+ "  WHERE r.requestName like '%LionBridge%' AND \r\n" + "		j.statusCode = 'IN_TRANSLATION' ";

		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();

			System.out.println("test");
			return rs.getString(0);

		} catch (Exception e) {
			return e.getMessage();
		}

	}

	public Connection getConnection() {

		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlserver://WAW-JT9NV02\\SQLEXPRESS", "admin1",
					"admin1");
			return conn;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

}