module com.example.snake_v1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.snake_v1 to javafx.fxml;
    exports com.example.snake_v1;
}