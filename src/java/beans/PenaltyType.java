package beans;

public class PenaltyType {
    int typeID;
    int minutes;
    String type;

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTypeID() {
        return this.typeID;
    }

    public int getMinutes() {
        return this.minutes;
    }

    public String getType() {
        return this.type;
    }
}
