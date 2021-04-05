package Timer;
import org.glassfish.tyrus.server.Server;

import javax.websocket.*;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.io.*;
import java.security.MessageDigest;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.ServerSocket;
import java.net.Socket;
//import javax.xml.bind.DatatypeConverter;
//import org.glassfish.tyrus.server.Server;

import java.util.Scanner;




@ServerEndpoint(value = "/")
public class WebSocketClient {
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("New connection");
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println(message);
    }

    @OnClose
    public void onClose(Session session) {
    }

    @OnError
    public void onError(Throwable exception, Session session) {
        exception.printStackTrace();
        System.err.println("Error");
    }

    public static void main(String[] args){
        InetAddress ip;
        try {
            ip = InetAddress.getLocalHost();
            String hostname = ip.getHostName();
            System.out.println("Your current IP address : " + ip);
            System.out.println("Your current Hostname : " + hostname);

        } catch (UnknownHostException e) {

            e.printStackTrace();
        }
        Server server = new Server("LAPTOP-MV9Q9EGQ", 9090, "/", WebSocketClient.class);

        try {
            server.start();
            while(true) {
            }

        } catch (DeploymentException e) {
            throw new RuntimeException(e);
        } finally {
            server.stop();
        }
    }

}