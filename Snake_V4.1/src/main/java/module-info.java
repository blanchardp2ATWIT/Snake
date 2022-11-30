module com.example.snake_v4_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.snake_v4_1 to javafx.fxml;
    exports com.example.snake_v4_1;
}