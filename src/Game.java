public class Game {
    // int[][] board;

    // public Game() {
    //     board = new int[4][4];
    // }

    public void checkWin(Board board) {
        int[][] boardArray = board.getBoard();
        int rowP1 = 0;
        int colP1 = 0;
        int diagP1 = 0;
        int diag2P1 = 0;

        int rowP2 = 0;
        int colP2 = 0;
        int diagP2 = 0;
        int diag2P2 = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (boardArray[i][j] == 1) {
                    rowP1++;
                }
                if (boardArray[j][i] == 1) {
                    colP1++;
                }
                if (boardArray[j][j] == 1) {
                    diagP1++;
                }
                if (boardArray[j][3 - j] == 1) {
                    diag2P1++;
                }

                if (boardArray[i][j] == 2) {
                    rowP2++;
                }
                if (boardArray[j][i] == 2) {
                    colP2++;
                }
                if (boardArray[j][j] == 2) {
                    diagP2++;
                }
                if (boardArray[j][3 - j] == 2) {
                    diag2P2++;
                }
            }
            if (rowP1 == 4 || colP1 == 4 || diagP1 == 4 || diag2P1 == 4) {
                System.out.println("Player 1 wins!");
                System.exit(1);
                return;
            }
            if (rowP2 == 4 || colP2 == 4 || diagP2 == 4 || diag2P2 == 4) {
                System.out.println("Player 2 wins!");
                System.exit(1);
                return;
            }
            rowP1 = 0;
            colP1 = 0;
            diagP1 = 0;
            diag2P1 = 0;

            rowP2 = 0;
            colP2 = 0;
            diagP2 = 0;
            diag2P2 = 0;

        }
        System.out.println("No winner!");
    }





    

    
}
