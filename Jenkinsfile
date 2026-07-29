pipeline {
    agent any

    environment {
    DOCKER_REPO = "557757/aws-devops-project"
    IMAGE_TAG = "${BUILD_NUMBER}"
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
                sh ''' docker build \
-t $DOCKER_REPO:$IMAGE_TAG \
-t $DOCKER_REPO:latest .'''
            }
        }

        stage('Docker Login') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh '''
                    echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin
                    '''
                }
            }
        }

        stage('Push Docker Image') {
    steps {
        sh '''
        echo "===== Docker Images ====="
        docker images

        echo "===== Pushing Build Tag ====="
        docker push $DOCKER_REPO:$IMAGE_TAG

        echo "===== Pushing Latest ====="
        docker push $DOCKER_REPO:latest
        '''
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
          $DOCKER_REPO:$IMAGE_TAG
        '''
    }
}
    }
}
