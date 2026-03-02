MyContacts App

MyContacts App is a Java-based, console-driven contact management system built to demonstrate object-oriented design and core Java concepts through structured use cases.

The application consolidates multiple user and contact management features into a single cohesive experience.

Features
User Management

User Registration

User Authentication

Profile Management (update details, change password, preferences)

Contact Management

Create and manage Person and Organization contacts

View contact details with enhanced formatting

Edit contacts with undo/redo support

Delete contacts (soft delete and hard delete)

Perform bulk operations on multiple contacts

Search and Filtering

Search contacts by name, phone, email, or tag

Advanced filtering options

Sorting capabilities

Tagging and Organization

Create and manage custom tags

Assign multiple tags to contacts

Organize contacts efficiently using tag categories

Admin Features

User oversight

Global search capabilities

Use Case Coverage

The system is implemented use-case wise, covering:

UC01 – User Registration

UC02 – User Authentication

UC03 – Profile Management

UC04 – Create Contact

UC05 – View Contact

UC06 – Edit Contact

UC07 – Delete Contact

UC08 – Bulk Operations

UC09 – Search Contacts

UC10 – Advanced Filtering

UC11 – Manage Tags

UC12 – Apply Tags

Core OOP Concepts Demonstrated

Encapsulation with private fields and validation logic

Inheritance through User and Contact hierarchies

Polymorphism via interface-based behaviors

Abstraction through well-defined interfaces

Composition for Contact, PhoneNumber, and Email relationships

Core Java Concepts Used

Collections Framework (List, Set, EnumSet)

Stream API and functional interfaces (Predicate, Comparator)

UUID for unique identifiers

LocalDateTime for timestamps

Optional for safe null handling

Regular expressions for validation

Exception handling and custom exceptions

Defensive copying and immutability principles

equals() and hashCode() implementations

Project Structure Overview

User Management Module

Contact Management Module

Search and Filter Module

Tag Management Module

Admin Module

Purpose

This project is designed as a structured learning and demonstration application to showcase:

Object-oriented design

Clean separation of concerns

Practical application of core Java features

Use-case driven development

How to Run

Clone the repository

Open in any Java IDE (IntelliJ, Eclipse, VS Code)

Run the main application class

Follow the console prompts
