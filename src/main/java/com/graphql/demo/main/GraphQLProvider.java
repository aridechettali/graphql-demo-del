package com.graphql.demo.main;


import com.graphql.demo.resolvers.BookResolver;
import com.graphql.demo.resolvers.MutationResolver;
import com.graphql.demo.resolvers.QueryResolver;

import graphql.execution.AsyncExecutionStrategy;
import graphql.execution.ExecutionStrategy;
import graphql.schema.GraphQLSchema;
import com.coxautodev.graphql.tools.SchemaParser;


import graphql.servlet.SimpleGraphQLServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServlet;


@Component
public class GraphQLProvider {

    @Bean
    public ServletRegistrationBean buildSchema() {

        GraphQLSchema schema  = SchemaParser.newParser()
                .resolvers(new BookResolver(), new MutationResolver(), new QueryResolver())
                .file("schema/book.graphqls")
                .file("schema/author.graphqls")
                .build().makeExecutableSchema();
        ExecutionStrategy executionStrategy = new AsyncExecutionStrategy();
        HttpServlet servlet = new SimpleGraphQLServlet(schema, executionStrategy);
        ServletRegistrationBean bean = new ServletRegistrationBean(servlet, "/graphql");
        return bean;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("*").allowedOrigins("http://localhost:*");
            }
        };
    }
}