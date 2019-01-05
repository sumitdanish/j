#!/bin/sh

#COMMAND LINE VARIABLES
# deploy port SECOND ARGUMENT
# Ex: 8081 | 8091 | 8092 
echo "start"

serverPort=$1
echo $1

#environment=$2
sudo su

#####
##### DONT CHANGE HERE ##############
#jar file
# $WORKSPACE is a jenkins var
echo "OK: $WORKSPACE"
targetPath=/var/lib/jenkins/workspace/j/jenkins/target/
sourFile=$targetPath*.jar
echo $sourFile
destFile=/var/lib/jenkins/data/*.jar
echo $destFile
### FUNCTIONS
##############

app_stop(){
    echo "insideStop"
    echo ""
    echo "Stoping process on port: $serverPort"
    fuser -k $severport > redirection &
    echo ""
}

app_delete(){
    echo "Deleting $destFile"
    rm -rf $destFile
    #rm -rf $targetPath
    echo ""
}

app_copy(){
    echo "Copying files from $sourFile"
    cp $sourFile $destFile
    echo ""
}

app_change(){
    echo "Changing File Permission: chmod 777 $destFile"
    chmod 777 $destFile
    echo ""
}
app_run(){
    #echo “java -jar $destFile –server.port=$serverPort $properties” | at now + 1 minutes
    nohup nice java -jar $destFile –server.port=$serverPort &
    echo "COMMAND: nohup nice java -jar $destFile"
    echo ""
}

### FUNCTIONS CALLS
#####################

# 1 – stop server on port
echo "stoping server"
app_stop

# 2 – delete destinations folder content
echo "deleting file"
app_delete

# 3 – copy files to deploy dir
echo "copying file"
app_copy

echo "changeFilePermission"
#app_change
# 4 – start server

echo "running...."
app_run
