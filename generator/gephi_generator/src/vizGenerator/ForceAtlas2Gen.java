package vizGenerator;

import org.gephi.layout.plugin.forceAtlas2.ForceAtlas2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Random;

public class ForceAtlas2Gen extends Generation{
    private boolean dissuade_hubs;
    private boolean linlog_mode;
    private boolean prevent_overlap;
    private double edge_weight_influence;
    private double scaling;
    private boolean stronger_gravity;
    private double gravity;
    private double tolerance;
    private boolean approximate_repulsion;
    private double approximation;

    private ForceAtlas2 layout;

    public ForceAtlas2Gen(int nb_steps,
                          String import_file_path,
                          String export_file_path,
                          boolean dissuade_hubs,
                          boolean linlog_mode,
                          boolean prevent_overlap,
                          double edge_weight_influence,
                          double scaling,
                          boolean stronger_gravity,
                          double gravity,
                          double tolerance,
                          boolean approximate_repulsion,
                          double approximation){

        super(nb_steps, import_file_path, export_file_path);

        this.dissuade_hubs = dissuade_hubs;
        this.linlog_mode = linlog_mode;
        this.prevent_overlap = prevent_overlap;
        this.edge_weight_influence = edge_weight_influence;
        this.scaling = scaling;
        this.stronger_gravity = stronger_gravity;
        this.gravity = gravity;
        this.tolerance = tolerance;
        this.approximate_repulsion = approximate_repulsion;
        this.approximation = approximation;

        this.layout = new ForceAtlas2(null);
        this.layout.setGraphModel(graphModel);
        this.layout.resetPropertiesValues();
    }

    @Override
    public void setVizProperties(){
        layout.setOutboundAttractionDistribution(dissuade_hubs);
        layout.setLinLogMode(linlog_mode);
        layout.setAdjustSizes(prevent_overlap);
        layout.setEdgeWeightInfluence(edge_weight_influence);
        layout.setScalingRatio(scaling);
        layout.setStrongGravityMode(stronger_gravity);
        layout.setGravity(gravity);
        layout.setJitterTolerance(tolerance);
        layout.setBarnesHutOptimize(approximate_repulsion);
        layout.setBarnesHutTheta(approximation);
    }

    @Override
    public void startLayouting(){
        layout.initAlgo();
        for (int i = 0; i < nb_steps; i++) {
            layout.goAlgo();
        }
        layout.endAlgo();
    }

    @Override
    public void randomizeLayoutSettings(){
        Random rd = new Random();

        this.setDissuade_hubs(rd.nextBoolean());
        this.setLinlog_mode(rd.nextBoolean());
        this.setStronger_gravity(rd.nextBoolean());
        this.setApproximate_repulsion(rd.nextBoolean());

        this.setEdge_weight_influence(rd.nextDouble());
        this.setScaling(1.0 + rd.nextDouble() * 29);
        this.setGravity(1.0 + rd.nextDouble() * 29);

    }

    @Override
    public void adjustSettingInNeighborhood(int distance){
        double temp_edge_weight_influence;

        temp_edge_weight_influence = getEdge_weight_influence() + distance * 0.01;
        if (temp_edge_weight_influence > 1.0) {
            temp_edge_weight_influence = temp_edge_weight_influence - 1.0;
        }
        setEdge_weight_influence(temp_edge_weight_influence);

        double temp_scaling;
        temp_scaling = getScaling() + distance * 0.1;
        if (temp_scaling > 30.0) {
            temp_scaling = temp_scaling - 29.0;
        }
        setScaling(temp_scaling);

        double temp_gravity;
        temp_gravity = getGravity() + distance * 0.1;
        if (temp_gravity > 30.0) {
            temp_gravity = temp_gravity - 29.0;
        }
        setGravity(temp_gravity);

    }

    @Override
    public void readConfig(String configDir) {
        String directory = configDir + "/fa2.txt";
        Path configPath = Paths.get(directory);
        String config = null;

        try {
            config = Files.readString(configPath);
        } catch (IOException e) {
            System.out.println("an error occurred during reading ForceAtlas2 configuration file.");
            e.printStackTrace();
        }

        ForceAtlas2Config forceAtlas2Config  = JsonDecoder.decode(config, ForceAtlas2Config.class);

        setDissuade_hubs(forceAtlas2Config.isDissuade_hubs());
        setLinlog_mode(forceAtlas2Config.isLinlog_mode());
        setEdge_weight_influence(forceAtlas2Config.getEdge_weight_influence());
        setScaling(forceAtlas2Config.getScaling());
        setStronger_gravity(forceAtlas2Config.isStronger_gravity());
        setGravity(forceAtlas2Config.getGravity());
        setApproximate_repulsion(forceAtlas2Config.isApproximate_repulsion());
    }

    @Override
    public void writeConfig(String configDir) {
        ForceAtlas2Config forceAtlas2Config = new ForceAtlas2Config(this);
        String config = JsonEncoder.encode(forceAtlas2Config);

        String directory = configDir + "/fa2.txt";
        Path configPath = Paths.get(directory);

        try {
            Files.write(configPath, Collections.singleton(config));
        } catch (IOException e) {
            System.out.println("an error occurred during writing ForceAtlas2 configuration file.");
            e.printStackTrace();
        }
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

    public boolean isPrevent_overlap() {
        return prevent_overlap;
    }

    public void setPrevent_overlap(boolean prevent_overlap) {
        this.prevent_overlap = prevent_overlap;
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

    public double getTolerance() {
        return tolerance;
    }

    public void setTolerance(double tolerance) {
        this.tolerance = tolerance;
    }

    public boolean isApproximate_repulsion() {
        return approximate_repulsion;
    }

    public void setApproximate_repulsion(boolean approximate_repulsion) {
        this.approximate_repulsion = approximate_repulsion;
    }

    public double getApproximation() {
        return approximation;
    }

    public void setApproximation(double approximation) {
        this.approximation = approximation;
    }
}
