
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client implements Runnable {

    private int port;
    private Socket soc;
    private String message;
    private String nom;
    private Scanner input;
    private PrintWriter writer;
    private BufferedReader reader;

    public Client(int port,String nom,int id) throws Exception{
        this.nom = nom;
        this.port = port;
        this.input = new Scanner(System.in);
        this.soc = new Socket("192.168.1.40",6020);
        this.writer = new PrintWriter(soc.getOutputStream(),true);
        this.reader = new BufferedReader(new InputStreamReader(soc.getInputStream()));
    }

    public int getPort() {
        return port;
    }

    public Socket getSoc() {
        return soc;
    }

    public String getNom() {
        return nom;
    }

    public Scanner getInput() {
        return input;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public String writeMessage(){
        return nom+" : "+input.nextLine();
    }

    public PrintWriter getWriter() {
        return writer;
    }


    public static void main(String[] args) throws Exception{


        
        Scanner entre = new Scanner(System.in);

        Client c = new Client(6020,entre.nextLine(),(int)Math.random()*10);

        InputStream input = c.getSoc().getInputStream();
        OutputStream output = c.getSoc().getOutputStream();
        PrintWriter writer = new PrintWriter(output,true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        // Thread cr = new Thread(new ClientReader(reader));
        // Thread cw = new Thread(new ClientWritter(c.nom,writer,c.input));
        // ExecutorService es = Executors.newFixedThreadPool(2);
        // es.execute(cr);
        // es.execute(cw);

        Thread cr = new ClientReader(c.getReader());
        Thread cw = new ClientWritter(c.getNom(),c.getWriter(),c.getInput());
        ExecutorService es = Executors.newFixedThreadPool(2);
        cr.start();
        cw.start();
        //System.out.println(c.getSoc().getOutputStream().toString());
        try{
            cr.join();
            cw.join();
        }catch (Exception e){}


        //entre.close();
        //reader.close();

        //System.out.println(reader.readLine());








    }

    @Override
    public void run() {
        Thread cr = new ClientReader(reader);
        Thread cw = new ClientWritter(nom,writer,input);
        ExecutorService es = Executors.newFixedThreadPool(2);
        cr.start();
        cw.start();
        try{
            cr.join();
            cw.join();
        }catch (Exception e){}




    }
}
