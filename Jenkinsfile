node {
     stage('Clone repository') {
         checkout scm
     }
     stage('Build image') {
         app = docker.build("unknown9732/wing-music")
     }
     stage('Push image') {
         docker.withRegistry('https://registry.hub.docker.com', 'unknown9732-docker') {
             app.push("latest")
         }
     }
 }