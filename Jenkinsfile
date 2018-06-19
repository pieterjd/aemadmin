pipeline {
    agent none
    stages {
        stage('Example Build') {
            agent any
            steps {
                echo 'Hello, Maven'
                sh 'mvn clean install'
            }
        }
        stage('Example Test') {
            agent any
            steps {
                echo 'Hello, JDK'
                sh 'java -version'
            }
        }
    }
}
