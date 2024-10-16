# Secure Coding with Spring Boot
## Overview
This repository demonstrates essential web security practices using **Spring Boot**. It focuses on how to effectively secure your web applications by addressing **injection vulnerabilities** and implementing **best practices** in modern web security. The project highlights the following security themes:

* **SQL Injection** (PostgreSQL)
* **NoSQL Injection** (MongoDB)
* **LDAP Injection**
* **Log and CSV Injection**
* **Web Security** using Spring Security and custom security configurations

It also covers fundamental techniques of **ethical hacking** and penetration testing, such as identifying and mitigating injection attacks through secure coding.

## Tech Stack
This project uses a variety of technologies to demonstrate secure coding principles:

## Backend

* **Spring Boot:** Framework to build RESTful web applications with embedded security.
* **Spring Security:** Core security framework to protect your web application against common threats.
* **Lombok:** Java library for reducing boilerplate code.
* **Java 11+:** Leveraging modern Java features for better security and efficiency.

## Databases

* **PostgreSQL:** For demonstrating SQL injection attacks and how to secure relational databases.
* **MongoDB:** For demonstrating NoSQL injection attacks and secure data handling in document-based databases.

## Frontend
* **Thymeleaf:** Server-side Java template engine for dynamic rendering of secure HTML.

## DevOps & Tools
* **Docker:** Containerization of the application for easy setup and isolated testing environments.
* **Burp Suite:** A leading tool for web vulnerability scanning and ethical hacking used to test and improve security.

# Key Security Themes

1. ### SQL Injection

* Demonstrates how attackers can exploit vulnerabilities in SQL queries and provides solutions using parameterized queries and ORM techniques.

2. ### NoSQL Injection
* Focuses on common NoSQL injection techniques with MongoDB and how to prevent them by securing query structures.

3. ### LDAP Injection
* Explains how Lightweight Directory Access Protocol (LDAP) queries can be vulnerable to injection attacks and ways to mitigate risks using strong input validation.

4. ### Log and CSV Injection
* Covers techniques to prevent attackers from injecting malicious payloads through logs or CSV file exports.

5. ### Web Security with Spring Security

* Basic and advanced security configurations to handle common web application vulnerabilities.
* Securing REST APIs and web applications using role-based access control and OAuth2.
6. ### Ethical Hacking Practices
* Practical exercises on identifying vulnerabilities, conducting penetration tests, and how to secure web applications from malicious attacks.

## Running the Project
### Prerequisites
* Java 11+
* Docker (optional but recommended for easy environment setup)
* PostgreSQL & MongoDB
* Burp Suite for vulnerability scanning (optional for ethical hacking exercises)

### Steps to Run
1. Clone the repository
```
https://github.com/SoulMan87/secure-coding
```
2. Set up the database and run the application using Docker Compose:
```
docker-compose up --build
```
3. Access the application:
* Web UI: http://localhost:8080
* API Docs (if included): http://localhost:8080/api-docs

## Contributions
Contributions are welcome! Feel free to submit issues or pull requests to add more security scenarios or improve existing ones.

### License
This project is licensed under the MIT License - see the LICENSE file for details.
