import Warm_Up.Book;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private ArrayList<Book> books;

    //Constructor
    public Library() {
        books = new ArrayList<Book>();
    }

    //Method to add a single book to BookCollection
    public void addBook(Book book) {
        books.add(book);
    }

    //Method to add multiple books to BookCollection
    public void addBooks(Book... newBooks){
        for(Book book: newBooks){
            books.add(book);
        }
    }

    //Method to get all the books in the BookCollection
    public List<Book> getAllBooks() {
        return books;
    }





}
