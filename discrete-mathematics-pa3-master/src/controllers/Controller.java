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

    /**
     * Encrypt functionality.
     */
    public void encryptButtonStep1Clicked() {
        String inputN = this.inputN.getText();
        // Validate if there is an input.
        if (inputN.equals("")) return;
        String output = EncryptionAlgorithm.getPQ(Integer.parseInt(inputN));
        this.output.setText(output);
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
        e.append(EncryptionAlgorithm.getRandomE(Integer.parseInt(inputN)));

        output.setText(e.toString());
    }

    public void encryptButtonStep3Clicked() {
        String inputN = this.inputN.getText();
        String inputM = this.inputM.getText();

        if (inputN.equals("") || inputM.equals("")) return;
        int userE;
        userE = 0;

        String output = EncryptionAlgorithm.getC(inputN, inputM, userE);

        this.output.setText(output);
    }

    /**
     * Decrypt functionality.
     */
    public void decryptButtonStep1Clicked() {
        String inputN = decryptInputN.getText();
        String inputE = decryptInputE.getText();

        // Validate if there is an input.
        if (inputN.equals("") || inputE.equals("")) return;

        String output = DecryptionAlgorithm.getD(Integer.parseInt(inputN), Integer.parseInt(inputE));
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

        String output = DecryptionAlgorithm.getM(Integer.parseInt(inputN), Integer.parseInt(inputE), inputC);

        decryptOutput.setText(output);
    }

    /**
     * onaction event for enabling e field for userE
     */

}


