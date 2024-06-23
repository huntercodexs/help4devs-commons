
# AWS HELPER

### EC2

### SQS

### RDS

### LAMBDA FUNCTIONS

### ROUTE 53

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

### ACL

### S3

### SG

### SSH

- How to access

<pre>
ssh -i "${KEYNAME}.pem" ${INSTANCE_URL}
</pre>

## COMPLETE AWS ENVIRONMENT

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
              - DATABASE


