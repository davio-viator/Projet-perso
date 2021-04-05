package Timer;

import Timer.TimerServer;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerSocketConstructor {

    public static void main(String[] args) {

        for(int port = 1; port <= 65535; port++){
            try {
                ServerSocket sSocket = new ServerSocket(port);
            } catch (IOException e) {
                System.err.println("Le port " + port + " est déjà utilisé ! ");
            }
        }
    }

    public static class Main {

        public static void main(String[] args) {

            String host = "localhost";
            int port = 9090;

            TimerServer ts = new TimerServer(host, port);
            ts.open();

            System.out.println("Serveur initialisé.");

            for(int i = 0; i < 5; i++){
                Thread t = new Thread(new ClientConnexion(host, port));
                t.start();
            }
        }
    }
}