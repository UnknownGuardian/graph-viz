package vizGenerator;

import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.graph.api.UndirectedGraph;
import org.gephi.io.exporter.api.ExportController;
import org.gephi.io.exporter.preview.PNGExporter;
import org.gephi.io.exporter.spi.GraphExporter;
import org.gephi.io.importer.api.Container;
import org.gephi.io.importer.api.EdgeDirectionDefault;
import org.gephi.io.importer.api.ImportController;
import org.gephi.io.processor.plugin.DefaultProcessor;
import org.gephi.layout.plugin.forceAtlas.ForceAtlas;
import org.gephi.layout.plugin.forceAtlas.ForceAtlasLayout;
import org.gephi.layout.plugin.forceAtlas2.ForceAtlas2;
import org.gephi.preview.api.PreviewController;
import org.gephi.preview.api.PreviewModel;
import org.gephi.preview.api.PreviewProperty;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.gephi.statistics.plugin.GraphDistance;
import org.openide.util.Lookup;

import java.io.File;
import java.io.IOException;

public class ForceAtlasGen extends Generation {

    private Boolean adjustSize;
    private Double gravity;
    private Boolean attractionDistribution;
    private Double attractionStrength;
    private Double cooling;
    private Boolean freezeBalance;
    private Double freezeStrength;
    private Double maxDisplacement;
    private Double inertia;
    private Double repulsionStrength;
    private Double speed;
    private Double freezeInertia;

    private ForceAtlasLayout layout;

    public ForceAtlasGen(int nb_steps,
                         String import_file_path,
                         String export_file_path,
                         Boolean adjustSize,
                         Double gravity,
                         Boolean attractionDistribution,
                         Double attractionStrength,
                         Double cooling,
                         Boolean freezeBalance,
                         Double freezeStrength,
                         Double maxDisplacement,
                         Double inertia,
                         Double repulsionStrength,
                         Double speed,
                         Double freezeInertia) {
        super(nb_steps, import_file_path, export_file_path);

        this.adjustSize = adjustSize;
        this.gravity = gravity;
        this.attractionDistribution = attractionDistribution;
        this.attractionStrength = attractionStrength;
        this.cooling = cooling;
        this.freezeBalance = freezeBalance;
        this.freezeStrength = freezeStrength;
        this.maxDisplacement = maxDisplacement;
        this.inertia = inertia;
        this.repulsionStrength = repulsionStrength;
        this.speed = speed;
        this.freezeInertia = freezeInertia;

        this.layout = new ForceAtlasLayout(null);
        this.layout.setGraphModel(graphModel);
        this.layout.resetPropertiesValues();
    }

    @Override
    public void setVizProperties(){
        layout.setAdjustSizes(adjustSize);
        layout.setGravity(gravity);
        layout.setOutboundAttractionDistribution(attractionDistribution);
        layout.setAttractionStrength(attractionStrength);
        layout.setCooling(cooling);
        layout.setFreezeBalance(freezeBalance);
        layout.setFreezeStrength(freezeStrength);
        layout.setInertia(inertia);
        layout.setMaxDisplacement(maxDisplacement);
        layout.setRepulsionStrength(repulsionStrength);
        layout.setSpeed(speed);
        layout.setFreezeInertia(freezeInertia);
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

    }

    public Boolean getAdjustSize() {
        return adjustSize;
    }

    public void setAdjustSize(Boolean adjustSize) {
        this.adjustSize = adjustSize;
    }

    public Double getGravity() {
        return gravity;
    }

    public void setGravity(Double gravity) {
        this.gravity = gravity;
    }

    public Boolean getAttractionDistribution() {
        return attractionDistribution;
    }

    public void setAttractionDistribution(Boolean attractionDistribution) {
        this.attractionDistribution = attractionDistribution;
    }

    public Double getAttractionStrength() {
        return attractionStrength;
    }

    public void setAttractionStrength(Double attractionStrength) {
        this.attractionStrength = attractionStrength;
    }

    public Double getCooling() {
        return cooling;
    }

    public void setCooling(Double cooling) {
        this.cooling = cooling;
    }

    public Boolean getFreezeBalance() {
        return freezeBalance;
    }

    public void setFreezeBalance(Boolean freezeBalance) {
        this.freezeBalance = freezeBalance;
    }

    public Double getFreezeStrength() {
        return freezeStrength;
    }

    public void setFreezeStrength(Double freezeStrength) {
        this.freezeStrength = freezeStrength;
    }

    public Double getMaxDisplacement() {
        return maxDisplacement;
    }

    public void setMaxDisplacement(Double maxDisplacement) {
        this.maxDisplacement = maxDisplacement;
    }

    public Double getInertia() {
        return inertia;
    }

    public void setInertia(Double inertia) {
        this.inertia = inertia;
    }

    public Double getRepulsionStrength() {
        return repulsionStrength;
    }

    public void setRepulsionStrength(Double repulsionStrength) {
        this.repulsionStrength = repulsionStrength;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getFreezeInertia() {
        return freezeInertia;
    }

    public void setFreezeInertia(Double freezeInertia) {
        this.freezeInertia = freezeInertia;
    }
}
