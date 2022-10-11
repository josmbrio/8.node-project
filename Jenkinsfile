def groovyScript
pipeline {
    agent any
    
    stages {
        stage("init Groovy scripts") {
            steps {
                script {
                    groovyScript = load "script.groovy"
                }              
            }           
        }

        stage("increment version 2") {
            steps {
                script {
                    echo "Incrementing version 2"
                    dir("app") {
                        def newVersion = groovyScript.increment_version_app_nodejs()
                        env.IMAGE_NAME = "node-app-${newVersion}-$BUILD_NUMBER"
                        echo "Image Name: $IMAGE_NAME"
                    }
                    
                }
            }
        }

        stage("test") {
            steps {
                script {
                    echo "Testing"
                    dir("app") {
                        groovyScript.test_app_nodejs()
                    }
                }
            }
        }
        
        stage("build image") {
            steps {
                script {
                    echo "Building image"
                    groovyScript.build_image("josmbrio/my-repo", "$IMAGE_NAME")                     
                }
            }
        }

        stage("commit version") {
            steps {
                script {
                    echo "Commiting version"
                    withCredentials([usernamePassword(credentialsId: 'gitlab-credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                        sh 'git config user.email "jenkins@example.com"'
                        sh 'git config user.name "jenkins"'

                        sh 'git remote set-url origin https://${USER}:${PASS}@192.168.100.6/josmbrio/8.node-project.git'
                        sh 'git add .'
                        sh 'git commit -m "This is from Jenkins version bump"'
                        sh 'git push origin HEAD:main'
                    }                                                            
                }
            }
        }
    }
}
