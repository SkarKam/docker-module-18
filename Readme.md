# Docker - module 18
---

## Contents:
1. [General Information](#-general-information)
2. [Task Objective](#-task-objective)
3. [Requirements](#-requirements)
4. [Pre-configuration](#pre-config)
5. [Configuration](#config)
6. [Run](#-how-to-run)
7. [Test](#-during-tests)
8. [Future innovations](#future)
9. [Notes](#-notes)
---

## 📖 General Information
This module is focus on learning about:
- How Docker works.
- The challenges of running applications without containerization.
- The pros and cons of containerization.
- How to write a `Dockerfile` and a `docker-compose.yaml`.

---

## 🎯 Task objective

Main assesment is focus on contenarizating UI test framework with usage of `Dockerfile` and `docker-compose.yaml`.

For more details check **Assesment.md**.

---

## ✅ Requirements
To run this solution, you'll need:
- Java 17 or higher
- Maven 3
- Docker (Linux OS)/ Docker Desktop (Windows/MacOS)

---

## ⚙️ Pre-configuration <a id='pre-config'/>

Before you begin, a few things need to be configured:
1. Rename the `.env_template` to `.env`.
2. Inside the new `.env` file, provide the email and password for your shop account. The file should look like this:

```env
EMAIL=example@gmail.com
PASS=example
```

---
## ⚙️ Configuration <a id='config'/>
### General Information

The default configuration can be found in the following files:

- (`env.properties`)[]
```properties
site.url = https://www.komputronik.pl/
remote.url = http://selenium-hub:4444/
```

- (`run.properties`)[]
```properties
browser.name = chrome
browser.width = 1280
browser.height = 720
is.remote = true
```
### ⚙️ Own configuration
Currently, you can customize the following settings in `run.properties`:
- Browser Type: Change `browser.name` to `chrome` or `firefox`. Note: This value is ***case-sensitive***.
- Window Size: Adjust `browser.width` and `browser.height` as needed.
- Execution Mode: Set `is.remote=true` to run tests against the remote Selenium Grid (via Docker) or `false` to run them on your local machine.

## ⏩ How to run
To run, follow this steps:
1. Open **Terminal/Command Prompt** and clone docker-module-18 repository:
```bash
  git clone [https://github.com/SkarKam/docker-module-18.git](https://github.com/SkarKam/docker-module-18.git)
```
2. Navigate into the cloned directory:
```bash
cd docker-module-18
```
3. Configurate `.env` file as described in the [Pre-configuration](#pre-configuration) section.
4. Ensure Docker is running. On Windows/macOS, launch the Docker Desktop app. On Linux, you can start the Docker daemon with:
```bash
   sudo systemctl start docker
```
5. Build the images and start the containers using Docker Compose. This command will also start the test execution automatically.
```bash
  docker-compose --build up
``` 

---

## 🧪 During tests

There is possibility to check current test progress. To do this, you need:
1. Open this page [selenium-grid](http://localhost:4444/ui/)
2. Click camera icon.
3. Provide password. For selenium grid it's usually ***secret***.
4. Watch how test work.

---

## 🗓 Future innovations <a id='future'/>
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
  - WebDriverManager from io.github.bonigarcia 5.4.1 (for local testing)
  - Lombok
