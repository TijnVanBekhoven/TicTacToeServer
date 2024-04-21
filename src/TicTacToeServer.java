import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TicTacToeServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

    private final int port;

    public TicTacToeServer(int port) {
        this.port = port;
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            System.out.println("new connection: " + clientSocket.getPort());

            TicTacToeGame game = new TicTacToeGame();
            sendMessage(game.start());

            while (true) {
                String clientMove = in.readLine();
                System.out.println(clientMove);
                if (clientMove.equals("close")) break;

                sendMessage(game.play(clientMove));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void stop() {
        try {
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void sendMessage(String msg) {
        out.println(msg + "\nETX");
    }
}
