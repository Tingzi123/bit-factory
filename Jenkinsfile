pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh './gradlew clean build'
            }
        }

        stage('Test') {
            steps {
                sh './gradlew test'
            }
        }

        stage('Deploy') {
            echo "Deploy!"
        }
    }
    post {
        success {
            echo "deploy success!"
        }

        failure {
            echo "deploy failure!"
        }
    }
}