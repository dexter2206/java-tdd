package sieve;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;


public class SieveTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Spy
    private List<Integer> primes = new ArrayList<>();

    @Mock
    private List<Integer> primes2;

    @Test
    public void whenBoundCorrectReturnsPrimes() {
        assertThat(Sieve.findPrimes(12))
                .hasSize(5)
                .containsExactly(2, 3, 5, 7, 11);
    }

    @Test

    public void whenBoundNonnegativeBelowThreeReturnsEmptyList() {
        assertThat(Sieve.findPrimes(0)).isEmpty();
    }

    @Test
    public void whenBoundNegativeThrowsExceptionWithCorrectMessage() {
        String msg = catchThrowable(() -> Sieve.findPrimes(-1)).getMessage();
        assertThat(msg)
                .isEqualToIgnoringCase("bound has to be nonnegative.")
                .startsWith("Bound");
    }

    @Test
    public void whenBoundNegativeThrowsIllegalArgumentException() {
        Throwable ex = catchThrowable(() -> Sieve.findPrimes(-1));
        assertThat(ex.getClass()).isEqualTo(IllegalArgumentException.class);
    }

    @Test
    public void whenBoundNegativeThrowsCorrectException() {
        assertThatThrownBy(() -> Sieve.findPrimes(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Bound")
                .hasMessageContaining("nonnegative")
                .hasMessage("Bound has to be nonnegative.");
    }

    @Test
    public void testSpy() {
        primes.add(10);
        primes.add(5);
        primes.remove(0);
        verify(primes, times(1)).add(10);
        verify(primes).add(5);
        verify(primes, never()).add(100);
        verifyNoMoreInteractions(primes);
    }

    @Test
    public void whenBoundBelowThreeDoesNotInteractWithList() {
        Sieve.findPrimes(2, primes);
        verifyZeroInteractions(primes);
    }

    @Test
    public void whenBoundCorrectAddsToOutputList() {
        Sieve.findPrimes(10, primes);
        verify(primes).add(2);
        verify(primes).add(3);
        verify(primes).add(5);
        verify(primes).add(7);
        verifyNoMoreInteractions(primes);
        assertThat(primes)
                .containsExactly(2,3,5,7)
                .hasSize(4);
    }

    @Test
    public void whenBoundCorrectAddsToOutputListMock() {
        Sieve.findPrimes(10, primes2);
        verify(primes2).add(2);
        verify(primes2).add(3);
        verify(primes2).add(5);
        verify(primes2).add(7);
        verifyNoMoreInteractions(primes2);
        when(primes2.size()).thenReturn(4);
        assertEquals(4, primes2.size());
    }
}