package satyendra_job.portal.demo.controller;

import org.springframework.web.bind.annotation.*;
import satyendra_job.portal.demo.model.JobPlan;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/api/jobplans")
public class UserController {

    private List<JobPlan> jobList = new ArrayList<>();

    @Autowired
    private JavaMailSender mailSender;

    // ✅ SAVE
    @PostMapping
    public JobPlan createJobPlan(@RequestBody JobPlan jobPlan) {

        jobList.add(jobPlan);

        // ✅ Email send
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("singhsatyendra.singh00@gmail.com");
        msg.setSubject("New Job Entry");

        msg.setText(
                "New job submitted:\n\n" +
                        "Date: " + jobPlan.getDate() + "\n" +
                        "Line: " + jobPlan.getLine() + "\n" +
                        "Department: " + jobPlan.getDepartment() + "\n" +
                        "Location: " + jobPlan.getLocation() + "\n" +
                        "Activity: " + jobPlan.getActivity()
        );

        mailSender.send(msg);

        return jobPlan;
    }

    // ✅ GET ALL
    @GetMapping
    public List<JobPlan> getAllJobPlans() {
        return jobList;
    }

}