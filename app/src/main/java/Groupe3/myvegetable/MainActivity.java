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
import Groupe3.myvegetable.beans.ListeProduitBean;
import Groupe3.myvegetable.beans.ProduitBean;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProduitAdapter produitAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        produitAdapter = new ProduitAdapter();
        recyclerView.setAdapter(produitAdapter);

        new Thread(() -> {
            try {
                List<ProduitBean> produits = RequestUtils.loadProduit();


                //Retourne sur le thread graphique
                runOnUiThread(() -> {
                    produitAdapter.submitList(produits);

                });
            }
            catch (Exception e) {
                e.printStackTrace();
                //Retourne sur le thread graphique
                runOnUiThread(() -> {
                    //binding.textView.setText("Une erreur est survenue : " + e.getMessage());
                    // binding.progressBar.setVisibility(View.GONE);
                });
            }
        }).start();
        Button btPanier = findViewById(R.id.btPanier); // Assurez-vous que l'ID correspond à celui de votre layout
        btPanier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "ça clique");
                onPanierClicked();
                System.out.println();
            }
        });

    }

    private ClientBean recupererTextes() {
        EditText etNom = findViewById(R.id.etNom);
        EditText etTelephone = findViewById(R.id.etTelephone);

        String nom = etNom != null ? etNom.getText().toString().trim() : "";
        String telephone = etTelephone != null ? etTelephone.getText().toString().trim() : "";

        if (nom.isEmpty()) {
            nom = "Nom non fourni";
        }

        if (telephone.isEmpty()) {
            telephone = "Téléphone non fourni";
        }
        System.out.println("Nom: " + nom + ", Téléphone: " + telephone);
        return new ClientBean(nom, telephone);
    }

    private void onPanierClicked() {
        ClientBean client = recupererTextes();
        List<ListeProduitBean> produits = Data.listeProduit;

        InfoPanier infoPanier = new InfoPanier();
        infoPanier.setClient(client);
        infoPanier.setProduits(produits);

        Gson gson = new Gson();
        String json = gson.toJson(infoPanier);

        Log.d("MainActivity", "Données Panier en JSON: " + json);
    }


}




