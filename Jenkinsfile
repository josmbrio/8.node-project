pipeline {
    agent any
    
    stages {

        stage('increment version') {
            steps {
                sript {
                    echo "Incrementing version"

                }
            }
        }

        stage('test') {
            steps {
                sript {
                    echo "Testing"
                }
            }
        }

        stage('package app') {
            steps {
                sript {
                    echo "Packaging app"
                }
            }
        }
        
        stage('build image') {
            steps {
                sript {
                    echo "Building image"
                }
            }
        }

        stage('commit version') {
            steps {
                sript {
                    echo "Commiting version"
                }
            }
        }
    }
}
