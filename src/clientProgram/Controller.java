package clientProgram;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import res.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Controller {

    ClientConnection clientConnection;

    @FXML
    Label incomingQuestion;

    @FXML
    TextArea txtHistory;

    @FXML
    TextField txtToServer;

    public void initialize() throws IOException {
        Socket clientSocket = new Socket("127.0.0.1", 8989);
        clientConnection = new ClientConnection(clientSocket, this);
    }

    public void sendMessage(ActionEvent actionEvent) {
        String text = "Client: " + txtToServer.getText();
        if(text.length() > 0){
            txtHistory.appendText( text + "\n");
            txtToServer.setText("");
            Message messageToServer = new Message(text);
            clientConnection.sendToServer(messageToServer);
        }
    }

}
