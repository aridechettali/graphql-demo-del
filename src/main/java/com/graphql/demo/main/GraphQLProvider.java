package com.graphql.demo.main;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

@Component
public class GraphQLProvider {

    @Autowired
    GraphQLDataFetchers graphQLDataFetchers;

    private GraphQL graphQL;

    @PostConstruct
    public void init() throws IOException {
        URL book = Resources.getResource("schema/book.graphqls");
        String bookSdl = Resources.toString(book, Charsets.UTF_8);

        URL author = Resources.getResource("schema/author.graphqls");
        String authorSdl = Resources.toString(author, Charsets.UTF_8);

        GraphQLSchema graphQLSchema = buildSchema(bookSdl,authorSdl);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();

    }

    private GraphQLSchema buildSchema(String bookSdl,String authorSdl) {

        SchemaParser schemaParser = new SchemaParser();
        TypeDefinitionRegistry typeRegistry = new TypeDefinitionRegistry();
        typeRegistry.merge(schemaParser.parse(bookSdl));
        typeRegistry.merge(schemaParser.parse(authorSdl));
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query").dataFetcher("bookById", graphQLDataFetchers.getBookByIdDataFetcher()))
                .type(newTypeWiring("Book").dataFetcher("author", graphQLDataFetchers.getAuthorDataFetcher()))
                .type(newTypeWiring("Query").dataFetcher("authorById", graphQLDataFetchers.getAuthorByIdDataFetcher()))
                .type(newTypeWiring("Query").dataFetcher("findAllBooks",graphQLDataFetchers.getAllBooks()))
                .type(newTypeWiring("Mutation").dataFetcher("newBook",graphQLDataFetchers.newBook()))
                .build();
    }

    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }
}