class ComplexNumber {//made non-public intentionally
    protected int real1, real2, imaginary1, imaginary2;

    public ComplexNumber(int real1, int imaginary1, int real2, int imaginary2) {
        this.real1 = real1;
        this.imaginary1 = imaginary1;
        this.real2 = real2;
        this.imaginary2 = imaginary2;
    }
}

public class ComplexNumberCalculator extends ComplexNumber {

    public ComplexNumberCalculator(int real1, int imaginary1, int real2, int imaginary2) {
        super(real1, imaginary1, real2, imaginary2);//calls the constructor of upper class
    }


    public boolean equal() {
        return real1 == real2 && imaginary1 == imaginary2;
    }

    public ComplexNumber add() {
        int realResult = real1 + real2;
        int imaginaryResult = imaginary1 + imaginary2;
        return new ComplexNumber(realResult, imaginaryResult, 0, 0);//store the result in a new object
    }


    public ComplexNumber mul() {
        int realResult = real1 * real2 - imaginary1 * imaginary2;//used the fact that i**2 is -1
        int imaginaryResult = real1 * imaginary2 + imaginary1 * real2;
        return new ComplexNumber(realResult, imaginaryResult, 0, 0);
    }


    public ComplexNumber sub() {
        int realResult = real1 - real2;
        int imaginaryResult = imaginary1 - imaginary2;
        return new ComplexNumber(realResult, imaginaryResult, 0, 0);
    }

    // Main
    public static void main(String[] args) {
        if (args.length < 5) {
            System.out.println("Problem in arguments. Please provide four integer values and the operation");
            return;
        }
        int first_real = Integer.parseInt(args[0]);
        int first_img = Integer.parseInt(args[1]);
        int second_real = Integer.parseInt(args[2]);
        int second_img = Integer.parseInt(args[3]);
        String op = args[4];

        // create an instance of ComplexNumberCalculator
        ComplexNumberCalculator calc = new ComplexNumberCalculator(first_real, first_img, second_real, second_img);

        switch (op) {
            case "equal":
                System.out.println(calc.equal());
                break;

            case "add":
                ComplexNumber sumResult = calc.add();
                System.out.println(sumResult.real1 + "+" + sumResult.imaginary1 + "i");
                break;
            case "sub":
                ComplexNumber subResult = calc.sub();
                System.out.println(subResult.real1 + "" + subResult.imaginary1 + "i");

                break;
            case "mul":
                ComplexNumber mulResult = calc.mul();
                System.out.println(mulResult.real1 + "+" + mulResult.imaginary1 + "i");

                break;

        }
    }
}


