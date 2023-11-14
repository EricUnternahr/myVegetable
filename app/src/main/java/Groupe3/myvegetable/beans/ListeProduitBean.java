package Groupe3.myvegetable.beans;

public class ListeProduitBean {
    private int quantite_produit;

    public ListeProduitBean(int quantite_produit) {
        this.quantite_produit = quantite_produit;
    }

    public int getQuantite_produit() {
        return quantite_produit;
    }

    public ListeProduitBean() {
    }

    public void setQuantite_produit(int quantite_produit) {
        this.quantite_produit = quantite_produit;
    }
}
