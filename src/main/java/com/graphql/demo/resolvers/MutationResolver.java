package com.graphql.demo.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.graphql.demo.main.DataRecords;
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
