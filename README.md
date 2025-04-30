# SWE 645 HW3 - Survey Fullstack App (Spring Boot + Vue.js + CI/CD + Kubernetes)

## Project Overview
This project implements a full-stack survey application with:

- A **Spring Boot backend** exposing RESTful APIs
- A **Vue.js frontend** (extra credit)
- A **MySQL database** hosted on AWS RDS
- Full **CI/CD pipeline** via **Jenkins**
- **Dockerized** backend & frontend
- **Kubernetes deployment** using **Rancher**

---

## Directory Structure

```bash
survey-fullstack-cicd/
├── backend/                    # Spring Boot app
├── frontend/                   # Vue.js UI
├── infra/                      # Dockerfiles, NGINX config, and K8s YAMLs
│   ├── Dockerfile              # For backend
│   ├── ui.Dockerfile           # For frontend
│   ├── nginx.conf              # SPA routing for Vue
│   └── k8s/
│       ├── backend-deployment.yaml
│       ├── backend-service.yaml
│       ├── frontend-deployment.yaml
│       ├── frontend-service.yaml
├── Jenkinsfile                 # Jenkins pipeline config
└── README.md                   # Project documentation
```

---

## Technologies Used

| Component        | Stack                     |
|------------------|---------------------------|
| Backend          | Spring Boot + Maven       |
| Frontend         | Vue.js 3 + Vue Router     |
| Database         | AWS RDS (MySQL)           |
| CI/CD            | Jenkins + DockerHub       |
| Orchestration    | Kubernetes + Rancher      |
| Containerization | Docker                    |
| Hosting          | EC2 (Ubuntu 22.04)        |

---

## Setup Instructions

###  Prerequisites

- AWS account
- EC2 instances (1 for Jenkins, 1 or more for Rancher)
- DockerHub account
- GitHub repository

### Backend Setup

1. Build and run the Spring Boot API:
```bash
cd backend
mvn clean package
docker build -t arc2233/survey-api:amd64-v1.0 -f ../infra/Dockerfile .
docker push arc2233/survey-api:amd64-v1.0
```

2. Deploy backend to Kubernetes:
```bash
kubectl apply -f infra/k8s/backend-deployment.yaml
kubectl apply -f infra/k8s/backend-service.yaml
```

---

### Frontend Setup (Vue.js)

1. Build the frontend app:
```bash
cd frontend
docker build -t arc2233/survey-ui:amd64-v1.0 -f ../infra/ui.Dockerfile .
docker push arc2233/survey-ui:amd64-v1.0
```

2. Deploy frontend to Kubernetes:
```bash
kubectl apply -f infra/k8s/frontend-deployment.yaml
kubectl apply -f infra/k8s/frontend-service.yaml
```

---

## CI/CD Pipeline (Jenkins)

1. Launch Jenkins on EC2 (port `8080` open in security group).
2. Install:
   - Git
   - Docker
   - Plugins: Docker Pipeline, GitHub, Pipeline
3. Setup Jenkinsfile:
   - Uses `github-creds` and `docker-creds`
   - Builds both backend and frontend images
   - Pushes to DockerHub
   - Applies K8s manifests

4. Trigger build:
   - Automatically on `git push`
   - Or manually via Jenkins UI

---

## AWS RDS MySQL Setup

- Engine: MySQL
- Port: `3306`
- Connect via Spring Boot: `application.properties` includes DB URL, username, and password.

---

## Access Information

| Component   | URL Example |
|-------------|-------------|
| Frontend UI | `http://<ec2-ip>:<frontend-node-port>` |
| Backend API | `http://<ec2-ip>:<backend-node-port>/api/v1.0/surveys` |

> **Tip**: Use `kubectl get svc` to find `NodePort` values.

---

## Functionality Checklist

- [x] Spring Boot REST APIs (`GET`, `POST`, `PUT`, `DELETE`)
- [x] MySQL RDS integration
- [x] Dockerized backend and frontend
- [x] Kubernetes deployment (via Rancher)
- [x] Jenkins CI/CD pipeline (multi-stage)
- [x] Vue.js UI (extra credit)
- [x] Postman tested API
- [x] End-to-end working demo

---

## Demo Video (Include link here)
> [Insert Google Drive / YouTube link here]

---

## Author

**Amlan Chowdhury**  
Email: arc2233@outlook.com 
GitHub: [https://github.com/amlan-roy-chowdhury](https://github.com/amlan-roy-chowdhury)
