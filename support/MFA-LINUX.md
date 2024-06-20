
# MFA + LINUX + EC2

### Requisites

- SETUP CORRECTLY THE SSHD SERVICE
- GOOGLE AUTHENTICATOR APP IS REQUIRED
- UBUNTU 20.04
- FEDORA 40
- REDHAT 9

> Note: It can be applied in the EC2 instances

### UBUNTU 2004 INSTALLATION

###### Step 1: Install the Google Authenticator Library (PAM: Plugable Authentication Modules)

<pre>
sudo apt install libpam-google-authenticator
</pre>

###### Step 2: Configure Google Authenticator for the current user

<pre>
google-authenticator -t -Q UTF8 -s /home/${USER}/.ssh/.google_authenticator
</pre>

- Answer the questions: y for all questions
- Scan the QR Code by your phone device

###### Step 3: Configure SSH with MFA

<pre>
sudo vi /etc/pam.d/sshd
</pre>

- Add the entry line at the end of the file like this

<pre>
auth required pam_google_authenticator.so secret=/home/${USER}/.ssh/.google_authenticator nullok
</pre>

<pre>
sudo vi /etc/ssh/sshd_config
</pre>

- Set the field ChallengeResponseAuthentication to yes value

<pre>
ChallengeResponseAuthentication yes
</pre>

- Restart SSH service

<pre>
sudo service sshd restart
</pre>

###### Step 4: Configure Console with MFA

<pre>
sudo vi /etc/pam.d/common-session
</pre>

- Add the entry line at the end of the file like this

<pre>
auth required pam_google_authenticator.so
</pre>

- Make logout session and login new session

###### Step 5: Configure SUDO with MFA

<pre>
sudo vi /etc/pam.d/common-auth
</pre>

- Add the line at the end of file like this

<pre>
auth required pam_google_authenticator.so nullok
</pre>

- Make logout session and login new session, try run the command sudo -i (you should be prompted by the verification code)

----

### FEDORA 40 INSTALLATION

###### Step 1: Install libraries

<pre>
sudo dnf install google-authenticator qrencode-libs
</pre>

###### Step 2: Edit the SSH files configuration

<pre>
sudo vi /etc/pam.d/sshd
</pre>

<pre>
#%PAM-1.0
#auth       substack     password-auth << COMMENT THIS LINE
auth       include      postlogin
account    required     pam_sepermit.so
account    required     pam_nologin.so
account    include      password-auth
password   include      password-auth
# pam_selinux.so close should be the first session rule
session    required     pam_selinux.so close
session    required     pam_loginuid.so
# pam_selinux.so open should only be followed by sessions to be executed in the user context
session    required     pam_selinux.so open env_params
session    required     pam_namespace.so
session    optional     pam_keyinit.so force revoke
session    optional     pam_motd.so
session    include      password-auth
session    include      postlogin
auth required pam_google_authenticator.so secret=/home/${USER}/.ssh/.google_authenticator nullok << ADD THIS LINE
</pre>

<pre>
sudo vi /etc/ssh/sshd_config
</pre>

<pre>
# Change to no to disable s/key passwords
#KbdInteractiveAuthentication yes
KbdInteractiveAuthentication yes
ChallengeResponseAuthentication yes
UsePAM yes
</pre>

###### Step 3: Restart SSH service

<pre>
sudo systemctl restart sshd
sudo systemctl status sshd
</pre>

###### Step 4: Make sure thar the directory .ssh exists

<pre>
mkdir -p /home/${USER}/.ssh/
chmod 755 /home/${USER}/.ssh/
</pre>

###### Step 5: Generate the QR Google Authentication Code

<pre>
google-authenticator -t -Q UTF8 -s /home/${USER}/.ssh/.google_authenticator
</pre>

###### Step 6: Read the QR Code in the Google Authenticator App

- Insert code in the app google authenticator

----

## REDHAT 9 INSTALLATION

###### Step 1: Install EPEL Repo

<pre>
sudo yum install -y https://dl.fedoraproject.org/pub/epel/epel-release-latest-9.noarch.rpm
</pre>

###### Step 2: Install Google Authenticator

<pre>
sudo yum install google-authenticator.x86_64 -y
sudo yum install qrencode-libs
</pre>

The qrencode-libs will provided the libraries to qr code generation, to be used in the time when the command 
google-authentication will be executed

###### Step 3: Configure SSH to use Google Authentication module

<pre>
sudo vi /etc/pam.d/sshd
</pre>

- Add this entry line below

<pre>
auth required pam_google_authenticator.so secret=/home/${USER}/.ssh/.google_authenticator nullok
</pre>

- Comment out the password requirement as we want to use only the key-based authentication (in the very first line).

<pre>
#auth       substack     password-auth
</pre>

###### Step 4: Update the sshd configuration

- Edit the file as root:

<pre>
sudo vi /etc/ssh/sshd_config
</pre>

<pre>
#ChallengeResponseAuthentication no
ChallengeResponseAuthentication yes
AuthenticationMethods publickey,keyboard-interactive
</pre>pre>

###### Step 5: Install Google Authenticator APP in your phone device (IOS or Android)

###### Step 6: Configure Google Authenticator in the Linux

> Note:
> This step has to be executed as the user to whom you want to set the MFA or Multi Factor Authentication. ec2-user or 
> root or ubuntu  or even as your personal user id which you have created.
<pre>
mkdir -p /home/${USER}/.ssh/
chmod 755 /home/${USER}/.ssh/
google-authenticator -t -Q UTF8 -s /home/${USER}/.ssh/.google_authenticator
</pre>

- After the first question, it would show you the QR code and the Secret Key

###### Step 7: Scan the Shown QR code in your Google Authenticator App

Now take your mobile and go to Google Authenticator App and click on the plus sign in the bottom right corner

###### Step 8: Restart SSH services

<pre>
sudo service sshd restart
</pre>

###### Step 9: Test the configuration

- Access the Linux terminal via SSH

ssh -i ~/.ssh/keyname.pem ec2-user@3.100.23.59
Verification code:
