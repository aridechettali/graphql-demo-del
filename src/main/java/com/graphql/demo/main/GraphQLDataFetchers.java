package com.graphql.demo.main;

import com.graphql.demo.model.Author;
import com.graphql.demo.model.Book;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class GraphQLDataFetchers {

    private static List<Book> bookData = getBookData();
    public static List<Book> getBookData() {
        List<Book> bookData = new ArrayList<>();
        bookData.add(new Book("book-1","Harry Potter and the Philosopher's Stone","author-1",223));
        bookData.add(new Book("book-2","Moby Dick","author-2",635));
        bookData.add(new Book("book-3","Interview with the vampire","author-3",371));
        return bookData;
    }

    private static List<Author> authorData = getAuthorData();
    public static List<Author> getAuthorData() {
        List<Author> authorData = new ArrayList<>();
        authorData.add(new Author("author-1","Joanne","Rowling"));
        authorData.add( new Author("author-2","Herman","Melville"));
        authorData.add(new Author("author-3","Anne","Rice"));
        return authorData;
    }

    public DataFetcher getBookByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String bookId = dataFetchingEnvironment.getArgument("id");
            return bookData
                    .stream()
                    .filter(book -> book.getId().equals(bookId))
                    .findFirst()
                    .orElse(null);
        };
    }

    public DataFetcher getAllBooks() {
        return dataFetchingEnvironment -> {
            return bookData;
        };
    }


    public DataFetcher newBook() {
        return dataFetchingEnvironment -> {
            String id = dataFetchingEnvironment.getArgument("id");
            String name = dataFetchingEnvironment.getArgument("name");
            String authorId = dataFetchingEnvironment.getArgument("authorId");
            int pageCount = dataFetchingEnvironment.getArgument("pageCount");
            Book book = new Book(id,name,authorId,pageCount);
            bookData.add(book);
            return book;
        };
    }

    public DataFetcher getAuthorDataFetcher() {
        return dataFetchingEnvironment -> {
           Book book = dataFetchingEnvironment.getSource();
            String authorId = book.getAuthorId();
            return authorData
                    .stream()
                    .filter(author -> author.getId().equals(authorId))
                    .findFirst()
                    .orElse(null);
        };
    }

    public DataFetcher getAuthorByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String authorId = dataFetchingEnvironment.getArgument("id");
            return authorData
                    .stream()
                    .filter(author -> author.getId().equals(authorId))
                    .findFirst()
                    .orElse(null);
        };
    }
}


