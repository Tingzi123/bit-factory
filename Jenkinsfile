pipeline {
    agent any
    /* triggers {
            pollSCM("H/5 * * * *")
    } */
    stages {
        stage('Test') {
            steps {
                sh './gradlew clean build'
            }
        }

        stage('Build') {
            steps {
                sh './ci build'
            }
        }

        stage('Deploy') {
            steps {
                sh './ci deploy'
            }
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