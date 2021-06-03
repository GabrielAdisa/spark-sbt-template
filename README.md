# Spark sbt template

This sbt template enables you to create a new spark project 

## Package

clean   -   Deletes all generated files (in the target directory).
```bash
sbt clean
```

To package your project:
```bash
sbt assembly
```
![sbt_command3](https://user-images.githubusercontent.com/48090690/120478351-ab3ebb00-c3a4-11eb-9700-09f235f3ffbf.jpg)

## Deploy 

Copy/Upload the jar to the destination
```
TARGET_LOCATION=<location>
cp target/scala-2.12/spark-sbt-template-assembly-1.0.jar $TARGET_LOCATION
```

## Run locally

To run your project locally:
```
JAR_PATH=$(pwd)/target/scala-2.12/spark-sbt-template-assembly-1.0.jar
spark-submit --master=local[*] --deploy-mode client --class LogAnalyzer $JAR_PATH
```
![sbt_command4](https://user-images.githubusercontent.com/48090690/120479064-6bc49e80-c3a5-11eb-938b-5c7603bb0d95.jpg)


## Run on AWS

To run the spark application on AWS, one needs to create an account on AWS and log on.
The steps below should be followed:

1. Create an EMR Cluster . Be sure that under "Software configuration", Spark is selected.
   You would also need to create an EC2 key pair that would be required for logging onto the created cluster through SSH.

![emr_cluster0](https://user-images.githubusercontent.com/48090690/120655377-89b00300-c47a-11eb-825e-ac98659f1db2.jpg)

![emr_cluster](https://user-images.githubusercontent.com/48090690/120656008-21adec80-c47b-11eb-8fd6-4fcf70a5f000.jpg)

![emr_cluster2](https://user-images.githubusercontent.com/48090690/120656046-2bcfeb00-c47b-11eb-92d4-83988fe6f84f.jpg)

2. Create a S3 Bucket.

![s3_folder0](https://user-images.githubusercontent.com/48090690/120656425-836e5680-c47b-11eb-8d4c-3a9d367dd6b0.jpg)

3. Upload both the "access.log.gz" file and the spark jar file to this created bucket

![s3_folder1](https://user-images.githubusercontent.com/48090690/120656813-d942fe80-c47b-11eb-91dd-c710be7c8179.jpg)

4. Ensure to create a folder in the bucket to house the output of the spark computation ("myjsonreport/" folder in the figure above)

5. Connect to the Master Node of the Cluster Using SSH. (How to do so is depicted in figure 3 above). Once connected, copy the jar file from the S3 bucket to the Cluster using the command below.

```
aws s3 cp s3://accesslogdsti/spark-sbt-template-assembly-1.0.jar .
```

![spark_begin](https://user-images.githubusercontent.com/48090690/120657916-de547d80-c47c-11eb-9c7f-5bd54443c651.jpg)

6. Run the spark application on AWS using the command below:

```
spark-submit --master yarn --deploy-mode cluster --class LogAnalyzer spark-sbt-template-assembly-1.0.jar
```
![spark_middle](https://user-images.githubusercontent.com/48090690/120658125-1065df80-c47d-11eb-8cbd-db642ae92c6f.jpg)

Once the application is successfully run, you would have the image displayed below.

![spark_end](https://user-images.githubusercontent.com/48090690/120658303-35f2e900-c47d-11eb-8a4a-2b19c81aee3d.jpg)

The result of the computation are located in the output folder created in the S3 Bucket created above.


## Note:

Modifications were made the jar file earlier used for running locally for it work on AWS.

a. The path and outputPath in the code were changed to reflect the S3 buckets.

![s3_code_mod](https://user-images.githubusercontent.com/48090690/120659879-b0703880-c47e-11eb-9bb7-a2b8cec71de5.jpg)

b. The versions of both Scala and Spark in the "build.sbt" file were modified as an error was generated using the provided versions. The versions were downgraded to conform with the versions available on the EMR Cluster.

![version_error](https://user-images.githubusercontent.com/48090690/120660085-e0b7d700-c47e-11eb-95f6-5049263cc9b3.jpg)

Culled from https://alvinalexander.com/misc/fixing-scala-error-java-lang.nosuchmethoderror-scala.product-init/


![versions_mod](https://user-images.githubusercontent.com/48090690/120659948-c251db80-c47e-11eb-8d0e-6728e02f5e5c.jpg)



