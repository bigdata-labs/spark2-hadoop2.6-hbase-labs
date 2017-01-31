# -*- mode: ruby -*-
# vi: set ft=ruby :

# Vagrantfile API/syntax version. Don't touch unless you know what you're doing!
VAGRANTFILE_API_VERSION = "2"

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|

	config.vm.box = "ubuntu/trusty64"
	config.vm.box_check_update = false

	config.ssh.forward_agent = true

	config.vm.provider :virtualbox do |vb|
		vb.customize ["modifyvm", :id, "--memory", 1024]
		vb.customize ["modifyvm", :id, "--cpus", 1]

		vb.customize ["modifyvm", :id, "--natdnshostresolver1", "on"]
		vb.customize ["modifyvm", :id, "--natdnsproxy1", "on"]
	end

	# Test "build" tag
	config.vm.provision "ansible" do |ansible|
		ansible.playbook = "test.yml"
		ansible.tags = [ "build" ]
		ansible.verbose = "#{ENV['VERBOSE']}" if ENV['VERBOSE']
	end

	# Test "configure" tag
	config.vm.provision "ansible" do |ansible|
		ansible.playbook = "test.yml"
		ansible.tags = [ "configure" ]
		ansible.verbose = "#{ENV['VERBOSE']}" if ENV['VERBOSE']
	end
end
