package com.example.snake_v4_1;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

    public static Rectangle snake,food;

    //this variable currentDirection is the variable we will use to set our snakes direction
    private Direction currentDirection;
    @Override
    public void start(Stage stage) throws IOException {

        pane = new Group();
        Scene scene = new Scene(pane, height, width);

        //Made the food and snake static, if not keep calling the method setSnake and the position would be always the same
        food = setFood();
        snake = setSnake();

        /*
    To change the point in which the snake goes to we have to use a thread
    The runnable will run forever and will also call the move method to move our snake
    until the game is ended or the user loses
        */
        Runnable r = () -> {

            try {

                for (; ; ) {

                    move();

                    //The thread is what makes the snake move continuously, the lowed the millis the faster it travels
                    Thread.sleep(50);
                }
            }
            catch (InterruptedException ie) {
            }

        };

        pane.getChildren().add(food);
        pane.getChildren().add(snake);

        /*when the game starts up our snakes initial direction will be moving up and
        the user will then press keys to change direction of snake
         */
        currentDirection = Direction.UP;


        //When ever the keyword is press change the position on food
        scene.addEventFilter(KeyEvent.KEY_PRESSED,event ->
                /*
                When the user presses an arrow key the
                direction value will be changed
                 */
        {
            KeyCode code = event.getCode();
            if (code.equals(KeyCode.UP))
            {
                currentDirection = Direction.UP;
                System.out.println("Up");
            }
            else if (code == KeyCode.DOWN)
            {
                currentDirection = Direction.DOWN;
                System.out.println("Down");
            }

            else if (code.equals(KeyCode.RIGHT))
            {
                currentDirection = Direction.RIGHT;
                System.out.println("Right");
            }

            else if (code.equals(KeyCode.LEFT))
            {
                currentDirection = Direction.LEFT;
                System.out.println("Left");
            }

        });



        stage.setTitle("Snake game");
        stage.setScene(scene);
        stage.show();

        //initiaiting the thread and starting the thread
        Thread th = new Thread(r);
        th.setDaemon(true);
        th.start();

    }

    public void CheckCollision ()
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
    private void step()
            /*this method is used to show if the snake
            is moving upwards then it will continue to go
            upwards unless a different arrow key is pressed
            (depends on currentDirection and point will be changed accordingly)
             */
    {
        if (currentDirection == Direction.UP){
            snake.setY(snake.getY() - 6);
        }

        else if (currentDirection == Direction.DOWN) {
            snake.setY(snake.getY() + 6); }

        else if (currentDirection == Direction.LEFT) {
            snake.setX(snake.getX() - 6); }

        else if (currentDirection == Direction.RIGHT) {
            snake.setX(snake.getX() + 6); }
    }


    private void move()
    {
        // Need to add the Platform class and call the runLater method
        Platform.runLater(() ->{
            step();
            CheckCollision(); //Call the method CheckCollision here, otherwise won't work.
        });
    }

    public static void main(String[] args) {
        launch();
    }
}