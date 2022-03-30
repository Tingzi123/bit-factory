pipeline {
    agent any
    /* triggers {
            pollSCM("H/5 * * * *")
    } */
    stages {
        stage('Initialize'){
            def dockerHome = tool 'myDocker'
            env.PATH = "${dockerHome}/bin:${env.PATH}"
        }
        stage('Test') {
            agent {
                docker { image 'testcontainers/ryuk' }
            }
            steps {
                sh './gradlew clean build'
            }
        }

        stage('Build') {
            agent {
                label 'docker'
            }
            steps {
                sh './ci build'
            }
        }

        stage('Deploy') {
            agent {
                label 'docker'
            }
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