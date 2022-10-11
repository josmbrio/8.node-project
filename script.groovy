incrementVersionPackageJSON() {

    sh "npm version minor"                  
    def file = readJSON file:"package.json"
    def version = file.version
    return $version

}

return this

