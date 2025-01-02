import java.util.ArrayList;
import java.util.List;

public class Nqueen {

    public static boolean isSafe(int row, int col, char[][] chess) {

        // row =i, col=j

        // chekcing horizontally

        for (int j = 0; j < chess.length; j++) {
            if (chess[row][j] == 'Q')
                return false;
        }

        // checking vertically

        for (int i = 0; i < chess.length; i++) {
            if (chess[i][col] == 'Q')
                return false;

        }
        // chekinc upward right diagonally

        // checking upward left(diagonally)

        int i = row;
        for (int j = col; i < chess.length && j < chess.length; i++, j++) {
            if (chess[i][j] == 'Q') {
                return false;
            }
        }

        // checking downward right diagonally

        i = row;
        for (int j = col; i >= 0 && j < chess.length; i--, j++) {
            if (chess[i][j] == 'Q') {
                return false;
            }
        }

        // chekcing downward left(diagonally)

        for (int j = col; i < chess.length && j > 0; i++, j--) {
            if (chess[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }

    public static void helper(char chess[][], int column, List<List<String>> allboard) {

        if (column == chess.length) {
            // travers through each row
            // 1. save each roew to list of string
            // 2. save created list in list 1 to allboard list
            // 3. saveboard(chess,allboard)
            // return;
        }

        // i =row
        for (int i = 0; i < chess.length; i++) {
            if (isSafe(i, column, chess)) {
                chess[i][column] = 'Q';
                helper(chess, column + 1, allboard);
                chess[i][column] = '.';
            }

        }

    }

    public static void main(String[] args) {

        List<List<String>> allboard = new ArrayList<>();

        helper(new char[4][4], 0, allboard);

    }

}
