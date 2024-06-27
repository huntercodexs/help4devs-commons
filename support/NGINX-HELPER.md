
# NGINX HELPER

> The version used in this document for Nginx is xxx

### Ubuntu 20.04

###### Installing

- System Update

<pre>
sudo apt update
sudo apt-get update
</pre>

- Install Nginx

<pre>
sudo apt install nginx
</pre>

- Allow Nginx on firewall

<pre>
sudo ufw allow 'Nginx Full'
sudo ufw status
</pre>

- Manage the NGINX service

<pre>
sudo systemctl daemon-reload
sudo systemctl enable nginx
sudo systemctl stop nginx
sudo systemctl start nginx
sudo systemctl status nginx
</pre>

- Check the NGINX page

<pre>
http://192.168.0.26:80
</pre>

- Edit NGINX configurations

<pre>
/etc/nginx/nginx.conf
/etc/nginx/sites-enable
/etc/nginx/sites-available
</pre>

- Sample site configuration

<pre>
server {
    listen 80;
    listen [::]:80;
    
    root /var/www/huntercodexs/html;
    index index.html index.htm index.nginx-debian.html;
    
    server_name huntercodexs www.huntercodexs;
    
    location / {
            try_files $uri $uri/ =404;
    }
}
</pre>

###### Uninstall

<pre>
sudo ufw delete allow 80/tcp
sudo ufw delete allow 443/tcp
sudo ufw reload

sudo systemctl stop nginx.service
sudo apt remove nginx
sudo apt-get remove nginx
sudo apt-get remove --auto-remove -y
cd /
sudo find . | grep nginx | xargs -i sudo rm -rf {}
</pre>

> TIP: Maybe it will be necessary to restart the machine

### Almalinux 20.04

###### Installing

- System Update

<pre>
sudo yum update
</pre>

- Install NGINX

<pre>
sudo yum install nginx
</pre>

- Allow Nginx on firewall

<pre>
sudo firewall-cmd --add-port=80/tcp --permanent
sudo firewall-cmd --add-port=443/tcp --permanent
sudo firewall-cmd --reload
</pre>

- Manage the NGINX service

<pre>
sudo systemctl daemon-reload
sudo systemctl enable nginx
sudo systemctl stop nginx
sudo systemctl start nginx
sudo systemctl status nginx
</pre>

- Check the NGINX page

<pre>
http://192.168.0.241:80
</pre>

- Edit NGINX configurations

<pre>
/etc/nginx/nginx.conf
/etc/nginx/sites-enable
/etc/nginx/sites-available
</pre>

- Sample site configuration

<pre>
server {
    listen 80;
    listen [::]:80;
    
    root /var/www/huntercodexs/html;
    index index.html index.htm index.nginx-debian.html;
    
    server_name huntercodexs www.huntercodexs;
    
    location / {
            try_files $uri $uri/ =404;
    }
}
</pre>

###### Uninstall

<pre>
sudo firewall-cmd --zone=public --remove-port=80/tcp
sudo firewall-cmd --zone=public --remove-port=443/tcp
sudo firewall-cmd --runtime-to-permanent 
sudo firewall-cmd --reload

sudo systemctl stop nginx.service
sudo yum remove nginx
sudo yum autoremove -y
cd /
sudo find . | grep nginx | xargs -i sudo rm -rf {}
</pre>

> TIP: Maybe it will be necessary to restart the machine

