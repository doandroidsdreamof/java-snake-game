public class Game {
    private GamePanel gamePanel;
    private GameWindow gameWindow;


    public Game() {
        GamePanel gamePanel = new GamePanel();
        GameWindow gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus(); // * in this way component recieves key inputs */
    }

}
