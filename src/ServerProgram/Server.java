package ServerProgram;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    public static void main(String[] args) {
        new Server();
    }

    Server() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {

            ServerSocket serverSocket = new ServerSocket(8989);
            boolean running = true;

            System.out.println("Server running");
            while (running) {

                Socket socket = serverSocket.accept();
                PlayerServer playerOne = new PlayerServer(socket);

                socket = serverSocket.accept();
                PlayerServer playerTwo = new PlayerServer(socket);
                new Game(playerOne, playerTwo);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
