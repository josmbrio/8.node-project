pipeline {
    agent any
    
    stages {

        stage('increment version') {
            steps {
                script {
                    echo "Incrementing version"
                    dir('app') {
                        sh "npm version minor"
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
