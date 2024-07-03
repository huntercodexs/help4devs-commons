
# AWS HELPER

### IAM

--------------------------------------------------------------------------------------------------------------------

###### Create a user step by step

- Login in the AWS Console with the administrator account
  - https://aws.amazon.com/
- Go to IAM control panel: 
  - https://us-east-1.console.aws.amazon.com/iam/home?region=us-east-2
- Click: in the Item Menu on the left side named Users
  - Users
- Specify user details
  - User name
  - Custom password
- Click: Next
  - Mark Attach policies directly
    - Choose the related permissions
      - AdministrativeAccess (for example)
- Click: Next
- Click: Create user
- Click: Return to users list

Now you will probably visualize the Users list with the last one that was created in this step by step.
So make a logout from the current account and try login using the user that was created in the steps ago. 

### BILLING

--------------------------------------------------------------------------------------------------------------------

###### Manage Billing Alarm

This setup is important to have a better control in the account costs and avoid surprises in the bills. For that click 
on the link https://us-east-1.console.aws.amazon.com/billing/home#/preferences and check the following settings.

- Click in the [Edit] button - Alert preferences
- Mark the checkbox
  - Receive AWS Free Tier alerts
  - Receive CloudWatch billing alerts
- Click: Update

Now go to cloud watching and make the configurations as showed below

> Cloud Watch: https://console.aws.amazon.com/cloudwatch/home

- Click on the link Create alarms
- Click: Create alarm (button)
- Select Region: US East (N. Virginia)us-east-1
- Select metric
- Click on Billing link
- Click on Total Estimated Charge
- Mark the checkbox (Currency: USD)
- Click on Select metric (in the bottom right screen)
- Fill the form 
- Choose Next button
- Select Notification Box
  - Fill the form to create a new Topic and inform the email to receive the alarm
- Click Next
- Give Alarm name
- Click Next
- Review the Configurations
- Finally, click Create alarm button

> IMPORTANT: Check your email address to Confirm "Simple Notification Service" and active this resource

### CLOUD-SHELL

--------------------------------------------------------------------------------------------------------------------

> ABOUT: Access the terminal commands in the AWS Cloud 

### AWS-CLI

--------------------------------------------------------------------------------------------------------------------

> CLI: Command Line Interface

> To install and configure the amazon aws-cli follow the instructions in the follow page from AWS DOCS
https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html

[Linux Installing]

<pre>
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install
</pre>

[Linux Updating]

<pre>
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install --bin-dir /usr/local/bin --install-dir /usr/local/aws-cli --update
</pre>

[Check Aws Version]

<pre>
aws --version
</pre>

[AWS CLI Configuration]

First of all you need to create the AccessKey to configure the aws-cli, then follow the steps below

- Click the Menu in the top right side in the screen
- Choose Security credentials
- Go down Access eys
  - Click Create access key button link
  - Choose the Command Line Interface (CLI)
    - Mark the checkbox
  - Click Next
  - Fill the questions in the form ahead
    - Save the generated Access Key and also the Secret access key
  - Click Done

- Execute the aws configuration steps

<pre>
aws configure
AWS Access Key ID [None]: AKIA5FTZ........
AWS Secret Access Key [None]: /N+htYqjH9cf68+1...................
Default region name [None]: us-east-1
Default output format [None]: json
</pre>

Now it's possible to execute all commands available for AWS Services, for example:

<pre>
aws s3 ls
aws ec2 describe-instances
</pre>

To get more details see the https://docs.aws.amazon.com/cli/latest/userguide/cli-chap-welcome.html

### EC2

--------------------------------------------------------------------------------------------------------------------

###### Create

To create an EC2 instance go to EC2 Dashboard and click in the item "Instance" placed at the Menu on the left side
screen, so you will fell down in EC2 Instances manager. Now you can follow the steps below to create a new instance.

- Click the "Launch instances" button in the top right screen
- Fill the form with the settings like below

[Name and tags]

- Name: your-instance-ec2-name (temporary-test-ec2)

[Application and OS Images (Amazon Machine Image)]

- Select the instance image: Amazon Linux, macOS, Ubuntu, Windows...
- Choose the AMI: Amazon Linux 2023
- Choose the architecture: 64-bit (x86)

[Instance type]

- Choose instance type: t2.micro (for Free tier eligible)

[Key pair]

- Create a key pair to SSH connections (don't forget to download and save it)
  - Click Create key pair (temporary-test-ec2)

[Network settings]

- Check VPC
- Check Subnet
- Check Public IP
- Choose the Firewall (security groups)
  - Create security group
    - Allow SSH traffic from [Anywhere: 0.0.0.0/0] (just for tests)
  - Select existing security group
    - default or any other one available 

[Configure storage]

- Add volume if necessary

[Advanced details]

- Check the advanced details if necessary

Finally, click the button "Launch instance" placed in the bottom right from the screen.
Go back to the EC2 Instance list dashboard and check the instance status that was created in the steps ago.

So now get the Public IP from the instance created and connect via SSH, for example

<pre>
ssh -i "temporary-test-ec2.pem" ec2-user@ec2-54-152-4-44.compute-1.amazonaws.com
</pre>

> NOTE: Don't forget to give correct permissions for the pem file 400

In case the server is disconnecting in a short time you can change the file /etc/ssh/sshd_config to fix it

<pre>
ClientAliveInterval	300
ClientAliveCountMax	3
</pre>

###### Destroy Instance EC2

It's pretty important to terminate the instance to avoid charges and bills surprised, 
so for that follow the steps below

- Go to your EC2 instance
- Choose 'Instance State'
- Select 'Terminate Instance'
- Check the Instance state: Terminated

### AMI - AMAZON MACHINE IMAGE

--------------------------------------------------------------------------------------------------------------------

To create an Amazon AMI and use in any EC2 instance follow the steps below:

- Get Access to the target AWS account
- Go to EC2 Instances Dashboard
- Choose the EC2 to create an AMI
- Click Actions in the top right screen
- Choose Images and Models
  - Create Image or Image Create
- Review the AMI form to make proper configuration
- Choose Create Image

Now you can use this AMI to create others EC2 instances with the same configurations, installations, applications and 
features, it can save a lot of time when you need to put the new services or machines online.

- Go to AMI management
- Choose the AMI
- Click right button in the AMI
- Choose Execute Instance in the AMI
- Set up the Instance Details like
  - [Name and tags]
  - [Ami]
  - [Instance type]
  - [Key pairs]
  - [Network settings]
    - VPC
    - Allow SSH traffic from: {Anywhere, My IP}
    - Configure the use of SUB-NET
    - Common security groups
  - [Storage management]
  - [Advanced Details]
    - Termination Protection ?
    - Shutdown behavior: {Stop, Terminate}
- Click on the button Execute Instance
- Check if the instance was generated in the EC2 Instances Dashboard

--------------------------------------------------------------------------------------------------------------------

### S3

--------------------------------------------------------------------------------------------------------------------

### ECS

--------------------------------------------------------------------------------------------------------------------

### EKS

--------------------------------------------------------------------------------------------------------------------

### SQS

--------------------------------------------------------------------------------------------------------------------

### RDS

--------------------------------------------------------------------------------------------------------------------

### DYNAMODB

--------------------------------------------------------------------------------------------------------------------

### API-GATEWAY

--------------------------------------------------------------------------------------------------------------------

### LAMBDA FUNCTIONS

--------------------------------------------------------------------------------------------------------------------

### ROUTE 53

--------------------------------------------------------------------------------------------------------------------

To get start the Route 53 configuration have in hand the following information

<pre>
    #DNS na AWS
    ns-1234.awsdns-00.org
    ns-1234.awsdns-00.co.uk
    ns-1234.awsdns-00.com
    ns-1234.awsdns-00.net

    #DNS in the domain server
    ns1.dns-parking.com
</pre>

- In the AWS manager console seek by Route 53 and click in it.

- If you have a hosted zone on Route 53, click on the "Hosted zone" link to access the options for
  zone management, and on this screen it is possible to create a new zone or edit an existing one. You too
  You can use the menu on the left side of the "Hosted Zones" screen to access this screen.

- Both in the previous case and in the case of being the first zone to be created, click on the "Create hosted zone" button.

- On the zone creation screen, enter the domain name, comment and type (public).

- When clicking on Create you will be directed to the created zone/domain screen, where you can carry out the
  settings necessary to activate routing for it.

- Click on Create record to be directed to the configuration screen for the record/domain to be routed.

- Fill in the registration name, choose the type of registration, in value enter the IP (or Elastic IP) of the machine
  which is in the cloud on AWS, TTL and Routing Policy, for example:

<pre>
    Registration name: your-domain.com or your-domain.com
    Record Type: A – Routes traffic to an IPv4 address and some AWS resources
    Value: 54.51.92.190
    TTL: 300 (seconds)
    Routing policy: Simple routing
</pre>

<pre>
    Registration name: your-domino.com or www.your-domain.com
    Record type: CNAME – routes traffic to another domain name and to some AWS resources
    Value: your-domain.com
    TTL: 300 (seconds)
    Routing policy: Simple routing
</pre>

- Obtain DNS data from AWS to insert into your hosting or registry server, for example:
<pre>
    ns-1234.awsdns-00.org
    ns-1234.awsdns-00.co.uk
    ns-1234.awsdns-00.com
    ns-1234.awsdns-00.net
</pre>

<p>
- Go to your hosting or registration service, DNS management panel and enter one by one the DNS entered
 by AWS.
</p>

<p>
    <strong>
        NOTE: You may need to wait a few moments for the redirection and feature activation to take effect.
    </strong>
</p>

### VPC

--------------------------------------------------------------------------------------------------------------------

### ACL

--------------------------------------------------------------------------------------------------------------------

### SECURITY GROUP

--------------------------------------------------------------------------------------------------------------------

### COSTS ESTIMATES

--------------------------------------------------------------------------------------------------------------------

### AWS NETWORKING

--------------------------------------------------------------------------------------------------------------------

In this topic we can see one small concept project to view how to work an environment based on AWS Cloud services.
Belo is the overview image to presentation this subject in a quickly and understandably way.

![know-how-AWS NETWORK.png](midias/know-how-AWS-NETWORK.png)

As we can see in the above image, there is a lot of resources used from AWS Cloud services that those are describe as
follows below:

- AWS CLOUD
- REGION
- VPC
- INTERNET GATEWAY
  - ROUTER
    - AVAILABILITY ZONE
    - PUBLIC ROUTER TABLE
      - ACL
        - PUBLIC SUBNET
          - SECURITY GROUP 1
            - MFA
              - EC2 - MAINTENANCE
          - SECURITY GROUP 2
            - EC2 - NGINX REVERSE PROXY
      - NAT INTERNET
    - PRIVATE ROUTER TABLE
      - ACL
        - PRIVATE SUBNET
          - SECURITY GROUP 3
            - EC2 - MICROSERVICE
              - QUANTITY = 4
                - SERVICE1 = hello-world-1.jar
                - SERVICE2 = hello-world-2.jar
                - SERVICE3 = hello-world-3.jar
                - SERVICE4 = hello-world-4.jar
            - DATABASE
              - RDS: MYSQL