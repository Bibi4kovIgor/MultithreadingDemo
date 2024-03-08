package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable, AutoCloseable{
    private final Socket clientSocket;

    public ClientHandler(Socket socket)
    {
        this.clientSocket = socket;
    }

    public void run()
    {
        try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(
                     clientSocket.getInputStream()))){

            String line;
            while ((line = in.readLine()) != null) {

                System.out.printf(
                        " Sent from the client: %s \n",
                        line);
                out.println(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {
        if (clientSocket.isConnected()) {
            clientSocket.close();
        }
    }
}
