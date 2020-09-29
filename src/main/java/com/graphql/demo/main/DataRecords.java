package com.graphql.demo.main;

import com.graphql.demo.model.Author;
import com.graphql.demo.model.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataRecords {

    public static List<Book> bookData = getBookData();
    public static List<Book> getBookData() {
        List<Book> bookData = new ArrayList<>();
        bookData.add(new Book("book-1","Harry Potter and the Philosopher's Stone","author-1",223));
        bookData.add(new Book("book-2","Moby Dick","author-2",635));
        bookData.add(new Book("book-3","Interview with the vampire","author-3",371));
        return bookData;
    }

    public static List<Author> authorData = getAuthorData();
    public static List<Author> getAuthorData() {
        List<Author> authorData = new ArrayList<>();
        authorData.add(new Author("author-1","Joanne","Rowling"));
        authorData.add( new Author("author-2","Herman","Melville"));
        authorData.add(new Author("author-3","Anne","Rice"));
        return authorData;
    }
}


