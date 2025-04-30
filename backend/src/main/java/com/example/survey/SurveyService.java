package com.example.survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SurveyService {

    @Autowired
    private SurveyRepository repository;

    public List<Survey> getAllSurveys() {
        return repository.findAll();
    }

    public Optional<Survey> getSurvey(Long id) {
        return repository.findById(id);
    }

    public Survey saveSurvey(Survey survey) {
        return repository.save(survey);
    }

    public void deleteSurvey(Long id) {
        repository.deleteById(id);
    }

    public Survey updateSurvey(Long id, Survey updated) {
        updated.setId(id);
        return repository.save(updated);
    }
}
