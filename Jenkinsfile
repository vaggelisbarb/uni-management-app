
pipeline {
    agent any

    tools {
        maven 'Maven-3.9.9'
        jdk 'JDK-21'
    }

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'main', url: 'https://github.com/vaggelisbarb/uni-management-app.git'
            }
        }

        stage('Build') {
            steps {
                dir('uni-management-app') { // Αν το repo κατεβαίνει σε φάκελο
                    bat 'mvn clean package'
                }
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Deploy') {
            steps {
                bat 'java -jar target/uni-management-app.jar'
            }
        }
    }
}
