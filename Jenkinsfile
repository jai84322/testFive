pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo 'Building the project...'
                // Replace with your actual build command, e.g., for Java: sh 'mvn clean install'
                bat 'echo Building on Windows'
            }
        }
        stage('Test') {
            steps {
                echo 'Running tests...'
                // Replace with your test command, e.g., for Java: sh 'mvn test'
                bat 'echo Running tests on Windows'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying application...'
                // Replace with your deployment command, e.g., for a simple deploy script: sh './deploy.sh'
                bat 'echo Deploying on Windows'
            }
        }
    }
}
