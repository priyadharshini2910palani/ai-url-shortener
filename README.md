# AI-Powered URL Shortener

## Project Overview

AI-Powered URL Shortener is a Spring Boot application that allows users to create and manage short URLs securely. The system includes JWT-based authentication, URL analytics, expiry management, and Gemini AI integration for URL safety analysis and smart short-code suggestions.

## Features

* User Registration
* User Login
* JWT Authentication
* URL Shortening
* Custom Short Codes
* URL Redirection
* Click Count Tracking
* URL Expiry Management
* Gemini AI URL Safety Analysis
* AI Suggested Short Codes
* User-specific URL Dashboard
* Admin Statistics Dashboard

## Technologies Used

* Java
* Spring Boot
* Spring Security
* JWT
* MySQL
* Spring Data JPA / Hibernate
* Gemini AI API
* Maven

## API Endpoints

### Authentication

* POST `/api/auth/register`
* POST `/api/auth/login`

### URL Management

* POST `/api/url/shorten`
* GET `/api/url/my-urls`
* GET `/api/url/{shortCode}`

### AI Analysis

* GET `/api/ai/analyze?url=...`

### Admin Dashboard

* GET `/api/admin/stats`

## Configuration

Update `application.properties`:

gemini.api.key=YOUR_GEMINI_API_KEY

## Run the Project

1. Configure MySQL database
2. Update application.properties
3. Run:

mvn spring-boot:run

## Author

Priyadharshini P
Final Year ECE Student | Java & Spring Boot Developer
