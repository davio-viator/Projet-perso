public abstract class Card {

    private String name;
    private String picture;
    private String origin;
    private String serialNumber;
    private boolean edition;
    private String description;


    public Card (){

    }

    public Card(String name,String picture,String origin,String serialNumber,boolean edition,String description){
        this.name = name;
        this.picture = picture;
        this.origin = origin;
        this.serialNumber = serialNumber;
        this.edition = edition;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public boolean isEdition() {
        return edition;
    }

    public String getDescription() {
        return description;
    }

    public String getOrigin() {
        return origin;
    }
}
