# CS212-Library-Management-System

## Project Description:
The Library Management System is a Java application that allows users to manage books and users in a library. Users can perform various operations such as adding new books and users, displaying available books, borrowing and returning books, and searching for books by title or author. It uses file handling to store data to files and retrieve data from files from the ArrayList.

## Setup and Usage:

### Prerequisites:
- Java Development Kit (JDK) installed on your machine.
- Text editor or an Integrated Development Environment (IDE) such as IntelliJ IDEA, Eclipse, or NetBeans.

### Installation:
1. Clone the project repository from GitHub or obtain the source code files.

2. Compile the Java files using the `javac` command:
   ```
   javac LibraryManagementSystem.java
   ```

### Usage:
3. Run the main class `LibraryManagementSystem` using the `java` command:
   ```
   java LibraryManagementSystem
   ```
   This will start the Library Management System program, and you can interact with it via the command-line interface.

## Key Features:
- **Book Management:** Add new books with attributes such as ID, title, author, genre, and availability status.
- **User Management:** Add new users with attributes such as ID, name, contact information, and a list of borrowed books.
- **Display Books:** View the list of available books in the library.
- **Borrowing and Returning Books:** Borrow and return books by specifying book ID and user ID.
- **Searching for Books:** Search for books by title or author and display detailed information about the books found.
- **File Handling:** Utilizes file handling to read from and write to text files (`books.txt` and `users.txt`) to store book and user information persistently.
- **Error Handling and Input Validation:** Ensures error handling and valid inputs when adding books or users, error handling is used in borrowing books and selecting options from the menu. Invalid inputs prompt the user to enter the information again. Error handling is also used in file handling methods.

## Contributing:
Contributions are welcome! Feel free to open issues or submit pull requests to improve the project.

