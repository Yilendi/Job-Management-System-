package com.yilenda.job;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import com.opencsv.CSVReader;
@Service
public class JobService {

    private static final String CSV_FILE_PATH = "src/national_M2021_dl.csv";
    private static final String url = "jdbc:mysql://localhost:3306/jobboard";
    private final String username = "root";
    private final String password = "password01";

    private Connection conn;
    @Autowired
    JobRepository jobRepository;



    public void setUp() throws Exception {
        try {
            // Set up connection to MySQL server
            conn = DriverManager.getConnection(url, username, password);



            // Switch to mydatabase
            String sql = "USE jobboard";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.executeUpdate();
            }

            // Create jobs table if it doesn't exist
            sql = "CREATE TABLE IF NOT EXISTS dashboard (id INT NOT NULL AUTO_INCREMENT, " +
                    "occupation VARCHAR(255), occupation_level VARCHAR(255), annual_salary INT, PRIMARY KEY (id))";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.executeUpdate();
            }
//
            sql = "TRUNCATE TABLE dashboard";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void
    importJobsFromCsvFile() throws IOException {
        try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE_PATH))) {
            String s = "abc123def456gh7i89jkl";
            boolean firstLine = true;
            List<String[]> records = reader.readAll();
            for (String[] record : records) {
                // skip first line
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String occupation = record[9];
                String occupationLevel = record[10];
                // skip if not a numeric value
                if (record[11].equals("*") || record[11].isEmpty()) {
                    continue;
                }

                int annualSalary = Integer.parseInt(record[11].replaceAll("[^\\d]", ""));
                addJob(occupation, occupationLevel, annualSalary);
            }
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    public void addJob(String occupation, String occupationLevel, int annualSalary) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO dashboard (occupation, occupation_level, annual_salary) VALUES (?, ?, ?)");
            stmt.setString(1, occupation);
            stmt.setString(2, occupationLevel);
            stmt.setInt(3, annualSalary);
            stmt.executeUpdate();
//            System.out.println("Added " + occupation + " " + occupationLevel + " " + annualSalary);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    // ignore exception
                }
            }
        }
    }



    public void close() throws Exception {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    // CREATE
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    // READ
    public List<Job> getJobs() {
        return jobRepository.findAll();
    }

    public Job getJobById(Long jobId) {
        return jobRepository.findById(jobId).get();
    }

    // DELETE
    public void deleteJobById(Long jobId) {
        jobRepository.deleteById(jobId);
    }

    // UPDATE
    public Job updateJob(Long jobId, Job jobDetails) {
        Job job = jobRepository.findById(jobId).get();
        job.setOccupation(jobDetails.getOccupation());
        job.setOccupationLevel(jobDetails.getOccupationLevel());
        job.setAnnualSalary(jobDetails.getAnnualSalary());

        return jobRepository.save(job);
    }
}
