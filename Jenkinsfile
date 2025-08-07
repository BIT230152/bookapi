pipeline{
    agent any
    stages{
        stage('clone'){
            steps{
                echo 'Cloning source code'
                git branch: 'main', url: 'https://github.com/BIT230152/bookapi.git'

            }
        }
        
    }
}