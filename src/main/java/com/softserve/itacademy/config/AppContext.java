package com.softserve.itacademy.config;

import com.softserve.itacademy.model.*;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.mapping.MetadataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
public class AppContext {
@Autowired
    static StandardServiceRegistry registry;
@Autowired
    static SessionFactory sessionFactory;

    @Bean
   SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            try {
                registry = new StandardServiceRegistryBuilder()
                        .configure("src/main/resources/hibernate.cfg.xml")
                        .build();
                Metadata metadata = new MetadataSources(registry)
                        .addAnnotatedClass(Role.class)
                        .addAnnotatedClass(State.class)
                        .addAnnotatedClass(Task.class)
                        .addAnnotatedClass(ToDo.class)
                        .addAnnotatedClass(User.class).buildMetadata();

                sessionFactory = metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
                e.printStackTrace();
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }

        return sessionFactory;
    }

}
