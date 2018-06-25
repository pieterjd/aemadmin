pipeline {
    agent none
    stages {
        stage('Example Build') {
            agent any
            steps {
                echo 'Hello, Maven'
                sh 'mvn -Dmaven.test.failure.ignore=true clean install'
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
