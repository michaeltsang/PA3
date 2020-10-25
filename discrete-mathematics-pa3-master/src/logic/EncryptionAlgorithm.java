package logic;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.ArrayList;

public class EncryptionAlgorithm {

    //Get the p & q based on n
    public static String getPQ(int n) {
        ArrayList<Double> results = Helper.calculatePQ(n);

        return "p is " + ((int) results.get(0).doubleValue()) + "\n" +
                "q is " + ((int) results.get(1).doubleValue()) + "\n" +
                "Amount of time busy finding p and q: " + results.get(2) + " milliseconds";
    }

    //Get random prime number
    public static String getRandomE(int n) {
        return Helper.getRandomE(n);
    }

    //Generate encrypted message
    public static String generateC(String n, String message, int e){
        char[] characters = message.toCharArray();
        int[] mNumberInt = new int[message.length()];

        //For loop to parse character to int
        for (int m = 0; m < message.length(); m++) {
            mNumberInt[m] = characters[m];
        }

        BigInteger mNumber[] = new BigInteger[message.length()];

        //For loop for the message numbers
        for (int j = 0; j < message.length(); j++){
            mNumber[j] = BigInteger.valueOf(mNumberInt[j]);
        }

        //Generate the encrypted message
        int[] encryptedArray = Helper.calculateC(mNumber, e, Integer.parseInt(n));

        return "e is: " + e + "\n" +
                "Message after encrypting is: " + Arrays.toString(encryptedArray);
    }
}
