package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.JobDB;

public class MainDAO implements IMainDAO {

	@Override
	public List<JobDB> getDBJobs() {
		Statement stmt = null;
		String query = "SELECT [ID],[jobID],[jobName],[jobDescription],[jobSubmitterId],[statusCode],[createDate] FROM [Job]";

		List<JobDB> result = new ArrayList<JobDB>();

		try {

			Connection conn = getConnection();

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
		}
		return null;

	}

	public static Connection getConnection() {

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
