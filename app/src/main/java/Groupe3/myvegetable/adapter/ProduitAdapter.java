package Groupe3.myvegetable.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import Groupe3.myvegetable.Data;
import Groupe3.myvegetable.beans.ListeProduitBean;
import Groupe3.myvegetable.beans.ProduitBean;
import Groupe3.myvegetable.databinding.RowProduitBinding;

import java.util.ArrayList;
import java.util.List;

// Adapter pour afficher une liste de produits dans un RecyclerView
public class ProduitAdapter extends ListAdapter<ProduitBean, ProduitAdapter.ViewHolder> {

    // Constructeur initialisant le DiffUtil pour la performance de la liste
    public ProduitAdapter() {
        super(new DiffUtil.ItemCallback<ProduitBean>() {
            @Override
            public boolean areItemsTheSame(@NonNull ProduitBean oldItem, @NonNull ProduitBean newItem) {
                return oldItem.getIdProduit() == newItem.getIdProduit();
            }

            @SuppressLint("DiffUtilEquals")
            @Override
            public boolean areContentsTheSame(@NonNull ProduitBean oldItem, @NonNull ProduitBean newItem) {
                return oldItem.equals(newItem);
            }
        });
    }

    // Crée et retourne un ViewHolder pour un élément de la liste
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowProduitBinding binding = RowProduitBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    // Associe les données du produit à un ViewHolder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProduitBean item = getItem(position);
        holder.bind(item);
        holder.binding.btajouter.setOnClickListener(v -> holder.addToCart());
    }

    // ViewHolder interne pour gérer l'affichage et l'interaction avec un élément de la liste
    static class ViewHolder extends RecyclerView.ViewHolder {
        private final RowProduitBinding binding;
        private int currentProductId;

        // Constructeur du ViewHolder
        ViewHolder(RowProduitBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            initializeSpinner();
            binding.btajouter.setOnClickListener(v -> addToCart());
        }

        // Lie un produit à ce ViewHolder
        void bind(ProduitBean item) {
            currentProductId = item.getIdProduit(); // Stocke l'ID du produit actuel
            binding.tvnomProduit.setText(item.getNomProduit());
            binding.tvdesignation.setText(item.getDescriptionProduit());
            binding.tvprix.setText(String.format("%.2f €", item.getPrixProduit()));
            Picasso.get()
                    .load(item.getImageProduit())
                    .into(binding.ivproduit);
        }

        // Ajoute le produit au panier avec la quantité sélectionnée
        private void addToCart() {
            int quantity = Integer.parseInt(binding.spinquantite.getSelectedItem().toString());

            ListeProduitBean existingProduct = findProductInList(currentProductId);
            if (existingProduct != null) {
                existingProduct.setQuantite_produit(existingProduct.getQuantite_produit());
            } else {
                ListeProduitBean newProduct = new ListeProduitBean(currentProductId, quantity);
                Data.listeProduit.add(newProduct);
            }
        }

        // Trouve un produit dans la liste par son ID
        private ListeProduitBean findProductInList(int productId) {
            for (ListeProduitBean product : Data.listeProduit) {
                if (product.getIdProduit() == productId) {
                    return product;
                }
            }
            return null;
        }

        // Initialise le Spinner pour la sélection de la quantité
        private void initializeSpinner() {
            Spinner spinner = binding.spinquantite;
            List<String> numbers = new ArrayList<>();
            for (int i = 0; i <= 100; i++) {
                numbers.add(String.valueOf(i));
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(spinner.getContext(), android.R.layout.simple_spinner_item, numbers);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // Gère la sélection d'élément dans le Spinner
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // Gère le cas où aucun élément n'est sélectionné
                }
            });
        }
    }
}
