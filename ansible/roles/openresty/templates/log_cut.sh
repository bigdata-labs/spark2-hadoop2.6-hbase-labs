/bin/mv {{nginx_log_path}}/access.log {{nginx_log_cut_path}}/access_`date +'%Y_%m_%d_%H_%M_%S_%s'`.log
/etc/init.d/nginx reload
