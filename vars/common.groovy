def compile() {
    if(app_lang == "nodejs") {
        sh 'npm install'
    }
    if(app_lang == "maven") {
        sh 'mvn package'
    }
}

def testcases() {
    // npm test
    // mvn test
    // python -m unittests
    // go test
    sh 'echo OK'
}

def codequality() {
    withAWSParameterStore(credentialsId: 'PARAM1', naming: 'absolute', path: '/sonarqube', recursive: true, regionName: 'us-east-1') {
        sh 'sonar-scanner -Dsonar.host.url=http://172.31.6.76:9000 -Dsonar.login=${SONARQUBE_USER} -Dsonar.password=${SONARQUBE_PASS} -Dsonar.projectKey=${component} ${sonar_extra_opts}'
        sh 'echo OK'
    }

//def codequality() {
//    withAWSParameterStore(credentialsId: 'PARAM1', naming: 'absolute', path: '/sonarqube', recursive: true, regionName: 'us-east-1') {
//        sh 'sonar-scanner -Dsonar.host.url=http://172.31.6.76:9000 -Dsonar.login=admin -Dsonar.password=admin123 -Dsonar.projectKey=${component} ${sonar_extra_opts}'
//    }
    // -Dsonar.qualitygate.wait=true
//}