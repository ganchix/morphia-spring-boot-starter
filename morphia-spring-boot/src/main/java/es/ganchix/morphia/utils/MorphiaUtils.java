package es.ganchix.morphia.utils;

import org.mongodb.morphia.utils.ReflectionUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Utils class used in autoconfiguration.
 *
 * Created by Rafael Ríos on 4/05/17.
 */
public class MorphiaUtils {

    /**
     * Return root package of spring boot application.
     *
     * @param applicationContext
     * @return list of packages
     */
    public static List<String> getApplicationPackageName(final ApplicationContext applicationContext) {

        Set<String> candidateClasses = new HashSet<>();
        candidateClasses.addAll(Arrays.asList(applicationContext.getBeanNamesForAnnotation(SpringBootApplication.class)));
        candidateClasses.addAll(Arrays.asList(applicationContext.getBeanNamesForAnnotation(EnableAutoConfiguration.class)));
        candidateClasses.addAll(Arrays.asList(applicationContext.getBeanNamesForAnnotation(ComponentScan.class)));

        if (candidateClasses.isEmpty()) {
            throw new RuntimeException("Is mandatory for the starter have @SpringBootApplication, @EnableAutoConfiguration or @ComponentScan annotation");
        } else {
            return candidateClasses.parallelStream()
                    .map(candidateClazz -> applicationContext.getBean(candidateClazz).getClass().getPackage().getName())
                    .distinct()
                    .collect(Collectors.toList());
        }

    }

    /**
     * Return classes from a package name.
     * @param packageName
     * @return list of class
     */
    public static Set<Class<?>> getClasses(final String packageName) {
        try {
            return ReflectionUtils.getClasses(packageName, true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
