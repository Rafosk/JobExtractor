package Lionbridge.Translations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import beans.Job;
import beans.JobDB;
import services.IMainService;
import utils.Configuration;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class IndexController {

	@Autowired
	private IMainService mainSerice;

	private static Configuration conf = new Configuration();

	public static void main(String[] args) {

		List<JobDB> a = getTestData(getConnection());

		System.out.println("test");

	}

	public static List<JobDB> getTestData(Connection conn) {

		Statement stmt = null;
		String query = "SELECT [ID],[jobID],[jobName],[jobDescription],[jobSubmitterId],[statusCode],[createDate] FROM [Job]";

		List<JobDB> result = new ArrayList<JobDB>();

		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				JobDB job = new JobDB();
				job.setID(rs.getInt("ID"));
				job.setJobID(rs.getString("jobID"));
				job.setJobDescription(rs.getString("jobDescription"));
				job.setJobSubmitterId(rs.getString("jobSubmitterId"));
				job.setStatusCode(rs.getString("statusCode"));
				job.setCreateDate(rs.getDate("createDate"));

				result.add(job);
			}

			rs.close();
			stmt.close();
			conn.close();
			return result;
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}		
		return null;

	}

	public static Connection getConnection() {

		try {
			Connection conn = DriverManager.getConnection("jdbc:sqlserver://WAW-JT9NV02\\SQLEXPRESS", "admin1",	"admin1");
			return conn;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@GetMapping("/allJobs")
	public String alljobs(Model model) {

		List<Job> jobsList = mainSerice.getAllJobs();
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

	@GetMapping(value = "/job")
	public String edit(@RequestParam String id, Model model) {

		Job job = mainSerice.getJob(id);
		model.addAttribute("job", job);

		return "job";
	}

	@GetMapping(value = "/savedJobs")
	public String allSavedJobs() {

		List<JobDB> jobDBList = mainSerice.getAllDBJobs();
		
		System.out.println("test");
		return "savedJobs";
	}

}