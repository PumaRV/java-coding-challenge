# HeavenHR Java Coding Challenge
This project was created as part of the hiring process for the position of Senior Software Engineer in HeavenHR. The main goal is to be able 
to create job offers and create applications for those same offers, along with other very basic statistics. 

# Dependencies
- Java 8 or superior
- Lombok
- Spring 
- H2 
- The project is built using maven. 

# Running  and Testing the application
We are using Maven Wrapper to avoid maven installation. 

1. Run ```$ ./mvnw clean install```
2. Run ```$ ./mvnw spring-boot:run``` 

This will start a web server and the API will be accesible through Http://localhost:8080.
For testing convenience, a postman collection has been created for testing the endpoints, it can be found in the following link: 
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/d82b4496088c6d6270aa)

Alternatively, you'll be able to access a Swagger page on Http://localhost:8080 to test the endpoints, and of course you can test them 
directly via postman or CURL.

Additionally, the H2 console it's enabled by default and can be accessed on http://localhost:8080/h2-console/ with the following information: 

```Driver Class: org.h2.Driver```
```JDBC URL: jdbc:h2:mem:~/h2/recruitment```
```User Name: sa```
```Password: ```

This values are configurable through the Application.properties file. Be aware, as we are using an in-memory database, all data will be lost
every time the application is restarted. 

#SPECS
The following are the use cases taken into account for the project: 
- User has to be able to create a job offer and read a single and list all offers.
- Candidate has to be able to apply for an offer.
- User has to be able to read one and list all applications per offer.
- User has to be able to progress the status of an application.
- User has to be able to track the number of applications.
- Status change triggers a notification.

#API
In this section you'll be able to find the API specifications and corresponding payloads. 

***
POST /offers

This endpoint is used to create a new job offer. It requires a JSON body: 
```
 {
    "jobTitle": "Senior Software Engineer",
    "startDate": "2019-06-05"
 }
```
Where: 
- jobTitle: Is the job title for the offer. This value must be unique. 
- startDate: The date this offer was published in format YYYY-MM-DD. 

ALL of the fields are required. 

***

GET /offers

This endpoint is used to list all the existing job offers. It returns an array of job offers: 
```
[
    {
        "jobTitle": "Senior Software Engineer",
        "startDate": "2019-06-05",
        "numberOfApplications": 2
    },
    {
        "jobTitle": "QA Engineer",
        "startDate": "2019-06-05",
        "numberOfApplications": 0
    }
]
```
***

POST /offers/{jobTitle}/applications

This endpoint creates an application for the job offer with the job title {jobTitle}. It requires a json body: 
```
{
    "candidateEmail": "otto.rodriguez.v@gmail.com", 
    "resumeText": "This guy is really awesome! You should hire him ASAP", 
    "applicationStatus": "APPLIED"
}
```
Where: 

- candidateEmail: Is the email for the applicant. 
- resumeText: Is the text or the resumé or CV. 
- applicationStatus = Represents the current status of the application. Acceptable values are (APPLIED,INVITED,REJECTED,HIRED)

*** 
GET /offers/{jobTitle}/applications/{email}

This endpoint returns a job application that has the unique combination of {jobTitle} in the offer and {email} in the job application. 
```
{
    "candidateEmail": "jose@gmail.com",
    "relatedOffer": {
        "jobTitle": "Senior Software Engineer",
        "startDate": "2019-06-05",
        "numberOfApplications": 2
    },
    "applicationStatus": "APPLIED",
    "resumeText": "This guy is really awesome! You should hire him"
}
```
***
GET /offers/{jobTitle}/applications

This endpoint returns all the job applications for the offer with the job title {jobTitle}. 
```
[
    {
        "candidateEmail": "otto.rodriguez.v@gmail.com",
        "relatedOffer": {
            "jobTitle": "Senior Software Engineer",
            "startDate": "2019-06-05",
            "numberOfApplications": 2
        },
        "applicationStatus": "APPLIED",
        "resumeText": "This guy is really awesome! You should hire him"
    },
    {
        "candidateEmail": "jorv15spawn@gmail.com",
        "relatedOffer": {
            "jobTitle": "Senior Software Engineer",
            "startDate": "2019-06-05",
            "numberOfApplications": 2
        },
        "applicationStatus": "INVITED",
        "resumeText": "This guy is also awesome! It might be the same guy though"
    }
]
```
***
GET /offers/{jobTitle}/applications/{email}/status

This endpoint returns the status of job application that has the unique combination of {jobTitle} in the offer and {email} in the job application.

```APPLIED``` 

***
GET /offers/applications/count

This endpoint returns the total of job applications that have been created. 

```2```

***

GET /offers/{jobTitle}/applications/count

This endpoint returns the count of job applications for a particular offer

```2```

***

PATCH /offers/{jobTitle}/applications/{email}/?status=INVITED

This endpoint allows the user to progress the status of an application. It requires a query parameter with a valid job application status. 

