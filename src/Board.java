public class Board {
   int board[][];
   
   public Board() {
      this.board = new int[4][4];
   }

   public void printBoard(){
        for(int i = 0; i < 4; i++){
             for(int j = 0; j < 4; j++){
                System.out.print(board[i][j] + " ");
             }
             System.out.println();
        }

   }

   public void setBoard(int row, int col, int value){
      this.board[row][col] = value;
   }

   public int[][] getBoard(){
      return board;
   }

}
