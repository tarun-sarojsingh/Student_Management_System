📌 Student Management System (Java)

A console-based Student Management System built in Java that allows users to manage student records effectively.
The project demonstrates Object-Oriented Programming (OOP) concepts and uses file serialization for data persistence.

🚀 Features

➕ Add Student – Insert student details with roll number, name, course, and GPA.

🔍 Search Student – Search by Roll Number or Name.

📋 Display All Records – View all stored students.

📊 Sort by GPA – Sort students in descending order of GPA.

💾 Persistence – Save and load records from a file (students.dat) using Java Serialization.

🎓 GPA Calculator – GPA auto-calculated from 5 subject marks.

🛠️ Tech Stack

Language: Java

Concepts Used: OOP (Encapsulation, Immutability, Collections), Exception Handling, File Handling, Serialization

Storage: File-based persistence (ObjectOutputStream / ObjectInputStream)

Interface: Console-based Menu

📂 Project Structure
├── MainApp.java         # Entry point, console menu & user interaction
├── Student.java         # Student entity (model class)
└── StudentManager.java  # Manages student operations (add, search, sort, save/load)

🎯 Future Enhancements

Replace file storage with database integration (MySQL/PostgreSQL).

Add a GUI (JavaFX/Swing) or Web-based UI.

Input validation for subject marks and names.

Support advanced filtering & partial name search.

📖 Learning Outcomes

Applied core Java concepts in a real-world use case.

Gained experience in data persistence using serialization.

Practiced modular design by separating model, service, and UI layers.
