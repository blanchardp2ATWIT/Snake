package com.example.snake_v1;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloApplication extends Application {
    private static final int height = 800;
    private static final int width = 800;
    private static final int size = 20;

    private static Group pane;
    @Override
    public void start(Stage stage) throws IOException {

        pane = new Group();
        Scene scene = new Scene(pane, height, width);


        Rectangle food = setFood();
        Rectangle snake = setSnake();

        pane.getChildren().add(food); // Adds the food object to the scene
        pane.getChildren().add(snake); //Adds the snake object to the scene


        stage.setTitle("Snake game");
        stage.setScene(scene);
        stage.show();
    }

    public static Rectangle setFood()
    {
        //Creates a food and place it at a random coordinates
        int x = (int) (Math.random() * (height-size));// creates a random x coordinate
        int y = (int) (Math.random() * (width -size));//creates a random y coordinate
        Rectangle rectangle = new Rectangle(x, y,size,size);//Create a rectangle Object
        rectangle.setFill(Color.RED);//set the color to red
        return rectangle;
    }

    public static Rectangle setSnake()
    {
        //Creates a new snake a set the heat to the middle of the screen
        Rectangle rectangle = new Rectangle(size,size);//Creates a rectangle Object
        rectangle.setX(height/2);//set the initial x coordinates
        rectangle.setY(width/2);//set the initial y coordinates
        rectangle.setFill(Color.GREEN);//set the color to green
        return rectangle;
    }
    public static void main(String[] args) {
        launch();
    }
}