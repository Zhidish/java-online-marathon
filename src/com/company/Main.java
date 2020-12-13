package com.company;

import java.util.*;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
       /* Map<String ,String > map = new HashMap<>();
        map.put("1",null);
        map.put("2",null);
        map.put("3",null);
        map.put("4",null);
        map.put(null,null);
        map.put(null ,"");
        map.put("234234214","Rostya");
        map.put("234234834","ROstya");
        map.put("234234434","Rostya");
        map.put("234234234","Rostya");
        map.put("234234224","Rostik");
        map.put("2342342288"," ");
        map.put("2342342218","");


        Map<String,String> m = new HashMap<>();
        m.put("0984578976","Ivan");
        m.put("0432424423","Ivan");
        m.put("0432424343423","Ivar");
        m.put("0434244363","Ivar");
        m.put(null,null);
        m.put(null,"Yura");
        m.put("","Ivan");

        MyUtils my = new MyUtils();

        Map<String,List<String >>b = my.createNotebook(m);
        System.out.println(b.toString());

   Map<String,List<String>> listMap=     new MyUtils().createNotebook(map);*/


        String str = new String("");

        System.out.println(str=="");


    }
}
/*
class MyUtils {
    public static class Student {
        private int id;
        private String name;


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Student student = (Student) o;
            if(name!=null && student.name!=null)
            return id == student.id &&
                 name.equals(student.name);
            else return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }

        public Student() {
        }

        public Student(int id, String name) {
            this.id = id;
            this.name = name;
        }


    }

    public Set<Student> commonStudents(List<Student> list1, List<Student> list2) {
        Set<Student> set = new HashSet<>();


        for (Student st_1 : list1) {

            for (Student st_2 : list2) {
                if (st_1.equals(st_2)) {
                    set.add(st_1);

                }

            }


        }

        return set;
    }
}*/


class MyUtils {
    public   Map<String,List<String>> createNotebook(Map<String, String> phones) {

        if (phones == null) {
            return null;
        }


        Map<String, List<String>> map = new HashMap<>();
        Set<String> set = new HashSet<>();

        for (Map.Entry<String, String> entry : phones.entrySet()) {
                if (entry.getValue() != null && entry.getKey() != null && !entry.getKey().isEmpty())
                    set.add(entry.getValue());
                else {
                    set.add(entry.getValue());
                }
        }

        for (String str : set) {
            List<String> list_1 = new ArrayList<>();
            for (Map.Entry<String, String> entry : phones.entrySet()) {
                if (entry.getValue() != null)
                    if ((entry.getValue().equals(str) && entry.getKey() != null )) {
                        list_1.add(entry.getKey());
                    }

            }
            map.put(str, list_1);
        }
        List<String> list_1 = new ArrayList<>();

        for (Map.Entry<String, String> entry : phones.entrySet()) {
            if (entry.getValue() == null &&  entry.getKey() != null && !entry.getKey().isEmpty()) {
                list_1.add(entry.getKey());
            }
        }

        map.put(null, list_1);
        return map;
    }
}






/*

interface DrinkReceipt {
    String getName();

    void addComponent(String componentName, int componentCount);
}

interface DrinkPreparation {
    Map<String, Integer> makeDrink();
}

interface Rating {
    int getRating();
}

class Caffee implements DrinkReceipt, DrinkPreparation, Rating {
    String name;

    void setName(String name) {
        this.name = name;
    }

    int rating;

    void setRating(int rating) {

        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    Map<String, Integer> ingredients;

  public   Map<String, Integer> makeDrink() {
        return ingredients;

    }


    public void addComponent(String componentName, int componentCount) {
        ingredients.put(componentName, componentCount);


    }
}

class Espresso extends Caffee {
    // Code
}

class Cappuccino extends Caffee {
    // Code
}

class MyUtils {
    public Map<String, Double> averageRating(List<Caffee> coffees) {
        int count = 0;
        int rate = 0;


        Map<String, Double> map = new HashMap();


        Set<String> set = new HashSet();
        for (Caffee caffe : coffees) {
            set.add(caffe.getName());
        }

        for (String set_1 : set) {

            for (Caffee caffee : coffees) {
                if (set_1.equals(caffee.getName())) {
                    count++;
                    rate += caffee.getRating();


                }

            }
            map.put(set_1, (double) rate / count);
            count = rate = 0;


        }
        return map;


    }

}
*/
