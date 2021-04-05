import java.io.BufferedReader;
import java.io.IOException;

public class ClientReader extends Thread {

    private BufferedReader reader;

    public ClientReader(BufferedReader reader){
        this.reader = reader;
    }

    @Override
    public void run() {
        String msg;
        while(true){
            try{
                msg = reader.readLine();
                System.out.println(msg);
            }catch (IOException e){}
        }

    }
}
