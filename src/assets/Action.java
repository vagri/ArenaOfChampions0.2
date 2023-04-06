package assets;

public class Action {
    private int ActionID;
    private String name;


    public Action(int actionID, String name) {
        this.ActionID = actionID;
        this.name = name;

    }

    public int getActionID() {
        return ActionID;
    }

    public void setActionID(int actionID) {
        this.ActionID = actionID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
