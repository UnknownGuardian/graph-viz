package vizGenerator;

public class ForceAtlas2Config {

    private boolean dissuade_hubs;
    private boolean linlog_mode;
    private double edge_weight_influence;
    private double scaling;
    private boolean stronger_gravity;
    private double gravity;
    private boolean approximate_repulsion;

//    this.setDissuade_hubs(rd.nextBoolean());
//        this.setLinlog_mode(rd.nextBoolean());
//        this.setStronger_gravity(rd.nextBoolean());
//        this.setApproximate_repulsion(rd.nextBoolean());
//
//        this.setEdge_weight_influence(rd.nextDouble());
//        this.setScaling(1.0 + rd.nextDouble() * 29);
//        this.setGravity(1.0 + rd.nextDouble() * 29);

    public ForceAtlas2Config(ForceAtlas2Gen forceAtlas2Gen){
        this.dissuade_hubs = forceAtlas2Gen.isDissuade_hubs();
        this.linlog_mode = forceAtlas2Gen.isLinlog_mode();
        this.edge_weight_influence = forceAtlas2Gen.getEdge_weight_influence();
        this.scaling = forceAtlas2Gen.getScaling();
        this.stronger_gravity = forceAtlas2Gen.isStronger_gravity();
        this.gravity = forceAtlas2Gen.getGravity();
        this.approximate_repulsion = forceAtlas2Gen.isApproximate_repulsion();
    }

    public boolean isDissuade_hubs() {
        return dissuade_hubs;
    }

    public void setDissuade_hubs(boolean dissuade_hubs) {
        this.dissuade_hubs = dissuade_hubs;
    }

    public boolean isLinlog_mode() {
        return linlog_mode;
    }

    public void setLinlog_mode(boolean linlog_mode) {
        this.linlog_mode = linlog_mode;
    }

    public double getEdge_weight_influence() {
        return edge_weight_influence;
    }

    public void setEdge_weight_influence(double edge_weight_influence) {
        this.edge_weight_influence = edge_weight_influence;
    }

    public double getScaling() {
        return scaling;
    }

    public void setScaling(double scaling) {
        this.scaling = scaling;
    }

    public boolean isStronger_gravity() {
        return stronger_gravity;
    }

    public void setStronger_gravity(boolean stronger_gravity) {
        this.stronger_gravity = stronger_gravity;
    }

    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public boolean isApproximate_repulsion() {
        return approximate_repulsion;
    }

    public void setApproximate_repulsion(boolean approximate_repulsion) {
        this.approximate_repulsion = approximate_repulsion;
    }

    @Override
    public String toString() {
        return "ForceAtlas2Config{" +
                "dissuade_hubs=" + dissuade_hubs +
                ", linlog_mode=" + linlog_mode +
                ", edge_weight_influence=" + edge_weight_influence +
                ", scaling=" + scaling +
                ", stronger_gravity=" + stronger_gravity +
                ", gravity=" + gravity +
                ", approximate_repulsion=" + approximate_repulsion +
                '}';
    }
}
