package sprint5.task2;

public class Main {

    public static void main(String[] args) throws TypeException, ColorException {


        new Plant("ORDINARY", "WHITE", "KOKOKOKOKO");


    }
}


class Plant {
    private String name;
    private Color color;
    private Type type;

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    public Plant(String str) {
        name = str;


    }

    public Plant(String type, String color, String name) throws ColorException, TypeException {

if(type!=null)
        try {
            Type.valueOf(type.toUpperCase());
            this.type = Type.valueOf(type.toUpperCase());

        } catch (IllegalArgumentException y) {

            try {
                Color.valueOf(color.toUpperCase());

            } catch (IllegalArgumentException e) {

                throw new ColorException("Invalid value " + color + " for field color");

            }


            throw new TypeException("Invalid value " + type + " for field type");


        }



        if(color!=null)
        try {

            Color.valueOf(color.toUpperCase());
            this.color = Color.valueOf(color.toUpperCase());

        } catch (IllegalArgumentException e) {


            try {
                Type.valueOf(type.toUpperCase());

            } catch (IllegalArgumentException y) {

                throw new TypeException("Invalid value " + type + " for field type");

            }


            throw new ColorException("Invalid value " + color + " for field color");

        }








        this.name = name;


    }

    @Override
    public String toString() {
        return "Plant{" + "type: " + type +
                ", color: " + color +
                ", name: " + name  +
                '}';

    }


}


enum Color {
    WHITE, RED, BLUE;


}

enum Type {
    RARE, ORDINARY;

}


class ColorException extends Exception {

    private String message;


    public ColorException(String str) {
        message = str;


    }


    @Override
    public String getMessage() {
        return message;
    }

}

class TypeException extends Exception {
    private String message;


    public TypeException(String str) {
        message = str;

    }


    @Override
    public String getMessage() {
        return message;
    }
}

