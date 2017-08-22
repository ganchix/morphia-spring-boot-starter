package io.github.ganchix.morphia.utils;

import io.github.ganchix.morphia.utils.context.error.SpringApplicationAcceptNoSpringBootApplication;
import io.github.ganchix.morphia.utils.context.normal.SpringApplicationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Rafael Ríos on 5/05/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class MorphiaUtilsTest {

    @Test(expected = Exception.class)
    public void testWhenNoApplicationAnnotation_ShouldReturnAException() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringApplicationAcceptNoSpringBootApplication.class);
        MorphiaUtils.getApplicationPackageName(context);
    }

    @Test
    public void testCallGetPackageName_ShouldReturnAPackageName() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringApplicationTest.class);
        List<String> applicationPackageName = MorphiaUtils.getApplicationPackageName(context);
        assertThat(applicationPackageName.size(), is(1));
        assertThat(applicationPackageName.get(0), is("io.github.ganchix.morphia.utils.context.normal"));
    }

    @Test
    public void testCallGetClasses_ShouldReturnAClassesInPackage() {
        Set<Class<?>> classes = MorphiaUtils.getClasses("io.github.ganchix.morphia.utils.context.normal");
        assertThat(classes.size(), is(1));

    }
    @Test
    public void testCallGetClasses_ShouldReturnAClassesInPackage1() {
        Set<Class<?>> classes = MorphiaUtils.getClasses("io.github.ganchix.morphia.utils.context.normal.NotClass.class");
    }


}
