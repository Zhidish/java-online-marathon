package sprint5.task1;

public class Main {

    public static void main(String[] args) {

        System.out.println( Operation.squareRectangle(1,-5));


    }
}



/*

    Create a method for calculating an area of a rectangle
        int squareRectangle(int a, int b), which should throw an IllegalArgumentException if at least one of its arguments is non positive.

        Create trySquareRectangle method which takes two parameters and calls squareRectangle to evaluate an area of a rectangle. Catch exceptions that can be generated.

        trySquareRectangle shouldn't generate any exceptions. In case when one or two parameters are non positive the method should return -1.

*/
class Operation{
    public static  int squareRectangle (int a, int b) throws IllegalArgumentException{
        //your code
        if(a<0 || b<0)
            throw new IllegalArgumentException("both arguments should be more than zero");
        return a*b;



    }

    public static int trySquareRectangle(int a, int b) {

        int result=0;
        try{

          result =   squareRectangle(a,b);

        }catch(IllegalArgumentException e ){
            return -1;

        }

        return result;
    }
}

