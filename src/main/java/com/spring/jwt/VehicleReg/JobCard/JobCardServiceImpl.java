package com.spring.jwt.VehicleReg.JobCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service
public class JobCardServiceImpl implements JobCardService{

    private final JobCardRepository jobCardRepository;

    @Autowired
    public JobCardServiceImpl(JobCardRepository jobCardRepository) {
        this.jobCardRepository = jobCardRepository;
    }

    @Override
    public JobCard createJobCard(JobCard jobCard) {
        try {
            return jobCardRepository.save(jobCard);
        } catch (Exception ex) {
            throw new RuntimeException("Error creating JobCard: " + ex.getMessage(), ex);
        }
    }

    @Override
    public JobCard getJobCardById(Integer id) {
        return jobCardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("JobCard not found with id: " + id));
    }

    @Override
    public List<JobCard> getAllJobCards() {
        return jobCardRepository.findAll();
    }


        @Transactional
        public JobCard updateJobCardPartial(Integer id, Map<String, Object> updates) {
            JobCard jobCard = jobCardRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("JobCard not found with id " + id));

            if (updates.containsKey("jobName")) {
                jobCard.setJobName((String) updates.get("jobName"));
            }
            if (updates.containsKey("jobType")) {
                jobCard.setJobType((String) updates.get("jobType"));
            }

            return jobCardRepository.save(jobCard);
        }

    @Transactional
    public void deleteJobCard(Integer id) {
        if (!jobCardRepository.existsById(id)) {
            throw new RuntimeException("JobCard not found with id " + id);
        }
        jobCardRepository.deleteById(id);
    }

    public List<JobCard> searchJobCards(String query) {
        if(query == null || query.trim().isEmpty()){
            return jobCardRepository.findAll();
        }
        return jobCardRepository
                .findByJobNameContainingIgnoreCaseOrJobTypeContainingIgnoreCase(query, query);
    }
    }