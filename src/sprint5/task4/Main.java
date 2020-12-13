package sprint5.task4;

import java.util.Objects;

public class Main {
}

class Person {

    public Person(String firstName, String lastName, String idCode) throws IllegalArgumentException {


        try {

            setLastName(lastName);
            setFirstName(firstName);
            setIdCode(idCode);


        } catch (NameException e) {

            try {
                setIdCode(idCode);
            } catch (CodeException ex) {
                throw new IllegalArgumentException("Incorrect value " + firstName + " for firstName (should start from upper case and contains only alphabetic characters and symbols -, _); Incorrect value " + idCode + " for code (should contains exactly 10 digits)");


            }

            setLastName(lastName);
            setFirstName(firstName);

        } catch (CodeException ex) {

            throw new CodeException("Incorrect value " + idCode + " for code (should contains exactly 10 digits");

        }

    }

    static Person buildPerson(String firstName, String lastName, String idCode) throws IllegalArgumentException {

        return new Person(firstName, lastName, idCode);

    }


    private String firsName;
    private String lastName;
    private String idCode;


    public Person() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        try {
            return firsName.equals(person.firsName) &&
                    lastName.equals(person.lastName) &&
                    idCode.equals(person.idCode);
        } catch (NullPointerException e) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(firsName, lastName, idCode);
    }

    @Override
    public String toString() {
        return firsName + " " + lastName + ": " + idCode;


    }

    public String getFirsName() {
        return firsName;
    }

    public void setFirstName(String firsName) throws NameException {
        if (firsName != null)
            if (firsName.matches("[0-9a-z]*") && !firsName.matches("[-]"))
                throw new NameException("Incorrect value joe for firstName (should start from upper case and contains only alphabetic characters and -, _)");

        this.firsName = firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws NameException {


        if (lastName != null)
            if (lastName.matches("[0-9a-z ]*") ) {
                this.lastName=null;
                throw new NameException("Incorrect value " + lastName + " for firstName (should start from upper case and contains only alphabetic characters and -, _)");
            }
        this.lastName = lastName;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) throws CodeException {

        if (idCode!= null)
        if (!idCode.matches("\\d{10}"))
            throw new CodeException("Incorrect value " + idCode + " for code (should contains exactly 10 digits)");


        this.idCode = idCode;
    }
}


class NameException extends RuntimeException {
    String message;


    public NameException(String str) {
        message = str;

    }

    @Override
    public String getMessage() {
        return message;
    }
}


class CodeException extends RuntimeException {
    String message;

    public CodeException(String str) {

        message = str;
    }


    @Override
    public String getMessage() {
        return message;
    }
}