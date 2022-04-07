pipeline {
    agent any
    /* triggers {
            pollSCM("H/5 * * * *")
    } */
    stages {
        stage('Test') {
            agent {
                docker { image 'testcontainers/ryuk' }
            }
            steps {
                sh './gradlew clean build'
            }
        }

        stage('Build') {
            steps {
                echo "Build!!"
                sh 'docker build -t 127.0.0.1:5000/bitfactory .'
                sh 'docker push 127.0.0.1:5000/bitfactory'
//                 sh './ci build'
            }
        }

        stage('Deploy') {
            agent {
                label 'docker'
            }
            steps {
                echo "Deploy!!"

//                 sh './ci deploy'
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