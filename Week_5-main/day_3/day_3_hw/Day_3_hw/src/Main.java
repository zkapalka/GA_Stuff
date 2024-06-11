import Warm_Up.Book;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //Problem 5
        Book book1 = new Book();
        System.out.println(book1.getTitle());
        System.out.println(book1.getAuthor());
        System.out.println();

        Book book2 = new Book("Lord of The Rings");
        System.out.println(book2.getTitle());
        System.out.println(book2.getAuthor());
        System.out.println();

        Book book3 = new Book("Throne of Glass", "Maas");
        System.out.println(book3.getTitle());
        System.out.println(book3.getAuthor());
        System.out.println();

        book3.setTitle("Harry Potter");
        System.out.println(book3.getTitle());

        //Problem 8 and 10
        System.out.println();
        Book.showNumbersOfBookCreated();
        System.out.println();

        // #Problem 7, This will help to test the setTitle method
        //book3.setTitle("");

        //Problem 9
        ArrayList<Book> myBookList = new ArrayList<>();

        Book book4 = new Book("Wheel of Time", "Robert Jordan");
        Book book5 = new Book("Dune", "Frank Herbert");
        Book book6 = new Book("Mistborn", "Brandon Sanderson");
        myBookList.add(book4);
        myBookList.add(book5);
        myBookList.add(book6);

        System.out.println("Problem 9");
        //Printing out myBookList arraylist
        for (Book book: myBookList) {
            System.out.println(book.getTitle() + " " + book.getAuthor());
        }

        //Problem #11 using Override
        System.out.println(book3);

        System.out.println();
        System.out.println("Problem 12");
        Library collections = new Library();

        collections.addBook(new Book("Java Programming", "John Smith"));
        collections.addBook(new Book("Data Structures and Algorithms", "Jane Doe"));
        collections.addBook(new Book("Machine Learning", "Alice Johnson"));

        List<Book> allBooks = collections.getAllBooks();
        for (Book book : allBooks) {
            System.out.println(book);
        }

    }
}
