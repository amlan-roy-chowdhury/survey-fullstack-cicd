pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-creds')  // Jenkins -> Credentials -> Global -> Add username/password with ID: dockerhub-creds
        DOCKER_IMAGE_API = "arc2233/survey-api:amd64-v1.0"
        DOCKER_IMAGE_UI = "arc2233/survey-ui:amd64-v1.0"
        KUBE_CONFIG_PATH = "/var/lib/jenkins/.kube/config"
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/YOUR_USERNAME/YOUR_REPO_NAME.git'
            }
        }

        stage('Build Spring Boot App') {
            steps {
                dir('springboot-survey-backend') {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build & Push Backend Docker Image') {
            steps {
                dir('springboot-survey-backend') {
                    sh """
                        docker build --platform linux/amd64 -t ${DOCKER_IMAGE_API} .
                        echo "$DOCKERHUB_CREDENTIALS_PSW" | docker login -u "$DOCKERHUB_CREDENTIALS_USR" --password-stdin
                        docker push ${DOCKER_IMAGE_API}
                    """
                }
            }
        }

        stage('Build & Push Frontend Docker Image') {
            steps {
                dir('survey-vue-client') {
                    sh """
                        docker build --platform linux/amd64 -f infra/ui.Dockerfile -t ${DOCKER_IMAGE_UI} .
                        echo "$DOCKERHUB_CREDENTIALS_PSW" | docker login -u "$DOCKERHUB_CREDENTIALS_USR" --password-stdin
                        docker push ${DOCKER_IMAGE_UI}
                    """
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                sh """
                    kubectl apply -f infra/k8s/backend-deployment.yaml
                    kubectl apply -f infra/k8s/backend-service.yaml
                    kubectl apply -f infra/k8s/frontend-deployment.yaml
                    kubectl apply -f infra/k8s/frontend-service.yaml
                """
            }
        }
    }

    post {
        success {
            echo 'Deployment completed successfully!'
        }
        failure {
            echo 'Build or deployment failed.'
        }
    }
}
