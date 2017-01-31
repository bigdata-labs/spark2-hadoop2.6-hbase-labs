## get start

1. download vagrant box: https://pan.baidu.com/s/1c1SAXTQ
1. vagrant box add u16 /path/you_downloaded_box
1. vagrant up
1. download hbase, hadoop, spark
1. download [hadoop-native-64-2.6.0.tar](http://dl.bintray.com/sequenceiq/sequenceiq-bin/:hadoop-native-64-2.6.0.tar) to ./ansible/roles/spark2/files
1. run ` ansible-playbook ./ansible/playbook.yml -i ./ansible/inventory -u vagrant -k`
  password is `vagrant`


download jdk8: https://pan.baidu.com/s/1bpxfpvD

download hbase: https://pan.baidu.com/s/1slhdGhZ

download hadoop: https://pan.baidu.com/s/1dFkjWch

download spark: https://pan.baidu.com/s/1dE6ows5

## checking
hdfs: http://192.168.11.151:50070/dfshealth.html#tab-datanode

spark: http://192.168.11.152:8080/

hbase: http://192.168.11.153:16010/master-status

## deploy spark hbase example

* word count example
	1. chmod +x ./ansible/deploy-wordcount.sh
	1. run `./ansible/deploy-wordcount.sh`

* the example for Spark integrate with Hbase
	1. chmod +x ./ansible/deploy-hbase-example.sh
	1. run `./ansible/deploy-hbase-example.sh`
