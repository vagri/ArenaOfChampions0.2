package assets;

public class Action {
    private int effectID;
    private String name;
    private int duration;
    private boolean stackable;
    private boolean isVisible;

    public Action(int effectID, String name, int duration, boolean stackable, boolean isVisible) {
        this.effectID = effectID;
        this.name = name;
        this.duration = duration;
        this.stackable = stackable;
        this.isVisible = isVisible;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isStackable() {
        return stackable;
    }

    public void setStackable(boolean stackable) {
        this.stackable = stackable;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
