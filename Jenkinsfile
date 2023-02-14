#!groovy
import groovy.json.JsonOutput

def cmd = """
            echo "cmd ing"
            cd /app/toy_api || exit
            ls -la
            sudo ./restart.sh
          """ as java.lang.Object

pipeline {
    agent any
    environment {
        SLACK_CHANNEL = '#배포방'
        BUILD_USER_ID = "DH"
        BUILD_USER = "DH"
    }
    stages {
        stage('check build user') {
                steps {
                        script {
                            BUILD_USER_ID = "${env.BUILD_USER_ID}"
                            BUILD_USER = "${env.BUILD_USER}"
                        }
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
                                        userRemoteConfigs                : [[credentialsId : 'Admin', name:'origin',url: 'https://github.com/RadSparkle/ToyProject.git']]
                                ]
                                )
                    }
        }
        stage('Build') {
                    steps {
                        script {
                            try {
                                sh ("chmod 755 ./gradlew")
                                sh ("./gradlew clean bootJar")
                                env.jarfile = sh (script: 'basename build/libs/*.jar .jar', returnStdout: true ).trim()
                                echo "set File ${env.jarfile}.jar"
                                sh ("ls -la")
                                slackSend (channel: SLACK_CHANNEL, color: '#00FF00', message: "빌드 성공: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
                            } catch (e) {
                                slackSend (channel: SLACK_CHANNEL, color: '#FF0000', message: "빌드 실패: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
                            }
                        }
                    }
        }
        stage('Release') {
                        steps {
                            script {
                                try {
                                    sh ("cp ${JENKINS_HOME}/workspace/API/build/libs/*.jar /app/toy_api/toy_api.jar")
                                    sh cmd
                                    slackSend (channel: SLACK_CHANNEL, color: '#00FF00', message: "배포 성공: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
                                } catch (e) {
                                    slackSend (channel: SLACK_CHANNEL, color: '#FF0000', message: "배포 실패: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
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