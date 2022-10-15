library identifier: 'jenkins-shared-library@main', retriever: modernSCM(
[$class: 'GitSCMSource',
 remote: 'https://myserver/josmbrio/jenkins-shared-library.git',
 credentialsId: 'gitlab-credentials'
])
//@Library('https://myserver/josmbrio/jenkins-shared-library.git')

def groovyScript
pipeline {
    agent any
    environment {
        SERVER_IP = "18.234.234.175"
        SERVER_CREDENTIALS = "ec2-credentials-ex"

    }
    
    stages {
/*        stage("init Groovy scripts") {
            steps {
                script {
                    groovyScript = load "increment_version_app_nodejs.groovy"
                }              
            }           
        }
*/
        stage("increment version 2") {
            steps {
                script {
                    echo "Incrementing version 2"
                    dir("app") {
                        def newVersion = increment_version_app_nodejs()
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
                        test_app_nodejs()
                    }
                }
            }
        }
        
        stage("build image") {
            steps {
                script {
                    echo "Building image"
                    build_image("josmbrio/my-repo", "$IMAGE_NAME")                     
                }
            }
        }

        stage("deploy") {
            steps {
                script {
                    echo "Deploying app"
                    def startScript = 'start-commands.sh ${IMAGE_NAME}'
                    sshagent(['${SERVER_CREDENTIALS'}]) {
                        //copiar docker-compose.yaml y start-commands.sh a instancia ec2
                        sh 'scp docker-compose.yaml ${SERVER_IP}'
                        sh 'scp start-commands.sh ${SERVER_IP}'
                        //ejecutar start-commands.sh en remoto en instancia ec2 pasando IMAGE_NAME
                        sh 'ssh o StrictHostKeyChecking=no ${SERVER_IP} bash ${startScript}'
                    } 
                    
                           
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
