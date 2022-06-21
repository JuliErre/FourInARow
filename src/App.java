public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        Board board = new Board();
        Game game = new Game();
        Player player1 = new Player(1, board, game,1000);
        Player player2 = new Player(2, board, game,2000);
        
            player1.start();
            player2.start();
            player1.join();
            player2.join();
            
    
           
        
       

    


    }
}
