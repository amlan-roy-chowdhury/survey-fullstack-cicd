pipeline {
    agent any

    environment {
        DOCKER_IMAGE_BACKEND = "arc2233/survey-api:amd64-v1.0"
        DOCKER_IMAGE_FRONTEND = "arc2233/survey-ui:amd64-v1.0"
    }

    stages {
        stage('Checkout') {
            steps {
                git credentialsId: 'github-creds', url: 'https://github.com/amlan-roy-chowdhury/survey-fullstack-cicd.git'
            }
        }

        stage('Build Backend Image') {
            steps {
                dir('springboot-survey-backend') {
                    sh 'docker build --platform linux/amd64 -t $DOCKER_IMAGE_BACKEND .'
                    withCredentials([usernamePassword(credentialsId: 'docker-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                        sh 'docker push $DOCKER_IMAGE_BACKEND'
                    }
                }
            }
        }

        stage('Build Frontend Image') {
            steps {
                dir('survey-vue-client') {
                    sh 'docker build --platform linux/amd64 -f infra/ui.Dockerfile -t $DOCKER_IMAGE_FRONTEND .'
                    withCredentials([usernamePassword(credentialsId: 'docker-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                        sh 'docker push $DOCKER_IMAGE_FRONTEND'
                    }
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                sh 'kubectl apply -f infra/k8s/backend-deployment.yaml'
                sh 'kubectl apply -f infra/k8s/backend-service.yaml'
                sh 'kubectl apply -f infra/k8s/frontend-deployment.yaml'
                sh 'kubectl apply -f infra/k8s/frontend-service.yaml'
            }
        }
    }
}
