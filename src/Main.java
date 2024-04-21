public class Main {
    public static void main(String[] args) {
        TicTacToeServer server = new TicTacToeServer(12345);
        server.start();
        server.stop();

        System.exit(0);
    }
}