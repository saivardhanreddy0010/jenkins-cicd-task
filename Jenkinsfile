pipeline {

    agent any

    stages {

        stage('Build') {
            steps {
                echo 'Building Docker image...'
                sh 'docker build -t jenkins-cicd-app:latest .'
            }
        }

        stage('Test') {
            steps {
                echo 'Running application tests...'
                sh 'docker run --rm jenkins-cicd-app:latest npm test'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying application...'

                sh '''
                    docker stop jenkins-cicd-app || true
                    docker rm jenkins-cicd-app || true

                    docker run -d \
                      --name jenkins-cicd-app \
                      -p 3000:3000 \
                      jenkins-cicd-app:latest
                '''
            }
        }
    }

    post {
        success {
            echo 'CI/CD Pipeline completed successfully!'
        }

        failure {
            echo 'CI/CD Pipeline failed!'
        }
    }
}