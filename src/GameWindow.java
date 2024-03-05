import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindow {
    private JFrame jframe;
    private JPanel buttonsPanel, startButtonPanel, mainPanel;
    private JButton startButton, easyButton, mediumButton, hardButton;
    private String difficulty;
    private GameContext gameContext;

    public GameWindow(GamePanel gamePanel) {
        jframe = new JFrame();
        jframe.setSize(600, 600);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setTitle("JAVA_SNAKE");
        jframe.setLocationRelativeTo(null);

        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
   

        startButton = new JButton("Start Game");
        initButton(startButton);

        startButtonPanel = new JPanel(new FlowLayout());
        startButtonPanel.add(startButton);

        initTabButtons();

        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        mainPanel.add(startButtonPanel, gbc);
        mainPanel.add(buttonsPanel, gbc);

        jframe.add(mainPanel);

        startButton.addActionListener(e -> initGame());

        jframe.setVisible(true);
        jframe.revalidate();
        jframe.repaint();

        this.cleanConsole(jframe);

    }

    private void initTabButtons() {
        buttonsPanel = new JPanel(new FlowLayout());
        easyButton = createButton("Easy");
        mediumButton = createButton("Medium");
        hardButton = createButton("Hard");

        buttonsPanel.add(easyButton);
        buttonsPanel.add(mediumButton);
        buttonsPanel.add(hardButton);

        selectDifficulty(easyButton);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(Color.WHITE);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectDifficulty(button);
            }
        });

        return button;
    }

    private void selectDifficulty(JButton selectedButton) {
        easyButton.setBackground(Color.LIGHT_GRAY);
        mediumButton.setBackground(Color.LIGHT_GRAY);
        hardButton.setBackground(Color.LIGHT_GRAY);
        selectedButton.setBackground(Color.DARK_GRAY);
        this.difficulty = new String(selectedButton.getText().toUpperCase());

        //* handle difficulty */
        GameContext gameContext = GameContext.getInstance();
        gameContext.setDifficulty(this.difficulty);

    }

    private void initButton(JButton startButton) {
        startButton.setFont(new Font("Arial", Font.BOLD, 16));
        startButton.setForeground(Color.GREEN);
        startButton.setBackground(new Color(67, 160, 71));
        startButton.setFocusPainted(false);
        startButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    }

    private void initGame() {
        Container contentPane = jframe.getContentPane();
        contentPane.removeAll();
        GamePanel gamePanel = new GamePanel();
        contentPane.add(gamePanel);
        gamePanel.requestFocusInWindow();
        contentPane.revalidate();
        contentPane.repaint();
    }

    // * Clean console history when frame is closed
    public void cleanConsole(JFrame frame) {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.exit(0);
            }
        });
    }

}
