import java.util.LinkedList;

public class Snake {
    private LinkedList<Cell> snakeList;
    int cellSize = 20;
    private int dx = 20;
    private int dy = 0;

    public Snake() {
        this.snakeList = new LinkedList<Cell>();
        initSnake(snakeList);

    }

    public LinkedList<Cell> getSnakeList() {
        return snakeList;
    }

    public void initSnake(LinkedList<Cell> snakeList) {
        for (int i = 0; i < 3; i++) {
            snakeList.add(new Cell(i + cellSize, 2));

        }
    }

    public void movement() {
        // * */ x:1 => x:2 => x:3 // getFirst() x:3 + 1(dx) // removeLast() => x2 => x3
        // * */ => x4
        // * */ similar to cinema effect
        Cell newHead = new Cell(snakeList.getFirst().x + dx, snakeList.getFirst().y + dy);
        snakeList.removeLast();
        snakeList.addFirst(newHead);
    }

    public void addSnakeNode() {
        Cell tail = snakeList.getLast();
        Cell beforeTail = snakeList.get(snakeList.size() - 2);

        int deltaX = tail.x - beforeTail.x;
        int deltaY = tail.y - beforeTail.y;

        int newTailX = tail.x + deltaX;
        int newTailY = tail.y + deltaY;
        snakeList.addLast(new Cell(newTailX, newTailY));

    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDx(int x) {
        this.dx = x;
    }

    public void setDy(int y) {
        this.dy = y;
    }

    public Cell getHead() {
        if (snakeList.getFirst() != null) {
            return snakeList.getFirst();
        }
        return null;
    }

}
