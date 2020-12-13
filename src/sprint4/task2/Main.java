package sprint4.task2;

import java.util.*;





class Wrapper<T>{
private T value;

}
public class Main {



    public static void main(String[] args) {



         String gh ="100";
         Integer i1= Integer.valueOf(gh);
         Integer i2= Integer.parseInt(gh);
         Integer i3 = new Integer(gh);
        Integer integer = new Integer(gh);
        Integer i4 = 100;
        Integer i5=100;

        System.out.println(i1==i2);
        System.out.println(i1==i3);
        System.out.println(i4==i5);



boolean bul= i1==i2;


    }
}


class MyUtils {
    public static class Student {
        private int id;
        private String name;


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Student student = (Student) o;
            if (name != null && student.name != null)
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
}
