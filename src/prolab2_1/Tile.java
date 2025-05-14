package prolab2_1;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import javax.swing.ImageIcon;

public class Tile {

    private Engel engel;
    private Hazine hazine;

    public Tile(Engel engel, Hazine hazine) {
        this.engel = engel;
        this.hazine = hazine;
    }

    public boolean engel_var_mi() {
        return engel != null;
    }

    public boolean hazine_var_mi() {
        return hazine != null;
    }

    public Engel getEngel() {
        return engel;
    }

    public void setEngel(Engel engel) {
        this.engel = engel;
    }

    public Hazine getHazine() {
        return hazine;
    }

    public void setHazine(Hazine hazine) {
        this.hazine = hazine;
    }

    

    public ImageIcon getEngelResmi() {
        if (engel_var_mi()) {
            return engel.getIcon();
        }
        return null;
    }

    public ImageIcon getHazineResmi() {
        if (hazine_var_mi()) {
            return hazine.getIcon();
        }
        return null;
    }

}
