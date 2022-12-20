node {
  try {
    stage('Deploy') {
      sh("curl -u squ_f1e4ff083b9e22a1bcf7b83d3fb8f471784136bd: -d 'projectKey=$projectName&gateName=Lowest-QG' -X POST http://3.108.228.16:9000/api/qualitygates/select")
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
