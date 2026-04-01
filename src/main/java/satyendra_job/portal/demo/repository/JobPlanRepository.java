package satyendra_job.portal.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import satyendra_job.portal.demo.model.JobPlan;

public interface JobPlanRepository extends JpaRepository<JobPlan, Long> {
}