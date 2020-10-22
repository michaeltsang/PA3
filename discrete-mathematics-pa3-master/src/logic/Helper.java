package logic;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class Helper {

    /**
     * This function calculates a p and a q for a given n.
     *
     * @param n - The n is the product of p and q.
     * @return an ArrayList with the prime numbers p = index 0, q = index 1 and the time (index 2).
     */
    public static ArrayList<Double> calculatePQ(int n) {
        double startTime = System.currentTimeMillis();

        ArrayList<Double> results = new ArrayList<Double>();
        ArrayList<Integer> primes = generatePrimeNumbers(n);

        for (int i = 0; i <= primes.size() - 1; i++) {
            for (int j = 0; j <= primes.size() - 1; j++) {
                int product = primes.get(i) * primes.get(j);

                // Go out the loop when the product is greater than n, since this isn't possible anymore.
                if (product > n) break;

                // Return the results when a correct product is found.
                if (product == n) {
                    results.add((double) primes.get(i));
                    results.add((double) primes.get(j));

                    double endTime = System.currentTimeMillis();
                    results.add((endTime - startTime));

                    return results;
                }
            }
        }
        return results;
    }

    /**
     * This function calculates d this is the private key, calculate it bases on the n and e.
     *
     * @param n - The product of p and q.
     * @param e - The public key.
     * @return
     */
    public static int calculateD(int n, int e) {
        ArrayList<Double> data = Helper.calculatePQ(n);

        int p = (int) data.get(0).doubleValue();
        int q = (int) data.get(1).doubleValue();

        int phi = Helper.calculatePhi(p, q);

        return Helper.modularInverse(e, phi);
    }

    /**
     * This function uses p & q to define maxprime number e
     * The output is a prime number between start and maxprime
     *
     * @return primeNumber converted to String
     */
    public static String getRandomE(int n){

        int primeNumber;
        ArrayList<Integer> primeList = generatePrimeNumbers(n);

        Random rand = new Random();
        primeNumber = primeList.get(rand.nextInt(primeList.size()));
        return Integer.toString(primeNumber);
    }

    /**
     * This function generates a list of prime numbers. It generates them till the given number.
     *
     * @param till - The max number to generate prime numbers to.
     * @return a list of prime numbers.
     */
    public static ArrayList<Integer> generatePrimeNumbers(int till) {
        ArrayList<Integer> primes = new ArrayList<Integer>();

        // This loop generates prime numbers till the till parameter.
        for (int i = 2; i <= till; i++) {
            int c = 0;
            for (int j = 1; j <= i; j++) {
                if (i % j == 0) c++;
            }
            if (c == 2) primes.add(i);
        }
        return primes;
    }

    public static int[] calculateC(BigInteger[] c, int e, int n) {
        int[] result = new int[c.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = c[i].pow(e).mod(BigInteger.valueOf(n)).intValue();
        }

        return result;
    }

    /**
     * This function calculates the modular inverse of e and phi. It is mostly used to calculate the 'd'.
     *
     * @param e   - The first variable.
     * @param phi - The second variable.
     * @return the modluar inverse of e and phi.
     */
    public static int modularInverse(int e, int phi) {
        e = e % phi;
        for (int x = 1; x < phi; x++)
            if ((e * x) % phi == 1)
                return x;
        return 1;
    }

    /**
     * This function calculates the phi based on p and q.
     *
     * @param p - Prime number p.
     * @param q - Prime number q.
     * @return
     */
    public static int calculatePhi(int p, int q) {
        return (p - 1) * (q - 1);
    }

    public static int[] calculateM(BigInteger[] c, int d, int n) {
        int[] result = new int[c.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = c[i].pow(d).mod(BigInteger.valueOf(n)).intValue();
        }

        return result;
    }

}
