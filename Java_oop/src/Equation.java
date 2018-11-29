import java.io.Serializable;
import java.util.ArrayList;

/**
 * Write abt your class
 * @author Carraig
 */
public class Equation implements calculate, Serializable {
    /**
     *An ArrayList that number the operators in the equation
     */
    private ArrayList<Double> number;
    /**
     *An ArrayList that stores the operators in the equation
     */
    private ArrayList<String> operator;
    /**
     * Equation the math problem to be solved
     */
    private String equation;
    /**
     *Result is the answer to the equation
     */
    private double result;
    /**
     *resultAsString result as type String
     */
    private String resultAsString;

    /**
     *Constructs an equation object
     * @param equation math problem in string format
     */
    public Equation(String equation){

        this.equation = equation;

        operator = operatorReturn();
        number = numberReturn();

        System.out.println(number);
        System.out.println(operator);

        result = calculate();

        resultAsString = Double.toString(result);
        System.out.println(number + resultAsString);
    }

    /**
     *Gets equation object information
     * @return the equation and its solution as string
     */
    public String toString()
    {
       return "\n" + this.equation + " = " + this.resultAsString + "\n";
    }

    /**
     *Gets the result as a String
     * @return solution as string
     */
    public String getResultAsString() {
        return resultAsString;
    }

    /**
     *Gets numbers and adds to array list
     * @return the numbers in the equation
     */
    //Method number return written with the assistance of john brosnan
    public ArrayList<Double> numberReturn() {

        String result= this.equation ;
        String tempstr = "";
        Double tempdble;
        ArrayList<Double> equationNumber = new ArrayList<>();


        int startValue = 0;
        for (int i = startValue; i < result.length(); i++) {

            int j;
            for (j = startValue; j < result.length(); j++) {
                if (Character.isDigit(result.charAt(j)) || result.charAt(j) == '.')
                {
                    tempstr += result.charAt(j);
                }

                else {

                    break;
                }


                }

            startValue = j+1;

            if (!tempstr.equals("")) {
                tempdble = Double.parseDouble(tempstr);

                equationNumber.add(tempdble);

                tempstr = "";

            }
        }

        return equationNumber;
    }


    /**
     *Gets operators and adds to array list
     * @return the operators in the equation
     */
    public ArrayList<String> operatorReturn() {

        String result = this.equation ;
        String tempstr;

        ArrayList<String> equationOperator = new ArrayList<>();

        for (int i=0; i<result.length();i++) {
            if (!Character.isDigit(result.charAt(i))) {
                tempstr = "" + result.charAt(i);
                equationOperator.add(tempstr);
            }
        }

        return equationOperator;

    }

    /**
     *Performs calculation based on operator and number array lists
     * @return the answer to the equation
     */
    public double calculate() {
        double answer = number.get(0);

        for(int i=0;i<this.operator.size();i++ )
        {
            if(operator.get(i).equals("+")){
                answer = number.get(i) + number.get(i+1);
                number.set(i+1,answer);
                System.out.println(number.get(i+1));
            }

            else if(operator.get(i).equals("x")){
                answer = number.get(i) * number.get(i+1);
                number.set(i+1,answer);
                System.out.println(number.get(i+1));
            }

            else if(operator.get(i).equals("-")){
                answer = number.get(i) - number.get(i+1);
                number.set(i+1,answer);
                System.out.println(number.get(i+1));
            }

            else if(operator.get(i).equals("\u00F7")){
                answer = number.get(i)/number.get(i+1);
                number.set(i+1,answer);
                System.out.println(number.get(i+1));
            }
        }

        return answer;
    }

}