# DICOM Management System - A Streamlined Research Collaboration Platform

This application addresses the challenges researchers face in managing DICOM files obtained from hospitals.  The current, unorganized approach of downloading and storing DICOM files locally leads to issues like:

1. Difficulty finding specific studies due to lack of centralized storage
2. Version control problems, making it hard to track changes
3. Potential security risks associated with uncontrolled file sharing

This DICOM Management System offers a secure and efficient solution for researchers to share DICOM data.

# Key Functionalities:

1. [Secure User Login and Registration](https://github.com/EraxDhruv/Album/blob/main/backend/src/main/java/com/example/HMS/controller/UserController.java): Researchers can register and log in using JWT authentication, ensuring secure access to the platform.
2. [Hospital Management](https://github.com/EraxDhruv/Album/blob/main/backend/src/main/java/com/example/HMS/controller/HospitalController.java):
    - Create, Update, and Delete Hospitals: Add new hospitals to the system, maintain their details, and remove them if necessary.
    - Hospital List Retrieval: View a comprehensive list of all registered hospitals.
3. [DICOM Upload](https://github.com/EraxDhruv/Album/blob/main/backend/src/main/java/com/example/HMS/controller/DicomPatientController.java): Upload DICOM files (individual) to designated hospital repositories. Parse the metadata via Dicom4che.
4. [Patient Management](https://github.com/EraxDhruv/Album/blob/main/backend/src/main/java/com/example/HMS/controller/DicomPatientController.java):
    - Retrieve Patient Lists: Get a complete list of patients associated with a specific hospital.
    - Patient Selection for Albums: Select patients to be included in research albums for sharing with collaborators.
6. Pagination, filtering and sorting functionalities for [hospital](https://github.com/EraxDhruv/Album/blob/main/backend/src/main/java/com/example/HMS/service/HospitalService.java) and [patient list](to do).
7. Adding patients to album using third party apis (to implement)
8. Intelligent File Organization:  The application automatically organizes uploaded DICOM files using a configurable filing system. This system ensures easy retrieval of specific studies later. (to implement)
9. Automated Uploads with Scheduling:  Schedule automatic transfers of DICOM files to designated hospital repositories, eliminating manual uploads and reducing the potential for errors. (to implement)

# Requirement
1. Java 17 installed
2. MySql Database Server running

# Technical stack:
1. Java
2. Maven
3. Spring Boot Framework
4. MySql Database
5. JPA for Data Persistence
6. HTML/CSS/JavaScript for User Interface
