package ServerProgram;

import res.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class PlayerServer implements Runnable{

    Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;

    public PlayerServer(Socket socket){
        this.socket = socket;
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        receiveFromClient();
    }

    private void receiveFromClient() {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("awaiting object from client");
                in = new ObjectInputStream(socket.getInputStream());
                Object objectFromClient;
                while ((objectFromClient = in.readObject()) != null){
                    if(objectFromClient instanceof Message){
                        Message message = (Message) objectFromClient;
                        System.out.println(message.getMessage());
                    }
                    else {
                        System.out.println("We got something else");
                    }
                    System.out.println("Loop complete");
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
}
