package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import logic.DecryptionAlgorithm;
import logic.EncryptionAlgorithm;

public class Controller {

    /* Encrypt fields */
    @FXML
    TextField encryptInputN;
    @FXML
    TextField encryptInputM;
    @FXML
    CheckBox checkBoxMultipleE;
    @FXML
    TextField encryptInputE;
    @FXML
    CheckBox checkBoxUserE;
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

    final String cssDefault = "-fx-border-color: gray;";
    final String cssError = "-fx-border-color: red;";

    /**
     * Encrypt functionality.
     */
    public void encryptButtonStep1Clicked() {
        String inputN = encryptInputN.getText();

        // Validate if there is an input.
        if (inputN.equals("")) return;

        String output = EncryptionAlgorithm.getPQ(Integer.parseInt(inputN));

        encryptOutput.setText(output);
    }

    /**
     * Encrypt functionality.
     */
    public void encryptButtonStep2Clicked() {
        String inputN = encryptInputN.getText();

        // Validate if there is an input.
        if (inputN.equals("")) return;

        StringBuilder e = new StringBuilder();

        if (checkBoxMultipleE.isSelected()){
            e.append("e's are: ");
            for (int i = 0; i < 5; i++){
                e.append(EncryptionAlgorithm.getRandomE(Integer.parseInt(inputN))).append(", ");
            }
        }else{
            e.append("e is: ");
            e.append(EncryptionAlgorithm.getRandomE(Integer.parseInt(inputN)));
        }

        encryptOutput.setText(e.toString());
    }

    public void encryptButtonStep3Clicked() {
        String inputN = encryptInputN.getText();
        String inputM = encryptInputM.getText();

        if (inputN.equals("") || inputM.equals("")) return;

        int userE;
        if (!encryptInputE.equals("") && checkBoxUserE.isSelected()){
            if (EncryptionAlgorithm.verifyE(inputN, encryptInputE.getText())){
                userE = Integer.parseInt(encryptInputE.getText());
                encryptInputE.setStyle(cssDefault);
            }else{
                userE = 0;
                encryptInputE.setStyle(cssError);
            }
        }else{
            userE = 0;
        }

        String output = EncryptionAlgorithm.getC(inputN, inputM, userE);

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
    public void checkBoxEnable(){
        if (checkBoxUserE.isSelected()){
            encryptInputE.setDisable(false);
        }else{
            encryptInputE.setDisable(true);
        }
    }

}
