package logic;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class Helper {

    // Calculate a p and a q for the n that is filled in by the user.
    public static ArrayList<Double> calculatePAndQ(int n) {
        double startTime = System.currentTimeMillis();
        ArrayList<Double> results = new ArrayList<Double>();
        ArrayList<Integer> primeNumbers = generatePrimeNumbers(n);

        // Look for the correct result within the range.
        for (int i = 0; i <= primeNumbers.size() - 1; i++) {
            for (int j = 0; j <= primeNumbers.size() - 1 ; j++) {
                int product = primeNumbers.get(i) * primeNumbers.get(j);

                // If the product is bigger than N, break the loop.
                if (product > n) break;

                // If the right product is found than it will return the results.
                if (product == n) {
                    results.add((double) primeNumbers.get(i));
                    results.add((double) primeNumbers.get(j));
                    double endTime = System.currentTimeMillis();
                    results.add((endTime - startTime));
                    return results;
                }
            }
        }
        return results;
    }

    // Method to calculate d, the private key, it is being calculated based on the n and the e.
    public static int calculateD(int n, int e) {
        ArrayList<Double> calculation = Helper.calculatePAndQ(n);

        // Get values from the list.
        int p = (int) calculation.get(0).doubleValue();
        int q = (int) calculation.get(1).doubleValue();

        // Calculate the PHI
        int phi = Helper.calculatePhi(p, q);

        return Helper.modularInverse(e, phi);
    }

    // This method uses p & q to define the maximum prime number e. This method then generates a prime number between the start and
    // the maximum prime.
    public static String generateRandomE(int n){
        int primeNumber;
        ArrayList<Integer> primeNumberList = generatePrimeNumbers(n);

        // Generate the random number to get from the prime number list.
        Random rand = new Random();
        primeNumber = primeNumberList.get(rand.nextInt(primeNumberList.size()));
        return Integer.toString(primeNumber);
    }

    // This method generates a list of prime numbers. It generates them up to the given MAX number.
    public static ArrayList<Integer> generatePrimeNumbers(int MAX) {
        ArrayList<Integer> primeNumberList = new ArrayList<Integer>();

        // Generate a loop with prime numbers up to the MAX number.
        for (int k = 2; k <= MAX; k++) {
            int p = 0;
            for (int o = 1; o <= k; o++) {
                if (k % o == 0) p++;
            }
            // It is a prime number, therefore add it to the list.
            if (p == 2) primeNumberList.add(k);
        }
        return primeNumberList;
    }

    // Calculate the encrypted message before the decryption.
    public static int[] calculateC(BigInteger[] c, int e, int n) {
        int[] result = new int[c.length];

        // Calculate C.
        int b = 0;
        do {
            result[b] = c[b].pow(e).mod(BigInteger.valueOf(n)).intValue();
            b++;
        } while (b < result.length);
        return result;
    }

    // Calculate the modular inverse of e and phi, the main use is to calculate d.
    public static int modularInverse(int e, int phi) {
        e = e % phi;
        for (int x = 1; x < phi; x++)
            if ((e * x) % phi == 1)
                return x;
        return 1;
    }

    // Calculate the phi based on p and q.
    public static int calculatePhi(int p, int q) {
        return (p - 1) * (q - 1);
    }

    // Calculate the message after encryption.
    public static int[] calculateM(BigInteger[] c, int d, int n) {
        int[] result = new int[c.length];

        // Calculate M.
        int i = 0;
        do {
            result[i] = c[i].pow(d).mod(BigInteger.valueOf(n)).intValue();
            i++;
        } while (i < result.length);

        return result;
    }

}
