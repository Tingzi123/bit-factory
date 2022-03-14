pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                ./gradlew clean build
            }
        }

        stage('Test') {
            steps {
                ./gradlew test
            }
        }

        stage("Deploy") {
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