package io.github.ganchix.morphia.configuration;

import com.mongodb.client.MongoClient;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.annotations.Entity;
import io.github.ganchix.morphia.utils.MorphiaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * AutoConfiguration class.
 * <p>
 * Created by Rafa on 05/04/17.
 */
@Configuration
@AutoConfigureAfter(MongoAutoConfiguration.class)
public class MorphiaAutoConfiguration {

    @Autowired
    private MongoClient mongoClient;


    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public Datastore datastore() {

        Datastore datastore = Morphia.createDatastore(mongoClient, mongoTemplate.getDb().getName());

        List<String> packageNamesFromApplication = MorphiaUtils.getApplicationPackageName(applicationContext);

        Set<Class<?>> classes = packageNamesFromApplication
                .parallelStream()
                .flatMap(packageName -> MorphiaUtils.getClasses(packageName).parallelStream())
                .collect(Collectors.toSet());
        classes.parallelStream()
                .filter(clazz -> Objects.nonNull(clazz.getAnnotation(Entity.class)))
                .forEach(clazz -> datastore.getMapper().map(clazz));

        datastore.ensureCaps();
        datastore.ensureIndexes();
        return datastore;
    }


}

