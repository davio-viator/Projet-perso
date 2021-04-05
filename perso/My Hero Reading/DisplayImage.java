import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;





public class DisplayImage{

  private static Scanner scanner;
  private static String input = "d";
  private static String record = "";
  private static String separator = '\\'+"v";
  private static String serie = "My_Hero_Academia_";
  private static String origin = "D://Téléchargements/Boku No Hero Academia/";
  private static Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

  public static void main(String[] args){

    scanner = new Scanner(System.in);

    String value = origin+serie+scanner.nextLine();

    ArrayList<String> pictures = getPictures(value);

    AlphaNumericalComparator apc = new AlphaNumericalComparator();

    Comparator<String> com = (o1,o2) ->{return apc.compare(o1,o2);};

    Collections.sort(pictures,com);

    try {
      DisplayImage di = new DisplayImage(pictures);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  public DisplayImage(ArrayList<String> paths) throws Exception, InterruptedException {
    for(String path : paths){
      if(input=="d") {
          input = "";
          BufferedImage img = ImageIO.read(new File(path));
          BufferedImage resized_image = new BufferedImage(1366,728,img.getType());
          ImageIcon icon = new ImageIcon(img.getScaledInstance((int)(size.getWidth()/2.3), (int)size.getHeight(), Image.SCALE_SMOOTH));
          JFrame frame = new JFrame();
          frame.setLayout(new FlowLayout());
          frame.setSize(1366, 728);
          //frame.setSize(1366,728);
          frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
          frame.setUndecorated(true);
          frame.setVisible(true);
          JLabel lbl = new JLabel();
          lbl.setIcon(icon);
          frame.add(lbl);
          frame.setVisible(true);
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          KeyListener test = new KeyListener() {
              @Override
              public void keyTyped(KeyEvent keyEvent) {
              }

              @Override
              public void keyPressed(KeyEvent keyEvent) {
                  if (keyEvent.getKeyChar() == 'd') {
                      input = "d";
                  }else if(keyEvent.getKeyCode() == KeyEvent.VK_RIGHT){
                      input = "d";
                  }
                  else if(keyEvent.getKeyCode() == KeyEvent.VK_SPACE){
                      input = "d";
                  }
              }

              @Override
              public void keyReleased(KeyEvent keyEvent) {
              }
          };
//                    input = "d";
          while(!input.equals("d")){
              if(frame.getKeyListeners().length <1) frame.addKeyListener(test);
          }
          frame.dispose();
      }
    }
  }

  public static ArrayList<String> getPictures(String folder){
    ArrayList<String> pictures = new ArrayList<>();
    if(folder!=null && folder.length()>4){
      File directory = new File(folder);
      File[] contents = directory.listFiles();
      for (File file : contents) {
        pictures.add(file.getAbsolutePath());
      }
    }
    return pictures;

  }



}