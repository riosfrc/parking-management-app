# Parking Management App
## Introduction
Created by Fernando Rios.

Application for managing vehicle access to a paid parking lot. The parking lot is not automated, so there is an employee in charge of registering vehicle entries and exits.
## Tech Stack
* Java 17
* Spring Boot 3.2.3
* PostgreSQL

## Project Description
Vehicles are identified by their license plate number. When a vehicle enters the parking lot, the employee registers its entry and when leaving, it registers its exit and, in some cases, charges the corresponding amount for the parking time.

The amount charged depends on the type of vehicle:

Official vehicles do not pay, but their stays are recorded to keep track. (A stay consists of one check-in and one check-out time)
Residents pay at the end of the month at a rate of MXN$0.05 per minute. The application will accumulate the time (in minutes) that they have remained parked.

Non-residents pay at the exit of the parking lot at a rate of MXN$0.5 per minute. It is expected that new types of vehicles may be included in the future, so the developed application should be easily extensible in that regard.

The use cases are described below. It does not go into details of the interaction between the employee and the application (point 1 of each use case), since it will not be the task of this exercise to develop that part.

#### Use case "Registration" ####
The employee chooses the "register entry" option and enters the license plate number of the entering car.
The application records the vehicle's entry time.

#### Use case "Log output" ####
The employee chooses the "check out" option and enters the license plate number of the leaving car.

The application performs the actions corresponding to the type of vehicle:
* Official: associate the stay (check-in time and check-out time) with the vehicle.
* Resident: add the duration of the stay to the total accumulated time.
* Non-resident: obtains the amount to pay.

#### Use case "Register official vehicle" ####
The employee chooses the option "register official vehicle" and enters his license plate number.
The application adds the vehicle to the list of official vehicles.

#### Use case "Register resident vehicle" ####
The employee chooses the option "register resident vehicle" and enters his license plate number.
The app adds the vehicle to the resident vehicle list.

#### Use case "Month begins" ####
The employee chooses the "month begins" option.
The application eliminates registered stays in official cars and resets the time parked by resident vehicles to zero.

#### "Resident Payments" Use Case ####
The employee chooses the "generate resident payment report" option and enters the name of the file in which they want to generate the report.
The application generates a file that details the time parked and the money to be paid for each of the resident vehicles. The file format will be as shown below:

| Plate number | Parked time (min.) | Amount to pay |
| ------------- | ------------- | ------------- |
| S1234A | 20134 | 1006.70 |
| 4567ABC | 4896 | 244.80 |
| ... | ..... | ..... |

#### Data persistence ####
The information of each of the vehicle rooms will be stored in a database. Because the database manager can be modified at any time.
