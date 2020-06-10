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

     stage('Build image') {
         sh 'sudo docker build --build-arg JAR_FILE=build/libs/*.jar -t unknown9732/wing-music .'
     }

     stage('Push image') {
         docker.withRegistry('https://registry.hub.docker.com', 'unknown9732-docker') {
             sh 'sudo docker push unknown9732/wing-music'
         }
     }

     stage('update service') {
         sh 'sudo docker service update --image unknown9732/wing-music wing-music'
     }
 }