import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Serveur {

    private ServerSocket s;
    private ArrayList<Connexion> listConnexion;

    public Serveur(){
        try{
            s = new ServerSocket(6020);
        }catch(Exception e){
            e.printStackTrace();
        }
        listConnexion = new ArrayList<>();
    }

    public ServerSocket getServer() {
        return s;
    }

    public ArrayList<Connexion> getListConnexion() {
        return listConnexion;
    }

    public static void main(String[] args) throws Exception {
        ServerSocket s = new ServerSocket(6020);
        Executor ex = Executors.newCachedThreadPool();
        ArrayList<Connexion> listConnexion = new ArrayList<>();
        System.out.println("START");
        

        ExecutorService es = Executors.newFixedThreadPool(10);

        ChatRoom cr = new ChatRoom();

        while(true) {
            try{
                Socket client = s.accept();
                System.out.println("Nouvelle connexion cliente");

                PrintWriter pw = new PrintWriter(client.getOutputStream(),true);

                Connexion c = new Connexion(client,cr,pw);
                Thread connexion = new Thread(c);
                listConnexion.add(c);

                pw.println("Bienvenue\n");

                es.execute(connexion);

            }catch (IOException e){
                e.printStackTrace();
            }
        }

        /*ins.close();
        outs.close();
        soc.close();*/
    }


}
