# AWS_Java
REF : https://www.youtube.com/playlist?list=PL-bgVzzRdaPhE4h7u9OWe0cf7P6H-MpeM
AWS, Java,21, Spring Boot 3

1. ✅ How to Create an AWS Free Tier Account, Setup Budget Alerts & Avoid Unexpected Charges
    1. Step-by-step AWS Free Tier account creation
       1. https://aws.amazon.com/free/
       2. 
    2. Set up free billing alerts to avoid surprise charges
    3. Common mistakes beginners make (and how to avoid them)
<img width="870" height="512" alt="image" src="https://github.com/user-attachments/assets/1a67b769-316c-41c5-9807-bbd3223d7648" />
## IAM :  For User Management
   5. Create Spring Boot and to connect with S3 Client we need AWS SDK
<img width="669" height="273" alt="image" src="https://github.com/user-attachments/assets/0c536b55-23d2-42e4-9a2b-cce9a4cd31f9" />
   6. Add dependency `implementation("software.amazon.awssdk:s3:2.33.2")`
   7. Create client : [S3Client.java](src/main/java/com/example/aws_java/config/S3Client.java) and update [application.properties](src/main/resources/application.properties)
   8. Perform Operation via [S3Controller.java](src/main/java/com/example/aws_java/Controller/S3Controller.java) and [S3Service.java](src/main/java/com/example/aws_java/service/S3Service.java)
   9. S3 Access user from IAM
## Create EC2 instance - (server)
   1. put in AWS > EC2 > advance script 
      ```shell
      sudo su root
   
       yum update -y
       yum install -y httpd
       systemctl start httpd
       systemctl enable httpd
    
       echo "<html><head><title>My First AWS Page</title></head><body><h1>Hello from EC2</h1><p>This is a static HTML page hosted on Apache.</p></body></html>" > /var/www/html/index.html
       ```
   2. Path can be find in EC2 > instance > connect > SSH Client
   3. to Copy JAR to EC2 `scp -i "<key.pem>" <SpringBoot.jar> <user>@<host>>:/home/ec2-user`
   3. `scp -i "aws-java-ec2-key-pair.pem" aws-java-0.0.1-SNAPSHOT-plain.jar ec2-user@ec2-52-66-68-74.ap-south-1.compute.amazonaws.com:/home/ec2-user`
   4. Check if java is installed in EC2 console `java -version`, or install with below command :
       ```shell
           sudo su root
    
           sudo yum update -y # To update all packages
           sudo yum install java-24-amazon-corretto -y 
       ```
   5. Run in EC2 console `java -jar aws-java-0.0.1-SNAPSHOT.jar` 
   6. Test on
      * http://ec2-52-66-68-74.ap-south-1.compute.amazonaws.com:8080/
      * http://52.66.68.74:8080/
   7. Run in Background, EC2 console : `nohup java -jar aws-java-0.0.1-SNAPSHOT.jar > output.log 2>&1 &`
   8. To see logs `tail -f output.log `
   9. SET PROFILE 
      1. Run > Add VM options > `-Dspring.profiles.active=local`
      2. In  [application.properties](src/main/resources/application.properties) as `spring.profiles.active=aws`
      3. In EC2 console for temp run `java -Dspring.profiles.active=aws -jar aws-java-0.0.1-SNAPSHOT.jar`
      4. In EC2 console for permanent run `nohup java -Dspring.profiles.active=aws -jar aws-java-0.0.1-SNAPSHOT.jar > output.log 2>&1 &`
      4. Create Role > IAM  > role > to access S3 in EC2 `%s3Full%` > Add role in EC2
      5. Test postman added : [AWS-Java.postman_collection.json](AWS-Java.postman_collection.json)
   10. Part 9 :  AWS RDS Tutorial for Beginners | Launch MySQL DB and Connect via Terminal
       1. Create DB on AWS > RDS
       2. Access from Windows Terminal
          * Format : `mysql -h your-endpoint.rds.amazonaws.com -P 3306 -u your-username -p`
          * URL :  `mysql -h aws-java.c50icia4g0x5.ap-south-1.rds.amazonaws.com -P 3306 -u admin -p`
   11. ✅ Master AWS DynamoDB 🚀 | Serverless, Scalable NoSQL Database Explained 
       1. Create new IAM user > Create > Edit >  Access > Use case : Local
       2. Local to DynamoDB Config added [AwsDbClient.java](src/main/java/com/example/aws_java/config/AwsDbClient.java)
       3. Run Deploy jar to AWS EC2 and access AWS DynamoDB
   12. 🚀 Serverless with AWS Lambda | Explained with Java App ⚡ | Easy Guide
       1. same like N8n or Camunda
       2. You can upload jar 
       3. Process >  Goto > Lambda Create app
       4. If any S3 related operation in Lambda and user role.
       5. Use case 
          1. If image is uploaded in s3, than compress it
          2. If table updated in DynamoDB, than log it
          1. Image / Video Processing
             - Triggered when a user uploads an image to S3.
             - Lambda can resize images, create thumbnails, or add watermarks.
             - 👉 Real-life example: Automatic thumbnail generation like Instagram or Facebook.
          2. Data Processing Pipelines
             - Lambda can process streaming data from: Kinesis, DynamoDB Streams, Kafka
             - 👉 Real-life example: Real-time log analysis, fraud detection in transactions, IoT sensor data processing.
          3. Serverless APIs
             - You can create REST APIs using API Gateway + Lambda without managing servers.
             - 👉 Real-life examples: Contact form submission API, Authentication API, Microservices architecture
          4. Event-driven Automation
             - Automatically responds to AWS events (e.g., S3 uploads, DynamoDB updates, CloudWatch alarms).
             - 👉 Example: Send email notifications via SES when a new order is placed.
          5. Scheduled Jobs (Cron Jobs)
             - Triggered periodically using CloudWatch Events or EventBridge.
             - 👉 Example: Clear cache at midnight, generate daily reports and backups.
          6. Chatbots & Voice Assistants
             - Integrate with Lex / Alexa to process user input.
             - 👉 Real-life example: “Alexa, what’s the weather?” → Lambda fetches data from an API.
          7. Security & Compliance
             - Automatically detect and fix misconfigured resources (e.g., public S3 buckets).
             - 👉 Real-life example: Auto-enforcing security rules.
   13. Part 13 : 🚀 Master AWS CLI | Installation, Configuration & Hands-On Demo ✅
       1. https://aws.amazon.com/cli/ > download CLI
       2. Create new IAM user > Create > Edit >  Access > Use case : CLI
       3. In Windows Terminal : run `aws configure`
       4. Add access key and secret key
       5. set Region : `aws configure set region ap-south-1`
       5. To create new bucket : `aws s3 mb s3://rohitpshelar-v1`
       6. To created `aws s3 ls`
       7. To remove bucket  `aws s3 rb s3://rohitpshelar-v1`
       8. Also Create command script (aws.bat) > `notepad aws.bat` > save as Encoding :ANSI
       ```shell
        set BUCKET_NAME=rohitpshelar-v1

        echo Creating S3 bucket...
        aws s3 mb s3://%BUCKET_NAME%
        
        echo Creating file in Windows...
        echo Hello > hello.txt
        
        echo Uploading file...
        aws s3 cp hello.txt s3://%BUCKET_NAME%
        
        echo Listing files...
        aws s3 ls s3://%BUCKET_NAME%/
        ```
       9. To Force remove bucket  `aws s3 rb s3://rohitpshelar-v1 --force` 
   14. 🚀AWS API Gateway Explained | Create, Manage & Secure APIs Easily✅ | Hands on Demo ⌨️
       1. API like rest which can connect : 
        - Other public API
        - S3
   15. Part 19 : ⚡ Master AWS SQS in Simple Steps | Message Queues Explained with Demo 🚀
       1. Like Kafka
       ```bash
          # Create Queue
          aws sqs create-queue --queue-name SQSrs.fifo --attributes FifoQueue=true
          # return : https://sqs.ap-south-1.amazonaws.com/702352075937/SQSrs.fifo
          set QUEUE_URL= https://sqs.ap-south-1.amazonaws.com/702352075937/SQSrs.fifo
          # Add Data
          aws sqs send-message --queue-url https://sqs.ap-south-1.amazonaws.com/702352075937/SQSrs.fifo --message-body "Order #123 placed" --message-group-id 1 --message-deduplication-id 1
       
          # Get Data
          aws sqs receive-message --queue-url https://sqs.ap-south-1.amazonaws.com/702352075937/SQSrs.fifo
       
          # Delete All msg
          aws sqs purge-queue --queue-url https://sqs.ap-south-1.amazonaws.com/702352075937/SQSrs.fifo
        
          # Delete Queue
          aws sqs delete-queue --queue-url https://sqs.ap-south-1.amazonaws.com/702352075937/SQSrs.fifo
       ```
   

