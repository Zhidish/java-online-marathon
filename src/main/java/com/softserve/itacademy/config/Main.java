package com.softserve.itacademy.config;

import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
      AppContext appContext = new AppContext();
      Session session = appContext.getSessionFactory().getCurrentSession();
      System.out.println(session.isOpen());
    }
}
