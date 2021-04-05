import java.util.Random;

public class Dice {

    private int face;
    private int result;

    public Dice(int f){
        this.face = f;
        result = 0;
    }

    public int roll(){
        Random rand = new Random();
        result =  rand.nextInt(face + 1 - 1) + 1;
        return result;
    }

    public static void main(String[] args) {
        Dice d20 = new Dice(20);
        Dice d12 = new Dice(12);
        Dice d10 = new Dice(10);
        Dice d8 = new Dice(8);
        Dice d6 = new Dice(6);
        Dice d4 = new Dice(4);

        System.out.println("d20 : "+d20.roll());
        System.out.println("d6 : "+d6.roll());
    }

}
