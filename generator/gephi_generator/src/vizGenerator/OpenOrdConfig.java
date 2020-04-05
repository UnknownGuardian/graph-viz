package vizGenerator;

public class OpenOrdConfig {

    private Integer liquidStage;
    private Integer expansionStage;
    private Integer coolDownStage;
    private Integer crunchStage;
    private Integer simmerStage;
    private long randomSeed;

    public OpenOrdConfig(OpenOrdGen openOrdGen) {

        this.liquidStage = openOrdGen.getLiquidStage();
        this.expansionStage = openOrdGen.getExpansionStage();
        this.coolDownStage = openOrdGen.getCoolDownStage();
        this.crunchStage = openOrdGen.getCrunchStage();
        this.simmerStage = openOrdGen.getSimmerStage();
        this.randomSeed = openOrdGen.getRandomSeed();

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

    public long getRandomSeed() {
        return randomSeed;
    }

    public void setRandomSeed(long randomSeed) {
        this.randomSeed = randomSeed;
    }

    @Override
    public String toString() {
        return "OpenOrdConfig{" +
                "liquidStage=" + liquidStage +
                ", expansionStage=" + expansionStage +
                ", coolDownStage=" + coolDownStage +
                ", crunchStage=" + crunchStage +
                ", simmerStage=" + simmerStage +
                ", randomSeed=" + randomSeed +
                '}';
    }
}
