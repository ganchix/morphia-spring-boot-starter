package io.github.ganchix.utils.context.normal;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import org.bson.types.ObjectId;
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
