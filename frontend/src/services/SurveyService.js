import axios from "axios";

const API_URL = "http://localhost:8080/api/v1.0/surveys";

class SurveyService {
  // GET all surveys
  getAllSurveys() {
    return axios.get(API_URL);
  }

  // GET a survey by ID
  getSurvey(id) {
    return axios.get(`${API_URL}/${id}`);
  }

  // POST (create) a survey
  createSurvey(surveyData) {
    return axios.post(API_URL, surveyData);
  }

  // PUT (update) a survey by ID
  updateSurvey(id, surveyData) {
    return axios.put(`${API_URL}/${id}`, surveyData);
  }

  // DELETE a survey by ID
  deleteSurvey(id) {
    return axios.delete(`${API_URL}/${id}`);
  }
}

export default new SurveyService();
