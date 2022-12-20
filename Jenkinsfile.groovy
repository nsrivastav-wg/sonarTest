node {
  try {
    stage('Static Analysis') {
      withCredentials([string(credentialsId: 'sonar-token', variable: 'user-token')]) {
        sh("curl -u ${user-token}: -d 'projectKey=$projectName&gateName=Lowest-QG' -X POST http://3.108.228.16:9000/api/qualitygates/select")
      }
      
      def scannerHome = tool 'SonarQube'
      def branch = 'dev'
      def path = './'
      def verbose = false
      withSonarQubeEnv('SonarQube'){
    sh("${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=${projectName} -Dsonar.sources=${path} -Dsonar.java.binaries=${path} -Dsonar.verbose=${verbose}")
    }
    }
  } catch (e) {
    throw e
  }
}
