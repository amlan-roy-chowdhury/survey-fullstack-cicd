<template>
  <div>
    <h2>All Surveys</h2>
    <button @click="fetchSurveys">Refresh List</button>
    <ul v-if="surveys.length">
      <li v-for="survey in surveys" :key="survey.id">
        <strong>{{ survey.firstName }} {{ survey.lastName }}</strong>
        <br />
        <router-link :to="`/surveys/${survey.id}`">View</router-link> |
        <router-link :to="`/edit/${survey.id}`">Edit</router-link> |
        <button @click="deleteSurvey(survey.id)">Delete</button>
      </li>
    </ul>
    <div v-else>No surveys found.</div>
    <router-link to="/create">+ Create New Survey</router-link>
  </div>
</template>

<script>
import SurveyService from "@/services/SurveyService";

export default {
  name: "SurveyList",
  data() {
    return {
      surveys: [],
    };
  },
  methods: {
    fetchSurveys() {
      SurveyService.getAll()
        .then((res) => {
          this.surveys = res.data;
        })
        .catch((err) => console.error(err));
    },
    deleteSurvey(id) {
      SurveyService.delete(id)
        .then(() => this.fetchSurveys())
        .catch((err) => console.error(err));
    },
  },
  mounted() {
    this.fetchSurveys();
  },
};
</script>
