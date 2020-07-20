package io.github.ganchix;

import dev.morphia.Datastore;
import io.github.ganchix.utils.context.normal.SpringApplicationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Rafael Ríos on 5/05/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringApplicationTest.class)
public class MorphiaAutoConfigurationTest {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void testAutoConfiguration_ShouldCreateAMorphiaAndDatastore() {
        assertThat(applicationContext.getBean(Datastore.class), notNullValue());

    }




}
