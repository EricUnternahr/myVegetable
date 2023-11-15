package Groupe3.myvegetable;

import static Groupe3.myvegetable.RequestUtils.loadProduit;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import Groupe3.myvegetable.adapter.ProduitAdapter;
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

        List<ProduitBean> produits = null;
        try {
            produits = loadProduit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        produitAdapter.submitList(loadProduit());
    }

    private List<ProduitBean> loadProduit() {
        List<ProduitBean> produits = new ArrayList<>();


        return produits;
    }
}
