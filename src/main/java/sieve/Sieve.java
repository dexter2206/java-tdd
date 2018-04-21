package sieve;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class Sieve {

    public static List<Integer> findPrimes(int limit) {
        return findPrimes(limit, new ArrayList<>());
    }

    public static List<Integer> findPrimes(int limit, List<Integer> primes) {
        boolean[] flags = new boolean[limit+1];
        int j;
        for(int i = 2; i < flags.length-1; i++) {
            if(!flags[i]) {
                primes.add(i);
                j = 2 * i;
                while(j < flags.length) {
                    flags[j] = true;
                    j += i;
                }
            }
        }
        return primes;
    }
}
