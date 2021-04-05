import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ChatRoom implements Runnable {

    private ArrayList<PrintWriter> listMemberWriter;
    private ArrayList<OutputStream> listMemberOutput;

    public ChatRoom(){
        listMemberWriter = new ArrayList<>();
        listMemberOutput = new ArrayList<>();
    }

    public void addMember(PrintWriter writer){
        listMemberWriter.add(writer);
    }

    public void addMember(OutputStream output){
        listMemberOutput.add(output);
    }

    public void removeMember(PrintWriter memberWriter){
        listMemberWriter.remove(memberWriter);
    }

    public void removeMember(OutputStream memberOutput){
        listMemberOutput.remove(memberOutput);
    }

    public void sendMessages(String message,PrintWriter senderWriter){
        for (PrintWriter pw : listMemberWriter) {
            if(!pw.equals(senderWriter)){
                pw.println(message);
            }
        }
    }

    public void sendMessages(String message,OutputStream senderOutput){
        for (OutputStream pw : listMemberOutput) {
            if(!pw.equals(senderOutput)){
                try {
                    pw.write(message.getBytes());
                }catch(Exception e){}

            }
        }
    }

    public ArrayList<PrintWriter> getListMemberWriter() {
        return listMemberWriter;
    }

    public ArrayList<OutputStream> getListMemberOutput() {
        return listMemberOutput;
    }

    @Override
    public void run() {

    }
}
