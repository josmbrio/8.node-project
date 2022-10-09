pipeline {
    agent any
    
    stages {

        stage('increment version') {
            steps {
                script {
                    echo "Incrementing version"
                    npm version minor
                }
            }
        }

        stage('test') {
            steps {
                script {
                    echo "Testing"
                }
            }
        }

        stage('package app') {
            steps {
                script {
                    echo "Packaging app"
                }
            }
        }
        
        stage('build image') {
            steps {
                script {
                    echo "Building image"
                }
            }
        }

        stage('commit version') {
            steps {
                script {
                    echo "Commiting version"
                }
            }
        }
    }
}
