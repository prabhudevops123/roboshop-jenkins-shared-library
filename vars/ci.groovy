def call() {
    pipeline {
        agent any

        stages {

            stage('Compile/Build') {
                steps {
                    echo 'Compile/Build'

                }
            }
            stage('Test Cases') {
                steps {
                    echo 'Test Cases'
                }
            }
        }
    }
}




//def call() {
//    pipeline {
//        agent any
//
//        stages {
//
//            stage('Compile/Build') {
//                steps {
//                    script {
//                        common.compile()
//                    }
//                }
//            }
//
//            stage('Test Cases') {
//                steps {
//                    script {
//                        common.testcases()
//                    }
//                }
//            }
//
//            stage('Code Quality') {
//                steps {
//                    script {
//                        common.codequality()
//                    }
//                }
//            }
//        }
//
//    }
//}