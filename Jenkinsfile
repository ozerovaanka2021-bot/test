pipeline {
  agent { label 'tqa-java-21' }
  options { timeout(time: 10, unit: 'MINUTES'); disableConcurrentBuilds() }
  stages {
    stage('verify') {
      steps {
        // Starter fault: this path intentionally does not exist.
                        sh 'test -f gradlew'
        sh 'sh gradlew --no-daemon compileJava'
      }
    }
    stage('test') {
      steps { sh 'sh gradlew --no-daemon test' }
          post {
       always {
          junit 'build/test-results/test/TEST-*.xml'
         archiveArtifacts artifacts: 'build/allure-results/**/*', allowEmptyArchive: true
       }
     }
    }
    stage('gate') {
      steps { sh 'sh gradlew --no-daemon check' }
    }
  }
}
