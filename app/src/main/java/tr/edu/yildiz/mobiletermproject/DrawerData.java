package tr.edu.yildiz.mobiletermproject;

public class DrawerData {

    private String DrawerName;
    private Integer drawerImage;

    public DrawerData(String drawerName, Integer drawerImage) {

        DrawerName = drawerName;
        this.drawerImage = drawerImage;
    }

    public Integer getDrawerImage() {
        return drawerImage;
    }

    public void setDrawerImage(Integer drawerImage) {
        this.drawerImage = drawerImage;
    }

    public String getDrawerName() {
        return DrawerName;
    }

    public void setDrawerName(String drawerName) {
        DrawerName = drawerName;
    }
}
