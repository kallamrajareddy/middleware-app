package com.kallam.middleware.configuration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import com.kallam.middleware.helper.MongoLocalDateTimeToStringConverter;
import com.kallam.middleware.helper.StringToMongoLocalDateTimeConverter;
import com.mongodb.MongoClient;

@Configuration
public class MongoConfiguration extends AbstractMongoConfiguration {

    @Value("${spring.data.mongodb.database:pawnbrokers}")
    String database;

    @Value("${spring.data.mongodb.host:localhost}:${spring.data.mongodb.port:27017}")
    String host;

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Bean
    @Override
    public MongoCustomConversions customConversions() {
        List<Converter<?, ?>> converterList = new ArrayList<>();
       // converterList.add(new ZonedDateTimeReadConverter());
        //converterList.add(new ZonedDateTimeWriteConverter());
        converterList.add(new MongoLocalDateTimeToStringConverter());
        converterList.add(new StringToMongoLocalDateTimeConverter());

        return new MongoCustomConversions(converterList);
    }

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(host);
    }
    

}