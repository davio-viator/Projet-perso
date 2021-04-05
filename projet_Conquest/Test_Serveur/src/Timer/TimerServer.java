package Timer;

import java.io.IOException;
import java.net.*;

public class TimerServer {

    //On initialise des valeurs par défaut
    private int port = 9090;
    private String host = "localhost";
    private ServerSocket server = null;
    private boolean isRunning = true;

    public TimerServer(){
        try {
            server = new ServerSocket(port, 100, InetAddress.getByName(host));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TimerServer(String pHost, int pPort){
        host = pHost;
        port = pPort;
        try {
            server = new ServerSocket(port, 100, InetAddress.getByName(host));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //On lance notre serveur
    public void open(){

        //Toujours dans un thread à part vu qu'il est dans une boucle infinie
        Thread t = new Thread(new Runnable(){
            public void run(){
                while(isRunning == true){

                    try {
                        //On attend une connexion d'un client
                        /*try {
                            SocketAddress endPoint = new InetSocketAddress(InetAddress.getByName(host), port);
                            server.bind(endPoint);
                        *//*}catch (BindException e) {
                            e.printStackTrace();*//*
                        } catch (IOException e) {
                            e.printStackTrace();
                        }*/
                        Socket client = server.accept();

                        //Une fois reçue, on la traite dans un thread séparé
                        /*System.out.println("Connexion cliente reçue.");
                        System.out.println(client.getPort());
                        System.out.println(client.getInetAddress().getHostAddress());*/
                        Thread t = new Thread(new ClientProcessor(client/*,port,host*/));
                        t.start();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    server = null;
                }
            }
        });

        t.start();
    }

    public void close(){
        isRunning = false;
    }
}