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

import Groupe3.myvegetable.beans.ProduitBean;
import Groupe3.myvegetable.databinding.RowProduitBinding;

import java.util.ArrayList;
import java.util.List;

public class ProduitAdapter extends ListAdapter<ProduitBean, ProduitAdapter.ViewHolder> {

    public ProduitAdapter() {
        super(new DiffUtil.ItemCallback<ProduitBean>() {
            @Override
            public boolean areItemsTheSame(@NonNull ProduitBean oldItem, @NonNull ProduitBean newItem) {
                return oldItem.getIdProduit() == newItem.getIdProduit();
            }

            @SuppressLint("DiffUtilEquals")
            @Override
            public boolean areContentsTheSame(@NonNull ProduitBean oldItem, @NonNull ProduitBean newItem) {
                // Comparez ici d'autres propriétés si nécessaire
                return oldItem.equals(newItem);
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowProduitBinding binding = RowProduitBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProduitBean item = getItem(position);
        holder.bind(item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final RowProduitBinding binding;

        ViewHolder(RowProduitBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            // Initialisation du Spinner
            initializeSpinner();
        }

        void bind(ProduitBean item) {
            binding.tvnomProduit.setText(item.getNomProduit());
            binding.tvdesignation.setText(item.getDescriptionProduit());
            binding.tvprix.setText(String.format("%.2f €", item.getPrixProduit()));
            Picasso.get()
                    .load(item.getImageProduit())
                    .into(binding.ivproduit);

        }

        private void initializeSpinner() {
            Spinner spinner = binding.spinquantite;
            List<String> numbers = new ArrayList<>();
            for (int i = 1; i <= 100; i++) {
                numbers.add(String.valueOf(i));
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(spinner.getContext(), android.R.layout.simple_spinner_item, numbers);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);


            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String selectedItem = parent.getItemAtPosition(position).toString();

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }
}
