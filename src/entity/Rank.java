package entity;

public class Rank {
    String name;
    float requiredPoint;

    public Rank(String name, float requiredPoint) {
        this.name = name;
        this.requiredPoint = requiredPoint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRequiredPoint() {
        return requiredPoint;
    }

    public void setRequiredPoint(float requiredPoint) {
        this.requiredPoint = requiredPoint;
    }
}
