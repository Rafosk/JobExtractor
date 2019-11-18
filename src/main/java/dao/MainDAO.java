package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import beans.Job;
import beans.JobDB;

@Service
public class MainDAO implements IMainDAO {

	@Autowired
	private Environment env;

	@Override
	public String insertJob(Job job) {
		
		String query = "INSERT INTO [Job] ([jobID],[jobName],[jobDescription],[jobSubmitterId],[statusCode],[createDate])\r\n" + 
				"     VALUES (?,?,?,?,?,?)";
		
		 try (Connection conn = getConnection(env.getProperty("datasource.url"), env.getProperty("datasource.login"),
					env.getProperty("datasource.password")); PreparedStatement stmt = conn.prepareStatement(query)) {
			 
	             PreparedStatement preparedStatement = conn.prepareStatement(query);

	            preparedStatement.setString(1, job.getJobId());
	            preparedStatement.setString(2, job.getJobName());
	            preparedStatement.setString(3, job.getDescription());
	            preparedStatement.setString(4, job.getSubmitterId());
	            preparedStatement.setString(5, job.getStatusCode().getStatusCode());	            
	            preparedStatement.setTimestamp(6, new Timestamp(job.getCreatedDate().getTime()));

	            preparedStatement.executeUpdate();

	            return "ok";
			
	        } catch (SQLException e) {
	            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		

		return null;
	}
	
	
	@Override
	public String deleteJob(String id) {

		Integer count = 0;
		String query = "DELETE FROM [Job] WHERE jobId = ?";

		try (Connection conn = getConnection(env.getProperty("datasource.url"), env.getProperty("datasource.login"),
				env.getProperty("datasource.password")); PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setString(1, id);
			count = stmt.executeUpdate();

			System.out.println("Record deleted successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (count >0) {
			return "ok";
		} else {
			return "no record found";
		}
	}

	@Override
	public List<JobDB> getDBJobs() {
		Statement stmt = null;
		String query = "SELECT [jobId],[jobName],[jobDescription],[jobSubmitterId],[statusCode],[createDate] FROM [Job]";

		List<JobDB> result = new ArrayList<JobDB>();

		try {

			Connection conn = getConnection(env.getProperty("datasource.url"), env.getProperty("datasource.login"),
					env.getProperty("datasource.password"));

			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				JobDB job = new JobDB();
				job.setJobID(rs.getString("jobID"));
				job.setJobName(rs.getString("jobName"));
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
		}
		return null;

	}

	public static Connection getConnection(String url, String login, String password) {

		try {
			Connection conn = DriverManager.getConnection(url, login, password);
			return conn;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

}
