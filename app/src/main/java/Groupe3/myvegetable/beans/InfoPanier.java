package Groupe3.myvegetable.beans;

import java.util.List;

import Groupe3.myvegetable.beans.ClientBean;
import Groupe3.myvegetable.beans.ListeProduitBean;

public class InfoPanier {
    private ClientBean client;
    private List<ListeProduitBean> produits;

    public InfoPanier() {
    }

    public InfoPanier(ClientBean client, List<ListeProduitBean> produits) {
        this.client = client;
        this.produits = produits;
    }

    public ClientBean getClient() {
        return client;
    }

    public void setClient(ClientBean client) {
        this.client = client;
    }

    public List<ListeProduitBean> getProduits() {
        return produits;
    }

    public void setProduits(List<ListeProduitBean> produits) {
        this.produits = produits;
    }
}
