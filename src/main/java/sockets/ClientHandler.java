package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientHandler implements Runnable, AutoCloseable {
  private static final Logger logger = Logger.getLogger(ClientHandler.class.getName());
  private final Socket clientSocket;

  public ClientHandler(Socket socket) {
    this.clientSocket = socket;
  }

  public void run() {
    try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
         BufferedReader in = new BufferedReader(
             new InputStreamReader(
                 clientSocket.getInputStream()))) {

      String line;
      while ((line = in.readLine()) != null) {

        System.out.printf(
            " Sent from the client: %s \n",
            line);
        out.println(line);
      }
    } catch (IOException e) {
      logger.log(Level.WARNING, e, String::new);
    }
  }

  @Override
  public void close() throws IOException {
    if (clientSocket.isConnected()) {
      clientSocket.close();
    }
  }
}
