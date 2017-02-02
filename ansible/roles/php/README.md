PHP
=========

Installs PHP.

Requirements
------------

None.

Role Variables
--------------

```yml
php_install:          '1'
php_ppa:              php
php_version:          7.0
php_packages:         ["php{{ php_version }}-fpm", "php{{ php_version }}-cli", "php{{ php_version }}-intl", "php{{ php_version }}-mcrypt", "php{{ php_version }}-mysql", "php{{ php_version }}-gd", php-apc]
php_server_timezone:  UTC
```

Dependencies
------------

```
bearandgiraffe.base
```

Example Playbook
----------------

```yml
- hosts: servers
  roles:
    - { role: bearandgiraffe.php, php_version: '7.0' }
```

License
-------

The Unlicense (see LICENSE)

Author Information
------------------

Youssef Chaker (@ychaker) from Bear & Giraffe LLC (@bearandgiraffe).
