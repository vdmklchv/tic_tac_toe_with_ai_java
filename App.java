package tictactoe;

import java.util.Scanner;

public class App {

    final static int NUMBER_OF_PLAYERS = 2;

    GAME_STATE gameState;
    static GAME_LEVEL gameLevel = GAME_LEVEL.EASY;
    ACTIVE_PLAYER activePlayer;

    APP_STATE appState;

    enum GAME_STATE {
        NOT_FINISHED, DRAW, XWIN, OWIN
    }

    enum APP_STATE {
        ON, OFF
    }

    enum GAME_LEVEL {
        EASY, MEDIUM, HARD
    }

    enum ACTIVE_PLAYER {
        PLAYER1, PLAYER2
    }

    void startGame() {
        setAppState(APP_STATE.ON);

        while (appState == APP_STATE.ON) {
            String[] command = null;
            while (command == null) {
                showMenu();
                command = getCommandArray();
            }
            if ("exit".equals(command[0])) {
                setAppState(APP_STATE.OFF);
            } else {
                setGameState(GAME_STATE.NOT_FINISHED);
                GameTable gameTable = new GameTable();
                activePlayer = ACTIVE_PLAYER.PLAYER1;

                Player[] players = createPlayers(new String[]{command[1], command[2]});
                Player player1 = players[0];
                Player player2 = players[1];

                while (gameState == GAME_STATE.NOT_FINISHED) {
                    playGame(gameTable, player1, player2);
                }
            }
        }
    }

    private Player[] createPlayers(String[] options) {
        Player[] players = new Player[NUMBER_OF_PLAYERS];
        for (int i = 0; i < options.length; i++) {
            String token = i == 0 ? "X" : "O";
            players[i] = getPlayer(options[i], token);
        }
        return players;
    }

    private Player getPlayer(String option, String token) {
        if ("user".equals(option)) {
            return new Player(token);
        }
        if ("easy".equals(option)) {
            return new ComputerPlayer(token, GAME_LEVEL.EASY);
        }
        if ("medium".equals(option)) {
            return new ComputerPlayer(token, GAME_LEVEL.MEDIUM);
        } else {
            return new ComputerPlayer(token, GAME_LEVEL.HARD);
        }
    }

    private void showMenu() {
        System.out.print("Input command: ");
    }

    private String[] getCommandArray() {
        Scanner sc = new Scanner(System.in);
        String commandString = sc.nextLine();
        String[] commandArray = commandString.split(" ");

        if (!isCommandValid(commandArray)) {
            System.out.println("Bad parameters!");
            return null;
        }
        return commandArray;
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
            if (!"easy".equals(command[2]) && !"user".equals(command[2]) && !"medium".equals(command[2])) {
                return false;
            }
            return true;
        }
    }

    void playGame(GameTable gameTable, Player player1, Player player2) {
        gameTable.printField();
        while (gameState == GAME_STATE.NOT_FINISHED) {
            Player currentActivePlayer = getActivePlayer(player1, player2);
            move(gameTable, currentActivePlayer);
        }
    }

    private void move(GameTable gameTable, Player player) {
        int[] coordinates = player.provideCoordinates(gameTable);
        gameTable.placeToken(coordinates, player.getToken());

        gameTable.printField();
        setFieldBasedGameState(gameTable);
        if (gameState != GAME_STATE.NOT_FINISHED) {
            printGameState(gameState);
        }
        switchPlayers();
    }

    private void setFieldBasedGameState(GameTable gameTable) {
        // Update game state according to situation on the game field
        if (gameTable.isWinningCombination("X")) {
            setGameState(GAME_STATE.XWIN);
        } else if (gameTable.isWinningCombination("O")) {
            setGameState(GAME_STATE.OWIN);
        } else if (gameTable.getNumberOfElements() == 0 && !gameTable.isWinningCombination("X")
                && !gameTable.isWinningCombination("O")) {
            setGameState(GAME_STATE.DRAW);
        } else if (gameTable.getNumberOfElements() > 0 && !gameTable.isWinningCombination("X")
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

    private void switchPlayers() {
        activePlayer = activePlayer == ACTIVE_PLAYER.PLAYER1 ? ACTIVE_PLAYER.PLAYER2 : ACTIVE_PLAYER.PLAYER1;
    }

    private Player getActivePlayer(Player player1, Player player2) {
        if (activePlayer == ACTIVE_PLAYER.PLAYER1) {
            return player1;
        } else {
            return player2;
        }
    }
}
