package fizzbuzz;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.PrintStream;
import static org.mockito.Mockito.*;

public class FizzBuzzTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    public PrintStream printer;

    @Test
    public void testFizzBuzz() {
        FizzBuzz.print(17, printer);
        InOrder inOrder = inOrder(printer);

        inOrder.verify(printer).println(1);
        inOrder.verify(printer).println(2);
        inOrder.verify(printer).println("Fizz");
        inOrder.verify(printer).println(4);
        inOrder.verify(printer).println("Buzz");
        inOrder.verify(printer).println("Fizz");
        inOrder.verify(printer).println(7);
        inOrder.verify(printer).println(8);
        inOrder.verify(printer).println("Fizz");
        inOrder.verify(printer).println("Buzz");
        inOrder.verify(printer).println(11);
        inOrder.verify(printer).println("Fizz");
        inOrder.verify(printer).println(13);
        inOrder.verify(printer).println(14);
        inOrder.verify(printer).println("FizzBuzz");
        inOrder.verify(printer).println(16);
        inOrder.verify(printer).println(17);
        verifyNoMoreInteractions(printer);
        verifyNoMoreInteractions(printer);
    }
}