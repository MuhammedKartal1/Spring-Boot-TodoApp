package com.project.todoapp.graphql.provider;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.coxautodev.graphql.tools.SchemaParser;
import com.project.todoapp.dto.UserDto;
import com.project.todoapp.graphql.data_fetcher.GraphQLDataFetchers;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLSchema;
import jakarta.annotation.PostConstruct;
import kotlin.text.Charsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Map;
/*
@Service
public class GraphQLProvider {
/*
    @Autowired
    GraphQLDataFetchers dataFetchers;

    private GraphQL graphQL;

    @PostConstruct
    public void init() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();

        URL resourceUrl = classLoader.getResource("schema.graphqls");
        InputStream inputStream = classLoader.getResourceAsStream("schema.graphqls");
        String sdl = null;

        if (inputStream != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charsets.UTF_8))) {
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append(System.lineSeparator());
                }
                sdl = stringBuilder.toString();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        GraphQLSchema schema = buildSchema(sdl);
        this.graphQL = GraphQL.newGraphQL(schema).build();
    }
/*
    private GraphQLSchema buildSchema(String sdl) {
        return  SchemaParser.newParser()
                .schemaString(sdl)
                .resolvers(buildQueryResolver(), buildMutationResolver())
                .build()
                .makeExecutableSchema();
    }

    private GraphQLQueryResolver buildQueryResolver() {
        return new GraphQLQueryResolver() {
            public List<UserDto> getAllUsers() {
                return dataFetchers.getAllUsersDataFetcher().get( DataFetchingEnvironmentBuilder.newDataFetchingEnvironment().build());
            }

            public UserDto getOneUser(Long userId) {
                DataFetcher fetcher = dataFetchers.getOneUserDataFetcher();
                DataFetchingEnvironment env = new DataFetchingEnvironmentBuilder().setArguments(Map.of("userId", userId)).build();
                return fetcher.get(env);
            }
        };
    }

    private GraphQLMutationResolver buildMutationResolver() {
        return new GraphQLMutationResolver() {
            public UserDto createUser(UserInput userInput) {
                DataFetcher fetcher = dataFetchers.createUserDataFetcher();
                DataFetchingEnvironment env = new DataFetchingEnvironmentBuilder().setArguments(Map.of("user", userInput)).build();
                return fetcher.get(env);
            }

            public UserDto updateOneUser(Long userId, UserInput userInput) {
                DataFetcher fetcher = dataFetchers.updateOneUserDataFetcher();
                DataFetchingEnvironment env = new DataFetchingEnvironmentBuilder().setArguments(Map.of("userId", userId, "user", userInput)).build();
                return fetcher.get(env);
            }

            public boolean deleteOneUser(Long userId) {
                DataFetcher fetcher = dataFetchers.deleteOneUserDataFetcher();
                DataFetchingEnvironment env = new DataFetchingEnvironmentBuilder().setArguments(Map.of("userId", userId)).build();
                return fetcher.get(env);
            }
        };
    }
}
*/
