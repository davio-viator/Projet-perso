import java.util.ArrayDeque;

public class Effect_Monster extends Monster {

    private String effect;

    public Effect_Monster(String name, String picture, String origin, String serialNumber, boolean edition, String description, int atk, int def, int lvl, String attribute, ArrayDeque<MonsterType> types,String effect) {
        super(name, picture, origin, serialNumber, edition, description, atk, def, lvl, attribute, types);
        this.effect = effect;
    }

    public void useEffect(){
        System.out.println(getName()+" utilise sont effet");
    }

    public String getEffect() {
        return this.effect;
    }

    public String toString(){
        return "["+getName()+","+getOrigin()+","+getSerialNumber()+","+
        getDescription()+","+getLevel()+"\natk : "+getOriginalAtk()+", def : "
        +getOriginalDef()+","+getAttibute()+","+getTypes()+",\n"+getEffect()+"]";
            
    }
}
