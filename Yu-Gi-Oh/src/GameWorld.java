import java.util.ArrayDeque;
import java.util.ArrayList;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class GameWorld {
  

  public static void main(String[] args) {
    String data = "";
    try {
      File obj = new File("C://Users/Davio/OneDrive/Bureau/Projet-perso/Yu-Gi-Oh/src/yugioh-card (5).txt");
      Scanner Reader = new Scanner(obj);
      while(Reader.hasNextLine()){
        data = Reader.nextLine();
        //System.out.println(data);
      }
      Reader.close();
    } catch (Exception e) {
      //TODO: handle exception
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    


    String[] values = data.split(";");

    for (String string : values) {
      //System.out.println(string);
    }


    String name = values[0];
    String lien = values[1];
    String circulation = values[2];
    String serial = values[3];
    String effect = values[4];
    int atk = Integer.parseInt(values[5]);
    int def = Integer.parseInt(values[6]);
    int lvl = Integer.parseInt(values[7]); 
    String type = values[8];
    String attribut = values[8];


    ArrayDeque<MonsterType> types = new ArrayDeque<MonsterType>();
    types.add(MonsterType.Dinosaur);

    Effect_Monster mt = new Effect_Monster("Tyranno Ultime-Conducteur",
    "https://www.otk-expert.fr/cartes/yugioh_ext/SR04/SR04-1.jpg",
    "LA RAGE DU DINOBROYEUR",
    "18940556",
    true,
    "no description",
    3500,
    3200,
    10,
    "Lumiere",
    types,
    "Ni Invocable Normalement ni Posable Normalement. Doit d'abord être Invoquée \nSpécialement (depuis votre main) en bannissant 2 monstres de Type Dinosaure depuis votre \nCimetière. Une fois par tour, durant la Main Phase de chaque joueur : vous pouvez détruire 1 \nmonstre dans votre main ou sur votre Terrain, et si vous le faites, changez tous les monstres \nface recto contrôlés par votre adversaire en Position de Défense face verso. Cette carte peut \nattaquer une fois tous les monstres contrôlés par votre adversaire. Au début de la Damage \nStep, si cette carte attaque un monstre en Position de Défense : vous pouvez infliger 1000 \npoints de dommages à votre adversaire, et si vous le faites, envoyez le monstre en Position \nde Défense au Cimetière.");
    
    Effect_Monster mt1 = new Effect_Monster(name,lien,circulation,serial,true,"no description",atk,def,lvl,attribut,types,effect);
    //mt.useEffect();
    //System.out.println(mt);
    System.out.println(mt1);

  }






}
