package com.k8port.service;

import com.k8port.model.Log;
import com.k8port.model.Summary;
import com.k8port.repository.LogRepository;
import com.k8port.repository.SummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Transactional
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private SummaryRepository summaryRepository;

    @Override
    public Summary findSummaryByCourseId(Long courseId) {
        Summary course = null;
        if (summaryRepository.findByCourseId(courseId).isPresent()) {
            course = summaryRepository.findByCourseId(courseId).get();
        }
        return course;
    }

    @Override
    public Log saveOrUpdate(Log log) {
        Summary existSummary = null;
        if (summaryRepository.findByCourseId(log.getCourseId()).isPresent()) {
            existSummary  = summaryRepository.findByCourseId(log.getCourseId()).get();
        }
        if (existSummary == null) {
            Summary summary = new Summary();
            summary.setCourseId(log.getCourseId());
            summary.setHitCount(1L);
            summaryRepository.save(summary);
        } else {
            summaryRepository.delete(existSummary);
            existSummary.setHitCount(existSummary.getHitCount());
            summaryRepository.save(existSummary);
        }
        log.setId(UUID.randomUUID());
        logRepository.save(log);
        return log;
    }

    @Override
    public Summary saveOrUpdate(Summary summary) {
        summaryRepository.save(summary);
        return summary;
    }

    @Override
    public List<Summary> findPopularCourses() {
        return summaryRepository.findPopularCourses();
    }
}
