package com.yilenda.job;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    // GET all jobs
    @GetMapping
    public ResponseEntity<List<Job>> getJobs() {
        List<Job> jobs = jobService.getJobs();
        return ResponseEntity.ok(jobs);
    }

    // GET job by id
    @GetMapping("/{jobId}")
    public ResponseEntity<Job> getJobById(@PathVariable Long jobId) {
        Job job = jobService.getJobById(jobId);
        return ResponseEntity.ok(job);
    }

    // POST create new job
    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        Job newJob = jobService.createJob(job);
        return ResponseEntity.ok(newJob);
    }

    // PUT update job by id
    @PutMapping("/{jobId}")
    public ResponseEntity<Job> updateJob(@PathVariable Long jobId, @RequestBody Job jobDetails) {
        Job updatedJob = jobService.updateJob(jobId, jobDetails);
        return ResponseEntity.ok(updatedJob);
    }

    // DELETE job by id
    @DeleteMapping("/{jobId}")
    public ResponseEntity<Void> deleteJobById(@PathVariable Long jobId) {
        jobService.deleteJobById(jobId);
        return ResponseEntity.ok().build();
    }
}
