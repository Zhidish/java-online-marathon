package com.softserve.itacademy;

import com.softserve.itacademy.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {


        AnnotationConfigApplicationContext annotationConfigContext = new AnnotationConfigApplicationContext(Config.class);
        User user=      annotationConfigContext.getBean(User.class);
        User user_1=     annotationConfigContext.getBean(User.class);

        System.out.println(user==user_1);




    }

}
