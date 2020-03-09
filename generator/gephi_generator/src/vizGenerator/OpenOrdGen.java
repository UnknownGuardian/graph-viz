package vizGenerator;

import org.gephi.layout.plugin.openord.OpenOrdLayout;

public class OpenOrdGen extends Generation {

    private Integer liquidStage;
    private Integer expansionStage;
    private Integer coolDownStage;
    private Integer crunchStage;
    private Integer simmerStage;
    private Float edgeCut;
    private Integer numIterations;

    private OpenOrdLayout layout;

    public OpenOrdGen(int nb_steps, String import_file_path, String export_file_path, Integer liquidStage, Integer expansionStage, Integer coolDownStage, Integer crunchStage, Integer simmerStage, Float edgeCut, Integer numIterations) {
        super(nb_steps, import_file_path, export_file_path);
        this.liquidStage = liquidStage;
        this.expansionStage = expansionStage;
        this.coolDownStage = coolDownStage;
        this.crunchStage = crunchStage;
        this.simmerStage = simmerStage;
        this.edgeCut = edgeCut;
        this.numIterations = numIterations;

        this.layout = new OpenOrdLayout(null);
        this.layout.setGraphModel(graphModel);
        this.layout.resetPropertiesValues();
    }

    @Override
    public void setVizProperties(){
        layout.setLiquidStage(liquidStage);
        layout.setExpansionStage(expansionStage);
        layout.setCooldownStage(coolDownStage);
        layout.setCrunchStage(crunchStage);
        layout.setSimmerStage(simmerStage);
        layout.setEdgeCut(edgeCut);
        layout.setNumIterations(numIterations);
    }

    @Override
    public void startLayouting(){
        layout.initAlgo();
        for (int i = 0; i < nb_steps; i++) {
            layout.goAlgo();
        }
        layout.endAlgo();
    }

    public Integer getLiquidStage() {
        return liquidStage;
    }

    public void setLiquidStage(Integer liquidStage) {
        this.liquidStage = liquidStage;
    }

    public Integer getExpansionStage() {
        return expansionStage;
    }

    public void setExpansionStage(Integer expansionStage) {
        this.expansionStage = expansionStage;
    }

    public Integer getCoolDownStage() {
        return coolDownStage;
    }

    public void setCoolDownStage(Integer coolDownStage) {
        this.coolDownStage = coolDownStage;
    }

    public Integer getCrunchStage() {
        return crunchStage;
    }

    public void setCrunchStage(Integer crunchStage) {
        this.crunchStage = crunchStage;
    }

    public Integer getSimmerStage() {
        return simmerStage;
    }

    public void setSimmerStage(Integer simmerStage) {
        this.simmerStage = simmerStage;
    }

    public Float getEdgeCut() {
        return edgeCut;
    }

    public void setEdgeCut(Float edgeCut) {
        this.edgeCut = edgeCut;
    }

    public Integer getNumIterations() {
        return numIterations;
    }

    public void setNumIterations(Integer numIterations) {
        this.numIterations = numIterations;
    }
}
