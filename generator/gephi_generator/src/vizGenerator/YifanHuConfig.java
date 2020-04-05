package vizGenerator;

public class YifanHuConfig {

    private Float optimalDistance;
    private Float relativeStrength;
    private boolean adaptiveCooling;

    public YifanHuConfig(YifanHuGen yifanHuGen) {
        this.optimalDistance = yifanHuGen.getOptimalDistance();
        this.relativeStrength = yifanHuGen.getRelativeStrength();
        this.adaptiveCooling = yifanHuGen.isAdaptiveCooling();
    }

    public Float getOptimalDistance() {
        return optimalDistance;
    }

    public void setOptimalDistance(Float optimalDistance) {
        this.optimalDistance = optimalDistance;
    }

    public Float getRelativeStrength() {
        return relativeStrength;
    }

    public void setRelativeStrength(Float relativeStrength) {
        this.relativeStrength = relativeStrength;
    }

    public boolean isAdaptiveCooling() {
        return adaptiveCooling;
    }

    public void setAdaptiveCooling(boolean adaptiveCooling) {
        this.adaptiveCooling = adaptiveCooling;
    }

    @Override
    public String toString() {
        return "YifanHuConfig{" +
                "optimalDistance=" + optimalDistance +
                ", relativeStrength=" + relativeStrength +
                ", adaptiveCooling=" + adaptiveCooling +
                '}';
    }
}
