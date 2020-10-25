package algorithms;

import java.math.BigInteger;

public class DecryptionAlgorithm {

     //Generate the d value, based on n and e
    public static String generateD(int n, int e) {
        int d = Calculations.calculateD(n, e);
        return "d is " + d;
    }

    //Decrypt the message
    public static String generateM(int n, int e, String decryptedMessage){

        //Calculate d
        int d = Calculations.calculateD(n, e);

        //Put the words in the array
        String[] result = decryptedMessage.split("\\s*,\\s*");
        BigInteger[] c = new BigInteger[result.length];

        //For loop to parse the values to int
        for (int i = 0; i < result.length; i++) {
            c[i] = BigInteger.valueOf(Integer.parseInt(result[i]));
        }

        //Calculate m
        int[] m = Calculations.calculateM(c,d,n);
        char[] mChars = new char[m.length];

        //For loop to parse the integer to character.
        for (int i = 0; i < m.length; i++) {
            mChars[i] = Character.toChars(m[i])[0];
        }
        return new String(mChars);
    }
}
