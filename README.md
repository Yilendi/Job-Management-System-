

# Job Management System

This is a web application built with Spring Boot and Spring Data JPA to manage jobs. 
Only jobs folder contains useful information.

## Setup

To run the application, you need to have the following installed:

- Java 8 or higher
- Maven

1. Clone the repository
2. Run `mvn clean install` to build the project and download dependencies.
3. Run `mvn spring-boot:run` to start the application.

## Data Model

The application uses a Job entity class to represent a job in the database. The entity class has the following attributes:

- id (auto-generated)
- occupation (string)
- occupationLevel (string)
- annualSalary (double)

## API Endpoints

The application implements REST API endpoints for managing jobs. These endpoints are handled by the JobController class. 

The following API endpoints are implemented:

- GET `/jobs`: returns a list of all jobs in the database
- GET `/jobs/{id}`: returns a job with the given id
- POST `/jobs`: creates a new job
- PUT `/jobs/{id}`: updates an existing job with the given id
- DELETE `/jobs/{id}`: deletes a job with the given id

## Service Layer

The application has a JobService class to handle business logic related to jobs. The JobService class defines methods to handle adding and removing jobs from the database. 

## Persistence Layer

The persistence layer is implemented using Spring Data JPA. The JobRepository interface is used to handle database operations related to jobs. 

## Visual Layer

The visual layer is implemented using Thymeleaf templates. The application has the following pages:

- `index.html`: the home page to manage jobs
- `newJob.html`: a page to create new job and add to database

To add a new job, click the "Add Job" button on the home page. This will take you to the new job page where you can fill in the job details and submit the form to create a new job.

## Demo


https://user-images.githubusercontent.com/108242130/235344157-6fdf0469-2ac3-4d90-b2cd-045dd50e46a1.mov




## Conclusion

That's all there is to it! With this application, you can easily manage jobs and keep track of important information like occupation, occupation level, and annual salary.
