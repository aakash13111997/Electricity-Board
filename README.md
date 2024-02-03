# Electricity Consumer Board Project

The Electricity Consumer Board Management Application is built with Angular and Springboot. The primary objective of the project is to design and develop a electrcity board consumer management website which can be accessed by the employee of Electricity Board who can manage users taking Electricity connections.

The employee has the authority to view the current list of users applying for the connections,based on the status i.e. the status is Pending or Rejected. The employee can perform CRUD operations with the connections on the website.He can check the connections applied for a range of DATE and based on Applicant ID.

There is a chart functionality also provided which can give a bar chart based on number of connections applied every month based on Status as Search Filter.

Sprinboot is used as a middleware for database connection and api generation. PostgreSQL workbench is used as the database. Angular 14 is used as the frontend framwork for component generation, routing, chart generation, api calling for all the components. This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 14.1.1.

## Tech Stack

**Client:** Angular, HTML, Typescript, CSS, Javascript

**Server:** Spring boot, Java

**Database:** PostgreSQL

## Screenshots - Attached PPT with all the screenshots

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The application will automatically take you to the search Connection Page where yu can search all the connections based on Applicant ID and Range of Date of Application.Please reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.

## Database Details

Schema:bcg
Table:electricity_board_consumer
Column Details:
applicant_id(big integer),
applicant_name(character varying),
gender(character varying),
district(character varying),
status(character varying),
state(character varying),
pincode(character varying),
ownership(character varying),
govtid_type(character varying),
id_number(character varying),
category(character varying),
loan_applied(character varying),reviewer_name(character varying),
reviewer_comments(character varying),
reviewer_id(character varying)
status(character varying),
date_of_application(timestamp without time zone),
date_of_approval(timestamp without time zone),
modified_date(timestamp without time zone)

Make a local database connection based on these schema and enter the data from the excel sheet given with the assignment for proper running of code.

## Backend Steps

Make DB connections on your local PGSQL server as mentioned above and then run the spring boot code using Maven clean,install and then debug.Backend will work.
GET call http://localhost:8085/electricity/connection based on parameters ->applicantId passed as long variable , toDate and fromDate
passed as String in the format'yyyy-mm-dd'. Eg.2021-06-15 for getting required data.

PUT call http://localhost:8085/electricity/connection/1 based on parameters ->applicantId passed as long variable , Connection entity details for updating the data

GET call http://localhost:8085/electricity/connectionsChart?status=Connection based on parameter status passed as string variable for fetching the data as Number of Connections per month.If no paramaeter is passed,then it will fetch data for all the data.It is used for chart preparation in the front end.

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI Overview and Command Reference](https://angular.io/cli) page.
