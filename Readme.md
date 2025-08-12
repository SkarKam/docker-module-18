# Docker - module 18
## General Information
This module is focus on learning about:
- how Docker works,
- pros and cons of contenarization,
- how to write Dockerfile and docker-compose.yaml.

## 🎯 Task objective
Main assesment is focus on contenarizating UI test framework with usage of Dockerfile and docker-compose.yaml

---

## ☑️ Requirements
To test solution, you need to have:
- Java 17 or higher
- Maven 3
- Docker (Linux OS)/ Docker Desktop (Windows/MacOS)

---

## Configuration
In actual moment, project use hardcoded configuration of browsers. Additional configuration will be added later.

## ⏩ How to run
To run, follow this steps:
1. Start docker: run Docker Desktop on Windows/MacOs or use this command on Linux:
```bash
   sudo systemctl start docker
```
2. Open **Terminal/Command Prompt** and clone docker-module-18 repository:
```bash
  git clone https://github.com/SkarKam/docker-module-18.git
```
3. Open **Terminal**, go to folder (📂 docker-module-18).
4. Build test image:
```bash
  docker build -t test-framework-image .
```
5. Build and start docker-compose up
```bash
  docker-compose --build up
``` 
6. Run test from Docker Compose containers.

---

## 🗓️ Future changes
In close future, I will add few changes such as:
- Add configuration for different browsers and dimesions.
- Add loggers.
- Repair StoreLocationTest.java (not working with standalone chrome container).
- Use parameters.
- Add allure reports.
- Change to BDD with usage of Cucumber.
- Integrate with CI/CD pipelines.

---

## 📓 Notes
- Technology used:
  - JDK 17-oracle
  - Maven 3.13.0
  - Surefire 3.5.2
  - TestNG 7.11.0
  - Selenium 4.25.0
  - WebDriverManage from io.github.bonigarcia 5.4.1 (for local testing)
  - Lombok
