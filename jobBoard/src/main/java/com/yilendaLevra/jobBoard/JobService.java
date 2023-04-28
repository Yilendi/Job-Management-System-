package com.yilendaLevra.jobBoard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    private static final String CSV_FILE_PATH = "src/a.csv";

    public void importJobsFromCsvFile(String csvFilePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String occupation = values[9];
                String occupationLevel = values[10];
                int annualSalary = Integer.parseInt(values[18]);
                saveToMysql(occupation, occupationLevel, annualSalary);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void saveToMysql(String occupation, String occupationLevel, int annualSalary) throws Exception {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase?user=root&password=mypassword");
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO jobs (occupation, occupation_level, annual_salary) VALUES (?, ?, ?)")) {
            stmt.setString(1, occupation);
            stmt.setString(2, occupationLevel);
            stmt.setInt(3, annualSalary);
            stmt.executeUpdate();
        }
    }

}
