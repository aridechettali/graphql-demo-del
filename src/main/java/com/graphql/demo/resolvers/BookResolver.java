package com.graphql.demo.resolvers;


import com.coxautodev.graphql.tools.GraphQLResolver;
import com.graphql.demo.main.DataRecords;
import com.graphql.demo.model.Author;
import com.graphql.demo.model.Book;



public class BookResolver implements GraphQLResolver<Book> {

    public BookResolver() {
    }

    public Author author(Book book) {
        String authorId = book.getAuthorId();
        return DataRecords.authorData
                .stream()
                .filter(author -> author.getId().equals(authorId))
                .findFirst()
                .orElse(null);

    }
}