import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// Book Class
//It represents a book object with attributes such as book ID, title, author, genre, and availability status.
// It has getters and setters
class Book {
    // attributes of class
    private String book_id;
    private String Title;
    private String author;
    private String genre;
    private boolean availability_stat;

    // constructor
    // availability status is set to true initially
    public Book(String bookID, String title, String author, String genre) {
        this.book_id = bookID;
        this.Title = title;
        this.author = author;
        this.genre = genre;
        this.availability_stat = true;
    }

    // Getters and Setters
    public String getBookID() {
        return book_id;
    }

    public void setBookID(String bookid) {
        this.book_id = bookid;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isAvailabilityStatus() {
        return availability_stat;
    }

    public void setAvailabilityStatus(boolean availabilitystatus) {
        this.availability_stat = availabilitystatus;
    }
}

// User Class
// It represents a book object with attributes such as book ID, title, author,
// genre, and availability status.
// It has getters and setters
class User {
    // class attributes
    private String user_id;
    private String name;
    private String contact_info;
    private ArrayList<Book> Booksborrowed; // array list for user's borrowed books

    // constructor
    public User(String userid, String name, String contactinformation) {
        this.user_id = userid;
        this.name = name;
        this.contact_info = contactinformation;
        this.Booksborrowed = new ArrayList<>();
    }

    // Getters and Setters
    public String getUserID() {
        return user_id;
    }

    public void setUserID(String userid) {
        this.user_id = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInformation() {
        return contact_info;
    }

    public void setContactInformation(String contactinformation) {
        this.contact_info = contactinformation;
    }

    public ArrayList<Book> getBooksBorrowed() {
        return Booksborrowed;
    }

    public void setBooksBorrowed(ArrayList<Book> borrowedBooks) {
        this.Booksborrowed = borrowedBooks;
    }
}

// Library Class
// This is an important class of library management system.
// It maintains lists of books and users, loads data from files, saves data to
// files, and has many methods for adding books, adding users, searching for
// books, borrowing books, and returning books.
class Library {
    private ArrayList<Book> books; // array list for instances of Book class which represent the book information
    private ArrayList<User> users; // array list for instances of Book class which represent the book information
    // constants of string type indicating the names of file for storing user and
    // book info
    private final String BOOKS_FILE = "books.txt";
    private final String USERS_FILE = "users.txt";

    public Library() {
        // initialization of book and user array list through constructor
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
        // methods to load user and book data from file
        loadBooks();
        loadUsers();
    }

    // method for adding new books
    // it adds a new book in library if it isn't added before. It checks for the
    // existence of a book using BookExistenceCheck method based on the title,
    // author, id and genre of book
    // if book doesn't already exist it sets the availability status of book to true
    // it adds it to the books ArrayList, then saves this book data to the file
    // using saveBooks()
    // it prints a message on adding a book. If the book already exists, it prints a
    // message indicating that the book already exists in the library.
    public void book_addition(Book book) {
        if (!BookExistenceCheck(book.getBookID(), book.getTitle(), book.getAuthor(), book.getGenre())) {
            book.setAvailabilityStatus(true);
            books.add(book);
            saveBooks();
            System.out.println("Book has been added successfully to the Library.");
        } else {
            System.out.println(" This book already exists in the Library.");
        }
    }

    // it performs the same function as book_addition() method
    // it adds a new user in library if it isn't added before. It checks for the
    // existence of a user using UserExistenceCheck method based on the id, name and
    // contact info of user
    // it adds it to the users ArrayList, then saves this user data to the file
    // using saveUsers()
    // it prints a message on adding a user. If the user already exists, it prints a
    // message indicating that the user already exists in the library.
    public void user_addition(User user) {
        if (!UserExistenceCheck(user.getUserID(), user.getName(), user.getContactInformation())) {
            users.add(user);
            saveUsers();
            System.out.println("User has been added successfully in the Library.");
        } else {
            System.out.println("This user already exists in the Library.");
        }
    }

    // equalsIgnoreCase() compares two strings for equality while ignoring
    // differences in case (uppercase or lowercase).
    // If the two strings have the same characters in the same order but differ in
    // case, this method considers them equal.
    // It returns true if the two strings are equal, ignoring case; otherwise, it
    // returns false.
    // This method is useful when you want to perform string comparison in a
    // case-insensitive manner.

    // checks if the book with the specific title, id, author, genre already exists
    // in the library using enhanced for loop
    // returns true if exists, else returns false
    private boolean BookExistenceCheck(String bookid, String title, String author, String genre) {
        for (Book book : books) {
            if (book.getBookID().equalsIgnoreCase(bookid)
                    || book.getTitle().equalsIgnoreCase(title)
                    || book.getAuthor().equalsIgnoreCase(author)
                    || book.getGenre().equalsIgnoreCase(genre)) {
                return true;
            }
        }
        return false;
    }

    // checks if the user with the specific name, id, contact info already exists in
    // the library using enhanced for loop
    // returns true if exists, else returns false
    private boolean UserExistenceCheck(String userid, String name, String contactInformation) {
        for (User user : users) {
            if (user.getUserID().equalsIgnoreCase(userid)
                    || user.getName().equalsIgnoreCase(name)
                    || user.getContactInformation().equalsIgnoreCase(contactInformation)) {
                return true;
            }
        }
        return false;
    }

    // returns ArrayList of books
    public ArrayList<Book> getBooks() {
        return books;
    }

    // It loads book data from the BOOKS_FILE into the books ArrayList.
    // It reads each line from the file and creates a new Book object
    // and adds it to the books ArrayList.
    private void loadBooks() {
        try (BufferedReader br = new BufferedReader(new FileReader(BOOKS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String bookID = parts[0];
                String title = parts[1];
                String author = parts[2];
                String genre = parts[3];
                boolean availabilityStatus = Boolean.parseBoolean(parts[4]);
                Book book = new Book(bookID, title, author, genre);
                book.setAvailabilityStatus(availabilityStatus);
                books.add(book);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Books file not found. Starting with an empty library.");
        } catch (IOException e) {
            System.out.println("Error reading books file.");
        }
    }

    // It loads user data from the USERS_FILE into the users ArrayList.
    // It reads each line from the file and creates a new User object
    // and adds it to the users ArrayList.
    private void loadUsers() {
        try (BufferedReader br = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String userID = parts[0];
                String name = parts[1];
                String contactInformation = parts[2];
                // Skipping borrowed books for now
                User user = new User(userID, name, contactInformation);
                users.add(user);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Users file not found. Starting with an empty file.");
        } catch (IOException e) {
            System.out.println("Error reading users file.");
        }
    }

    // Saves the current data of the books ArrayList to the BOOKS_FILE.
    // It iterates over each book in the array list and writes its data to the file.
    private void saveBooks() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(BOOKS_FILE))) {
            for (Book book : books) {
                pw.println(book.getBookID() + "," + book.getTitle() + "," + book.getAuthor() + ","
                        + book.getGenre() + "," + book.isAvailabilityStatus());
            }
        } catch (IOException e) {
            System.out.println("Error saving books file.");
        }
    }

    // Saves the current data of the users ArrayList to the USERS_FILE.
    // It iterates over each user in the array list and writes its data to the file.
    private void saveUsers() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(USERS_FILE))) {
            for (User user : users) {
                pw.println(user.getUserID() + "," + user.getName() + "," + user.getContactInformation());
            }
        } catch (IOException e) {
            System.out.println("Error saving users file.");
        }
    }

    // Both loading and saving methods are enclosed within try-catch blocks to
    // handle potential FileNotFoundException (when the file does not exist) and
    // IOException (when there are issues reading from or writing to the file).
    // In case of an exception, an appropriate error message is printed to the
    // console
    // Searches for a book by title and displays its info if found using
    // displayBookInfo() method
    public void searchBooksByTitle(String title) {
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Book found in Library:");
                displayBookInfo(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Book not found in Library.");
        }
    }

    // Searches for a book by author and displays its info if found using
    // displayBookInfo() method
    public void searchBooksByAuthor(String author) {
        boolean found = false;
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                System.out.println("Book found in Library:");
                displayBookInfo(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Book not found in Library.");
        }
    }

    // method to borrow a book
    // it searches for a book with the specified id by iterating over the books
    // array list
    // if the book is found and its status is true, it sets the status to falsw
    // it then searches for the user with the specified id in the users list
    // if the user is found, it adds the book to the users array list of borrowed
    // books
    // it then displays if the book is borrowed, user isn't found, book isn't found
    // or available accordingly
    public void borrowBook(String bookID, String userID) {
        Book foundBook = null;
        for (Book book : books) {
            if (book.getBookID().equals(bookID)) {
                foundBook = book;
                break;
            }
        }
        if (foundBook != null && foundBook.isAvailabilityStatus()) {
            // Book is available
            foundBook.setAvailabilityStatus(false);
            // Now find the user and add the book to their borrowed books
            for (User user : users) {
                if (user.getUserID().equals(userID)) {
                    user.getBooksBorrowed().add(foundBook);
                    System.out.println("Book borrowed successfully.");
                    return;
                }
            }
            System.out.println("User not found in Library.");
        } else if (foundBook != null) {
            System.out.println("This book is not available for borrowing at the moment.");
        } else {
            System.out.println("Book not found in Library.");
        }
    }

    // Return a borrowed book
    // It iterates through the users ArrayList to find the user with the specified
    // userID.
    // If the user is found, it iterates through the user's list of borrowed books
    // to find the book with the specified book id.
    // If the book is found, it sets the book's status to true.
    // It then removes the returned book from the user's list of borrowed books.
    // it prints a message indicating whether the book was returned successfully or
    // book not found in the user's borrowed list or user not found accordingly
    public void returnBook(String bookID, String userID) {
        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                for (Book book : user.getBooksBorrowed()) {
                    if (book.getBookID().equals(bookID)) {
                        book.setAvailabilityStatus(true);
                        user.getBooksBorrowed().remove(book);
                        System.out.println("Book returned successfully.");
                        return;
                    }
                }
                System.out.println("Book not found in user's borrowed list.");
                return;
            }
        }
        System.out.println("User not found in Library.");
    }

    // Display book information
    private void displayBookInfo(Book book) {
        System.out.println("Book ID: " + book.getBookID());
        System.out.println("Book Title: " + book.getTitle());
        System.out.println("Book Author: " + book.getAuthor());
        System.out.println("Book Genre: " + book.getGenre());
        System.out.println(
                "Book's Availability Status: " + (book.isAvailabilityStatus() ? "Available" : "Not Available"));
        System.out.println();
    }
}

// Main Class
public class LibraryManagementSystem {
    // it allows user to interact with the system4
    // It offers options to add books, add users, display books, borrow books,
    // return books, and search for books.
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        int choice;
        // The main method initializes an instance of the Library class and a Scanner
        // object for user input.
        // It uses case statement to handle errors
        // It presents a menu of options to the user, such as adding a book, adding a
        // user, displaying books, borrowing a book, returning a book, searching for a
        // book, and exiting the program.

        do {
            System.out.println("Welcome to Library Management System:");
            System.out.println("1. Add a Book");
            System.out.println("2. Add a User");
            System.out.println("3. Display Books");
            System.out.println("4. Borrow a Book");
            System.out.println("5. Return Book");
            System.out.println("6. Search for a Book");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addBooks(library, scanner);
                    break;
                case 2:
                    addUsers(library, scanner);
                    break;
                case 3:
                    displayBooks(library);
                    break;
                case 4:
                    borrowBooks(library, scanner);
                    break;
                case 5:
                    returnBooks(library, scanner);
                    break;
                case 6:
                    searchBooks(library, scanner);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter again.");
            }
        } while (choice != 0);

        scanner.close();
    }

    // This method allows the user to add a new book to the library.
    // It asks the user to enter the book ID, title, author, and genre.
    // Input validation is performed to ensure that the user provides valid input
    // i.e. non-empty strings without numbers.
    // After validated, it creates a new Book object with the provided information
    // and adds it to the library using the book_addition method of the Library
    // class.
    private static void addBooks(Library library, Scanner scanner) {
        System.out.print("Enter Book ID: ");
        String bookID = scanner.nextLine();
        System.out.print("Enter Title: ");
        String title = validateInput(scanner);
        System.out.print("Enter Author: ");
        String author = validateInput(scanner);
        System.out.print("Enter Genre: ");
        String genre = validateInput(scanner);

        Book book = new Book(bookID, title, author, genre);
        library.book_addition(book);
    }

    // This is a method used for input validation.
    // It takes a Scanner object as input and prompts the user for input until valid
    // input is provided.
    // Valid input is defined as a non-empty string without any numbers.
    // Once valid input is provided, it returns the input string.
    private static String validateInput(Scanner scanner) {
        String input;
        while (true) {
            input = scanner.nextLine();
            if (!input.isEmpty() && !input.matches(".*\\d.*")) {
                return input;
            }
            System.out.println("Input cannot be left blank or contain numbers. Please enter again:");
        }
    }

    // this method is similar to addBooks method
    private static void addUsers(Library library, Scanner scanner) {
        System.out.print("Enter User ID: ");
        String userID = scanner.nextLine();
        System.out.print("Enter User Name: ");
        String name = validateInput(scanner);
        System.out.print("Enter User Contact Information: ");
        String contactInformation = validateNumericInput(scanner);

        User user = new User(userID, name, contactInformation);
        library.user_addition(user);
    }

    // This is a method used for validating numeric input, specifically for contact
    // information.
    // It takes a Scanner object as input and prompts the user for input until valid
    // numeric input is provided.
    // Valid input is defined as a non-empty string containing only digits.
    // Once valid input is provided, it returns the input string.
    private static String validateNumericInput(Scanner scanner) {
        String input;
        while (true) {
            input = scanner.nextLine();
            if (!input.isEmpty() && input.matches("\\d+")) {
                return input;
            }
            System.out
                    .println("Contact information must contain only numbers and cannot be empty. Please enter again:");
        }
    }

    // This method displays all the books currently available in the library.
    // It iterates through the list of books obtained from the getBooks method of
    // the Library class and prints out information about each book, such as ID,
    // title, author, genre, and availability status.
    private static void displayBooks(Library library) {
        System.out.println("Books in the Library:");
        for (Book book : library.getBooks()) {
            System.out.println("Book ID: " + book.getBookID());
            System.out.println("Book Title: " + book.getTitle());
            System.out.println("Book Author: " + book.getAuthor());
            System.out.println("Book Genre: " + book.getGenre());
            System.out.println(
                    "Book's Availability Status: " + (book.isAvailabilityStatus() ? "Available" : "Not Available"));
            System.out.println();
        }
    }

    // This method allows the user to borrow a book from the library.
    // It prompts the user to enter the book ID and user ID.
    // It then calls the borrowBook method of the Library class, passing the book ID
    // and user ID as arguments, to perform the borrowing operation.
    private static void borrowBooks(Library library, Scanner scanner) {
        System.out.print("Enter Book ID: ");
        String bookID = scanner.nextLine();
        System.out.print("Enter User ID: ");
        String userID = scanner.nextLine();
        library.borrowBook(bookID, userID);
    }

    // similar functionality as of the above method
    private static void returnBooks(Library library, Scanner scanner) {
        System.out.print("Enter Book ID: ");
        String bookID = scanner.nextLine();
        System.out.print("Enter User ID: ");
        String userID = scanner.nextLine();
        library.returnBook(bookID, userID);
    }

    // This method allows the user to search for books by title or author.
    // It presents a sub-menu with options to search by title or author.
    // Based on the user's choice, it prompts the user to enter the title or author
    // name.
    // It then calls the corresponding search method (searchBooksByTitle or
    // searchBooksByAuthor) of the Library class to perform the search operation.
    private static void searchBooks(Library library, Scanner scanner) {
        System.out.println("Search Books Menu:");
        System.out.println("1. Search by Title");
        System.out.println("2. Search by Author");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter Title: ");
                String title = scanner.nextLine();
                library.searchBooksByTitle(title);
                break;
            case 2:
                System.out.print("Enter Author: ");
                String author = scanner.nextLine();
                library.searchBooksByAuthor(author);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}
