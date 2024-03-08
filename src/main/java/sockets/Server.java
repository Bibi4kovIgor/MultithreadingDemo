package sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

  private static final Logger logger = Logger.getLogger(Server.class.getName());
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
          logger.log(Level.WARNING, e, String::new);
        }
    }

    public static void main(String[] args) {
        listenMessage();
    }
}
