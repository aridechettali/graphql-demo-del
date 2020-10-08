package com.graphql.demo.server.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.graphql.demo.server.util.DataRecords;
import com.graphql.demo.model.Book;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MutationResolver implements GraphQLMutationResolver {

  public Book newBook(String id, String name, String authorId, Integer pageCount) {
    Book book = new Book(id,name,authorId,pageCount);
    DataRecords.bookData.add(book);
    return book;
  }
}
