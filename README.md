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

Copy/Upload the fatjar to the destination
```
TARGET_LOCATION=<location>
cp target/scala-2.12/spark-sbt-template-assembly-1.0.jar $TARGET_LOCATION
```

## Run

To run your project locally:
```
JAR_PATH=$(pwd)/target/scala-2.12/spark-sbt-template-assembly-1.0.jar
spark-submit --master=local[*] --deploy-mode client --class LogAnalyzer $JAR_PATH
```
![sbt_command4](https://user-images.githubusercontent.com/48090690/120479064-6bc49e80-c3a5-11eb-938b-5c7603bb0d95.jpg)


## AWS

![aws_part1](https://user-images.githubusercontent.com/48090690/120251599-f4462080-c279-11eb-9c5c-64091079fe77.jpg)

![aws_part2](https://user-images.githubusercontent.com/48090690/120251622-045e0000-c27a-11eb-9c8b-ef35734336a3.jpg)

Culled from https://docs.aws.amazon.com/emr/latest/ReleaseGuide/emr-spark-submit-step.html
