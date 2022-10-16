package sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void listenMessage() {

        try (ServerSocket server = new ServerSocket(1234)) {

            server.setReuseAddress(true);

            do {

                Socket client = server.accept();
                System.out.println("New client connected "
                        + client.getInetAddress()
                        .getHostAddress());

                ClientHandler clientSock
                        = new ClientHandler(client);

                new Thread(clientSock).start();
            } while (true);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        listenMessage();
    }
}
