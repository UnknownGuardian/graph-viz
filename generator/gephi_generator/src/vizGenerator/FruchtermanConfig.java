package vizGenerator;

public class FruchtermanConfig {

    private Double gravity;

    public FruchtermanConfig(FruchtermanGen fruchtermanGen) {
        this.gravity = fruchtermanGen.getGravity();
    }

    public Double getGravity() {
        return gravity;
    }

    public void setGravity(Double gravity) {
        this.gravity = gravity;
    }

    @Override
    public String toString() {
        return "FruchtermanConfig{" +
                "gravity=" + gravity +
                '}';
    }
}
