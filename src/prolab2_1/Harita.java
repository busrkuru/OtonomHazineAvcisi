package prolab2_1;

import java.util.Random;

public class Harita {

    private final int genislik;
    private final int yukseklik;
    private final Tile[][] tiles;

    public Harita(int genislik, int yukseklik) {
        this.genislik = genislik;
        this.yukseklik = yukseklik;
        this.tiles = new Tile[yukseklik][genislik];
        initializeMap();
    }

    private void initializeMap() {
        Random random = new Random();
        for (int i = 0; i < yukseklik; i++) {
            for (int j = 0; j < genislik; j++) {
                tiles[i][j] = new Tile(null, null);
            }
        }

        double agac_sans = 0.01;
        double dag_sans = 0.005;
        double kaya_sans = 0.01;
        double duvar_sans = 0.005;
        double kus_sans = 0.001;
        double hazineSans = 0.005;
        String[] hazineTurleri = {"altin", "gumus", "zumrut", "bakir"};
        for (int i = 0; i < yukseklik; i++) {
            for (int j = 0; j < genislik; j++) {

                if (random.nextDouble() < agac_sans) {
                    int boyut = random.nextInt(3) + 2;
                    if (i + boyut <= yukseklik && j + boyut <= genislik && agac_yerlestirilebilir_mi(i, j, boyut)) {
                        Agac agac;
                        if (j < genislik / 2) {
                            agac = new KisAgac(new Konum(j, i, boyut, boyut));
                        } else {
                            agac = new YazAgac(new Konum(j, i, boyut, boyut));
                        }
                        agac_doldur(i, j, boyut, agac);
                    }
                } else if (random.nextDouble() < dag_sans) {
                    int boyut = 15;
                    if (i + boyut <= yukseklik && j + boyut <= genislik && dag_yerlestirilebilir_mi(i, j, boyut)) {
                        Dag dag;
                        if (j < genislik / 2) {
                            dag = new KisDag(new Konum(j, i, boyut, boyut));
                        } else {
                            dag = new YazDag(new Konum(j, i, boyut, boyut));
                        }
                        dag_doldur(i, j, boyut, dag);
                    }
                } else if (random.nextDouble() < kaya_sans) {
                    int boyut = random.nextBoolean() ? 2 : 3;
                    if (i + boyut <= yukseklik && j + boyut <= genislik && kaya_yerlesebilir_mi(i, j, boyut)) {
                        Kaya kaya;
                        if (j < genislik / 2) {
                            kaya = new KisKaya(new Konum(j, i, boyut, boyut));
                        } else {
                            kaya = new YazKaya(new Konum(j, i, boyut, boyut));
                        }
                        kaya_doldur(i, j, boyut, kaya);
                    }
                } else if (hazineYerlestirilebilirMi(i, j)) {
                    double rnd = random.nextDouble();
                    if (rnd < 0.001) {
                        yerlestirHazine(i, j, "altin");
                    } else if (rnd < 0.003) {
                        yerlestirHazine(i, j, "gumus");
                    } else if (rnd < 0.006) {
                        yerlestirHazine(i, j, "zumrut");
                    } else if (rnd < 0.010) {
                        yerlestirHazine(i, j, "bakir");
                    }
                }
            }

        }
        duvarYerlestir(random, duvar_sans);
        kus_yerlestir(random, kus_sans);
    }

    private boolean agac_yerlestirilebilir_mi(int satir_baslangic, int sutun_baslangic, int boyut) {
        for (int i = satir_baslangic; i < satir_baslangic + boyut; i++) {
            for (int j = sutun_baslangic; j < sutun_baslangic + boyut; j++) {
                if (tiles[i][j].engel_var_mi()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void agac_doldur(int satir_baslangic, int sutun_baslangic, int boyut, Agac agac) {
        for (int i = satir_baslangic; i < satir_baslangic + boyut; i++) {
            for (int j = sutun_baslangic; j < sutun_baslangic + boyut; j++) {
                tiles[i][j] = new Tile(agac, null);
            }
        }
    }

    private boolean dag_yerlestirilebilir_mi(int satir_baslangic, int sutun_baslangic, int size) {
        for (int i = satir_baslangic; i < satir_baslangic + size; i++) {
            for (int j = sutun_baslangic; j < sutun_baslangic + size; j++) {
                if (i >= yukseklik || j >= genislik || tiles[i][j].engel_var_mi()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void dag_doldur(int satir_baslangic, int sutun_baslangic, int size, Dag mountain) {
        for (int i = satir_baslangic; i < satir_baslangic + size; i++) {
            for (int j = sutun_baslangic; j < sutun_baslangic + size; j++) {
                tiles[i][j] = new Tile(mountain, null);
            }
        }
    }

    private boolean kaya_yerlesebilir_mi(int satir_baslangic, int sutun_baslangic, int size) {
        for (int i = satir_baslangic; i < satir_baslangic + size; i++) {
            for (int j = sutun_baslangic; j < sutun_baslangic + size; j++) {
                if (tiles[i][j].engel_var_mi()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void kaya_doldur(int satir_baslangic, int sutun_baslangic, int size, Kaya rock) {
        for (int i = satir_baslangic; i < satir_baslangic + size; i++) {
            for (int j = sutun_baslangic; j < sutun_baslangic + size; j++) {
                tiles[i][j] = new Tile(rock, null);
            }
        }
    }

    private void duvarYerlestir(Random rand, double wallChance) {
        for (int i = 0; i < yukseklik; i++) {
            for (int j = 0; j < genislik; j++) {
                if (rand.nextDouble() < wallChance) {
                    if (j + 10 <= genislik && duvar_yerlesebilir_mi(i, j)) {
                        Duvar wall;
                        if (rand.nextBoolean()) {
                            wall = new WinterWall(new Konum(j, i, 10, 1));
                        } else {
                            wall = new SummerWall(new Konum(j, i, 10, 1));
                        }
                        duvar_doldur(i, j, wall);
                        j += 10;
                    }
                }
            }
        }
    }

    private boolean duvar_yerlesebilir_mi(int satir_baslangic, int sutun_baslangic) {
        for (int j = sutun_baslangic; j < sutun_baslangic + 10 && j < genislik; j++) {
            if (tiles[satir_baslangic][j].engel_var_mi()) {
                return false;
            }
        }
        return true;
    }

    private void duvar_doldur(int row, int sutun_baslangic, Duvar wall) {
        for (int j = sutun_baslangic; j < sutun_baslangic + 10 && j < genislik; j++) {
            tiles[row][j] = new Tile(wall, null);
        }
    }

    private void kus_yerlestir(Random rand, double birdChance) {
        for (int i = 0; i < yukseklik; i++) {
            for (int j = 0; j < genislik; j++) {
                if (rand.nextDouble() < birdChance) {
                    Konum birdLocation = new Konum(j, i, 2, 2);
                    Kus bird = new Kus(birdLocation);
                    kus_doldur(i, j, bird);
                }
            }
        }
    }

    private void kus_doldur(int satir_baslangic, int sutun_baslangic, Kus bird) {
        for (int i = satir_baslangic; i < satir_baslangic + 2 && i < yukseklik; i++) {
            for (int j = sutun_baslangic; j < sutun_baslangic + 2 && j < genislik; j++) {
                if (!tiles[i][j].engel_var_mi()) {
                    tiles[i][j] = new Tile(bird, null);
                }
            }
        }
    }

    private void yerlestirHazine(int i, int j, String tur) {
        Hazine hazine = new Hazine(new Konum(j, i, 1, 1), tur);
        tiles[i][j] = new Tile(null, hazine);
    }

    private boolean hazineYerlestirilebilirMi(int i, int j) {
        return !tiles[i][j].engel_var_mi() && !tiles[i][j].hazine_var_mi();
    }

    public Tile[][] getTiles() {
        return tiles;
    }

}
