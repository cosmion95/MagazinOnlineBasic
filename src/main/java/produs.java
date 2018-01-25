public class produs {

    private String nume;
    private double pret;
    private int id;
    private int cantitate;

    public produs(String nume, double pret, int id) {
        this.nume = nume;
        this.pret = pret;
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    public int getId() {
        return id;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }
}

