node {
     stage('Clone repository') {
         checkout scm
     }

     stage('chmod') {
         sh 'chmod +x gradlew'
     }

     stage('Gradle Build') {
         sh './gradlew bootjar'
     }

     stage('Build & Push image') {
         docker.withRegistry('https://registry.hub.docker.com', 'unknown9732-docker') {
             def image = docker.build("unknown9732/wing-music:latest", "--build-arg JAR_FILE=build/libs/*.jar", "-f ${dockerfile} .")
             image.push()
         }
     }

     stage('update service') {
         sh 'sudo docker service update --image unknown9732/wing-music wing-music'
     }
 }