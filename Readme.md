# Docker - module 18
---

## Contents:
1. [General Information](#general-information)
2. [Task Objective](#task-objective)
3. [Requirements](#requirements)
4. [Pre-configuration](#pre-configuration)
5. [Configuration](#own-configuration)
6. [Run](#how-to-run)
7. [Test](#during-tests)
8. [Future innovations](#future-innovations)
9. [Notes](#notes)
---

## General Information
This module is focus on learning about:
- how Docker works,
- issues with not contenaraze apps,
- pros and cons of contenarization,
- how to write Dockerfile and docker-compose.yaml.

---

## Task objective

Main assesment is focus on contenarizating UI test framework with usage of Dockerfile and docker-compose.yaml

For more details check **Assesment.md**.

---

## Requirements
To test solution, you need to have:
- Java 17 or higher
- Maven 3
- Docker (Linux OS)/ Docker Desktop (Windows/MacOS)

---

## Pre-configuration

Before starting tests, few things need to be configurated:
- Change name of .env_template to .env.
- Save inside :scroll: **.env** email and password for shop account. File should looks like this:

```env
EMAIL=example@gmail.com
PASS=example
```

---
## Configuration
### General Information
All other default configuration can be found in this files:

- env.properties
```properties
site.url = https://www.komputronik.pl/
remote.url = http://selenium-hub:4444/
```

- run.properties
```properties
browser.name = chrome
browser.width = 1280
browser.height = 720
is.remote = true
```
### Own configuration
In actual time, application support few configuration changes such as:
- Type of browser (support Chrome/Firefox).
   - change browser.name = chrome/firefox (use lowercase, because it is CASE SENSITIVE)
- Size of window. 
- Local or remote run
   - for running remotly is.remote = true, for local run is.remote = false. 

## How to run
To run, follow this steps:
1. Open **Terminal/Command Prompt** and clone docker-module-18 repository:
```bash
  git clone https://github.com/SkarKam/docker-module-18.git
```
2. Configurate .env that was described in [Pre-configuration](#pre-configuration)
3. Start docker: run Docker Desktop app on Windows/MacOs or use this command on Linux:
```bash
   sudo systemctl start docker
```
4. Open **Terminal**, go to folder (📂 docker-module-18).
5. Build test image:
```bash
  docker build -t test-framework-image .
```
6. Build and start docker-compose up
```bash
  docker-compose --build up
``` 
7. Run test from Docker Compose containers.

---

## During tests

There is possibility to check current test progress. To do this, you need:
1. Open this page [selenium-grid](http://localhost:4444/ui/)
2. Click camera icon.
3. Provide password. For selenium grid it's usually ***secret***.
4. Watch how test work.

---

## Future innovations
In close future, I will add few changes such as:
- Add configuration for different browsers and dimesions.
- Add loggers.
- Repair StoreLocationTest.java (not working with standalone chrome container).
- Use parameters.
- Add allure reports.
- Change to BDD with usage of Cucumber.
- Integrate with CI/CD pipelines.

---

## Notes
- Technology used:
  - JDK 17-oracle
  - Maven 3.13.0
  - Surefire 3.5.2
  - TestNG 7.11.0
  - Selenium 4.25.0
  - WebDriverManage from io.github.bonigarcia 5.4.1 (for local testing)
  - Lombok
