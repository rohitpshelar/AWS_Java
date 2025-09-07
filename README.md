# AWS_Java
AWS, Java,21, Spring Boot 3

1. ✅ How to Create an AWS Free Tier Account, Setup Budget Alerts & Avoid Unexpected Charges
    1. Step-by-step AWS Free Tier account creation
       1. https://aws.amazon.com/free/
       2. 
    2. Set up free billing alerts to avoid surprise charges
    3. Common mistakes beginners make (and how to avoid them)
<img width="870" height="512" alt="image" src="https://github.com/user-attachments/assets/1a67b769-316c-41c5-9807-bbd3223d7648" />
   4. IAM :  For User Management
   5. Create Spring Boot and to connect with S3 Client we need AWS SDK
<img width="669" height="273" alt="image" src="https://github.com/user-attachments/assets/0c536b55-23d2-42e4-9a2b-cce9a4cd31f9" />
   6. Add dependency `implementation("software.amazon.awssdk:s3:2.33.2")`
   7. Create client : [S3Client.java](src/main/java/com/example/aws_java/config/S3Client.java) and update [application.properties](src/main/resources/application.properties)
   8. Perform Operation via [S3Controller.java](src/main/java/com/example/aws_java/Controller/S3Controller.java) and [S3Service.java](src/main/java/com/example/aws_java/service/S3Service.java)
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
   3. scp -i "aws-java-ec2-key-pair.pem" aws-java-0.0.1-SNAPSHOT-plain.jar ec2-user@ec2-52-66-68-74.ap-south-1.compute.amazonaws.com:/home/ec2-user
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