package clientProgram;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientConnection implements Runnable {
    Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;

    Controller controller;


    ClientConnection(Socket socket, Controller controller) throws IOException {
        this.controller = controller;
        this.socket = socket;
        Thread thread = new Thread(this);
        if (socket.isConnected()){
            controller.incomingQuestion.setText("Connected");
        }
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("client started");
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());

            receiveFromServer();

            System.out.println("closing outputstreams");

            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void receiveFromServer() {
        Thread thread = new Thread(() -> {
            try {
                Object objectFromServer;
                while ((objectFromServer = in.readObject()) != null){
                    if (objectFromServer instanceof String){
                        String text = "Server: " + (String) objectFromServer + "\n";
                        controller.txtHistory.appendText(text);
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    public void sendToServer(Object objectToSend) {
        try {
            out.writeObject(objectToSend);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
