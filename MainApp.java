// MainApp.java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Student demoStudent = new Student("S101", "Alice", "1234", 200);
    private Vendor demoVendor = new Vendor("V101", "Canteen Uncle", "1111");

    @Override
    public void start(Stage stage) {
        // -------- Login Screen --------
        Label userLabel = new Label("User ID:");
        TextField userIdField = new TextField();
        Label passLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Button loginBtn = new Button("Login");
        Label message = new Label();

        VBox loginLayout = new VBox(10, userLabel, userIdField, passLabel, passwordField, loginBtn, message);
        Scene loginScene = new Scene(loginLayout, 300, 200);

        // -------- Student Dashboard --------
        Label welcome = new Label();
        Label balanceLabel = new Label();
        Button orderBtn = new Button("Buy Lunch (₹50)");
        Button addMoneyBtn = new Button("Add ₹100");
        Button historyBtn = new Button("View Orders");
        ListView<String> orderList = new ListView<>();

        VBox studentLayout = new VBox(10, welcome, balanceLabel, orderBtn, addMoneyBtn, historyBtn, orderList);
        Scene studentScene = new Scene(studentLayout, 400, 300);

        // -------- Vendor Dashboard --------
        Label vendorWelcome = new Label();
        ListView<String> vendorOrders = new ListView<>();
        TextField qrInput = new TextField();
        qrInput.setPromptText("Enter QR code...");
        Button serveBtn = new Button("Serve Order");

        VBox vendorLayout = new VBox(10, vendorWelcome, vendorOrders, qrInput, serveBtn);
        Scene vendorScene = new Scene(vendorLayout, 400, 300);

        // -------- Actions --------
        loginBtn.setOnAction(e -> {
            String id = userIdField.getText();
            String pwd = passwordField.getText();

            if (demoStudent.getId().equals(id) && demoStudent.checkPassword(pwd)) {
                welcome.setText("Welcome, " + demoStudent.getName());
                balanceLabel.setText("Balance: ₹" + demoStudent.getBalance());
                stage.setScene(studentScene);
            } else if (demoVendor.getId().equals(id) && demoVendor.checkPassword(pwd)) {
                vendorWelcome.setText("Welcome, " + demoVendor.getName());
                vendorOrders.getItems().clear();
                for (Order o : demoStudent.getOrders()) {
                    vendorOrders.getItems().add(o.toString());
                }
                stage.setScene(vendorScene);
            } else {
                message.setText("Invalid login!");
            }
        });

        orderBtn.setOnAction(e -> {
            if (demoStudent.placeOrder("Lunch", 50)) {
                balanceLabel.setText("Balance: ₹" + demoStudent.getBalance());
                Order latest = demoStudent.getOrders().get(demoStudent.getOrders().size() - 1);
                Alert qrPopup = new Alert(Alert.AlertType.INFORMATION);
                qrPopup.setTitle("Order QR Code");
                qrPopup.setHeaderText("Your order is confirmed!");
                qrPopup.setContentText("Show this at counter:\nQR-" + latest.getOrderId());
                qrPopup.show();
            } else {
                balanceLabel.setText("Not enough balance!");
            }
        });

        addMoneyBtn.setOnAction(e -> {
            demoStudent.addBalance(100);
            balanceLabel.setText("Balance: ₹" + demoStudent.getBalance());
        });

        historyBtn.setOnAction(e -> {
            orderList.getItems().clear();
            for (Order o : demoStudent.getOrders()) {
                orderList.getItems().add(o.toString());
            }
        });

        serveBtn.setOnAction(e -> {
            String qr = qrInput.getText().trim();
            boolean found = false;
            for (Order o : demoStudent.getOrders()) {
                if (qr.equals("QR-" + o.getOrderId())) {
                    demoVendor.serveOrder(o);
                    Alert confirm = new Alert(Alert.AlertType.INFORMATION);
                    confirm.setHeaderText("Order Served");
                    confirm.setContentText("Order " + o.getOrderId() + " marked as served!");
                    confirm.show();
                    found = true;
                    break;
                }
            }
            if (!found) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setHeaderText("Invalid QR");
                error.setContentText("No order found with that QR code.");
                error.show();
            }
            qrInput.clear();
        });

        // -------- Start App --------
        stage.setTitle("University Payment System");
        stage.setScene(loginScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
