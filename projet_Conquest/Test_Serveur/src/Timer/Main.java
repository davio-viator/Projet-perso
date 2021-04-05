package Timer;
public class Main {

    public static void main(String[] args) {

        String host = "localhost";
        int port = 9090;

        TimerServer ts = new TimerServer(/*host, port*/);
        ts.open();

        System.out.println("Serveur initialisé.");

        /*for(int i = 0; i < 5; i++){
            Thread t = new Thread(new ClientConnexion(host, port));
            t.start();
        }*/
    }
}