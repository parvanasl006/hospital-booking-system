Markdown# 🏥 Hospital Appointment Booking System

A desktop application built using **Java Swing** and **MySQL** that provides a platform for managing hospital appointments, patients, and doctors.

---

## 📌 Features

* **Authentication & Authorization**
  * Multi-role login for **Patients** and **Admins**.
  * User registration for new patients.

* **Patient Dashboard**
  * View upcoming and past appointments.
  * Book new appointments with available doctors.
  * Cancel existing appointments.

* **Admin Capabilities**
  * Manage doctor profiles and specializations.
  * Oversee patient profiles and system appointments.

---

## 🛠️ Project Structure

```text
├── database/
│   └── DatabaseConnection.java    # Handles MySQL JDBC connectivity
├── models/
│   ├── Appointment.java           # Appointment data model
│   ├── Doctor.java                # Doctor data model
│   └── Patient.java               # Patient data model
├── ui/
│   ├── LoginFrame.java            # Main entry point and authentication screen
│   ├── RegisterFrame.java         # New user registration screen
│   └── PatientDashboard.java      # Patient control panel & appointment viewer
└── database.sql                   # SQL schema and seed data
📋 PrerequisitesBefore running this application, make sure you have the following installed:Java Development Kit (JDK 8 or higher)MySQL Server (or XAMPP / WAMP)MySQL Connector/J Driver (mysql-connector-j-x.x.x.jar)🚀 Setup & Installation1. Database SetupOpen your MySQL client (e.g., MySQL Workbench or phpMyAdmin).Execute the provided SQL script to set up the database and sample data:SQLCREATE DATABASE IF NOT EXISTS hospital_db;
USE hospital_db;
-- Run the full script provided in the database schema file
2. Configure Database CredentialsOpen src/database/DatabaseConnection.java and update your MySQL connection details if necessary:Javaprivate static final String URL = "jdbc:mysql://localhost:3306/hospital_db";
private static final String USER = "root";
private static final String PASSWORD = "your_password"; // Add your MySQL password here
3. Build & RunUsing Command Line:Compile the Java source files (include the MySQL Connector JAR in your classpath):Bashjavac -cp .;mysql-connector-j.jar database/*.java models/*.java ui/*.java
Run the application:Bashjava -cp .;mysql-connector-j.jar ui.LoginFrame
Using an IDE (IntelliJ IDEA / Eclipse / NetBeans):Import the project into your preferred IDE.Add the MySQL Connector JAR as a library dependency.Locate ui.LoginFrame.java and run the main method.🔑 Default CredentialsRoleUsername / EmailPasswordAdminadminadmin123Doctor (Sample)rajesh@hospital.com(N/A - Doctor Accounts)📄 LicenseThis project is open-source and available under the MIT License.
