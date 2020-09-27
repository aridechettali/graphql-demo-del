package com.graphql.demo.graphqldemo;

import graphql.execution.AsyncExecutionStrategy;
import graphql.execution.ExecutionStrategy;
import graphql.schema.GraphQLSchema;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GraphqlDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphqlDemoApplication.class, args);
	}

}