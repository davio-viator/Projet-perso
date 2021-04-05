import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class ClientWritter extends Thread {

    private String name;
    private PrintWriter writer;
    private OutputStream output;
    private Scanner scanner;
    private ChatRoom chat;

    public ClientWritter(String name,PrintWriter writer,Scanner scanner){
        this.name = name;
        this.writer = writer;
        this.scanner = scanner;
    }


    public ClientWritter(String name,OutputStream output,Scanner scanner){
        this.name = name;
        this.output = output;
        this.scanner = scanner;
    }


    public void writeMessage(String message){
        writer.println(name+" : "+message);
    }
    public void writeMessage2(String message){
        try {
            output.write(message.getBytes());
        }catch(Exception e){}
    }

    @Override
    public void run() {
        while(true){
            try{
                writeMessage(scanner.nextLine());
            }catch (Exception e){}
        }

    }
}
