package sieve;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(JUnitParamsRunner.class)
public class SieveTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Spy
    List<Integer> primesList = new ArrayList<>();

    @Test
    public void returnsPrimes() {
        assertThat(Sieve.findPrimes(16))
                .hasSize(6)
                .containsExactly(2,3,5,7,11,13);
    }

    @Test
    public void respectsExclusiveUpperBound() {
        assertThat(Sieve.findPrimes(17)).doesNotContain(17);
    }

    @Test
    public void addsToExistingList() {
        Sieve.findPrimes(9, primesList);
        InOrder inorder = inOrder(primesList);
        inorder.verify(primesList, times(1)).add(2);
        inorder.verify(primesList, times(1)).add(3);
        inorder.verify(primesList, times(1)).add(5);
        inorder.verify(primesList, times(1)).add(7);
    }
}