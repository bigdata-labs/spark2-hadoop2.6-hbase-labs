# -*- mode: ruby -*-
# vi: set ft=ruby :

# All Vagrant configuration is done below. The "2" in Vagrant.configure
# configures the configuration version (we support older styles for
# backwards compatibility). Please don't change it unless you know what
# you're doing.

Vagrant.configure(2) do |config|


  ANSIBLE_RAW_SSH_ARGS = []
  VAGRANT_VM_PROVIDER = "virtualbox"
  machine_box = "u16"
  #machine_box = "boxcutter/ubuntu1604"

  config.vm.define "offlinenode1" do |machine|
    machine.vm.box = machine_box
    machine.vm.hostname = "offlinenode1"
    machine.vm.network "private_network", ip: "192.168.11.151"
    machine.vm.network "forwarded_port", guest: 50070, host: 50070  # for hadoop
    machine.vm.provider "virtualbox" do |node|
        node.name = "offlinenode1"
        node.memory = 4096
        node.cpus = 4
    end
   end

   config.vm.define "offlinenode2" do |machine|
     machine.vm.box = machine_box
     machine.vm.hostname = "offlinenode2"
     machine.vm.network "private_network", ip: "192.168.11.152"
     machine.vm.network "forwarded_port", guest: 8080, host: 8080 # for spark
     machine.vm.provider "virtualbox" do |node|
         node.name = "offlinenode2"
         node.memory = 4096
         node.cpus = 4
     end
    end

    config.vm.define "offlinenode3" do |machine|
      machine.vm.box = machine_box
      machine.vm.hostname = "offlinenode3"
      machine.vm.network "private_network", ip: "192.168.11.153"
      machine.vm.network "forwarded_port", guest: 16010, host: 16010 # for spark
      machine.vm.provider "virtualbox" do |node|
          node.name = "offlinenode3"
          node.memory = 4096
          node.cpus = 4
      end
     end

     config.vm.define "offlinenode4" do |machine|
       machine.vm.box = machine_box
       machine.vm.hostname = "offlinenode4"
       machine.vm.network "private_network", ip: "192.168.11.154"
       machine.vm.network "forwarded_port", guest: 8088, host: 8088 # ganglia
       machine.vm.provider "virtualbox" do |node|
           node.name = "offlinenode4"
           node.memory = 2048
           node.cpus = 2
       end
      end
end
