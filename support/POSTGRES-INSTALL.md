
# POSTGRES INSTALL

> The version used in this document for PostgresSQL is 16.3

### Ubuntu 20.04

###### Postgres Installing

- System Update

<pre>
sudo apt-get update
</pre>

- Prepare environment

<pre>
sudo apt install wget ca-certificates

wget --quiet -O - https://www.postgresql.org/media/keys/ACCC4CF8.asc | sudo apt-key add -

sudo sh -c 'echo "deb http://apt.postgresql.org/pub/repos/apt/ $(lsb_release -cs)-pgdg main" >> /etc/apt/sources.list.d/pgdg.list'

sudo apt update
sudo apt-get update
</pre>

- Install PostgresSQL

<pre>
sudo apt install postgresql postgresql-contrib
</pre>

- Allow firewall postgresql port
<pre>
sudo ufw allow 5432/tcp
sudo ufw reload
</pre>

- Enable PostgresSQL service

<pre>
sudo systemctl stop postgresql
sudo systemctl daemon-reload
sudo systemctl enable postgresql
</pre>

- Manage PostgresSQL service

<pre>
sudo systemctl start postgresql
sudo systemctl status postgresql
</pre>

- Access PostgresSQL

<pre>
sudo -u postgres psql
</pre>

- PostgresSQL Version

<pre>
sudo -u postgres psql
psql (16.3 (Ubuntu 16.3-1.pgdg20.04+1))
Type "help" for help.

postgres=# SELECT version();
                                                              version                                                              
-----------------------------------------------------------------------------------------------------------------------------------
 PostgreSQL 16.3 (Ubuntu 16.3-1.pgdg20.04+1) on x86_64-pc-linux-gnu, compiled by gcc (Ubuntu 9.4.0-1ubuntu1~20.04.2) 9.4.0, 64-bit
(1 row)

postgres=#
</pre>

- Manage PostgresSQL Database

<pre>
#LIST ALL USERS
\du

#SET PASSWORD
\password postgres

#QUIT
\q
</pre>

- Configure PostgresSQL Server

<pre>
sudo vi /etc/postgresql/${POSTGRES-VERSION}/main/postgresql.conf
</pre>

<pre>
listen_addresses = '*'
</pre>

<pre>
sudo vi /etc/postgresql/${POSTGRES-VERSION}/main/pg_hba.conf
</pre>

<pre>
host all all 0.0.0.0/0 md5
</pre>

<pre>
sudo systemctl restart postgresql
</pre>

- Check port listening 5432

<pre>
sudo ss -nlt | grep 5432
</pre>

- Connect via CLI remotely

<pre>
psql -h 5.199.162.56 -p 5432 -d test_erp -U postgres
</pre>

- Connect to postgres database with DBeaver

<pre>
Host: 192.168.26
Port: 5432
Database: postgres
Authentication: Database Native
Username: postgres
Password: postgres
</pre>

> See more details in the link https://github.com/huntercodexs/docker-series/tree/databases?tab=readme-ov-file#postgres

###### Uninstalling from Ubuntu

> TIP: You can create a script to automate this process, for example destroy.sh

<pre>
#!/bin/bash

echo "This process can't to be undo, Continue ?"
echo "Press [Enter] to continue, Press [Ctrl+C] to Abort "
read OP

echo "Locking firewall - port 5432"
sudo ufw delete allow 5432/tcp
sudo ufw reload

sleep 2

echo "Stopping and removing service - postgresql.service"
sudo systemctl stop postgresql.service
sudo service postgresql stop
sudo rm -f /etc/systemd/system/postgresql.service
sudo rm -f /etc/systemd/system/multi-user.target.wants/postgresql.service
sudo systemctl daemon-reload

sleep 2

echo "Removing postgresql, folders and links"
sudo apt remove postgresql* postgresql-contrib* -y
sudo apt remove wget ca-certificates -y
sudo apt-get remove postgresql* -y
sudo apt-get remove wget ca-certificates -y
sudo apt-get remove --auto-remove -y
sudo dpkg --purge postgresql-16
sudo dpkg --purge postgresql-client-common 
sudo dpkg --purge postgresql-common 

cd /
sudo rm -rf /etc/apt/sources.list.d/pgdg.list
sudo find . | grep -v home | grep postgres | xargs -i sudo rm -rf {} >> /dev/null 2>&1

sleep 2

echo "Removing user - postgresql"
sudo userdel postgresql
sudo userdel postgres
sudo userdel postgre
cd /home/ubuntu-vbox

sleep 2

echo "DONE"
exit
</pre>

### Almalinux 8.10

###### Postgres Installing

- System Update

<pre>
sudo yum update -y
sudo dnf update -y
</pre>

- Prepare environment

<pre>
sudo vi /etc/yum.repos.d postgresql-org-7.0.repo
</pre>

<pre>
 postgresql-org-7.0]
name=PostgresSQL Repository
baseurl=https://repo postgresql.org/yum/redhat/8 postgresql-org/7.0/x86_64/
gpgcheck=1
enabled=1
gpgkey=https://pgp postgresql.com/server-7.0.asc
</pre>

<pre>
sudo yum update -y
sudo dnf update -y
</pre>

- Install PostgresSQL

<pre>
sudo yum install postgresql-org
</pre>

- Lock PostgresSQL apt-get update (optional)

<pre>
echo  postgresql-org hold" | sudo dpkg --set-selections
echo  postgresql-org-database hold" | sudo dpkg --set-selections
echo  postgresql-org-server hold" | sudo dpkg --set-selections
echo  postgresql-postgresqlsh hold" | sudo dpkg --set-selections
echo  postgresql-org-postgresqls hold" | sudo dpkg --set-selections
echo  postgresql-org-tools hold" | sudo dpkg --set-selections
</pre>

- Check directories

<pre>
/var/lib postgresql
/var/log postgresql
</pre>

- PostgresSQL configuration file

<pre>
/etc/postgresql.conf
</pre>

- Allow firewall postgresql port

> 27017 The default port for postgresql and postgresqls instances. You can change this port with port or --port.

> 27018 The default port for postgresql when running with --shardsvr command-line option or the shardsvr value for the clusterRole setting in a configuration file.

> 27019 The default port for postgresql when running with --configsvr command-line option or the configsvr value for the clusterRole setting in a configuration file.

> 27020 The default port from which postgresqlcryptd listens for messages. postgresqlcryptd is installed with PostgresSQL Enterprise Server and supports automatic encryption operations.

<pre>
sudo firewall-cmd --add-port=27017/tcp --permanent
sudo firewall-cmd --add-port=27018/tcp --permanent
sudo firewall-cmd --add-port=27019/tcp --permanent
sudo firewall-cmd --add-port=27020/tcp --permanent
sudo firewall-cmd --reload
</pre>

- Enable PostgresSQL service

<pre>
sudo systemctl daemon-reload
sudo systemctl enable postgresql
</pre>

- Manage PostgresSQL service

<pre>
sudo systemctl stop postgresql
sudo systemctl start postgresql
sudo systemctl status postgresql
</pre>

- Check PostgresSQL version

<pre>
postgresql --version
</pre>

<pre>
db version v7.0.11
Build Info: {
    "version": "7.0.11",
    "gitVersion": "f451220f0df2b9dfe073f1521837f8ec5c208a8c",
    "openSSLVersion": "OpenSSL 1.1.1f  31 Mar 2020",
    "modules": [],
    "allocator": "tcmalloc",
    "environment": {
        "distmod": "ubuntu2004",
        "distarch": "x86_64",
        "target_arch": "x86_64"
    }
}

</pre>

- Access PostgresSQL

<pre>
postgresqlsh
</pre>

- Manage PostgresSQL Database

<pre>
#SHOW DATABASES
show dbs

#CREATE DATABASE
use dbtest
db

#CREATE USER
db.createUser(
    {
        user: "test",
        pwd: "123change",
        roles: [{role: "readWrite", db: "dbtest"}]
    }
)

#SELECT USERS
db.getUsers()
show users

#DELETE USER
db.dropUser("test", {w: "majority", wtimeout: 4000})
</pre>

- Secure PostgresSQL

<pre>
postgresqlsh
</pre>

<pre>
use admin
</pre>

<pre>
db.createUser(
  {
    user: "adminTest",
    pwd: passwordPrompt(),
    roles: [ { role: "userAdminAnyDatabase", db: "admin" }, "readWriteAnyDatabase" ]
 }
)
</pre>

<pre>
sudo vi /etc/postgresql.conf
</pre>

<pre>
security:
  authorization: enabled
</pre>

<pre>
sudo systemctl restart postgresql
</pre>

<pre>
postgresqlsh   postgresql://adminTest@postgresql-ip-address:27017"
</pre>

- Remote Access

<pre>
sudo vi /etc/postgresql.conf
</pre>

<pre>
# network interfaces
net:
  port: 27017
  bindIp: 127.0.0.1, postgresql-server-ip
</pre>

Install tools for access remotely

<pre>
sudo apt install netcat
</pre>

<pre>
nc -zv postgresql_server_ip 27017
</pre>

- Database PostgresSQL handler

Data insert

<pre>
db.staff.insertOne({ name: "Alice", age: 25, city: "London", married: true, hobbies: ["Travelling", "Swimming", "Cooking"] })
</pre>

Retrieve data

<pre>
db.staff.find()
db.staff.find({ married: true })
</pre>

Update data

<pre>
db.staff.update({ name: "Bob" }, {$set: { name: "Robert" }})
</pre>

Delete data

<pre>
.deleteOne()
.deleteMany() 
</pre>

<pre>
db.staff.deleteOne({ name: "Robert"})
db.staff.deleteMany({married: true})
</pre>

###### Uninstalling from Ubuntu

> TIP: You can create a script to automate this process, for example destroy.sh

<pre>
#!/bin/bash

echo "This process can't to be undo, Continue ?"
echo "Press [Enter] to continue, Press [Ctrl+C] to Abort "
read OP

echo "Locking firewall - port 27017"
sudo firewall-cmd --zone=public --remove-port=27017/tcp
echo "Locking firewall - port 27018"
sudo firewall-cmd --zone=public --remove-port=27018/tcp
echo "Locking firewall - port 27019"
sudo firewall-cmd --zone=public --remove-port=27019/tcp
echo "Locking firewall - port 27020"
sudo firewall-cmd --zone=public --remove-port=27020/tcp
sudo firewall-cmd --runtime-to-permanent 
sudo firewall-cmd --reload

sleep 2

echo "Stopping and removing service - postgresql.service"
sudo systemctl stop postgresql.service
sudo service postgresql stop
sudo rm -f /etc/systemd/system/postgresql.service
sudo rm -f /etc/systemd/system/multi-user.target.wants/postgresql.service
sudo rm -f /etc/init.d/postgresql
sudo systemctl daemon-reload

sleep 2

echo "Removing postgresql, folders and links"
sudo yum remove postgresql* -y
sudo dnf remove postgresql* -y
sudo yum autoremove -y
cd /
sudo find . | grep -v home | grep postgresql | xargs -i sudo rm -rf {}
cd $HOME
sudo rm -rf $HOME/ postgresql

sleep 2

echo "Removing user - postgresql"
sudo userdel postgresql
cd /home/almalinux-vbox

sleep 2

echo "DONE"
exit
</pre>

