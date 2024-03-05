import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {
    private Snake snake;
    private int cellSize;

    public Controller(Snake snake, int cellSize) {
        this.snake = snake;
        this.cellSize = cellSize;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP && snake.getDy() != cellSize) {
            // System.out.println("up key pressed");
            snake.setDy(-cellSize);
            snake.setDx(0);

        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN && snake.getDy() != -cellSize) {
            // System.out.println("down key pressed");
            snake.setDy(cellSize);
            snake.setDx(0);

        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT && snake.getDx() != -cellSize) {
            // System.out.println("right key pressed");
            snake.setDy(0);
            snake.setDx(cellSize);
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT && snake.getDx() != cellSize) {
            // System.out.println("left key pressed");
            snake.setDy(0);
            snake.setDx(-cellSize);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
       // System.out.println(e.getKeyCode());
    }

}