package logic;

import controllers.Controller;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;
import java.util.ArrayList;

public class EncryptionAlgorithm {

    /**
     * This function get the result of p and q, if not p and q can be found show an error message.
     *
     * @param n - The prime product of p and q.
     * @return returns and String with a formatted result.
     */
    public static String getPQ(int n) {
        ArrayList<Double> results = Helper.calculatePQ(n);

        // Check if there are any results found.
        return results.size() == 0 ?
                "No result found." :
                "p is " + ((int) results.get(0).doubleValue()) + "\n" +
                        "q is " + ((int) results.get(1).doubleValue()) + "\n" +
                        "Amount of time busy finding p and q: " + results.get(2);
    }

    /**
     * Get random prime number
     *
     * @param n - The prime product of p and q.
     * @return prime number e
     */
    public static String getRandomE(int n) {
        return Helper.getRandomE(n);
    }

    public static Boolean verifyE(String n, String e){
        ArrayList<Integer>  results = Helper.generatePrimeNumbers(Integer.parseInt(n));
        if (results.contains(Integer.parseInt(e))) {
            return true;
        }else{
            return false;
        }
    }

    /**
     * This function will encrypt a message from the user input
     * @param n key
     * @param message message to encrypt
     * @return the encrypted message
     */
    public static String getC(String n, String message, int userE){
        //Check if user submitted his own e
        int e;
        if (verifyE(n, Integer.toString(userE))){
            e = userE;
        }else{
            e = Integer.parseInt(Helper.getRandomE(Integer.parseInt(n)));
        }

        char[] chars = message.toCharArray();
        int[] mNumbersInt = new int[message.length()];

        // Parse character to int
        for (int j = 0; j < message.length(); j++) {
            mNumbersInt[j] = chars[j];
        }

        BigInteger mNumbers[] = new BigInteger[message.length()];

        for (int i = 0; i < message.length(); i++){
            mNumbers[i] = BigInteger.valueOf(mNumbersInt[i]);
        }

        int[] encryptedArray = Helper.calculateC(mNumbers,e, Integer.parseInt(n));

        return "e is: " + e + "\n" +
                "Message after encrypting is: " + Arrays.toString(encryptedArray);
    }
}
