#!groovy
import groovy.json.JsonOutput

def mkdir = """
          rm -rf /home/jenkins/api || exit
          [[ ! -d "/home/jenkins/api" ]] && mkdir /home/jenkins/api
          """ as java.lang.Object

pipeline {
    agent any
//     parameters {
//         gitParameter branch: 'origin/$BRANCH_NAME', name: 'REVISION', type: 'PT_REVISION'
//     }
    environment {
        SLACK_CHANNEL = '#배포방'
        BUILD_USER_ID = "테스트유저"
        BUILD_USER = "테스트유저"
    }
    stages {
        stage('check build user') {
                steps {
                        script {
                            BUILD_USER_ID = "${env.BUILD_USER_ID}"
                            BUILD_USER = "${env.BUILD_USER}"
                        }
                    // Test out of wrap
                    echo "Build User ID: ${BUILD_USER_ID}"
                    echo "Build User: ${BUILD_USER}"
                }
        }
        stage('checkout') {
                    steps {
                        checkout(
                                [
                                        $class                           : 'GitSCM',
                                        branches                         : [[name: '$BRANCH_NAME']],
                                        doGenerateSubmoduleConfigurations: false,
                                        extensions                       : [],
                                        submoduleCfg                     : [],
                                        userRemoteConfigs                : [[credentialsId : 'Admin', name:'origin',url: 'https://github.com/RadSparkle/Jenkins-TEST.git']]
                                ]
                                )
                    }
        }
        stage('Build') {
                    steps {
                        script {
                            try {
                                sh ("cd ${JENKINS_HOME}/workspace/API")
                                sh ("chmod 755 ./gradlew")
                                sh ("./gradlew clean bootJar")
                                env.jarfile = sh (script: 'basename build/libs/*.jar .jar', returnStdout: true ).trim()
                                echo "set File ${env.jarfile}.jar"
                                sh ("ls -la")
//                                 slackSend (channel: SLACK_CHANNEL, color: '#00FF00', message: "빌드 성공: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
                            } catch (e) {
//                                 slackSend (channel: SLACK_CHANNEL, color: '#FF0000', message: "빌드 실패: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
                            }
                        }
                    }
        }
        stage('Release') {
                        steps {
                            script {
                                try {
                                    cd ("/app/toy_api")
                                    sh ("ls -la")
                                    sh ("cp ${JENKINS_HOME}/workspace/API/build/libs/*.jar /app/toy_api/toy_api.jar")
                                    sh ("/app/toy_api/api_execute.sh")
//                                     slackSend (channel: SLACK_CHANNEL, color: '#00FF00', message: "배포 성공: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
                                } catch (e) {
//                                     slackSend (channel: SLACK_CHANNEL, color: '#FF0000', message: "배포 실패: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
                                }
                            }
                        }
                        }
    }
//     post {
//         success {
//             slackSend (channel: SLACK_CHANNEL, color: '#00FF00', message: "배포 성공: '${env.BUILD_USER} 'Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
//         }
//         failure {
//             slackSend (channel: SLACK_CHANNEL, color: '#FF0000', message: "배포 실패: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
//         }
//     }
}