package vizGenerator;

import org.gephi.layout.plugin.openord.OpenOrdLayout;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Random;

public class OpenOrdGen extends Generation {

    private Integer liquidStage;
    private Integer expansionStage;
    private Integer coolDownStage;
    private Integer crunchStage;
    private Integer simmerStage;
    private Float edgeCut;
    private Integer numIterations;
    private long randomSeed;

    private OpenOrdLayout layout;

    public OpenOrdGen(int nb_steps, String import_file_path, String export_file_path, Integer liquidStage, Integer expansionStage, Integer coolDownStage, Integer crunchStage, Integer simmerStage, Float edgeCut, Integer numIterations, long randomSeed) {
        super(nb_steps, import_file_path, export_file_path);
        this.liquidStage = liquidStage;
        this.expansionStage = expansionStage;
        this.coolDownStage = coolDownStage;
        this.crunchStage = crunchStage;
        this.simmerStage = simmerStage;
        this.edgeCut = edgeCut;
        this.numIterations = numIterations;

        this.randomSeed = randomSeed;

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
        layout.setNumThreads(7);
        layout.setRandSeed(randomSeed);
//
    }

    @Override
    public void startLayouting(){
        int n;

        if (nb_steps > numIterations){
            n = numIterations;
        } else {
            n = nb_steps;
        }

        layout.initAlgo();
        for (int i = 0; i < n; i++) {
            layout.goAlgo();
        }
        layout.endAlgo();
    }

    @Override
    public void randomizeLayoutSettings(){
        Random rd = new Random();

        this.setRandomSeed(rd.nextLong());

    }

    @Override
    public void adjustSettingInNeighborhood(int distance){

        Integer temp_liquidStage = getLiquidStage();
        temp_liquidStage = adjustIntParam(temp_liquidStage, distance, 1, 100, 0);
        setLiquidStage(temp_liquidStage);

        Integer temp_expansionStage = getExpansionStage();
        temp_expansionStage = adjustIntParam(temp_expansionStage, distance, 1, 100, 0);
        setExpansionStage(temp_expansionStage);

        Integer temp_coolDownStage = getCoolDownStage();
        temp_coolDownStage = adjustIntParam(temp_coolDownStage, distance, 1, 100, 0);
        setCoolDownStage(temp_coolDownStage);

        Integer temp_crunchStage = getCrunchStage();
        temp_crunchStage = adjustIntParam(temp_crunchStage, distance, 1, 100, 0);
        setCrunchStage(temp_crunchStage);

        Integer temp_simmerStage = getSimmerStage();
        temp_simmerStage = adjustIntParam(temp_simmerStage, distance, 1, 100, 0);
        setSimmerStage(temp_simmerStage);

    }

    @Override
    public void readConfig(String configDir) {
        String directory = configDir + "/oo.txt";
        Path configPath = Paths.get(directory);
        String config = null;

        try {
            config = Files.readString(configPath);
        } catch (IOException e) {
            System.out.println("an error occurred during reading OpenOrd configuration file.");
            e.printStackTrace();
        }

        OpenOrdConfig openOrdConfig = JsonDecoder.decode(config, OpenOrdConfig.class);

        setLiquidStage(openOrdConfig.getLiquidStage());
        setExpansionStage(openOrdConfig.getExpansionStage());
        setCoolDownStage(openOrdConfig.getCoolDownStage());
        setCrunchStage(openOrdConfig.getCrunchStage());
        setSimmerStage(openOrdConfig.getSimmerStage());
        setRandomSeed(openOrdConfig.getRandomSeed());

    }

    @Override
    public void writeConfig(String configDir) {

        OpenOrdConfig openOrdConfig = new OpenOrdConfig(this);
        String config = JsonEncoder.encode(openOrdConfig);

        String directory = configDir + "/oo.txt";
        Path configPath = Paths.get(directory);

        try {
            Files.write(configPath, Collections.singleton(config));
        } catch (IOException e) {
            System.out.println("an error occurred during writing OpenOrd configuration file.");
            e.printStackTrace();
        }
    }

    private boolean isParametersValid() {
        return ((liquidStage > 0) && (liquidStage < 100)
                && (expansionStage > 0) && (expansionStage < 100)
                && (coolDownStage > 0) && (coolDownStage < 100)
                && (crunchStage > 0) && (crunchStage < 100)
                && (simmerStage > 0) && (simmerStage < 100));
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

    public long getRandomSeed() {
        return randomSeed;
    }

    public void setRandomSeed(long randomSeed) {
        this.randomSeed = randomSeed;
    }

    @Override
    public String toString() {
        return "OpenOrdGen{" +
                "liquidStage=" + liquidStage +
                ", expansionStage=" + expansionStage +
                ", coolDownStage=" + coolDownStage +
                ", crunchStage=" + crunchStage +
                ", simmerStage=" + simmerStage +
                ", edgeCut=" + edgeCut +
                ", numIterations=" + numIterations +
                ", randomSeed=" + randomSeed +
                '}';
    }
}
