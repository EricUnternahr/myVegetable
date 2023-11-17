package Groupe3.myvegetable;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import java.util.List;
import Groupe3.myvegetable.adapter.ProduitAdapter;
import Groupe3.myvegetable.beans.ClientBean;
import Groupe3.myvegetable.beans.InfoPanier;
import Groupe3.myvegetable.beans.ListeProduitBean;
import Groupe3.myvegetable.beans.ProduitBean;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProduitAdapter produitAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation du RecyclerView pour afficher les produits
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        produitAdapter = new ProduitAdapter();
        recyclerView.setAdapter(produitAdapter);

        // Chargement asynchrone des données de produit
        new Thread(() -> {
            try {
                List<ProduitBean> produits = RequestUtils.loadProduit();
                runOnUiThread(() -> produitAdapter.submitList(produits));
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> {
                    // Gérer ici l'échec de chargement des données
                });
            }
        }).start();

        // Configuration de l'écouteur de clic pour le bouton Panier
        Button btPanier = findViewById(R.id.btPanier);
        btPanier.setOnClickListener(v -> {
            Log.d("MainActivity", "ça clique");
            onPanierClicked();
        });
    }

    // Récupère les textes des EditText et crée un ClientBean
    private ClientBean recupererTextes() {
        EditText etNom = findViewById(R.id.etNom);
        EditText etTelephone = findViewById(R.id.etTelephone);

        String nom = etNom != null ? etNom.getText().toString().trim() : "";
        String telephone = etTelephone != null ? etTelephone.getText().toString().trim() : "";

        if (nom.isEmpty()) nom = "Nom non fourni";
        if (telephone.isEmpty()) telephone = "Téléphone non fourni";

        return new ClientBean(nom, telephone);
    }

    // Construit et envoie le JSON lorsque le bouton Enregistrer commande est cliqué
    private void onPanierClicked() {
        ClientBean client = recupererTextes();
        List<ListeProduitBean> produits = Data.listeProduit;

        InfoPanier infoPanier = new InfoPanier();
        infoPanier.setClient(client);
        infoPanier.setProduits(produits);

        Gson gson = new Gson();
        String json = gson.toJson(infoPanier);

        RequestUtils.envoyerJson(json);
        Log.d("MainActivity", "Données Panier en JSON: " + json);
    }
}
