package com.example.atmapp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;


import static com.example.atmapp.users.*;

public class mainApp extends Application {
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
        writeNewUser();
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;


        Label pinLabel = new Label("PIN:");
        TextField pinTextField = new TextField();

        Button loginButton = new Button("Giriş");
        loginButton.setOnAction(event -> {
            String inputValue = pinTextField.getText();
            if (inputValue.equals(User1.getPassword())) {
                openNewPage(String.valueOf(User1.getBalance()));
                primaryStage.hide();
                pinTextField.clear();
            } else {
                showErrorAlert();
                pinTextField.clear();

            }
        });

        Button deleteButton = new Button("Sil");
        deleteButton.setOnAction(event -> {
            String currentText = pinTextField.getText();
            if (!currentText.isEmpty()) {
                String updatedText = currentText.substring(0, currentText.length() - 1);
                pinTextField.setText(updatedText);
            }
        });

        Button oneButton = new Button("1");
        oneButton.setOnAction(event -> {
            String text = "1";
            String currentText = pinTextField.getText();
            pinTextField.setText(currentText + text);
        });

        Button twoButton = new Button("2");
        twoButton.setOnAction(event -> {
            String text = "2";
            String currentText = pinTextField.getText();
            pinTextField.setText(currentText + text);
        });

        Button threeButton = new Button("3");
        threeButton.setOnAction(event -> {
            String text = "3";
            String currentText = pinTextField.getText();
            pinTextField.setText(currentText + text);
        });

        Button fourButton = new Button("4");
        fourButton.setOnAction(event -> {
            String text = "4";
            String currentText = pinTextField.getText();
            pinTextField.setText(currentText + text);
        });

        Button fiveButton = new Button("5");
        fiveButton.setOnAction(event -> {
            String text = "5";
            String currentText = pinTextField.getText();
            pinTextField.setText(currentText + text);
        });

        Button sixButton = new Button("6");
        sixButton.setOnAction(event -> {
            String text = "6";
            String currentText = pinTextField.getText();
            pinTextField.setText(currentText + text);
        });

        Button sevenButton = new Button("7");
        sevenButton.setOnAction(event -> {
            String text = "7";
            String currentText = pinTextField.getText();
            pinTextField.setText(currentText + text);
        });

        Button eightButton = new Button("8");
        eightButton.setOnAction(event -> {
            String text = "8";
            String currentText = pinTextField.getText();
            pinTextField.setText(currentText + text);
        });

        Button nineButton = new Button("9");
        nineButton.setOnAction(event -> {
            String text = "9";
            String currentText = pinTextField.getText();
            pinTextField.setText(currentText + text);
        });

        Button zeroButton = new Button("0");
        zeroButton.setOnAction(event -> {
            String text = "0";
            String currentText = pinTextField.getText();
            pinTextField.setText(currentText + text);
        });

        Button starButton = new Button("*");
        starButton.setOnAction(event -> {
            String text = "*";
            String currentText = pinTextField.getText();
            pinTextField.setText(currentText + text);
        });

        Button hastagButton = new Button("#");
        hastagButton.setOnAction(event -> {
            String text = "#";
            String currentText = pinTextField.getText();
            pinTextField.setText(currentText + text);
        });

        VBox lbl_text = new VBox(10);
        lbl_text.getChildren().addAll(pinLabel, pinTextField);
        HBox btn_login = new HBox(10);
        btn_login.getChildren().addAll(deleteButton, loginButton);
        HBox buttons1 = new HBox(10);
        buttons1.getChildren().addAll(oneButton, twoButton, threeButton);
        HBox buttons2 = new HBox(10);
        buttons2.getChildren().addAll(fourButton, fiveButton, sixButton);
        HBox buttons3 = new HBox(10);
        buttons3.getChildren().addAll(sevenButton, eightButton, nineButton);
        HBox buttons4 = new HBox(10);
        buttons4.getChildren().addAll(starButton, zeroButton, hastagButton);
        VBox homeLayout = new VBox(10);
        homeLayout.setPadding(new Insets(10));
        homeLayout.getChildren().addAll(lbl_text, btn_login, buttons1, buttons2, buttons3, buttons4);
        Scene homeScene = new Scene(homeLayout, 250, 300);
        primaryStage.setScene(homeScene);
        primaryStage.show();


    }

    private void openOutPage() {
        Stage outStage = new Stage();
        VBox inRoot = new VBox(10);
        inRoot.setPadding(new Insets(10));

        HBox box2 = new HBox(10);
        Label lbl2 = new Label("Tutar");
        TextField txt2 = new TextField();

        Button btn_send = new Button("Çek");
        btn_send.setOnAction(event -> {
            float countValue;
            try {
                countValue = Float.parseFloat(txt2.getText());
            } catch (NumberFormatException e) {
                showTransferError();
                return;
            }

            if ( (countValue > 0) && (User1.getBalance() > countValue) ){
                User1.setBalance(User1.getBalance()-countValue);
                succesAlert();
                outStage.close();
            } else {
                showLowBalanceError();
            }
        });

        box2.getChildren().addAll(lbl2, txt2);
        inRoot.getChildren().addAll(box2, btn_send);
        Scene newScene = new Scene(inRoot, 250, 300);
        outStage.setScene(newScene);
        outStage.show();

    }
    private void openInPage() {
        Stage inStage = new Stage();
        VBox inRoot = new VBox(10);
        inRoot.setPadding(new Insets(10));

        HBox box2 = new HBox(10);
        Label lbl2 = new Label("Tutar");
        TextField txt2 = new TextField();

        Button btn_send = new Button("Yükle");
        btn_send.setOnAction(event -> {
            float countValue;
            try {
                countValue = Float.parseFloat(txt2.getText());
            } catch (NumberFormatException e) {
                showTransferError();
                return;
            }

            if (countValue > 0) {
                User1.setBalance(User1.getBalance()+countValue);
                succesAlert();

                inStage.close();
            } else {
                showTransferError();
            }
        });

        box2.getChildren().addAll(lbl2, txt2);
        inRoot.getChildren().addAll(box2, btn_send);
        Scene newScene = new Scene(inRoot, 250, 300);
        inStage.setScene(newScene);
        inStage.show();
    }
    private void openTransferPage() {
        Stage transferStage = new Stage();
        VBox transferRoot = new VBox(10);
        transferRoot.setPadding(new Insets(10));

        HBox box1 = new HBox(10);
        Label lbl1 = new Label("ID");
        TextField txt1 = new TextField();

        HBox box2 = new HBox(10);
        Label lbl2 = new Label("Tutar");
        TextField txt2 = new TextField();

        Button btn_send = new Button("Gönder");
        btn_send.setOnAction(event -> {
            String idValue = txt1.getText();
            float countValue;
            try {
                countValue = Float.parseFloat(txt2.getText());
            } catch (NumberFormatException e) {
                showTransferError();
                return;
            }

            if (    (Objects.equals(idValue, User2.getId())) ||
                    (Objects.equals(idValue, User3.getId())) ||
                    (Objects.equals(idValue, User4.getId())) ||
                    (Objects.equals(idValue, User5.getId())) && countValue > 0) {
                if (countValue<User1.getBalance()){
                    if (Objects.equals(idValue, User2.getId())){
                        User2.setBalance(User2.getBalance()+countValue);
                        User1.setBalance(User1.getBalance()-countValue);
                        succesAlert();
                        transferStage.close();

                    } else if (Objects.equals(idValue, User3.getId())){
                        User3.setBalance(User3.getBalance()+countValue);
                        User1.setBalance(User1.getBalance()-countValue);
                        succesAlert();
                        transferStage.close();

                    } else if (Objects.equals(idValue, User4.getId())){
                        User4.setBalance(User4.getBalance()+countValue);
                        User1.setBalance(User1.getBalance()-countValue);
                        succesAlert();
                        transferStage.close();

                    } else if (Objects.equals(idValue, User5.getId())){
                        User5.setBalance(User5.getBalance()+countValue);
                        User1.setBalance(User1.getBalance()-countValue);
                        succesAlert();
                        transferStage.close();

                    }
                } else {
                    showLowBalanceError();
                }

            } else {
                showTransferError();
            }
        });



        box1.getChildren().addAll(lbl1, txt1);
        box2.getChildren().addAll(lbl2, txt2);
        transferRoot.getChildren().addAll(box1, box2, btn_send);
        Scene newScene = new Scene(transferRoot, 250, 300);
        transferStage.setScene(newScene);
        transferStage.show();

    }
    private void openNewPage(String x) {
        Stage newStage = new Stage();
        VBox newRoot = new VBox(10);
        newRoot.setPadding(new Insets(10));
        Label lbl_text = new Label("Bakiyeniz:");
        //Label lbl_balance = new Label(String.valueOf(User1.getBalance()));
        Label lbl_balance = new Label(x);

        HBox balanceBox = new HBox(10);
        balanceBox.getChildren().addAll(lbl_text, lbl_balance);


        Button btn_in = new Button("Para Yatır");
        btn_in.setOnAction(event -> openInPage());

        Button btn_out = new Button("Para Çek");
        btn_out.setOnAction(event -> openOutPage());

        Button btn_transfer = new Button("Para Transferi");
        btn_transfer.setOnAction(event -> openTransferPage());

        Button btn_exit = new Button("Çıkış Yap");
        btn_exit.setOnAction(event -> {
            newStage.close();
            primaryStage.show();
        });

        newRoot.getChildren().addAll(balanceBox, btn_in, btn_out, btn_transfer, btn_exit);
        Scene newScene = new Scene(newRoot, 250, 300);
        newStage.setScene(newScene);
        newStage.show();
    }

    private void showErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Hata");
        alert.setHeaderText(null);
        alert.setContentText("Yanlış şifre girdiniz!");
        alert.showAndWait();
    }

    private void succesAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Başarılı");
        alert.setHeaderText(null);
        alert.setContentText("İşlem Başarılı!");
        alert.showAndWait();
    }

    private void showTransferError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Hata");
        alert.setHeaderText(null);
        alert.setContentText("Yanlış bilgi girdiniz!");
        alert.showAndWait();
    }

    private void showLowBalanceError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Hata");
        alert.setHeaderText(null);
        alert.setContentText("Bu işlem için bakiyeniz yetersiz!");
        alert.showAndWait();
    }

    public static void writeNewUser() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt", true))) {

            bw.write(User1.getName()+" "+User1.getSurname()+" "+User1.getId()+" "+User1.getPassword()+" "+User1.getBalance());
            bw.newLine();
            bw.write(User2.getName()+" "+User2.getSurname()+" "+User2.getId()+" "+User2.getPassword()+" "+User2.getBalance());
            bw.newLine();
            bw.write(User3.getName()+" "+User3.getSurname()+" "+User3.getId()+" "+User3.getPassword()+" "+User3.getBalance());
            bw.newLine();
            bw.write(User4.getName()+" "+User4.getSurname()+" "+User4.getId()+" "+User4.getPassword()+" "+User4.getBalance());
            bw.newLine();
            bw.write(User5.getName()+" "+User5.getSurname()+" "+User5.getId()+" "+User5.getPassword()+" "+User5.getBalance());
            bw.newLine();
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
