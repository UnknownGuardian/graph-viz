package vizGenerator;

public class ForceAtlasConfig {

    private Double gravity;
    private Boolean attractionDistribution;
    private Double attractionStrength;
    private Double cooling;
    private Boolean freezeBalance;
    private Double freezeStrength;
    private Double repulsionStrength;

    public ForceAtlasConfig(ForceAtlasGen forceAtlasGen) {
        this.gravity = forceAtlasGen.getGravity();
        this.attractionDistribution = forceAtlasGen.getAttractionDistribution();
        this.attractionStrength = forceAtlasGen.getAttractionStrength();
        this.cooling = forceAtlasGen.getCooling();
        this.freezeBalance = forceAtlasGen.getFreezeBalance();
        this.freezeStrength = forceAtlasGen.getFreezeStrength();
        this.repulsionStrength = forceAtlasGen.getRepulsionStrength();
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

    public Double getRepulsionStrength() {
        return repulsionStrength;
    }

    public void setRepulsionStrength(Double repulsionStrength) {
        this.repulsionStrength = repulsionStrength;
    }

    @Override
    public String toString() {
        return "ForceAtlasConfig{" +
                "gravity=" + gravity +
                ", attractionDistribution=" + attractionDistribution +
                ", attractionStrength=" + attractionStrength +
                ", cooling=" + cooling +
                ", freezeBalance=" + freezeBalance +
                ", freezeStrength=" + freezeStrength +
                ", repulsionStrength=" + repulsionStrength +
                '}';
    }
}
