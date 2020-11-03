package parkinson.chris.model;

public class MunroResult {
    private String name;
    private String heightMeters;
    private String hillCategory;
    private String gridReference;

    public MunroResult(String name, String heightMeters, String hillCategory, String gridReference) {
        this.name = name;
        this.heightMeters = heightMeters;
        this.hillCategory = hillCategory;
        this.gridReference = gridReference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeightMeters() {
        return heightMeters;
    }

    public void setHeightMeters(String heightMeters) {
        this.heightMeters = heightMeters;
    }

    public String getHillCategory() {
        return hillCategory;
    }

    public void setHillCategory(String hillCategory) {
        this.hillCategory = hillCategory;
    }

    public String getGridReference() {
        return gridReference;
    }

    public void setGridReference(String gridReference) {
        this.gridReference = gridReference;
    }
}
