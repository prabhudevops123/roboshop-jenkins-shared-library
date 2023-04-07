//def call() {
//    pipeline {
//        agent any
//
//        stages {
//
//            stage('Compile/Build') {
//                steps {
//                    echo 'Compile/Build'
//
//                }
//            }
//            stage('Test Cases') {
//                steps {
//                    echo 'Test Cases'
//                }
//            }
//        }
//    }
//}

def call() {
    if (!env.sonar_extra_opts) {
        env.sonar_extra_opts=""
    }
    pipeline {
        agent any

        stages {

            stage('Compile/Build') {
                steps {
                    mail bcc: '', body: 'Test', cc: '', from: 'prabh.rao@gmail.com', replyTo: '', subject: 'Test', to: 'prabh.rao@gmail.com'
                    script {
                        common.compile()
                    }
                }
            }

            stage('Test Cases') {
                steps {
                    script {
                        common.testcases()
                    }
                }
            }

            stage('Code Quality') {
                steps {
                    script {
                        common.codequality()
                    }
                }
            }
        }

        post {
            failure {
                mail body: "<h1>${component} - Pipeline Failed \n ${BUILD_URL}</h1>", from: 'prabh.rao@gmail.com', subject: "${component} - Pipeline Failed", to: 'prabh.rao@gmail.com',  mimeType: 'text/html'
            }
        }

    }
}