package assets;

public class Action {
    private int effectID;
    private String name;


    public Action(int effectID, String name) {
        this.effectID = effectID;
        this.name = name;

    }

    public int getEffectID() {
        return effectID;
    }

    public void setEffectID(int effectID) {
        this.effectID = effectID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
