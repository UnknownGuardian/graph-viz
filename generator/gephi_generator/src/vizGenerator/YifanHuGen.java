package vizGenerator;

import org.gephi.layout.plugin.force.StepDisplacement;
import org.gephi.layout.plugin.force.yifanHu.YifanHu;
import org.gephi.layout.plugin.force.yifanHu.YifanHuLayout;
import org.gephi.layout.spi.Layout;

public class YifanHuGen extends Generation {

    private Float optimalDistance;
    private Float relativeStrength;
    private boolean adaptiveCooling;
    private Float convergenceThreshold;
    private int quadTreeMaxLevel;
    private Float theta;

    private YifanHuLayout layout;

    public YifanHuGen(int nb_steps, String import_file_path, String export_file_path, Float optimalDistance, Float relativeStrength, boolean adaptiveCooling, Float convergenceThreshold, int quadTreeMaxLevel, Float theta) {
        super(nb_steps, import_file_path, export_file_path);
        this.optimalDistance = optimalDistance;
        this.relativeStrength = relativeStrength;
        this.adaptiveCooling = adaptiveCooling;
        this.convergenceThreshold = convergenceThreshold;
        this.quadTreeMaxLevel = quadTreeMaxLevel;
        this.theta = theta;

        this.layout = new YifanHuLayout(null, new StepDisplacement(1f));
        this.layout.setGraphModel(graphModel);
        this.layout.resetPropertiesValues();
    }

    @Override
    public void setVizProperties(){
        layout.setOptimalDistance(optimalDistance);
        layout.setRelativeStrength(relativeStrength);
        layout.setInitialStep(0.1f * optimalDistance);
        layout.setStepRatio(0.95f);
        layout.setAdaptiveCooling(adaptiveCooling);
        layout.setConvergenceThreshold(convergenceThreshold);
        layout.setQuadTreeMaxLevel(quadTreeMaxLevel);
        layout.setBarnesHutTheta(theta);
    }

    @Override
    public void startLayouting(){
        layout.initAlgo();
        for (int i = 0; i < nb_steps; i++) {
            layout.goAlgo();
        }
        layout.endAlgo();
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

    public Float getConvergenceThreshold() {
        return convergenceThreshold;
    }

    public void setConvergenceThreshold(Float convergenceThreshold) {
        this.convergenceThreshold = convergenceThreshold;
    }

    public int getQuadTreeMaxLevel() {
        return quadTreeMaxLevel;
    }

    public void setQuadTreeMaxLevel(int quadTreeMaxLevel) {
        this.quadTreeMaxLevel = quadTreeMaxLevel;
    }

    public Float getTheta() {
        return theta;
    }

    public void setTheta(Float theta) {
        this.theta = theta;
    }
}
