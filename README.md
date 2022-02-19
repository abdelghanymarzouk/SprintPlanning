## Sprint Planning Service

#### Goal

The Goal is to implement an application to help the tem to plan thier stories.
#### Description

My used algorithm to plan the stories:
1. I calculate available point per week by this equation -> number of developrs * 10
2. Then I loop in all stories and choose the stories which have estimated points 10 
   or if I found 2 stories the sum of thier estimated points is 10.
3. Then I continue with the remaining stories till I finish all of them.
* #### Hints 
  * I'm not assigning any stories to any developer in the planning to be assigned to them by updateStory endpoint. (It is easy to assign but it is just plan)
  * I didn't make a unit tests for IssueService class because it will be the same as DevelopService. (to be created)
  * I make an assume that each story  estimatation points shouldn't increase than 10 points. ( I make a validation for it when creating or updating a story)

#### Used Technologies

* ##### Back-End
    * **Spring** (boot, JPA).
    * **Swagger UI
    * **H2** in-memory database.

#### Requirements

* [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/index.html) or later
* Docker (Optional)

#### Installing & running

* If you have Docker and Linux, you can run the app by running commands on CMD:
  *  dos2unix runApp.sh
  *  chmod 777 runApp.sh
  *  ./runApp.sh 

* you can run the application by using
  ```./mvnw spring-boot:run ```

* Alternatively, you can build the JAR file then run it as follows:
    * ```./mvnw clean package```
    * ```java -jar target/sprint-planning-0.0.1-SNAPSHOT.jar```


#### Testing

After the application running, follow the below steps:

1. Open http://localhost:8080, then you will be redirecred to Swagger ui Page 
2. You will find 3 Main Endpoints (Issue , Developer , Sprint Planning)
3. In Issue Group you will find all endpoints related to Stories and bug (CRUD operations)
4. In Developer Grouo you will find all endpoints related to Developers (CRUD operations)
5. In Sprint Planning Group you will find one endpoint to get your plan (/plan)


#### Road Map

* Enhancing the current algorithim for  getting the plan.
* Increasing the code coverage by adding more unit and integration tests.
* Applying security for the application.

## Authors

[**Abdelghany Marzouk**](https://www.linkedin.com/in/abdelghanymarzouk/)
