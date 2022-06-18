package tictactoe;

import java.util.Scanner;
import tictactoe.Enums.*;

public class TicTacGame {
    APP_STATE appState;
    GAME_STATE gameState;
    Player activePlayer;


    void startGame() {
        setAppState(APP_STATE.ON);

        while (isAppOn()) {
            Options options = null;
            while (options == null) {
                showMenu();
                options = getOptions();
            }
            if ("exit".equals(options.getOption_1())) {
                setAppState(APP_STATE.OFF);
            } else {
                Screen screen = new Screen();

                setGameState(GAME_STATE.NOT_FINISHED);
                GameTable gameTable = new GameTable();
                Player[] players = createPlayers(options);
                Player player1 = players[0];
                Player player2 = players[1];

                activePlayer = player1;

                while (gameNotFinished()) {
                    playGame(gameTable, player1, player2, screen);
                }
            }
        }
    }

    private boolean gameNotFinished() {
        return gameState == GAME_STATE.NOT_FINISHED;
    }

    private boolean isAppOn() {
        return appState == APP_STATE.ON;
    }

    private Player[] createPlayers(Options options) {
        PlayerCreator playerCreator = new PlayerCreator();

        return new Player[]{playerCreator.make(options.getOption_2(), "X"),
                playerCreator.make(options.getOption_3(), "O")};
    }

    private void showMenu() {
        System.out.print("Input command: ");
    }

    private Options getOptions() {
        Scanner sc = new Scanner(System.in);
        String commandString = sc.nextLine();
        String[] commandArray = commandString.split(" ");

        if (!isCommandValid(commandArray)) {
            System.out.println("Bad parameters!");
            return null;
        }

        if (commandArray.length == 1) {
            return new Options(commandArray[0], null, null);
        } else {
            return new Options(commandArray[0], commandArray[1], commandArray[2]);
        }
    }

    private boolean isCommandValid(String[] command) {
        if ("exit".equals(command[0])) {
            return true;
        }
        if (!"start".equals(command[0])) {
            return false;
        } else {
            if (command.length != 3) {
                return false;
            }
            if (!"easy".equals(command[1]) && !"user".equals(command[1]) && !"medium".equals(command[1])) {
                return false;
            }
            return "easy".equals(command[2]) || "user".equals(command[2]) || "medium".equals(command[2]);
        }
    }

    void playGame(GameTable gameTable, Player player1, Player player2, Screen screen) {
        screen.printField(gameTable.getField());
        while (gameNotFinished()) {
            activePlayer.move(gameTable, screen);
            switchPlayers(player1, player2);
            setFieldBasedGameState(gameTable);
        }
        printGameState(gameState);
    }

    private void setFieldBasedGameState(GameTable gameTable) {
        // Update game state according to situation on the game field
        if (gameTable.isWinningCombination("X")) {
            setGameState(GAME_STATE.XWIN);
        } else if (gameTable.isWinningCombination("O")) {
            setGameState(GAME_STATE.OWIN);
        } else if (gameTable.getNumberOfFreeElements() == 0 && !gameTable.isWinningCombination("X")
                && !gameTable.isWinningCombination("O")) {
            setGameState(GAME_STATE.DRAW);
        } else if (gameTable.getNumberOfFreeElements() > 0 && !gameTable.isWinningCombination("X")
                && !gameTable.isWinningCombination("O")) {
            setGameState(GAME_STATE.NOT_FINISHED);
        }
    }

    private void setGameState(GAME_STATE newGameState) {
        gameState = newGameState;
    }

    private void setAppState(APP_STATE newAppState) {
        appState = newAppState;
    }

    private void printGameState(GAME_STATE state) {
        switch (state) {
            case DRAW:
                System.out.println("Draw");
                break;
            case XWIN:
                System.out.println("X wins");
                break;
            case OWIN:
                System.out.println("O wins");
                break;
            default:
                System.out.println("Unknown state");
                break;
        }
    }

    private void switchPlayers(Player player1, Player player2) {
        activePlayer = activePlayer == player1 ? player2 : player1;
    }
    
}
