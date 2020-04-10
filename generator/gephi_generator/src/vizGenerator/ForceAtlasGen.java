package vizGenerator;

import org.gephi.layout.plugin.forceAtlas.ForceAtlasLayout;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Random;

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
        Random rd = new Random();

        this.setAttractionDistribution(rd.nextBoolean());
        this.setFreezeBalance(rd.nextBoolean());

        this.setGravity(100.0 + rd.nextDouble() * 900);
        this.setAttractionStrength(rd.nextDouble() * 30);
        this.setCooling(1 + rd.nextDouble() * 9);
        this.setFreezeStrength(1 + rd.nextDouble() * 9);
        this.setRepulsionStrength(100.0 + rd.nextDouble() * 900);
    }

    @Override
    public void adjustSettingInNeighborhood(int distance){
        double temp_gravity = getGravity();
        temp_gravity = adjustDoubleParam(temp_gravity, distance, 2.0, 1000.0, 100.0);
//        temp_gravity = getGravity() + distance;
//        if (temp_gravity > 1000.0){
//            temp_gravity = temp_gravity - 900.0;
//        }
        setGravity(temp_gravity);

        double temp_attractionStrength = getAttractionStrength();
        temp_attractionStrength = adjustDoubleParam(temp_attractionStrength, distance, 0.1, 30.0, 0.0);
//        temp_attractionStrength = getAttractionStrength() + distance * 0.05;
//        if (temp_attractionStrength > 30.0){
//            temp_attractionStrength = temp_attractionStrength - 30.0;
//        }
        setAttractionStrength(temp_attractionStrength);

        double temp_cooling = getCooling();
        temp_cooling = adjustDoubleParam(temp_cooling, distance, 0.1, 10.0, 1.0);
//        temp_cooling = getCooling() + distance * 0.05;
//        if (temp_cooling > 10.0){
//            temp_cooling = temp_cooling - 9.0;
//        }
        setCooling(temp_cooling);

        double temp_freezeStrength = getFreezeStrength();
        temp_freezeStrength = adjustDoubleParam(temp_freezeStrength, distance, 0.1, 10.0, 1.0);
//        temp_freezeStrength = getFreezeStrength() + distance * 0.05;
//        if (temp_freezeStrength > 10.0){
//            temp_freezeStrength = temp_freezeStrength - 9.0;
//        }
        setFreezeStrength(temp_freezeStrength);

        double temp_repulsionStrength = getRepulsionStrength();
        temp_repulsionStrength = adjustDoubleParam(temp_repulsionStrength, distance, 1.0, 1000.0, 100.0);
//        temp_repulsionStrength = getRepulsionStrength() + distance;
//        if (temp_repulsionStrength > 1000.0){
//            temp_repulsionStrength = temp_repulsionStrength - 900.0;
//        }
        setRepulsionStrength(temp_repulsionStrength);
    }

    @Override
    public void readConfig(String configDir) {
        String directory = configDir + "/fa.txt";
        Path configPath = Paths.get(directory);
        String config = null;

        try {
            config = Files.readString(configPath);
        } catch (IOException e) {
            System.out.println("an error occurred during reading ForceAtlas configuration file.");
            e.printStackTrace();
        }

        ForceAtlasConfig forceAtlasConfig = JsonDecoder.decode(config, ForceAtlasConfig.class);

        setGravity(forceAtlasConfig.getGravity());
        setAttractionDistribution(forceAtlasConfig.getAttractionDistribution());
        setAttractionStrength(forceAtlasConfig.getAttractionStrength());
        setCooling(forceAtlasConfig.getCooling());
        setFreezeBalance(forceAtlasConfig.getFreezeBalance());
        setFreezeStrength(forceAtlasConfig.getFreezeStrength());
        setRepulsionStrength(forceAtlasConfig.getRepulsionStrength());
    }

    @Override
    public void writeConfig(String configDir) {
        ForceAtlasConfig forceAtlasConfig = new ForceAtlasConfig(this);
        String config = JsonEncoder.encode(forceAtlasConfig);

        String directory = configDir + "/fa.txt";
        Path configPath = Paths.get(directory);

        try {
            Files.write(configPath, Collections.singleton(config));
        } catch (IOException e) {
            System.out.println("an error occurred during writing ForceAtlas configuration file.");
            e.printStackTrace();
        }
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
