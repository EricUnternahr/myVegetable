package Groupe3.myvegetable.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

import Groupe3.myvegetable.beans.ProduitBean;
import Groupe3.myvegetable.databinding.RowProduitBinding;

public class ProduitAdapter {
static class ViewHolder extends RecyclerView.ViewHolder{
    RowProduitBinding binding;

    public ViewHolder(RowProduitBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
    static class Comparator extends DiffUtil.ItemCallback<ProduitBean> {

        @Override
        public boolean areItemsTheSame(@NonNull ProduitBean oldItem, @NonNull ProduitBean newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull ProduitBean oldItem, @NonNull ProduitBean newItem) {
            return Objects.equals(oldItem.getNomProduit(), newItem.getNomProduit());

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowProduitBinding binding =  RowProduitBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, String ) {
        ProduitBean item = getItem(position);
        holder.binding.tvtemp.setText(item.getSpeed() + "");
    }

    public WeatherAdapter() {
        super(new Comparator());
    }
}
