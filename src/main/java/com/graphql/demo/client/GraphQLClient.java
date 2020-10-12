package com.graphql.demo.client;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class GraphQLClient {
    public static void main(String[] args) {
        ApolloClient apolloClient = ApolloClient.builder()
                .serverUrl("http://127.0.0.1:8080/graphql")
                .build();

        apolloClient.query(new BookDetailQuery("book-1")).enqueue(new ApolloCall.Callback<Optional<BookDetailQuery.Data>>() {
            @Override
            public void onResponse(@NotNull Response<Optional<BookDetailQuery.Data>> response) {
                BookDetailQuery.Data bookData = response.getData().get();
                System.out.println(bookData.getBookById().get().id.get());
                System.out.println(bookData.getBookById().get().name.get());
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
            }
        });

        apolloClient.query(new BookDetailsQuery()).enqueue(new ApolloCall.Callback<Optional<BookDetailsQuery.Data>>() {
            @Override
            public void onResponse(@NotNull Response<Optional<BookDetailsQuery.Data>> response) {

                List<BookDetailsQuery.FindAllBook> books = response.getData().get().getFindAllBooks().get();
                for(BookDetailsQuery.FindAllBook book : books) {
                    System.out.println (book.id.get() +" "+ book.name.get() + " " + book.pageCount.get() +" "+ book.author.get());

                }
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });
    }
}
