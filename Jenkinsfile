pipeline {
    agent any
    /* triggers {
            pollSCM("H/5 * * * *")
    } */
    tools {
            jdk 'jdk11'
        }
    stages {
        stage('Test') {
            agent {
                docker { image 'testcontainers/ryuk' }
            }
            steps {
//                 env.JAVA_HOME="${tool 'jdk11'}"
//                 echo $JAVA_HOME
//                 sh 'lstttt /Users/tingchen'
//                 sh "JAVA_HOME=/usr/local/Cellar/openjdk@11/11.0.14.1/libexec/openjdk.jdk/Contents/Home"
//                 echo $JAVA_HOME
                sh 'java -version'
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