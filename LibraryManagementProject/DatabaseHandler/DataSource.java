package DatabaseHandler;

import MainData.Books;
import MainData.Member;
import UIMaker.AlertMaker;
import UIMaker.TrayNotificationMaker;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ListAdder.LibraryFileSystem.getLibraryFileSystem;
import static ListAdder.MemberFileSystem.getMemberFileSystem;

public class DataSource {
    private static DataSource dataSource = new DataSource();
    public static final String DB_NAME = "library.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Java Project\\LibaryMangementSystemDatabase\\" + DB_NAME;
    // For Books Table
    public static final String TABLE_BOOK = "BOOKS";
    public static final String TABLE_BOOK_ID = "ID";
    public static final String TABLE_BOOK_TITLE = "TITLE";
    public static final String TABLE_BOOK_AUTHOR = "AUTHOR";
    public static final String TABLE_BOOK_PUBLISHER = "PUBLISHER";
    public static final String TABLE_BOOK_AVAILABLE = "AVAILABLE";
    // creating table for books.
    public static final String CREATE_TABLE_BOOK = "CREATE TABLE IF NOT EXISTS " + TABLE_BOOK + " ( " + TABLE_BOOK_ID + " INTEGER PRIMARY KEY, " + TABLE_BOOK_TITLE + " TEXT NOT NULL, " + TABLE_BOOK_AUTHOR + " TEXT, " + TABLE_BOOK_PUBLISHER + " TEXT," + TABLE_BOOK_AVAILABLE + " INTEGER) ";
    //for member table.
    public static final String TABLE_MEMBER = "MEMBERS";
    public static final String TABLE_MEMBER_ID = "ID";
    public static final String TABLE_MEMBER_USER_NAME = "NAME";
    public static final String TABLE_MEMBER_MOBILE_NUMBER = "PHONE";
    public static final String TABLE_MEMBER_EMAIL = "EMAIL";
    public static final String TABLE_MEMBER_PASSWORD = "PASSWORD";
    //creata table for members
    public static final String CREATE_TABLE_MEMBER = "CREATE TABLE IF NOT EXISTS " + TABLE_MEMBER + " ( " + TABLE_BOOK_ID + " INTEGER PRIMARY KEY ," + TABLE_MEMBER_USER_NAME + " TEXT, " + TABLE_MEMBER_MOBILE_NUMBER + " INTEGER, " + TABLE_MEMBER_EMAIL + " TEXT, " + TABLE_MEMBER_PASSWORD + " TEXT ) ";
    ////////////////////////////////
    public static final String QUERY_ID_FOR_BOOKS = "SELECT " + TABLE_BOOK_ID + " FROM " + TABLE_BOOK + " WHERE " + TABLE_BOOK_ID + " = ? ";
    public static final String noCaseSensitive = " COLLATE NOCASE ASC";
    public static final int booksNotAvailable = 0;
    public static final int booksAvailable = 1;
    private Connection connection;
    private PreparedStatement addBooksToLibrary;
    private PreparedStatement query_id_for_books;
    private static final String QUERY_BOOK = " SELECT * FROM BOOKS ";
    public static final String Add_VALUE_TO_BOOKS = "INSERT INTO BOOKS (ID, TITLE, AUTHOR, PUBLISHER, AVAILABLE) VALUES (?, ?, ?, ?, ? )";
    private PreparedStatement statementForUpdateBook;
    public static final String UPDATE_ISSUED_BOOK = "UPDATE " + TABLE_BOOK + " SET " + TABLE_BOOK_AVAILABLE + " = " + booksNotAvailable + " WHERE id = ? ";

    // For members
    private PreparedStatement query_id_for_member;
    public static final String QUERY_ID_FOR_MEMBERS = "SELECT " + TABLE_BOOK_ID + " FROM " + TABLE_MEMBER + " WHERE " + TABLE_BOOK_ID + " = ? ";
    private PreparedStatement addRegistersOfMembers;
    public static final String Add_VALUE_TO_MEMBERS = "INSERT INTO MEMBERS (" + TABLE_BOOK_ID + "," + TABLE_MEMBER_USER_NAME + ", " + TABLE_MEMBER_MOBILE_NUMBER + ", EMAIL, PASSWORD) VALUES (?, ?, ?, ?, ? )";
    private static final String QUERY_MEMBER = " SELECT * FROM MEMBERS ";

    // For Admins
    public static final String NAME = "ADMIN";
    public static final String TABLE_USERNAME = "USERNAME";
    public static final String TABLE_PASSWORD = "PASSWORD";
    public static final String CREATE_TABLE_ADMIN = "CREATE TABLE IF NOT EXISTS " + NAME + " ( " + TABLE_USERNAME + " TEXT PRIMARY KEY, " + TABLE_PASSWORD + " TEXT ) ";
    public static final String QUERY_FOR_ADMIN = "SELECT " + TABLE_USERNAME + "," + TABLE_PASSWORD + "  FROM " + NAME;
    private PreparedStatement getQuery_id_for_admins;

    // For issued Books.
    public static final String TABLE_NAME_ISSUED_BOOK = "ISSUED_BOOK";
    public static final String TABLE_ISSUED_BOOK_ID = "bookID";
    public static final String TABLE_ISSUED_BOOK_MEMBER_ID = "memberID";
    public static final String TABLE_ISSUED_BOOK_ISSUETIME = "issueTime";
    public static final String TABLE_ISSUED_BOOK_RENEW_COUNT = "renewCount";
    public static final String CHECK_ISSUED_BOOK = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_ISSUED_BOOK + " ( " + TABLE_ISSUED_BOOK_ID + " INTEGER, " + TABLE_ISSUED_BOOK_MEMBER_ID + " INTEGER, " + TABLE_ISSUED_BOOK_ISSUETIME + " TEXT , " + TABLE_ISSUED_BOOK_RENEW_COUNT + " INTEGER DEFAULT 0, FOREIGN KEY (" + TABLE_ISSUED_BOOK_ID + ") REFERENCES BOOKS(ID) , FOREIGN KEY (" + TABLE_ISSUED_BOOK_MEMBER_ID + ") REFERENCES MEMBERS(ID))";
    public static final String QUERY_ISSUED_BOOK = "INSERT INTO " + TABLE_NAME_ISSUED_BOOK + "( " + TABLE_ISSUED_BOOK_ID + ", " + TABLE_ISSUED_BOOK_MEMBER_ID + ", " + TABLE_ISSUED_BOOK_ISSUETIME + " ) VALUES ( ?, ? , DATE('now') )";
    private PreparedStatement query_issued_book;


    public static DataSource getDataSource() {
        return dataSource;
    }

    public static void main(String[] args) {
    }

    private DataSource() {

    }

    public Connection getConnection() {
        return connection;
    }

    public boolean openConnection() {
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING);
            createTableBook();
            createTableMember();
            createTableAdmin();
            createIssuedBook();
            statementForUpdateBook = connection.prepareStatement(UPDATE_ISSUED_BOOK);
            query_id_for_books = connection.prepareStatement(QUERY_ID_FOR_BOOKS);
            query_issued_book = connection.prepareStatement(QUERY_ISSUED_BOOK);
            addBooksToLibrary = connection.prepareStatement(Add_VALUE_TO_BOOKS, Statement.RETURN_GENERATED_KEYS);
            // For members.
            query_id_for_member = connection.prepareStatement(QUERY_ID_FOR_MEMBERS);
            addRegistersOfMembers = connection.prepareStatement(Add_VALUE_TO_MEMBERS, Statement.RETURN_GENERATED_KEYS);
            // For admins
            getQuery_id_for_admins = connection.prepareStatement(QUERY_FOR_ADMIN);
            System.out.println("Connection opened.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't get to the Connections.");
            return false;
        }
    }

    public void createTableBook() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(CREATE_TABLE_BOOK);
            System.out.println("Table " + TABLE_BOOK + " created. ");
        } catch (SQLException e) {
            System.out.println("Couldn't create the TABLE books.");
        }
    }

    public void createTableMember() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(CREATE_TABLE_MEMBER);
            System.out.println("Table " + CREATE_TABLE_MEMBER + " created.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't create the TABLE member.");
        }
    }

    public int addBooksToTable(int id, String title, String author, String publisher, int available) throws SQLException {
        try {
            connection.setAutoCommit(false);
            query_id_for_books.setInt(1, id);
            ResultSet results = query_id_for_books.executeQuery();
            if (results.next()) {
                AlertMaker.warningAlert(" Id Not acceptable", "Inputted id is already in the table.");
                System.out.println("Already on the table");
                return results.getInt(1);
            } else {
                addBooksToLibrary.setInt(1, id);
                addBooksToLibrary.setString(2, title);
                addBooksToLibrary.setString(3, author);
                addBooksToLibrary.setString(4, publisher);
                addBooksToLibrary.setInt(5, available);

                int rowsAffected = addBooksToLibrary.executeUpdate();
                if (rowsAffected != 1) {
                    throw new SQLException("Couldn't insert albums");
                } else {
                    System.out.println("Inserted into the table : " + TABLE_BOOK);
                    connection.commit();
                }
                ResultSet generatedKeys = addBooksToLibrary.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Couldn't get id for the books");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
        return -1;
    }

    public List<Books> viewBooks() {
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(QUERY_BOOK)) {
            List<Books> books = new ArrayList<>();
            while (resultSet.next()) {
                Books booksObj = new Books();
                booksObj.setId(resultSet.getInt(TABLE_BOOK_ID));
                booksObj.setTitle(resultSet.getString(TABLE_BOOK_TITLE));
                booksObj.setAuthor(resultSet.getString(TABLE_BOOK_AUTHOR));
                booksObj.setPublisher(resultSet.getString(TABLE_BOOK_PUBLISHER));
                booksObj.setAvailability(resultSet.getInt(TABLE_BOOK_AVAILABLE));
                books.add(booksObj);
            }
            return books;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    // For register members
    public int addMembersToTable(int id, String name, int phone, String email, String password) {
        try {
            connection.setAutoCommit(false);
            query_id_for_member.setInt(1, id);
            ResultSet results = query_id_for_member.executeQuery();
            if (results.next()) {
                AlertMaker.warningAlert("Warning", "This member id is already register.\nYou can login directly from the login page.");
                System.out.println("Already on the database.");
                return results.getInt(1);
            } else {
                addRegistersOfMembers.setInt(1, id);
                addRegistersOfMembers.setString(2, name);
                addRegistersOfMembers.setInt(3, phone);
                addRegistersOfMembers.setString(4, email);
                addRegistersOfMembers.setString(5, password);

                int affectedRow = addRegistersOfMembers.executeUpdate();
                if (affectedRow != 1) {
                    throw new SQLException("Couldn't insert into the database");
                } else {
                    AlertMaker.successAlert("Added", "Successfully added member to the database.");
                    TrayNotificationMaker.addMembers("Successfully added : " + name);
                    System.out.println("Added successfully to the database");
                    connection.commit();
                }
                ResultSet resultSet = addBooksToLibrary.getGeneratedKeys();
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else {
                    System.out.println("Couldnt get the id of the database.");
                }
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    // For admin
    public void createTableAdmin() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(CREATE_TABLE_ADMIN);
            System.out.println("Admin table created.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkAdminRegistration(String userName, String password) {
        String userNameDatabase;
        String passwordDatabase;
        try {
            ResultSet resultSet = getQuery_id_for_admins.executeQuery();
            while (resultSet.next()) {
                userNameDatabase = resultSet.getString(1);
                passwordDatabase = resultSet.getString(2);
                if (userNameDatabase.equals(userName) && passwordDatabase.equals(password)) {
                    return true;
                }
            }
            AlertMaker.errorAlert("Error", "Error while entering the text field");
            return false;

        } catch (SQLException e) {
            System.out.println("No.");
            return false;
        }
    }

    public ObservableList<Books> loadBooksToDatabase() {
        getLibraryFileSystem().getObservableList().clear();
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(QUERY_BOOK)) {
            while (resultSet.next()) {
                Books booksObj = new Books();
                booksObj.setId(resultSet.getInt(TABLE_BOOK_ID));
                booksObj.setTitle(resultSet.getString(TABLE_BOOK_TITLE));
                booksObj.setAuthor(resultSet.getString(TABLE_BOOK_AUTHOR));
                booksObj.setPublisher(resultSet.getString(TABLE_BOOK_PUBLISHER));
                booksObj.setAvailability(resultSet.getInt(TABLE_BOOK_AVAILABLE));
                getLibraryFileSystem().addBooksToTable(booksObj);
            }
            return getLibraryFileSystem().getObservableList();
        } catch (SQLException e) {
            e.printStackTrace();
            AlertMaker.errorAlert("Error", "Couldn't load data to the table.");
            return null;
        }
    }

    public ObservableList<Member> loadMembersToDatabase() {
        getMemberFileSystem().getGetMemberList().clear();
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(QUERY_MEMBER)) {
            while (resultSet.next()) {
                Member memberObj = new Member();
                memberObj.setId(resultSet.getInt(TABLE_BOOK_ID));
                memberObj.setName(resultSet.getString(TABLE_MEMBER_USER_NAME));
                memberObj.setPhone(resultSet.getInt(TABLE_MEMBER_MOBILE_NUMBER));
                memberObj.setEmail(resultSet.getString(TABLE_MEMBER_EMAIL));
                memberObj.setPassword(resultSet.getString(TABLE_MEMBER_PASSWORD));
                getMemberFileSystem().addMembersToDatabase(memberObj);
            }
            return getMemberFileSystem().getGetMemberList();
        } catch (SQLException e) {
            e.printStackTrace();
            AlertMaker.errorAlert("Error", "Couldn't load data to the table.");
            return null;
        }
    }

//    public ResultSet getAction(int id) {
//        try (Statement resultSet = connection.createStatement()) {
//            query_id_for_books.setInt(1, id);
//            return query_id_for_books.executeQuery();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    public ResultSet getAction(String query) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean getIssuedAction(String query) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // For issued Book
    public void createIssuedBook() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(CHECK_ISSUED_BOOK);
            System.out.println("ISSUED BOOK table created.");
        } catch (SQLException e) {
            System.out.println("Cannot create table named ISSUED BOOK.");
            e.printStackTrace();
        }
    }

   /* public int updateIntoBook(int bookID) {
        try {
            statementForUpdateBook.setInt(1, bookID);
            ResultSet resultSet = statementForUpdateBook.executeQuery();
            if (resultSet.next()) {
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return -1;
    }

    public int insertIntoIssuedBook(int memberID, int bookID) {
        try {
            connection.setAutoCommit(false);
            int newBookID = updateIntoBook(bookID);
            query_issued_book.setInt(1, newBookID);
            query_issued_book.setInt(2, memberID);
            ResultSet updateIssuedBook = statementForUpdateBook.executeQuery();
            if (updateIssuedBook.next()) {
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }*/

}
