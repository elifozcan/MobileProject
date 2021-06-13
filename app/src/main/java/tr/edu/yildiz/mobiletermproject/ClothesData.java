package tr.edu.yildiz.mobiletermproject;

import android.net.Uri;

public class ClothesData {
    String clothes_type, color, pattern, purchase_date, price, path;


    public ClothesData(String clothes_type, String color, String pattern, String purchase_date, String price, String path) {
        this.clothes_type = clothes_type;
        this.color = color;
        this.pattern = pattern;
        this.purchase_date = purchase_date;
        this.price = price;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getClothes_type() {
        return clothes_type;
    }

    public void setClothes_type(String clothes_type) {
        this.clothes_type = clothes_type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(String purchase_date) {
        this.purchase_date = purchase_date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
