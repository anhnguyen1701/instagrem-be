package com.uet.instagrem.container;

import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.uet.instagrem.common.mongodb.entities.PostEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.uet.instagrem.common.mongodb.repositories.PostRepository;

import java.util.Collections;

@Configuration
@EnableMongoRepositories(basePackageClasses = {PostRepository.class}, mongoTemplateRef = "dataMongoTemplate")
@EntityScan(basePackageClasses = {PostEntity.class})
@EnableConfigurationProperties
public class MongoDbConfig {
    @Bean(name = "dataMongoProperties")
    @ConfigurationProperties(prefix = "mongodb")
    @Primary
    public MongoProperties mongoProperties() {
        return new MongoProperties();
    }

    @Bean(name = "dataMongoClient")
    public MongoClient mongoClient(@Qualifier("dataMongoProperties") MongoProperties mongoProperties) {
        return MongoClients.create(MongoClientSettings.builder()
                .applyToClusterSettings(builder -> builder
                        .hosts(Collections.singletonList(new ServerAddress(mongoProperties.getHost(), mongoProperties.getPort()))))
                .build());
    }

    @Primary
    @Bean(name = "dataMongoDBFactory")
    public MongoDatabaseFactory mongoDatabaseFactory(
            @Qualifier("dataMongoClient") MongoClient mongoClient,
            @Qualifier("dataMongoProperties") MongoProperties mongoProperties) {
        return new SimpleMongoClientDatabaseFactory(mongoClient, mongoProperties.getDatabase());
    }

    @Primary
    @Bean(name = "dataMongoTemplate")
    public MongoTemplate mongoTemplate(@Qualifier("dataMongoDBFactory") MongoDatabaseFactory mongoDatabaseFactory) {
        return new MongoTemplate(mongoDatabaseFactory);
    }
}
