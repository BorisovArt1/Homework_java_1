package Borisov_AS.chat.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientConnection {
    private final OutputStream outputStream;
    private final InputStream inputStream;
    public ClientConnection(String serverAddress, int port) throws IOException{
        Socket socket = new Socket("localhost", 8080);
        outputStream = socket.getOutputStream();
        inputStream = socket.getInputStream();
    }
    public void start(){
        new WriteThread(outputStream).start();
        new ReadThread(inputStream).start();
    }
    public static void main(String[] args){
        try {
            ClientConnection client = new ClientConnection("localhost", 8080);
            client.start();
        } catch (IOException e) {
            System.out.println("Не удалось установить соединение с сервером: " + e.getMessage());
        }
    }
}

