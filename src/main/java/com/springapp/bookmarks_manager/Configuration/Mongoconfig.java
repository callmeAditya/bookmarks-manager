package com.springapp.bookmarks_manager.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class Mongoconfig {

     @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(
            "mongodb+srv://mongoApplication_db_user:ydCo7NYxzfwNqhEf@dbcluster101.qeo94jc.mongodb.net/testSchema"
        );
    }

    @Bean
    public MongoDatabaseFactory mongoDatabaseFactory(MongoClient mongoClient) {
        return new SimpleMongoClientDatabaseFactory(
            mongoClient, "testSchema"
        );
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoDatabaseFactory factory) {
        return new MongoTemplate(factory);
    }

}
