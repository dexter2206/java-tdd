package fizzbuzz;

import java.io.PrintStream;

public class FizzBuzz {

    public static void print(int n, PrintStream printer) {

        for(int i = 1; i <= n; i++) {
            if(i % 15 == 0) {
                printer.println("FizzBuzz");
                continue;
            }
            if(i % 3 == 0) {
                printer.println("Fizz");
                continue;
            }
            if(i % 5 == 0) {
                printer.println("Buzz");
                continue;
            }
            printer.println(i);
        }
    }
}
