package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    private static final Logger logger = Logger.getLogger(Client.class.getName());
    public static void sendMessage() {



        try (Socket socket = new Socket("localhost", 1234)) {
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            BufferedReader in
                    = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));

            Scanner sc = new Scanner(System.in);
            String line = null;

            while (!"exit".equalsIgnoreCase(line)) {

                line = sc.nextLine();

                out.println(line);
                out.flush();

                System.out.println("Server replied "
                        + in.readLine());
            }

            sc.close();
        }
        catch (IOException e) {
            logger.log(Level.WARNING, e, String::new);
        }

    }

    public static void main(String[] args) {
        sendMessage();
    }
}
