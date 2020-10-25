package logic;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.ArrayList;

public class EncryptionAlgorithm {

    //Get the p & q based on n
    public static String getPQ(int n) {
        ArrayList<Double> results = Helper.calculatePAndQ(n);

        return "p is " + ((int) results.get(0).doubleValue()) + "\n" +
                "q is " + ((int) results.get(1).doubleValue()) + "\n" +
                "Amount of time busy finding p and q: " + results.get(2) + " milliseconds";
    }

    //Get random prime number
    public static String getRandomE(int n) {
        return Helper.generateRandomE(n);
    }

    //Generate encrypted message
    public static String generateC(String n, String message, int e){
        char[] characters = message.toCharArray();
        int[] mNumberInt = new int[message.length()];

        //For loop to parse character to int
        int m = 0;
        do {
            mNumberInt[m] = characters[m];
            m++;
        } while (m < message.length());

        BigInteger[] mNumber = new BigInteger[message.length()];

        //For loop for the message numbers
        int j = 0;
        do {
            mNumber[j] = BigInteger.valueOf(mNumberInt[j]);
            j++;
        } while (j < message.length());

        //Generate the encrypted message
        int[] encryptedArray = Helper.calculateC(mNumber, e, Integer.parseInt(n));

        return "e is: " + e + "\n" +
                "Message after encrypting is: " + Arrays.toString(encryptedArray);
    }
}
