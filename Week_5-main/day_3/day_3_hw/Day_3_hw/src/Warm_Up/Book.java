package Warm_Up;

public class Book {

    //Problem 8
    //Static means that it belongs to the class itself instead of the individual classes/objects. This means that with the number
    // of book created, it would only count for this variable, and not for each object. This applies to methods as well.

    public static int numberOfBooksCreated = 0;

    private String title;
    private String author;

    //Constructor Default;Problem #1
    public Book() {
        this("", "");
    }

    //Constructor w/ args Problem #1
    public Book (String title, String author) {
        //This is used to refer to the current instance using this class. It allows for
        // different instances to have different titles and authors.
        InitializeBook(title, author);
    }

    //Overloaded Constructor Problem #4
    public Book (String title){
        this(title,"");
    }

    //Testing this master constructor out to see how well this works for me.
    private void InitializeBook (String title, String author) {
        this.title = title;
        this.author = author;
        numberOfBooksCreated++;
    }

    //Getters Problem #2
    public String getTitle() {
        String modifiedTitleString = "BOOK TITLE: " + title;
        return modifiedTitleString;
    }

    public String getAuthor() {
        String modifiedAuthorString = "AUTHOR: " + author;
        return modifiedAuthorString;
    }

    //Setters Problem #2
    //This is used in setters to help tell the difference between difference instances that
    //will refer to this method.
    public void setTitle(String title) {
        if (title == null || title.isEmpty() ) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        this.title = title;
    }

    public void setAuthor(String author) {
        if (author == null || author.isEmpty() ) {
            throw new IllegalArgumentException("Author cannot be null or empty");
        }
        this.author = author;
    }
    
    public static void showNumbersOfBookCreated() {
        System.out.println("This is how many books was created: " + numberOfBooksCreated + " books created.");
    }

    //Override the toString() method from #11
    @Override
    public String toString(){
        return "Title: " + title + " , author: " + author;
    }
}
