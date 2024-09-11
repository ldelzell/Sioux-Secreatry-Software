# Sioux Secretary Software - Group Project

## Introduction

Sioux is facing a problem with their meeting booking system. Their secretary has too much to do and cannot keep up. That's why they asked us to develop a system for the secretary and parking management. The secretary's job is to create meetings by entering some client information, such as name, time, license plate (if coming by car), and check the availability of her colleagues for the meetings.

The project involves various technologies for creating a full-stack application, a camera detection algorithm for the parking lot, and a parking space detector to see if there are free spots.

## Technologies

- **Full-Stack App:** Java, Gradle, Spring Boot, JWT Authentication, WebSockets, React, TypeScript, with MySQL as the database, implemented using Flyway and JPA.
- **License Plate Detection:** Python + Tesseract (pytesseract)
- **Parking Space Detection:** Arduino

## 1. Client & Team

**Clients:** Sioux  
**Team:**
- **Stanislav Nikolov** (Team Lead, responsible for license plate detection, parts of back-end, and helping where needed)  
- **Atanas Tsenov** (Front-end)  
- **Desislav Hristov** (Back-end)  
- **Mihail Mihov** (Parking spot detection)

## 2. Current Situation

Sioux is facing a problem with their meeting booking system. Their secretary has too much to do and cannot keep up. That's why they asked us to develop a system for the secretary and parking management. The secretary's job is to create meetings by entering some client information, such as name, time, license plate (if coming by car), and check the availability of her colleagues for the meetings.

## 4. Project Goal

Develop an efficient meeting booking and parking management system for Sioux to streamline the secretary's workflow. The system will allow the secretary to schedule meetings, enter client details (name, time, license plate), and check colleague availability. It will also incorporate a parking space detection feature and a camera-based algorithm to identify available parking spots, improving the overall parking experience.

## 5. Deliverables

### Common Deliverables for the Applications:

1. **User Interface (UI) Design:**  
   A fully functional and intuitive front-end interface that allows the secretary to easily input meeting details, client information, check availability, and manage the meeting calendar.

2. **Meeting Scheduling System:**  
   Backend functionality that enables scheduling, editing, and canceling of meetings, including availability checks for colleagues and clients.

3. **Parking Management System:**  
   Integration of a parking space detection feature, with real-time updates on available parking spots for clients arriving by car.

4. **Camera Detection Algorithm:**  
   Implementation of a camera-based license plate recognition system to automatically identify and verify client vehicles as they enter the parking area.

5. **Database Integration:**  
   A secure and scalable database to store client, meeting, and parking information, including client details, vehicle license plates, and colleague availability.

6. **Testing and Documentation:**  
   Comprehensive testing of the application, including system, unit, and integration testing. Complete user and technical documentation for the application’s features, setup, and usage.

## 6. Non-Deliverables

1. **Regular Updates:** No provision for ongoing updates or improvements beyond the initial release.
2. **User Support:** No user support beyond providing user documentation.
3. **Physical Products:** No development or delivery of physical products.

## 7. Constraints

- Project completion by June 28th, 2024.
- Development in Java, Gradle, Spring Boot, JWT Authentication, WebSockets, React, TypeScript, MySQL (implemented using Flyway and JPA), Python, and Arduino.

## 8. Features

- License plate detection
- Parking spot detection
- Create a meeting
- Notify when a client enters the parking lot
- Email verification
