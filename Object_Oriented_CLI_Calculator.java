import java.util.Scanner;

/**
 * @author Douglas Johnston
 *
 * The type Object oriented cli calculator.
 */
public class Object_Oriented_CLI_Calculator {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        char calculation;
        char operation;
        int x1;
        int x2;
        int base;
        //CLI
        System.out.println("Type h for hex or b for binary: ");
        calculation = s.next().charAt(0);
        if(calculation == 'h') {
            base = 16;
        } else if(calculation == 'b'){
            base = 2;
        } else {
            System.out.println("That argument is not valid");
            base = 0;
            System.exit(0);
        }
        System.out.println("Type the operation you want to do (+, -, *, /): ");
        operation = s.next().charAt(0);
        System.out.println("Type the first number to preform the operation on: ");
        String input = s.next();
        x1 = input.contains("-") ? -Integer.parseUnsignedInt(input.replace("-", ""), base) : Integer.parseUnsignedInt(input, base);

        System.out.println("Type the second number to preform the operation on: ");
        input = s.next();
        x2 = input.contains("-") ? -Integer.parseUnsignedInt(input.replace("-", ""), base) : Integer.parseUnsignedInt(input, base);
        System.out.println("Here is the result: ");
        int result = calculate(x1, x2, operation);
        String x1Str;
        String x2Str;
        String resultStr;
        if(calculation == 'h'){
            System.out.println("Hex value: ");
            if(x1 < 0){
                x1Str = "-" + Integer.toHexString(x1 / -1);
            } else {
                x1Str = Integer.toHexString(x1);
            }

            if(x2 < 0){
                x2Str = "-" + Integer.toHexString(x2 / -1);
            } else {
                x2Str = Integer.toHexString(x2);
            }

            if(result < 0){
                resultStr = "-" + Integer.toHexString(result / -1);
            } else {
                resultStr = Integer.toHexString(result);
            }

            System.out.println(x1Str + operation + x2Str);
            System.out.println("= " + resultStr);
        } else {
            System.out.println("Binary value: ");
            System.out.println(String.format("%8s", Integer.toBinaryString(x1)).replace(' ', '0') + " " + operation + " " + String.format("%8s", Integer.toBinaryString(x2)).replace(' ', '0'));
            if(result < 0){
                System.out.println("= -" + String.format("%8s", Integer.toBinaryString(result / -1)).replace(' ', '0'));
            } else {
                System.out.println("= " + String.format("%8s", Integer.toBinaryString(result)).replace(' ', '0'));
            }
        }

        System.out.println("Decimal value: ");
        System.out.println(x1 + " " + operation + " " + x2);
        System.out.println("= " + result);
        s.close();
    }

    /**
     * Calculates a value between two ints depending on the operation.
     *
     * @param x1         the first int
     * @param x2         the second int
     * @param operation the operation
     * @return the int
     */
    public static int calculate(int x1, int x2, char operation) {
        int out;
        switch (operation) {
            case '+':
                out = x1 + x2;
                return out;
            case '-':
                out = x1 - x2;
                return out;
            case '*':
                out = x1 * x2;
                return out;
            case '/':
                out = x1 / x2;
                return out;
            default:
                System.out.println("The operation was not defined or a valid operation was not provided");
                System.exit(0);
                return 0; //this needs to be here or else the compiler gets mad even though its unreachable
        }
    }
}   
