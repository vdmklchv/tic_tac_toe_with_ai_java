package tictactoe;

public class Screen {
    void printField(String[][] field) {
        System.out.println("---------");
        for (String[] row: field) {
            System.out.print("| ");
            System.out.print(String.join(" ", row));
            System.out.print(" |\n");
        }
        System.out.println("---------");
    }
}
