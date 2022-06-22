import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Player extends Thread {
    private int playerNumber;
    private Board board;
    private int[][] boardArray;

    private static final Semaphore FREE_SEMAPHORE;
    private static final Semaphore WORK_SEMAPHORE;
    private static final Semaphore MUTEX_SEMAPHORE;

    static {
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
            if (playerNumber == 1) {
                try {
                    WORK_SEMAPHORE.acquire();
                    MUTEX_SEMAPHORE.acquire();

                    int[][] b = board.getBoard();

                    System.out.println("Player " + playerNumber + " turn");

                    int row = -1;
                    int col = scan.nextInt();

                    if (col <= 3) {

                        for (int i = 3; i >= 0; i--) {
                            if (b[i][col] == 0) {
                                row = i;
                                break;
                            }

                        }
                    }

                    while (row == -1 || col > 3) {
                        System.out.println("Invalid move, try again: ");
                        col = scan.nextInt();

                        if (col <= 3) {
                            for (int i = 3; i >= 0; i--) {
                                if (b[i][col] == 0) {
                                    row = i;
                                    break;
                                }

                            }
                        }
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
            } else {

                try {
                    FREE_SEMAPHORE.acquire();
                    MUTEX_SEMAPHORE.acquire();

                    int[][] b = board.getBoard();

                    System.out.println("Player " + playerNumber + " turn");

                    int row = -1;
                    int col = scan.nextInt();

                    if (col <= 3) {
                        for (int i = 3; i >= 0; i--) {
                            if (b[i][col] == 0) {
                                row = i;
                                break;
                            }

                        }
                    }

                    while (row == -1 || col > 3) {
                        System.out.println("Invalid move, try again: ");
                        col = scan.nextInt();

                        if (col <= 3) {
                            for (int i = 3; i >= 0; i--) {
                                if (b[i][col] == 0) {
                                    row = i;
                                    break;
                                }

                            }
                        }
                    }

                    board.setBoard(row, col, playerNumber);
                    board.printBoard();
                    game.checkWin(board);

                    MUTEX_SEMAPHORE.release();
                    WORK_SEMAPHORE.release();
                    Thread.sleep(time);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
