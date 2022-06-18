package tictactoe;

public interface Player {
    String getToken();
    Coordinate provideCoordinates(GameTable gameTable);
    void move(GameTable gameTable, Screen screen);

}
