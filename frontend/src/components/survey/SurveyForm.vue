<template>
  <div>
    <h2>{{ isEdit ? "Edit" : "Create" }} Survey</h2>
    <form @submit.prevent="handleSubmit">
      <label>First Name: <input v-model="survey.firstName" required /></label
      ><br />
      <label>Last Name: <input v-model="survey.lastName" required /></label
      ><br />
      <label>Email: <input v-model="survey.email" required /></label><br />
      <label>Address: <input v-model="survey.address" required /></label><br />
      <label>City: <input v-model="survey.city" required /></label><br />
      <label>State: <input v-model="survey.state" required /></label><br />
      <label>Zip: <input v-model="survey.zip" required /></label><br />
      <label>Telephone: <input v-model="survey.telephone" required /></label
      ><br />
      <label
        >Date of Survey:
        <input v-model="survey.dateOfSurvey" type="date" required /></label
      ><br />
      <label
        >Recommendation:
        <select v-model="survey.recommendation">
          <option>Very Likely</option>
          <option>Likely</option>
          <option>Unlikely</option>
        </select> </label
      ><br />
      <label
        >Interest:
        <select v-model="survey.interest">
          <option>Friends</option>
          <option>Television</option>
          <option>Internet</option>
          <option>Other</option>
        </select> </label
      ><br />
      <label
        >Likings:
        <select v-model="survey.likings">
          <option>Students</option>
          <option>Location</option>
          <option>Campus</option>
          <option>Atmosphere</option>
          <option>Dorm Rooms</option>
          <option>Sports</option>
        </select> </label
      ><br />
      <button type="submit">{{ isEdit ? "Update" : "Submit" }}</button>
    </form>
  </div>
</template>

<script>
import SurveyService from "@/services/SurveyService";

export default {
  name: "SurveyForm",
  data() {
    return {
      survey: {
        firstName: "",
        lastName: "",
        email: "",
        address: "",
        city: "",
        state: "",
        zip: "",
        telephone: "",
        dateOfSurvey: "",
        recommendation: "",
        interest: "",
        likings: "",
      },
    };
  },
  computed: {
    isEdit() {
      return !!this.$route.params.id;
    },
  },
  created() {
    if (this.isEdit) {
      SurveyService.get(this.$route.params.id).then((res) => {
        this.survey = res.data;
      });
    }
  },
  methods: {
    handleSubmit() {
      if (this.isEdit) {
        SurveyService.update(this.$route.params.id, this.survey).then(() => {
          this.$router.push("/");
        });
      } else {
        SurveyService.create(this.survey).then(() => {
          this.$router.push("/");
        });
      }
    },
  },
};
</script>
