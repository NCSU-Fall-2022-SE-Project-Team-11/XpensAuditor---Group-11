# XpensAuditor : A smart way to track your expenses
<img src="https://github.com/NCSU-Fall-2022-SE-Project-Team-11/XpensAuditor---Group-11/blob/main/project-docs/demo/ic_account-playstore.png" alt="app_icon" width="250px" />

[![Code Coverage](https://codecov.io/gh/NCSU-Fall-2022-SE-Project-Team-11/XpensAuditor---Group-11/branch/main/graphs/badge.svg)](https://codecov.io/gh/NCSU-Fall-2022-SE-Project-Team-11/XpensAuditor---Group-11/branch/main)
[![DOI](https://zenodo.org/badge/566109274.svg)](https://zenodo.org/badge/latestdoi/566109274)
[![Collaborators](https://img.shields.io/badge/Collaborators-5-orange.svg?style=flat)](https://github.com/NCSU-Fall-2022-SE-Project-Team-11/XpensAuditor---Group-11/graphs/contributors)
[![License](https://img.shields.io/badge/License-MIT-purple.svg?style=flat)](https://github.com/NCSU-Fall-2022-SE-Project-Team-11/XpensAuditor---Group-11/blob/main/LICENSE)
[![Language](https://img.shields.io/badge/Language-Java-blue.svg?style=flat)](https://github.com/NCSU-Fall-2022-SE-Project-Team-11/XpensAuditor---Group-11/search?l=java)
[![Documentation Status](https://readthedocs.org/projects/ansicolortags/badge/?version=latest)](https://github.com/NCSU-Fall-2022-SE-Project-Team-11/XpensAuditor---Group-11/blob/main/README.md)
![GitHub release (latest by date)](https://img.shields.io/github/v/release/NCSU-Fall-2022-SE-Project-Team-11/XpensAuditor---Group-11?display_name=tag)
![GitHub issues](https://img.shields.io/github/issues/NCSU-Fall-2022-SE-Project-Team-11/XpensAuditor---Group-11)
![GitHub closed issues](https://img.shields.io/github/issues-closed/NCSU-Fall-2022-SE-Project-Team-11/XpensAuditor---Group-11)
[![Build & Test](https://github.com/NCSU-Fall-2022-SE-Project-Team-11/XpensAuditor---Group-11/actions/workflows/android.yml/badge.svg)](https://github.com/NCSU-Fall-2022-SE-Project-Team-11/XpensAuditor---Group-11/actions/workflows/android.yml)
[![GitHub Repo Size](https://img.shields.io/github/repo-size/NCSU-Fall-2022-SE-Project-Team-11/XpensAuditor---Group-11.svg)](https://img.shields.io/github/repo-size/NCSU-Fall-2022-SE-Project-Team-11/XpensAuditor---Group-11.svg)
#
**Java**, **Python**, **Firebase** Android App to track and share expenses among multiple users with several features like real time SMS and email alerts, interactive charts of expenses, custom category, etc. The data is stored in **Firebase** and the app also scrapes and compares prices of online products across e-commerce websites like Walmart and Amazon and gives the best recommendation. Integrated the app with **Telegram** chatbots using **Python** APIs for additional features.


## Summary
 
 - This mobile application allows customers to add their expenses and keep track of them. 
 - Takes required minimal amount of data like date of transaction, product name and value
 - Each user needs to create/sign up to access the application, Firebase is used to achieve authorization, authentication and accounting
 - The application keeps track of user data and stores it in the Firebase realtime database
 - The app supports multiple interesting features like Rating, Customer Service Feedback, Contact Us
 - The Mailing and SMS services have been configured for the app for ease of sending customer updates, password reset requests and reading personal expenses 
 - Profile set up module is where user can update their details 
 - Account Settings are available to change password, send password reset email

 NOTE: The 'docs' folder at the root contains the code documentation to be served via GitHub pages. The docs relating to project metrics and other elements are stored in the 'project_docs' folder.

## Project Documentation 
Please refer to the [project-docs](https://github.com/NCSU-Fall-2022-SE-Project-Team-11/XpensAuditor---Group-11/tree/main/project-docs) folder for all documentations as doc folder is being used to host github pages for JavaDoc.

## Classes and Functions Documentation 
For Classes and Functions related Documentation, please refer [JavaDoc](https://ncsu-fall-2022-se-project-team-11.github.io/XpensAuditor---Group-11/) hosted on Github Pages.

## Old Demo

https://user-images.githubusercontent.com/112219214/194787499-0125447e-f68d-444b-9a86-559b14adf898.mp4

## New Demo

https://user-images.githubusercontent.com/95981350/205693235-612c9cdf-06b4-4c26-8195-1413991893e5.mp4


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

## Changelog and Comparison with previous project
 - The previous project only allowed one user to record their transactions at a time. The current system will allow multiple users to share expenses simultaneously simply by providing email addresses of shared expense users.
 - Earlier, the option of data analysis only showed "To be updated in later versions". We Added a new feature such that the option now shows charts and plots to visualize expenses by month, year or any specific date and time.
 - Added a new feature to compare online product prices across popular ecommerce sites like Walmart and Amazon by simply typing in the product name.
 - Added a new feature where the user can set daily, monthly and yearly spending limits on transactions and the user will get alerted on email and SMS should he exceed those limits.
 - Earlier, the project only had a given set of expense categories like Education, Bills, Food, etc from which the user was supposed to choose one. However, now the user can also add expenses with his own custom category.
 - The previous system was able to send one email and SMS at a time. The new system can simultaneously send multiple emails and SMS to all the users.
 - Earlier, there was no option to edit or modify the added transactions. The current system allows users to edit every transaction.
 - The previous project only recorded the ratings and suggestions on the client side. The new version stores ratings and suggestions in the database.
 - In the previous version, the user was never alerted when he tried to overwrite a rating or suggestion. The new version prompts user to choose whether or not to modify the previous rating or review.
 - The previous version had broken CI tests. We fixed the previous tests and added new ones in the current version.
 - The previous project had only 60% coverage. We increased the coverage to 90% in the new version.
 - Make sensitive files like google-services.json private and remove the projects dependency on it.

## X-fold improvements:
- Fine-tuned the Firebase schema to store data better and **new fields dynamically** for additional information which can be extended to build further improvements (such as improved personalization) in the future.
- Earlier the app supported just one user, but now scaled to multiple simultaneous users. Made the architechture more flexible by having a state-driven Firebase instance which enabled the app to cater to multiple user scenario where one user can log in and log out without having to worry about the state of the application. This has been scaled to **any 'n' number of users** using the application on a single device.
- Implemented Database sharding and indexing to scale the database to such an extent that it can be used for large scale production level projects. This gave **65% faster** query retrival than before.
- All the email alerts are configured to use an external API instead of a Java library to **reduce the application size** and to **improve dependencies**. This also **lowers the complexity** since there is a clear separation of responsibility. Lastly, we can also scale this further to use the same API for sending text alerts and to reach a wider audience.
- Some of the tasks (including a few network calls) have been converted to **asynchronous tasks** to improve performance of the application. This also optimizes the overall performance of the application.
- The usage of Firebase allows for **dynamic scaling** to reach more users as the **load increases** without having to modify the application.
 

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

