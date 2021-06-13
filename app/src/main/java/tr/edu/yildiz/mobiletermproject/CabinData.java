package tr.edu.yildiz.mobiletermproject;

public class CabinData {
    private String CombineName;
    private Integer cabinImage;

    public CabinData(String combineName, Integer cabinImage) {
        CombineName = combineName;
        this.cabinImage = cabinImage;
    }

    public String getCombineName() {
        return CombineName;
    }

    public void setCombineName(String combineName) {
        CombineName = combineName;
    }

    public Integer getCabinImage() {
        return cabinImage;
    }

    public void setCabinImage(Integer cabinImage) {
        this.cabinImage = cabinImage;
    }
}
