# Docker - module 18
---

## Contents:
### 1. [General Information](#-general-information)
### 2. [Task Objective](#-task-objective)
### 3. [Requirements](#-requirements)
### 4. [Pre-configuration](#pre-config)
### 5. [Configuration](#config)
### 6. [How to run](#-how-to-run)
### 7. [Live test viewing](#-live-test-viewing)
### 8. [Post Run Cleaning](#-post-run-cleaning)
### 8. [Future Plans](#future)
### 9. [Tech Stack](#-tech-stack)
    
---

## 📖 General Information
This module is focused on learning about:
- How Docker works.
- The challenges of running applications without containerization.
- The pros and cons of containerization.
- How to write a `Dockerfile` and a `docker-compose.yaml`.

---

## 🎯 Task Objective

The main objective is to containerize a UI test framework using a `Dockerfile` and `docker-compose.yaml`.

To obtain this goal, we need follow this steps:
1. ***Define a Base Image:*** A `Dockerfile` was created to define a base image containing all necessary components, such as the Java Runtime Environment and browser drivers.
2. ***Build the Docker Image:*** A custom Docker image for the test framework was built using the `docker build` command.
3. ***Define Services with Docker Compose:*** A `docker-compose.yaml` file was set up to define and orchestrate all required services (e.g., Selenium Hub, browser nodes) and expose their necessary ports.
4. ***Enable Remote Execution:*** The test framework was configured to run tests inside a Docker container by connecting to the remote Selenium Grid.
5. ***Run and Verify:*** The entire test suite was executed using docker-compose to ensure all services work together correctly and that the tests pass successfully within the containerized environment.

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
### ⚙️ Default configuration

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
---
### ⚙️ Custom Configuration
Currently, you can customize the following settings in `run.properties`:
- Browser Type: Change `browser.name` to `chrome` or `firefox`. Note: This value is ***case-sensitive***.
- Window Size: Adjust `browser.width` and `browser.height` as needed.
- Execution Mode: Set `is.remote=true` to run tests against the remote Selenium Grid (via Docker) or `false` to run them on your local machine.

---

## ⏩ How to run

### Default run
To run, follow this steps:
1. Open **Terminal/Command Prompt** and clone docker-module-18 repository:
```bash
  git clone https://github.com/SkarKam/docker-module-18.git
```
2. Navigate into the cloned directory:
```bash
cd docker-module-18
```
3. Configurate `.env` file as described in the [Pre-configuration](#pre-config) section.
4. Ensure Docker is running. On Windows/macOS, launch the Docker Desktop app. On Linux, you can start the Docker daemon with:
```bash
   sudo systemctl start docker
```
5. Build the images and start the containers using Docker Compose. This command will also start the test execution automatically.
```bash
  docker-compose --build up
``` 

### Alternative Run (with Docker Commands)
This is an alternative route to run the solution without using  `docker-compose.yaml`.

1. Complete steps 1 through 4 from the ***[Default Run](#default-run)*** instructions.
2. In your **terminal**, build the *test framework* image:
```bash
docker build -t test-framework-image .
```
3. Create a dedicated network for the containers to communicate:
```bash
docker network create selenium-grid-net
```
4. Run the Selenium Hub container:
```bash
docker run -d \
--name selenium-hub \
--network selenium-grid-net \
-p 4442:4442 -p 4443:4443 -p 4444:4444 \
selenium/hub:4.34.0-20250727   
``` 
5. Run the Chrome browser node container:
```bash
 docker run -d \
--name chrome \
--network selenium-grid-net \
--shm-size="4g" \
-e SE_EVENT_BUS_HOST=selenium-hub \
-e SE_EVENT_BUS_PUBLISH_PORT=4442 \
-e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 \
selenium/node-chrome:4.34.0-20250727
```
6. Run the test framework container to execute your tests:
```bash
docker run -d \
--name test-framework-container \
--network selenium-grid-net \
test-framework-image
```

---

## 🧪 Live test viewing

It's possible to watch the tests exeute in real-time. To do so:
1. Open Selenium Grid UI in your browser: [selenium-grid](http://localhost:4444/ui/)
2. You will see active sessions. Click the camera icon 🎥 next to a session.
3. When prompted for a password, enter ***secret***.
4. Watch your test run live inside the container!

---

## 🪣 Post Run Cleaning
After your tests are complete, use these commands to clean up your Docker environment.

1. Stop and remove all containers created for the test run (hub and nodes):
```bash
docker rm -f selenium-hub chrome test-framework-container
```
2. Remove the test framework image:
```
docker rmi test-framework-image
```
3. Remove the network:
```bash
docker network rm selenium-grid-net
```

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
