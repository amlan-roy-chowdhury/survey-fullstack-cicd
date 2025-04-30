package com.example.survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/surveys")
@CrossOrigin(origins = "*")
public class SurveyController {

    @Autowired
    private SurveyService service;

    @GetMapping
    public List<Survey> getAll() {
        return service.getAllSurveys();
    }

    @GetMapping("/{id}")
    public Survey getById(@PathVariable Long id) {
        return service.getSurvey(id).orElse(null);
    }

    @PostMapping
    public Survey create(@RequestBody Survey survey) {
        return service.saveSurvey(survey);
    }

    @PutMapping("/{id}")
    public Survey update(@PathVariable Long id, @RequestBody Survey survey) {
        return service.updateSurvey(id, survey);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteSurvey(id);
    }
}
