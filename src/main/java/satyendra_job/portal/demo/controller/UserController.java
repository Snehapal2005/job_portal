package satyendra_job.portal.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import satyendra_job.portal.demo.model.JobPlan;
import satyendra_job.portal.demo.repository.JobPlanRepository;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;

import java.util.List;
@CrossOrigin(origins ="*")
@RestController
@RequestMapping("/api/jobplans")
public class UserController {


        @Autowired
        private JobPlanRepository repository;

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping
    public JobPlan createJobPlan(@RequestBody JobPlan jobPlan) {

        // ✅ 1. पहले database में save
        JobPlan saved = repository.save(jobPlan);

        // ✅ 2. Email send
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("singhsatyendra.singh00@gmail.com");   // यहाँ admin का email डालो
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

        // ✅ 3. response return
        return saved;
    }
        @GetMapping
        public List<JobPlan> getAllJobPlans() {
            return repository.findAll();
        }

        // कोई खास ID से ढूंढना हो
        @GetMapping("/{id}")
        public JobPlan getJobPlanById(@PathVariable Long id) {
            return repository.findById(id).orElseThrow(() -> new RuntimeException("Job Plan not found"));
        }

        // और भी जैसे update, delete वगैरह चाहो तो जोड़ सकते हो
    }

