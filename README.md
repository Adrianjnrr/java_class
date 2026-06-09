# TechCorp Duel Simulator

## Overview

TechCorp Duel Simulator is a Java-based business management simulation game where a player competes against an AI-controlled company. The objective is to manage employees, complete projects, increase company value, and finish a strategic project before the competitor.

The project was initially developed as a console application and later transformed into a web application using Spring Boot, HTML, CSS, and JavaScript. It is deployed online using Docker and Render.

---

## Features

### Company Management

* Manage company budget
* Track reputation
* Calculate company value
* Compete against an AI company

### Employee Management

* Hire Developers
* Hire Testers
* Hire Interns
* Assign employees to projects

### Project Management

* Strategic AI Platform
* Company Website
* Mobile App
* Project progress tracking
* Project rewards

### AI Competition

* AI-controlled company
* Automatic project progression
* Strategic competition

### Game Events

* Investor Funding
* High Demand
* Market Slowdown

### Web Dashboard

* Real-time game information
* Employee management
* Project management
* Progress bars
* Winner screen
* Final scoreboard

---

## Technologies Used

### Backend

* Java 17
* Spring Boot
* Maven

### Frontend

* HTML
* CSS
* JavaScript

### Deployment

* Docker
* GitHub
* Render

---

## Project Structure

```text
src/main/java/com/example/techcorp
│
├── api
│   ├── GameController.java
│   ├── GameService.java
│   ├── GameState.java
│   ├── EmployeeInfo.java
│   └── ProjectInfo.java
│
├── engine
│   └── GameEngine.java
│
├── ui
│   └── ConsoleUI.java
│
├── Company.java
├── Employee.java
├── Developer.java
├── Tester.java
├── Manager.java
├── Intern.java
├── Project.java
├── ProjectStatus.java
├── AIPlayer.java
├── GameResult.java
└── Main.java
```

---

## Object-Oriented Programming Concepts

This project demonstrates:

### Encapsulation

* Company class
* Employee class
* Project class

### Inheritance

* Developer extends Employee
* Tester extends Employee
* Manager extends Employee
* Intern extends Employee

### Polymorphism

* Different employee types treated as Employee objects

### Composition

* Company contains Employees and Projects

### Abstraction

* Separation of business logic, API layer, and UI layer

---

## How the Game Works

1. Start a new game.
2. Hire employees.
3. Assign employees to projects.
4. Process turns.
5. Employees contribute progress based on skill.
6. Projects generate rewards and reputation.
7. Compete against the AI company.
8. Complete the strategic project before the AI.

---

## Winning Conditions

The game ends when:

* The player completes the strategic project first.
* The AI completes the strategic project first.
* A company becomes bankrupt.
* Maximum turns are reached.

Possible outcomes:

* PLAYER_WINS
* AI_WINS
* DRAW

---

## REST API Endpoints

### Game State

```http
GET /game/state
```

Returns the current game state.

### Projects

```http
GET /game/projects
GET /game/ai-projects
```

### Employees

```http
GET /game/employees
GET /game/ai-employees
```

### Actions

```http
POST /game/work
POST /game/hire/developer
POST /game/hire/tester
POST /game/hire/intern
POST /game/assign
POST /game/new
```

---

## Running Locally

Clone the repository:

```bash
git clone https://github.com/Adrianjnrr/java_class.git
```

Navigate to the project:

```bash
cd my-java-app
```

Run the application:

```bash
mvn spring-boot:run
```

Open:

```text
http://localhost:8080
```

---

## Deployment

The application is deployed using Render.

Live Application:

https://java-class-1.onrender.com

Deployment stack:

GitHub → Docker → Render

---

## Author

Adrian

Warsaw School of Economics (SGH)

Field of Study: Advanced Analytics – Big Data

---

## Future Improvements

* Database integration
* Save and load game functionality
* Advanced AI behavior
* Multiplayer support
* User authentication
* Analytics dashboard

---

## Conclusion

TechCorp Duel Simulator demonstrates the practical application of Object-Oriented Programming, Spring Boot REST APIs, frontend development, Docker containerization, and cloud deployment. The project evolved from a console simulation into a fully functional web application and serves as a complete example of modern software development practices.
