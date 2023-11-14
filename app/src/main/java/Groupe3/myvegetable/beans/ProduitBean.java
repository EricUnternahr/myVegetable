package Groupe3.myvegetable.beans;

public class ProduitBean {
    private int idProduit;
    private String nomProduit;
    private String descriptionProduit;
    private String uniteProduit;
    private float prixProduit;
    private String imageProduit;

    public ProduitBean(int id_produit, String nom_produit, String description_produit, String unite_Produit, float prix_produit, String image_produit) {
        this.idProduit = id_produit;
        this.nomProduit = nom_produit;
        this.descriptionProduit = description_produit;
        this.uniteProduit = unite_Produit;
        this.prixProduit = prix_produit;
        this.imageProduit = image_produit;
    }

    public ProduitBean() {
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getDescriptionProduit() {
        return descriptionProduit;
    }

    public void setDescriptionProduit(String descriptionProduit) {
        this.descriptionProduit = descriptionProduit;
    }

    public String getUniteProduit() {
        return uniteProduit;
    }

    public void setUniteProduit(String uniteProduit) {
        this.uniteProduit = uniteProduit;
    }

    public float getPrixProduit() {
        return prixProduit;
    }

    public void setPrixProduit(float prixProduit) {
        this.prixProduit = prixProduit;
    }

    public String getImageProduit() {
        return imageProduit;
    }

    public void setImageProduit(String imageProduit) {
        this.imageProduit = imageProduit;
    }
}
