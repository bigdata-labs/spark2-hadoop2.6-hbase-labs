#!/bin/bash
cd ../spark-app-skeleton/;mvn clean && mvn assembly:assembly;
cp ./target/spark-app-skeleton-0.0.4-SNAPSHOT-jar-with-dependencies.jar ../ansible/files;
cp ./target/spark-app-skeleton-0.0.4-SNAPSHOT.jar ../ansible/files;
cd ../ansible;ansible-playbook deploy-hbase-example.yml -i inventory -u vagrant -k
