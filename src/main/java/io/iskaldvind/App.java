package io.iskaldvind;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class App extends JFrame {

    final private Random random = new Random();
    protected boolean isGameFinished = false;
    protected int bombs;
    protected int size;

    public App(final int SIZE) {
        setTitle("Bomb Master");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Font font = new Font("Verdana", Font.PLAIN, 11);
        size = SIZE;
        setBounds(300, 300, size * 80, size * 80);

        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        gameMenu.setFont(font);

        addGame(gameMenu, font, "easy", 5);
        addGame(gameMenu, font, "advanced", 7);
        addGame(gameMenu, font, "hardcore", 9);

        gameMenu.addSeparator();
        JMenuItem exit = new JMenuItem("Exit");
        exit.setFont(font);
        exit.addActionListener(actionEvent -> dispose());
        gameMenu.add(exit);

        menuBar.add(gameMenu);
        setJMenuBar(menuBar);

        bombs = (size * size) / 4;
        int[][] field = new int[size][size];
        setBombs(field);
        setMarks(field);

        JButton[][] buttons = new JButton[size][size];
        boolean[][] opened = new boolean[size][size];

        setLayout(new GridLayout(size, size));
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                JButton button = new JButton();
                button.setFont(new Font("Dialog", Font.BOLD, 30));
                buttons[row][col] = button;
                int rrow = row;
                int ccol = col;
                button.addActionListener(actionEvent -> {
                    if (isGameFinished) return;
                    int fieldValue = field[rrow][ccol];
                    if (fieldValue != -1) {
                        openButton(field, buttons, opened, rrow, ccol);
                        if (checkWin(opened)) {
                            isGameFinished = true;
                            showBombs(buttons, field, -1, -1, true);
                        }
                    } else {
                        isGameFinished = true;
                        button.setText("\u0489");
                        button.setBackground(Color.RED);
                        button.setForeground(Color.WHITE);
                        button.setOpaque(true);
                        button.setBorderPainted(false);
                        showBombs(buttons, field, rrow, ccol, false);
                    }
                });
                add(button);
            }
        }

        setVisible(true);
    }

    private void addGame(JMenu gameMenu, Font font, String description, int size) {
        JMenuItem newEasyGame = new JMenuItem("New " + description + " game");
        newEasyGame.setFont(font);
        newEasyGame.addActionListener(actionEvent -> {
            dispose();
            new App(size);
        });
        gameMenu.add(newEasyGame);
    }

    private void setBombs(int[][] field) {
        int bombsLeft = bombs;
        while (bombsLeft > 0) {
            int row = random.nextInt(size);
            int col = random.nextInt(size);
            if (field[row][col] != -1) {
                field[row][col] = -1;
                bombsLeft--;
            }
        }
    }

    private void setMarks(int[][] field) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (field[row][col] != -1) {
                    int bombs = 0;
                    for (int i = Math.max(row - 1, 0); i < Math.min(row + 2, size); i++) {
                        for (int j = Math.max(col - 1, 0); j < Math.min(col + 2, size); j++) {
                            if (field[i][j] == -1) bombs++;
                        }
                    }
                    field[row][col] = bombs;
                }
            }
        }
    }

    private void showBombs(JButton[][] buttons, int[][] field, int excludeRow, int excludeCol, boolean isWin) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if ((row != excludeRow || col != excludeCol) && field[row][col] == -1) {
                    JButton button = buttons[row][col];
                    if (isWin) {
                        button.setBackground(Color.GREEN);
                    } else {
                        button.setBackground(Color.BLACK);
                    }
                    button.setText("\u0489");
                    button.setForeground(Color.WHITE);
                    button.setOpaque(true);
                    button.setBorderPainted(false);
                }
            }
        }
    }

    private boolean checkWin(boolean[][] opened) {
        int closed = 0;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (!opened[row][col]) closed++;
            }
        }
        return closed <= bombs;
    }

    private void openButton(int[][] field, JButton[][] buttons, boolean[][] opened, int row, int col) {
        JButton button = buttons[row][col];
        opened[row][col] = true;
        int fieldValue = field[row][col];
        button.setBackground(Color.WHITE);
        if (fieldValue != 0) {
            button.setText(fieldValue + "");
        } else {
            openNearestButtons(field, buttons, opened, row, col);
        }
        Color foreground;
        if (fieldValue < 3) {
            foreground = Color.BLUE;
        } else if (fieldValue < 5) {
            foreground = Color.ORANGE;
        } else if (fieldValue < 7) {
            foreground = Color.RED;
        } else {
            foreground = Color.BLACK;
        }
        button.setForeground(foreground);
        button.setOpaque(true);
        button.setBorderPainted(false);
    }

    private void openNearestButtons(int[][] field, JButton[][] buttons, boolean[][] opened, int row, int col) {
        for (int i = Math.max(row - 1, 0); i < Math.min(row + 2, size); i++) {
            for (int j = Math.max(col - 1, 0); j < Math.min(col + 2, size); j++) {
                if ((i != row || j != col) && !opened[i][j]) {
                    openButton(field, buttons, opened, i, j);
                }
            }
        }
    }
}