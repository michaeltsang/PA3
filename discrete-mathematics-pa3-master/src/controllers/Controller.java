package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import logic.DecryptionAlgorithm;
import logic.EncryptionAlgorithm;

public class Controller {

    //Encrypt fields
    @FXML
    TextField inputN;
    @FXML
    TextField inputM;
    @FXML
    TextArea output;

    //Decrypt fields
    @FXML
    TextField decryptInputN;
    @FXML
    TextField decryptInputE;
    @FXML
    TextField decryptInputC;
    @FXML
    TextArea decryptOutput;

    StringBuilder e = new StringBuilder();

    //Encrypt step 1
    public void encryptButtonStep1Clicked() {
        //Get the input from n
        String inputN = this.inputN.getText();

        //Calculate p & q
        String output = EncryptionAlgorithm.getPQ(Integer.parseInt(inputN));

        //Set the output
        this.output.setText(output);
    }

    //Encrypt step 2
    public void encryptButtonStep2Clicked() {
        //Get the input from n
        String inputN = this.inputN.getText();

        //Generate e
        e.append("e is: ");
        e.append(EncryptionAlgorithm.getRandomE(Integer.parseInt(inputN)));

        //Put it in the output
        output.setText(e.toString());
    }

    //Getter for e
    public StringBuilder getE() {
        return e;
    }

    //Encrypt step 3
    public void encryptButtonStep3Clicked() {
        //Get the n & m
        String inputN = this.inputN.getText();
        String inputM = this.inputM.getText();

        //Get the value of e from step 2
        int outputE = Integer.parseInt(getE().delete(0,6).toString());

        //Generate c
        String output = EncryptionAlgorithm.generateC(inputN, inputM, outputE);

        //Set the output
        this.output.setText(output);
    }

    //Decrypt step 1
    public void decryptButtonStep1Clicked() {
        //Get the input from n & e
        String inputN = decryptInputN.getText();
        String inputE = decryptInputE.getText();

        //Generate D
        String output = DecryptionAlgorithm.generateD(Integer.parseInt(inputN), Integer.parseInt(inputE));

        //Set the text
        decryptOutput.setText(output);
    }

    //Decrypt step 2
    public void decryptButtonStep2Clicked() {
        //Get the input from n & e & c
        String inputN = decryptInputN.getText();
        String inputE = decryptInputE.getText();
        String inputC = decryptInputC.getText();

        //Generate m
        String output = DecryptionAlgorithm.generateM(Integer.parseInt(inputN), Integer.parseInt(inputE), inputC);

        //Set the output
        decryptOutput.setText(output);
    }
}


