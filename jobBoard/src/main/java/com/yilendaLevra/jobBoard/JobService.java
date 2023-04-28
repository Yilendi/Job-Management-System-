package com.yilendaLevra.jobBoard;


import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//@Service
public class JobService {

    private static final String CSV_FILE_PATH = "src/national_M2021_dl.csv";
    private static final String url = "jdbc:mysql://localhost:3306/jobboard";
    private final String username = "root";
    private final String password = "";

    private Connection conn;

    public JobService() {
        try {
            setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setUp() throws Exception {
        try {
            // Set up connection to MySQL server
            conn = DriverManager.getConnection(url, username, password);

            // Create database if it doesn't exist
            String sql = "CREATE DATABASE IF NOT EXISTS jobboard";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.executeUpdate();
            }

            // Switch to mydatabase
            sql = "USE jobboard";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.executeUpdate();
            }

            // Create jobs table if it doesn't exist
            sql = "CREATE TABLE IF NOT EXISTS jobs (id INT NOT NULL AUTO_INCREMENT, " +
                    "occupation VARCHAR(255), occupation_level VARCHAR(255), annual_salary INT, PRIMARY KEY (id))";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void
    importJobsFromCsvFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String occupation = values[9];
                String occupationLevel = values[10];
                int annualSalary = Integer.parseInt(values[18]);
                addJob(occupation, occupationLevel, annualSalary);
            }
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    public void addJob(String occupation, String occupationLevel, int annualSalary) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO jobs (occupation, occupation_level, annual_salary) VALUES (?, ?, ?)");
            stmt.setString(1, occupation);
            stmt.setString(2, occupationLevel);
            stmt.setInt(3, annualSalary);
            stmt.executeUpdate();
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

    public void deleteJob(String occupation) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM jobs WHERE occupation = ?");
            stmt.setString(1, occupation);
            stmt.executeUpdate();
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


}
