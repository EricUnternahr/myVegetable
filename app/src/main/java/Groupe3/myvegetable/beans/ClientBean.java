package Groupe3.myvegetable.beans;

public class ClientBean {
    private String telephone;
    private String nom;

    public ClientBean() {
    }

    public ClientBean(String telephone, String nom) {
        this.telephone = telephone;
        this.nom = nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
