# XpensAuditor : a smart way to track your expenses

![App Logo](https://github.com/NCSU-Fall-2022-SE-Project-Team-11/XpensAuditor---Group-11/blob/main/project-docs/demo/ic_account-playstore.png)

[![Code Coverage](https://codecov.io/gh/NCSU-Fall-2022-SE-Project-Team-11/XpensAuditor---Group-11/branch/main/graphs/badge.svg)](https://codecov.io/gh/NCSU-Fall-2022-SE-Project-Team-11/XpensAuditor---Group-11/branch/main)
[![DOI](https://zenodo.org/badge/566109274.svg)](https://zenodo.org/badge/latestdoi/566109274)
[![Collaborators](https://img.shields.io/badge/Collaborators-5-orange.svg?style=flat)](https://github.com/NCSU-Fall-2022-SE-Project-Team-11/XpensAuditor---Group-11/graphs/contributors)
[![License](https://img.shields.io/badge/License-MIT-purple.svg?style=flat)](https://github.com/NCSU-Fall-2022-SE-Project-Team-11/XpensAuditor---Group-11/blob/main/LICENSE)
[![Language](https://img.shields.io/badge/Language-Java-blue.svg?style=flat)](https://github.com/NCSU-Fall-2022-SE-Project-Team-11/XpensAuditor---Group-11/search?l=java)
[![Documentation Status](https://readthedocs.org/projects/ansicolortags/badge/?version=latest)](https://github.com/NCSU-Fall-2022-SE-Project-Team-11/XpensAuditor---Group-11/blob/main/README.md)
![GitHub release (latest by date)](https://img.shields.io/github/v/release/NCSU-Fall-2022-SE-Project-Team-11/XpensAuditor---Group-11?display_name=tag)
![GitHub issues](https://img.shields.io/github/issues/NCSU-Fall-2022-SE-Project-Team-11/XpensAuditor---Group-11)
[![Build & Test](https://github.com/NCSU-Fall-2022-SE-Project-Team-11/XpensAuditor---Group-11/actions/workflows/android.yml/badge.svg)](https://github.com/NCSU-Fall-2022-SE-Project-Team-11/XpensAuditor---Group-11/actions/workflows/android.yml)
[![GitHub Repo Size](https://img.shields.io/github/repo-size/NCSU-Fall-2022-SE-Project-Team-11/XpensAuditor---Group-11.svg)](https://img.shields.io/github/repo-size/NCSU-Fall-2022-SE-Project-Team-11/XpensAuditor---Group-11.svg)
#

## Summary
 
 - This mobile application allows customers to add their expenses and keep track of them. 
 - Takes required minimal amount of data like date of transaction, product name and value
 - Each user needs to create/sign up to access the application, Firebase is used to achieve authorization, authentication and accounting
 - The application keeps track of user data and stores it in the Firebase realtime database
 - The app supports multiple interesting features like Rating, Customer Service Feedback, Contact Us
 - The Mailing and SMS services have been configured for the app for ease of sending customer updates, password reset requests and reading personal expenses 
 - Profile set up module is where user can update their details 
 - Account Settings are available to change password, send password reset email

## [DOCS](https://ncsu-fall-2022-se-project-team-11.github.io/XpensAuditor---Group-11/)

## Demo

https://user-images.githubusercontent.com/112219214/194787499-0125447e-f68d-444b-9a86-559b14adf898.mp4


## Roadmap

 - Issues encountered and solved so far - [ISSUES](https://github.com/NCSU-Fall-2022-SE-Project-Team-11/XpensAuditor---Group-11/issues?q=is%3Aissue+is%3Aclosed)
 - Improved Items :
   - Analysis on detected expenses - Category wise expenses and expenditure graphs
   - Added custom categories.
   - Auto detect categories based on vendor.
   - Change password feature is implemented.
   - Added shared expenses feature.
   - Email alerts when a shared expense is added (to all users involved in the expense).
   - Daily/ Monthly limits for controlling spending.
   - Notifications when spending limits are breached.
   - Added feature to edit transactions.
   - Updated documentation with automated Gradle task for generation & a GitHub pages website for hosting (linked aovve).


## X-fold improvements:
- Fine-tuned the Firebase schema to store data better and new fields for additional information which can be extended to build further improvements (such as improved personalization) in the future.
- All the email alerts are configured to use an external API instead of a Java library to reduce the application size and to improve dependencies. This also lowers the complexity since there is a clear separation of responsibility. Lastly, we can also scale this further to use the same API for sending text alerts and to reach a wider audience.
- Some of the tasks (including a few network calls) have been converted to asynchronous tasks to improve performance of the application. This also optimizes the overall performance of the application.
- The usage of Firebase allows for dynamic scaling to reach more users as the load increase without having to modify the application.
- Made the architechture more flexible by having a state-driven Firebase instance which enabled the app to cater to multiple user scenario where one user can log in and log out without having to worry about the state of the application. This can be scaled to any 'n' number of users using the application on a single device.
 

## License

 This project is licensed under the MIT License. See the [LICENSE](https://github.com/NCSU-Fall-2022-SE-Project-Team-11/XpensAuditor---Group-11/blob/main/LICENSE) file for details
 
## Tools used

- Android Studio
- Embedded Emulator/ USB debugging on android device

## Run the application
### Android Studio
 - Must have android studio installed
 - Clone the github repo
   git clone https://github.com/NCSU-Fall-2022-SE-Project-Team-11/XpensAuditor---Group-11.git
 - Open the cloned repo using android studio
 - Build the application for any dependency inconsistencies and proper functioning of app
 - Expand the file structure and run the app using "Run" option
 
## Test the application

 - Application has an existing test suite
 - Navigate to the folder of Android Test and right click and select "Run tests in ...."
 - It runs all the test cases in the package and provides the result and code coverage
 
## Team Members:

This repository is made for CSC 510 Software Engineering Course:

Members
 - Aditi Vakeel 
 - Harshitha Paruchuri 
 - Kaushik Jadhav
 - Pradyumna Vemuri
 - Surya Prakash Susarla

