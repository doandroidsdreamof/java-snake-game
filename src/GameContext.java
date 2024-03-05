public class GameContext {
    private boolean gameover;
    private static GameContext instance;
    private int DELAY;
    private int score;

    public static GameContext getInstance() {
        if (instance == null) {
            synchronized (GameContext.class) {
                if (instance == null) {
                    instance = new GameContext();
                }
            }
        }
        return instance;
    }

    private GameContext() {
        this.gameover = false;
        this.score = 0;
    }

    public void setState(boolean state) {
        this.gameover = state;
    }

    public boolean getGameState() {
        return this.gameover;
    }

    public int getScore() {
        return this.score;
    }

    public int getDelay() {
        return this.DELAY;
    }

    public void resetScore() {
        this.score = 0;
    }

    public void incrementScore() {
        this.score++;
    }

    public void setDifficulty(String difficulty) {
        if ("EASY".equals(difficulty)) {
            System.out.println("EASY ====>" + difficulty);
            this.DELAY = 75;
        }
        if ("MEDIUM".equals(difficulty)) {
            System.out.println("MEDIUM ====>" + difficulty);
            this.DELAY = 60;

        }
        if ("HARD".equals(difficulty)) {
            System.out.println("HARD ====>" + difficulty);
            this.DELAY = 40;

        }
    }
}
