# Password
# Snake Game

A simple Snake game implemented in Java. The objective is to control a snake to eat food, growing in length with each bite. The game ends if the snake collides with itself or the walls.

## Features

- Classic Snake game mechanics
- Dynamic difficulty as the snake grows
- Simple UI for playing the game

## Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/pappuruswati/Snake.git
Navigate to the project directory:

bash
Copy code
cd Snake
Compile the Java files:

bash
Copy code
javac *.java
Run the game:

bash
Copy code
java Main
Gameplay Instructions
Use the arrow keys (up, down, left, right) to control the snake.
The snake grows longer as it eats food.
The game ends when the snake collides with the wall or itself.
Contributing
Feel free to fork the repository and submit pull requests if you'd like to contribute.

License
This project is open-source and available under the MIT License.

markdown
Copy code

### Steps for Customization:
- **Project Name**: Ensure the project name matches the one in your repository.
- **Installation Instructions**: Adjust if you're using a build tool (like Maven or Gradle).
- **Gameplay Details**: Customize the instructions based on your game’s controls or features.

Feel free to modify it to suit your project further!





You said:
full code matter to copy and paste it
ChatGPT said:
ChatGPT
Sure! Here's a full implementation of a simple Snake game in Java that you can copy and paste into your project files:

1. Main.java
This is the entry point of the game.

java
Copy code
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Random;

public class Main extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int DOT_SIZE = 20;
    private static final int NUM_DOTS = (WIDTH * HEIGHT) / (DOT_SIZE * DOT_SIZE);

    private LinkedList<Point> snake;
    private Point food;
    private int direction;
    private boolean isGameOver;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main game = new Main();
            game.setVisible(true);
        });
    }

    public Main() {
        setTitle("Snake Game");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        snake = new LinkedList<>();
        snake.add(new Point(WIDTH / 2, HEIGHT / 2));
        direction = KeyEvent.VK_RIGHT;  // Start moving right
        isGameOver = false;

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!isGameOver) {
                    int newDirection = e.getKeyCode();
                    if ((newDirection == KeyEvent.VK_UP && direction != KeyEvent.VK_DOWN) ||
                        (newDirection == KeyEvent.VK_DOWN && direction != KeyEvent.VK_UP) ||
                        (newDirection == KeyEvent.VK_LEFT && direction != KeyEvent.VK_RIGHT) ||
                        (newDirection == KeyEvent.VK_RIGHT && direction != KeyEvent.VK_LEFT)) {
                        direction = newDirection;
                    }
                }
            }
        });

        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isGameOver) {
                    move();
                    checkCollision();
                    repaint();
                }
            }
        });
        timer.start();
        
        generateFood();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        if (isGameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("Game Over", WIDTH / 4, HEIGHT / 2);
        } else {
            g.setColor(Color.GREEN);
            for (Point p : snake) {
                g.fillRect(p.x, p.y, DOT_SIZE, DOT_SIZE);
            }
            
            g.setColor(Color.RED);
            g.fillRect(food.x, food.y, DOT_SIZE, DOT_SIZE);
        }
    }

    private void move() {
        Point head = snake.getFirst();
        Point newHead = null;

        switch (direction) {
            case KeyEvent.VK_UP:
                newHead = new Point(head.x, head.y - DOT_SIZE);
                break;
            case KeyEvent.VK_DOWN:
                newHead = new Point(head.x, head.y + DOT_SIZE);
                break;
            case KeyEvent.VK_LEFT:
                newHead = new Point(head.x - DOT_SIZE, head.y);
                break;
            case KeyEvent.VK_RIGHT:
                newHead = new Point(head.x + DOT_SIZE, head.y);
                break;
        }

        snake.addFirst(newHead);
        
        if (newHead.equals(food)) {
            generateFood();
        } else {
            snake.removeLast();
        }
    }

    private void checkCollision() {
        Point head = snake.getFirst();

        // Check if snake collides with the wall
        if (head.x < 0 || head.x >= WIDTH || head.y < 0 || head.y >= HEIGHT) {
            isGameOver = true;
        }

        // Check if snake collides with itself
        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                isGameOver = true;
            }
        }
    }

    private void generateFood() {
        Random rand = new Random();
        food = new Point(rand.nextInt(WIDTH / DOT_SIZE) * DOT_SIZE,
                         rand.nextInt(HEIGHT / DOT_SIZE) * DOT_SIZE);
    }
}
2. README.md
Here’s a README.md file that you can use for your Snake game repository.

markdown
Copy code
# Snake Game

A simple Snake game implemented in Java. The objective is to control a snake to eat food, growing in length with each bite. The game ends if the snake collides with itself or the walls.

## Features

- Classic Snake game mechanics
- Dynamic difficulty as the snake grows
- Simple UI for playing the game

## Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/pappuruswati/Snake.git
Navigate to the project directory:

bash
Copy code
cd Snake
Compile the Java files:

bash
Copy code
javac Main.java
Run the game:

bash
Copy code
java Main
Gameplay Instructions
Use the arrow keys (up, down, left, right) to control the snake.
The snake grows longer as it eats food.
The game ends when the snake collides with the wall or itself.
Contributing
Feel free to fork the repository and submit pull requests if you'd like to contribute.

License
This project is open-source and available under the MIT License.

markdown
Copy code

### How to Run the Game:

1. Copy both `Main.java` and `README.md` files into your project directory.
2. Open a terminal in your project directory and run the following commands:

   ```bash
   javac Main.java
   java Main
This will compile and run the Snake game in a window, and you can start playing right away.
