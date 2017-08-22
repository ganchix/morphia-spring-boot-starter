# Spring boot starter Morphia [![Build Status](https://travis-ci.org/ganchix/morphia-spring-boot-starter.svg?branch=master)](https://travis-ci.org/ganchix/morphia-spring-boot-starter) [![codecov](https://codecov.io/gh/ganchix/morphia-spring-boot-starter/branch/master/graph/badge.svg)](https://codecov.io/gh/ganchix/morphia-spring-boot-starter) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/es.ganchix/morphia-spring-boot-parent/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/es.ganchix/morphia-spring-boot-parent) [![GitHub stars](https://img.shields.io/github/stars/badges/shields.svg?style=social&label=Star)](https://github.com/ganchix/morphia-spring-boot-starter)

Spring boot starter for use [Morphia](https://mongodb.github.io/morphia/) in a Spring way easily.

# Table of Contents
 
- [Overview](#overview)
- [Getting started](#getting-started)
- [License](#license)


### Overview

This implementation offers a Spring way to use [Morphia](https://mongodb.github.io/morphia/) framework, 
we use the configuration of Spring Data Mongo to instantiate Morphia and Datastore objects, both objects can be 
injected.

The starter scan Entity annotations to add in datastore and ensure indexes.


### Getting started
#### Code example

First of all need to configure your properties like Spring Data Mongo, a example of `application.properties` :

```
spring.data.mongodb.uri=mongodb://user:password@ip:port/database
spring.data.mongodb.repositories.enabled=true
```

Create your domain classes, for example:

```java
import lombok.Data;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Data
@Entity
public class Account {
    
    @Id
    private ObjectId id;
    private String user;
    private Double amount;
    private Long createdDate;
    
}
```

Create your repositories:

```java
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
    
@Component
public class AccountRepositoryImpl implements AccountRepository {
    
    @Autowired
    private Datastore datastore;
    
    @Override
    public void delete(String user) {
        Query<Account> removeQuery = datastore.createQuery(Account.class)
                    .filter("user", user);
        datastore.delete(removeQuery);
    }
}
```


#### Integration using `@SpringBootApplication` or `@EnableAutoConfiguration` 

Only add Maven dependency:

```xml
<dependency>
    <groupId>io.github.ganchix</groupId>
    <artifactId>morphia-spring-boot-starter</artifactId>
    <version>LATEST_VERSION</version>
</dependency>

```

#### Without `@SpringBootApplication` or `@EnableAutoConfiguration` 

You need a `@ComponentScan` in the root package.

Add Maven dependency:

```xml
<dependency>
    <groupId>io.github.ganchix</groupId>
    <artifactId>morphia-spring-boot</artifactId>
    <version>LATEST_VERSION</version>
</dependency>

```

Import auto configuration `MorphiaAutoConfiguration`:

```java
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
    
@Configuration
@Import(MorphiaAutoConfiguration.class)
public class MorphiaConfig {
    
}
```


### License

Spring boot starter Morphia is licensed under the MIT License. See [LICENSE](LICENSE.md) for details.

Copyright (c) 2017 Rafael Ríos Moya