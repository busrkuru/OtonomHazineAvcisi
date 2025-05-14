package prolab2_1;

import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.ImageIcon;

public class Karakter {

    private int id;
    private String ad;
    private Konum konum;
    private ImageIcon karakterGorseli;
    private ArrayList<Hazine> toplananHazineler = new ArrayList<>();

    public Karakter(int id, String ad, Konum konum) {
        this.id = id;
        this.ad = ad;
        this.konum = konum;
        resimYukle("/prolab2_1/resources/karakter_gorseli.png");
    }

    private void resimYukle(String resimPath) {
        URL resimUrl = getClass().getResource(resimPath);
        if (resimUrl != null) {
            ImageIcon originalIcon = new ImageIcon(resimUrl);
            Image resim = originalIcon.getImage();
            Image yeniResim = resim.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            this.karakterGorseli = new ImageIcon(yeniResim);
        } else {
            System.err.println("Karakter görseli yüklenemedi: " + resimPath);
            this.karakterGorseli = null;
        }
    }

    public ImageIcon getKarakterGorseli() {
        return karakterGorseli;
    }

    public void setKarakterGorseli(ImageIcon karakterGorseli) {
        this.karakterGorseli = karakterGorseli;
    }

    public void hareketEtVeTopla(Tile[][] harita) {
        int x = konum.getX1();
        int y = konum.getY1();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int yeniX = x + i;
                int yeniY = y + j;

                if (yeniX >= 0 && yeniX < harita[0].length && yeniY >= 0 && yeniY < harita.length) {
                    Tile tile = harita[yeniY][yeniX];

                    if (tile.hazine_var_mi() && !toplananHazineler.contains(tile.getHazine())) {
                        Hazine hazine = tile.getHazine();
                        toplananHazineler.add(hazine);

                        System.out.println(hazine.getTür() + " sandık toplandı! ("
                                + hazine.getKonum().getX1() + "," + hazine.getKonum().getY1()
                                + ") konumunda bulundu.");
                        tile.setHazine(null);
                    }
                }
            }
        }

        hazineSiralamaVeGoster();
    }

    public void hazineSiralamaVeGoster() {
        Collections.sort(toplananHazineler, new Comparator<Hazine>() {
            @Override
            public int compare(Hazine h1, Hazine h2) {
                return Integer.compare(h1.getOncelik(), h2.getOncelik());
            }
        });

        for (Hazine hazine : toplananHazineler) {
            System.out.println(hazine.getTür() + " sandık toplandı! ("
                    + hazine.getKonum().getX1() + "," + hazine.getKonum().getY1()
                    + ") konumunda bulundu.");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public Konum getKonum() {
        return konum;
    }

    public void setKonum(Konum konum) {
        this.konum = konum;
    }

    public ArrayList<Hazine> getToplananHazineler() {
        return toplananHazineler;
    }

    public void setToplananHazineler(ArrayList<Hazine> toplananHazineler) {
        this.toplananHazineler = toplananHazineler;
    }

}
