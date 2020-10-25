package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import logic.Decrypt;
import logic.Encrypt;

public class Controller {

    //Encrypt fields
    @FXML
    TextField inputN;
    @FXML
    TextField inputM;
    @FXML
    TextArea encryptOutput;

    /* Decrypt fields */
    @FXML
    TextField decryptInputN;
    @FXML
    TextField decryptInputE;
    @FXML
    TextField decryptInputC;
    @FXML
    TextArea decryptOutput;

    /**
     * Encrypt functionality.
     */
    public void encryptButtonStep1Clicked() {
        String inputN = this.inputN.getText();
        // Validate if there is an input.
        if (inputN.equals("")) return;
        String output = Encrypt.getPQ(Integer.parseInt(inputN));
        encryptOutput.setText(output);
    }

    /**
     * Encrypt functionality.
     */
    public void encryptButtonStep2Clicked() {
        String inputN = this.inputN.getText();

        // Validate if there is an input.
        if (inputN.equals("")) return;

        StringBuilder e = new StringBuilder();

            e.append("e is: ");
            e.append(Encrypt.getRandomE(Integer.parseInt(inputN)));

        encryptOutput.setText(e.toString());
    }

    public void encryptButtonStep3Clicked() {
        String inputN = this.inputN.getText();
        String inputM = this.inputM.getText();

        if (inputN.equals("") || inputM.equals("")) return;
        int userE;
                userE = 0;

        String output = Encrypt.getC(inputN, inputM, userE);

        encryptOutput.setText(output);
    }

    /**
     * Decrypt functionality.
     */
    public void decryptButtonStep1Clicked() {
        String inputN = decryptInputN.getText();
        String inputE = decryptInputE.getText();

        // Validate if there is an input.
        if (inputN.equals("") || inputE.equals("")) return;

        String output = Decrypt.getD(Integer.parseInt(inputN), Integer.parseInt(inputE));
        decryptOutput.setText(output);
    }

    /**
     * Decrypt functionality.
     */
    public void decryptButtonStep2Clicked() {
        String inputN = decryptInputN.getText();
        String inputE = decryptInputE.getText();
        String inputC = decryptInputC.getText();

        if (inputN.equals("") || inputE.equals("") || inputC.equals("")) return;

        String output = Decrypt.getM(Integer.parseInt(inputN), Integer.parseInt(inputE), inputC);

        decryptOutput.setText(output);
    }

    /**
     * onaction event for enabling e field for userE
     */

    }


