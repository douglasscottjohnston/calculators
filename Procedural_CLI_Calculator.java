import java.util.Scanner;

/**
 * @author Douglas Johnston
 *
 * The type Procedural cli calculator.
 */
public class Procedural_CLI_Calculator {

    /**
     * The Calculation.
     */
    static char calculation;
    /**
     * The Operation.
     */
    static char operation;
    /**
     * The first input
     */
    static String x1;
    /**
     * The second input
     */
    static String x2;
    /**
     * The scanner
     */
    static Scanner s = new Scanner(System.in);

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        System.out.println("Type h for hex or b for binary: ");
        calculation = s.next().charAt(0);
        System.out.println("Type c to make a conversion or o to make an operation");
        if(s.next().charAt(0) == 'o'){
            operate();
        } else {
            conversion();
        }
        System.exit(0);
        s.close();
    }

    /**
     * Conversion.
     */
    public static void conversion(){
        if(calculation == 'h') {
            System.out.println("Type h to convert from hex to decimal or d to convert from decimal to hex");
            char convert = s.next().charAt(0);
            if(convert == 'h'){
                System.out.println("Type the hex value to convert: ");
                x1 = s.next();
                System.out.println("Here is the converted decimal: ");
                //handle negatives
                int x1Int;
                if(x1.contains("-")){
                    x1Int = hexToInt(x1.replace("-", ""));
                    x1Int = x1Int / -1;
                    System.out.println(x1Int);
                } else {
                    System.out.println(hexToInt(x1));
                }
            } else if(convert == 'd'){
                System.out.println("Type the decimal value to convert");
                int d = s.nextInt();
                System.out.println("Here is the converted hex value: ");
                if(d < 0){
                    x1 = "-" + intToHex(d / -1);
                } else {
                    x1 = intToHex(d);
                }
                System.out.println(x1);
            } else {
                System.out.println("The input was not valid");
                System.exit(0);
            }
        } else {
            System.out.println("Type b to convert from binary to decimal or d to convert from decimal to binary");
            char convert = s.next().charAt(0);
            if(convert == 'b'){
                System.out.println("Type the binary value to convert: ");
                x1 = s.next();
                if(x1.contains("-")){
                    System.out.println("The value can only contain 0 or 1");
                    System.exit(0);
                } else {
                    System.out.println("Here is the converted decimal value: ");
                    System.out.println(binaryToInt(x1));
                }
            } else if(convert == 'd'){
                System.out.println("Type the decimal value to convert: ");
                int d = s.nextInt();
                System.out.println("Here is the converted binary value: ");
                System.out.println(intToBinary(d));
            } else {
                System.out.println("The input was not valid");
                System.exit(0);
            }
        }
        x1 = s.next();

    }

    /**
     * Operate.
     */
    public static void operate(){
        System.out.println("Type the operation you want to do (+, -, *, /): ");
        operation = s.next().charAt(0);
        System.out.println("Type the first number to preform the operation on: ");
        x1 = s.next();
        System.out.println("Type the second number to preform the operation on: ");
        x2 = s.next();

        System.out.println("Here is the result: ");
        int x1Int = 0;
        int x2Int = 0;
        int result = 0;
        String hexResult;

        if(calculation == 'h'){
            //handle negative numbers
            if(x1.contains("-")){
                x1Int = hexToInt(x1.replace("-", ""));
                System.out.println(x1Int);
                x1Int = x1Int / -1;
                System.out.println(x1Int);
            }

            if(x2.contains("-")){
                x2Int = hexToInt(x2.replace("-", ""));
                x2Int = x2Int / -1;
            }
            if(!x1.contains("-")){
                x1Int = hexToInt(x1);
            }
            if(!x2.contains("-")){
                x2Int = hexToInt(x2);
            }

            result = calculate(x1Int, x2Int, operation);
            if(result < 0){
                hexResult = "-" + intToHex(result / -1);
            } else {
                hexResult = intToHex(result);
            }
            System.out.println("Hex value: ");
            System.out.println(x1 + " " + operation + " " + x2);
            System.out.println("= " + hexResult);
        } else if(calculation == 'b') {
            if(x1.contains("-") || x2.contains("-")) {
                System.out.println("The numbers can only contain 0 or 1");
                System.exit(0);
            }
            x1Int = binaryToInt(x1);
            x2Int = binaryToInt(x2);
            result = calculate(x1Int, x2Int, operation);
            System.out.println("Binary value: ");
            System.out.println(x1 + " " + operation + " " + x2);
            System.out.println("= " + intToBinary(result));
        } else {
            System.out.println("That argument is not valid");
            System.exit(0);
        }

        System.out.println("\nDecimal value: ");
        System.out.println(x1Int + " " + operation + " " + x2Int);
        System.out.println("= " + result);
    }

    /**
     * Converts a hex string to int.
     *
     * @param hexString the hex string
     * @return the int
     */
    public static int hexToInt(String hexString) {
        if (hexString.equals("0")) return 0;
        String digits = "0123456789ABCDEF";
        hexString = hexString.toUpperCase();
        int val = 0;
        for (int i = 0; i < hexString.length(); i++) {
            char c = hexString.charAt(i);
            int d = digits.indexOf(c);
            val = 16*val + d;
        }
        return val;
    }

    /**
     * Converts an int to hex string.
     *
     * @param x the int
     * @return the hex string
     */
    public static String intToHex(int x) {
        String digits = "0123456789ABCDEF";
        String out = "";

        while (x != 0){
            out = digits.charAt(x & 0xF) + out; //I didn't use string builder because I wasn't sure if we were allowed to
            x = x >>> 4;
            if(out.length() > 8) break;
        }
        return out;
    }

    /**
     * Converts a binary string to int.
     *
     * @param binaryString the binary string
     * @return the int
     */
    public static int binaryToInt(String binaryString) {
        if (binaryString.equals("0")) return 0;
        int out = 0;

        for (int i = 0; i < binaryString.length(); i++) {
            out = 2 * out + (binaryString.charAt(i) - '0');
        }

        return out;
    }

    /**
     * Converts an int to binary string.
     *
     * @param x the int
     * @return the binary string
     */
    public static String intToBinary(int x) {
        String out = "";

        while (x != 0) {
            out = (x % 2) + out; //I didn't use string builder because I wasn't sure if we were allowed to
            x = x / 2;
        }
        return out;
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
                return 0;
        }
    }
}
