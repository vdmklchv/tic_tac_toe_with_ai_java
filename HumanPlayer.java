package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer implements Player {

    private final String token;

    public HumanPlayer(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public Coordinate provideCoordinates(GameTable gameTable) {
        Scanner sc = new Scanner(System.in);
        int coordinate_1;
        int coordinate_2;
        while (true) {
            try {
                System.out.print("Enter the coordinates: ");
                coordinate_1 = sc.nextInt();
                coordinate_2 = sc.nextInt();
                if (coordinate_1 < 1 || coordinate_1 > 3 || coordinate_2 < 1 || coordinate_2 > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }
                return new Coordinate(coordinate_1 - 1, coordinate_2 - 1); // - 1 to compensate for zero-based indexing
            } catch (IllegalArgumentException | InputMismatchException e) {
                System.out.println("You should enter numbers!");
                sc.nextLine();
            }
        }
    }

    public void move(GameTable gameTable, Screen screen) {
        Coordinate coordinate = provideCoordinates(gameTable);
        while (gameTable.isCellOccupied(coordinate)) {
            System.out.println("This cell is occupied! Choose another one!");
            coordinate = provideCoordinates(gameTable);
        }
        gameTable.placeToken(coordinate, getToken());
        screen.printField(gameTable.getField());
    }
}
