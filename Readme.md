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
This module is focused on learning about:
- How Docker works.
- The challenges of running applications without containerization.
- The pros and cons of containerization.
- How to write a `Dockerfile` and a `docker-compose.yaml`.

---

## 🎯 Task objective

The main objective is to containerize a UI test framework using a `Dockerfile` and `docker-compose.yaml`.

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

- [`env.properties`](https://github.com/SkarKam/docker-module-18/blob/main/src/main/resources/env.properties)
```properties
site.url = https://www.komputronik.pl/
remote.url = http://selenium-hub:4444/
```

- [`run.properties`](https://github.com/SkarKam/docker-module-18/blob/main/src/main/resources/run.properties)
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
  git clone https://github.com/SkarKam/docker-module-18.git
```
3. Navigate into the cloned directory:
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

## 🧪 Live test viewing

It's possible to watch the tests exeute in real-time. To do so:
1. Open Selenium Grid UI in your browser: [selenium-grid](http://localhost:4444/ui/)
2. You will see active sessions. Click the camera icon 🎥 next to a session.
3. When prompted for a password, enter ***secret***.
4. Watch your test run live inside the container!

---

## 🗓 Future Plans <a id='future'/>
In the near future, I plan to implement the following improvements:
- Implement comprehensive logging.
- Repair [`StoreLocationTest.java`](https://github.com/SkarKam/docker-module-18/blob/main/src/test/java/StoreLocationTest.java) (currently fails when run with a standalone Chrome container).
- UParameterize tests for better data management.
- Generate Allure test reports.
- Refactor the framework to use a BDD approach with Cucumber.
- Integrate the test suite with a CI/CD pipeline.

---

## 📓 Tech Stack
- Java: Oracle JDK 17
- Build Tool: Maven 3.13.0
- Test Runner: Surefire 3.5.2
- Testing Framework: TestNG 7.11.0
- Browser Automation: Selenium 4.25.0
- WebDriver Management: WebDriverManager from io.github.bonigarcia 5.4.1 (for local testing)
