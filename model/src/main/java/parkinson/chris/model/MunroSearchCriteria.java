package parkinson.chris.model;

public class MunroSearchCriteria {

    private MunroClassification hillCategory;
    private SortColumn sortColumn;
    private SortPriority sortPriority;
    private Integer maximumResults;
    private Double minimumHeight;
    private Double maximumHeight;

    public MunroClassification getHillCategory() {
        return hillCategory;
    }

    public void setHillCategory(MunroClassification hillCategory) {
        this.hillCategory = hillCategory;
    }

    public SortColumn getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(SortColumn sortColumn) {
        this.sortColumn = sortColumn;
    }

    public SortPriority getSortPriority() {
        return sortPriority;
    }

    public void setSortPriority(SortPriority sortPriority) {
        this.sortPriority = sortPriority;
    }

    public Integer getMaximumResults() {
        return maximumResults;
    }

    public void setMaximumResults(Integer maximumResults) {
        this.maximumResults = maximumResults;
    }

    public Double getMinimumHeight() {
        return minimumHeight;
    }

    public void setMinimumHeight(Double minimumHeight) {
        this.minimumHeight = minimumHeight;
    }

    public Double getMaximumHeight() {
        return maximumHeight;
    }

    public void setMaximumHeight(Double maximumHeight) {
        this.maximumHeight = maximumHeight;
    }
}
