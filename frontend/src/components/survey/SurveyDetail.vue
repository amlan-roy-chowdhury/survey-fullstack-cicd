<template>
  <div v-if="survey">
    <h2>Survey Details</h2>
    <p><strong>Name:</strong> {{ survey.firstName }} {{ survey.lastName }}</p>
    <p><strong>Email:</strong> {{ survey.email }}</p>
    <p><strong>City/State:</strong> {{ survey.city }}, {{ survey.state }}</p>
    <p><strong>Recommendation:</strong> {{ survey.recommendation }}</p>
    <p><strong>Interest:</strong> {{ survey.interest }}</p>
    <p><strong>Likings:</strong> {{ survey.likings }}</p>
    <router-link to="/">← Back</router-link>
  </div>
</template>

<script>
import SurveyService from "@/services/SurveyService";

export default {
  name: "SurveyDetail",
  data() {
    return {
      survey: null,
    };
  },
  created() {
    const id = this.$route.params.id;
    SurveyService.get(id)
      .then((res) => {
        this.survey = res.data;
      })
      .catch((err) => console.error(err));
  },
};
</script>
