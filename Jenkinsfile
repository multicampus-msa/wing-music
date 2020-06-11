node {
     stage('Clone repository') {
         checkout scm
     }

     stage('chmod') {
         sh 'chmod +x gradlew'
     }

     stage('Gradle Build') {
         sh './gradlew bootjar'
         sh 'cp /var/lib/jenkins/workspace/wing-user/build/libs/*.jar ./'
     }

     stage('Build & Push image') {
          docker.withRegistry('https://registry.hub.docker.com', 'midaslmg94-docker') {
             def image = docker.build("midaslmg94/wing-user:latest")
             image.push()
             sh 'sudo docker service update --image midaslmg94/wing-user wing-user'
         }
     }
 }
