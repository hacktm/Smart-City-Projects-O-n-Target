#!/bin/sh

cd ..
sshpass -p 'enjoycity' ssh root@enjoycity.busymachines.com /home/enjoycity/killApp.sh
sbt assembly
sshpass -p 'enjoycity' scp ./target/scala-2.11/enjoycity-0.1-SNAPSHOT.jar root@enjoycity.busymachines.com:/home/enjoycity
sshpass -p 'enjoycity' ssh root@enjoycity.busymachines.com java -cp /home/enjoycity/enjoycity-0.1-SNAPSHOT.jar com.enjoycity.Main