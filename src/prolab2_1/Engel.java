package prolab2_1;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.net.URL;
import java.util.Map;

public abstract class Engel implements IEngel {

    protected Konum konum;

    public Engel(Konum konum) {
        this.konum = konum;
    }
    public Konum getKonum() {
        return konum;
    }

    public void setKonum(Konum konum) {
        this.konum = konum;
    }
    
    @Override
    public abstract boolean gecilebilirMi();

    @Override
    public abstract ImageIcon getIcon();

    

}

abstract class HareketsizEngeller extends Engel {

    public HareketsizEngeller(Konum konum) {
        super(konum);
    }

    @Override
    public boolean gecilebilirMi() {
        return false; 
    }
}

class Agac extends HareketsizEngeller implements IResimYukle {

    private ImageIcon icon;

    public Agac(Konum konum, String resim_path) {
        super(konum);
        resimYukle(resim_path);
    }
    
    @Override
    public void resimYukle(String resim_path) {
        ImageIcon originalIcon = new ImageIcon(getClass().getResource(resim_path));
        Image resim = originalIcon.getImage();
        Image yeni_resim = resim.getScaledInstance(konum.getGenislik() * 100, konum.getYukseklik() * 100, Image.SCALE_SMOOTH);
        this.icon = new ImageIcon(yeni_resim);
    }

    
    @Override
    public ImageIcon getIcon() {
        return icon;
    }

    @Override
    public boolean gecilebilirMi() {
        return false;
    }

}

class KisAgac extends Agac {

    public KisAgac(Konum konum) {
        super(konum, "/prolab2_1/resources/tree_2x2.png"); 
    }
}

class YazAgac extends Agac {

    public YazAgac(Konum konum) {
        super(konum, "/prolab2_1/resources/tree_3x3.png"); 
    }
}

class Dag extends HareketsizEngeller implements IResimYukle {

    private ImageIcon icon;

    public Dag(Konum konum, String resim_path) {
        super(konum);
        resimYukle(resim_path);
    }
    
    @Override
    public void resimYukle(String resim_path) {
        ImageIcon originalIcon = new ImageIcon(getClass().getResource(resim_path));
        Image resim = originalIcon.getImage();
        Image yeni_resim = resim.getScaledInstance(konum.getGenislik() * 100, konum.getYukseklik() * 100, Image.SCALE_SMOOTH);
        this.icon = new ImageIcon(yeni_resim);
    }

    @Override
    public ImageIcon getIcon() {
        return icon;
    }

    @Override
    public boolean gecilebilirMi() {
        return false;
    }

}

class KisDag extends Dag {

    public KisDag(Konum konum) {
        super(konum, "/prolab2_1/resources/mountain_winter.png"); 
    }
}

class YazDag extends Dag {

    public YazDag(Konum konum) {
        super(konum, "/prolab2_1/resources/mountain_summer.png"); 
    }
}

class Kaya extends HareketsizEngeller implements IResimYukle {

    private ImageIcon icon;

    public Kaya(Konum konum, String resim_path) {
        super(konum);
        resimYukle(resim_path);
    }

    @Override
    public void resimYukle(String resim_path) {
        ImageIcon originalIcon = new ImageIcon(getClass().getResource(resim_path));
        Image resim = originalIcon.getImage();
        Image yeni_resim = resim.getScaledInstance(konum.getGenislik() * 100, konum.getYukseklik() * 100, Image.SCALE_SMOOTH);
        this.icon = new ImageIcon(yeni_resim);
    }

    @Override
    public ImageIcon getIcon() {
        return icon;
    }

    @Override
    public boolean gecilebilirMi() {
        return false;
    }

}

class KisKaya extends Kaya {

    public KisKaya(Konum konum) {
        super(konum, "/prolab2_1/resources/winter_rock.png"); 
    }
}

class YazKaya extends Kaya {

    public YazKaya(Konum konum) {
        super(konum, "/prolab2_1/resources/summer_rock.png");
    }
}

abstract class Duvar extends HareketsizEngeller implements IResimYukle {

    protected ImageIcon icon;

    public Duvar(Konum konum, String resim_path) {
        super(konum);
        resimYukle(resim_path);
    }
    
    @Override
    public void resimYukle(String resim_path) {
        ImageIcon originalIcon = new ImageIcon(getClass().getResource(resim_path));
        Image resim = originalIcon.getImage();
        Image yeni_resim = resim.getScaledInstance(konum.getGenislik() * 100, konum.getYukseklik() * 100, Image.SCALE_SMOOTH);
        this.icon = new ImageIcon(yeni_resim);
    }

    @Override
    public ImageIcon getIcon() {
        return icon;
    }

    @Override
    public boolean gecilebilirMi() {
        return false;
    }
}

class WinterWall extends Duvar {

    public WinterWall(Konum konum) {
        super(konum, "/prolab2_1/resources/winter_wall.png"); 
    }
}

class SummerWall extends Duvar {

    public SummerWall(Konum konum) {
        super(konum, "/prolab2_1/resources/summer_wall.png");
    }
}

class Hazine {

    private Konum konum;
    private String tur;
    private ImageIcon icon;

    public Hazine(Konum konum, String tur) {
        this.konum = konum;
        this.tur = tur;
        resimYukle(this.tur);
    }
    private static final Map<String, String> GORSEL_YOLLARI = Map.of(
        "altin", "/prolab2_1/resources/altın.png",
        "gumus", "/prolab2_1/resources/gümüş.png",
        "zumrut", "/prolab2_1/resources/zümrüt.png",
        "bakir", "/prolab2_1/resources/bakır.png"
    );

    private void resimYukle(String tur) {
        String resimYolu = GORSEL_YOLLARI.get(tur);
        if (resimYolu != null) {
            ImageIcon originalIcon = new ImageIcon(getClass().getResource(resimYolu));
            Image resim = originalIcon.getImage();
            Image yeniResim = resim.getScaledInstance(konum.getGenislik() * 100, konum.getYukseklik() * 100, Image.SCALE_SMOOTH);
            this.icon = new ImageIcon(yeniResim);
        } else {
            throw new IllegalArgumentException("Tanımlanmayan hazine türü: " + tur);
        }
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public Konum getKonum() {
        return konum;
    }

    public void setKonum(Konum konum) {
        this.konum = konum;
    }

    public String getTür() {
        return tur;
    }

    public void setTür(String tur) {
        this.tur = tur;
    }
    public int getOncelik() {
        switch (tur) {
            case "altin":
                return 1;
            case "gumus":
                return 2;
            case "zumrut":
                return 3;
            case "bakir":
                return 4;
            default:
                return Integer.MAX_VALUE;
        }
    }

    
}

abstract class HareketliEngeller extends Engel {

    protected int hareket_araligi; 
    public HareketliEngeller(Konum konum, int hareket_araligi) {
        super(konum);
        this.hareket_araligi = hareket_araligi;
    }

    public abstract void hareket(); 

    @Override
    public boolean gecilebilirMi() {
        return false; 
    }
}

class Kus extends HareketliEngeller implements IHareket {

    private ImageIcon icon;
    private int originalY; 
    private boolean yukari_hareket = true; 

    public Kus(Konum konum) {
        super(konum, 5); 
        this.originalY = konum.getY1(); 
        resimYukle();
    }

    private void resimYukle() {
    String resim_path = "/prolab2_1/resources/bird.png";
    URL imageUrl = getClass().getResource(resim_path);
    if (imageUrl != null) {
        icon = new ImageIcon(imageUrl);
        Image resim = icon.getImage();
        Image yeni_resim = resim.getScaledInstance(50, 50, Image.SCALE_SMOOTH); 
        icon = new ImageIcon(yeni_resim);
    } else {
        System.err.println("Kuş görseli yüklenemedi: " + resim_path);
    }
}


    

    @Override
    public void hareket() {
        if (yukari_hareket) {
            if (konum.getY1() > originalY - hareket_araligi) {
                konum.setY1(konum.getY1() - 1); 
            } else {
                yukari_hareket = false; 
            }
        } else {
            if (konum.getY1() < originalY + hareket_araligi) {
                konum.setY1(konum.getY1() + 1);
            } else {
                yukari_hareket = true; 
            }
        }
    }

    @Override
    public ImageIcon getIcon() {
        return icon;
    }

    @Override
    public boolean gecilebilirMi() {
        return false;
    }
}
