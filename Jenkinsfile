pipeline {
    agent any
    
    stages {

        stage('increment version 2') {
            steps {
                script {
                    echo "Incrementing version 2"
                    dir('app') {
                        npm version minor
                        sh "cat package.json| grep version"
                    }
                    
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
