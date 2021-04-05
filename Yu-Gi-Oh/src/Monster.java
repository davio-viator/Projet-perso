import java.util.ArrayDeque;

public class Monster extends Card {

    private int originalAtk;
    private int originalDef;
    private int level;
    private String attibute;
    private ArrayDeque<MonsterType> types;

    public Monster(String name, String picture, String origin, String serialNumber, boolean edition, String description,int atk,int def,int lvl,String attribute,ArrayDeque<MonsterType> types) {
        super(name, picture, origin, serialNumber, edition, description);
        this.originalAtk = atk;
        this.originalDef = def;
        this.level = lvl;
        this.attibute = attribute;
        this.types = new ArrayDeque<>(types);
    }

    public int getOriginalAtk() {
        return originalAtk;
    }

    public int getOriginalDef() {
        return originalDef;
    }

    public int getLevel() {
        return level;
    }

    public String getAttibute() {
        return attibute;
    }

    public ArrayDeque<MonsterType> getTypes() {
        return types;
    }
}
