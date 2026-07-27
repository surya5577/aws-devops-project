pipeline {
    agent any

    environment {
        IMAGE_NAME = "557757/aws-devops-project:v1"
        CONTAINER_NAME = "springboot-app"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build JAR') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Push Docker Image') {
            steps {
                sh 'docker push $IMAGE_NAME'
            }
        }

        stage('Deploy Container') {
            steps {
                sh '''
                docker stop $CONTAINER_NAME || true
                docker rm $CONTAINER_NAME || true

                docker run -d \
                  --name $CONTAINER_NAME \
                  -p 8081:8081 \
                  $IMAGE_NAME
                '''
            }
        }
    }
}
