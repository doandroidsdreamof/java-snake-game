import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.LinkedList;

public class GamePanel extends JPanel implements ActionListener {
    private static final Random random = new Random();
    private Snake snake;
    private Timer timer;
    private Cell food;
    private GameContext gameContext;
    private JLabel score;
    // * hard-coded screen sizes to => getWindth() && getHeight() */
    private final static int MAX_COORDINATE = 600;
    int gapSize = 3;
    static int cellSize = 20;

    public GamePanel() {
        // * Bind keys to controller. */
        snake = new Snake();
        addKeyListener(new Controller(snake, cellSize));
        this.initFood();
        this.gameContext = GameContext.getInstance();
        this.score = new JLabel("Score: " + gameContext.getScore());
        this.score.setOpaque(true);
        this.add(score);
        this.timer = new Timer(gameContext.getDelay(), this);
        this.timer.start();
    }

    private void initFood() {
        int x = random.nextInt((int) (MAX_COORDINATE / cellSize) - 1) * cellSize;
        int y = random.nextInt((int) (MAX_COORDINATE / cellSize) - 1) * cellSize;
        food = new Cell(x, y);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawSnake(g);
        drawFood(g);

    }

    private void drawSnake(Graphics g) {
        g.setColor(Color.RED);
        for (Cell cell : snake.getSnakeList()) {
            g.fillRect(cell.getX(), cell.getY(), cellSize - gapSize, cellSize - gapSize);

        }
    }

    private void drawFood(Graphics g) {
        g.setColor(Color.GREEN);
        if (this.food != null) {
            g.fillRect(food.x, food.y, cellSize - gapSize, cellSize - gapSize);

        }
    }

    private void checkFood() {
        Cell head = snake.getHead();
        if (Math.abs(head.x - food.x) < cellSize && Math.abs(head.y - food.y) < cellSize) {
            initFood();
            snake.addSnakeNode();
            this.gameContext.incrementScore();
            this.score.setText("Score: " + this.gameContext.getScore());
        }
    }

    private void resetOverflow(Cell cell) {
        if (cell.getX() >= this.getWidth()) {
            cell.setX(0);
        } else if (cell.getX() < 0) {
            cell.setX(this.getWidth());
        }
        if (cell.getY() >= this.getHeight()) {
            cell.setY(0);
        } else if (cell.getY() < 0) {
            cell.setY(this.getHeight());
        }

    }

    // TODO TİCK_FRAME based game loop design
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(this.gameContext.getGameState());
        if (!this.gameContext.getGameState()) {
            snake.movement();
            checkFood();
            collisionDetector();
            for (Cell cell : snake.getSnakeList()) {
                resetOverflow(cell);
            }
        }
        repaint();

    }

    public void collisionDetector() {
        int snakeListSize = snake.getSnakeList().size();
        if (snakeListSize > 5) {
            LinkedList<Cell> snakeBody = snake.getSnakeList();
            Cell snakeHead = snake.getHead();
            for (int i = 1; i < snake.getSnakeList().size(); i++) {
                Cell cell = snakeBody.get(i);
                if (snakeHead.x == cell.x && snakeHead.y == cell.y) {
                    System.out.println("collision detected");
                    this.gameContext.setState(true);
                    this.resetGame();
                    break;

                }
            }

        }

    }

    public void resetGame() {
        this.snake = new Snake();
        addKeyListener(new Controller(snake, cellSize));
        initFood();
        this.gameContext.resetScore();
        score.setText("Score: " + this.gameContext.getScore());
        timer.setDelay(this.gameContext.getDelay());
        setFocusable(true);
        requestFocusInWindow();
        timer.restart();
        this.gameContext.setState(false);

    }

}
