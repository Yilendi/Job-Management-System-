package com.yilendaLevra.jobBoard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    public void importJobsFromCsvFile(String csvFilePath) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(csvFilePath));
        String row;
        boolean firstLine = true;
        // need to skip header line
        while ((row = csvReader.readLine()) != null) {
            if (firstLine) {
                firstLine = false;
                continue;
            }
            String[] data = row.split(",");
            Job job = new Job();
            job.setOccupation(data[9]);
            job.setOccupationLevel(data[10]);
            job.setAnnualSalary(Integer.parseInt(data[18]));
            jobRepository.save(job);
        }
        csvReader.close();
    }

}
