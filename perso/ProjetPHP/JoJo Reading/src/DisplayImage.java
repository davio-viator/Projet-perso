import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public class DisplayImage {

    private static Scanner scanner;
    private static String input = "d";
    private static String record = "";
    private static String separator = '\\'+"v";

    public static void main(String avg[]) throws Exception
    {


        scanner = new Scanner(System.in);
        ArrayList<String> parts = getParts("c://Users/socia/Desktop/JoJo/JJCA");
        String p = getPart(parts);
        System.out.println(p);


        ArrayList<String> volumes = getVolumes(p);
        String v = getVolume(volumes);
        System.out.println(v);


        ArrayList<String> chapters = getChapters(v);
//        String t = getChapter(chapters);
//        String c = t.substring(0,t.length()-2);
//        t = t.substring(t.length()-1);
//        System.out.println(t);
//        System.out.println(c);
        String c = getChapter(chapters);


        ArrayList<String> images = getPictures(c);

        DisplayImage abc = new DisplayImage(images);
    }

    public DisplayImage(ArrayList<String> paths) throws Exception, InterruptedException {
        if (paths.size()>2){
            for(String path : paths){
                if(input=="d") {
                    input = "";
                    BufferedImage img = ImageIO.read(new File(path));
                    BufferedImage resized_image = new BufferedImage(1366,728,img.getType());
                    ImageIcon icon = new ImageIcon(img.getScaledInstance(1366, 760, Image.SCALE_SMOOTH));
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
            if(!paths.isEmpty()){
                nextChapter(paths.get(0));
                record = paths.get(0);
            }
        }
        nextVolume(paths.get(0));
    }


    public static String[] sortingFileArray(File[] contents){
        String[] pathNames = new String[contents.length];
        int index = 0;
        for(File f : contents){
            pathNames[index] = f.getAbsolutePath();
            index++;
        }
        Arrays.asList(pathNames);
        Arrays.sort(pathNames);
        return pathNames;
    }


    public static ArrayList<String> getParts(String folder){
        File directory = new File(folder);
        File[] contents = directory.listFiles();
        ArrayList<String> volumes = new ArrayList<>();
        for (File f : contents) {
            System.out.println("part : "+f.getAbsolutePath());
            volumes.add(f.getAbsolutePath());
        }


        return volumes;
    }

    public static ArrayList<String> getVolumes(String folder) {
        File directory = new File(folder);
        File[] contents = directory.listFiles();
        ArrayList<String> volumes = new ArrayList<>();
        for (File f : contents) {
            if(!f.getAbsolutePath().contains(".zip")){
                System.out.println("volume : "+f.getAbsolutePath());
                volumes.add(f.getAbsolutePath());
            }
        }


        return volumes;
    }


    public static ArrayList<String> getChapters(String folder) {
        File directory = new File(folder);
        File[] contents = directory.listFiles();
        File[] sortTest = contents.clone();
        ArrayList<String> chapters = new ArrayList<>();
        for (File f : contents) {
            System.out.println("get chapters : "+f.getAbsolutePath());
            chapters.add(f.getAbsolutePath());
        }

        for (int i = 0; i < sortTest.length ; i++) {

        }
        return chapters;
    }


    public static ArrayList<String> getPictures(String folder) {
        //System.out.println("test next volume "+folder);
        ArrayList<String> volumes = new ArrayList<>();
        if(folder!=null && folder.length()>4){
            File directory = new File(folder);
            File[] contents = directory.listFiles();
            //ArrayList<String> volumes = new ArrayList<>();
            for (File f : contents) {
                System.out.println("picture : "+f.getAbsolutePath());
                volumes.add(f.getAbsolutePath());
            }
        }else{
            volumes.add(folder);
        }

        return volumes;
    }


    public static String getPart(ArrayList<String> parts){
        String final_part = "";
        String part_number = "";
        System.out.println("which part do you want");
        part_number = scanner.nextLine();
        //scanner.close();
        for (String part:parts){
            if(part.contains(part_number) && !part.contains("Monochrome")) final_part = part;
        }
        return final_part;
    }

    public static String getVolume(ArrayList<String> volumes){
        String final_volume = "";
        String volume_number = "";
        System.out.println("which volume do you want");
        volume_number = scanner.nextLine();
        //scanner.close();
        for (String volume:volumes){
            if(volume.contains(volume_number) ) final_volume = volume;
        }
        return final_volume;
    }

    public static String getVolume2(String currentVolume, ArrayList<String> volumes){
        String final_volume = "";
        String volume_number = currentVolume.substring(currentVolume.indexOf("Volume")+7,currentVolume.indexOf("Volume")+9);
        System.out.println("test getVolumes2 : "+volume_number);

        //scanner.close();
        for (String volume:volumes){
            if(volume.contains(volume_number) && volumes.indexOf(volume)+1<volumes.size() ) {
                final_volume = volumes.get(volumes.indexOf(volume)+1);
                break;
            }

        }
        System.out.println(final_volume);
        return final_volume;
    }



    public static String getChapter(ArrayList<String> chapters){
        String final_chapter = "";
        int chapter_number;
        System.out.println("which chapter do you want (last chapter is "+chapters.size()+")");
        chapter_number = scanner.nextInt();
        System.out.println("chapter number entered "+chapter_number);
        if(chapter_number<= chapters.size()){
            for (int i = 0 ;i < chapters.size(); i++){
                if(chapter_number == i+1) {
                    final_chapter = chapters.get(chapter_number-1)/*+"$"+(i+1)*/;
                    System.out.println("chapter number from array "+(i+1));
                    System.out.println("chapter gotten "+chapters.get(chapter_number-1));
                }
                System.out.println(chapters.get(i));
            }
        }else{
            System.out.println("you reached the last chapter");
            //System.out.println(chapters.get(chapters.size()-1));
            final_chapter = chapters.get(0).substring(47,49);
        }

        return final_chapter;
    }

    public static String getChapter2(String curentPath, ArrayList<String> chapters){

        String chapterNumber2 = curentPath.substring(curentPath.indexOf(separator)+1,curentPath.indexOf(separator)+9);
        String volumeNumber2 = chapters.get(0).substring(chapters.get(0).indexOf(separator)+1,chapters.get(0).indexOf(separator)+8);


        System.out.println("chapteNumber2 = "+chapterNumber2);
        System.out.println("volume number2 = "+volumeNumber2);

        String volume = volumeNumber2.substring(1,3);
        System.out.println("volume : "+volume);


        String chapterN = chapterNumber2.substring(3,chapterNumber2.length()-1);
        System.out.println("chapter : "+chapterN+"|");


        String lastVolumeChapter = chapters.get(chapters.size()-1);
        lastVolumeChapter = lastVolumeChapter.substring(lastVolumeChapter.indexOf(separator)+1,lastVolumeChapter.indexOf(separator)+9);
        String lastNumber = lastVolumeChapter.substring(3);

        System.out.println("last number "+lastNumber);

        chapterN = chapterN.toLowerCase();
        chapterN = chapterN.replaceAll("[^\\d.]","");
        chapterN = chapterN.replace(" ","");



        if(lastNumber.charAt(lastNumber.length()-2)== ' '){
            lastNumber = lastNumber.substring(0,lastNumber.length()-2);
        }

        lastNumber = lastNumber.replaceAll(" ","");
        lastNumber = lastNumber.toLowerCase();
        lastNumber = lastNumber.replaceAll("[^\\d.]","");

        if(volume.contains("c")){
            volume = volume.replaceAll(" ","");
            volume = volume.replaceAll("c","");
            volume = volume.substring(0,1);
        }

        System.out.println(volume);
        if(chapterN.charAt(0)=='0'){
            chapterN = chapterN.replaceFirst("0","");
        }
        System.out.println(chapterN+"|");

        System.out.println("L317 this should be the new chapter : v"+volume+"c"+(Integer.parseInt(chapterN)+1));



        System.out.println(lastNumber+"|");

        int newChapterNumber = 0;
        String volumeNumber = curentPath.substring(curentPath.indexOf("Volume")+7,curentPath.indexOf("Volume")+9);

        String final_chapter = "";

        newChapterNumber = Integer.parseInt(chapterN)+1;
        System.out.println("newChapterNumber value = "+newChapterNumber);
        System.out.println("lastChapterNumber value = "+Integer.parseInt(lastNumber));

        System.out.println("volumeNumber value = "+volumeNumber);
        System.out.println("volume value = "+volume);
        System.out.println("newChapterNumber value = "+newChapterNumber);
        System.out.println("");
        if(newChapterNumber<= (Integer.parseInt(lastNumber))){
            for (String chapter : chapters){
                if(chapter.contains("v"+volume/*.replace("0","")*/+"c"+newChapterNumber+"") || chapter.contains("v"+volume/*.replace("0","")*/+"c0"+newChapterNumber) ){
//                    System.out.println("volumeNumber value = "+volumeNumber);
//                    System.out.println("chapter value = "+chapter);
//                    System.out.println("volume value = "+volume);
//                    System.out.println("volumeNumber value = "+volumeNumber);
//                    System.out.println("lastNumber value = "+Integer.parseInt(lastNumber));
//                    System.out.println("newChapterNumber value = "+newChapterNumber);
//                    System.out.println("v"+volume+"c"+newChapterNumber);
//                    System.out.println("contains 'c0' = "+chapter.contains("v"+volume+"c0"+newChapterNumber));
//                    System.out.println("contains 'c' = "+chapter.contains("v"+volume+"c"+newChapterNumber));
                    final_chapter = chapter;
                    break;
                }

            }
        }else{
            System.out.println("you reached the last chapter");
            final_chapter = chapters.get(0).substring(47,49);
        }
        return  final_chapter;
    }


    public static String getImage(ArrayList<String> images){
        String final_chapter = "";
        int chapter_number;
        System.out.println("which chapter do you want");
        chapter_number = scanner.nextInt();
        for (String chapter:images){
            if(chapter.contains(chapter_number+"") ) final_chapter = chapter;
        }
        return final_chapter;
    }

    public static void nextChapter(String path) throws  Exception{

        //String new_path = path.substring(0,81);
        String new_path = path.substring(0,path.indexOf(separator));
        //int substringTest = path.indexOf("v");
        System.out.println("test 1 "+new_path);
        //System.out.println(path.substring(substringTest));
        //getNextChapterValue(path);
        ArrayList<String> newVolumes = getVolumes(new_path);
        //String c = getChapter(newVolumes);
        String c = getChapter2(path,newVolumes);
        //String c = getNextChapterPictures(new_path,getNextChapterValue(path));
        ArrayList<String> images = getPictures(c);
        DisplayImage abc = new DisplayImage(images);
    }

    public static String getNextChapterValue(String path){
        System.out.println("le prochain chapitre est " +Character.getNumericValue((path.substring(path.indexOf(separator)+2,path.indexOf(separator)+3).charAt(0))+1));
        int number = Character.getNumericValue(path.substring(85,86).charAt(0))+1;
        String ss = path.substring(path.indexOf(separator)+4,path.indexOf(separator)+5);
        System.out.println("valeur du prochain chapitre " + ss);
        String s = path.substring(82,85)+number;

        System.out.println("ancienne valeure du chapitre " + s);
        return s;
    }


    public static void nextVolume(String path) throws Exception{
        if(path.length()>2){
            scanner = new Scanner(System.in);
            //System.out.println("test next volume "+path.substring(0,39));
            ArrayList<String> volumes = getVolumes(path.substring(0,39));
            //String v = getVolume(volumes);
            String v = getVolume2(path,volumes);


            ArrayList<String> chapters = getChapters(v);
            //String c = getChapter(chapters);
            String c = getChapter2(path,chapters);
            //String c = getChapter2(chapters.get(0),chapters);


            ArrayList<String> images = getPictures(c);

            DisplayImage abc = new DisplayImage(images);
        }


    }

    public static String toString2(ArrayList<String> array){
        String string = "";
        for (String s: array) {
            string+= s+"\n";
        }
        return string;
    }

    public static String getNextChapterPictures(String path,String previousChapterNumber){
        String new_number = "";
        System.out.println(path);
        File directory = new File(path);
        File[] contents = directory.listFiles();
        ArrayList<String> chapters = new ArrayList<>();
        for (File f : contents) {
            //System.out.println(f.getAbsolutePath());
            chapters.add(f.getAbsolutePath());
            if(f.getAbsolutePath().contains(previousChapterNumber))new_number = f.getAbsolutePath();
        }
        return new_number;
    }








}