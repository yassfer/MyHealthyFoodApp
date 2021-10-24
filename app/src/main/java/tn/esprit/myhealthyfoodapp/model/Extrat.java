package tn.esprit.myhealthyfoodapp.model;

public class Extrat {
    private int idExtrat;
    private String nameExtrat;
    private String imgExtrat;

    public Extrat(int idExtrat, String nameExtrat, String imgExtrat) {
        this.idExtrat = idExtrat;
        this.nameExtrat = nameExtrat;
        this.imgExtrat = imgExtrat;
    }

    public int getIdExtrat() {
        return idExtrat;
    }

    public void setIdExtrat(int idExtrat) {
        this.idExtrat = idExtrat;
    }

    public String getNameExtrat() {
        return nameExtrat;
    }

    public void setNameExtrat(String name) {
        this.nameExtrat = name;
    }

    public String getImgExtrat() {
        return imgExtrat;
    }

    public void setImgExtrat(String imgExtrat) {
        this.imgExtrat = imgExtrat;
    }
}
