package logic;

import java.math.BigInteger;

public class DecryptionAlgorithm {


     // This function retrieves the d from the n and e values. It does so by calculating the phi and modular inverse of
     // e and phi.

     /**
     * @param n - The product of p & q.
     * @param e - The public key.
     * @return returns the d.
     */
    public static String getD(int n, int e) {
        int d = Helper.calculateD(n, e);
        return "d is " + d;
    }

    /**
     * This function retrieves all the unicode integers of the message.
     *
     * @param n - The product of p and q.
     * @param e - The public key.
     * @param decryptedMessage - The decrypted message.
     * @return an array of all the unicode integers.
     */
    public static String getM(int n, int e, String decryptedMessage){
        int d = Helper.calculateD(n, e);

        String[] result = decryptedMessage.split("\\s*,\\s*");

        BigInteger[] c = new BigInteger[result.length];

        // Parse all the values of the public key to an integer.
        for (int i = 0; i < result.length; i++) {
            c[i] = BigInteger.valueOf(Integer.parseInt(result[i]));
        }

        int[] m = Helper.calculateM(c,d, n);
        char[] mChars = new char[m.length];

        // Parse the integer to a character.
        for (int i = 0; i < m.length; i++) {
            mChars[i] = Character.toChars(m[i])[0];
        }

        return new String(mChars);
    }
}
