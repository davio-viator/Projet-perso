import java.io.*;
import java.net.Socket;

public class Connexion implements Runnable {

    private Socket socket;
    private String message;
    private ChatRoom chatRoom;
    private PrintWriter writer;

    public Connexion(Socket soc,ChatRoom chat,PrintWriter pw){
        socket = soc;
        message = "";
        chatRoom = chat;
        writer = pw;
    }

    public void reponse(String message){
        this.message =  message;
    }

    public String getMessage() {
        String s = message;
        message = "";
        return s;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void run() {
        try{
            BufferedReader ins = new BufferedReader(
                    new InputStreamReader(this.socket.getInputStream()) );
            PrintWriter outs = new PrintWriter( new BufferedWriter(
                    new OutputStreamWriter(this.socket.getOutputStream())), true);

            String sa = "";

            chatRoom.addMember(outs);

            do{
                sa = ins.readLine();
                reponse(sa);

                try {
                    chatRoom.sendMessages(sa, outs);

                }catch(Exception e){}
                //outs.println(sa);
            }while(!sa.equals("stop"));

        }catch (Exception e){}

    }
}
