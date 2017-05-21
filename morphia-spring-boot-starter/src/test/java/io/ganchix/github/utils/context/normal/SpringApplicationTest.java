package io.ganchix.github.utils.context.normal;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringApplicationTest {

}

@Entity
class Account {

    @Id
    private ObjectId id;
    private String user;
    private Double amount;
    private Long createdDate;

}
