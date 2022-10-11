increment_version_app_nodejs() {

    sh "npm version minor"                  
    def file = readJSON file:"package.json"
    def version = file.version
    return $version

}

test_app_nodejs() {

    sh "npm install"
    sh "npm test"

}

build_image(String repo, String image_name) {
    sh "docker build . -t $repo:$image_name"
    withCredentials([usernamePassword(credentialsId: "docker-hub-repo",usernameVariable: "USER", passwordVariable: "PASS")]) {
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push $repo:$image_name"
    }
}

return this

