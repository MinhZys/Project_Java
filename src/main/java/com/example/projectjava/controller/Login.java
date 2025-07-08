package com.example.projectjava.controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.example.projectjava.database.DBConnect;
public class Login {
    @FXML
    private AnchorPane Log;

    @FXML
    private Button Loginbtn;

    @FXML
    private Button Signupbtn;

    @FXML
    private Hyperlink _forgot;

    @FXML
    private PasswordField _password;

    @FXML
    private TextField _username;

    @FXML
    private Button side_Already;

    @FXML
    private AnchorPane side_Create;

    @FXML
    private Button side_CreateBtn;

    @FXML
    private AnchorPane side_Loginform;

    @FXML
    private AnchorPane side_Regiter;

    @FXML
    private TextField su_Address;

    @FXML
    private TextField su_Email;

    @FXML
    private PasswordField su_Password;

    @FXML
    private TextField su_Phone;

    @FXML
    private TextField su_Username;



    @FXML
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;


    @FXML
    void switchForm(ActionEvent event) {

        TranslateTransition slider = new TranslateTransition();

        if(event.getSource() == side_CreateBtn) {
            slider.setNode(side_Create);
            slider.setToX(300);
            slider.setDuration(Duration.seconds(.5));

            slider.setOnFinished((  ActionEvent e) ->{
                side_Already.setVisible(true);
                side_CreateBtn.setVisible(false);
            });
            slider.play();
        } else if(event.getSource() == side_Already){
            slider.setNode(side_Create);
            slider.setToX(0);
            slider.setDuration(Duration.seconds(1));

            slider.setOnFinished((ActionEvent e) ->{
                side_Already.setVisible(false);
                side_CreateBtn.setVisible(true);
            });
            slider.play();
        }

    }


    @FXML
    void handleLogin(ActionEvent event) {
        String username = _username.getText();
        String password = _password.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Vui lòng nhập đủ thông tin!");
            return;
        }

        try (Connection conn = DBConnect.getConnection()) {
            String sql = "SELECT RoleID FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int roleId = rs.getInt("RoleID");

                // Chuyển scene
                String fxmlFile = null;
                switch (roleId) {
                    case 1:
                        fxmlFile = "/com/example/projectjava/AdminView.fxml";
                        break;
                    case 2:
                        fxmlFile = "/com/example/projectjava/StaffView.fxml";
                        break;
                    case 3:
                        fxmlFile = "/com/example/projectjava/CustomerView.fxml";
                        break;
                    default:
                        showAlert("Quyền không hợp lệ!");
                        return;
                }

                // Load file FXML tương ứng
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
                Parent root = loader.load();
                Stage stage = (Stage) Loginbtn.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();

            } else {
                showAlert("Sai tên đăng nhập hoặc mật khẩu!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Lỗi khi kết nối database!");
        }
    }


    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }




}
