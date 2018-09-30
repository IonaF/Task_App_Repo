# Task_App_Repo

All you need to do is to clone this repository,

```
git clone https://github.com/IonaF/Task_App_Repo
cd my-task-list-app
```

Ensure the JAVA_HOME and M2_HOME paths are set

Then run the following command to generate the executable jar:

```
mvn clean package install
```

This jar gets generated in the target folder withing the current directory.

You can then execute the project as follows:

```
java -jar target/<name_of_the_executable_jar>
```

Open the browser and type the following: 
```
http://localhost:8080/#/app/
```
