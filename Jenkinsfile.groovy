node {
  try {
    stage('Deploy') {
      sh("curl -u sqa_46ee6fd0572c2b9dcf392015aac477ebaf9be130: -d 'projectKey=$projectName&gateName=Lowest-QG' -X POST http://3.108.228.16:9000/api/qualitygates/select")
      def scannerHome = tool 'SonarQube'
      def branch = 'dev'
      def path = 'files'
      def verbose = false
      withSonarQubeEnv('SonarQube'){
    sh("${scannerHome}/bin/sonar-scanner -Dsonar.branch.name=${branch} -Dsonar.projectKey=${projectName} -Dsonar.sources=${path} -Dsonar.java.binaries=${path} -Dsonar.verbose=${verbose}")
    }
    }
  } catch (e) {
    throw e
  }
}
