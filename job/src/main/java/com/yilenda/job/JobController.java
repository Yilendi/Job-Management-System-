package com.yilenda.job;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;


@Controller
public class JobController {

    @Autowired
    JobService jobService;

    // GET all jobs
    @GetMapping("/api/jobs")
    public ResponseEntity<List<Job>> getJobs() {
        List<Job> jobs = jobService.getJobs();
        for (Job job : jobs) {
            System.out.println(job.getOccupation());
        }
        return ResponseEntity.ok(jobs);
    }

    @GetMapping("/")
    public String welcomeAboard(Model model) {
        List<Job> joblists = jobService.getJobs();
        if (joblists.size() > 10) {
            joblists = joblists.subList(joblists.size() - 10, joblists.size());
        }
        // reverse joblists
        Collections.reverse(joblists);
        model.addAttribute("joblists", joblists);
        return "index"; // Return the name of the HTML template
    }
    @GetMapping("/addNew")
    public String addNew(Model model) {
        model.addAttribute("newjob", new Job());
        return "addNew";
    }

    @GetMapping("/delete/{id}")
    public String deleteJob(@PathVariable("id") Long id, Model model) {
        jobService.deleteJobById(id);
        return "redirect:/";
    }

    @PostMapping("/saveJob")
    public String saveJob(@ModelAttribute("newjob") Job joblist) {
        // save employee to database
        jobService.createJob(joblist);
        return "redirect:/";
    }

    // GET job by id
    @GetMapping("/api/jobs/{jobId}")
    public ResponseEntity<Job> getJobById(@PathVariable Long jobId) {
        Job job = jobService.getJobById(jobId);
        return ResponseEntity.ok(job);
    }

    // POST create new job
    @PostMapping("/api/jobs")
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        Job newJob = jobService.createJob(job);
        return ResponseEntity.ok(newJob);
    }

    // PUT update job by id
    @PutMapping("/api/jobs/{jobId}")
    public ResponseEntity<Job> updateJob(@PathVariable Long jobId, @RequestBody Job jobDetails) {
        Job updatedJob = jobService.updateJob(jobId, jobDetails);
        return ResponseEntity.ok(updatedJob);
    }

    // DELETE job by id
    @DeleteMapping("/api/jobs/{jobId}")
    public ResponseEntity<Void> deleteJobById(@PathVariable Long jobId) {
        jobService.deleteJobById(jobId);
        return ResponseEntity.ok().build();
    }
}
