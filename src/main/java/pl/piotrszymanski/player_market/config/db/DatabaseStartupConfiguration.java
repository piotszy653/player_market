package pl.piotrszymanski.player_market.config.db;

import liquibase.Liquibase;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.support.DatabaseStartupValidator;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.*;
import java.util.stream.Stream;

@Configuration
public class DatabaseStartupConfiguration {

    @Bean
    public DatabaseStartupValidator databaseStartupValidator(DataSource dataSource) {
        DatabaseStartupValidator dsv = new DatabaseStartupValidator();
        dsv.setDataSource(dataSource);
        dsv.setValidationQuery(DatabaseDriver.POSTGRESQL.getValidationQuery());
        return dsv;
    }

    @Bean
    public static BeanFactoryPostProcessor dependsOnPostProcessor() {
        return bf -> {
            // Let beans that need the database depend on the DatabaseStartupValidator
            // like the JPA EntityManagerFactory or Liquibase
            String[] liquibase = bf.getBeanNamesForType(Liquibase.class);
            Stream.of(liquibase)
                    .map(bf::getBeanDefinition)
                    .forEach(it -> it.setDependsOn(appendDependsOn(it, Collections.singletonList("databaseStartupValidator"))));

            String[] jpa = bf.getBeanNamesForType(EntityManagerFactory.class);
            Stream.of(jpa)
                    .map(bf::getBeanDefinition)
                    .forEach(it -> it.setDependsOn(appendDependsOn(it, Collections.singletonList("databaseStartupValidator"))));
        };
    }

    private static String[] appendDependsOn(BeanDefinition beanDefinition, List<String> newDependsOn) {

        List<String> dependsOn = new ArrayList<>(newDependsOn);

        Optional.ofNullable(beanDefinition.getDependsOn())
                .ifPresent(depends -> dependsOn.addAll(Arrays.asList(depends)));


        return dependsOn.toArray(new String[0]);
    }
}
