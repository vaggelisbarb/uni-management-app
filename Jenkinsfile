
pipeline {
    agent any

    tools {
        maven 'Maven-3.9.9'
    }

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'main', url: 'https://github.com/vaggelisbarb/uni-management-app.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Deploy') {
            steps {
                sh 'java -jar target/uni-management-app.jar'
            }
        }
    }
}
