package com.example.snake_v3;

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
    //sets the height
    private static final int height = 800;
    //sets the width
    private static final int width = 800;
    //sets the size
    private static final int size = 20;

    private static final int state  = 0;
    private static Group pane;
    @Override
    public void start(Stage stage) throws IOException {

        pane = new Group();
        Scene scene = new Scene(pane, height, width);


        Rectangle food = setFood();
        Rectangle snake = setSnake();

        pane.getChildren().add(food);
        pane.getChildren().add(snake);


        //When ever the keyword is press change the position on food
        scene.addEventFilter(KeyEvent.KEY_PRESSED,event ->
        {
            KeyCode code = event.getCode();
            if (code.equals(KeyCode.UP))
            {
                snake.setY(snake.getY() - 6);
            }
            else if (code == KeyCode.DOWN)
            {
                snake.setY(snake.getY() + 6);
            }

            else if (code.equals(KeyCode.RIGHT))
            {
                snake.setX(snake.getX() + 6);
            }

            else if (code.equals(KeyCode.LEFT))
            {
                snake.setX(snake.getX() - 6);
            }
            CheckCollision(snake,food);
        });



        stage.setTitle("Snake game");
        stage.setScene(scene);
        stage.show();
    }

    public void CheckCollision (Rectangle snake, Rectangle food)
    {
        if (snake.getBoundsInParent().intersects(food.getBoundsInParent()))
        {
            System.out.println("Eat");
            food.setX((int) (Math.random() * (height-size)));
            food.setY((int) (Math.random() * (width-size)));
        }
        else if (snake.getX() <= 0 || snake.getY() <= 0 ){
            System.out.println("you hit the border");
           System.exit(0);
           // ADD A LOSSE SCREEN HERE OR SOMETHING
        }

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
        rectangle.setX(height/2);
        rectangle.setY(width/2);
        rectangle.setFill(Color.GREEN);//set the color to green
        return rectangle;
    }
    public static void main(String[] args) {
        launch();
    }
}