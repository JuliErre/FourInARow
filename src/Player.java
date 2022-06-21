import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Player extends Thread  {
    private int playerNumber;
    private Board board;
    private int[][] boardArray;

    private static final Semaphore FREE_SEMAPHORE;
    private static final Semaphore WORK_SEMAPHORE;
    private static final Semaphore MUTEX_SEMAPHORE;

    static{
        FREE_SEMAPHORE = new Semaphore(0);
        WORK_SEMAPHORE = new Semaphore(1);
        MUTEX_SEMAPHORE = new Semaphore(2);
    }

    Semaphore semaphore = new Semaphore(1);


    private Game game;
    private int time;
    private boolean turn;

    public Player(int playerNumber, Board board, Game game, int time) {
        this.playerNumber = playerNumber;
        this.board = board;
        this.boardArray = board.getBoard();
        this.game = game;
        this.time = time;
        this.turn = true;
    }

    public void run() {
        Scanner scan = new Scanner(System.in);
        
        
        while (true) {
            if(playerNumber == 1){
                try {
                    WORK_SEMAPHORE.acquire();
                    MUTEX_SEMAPHORE.acquire();
                    
                 
                    int[][] b = board.getBoard();
                    
                    
                    System.out.println("Player "+ playerNumber+" turn");
                    
                    int row = scan.nextInt();
                    int col = scan.nextInt();
                    
                    
                    while (row > 3 || col > 3 ||b[row][col] != 0) {
                        System.out.println("Invalid move, try again: ");
                        row = scan.nextInt();
                        col = scan.nextInt();
                       }
                       board.setBoard(row, col, playerNumber);
                       board.printBoard();
                       game.checkWin(board);
                       
                       MUTEX_SEMAPHORE.release();
                FREE_SEMAPHORE.release();
                     
                       Thread.sleep(time); 
                      
                       
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else{

                try {
                    FREE_SEMAPHORE.acquire();
                    MUTEX_SEMAPHORE.acquire();
                         
                         int[][] b = board.getBoard();
                         
                         
                         System.out.println("Player "+ playerNumber+" turn");
                         
                         int row = scan.nextInt();
                         int col = scan.nextInt();
                         
                         
                         while (row > 3 || col > 3 ||b[row][col] != 0) {
                             System.out.println("Invalid move, try again: ");
                             row = scan.nextInt();
                             col = scan.nextInt();
                            }
                            board.setBoard(row, col, playerNumber);
                            board.printBoard();
                            game.checkWin(board);
                            
                           
                            MUTEX_SEMAPHORE.release();
                            WORK_SEMAPHORE.release();
                            Thread.sleep(time); 
                            
                    }
                 catch (Exception e) {
                    e.printStackTrace();
                }
            }

    
       
    }

    
    }
}
