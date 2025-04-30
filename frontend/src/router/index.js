import { createRouter, createWebHistory } from "vue-router";
import SurveyList from "@/components/survey/SurveyList.vue";
import SurveyForm from "@/components/survey/SurveyForm.vue";
import SurveyDetail from "@/components/survey/SurveyDetail.vue";

const routes = [
  { path: "/", name: "SurveyList", component: SurveyList },
  { path: "/create", name: "CreateSurvey", component: SurveyForm },
  { path: "/edit/:id", name: "EditSurvey", component: SurveyForm, props: true },
  {
    path: "/surveys/:id",
    name: "SurveyDetail",
    component: SurveyDetail,
    props: true,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
