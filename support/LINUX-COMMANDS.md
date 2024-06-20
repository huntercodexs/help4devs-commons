
# LINUX COMMANDS

###### USER CREATE

<pre>
sudo /usr/sbin/adduser --user-group --no-create-home --shell=/sbin/nologin --comment='{USERNAME} account' {USERNAME}
</pre>

###### USER DELETE

<pre>
sudo /usr/sbin/userdel {USERNAME}
</pre>

###### CHECK USER

<pre>
cat /etc/passwd
</pre>

# CHECK GROUPS

<pre>
cat /etc/group
</pre>

###### SSH CONNECT USING PEM

<pre>
sudo ssh -i /home/ec2-user/key/KEY-NAME-AWS.pem ec2-user@10.0.7.160
sudo ssh -i /home/ec2-user/key/KEY-NAME-AWS.pem ubuntu@10.0.X.X
</pre>

###### CHECK AVAILABLE PORTS

<pre>
sudo netstat
sudo netstat -ano
sudo netstat -tunl
sudo netstat -tulpn
sudo netstat -ano | grep 443
sudo netstat -ano | egrep '443|I-Node'
sudo netstat -na | grep tcp | grep -i listen
</pre>
