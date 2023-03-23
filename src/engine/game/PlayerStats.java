package engine.game;

class PlayerStats {

    private int ID;
    private String name;
    private int teamID;
    private int championID;
    private int maxHP;
    private int currentHP;
    private int maxR;
    private int currentR;
    private int CDRemaining[];
    private boolean isDead;

    public PlayerStats(int ID, String name,int teamID, int championID, int maxHP, int currentHP, int maxR, int currentR,int CDRemaining[], boolean isDead) {
        this.ID = ID;
        this.name = name;
        this.teamID = teamID;
        this.championID = championID;
        this.maxHP = maxHP;
        this.currentHP = currentHP;
        this.maxR = maxR;
        this.currentR = currentR;
        this.CDRemaining = CDRemaining;
        this.isDead = isDead;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChampionID() {
        return championID;
    }

    public void setChampionID(int championID) {
        this.championID = championID;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public int getMaxR() {
        return maxR;
    }

    public void setMaxR(int maxR) {
        this.maxR = maxR;
    }

    public int getCurrentR() {
        return currentR;
    }

    public void setCurrentR(int currentR) {
        this.currentR = currentR;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    @Override
    public String toString() {
        return "PlayerStats{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", championID=" + championID +
                ", maxHP=" + maxHP +
                ", currentHP=" + currentHP +
                ", maxR=" + maxR +
                ", currentR=" + currentR +
                ", isDead=" + isDead +
                '}';
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public int[] getCDRemaining() {
        return CDRemaining;
    }

    public void setCDRemaining(int[] CDRemaining) {
        this.CDRemaining = CDRemaining;
    }
}
