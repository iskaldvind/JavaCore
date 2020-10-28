package io.iskaldvind;

import java.util.Random;
import java.util.Scanner;

public class Lesson4 {

    public static final Scanner scanner = new Scanner(System.in);
    public static final Random random = new Random();

    // Game configuration params:
    // 0 - field size: String parsable to int
    // 1 - game description: String
    // 2 - number of player's marks in a line to win: String parsable to int
    // 3 - columns labels: String of chars separated by single space
    // 4 - rows labels: String of chars separated by single space
    public static final String[][] GAMES = {
            {"3", "3x3 field game", "3", "1 2 3", "A B C"},
            {"5", "5x5 field game", "4", "1 2 3 4 5", "A B C D E"}
    };
    public static final int FIELDSIZE = 0;
    public static final int DESCRIPTION = 1;
    public static final int WINCONDITION = 2;
    public static final int COLLABELS = 3;
    public static final int ROWLABELS = 4;
    public static final int NUMBEROFPLAYERS = 2;
    public static int selectedGameIndex;
    public static int gameFieldSize;
    public static int[][] gameField;
    public static int gameWinLength = 0;
    public static String[] gameColLabels;
    public static String[] gameRowLabels;

    public static boolean isGameChosen = false;

    public static final String[] MARKS = {".", "X", "O"};
    public static int playerControlledByHuman;
    public static boolean isPlayerChosen = false;

    public static final String[] DIFFICULTIES = {"Easy", "Hard"};
    public static int difficulty;
    public static int mostDangerousOpponentLineSize = 0;
    public static int mostDangerousOpponentPointRow = -1;
    public static int mostDangerousOpponentPointCol = -1;

    public static boolean isDifficultyChosen = false;

    public static int playerTurn;
    public static final int RUNNING = 0;
    public static final int DRAW = 1;
    public static final int WIN = 2;
    public static int gameState = 0;

    public static boolean isPlayAgain = false;


    public static void main(String[] args) {
        while (true) {
            configureGame();
            if (!isGameChosen) break;
            gameTurn();
            askPlayAgain();
            if (!isPlayAgain) {
                break;
            } else {
                gameState = RUNNING;
                isGameChosen = false;
                isPlayerChosen = false;
                isDifficultyChosen = false;
            }
        }
    }

    public static void configureGame() {
        while (true) {
            if (!isGameChosen) chooseGame();
            if (!isGameChosen) break;
            if (!isPlayerChosen) choosePlayer();
            if (!isPlayerChosen) {
                isGameChosen = false;
                continue;
            }
            if (!isDifficultyChosen) chooseDifficulty();
            if (!isDifficultyChosen) {
                isPlayerChosen = false;
                continue;
            }
            break;
        }
    }

    public static void chooseGame() {
        final int EXITOPTION = 0;
        while (true) {
            int nextOption = EXITOPTION;
            String gameOptions = "Choose game: " + nextOption + " - Exit";
            for (String[] game : GAMES) {
                nextOption++;
                gameOptions += ", " + nextOption + " - " + game[DESCRIPTION];
            }
            System.out.println(gameOptions);

            int gameChoice = Integer.parseInt(scanner.nextLine());
            if (gameChoice == EXITOPTION) {
                isGameChosen = false;
                break;
            } else if (gameChoice > nextOption) {
                System.out.println("Wrong choice, please try again\n");
                continue;
            }
            selectedGameIndex = gameChoice - 1 - EXITOPTION;
            String[] game = GAMES[selectedGameIndex];
            gameFieldSize = Integer.parseInt(game[FIELDSIZE]);
            gameField = new int[gameFieldSize][gameFieldSize];
            for (int rowN = 0; rowN < gameFieldSize; rowN++) {
                for (int colN = 0; colN < gameFieldSize; colN++) {
                    gameField[rowN][colN] = 0;
                }
            }
            gameColLabels = game[COLLABELS].split(" ");
            gameRowLabels = game[ROWLABELS].split(" ");
            gameWinLength = Integer.parseInt(game[WINCONDITION]);
            isGameChosen = true;
            break;
        }
    }

    public static void choosePlayer() {
        final int BACKOPTION = 0;
        while (true) {
            int player = BACKOPTION;
            String playerOptions = "Choose your player: " + BACKOPTION + " - Back";
            for (int i = 1; i <= NUMBEROFPLAYERS; i++) {
                player++;
                playerOptions += ", " + player + " - '" + MARKS[player] + "'";
            }
            System.out.println(playerOptions);
            int userChoice = Integer.parseInt(scanner.nextLine());
            if (userChoice < BACKOPTION || userChoice > BACKOPTION + NUMBEROFPLAYERS) {
                System.out.println("Wrong choice, please try again\n");
                continue;
            } else if (userChoice == BACKOPTION) {
                isPlayerChosen = false;
                break;
            }
            playerControlledByHuman = userChoice;
            isPlayerChosen = true;
            break;
        }
    }

    public static void chooseDifficulty() {
        final int BACKOPTION = 0;
        final int NUMBEROFDIFFICULTIES = 2;
        while (true) {
            int difficultyOption = BACKOPTION;
            String difficultyOptions = "Choose difficulty: " + BACKOPTION + " - Back";
            for (int i = 1; i <= NUMBEROFDIFFICULTIES; i++) {
                difficultyOption++;
                difficultyOptions += ", " + difficultyOption + " - " + DIFFICULTIES[difficultyOption - 1];
            }
            System.out.println(difficultyOptions);
            int userChoice = Integer.parseInt(scanner.nextLine());
            if (userChoice < BACKOPTION || userChoice > BACKOPTION + NUMBEROFDIFFICULTIES) {
                System.out.println("Wrong choice, please try again\n");
                continue;
            } else if (userChoice == BACKOPTION) {
                isDifficultyChosen = false;
                isPlayerChosen = false;
                break;
            }
            difficulty = userChoice - 1;
            isDifficultyChosen = true;
            break;
        }
    }

    public static void gameTurn() {
        printField();
        while (true) {
            for (int i = 1; i <= NUMBEROFPLAYERS; i++) {
                playerTurn = i;
                if (playerTurn == playerControlledByHuman) {
                    humanMove();
                    System.out.println("\nYou moved:");
                    printField();
                } else {
                    aiMove();
                    System.out.println("\nComputer moved:");
                    printField();
                }
                checkGameState();
                System.out.println("GAME STATE " + gameState);
                if (gameState == DRAW) {
                    System.out.println("\n\nGame ends with DRAW!\n\n");
                    break;
                } else if (gameState == WIN) {
                    String winText = playerTurn == playerControlledByHuman ?
                            "You ('" + MARKS[playerTurn] + "') WIN the game!":
                            "Computer ('" + MARKS[playerTurn] + "') WINS the game!";
                    System.out.println("\n\n" + winText + "\n\n");
                    break;
                } else {
                    playerTurn++;
                }
            }
            if (gameState == WIN || gameState == DRAW) break;
        }
    }

    public static void printField() {
        String xRow = "   " + GAMES[selectedGameIndex][COLLABELS];
        System.out.println(xRow);
        String[] gameYChars = GAMES[selectedGameIndex][ROWLABELS].split(" ");
        for (int row = 0; row < gameFieldSize; row++) {
            String fieldRow = gameYChars[row] + "  ";
            for (int col = 0; col < gameFieldSize; col++) {
                String fieldMark;
                int fieldPointValue = gameField[row][col];
                if (fieldPointValue == 0) {
                    fieldMark = MARKS[0];
                } else {
                    fieldMark = MARKS[fieldPointValue];
                }
                fieldRow += fieldMark + " ";
            }
            System.out.println(fieldRow);
        }
    }

    public static void humanMove() {
        System.out.println("Choose field to mark: (type row and column without space e.g. '"+ gameRowLabels[0] + gameColLabels[0] +"')");
        while (true) {
            String colRow = scanner.nextLine();
            int colIndex = -1;
            int rowIndex = -1;
            for (int i = 0; i < gameFieldSize; i++) {
                if (gameRowLabels[i].equals(colRow.charAt(0) + "")) colIndex = i;
                if (gameColLabels[i].equals(colRow.charAt(1) + "")) rowIndex = i;
            }
            if (colIndex < 0 || rowIndex < 0) {
                System.out.println("Game field has no such point. Choose other one:");
            } else if (gameField[colIndex][rowIndex] > 0) {
                System.out.println("This poin is already marked. Choose other one:");
            } else {
                gameField[colIndex][rowIndex] = playerTurn;
                break;
            }
        }
    }

    public static void aiMove() {
        if (DIFFICULTIES[difficulty] == "Easy") {
            aiEasyMove();
        } else {
            aiHardMove();
        }
    }

    public static void aiEasyMove() {
        while (true) {
            int col = random.nextInt(gameFieldSize);
            int row = random.nextInt(gameFieldSize);
            if (gameField[col][row] == 0) {
                gameField[col][row] = playerTurn;
                break;
            }
        }
    }

    public static void aiHardMove() {
        if (mostDangerousOpponentPointCol != -1
                && mostDangerousOpponentPointRow != -1
                && gameField[mostDangerousOpponentPointRow][mostDangerousOpponentPointCol] == 0) {
            gameField[mostDangerousOpponentPointRow][mostDangerousOpponentPointCol] = playerTurn;
        } else {
            aiEasyMove();
        }
    }

    public static void checkGameState() {
        mostDangerousOpponentLineSize = 0;
        mostDangerousOpponentPointCol = -1;
        mostDangerousOpponentPointCol = -1;

        // Check rows
        for (int rowN = 0; rowN < gameFieldSize; rowN++) {
            int startRowIndex = rowN;
            int startColIndex = 0;
            int endRowIndex = rowN;
            int endColIndex = gameFieldSize - 1;
            checkLine(gameField[rowN], startRowIndex, startColIndex, endRowIndex, endColIndex);
            if (gameState == WIN) return;
        }

        // Check columns
        for (int colN = 0; colN < gameFieldSize; colN++) {
            int[] col = new int[gameFieldSize];
            for (int rowN = 0; rowN < gameFieldSize; rowN++) {
                col[rowN] = gameField[rowN][colN];
            }
            int startRowIndex = 0;
            int startColIndex = colN;
            int endRowIndex = gameFieldSize - 1;
            int endColIndex = colN;
            checkLine(col, startRowIndex, startColIndex, endRowIndex, endColIndex);
            if (gameState == WIN) return;
        }

        // Check top Diagonals (from elements of top row)
        for (int colN = 0; colN < gameFieldSize; colN++) {
            // Top to left diagonal - if there are at least $gameWinLength elements to the left of column including it
            if (colN >= gameWinLength - 1) {
                int[] topToLeftDiagonal = new int[colN + 1]; // Elements in this diagonal
                for (int rowN = 0; rowN <= colN; rowN++) {
                    topToLeftDiagonal[rowN] = gameField[rowN][colN-rowN];
                }
                int startRowIndex = 0;
                int startColIndex = colN;
                int endRowIndex = colN;
                int endColIndex = gameFieldSize - 1;
                checkLine(topToLeftDiagonal, startRowIndex, startColIndex, endRowIndex, endColIndex);
                if (gameState == WIN) return;
            }
            // Top to right diagonal - if there are at least $gameWinLength elements to the right of column including it
            if (colN <= gameFieldSize - gameWinLength) {
                int[] topToRightDiagonal = new int[gameFieldSize - colN]; // Elements in this diagonal
                for (int rowN = 0; rowN < gameFieldSize - colN; rowN++) {
                    topToRightDiagonal[rowN] = gameField[rowN][colN+rowN];
                }
                int startRowIndex = 0;
                int startColIndex = colN;
                int endRowIndex = gameFieldSize - colN - 1;
                int endColIndex = gameFieldSize - gameWinLength;
                checkLine(topToRightDiagonal, startRowIndex, startColIndex, endRowIndex, endColIndex);
                if (gameState == WIN) return;
            }
        }

        // Check bottom Diagonals (from elements of bottom row)
        for (int colN = 0; colN < gameFieldSize; colN++) {
            // Bottom to left diagonal - if there are at least $gameWinLength elements to the left of column including it
            // excluding right most diagonal which was already checked as top diagonal
            if (colN >= gameWinLength - 1 && colN < gameFieldSize - 1) {
                int[] bottomToLeftDiagonal = new int[colN + 1]; // Elements in this diagonal
                for (int rowN = gameFieldSize - 1; rowN >= gameFieldSize - 1 - colN; rowN--) {
                    bottomToLeftDiagonal[gameFieldSize - 1 - rowN] = gameField[rowN][rowN - 1];
                }
                int startRowIndex = gameFieldSize - 1;
                int startColIndex = colN;
                int endRowIndex = gameFieldSize - colN - 1;
                int endColIndex = gameFieldSize - 2;
                checkLine(bottomToLeftDiagonal, startRowIndex, startColIndex, endRowIndex, endColIndex);
                if (gameState == WIN) return;
            }
            // Bottom to right diagonal - if there are at least $gameWinLength elements to the right of column including it
            // excluding left most diagonal which was already checked as top diagonal
            if (colN > 0 && colN <= gameFieldSize - gameWinLength) {
                int[] bottomToRightDiagonal = new int[gameFieldSize - colN]; // Elements in this diagonal
                for (int rowN = gameFieldSize - 1; rowN >= colN; rowN--) {
                    bottomToRightDiagonal[gameFieldSize - 1 - rowN] = gameField[rowN][colN + gameFieldSize - 1 - rowN];
                }
                int startRowIndex = gameFieldSize - 1;
                int startColIndex = colN;
                int endRowIndex = colN;
                int endColIndex = gameFieldSize - gameWinLength;
                checkLine(bottomToRightDiagonal, startRowIndex, startColIndex, endRowIndex, endColIndex);
                if (gameState == WIN) return;
            }
        }

        // Check if field is complete
        if (gameState == RUNNING) {
            boolean isIncomplete = false;
            for (int rowN = 0; rowN < gameFieldSize; rowN ++) {
                for (int colN = 0; colN < gameFieldSize; colN++) {
                    if (gameField[rowN][colN] == 0) {
                        isIncomplete = true;
                        break;
                    }
                }
                if (isIncomplete) break;
            }
            if (!isIncomplete) {
                gameState = DRAW;
            }
        }
    }

    public static void checkLine(int[] line, int startRowIndex, int startColIndex, int endRowIndex, int endColIndex) {
        int playerValue = 0;
        int valuesLength = 0;
        int opponentDangerWeight = 0; // Count of player marks on line;
        int dangerousPointRow = -1;
        int dangerousPointCol = -1;
        for (int i = 0; i < line.length; i++) {
            if (line[i] == playerControlledByHuman) {
                opponentDangerWeight++;
            } else if (line[i] == 0 && dangerousPointRow == -1 || dangerousPointCol == -1) {
                dangerousPointRow = startRowIndex + i * (endRowIndex - startRowIndex) / line.length;
                dangerousPointCol = startColIndex + i * (endColIndex - startColIndex) / line.length;
            }
            if (line[i] != playerValue || line[i] == 0) {
                playerValue = line[i];
                valuesLength = playerValue != 0 ? 1 : 0;
            } else {
                valuesLength++;
            }
            if (valuesLength == gameWinLength) { // Line length match win condition
                gameState = WIN;
                break;
            } else if (valuesLength + line.length - i - 1 < gameWinLength) { // Left elements is not enough to match win condition
                gameState = RUNNING;
                break;
            }
        }
        if (mostDangerousOpponentLineSize < opponentDangerWeight) {
            mostDangerousOpponentPointRow = dangerousPointRow;
            mostDangerousOpponentPointCol = dangerousPointCol;
        }
    }

    public static void askPlayAgain() {
        while (true) {
            System.out.println("Do you want to play again? (y/n)");
            String answer = scanner.nextLine();
            if ("y".equals(answer)) {
                isPlayAgain = true;
                gameState = RUNNING;
                System.out.println("OK!\n\n");
                return;
            } else if ("n".equals(answer)) {
                isPlayAgain = false;
                System.out.println("Buy!");
                return;
            } else {
                System.out.println("Wrong answer");
            }
        }
    }
}
