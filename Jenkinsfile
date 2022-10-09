pipeline {
    agent any
    
    stages {

        stage("increment version 2") {
            steps {
                script {
                    echo "Incrementing version 2"
                    dir("app") {
                        sh "npm version minor"
                        sh "cat package.json | grep -i version"
                    
                        def file = readJSON file:"package.json"
                        def version = file.version
                        env.IMAGE_NAME = "node-app-${version}-$BUILD_NUMBER"

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
                        sh "npm install"
                        sh "npm test"
                    }
                }
            }
        }
        
        stage("build image") {
            steps {
                script {
                    echo "Building image"
                    sh "docker build . -t josmbrio/my-repo:$IMAGE_NAME"

                    withCredentials([usernamePassword(credentialsId: "docker-hub-repo",usernameVariable: "USER", passwordVariable: "PASS")
                    ]) {
                        sh "echo $PASS | docker login -u josmbrio --password-stdin"
                        sh "docker push josmbrio/my-repo:$IMAGE_NAME"
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
