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
