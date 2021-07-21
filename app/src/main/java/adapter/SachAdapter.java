package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.baitapandroid.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import activity.BookDetailsActivity;
import model.Sach;
import ulti.CheckConnection;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.ItemHolder> implements Filterable {
    Context context;

    ArrayList<Sach> arraySach;
    ArrayList<Sach> listSort;

    public Filter getFilter() {
        return null;
    }

    Filter filter;
    public SachAdapter(Context context, ArrayList<Sach> arraySach) {
        this.context = context;
        this.arraySach = arraySach;
        this.listSort  = arraySach;
    }


    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_sach_new, parent, false);
        ItemHolder itemHolder = new ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Sach sach = arraySach.get(position);
        holder.txtTenSach.setText(sach.getTenSach());
        //holder.txtTenTG.setText(sach.getMaTacGia());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtGiaSach.setText("Price : " + decimalFormat.format(sach.getGiaBan()) + " VNĐ");
        Glide.with(context).load(sach.getHinhAnh()).placeholder(R.drawable.no_image).error(R.drawable.error).into(holder.imgHinhSach);
        holder.rate.setRating((float) 4.5);
        //Picasso.with(context).load(sach.getHinhAnh()).placeholder(R.drawable.no_image).error(R.drawable.error).into(holder.imgHinhSach);
    }

    @Override
    public int getItemCount() {
        return arraySach.size();
    }



    public class ItemHolder extends  RecyclerView.ViewHolder
    {
        public ImageView imgHinhSach, imgContainer, imgAnhDetail;
        public TextView txtTenSach, txtGiaSach, txtTenTG, textTenTG, textGia,title, rateText;
        public RatingBar rate, ratingBar;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhSach = (ImageView) itemView.findViewById(R.id.imgSach);
            txtGiaSach = (TextView) itemView.findViewById(R.id.tvGiaBan);
            txtTenSach = (TextView) itemView.findViewById(R.id.tvTenSach);
            txtTenTG = (TextView) itemView.findViewById(R.id.tvTenTacGia);
            rate = (RatingBar) itemView.findViewById(R.id.ratingBar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, BookDetailsActivity.class);
                    intent.putExtra("thongtinsach", arraySach.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    CheckConnection.ShowToast_Short(context, arraySach.get(getPosition()).getTenSach());
                    context.startActivity(intent);
                }
            });
//
//            title = itemView.findViewById(R.id.tvMoTaDetail);
//            imgContainer = (ImageView) itemView.findViewById(R.id.container);
//            rateText = itemView.findViewById(R.id.tvScoreDetail);
//            imgAnhDetail = (ImageView) itemView.findViewById(R.id.imgSachDetail);
//            textTenTG = (TextView) itemView.findViewById(R.id.tvTenTacGiaDetail);
//            textGia = (TextView) itemView.findViewById(R.id.tvGiaBanDetail);
//            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBarDetail);
//
        }
    }

    //Tìm kiếm sản phẩm
    public void resetData() {
        arraySach = listSort;
    }

    public Filter getFilterx() {
        if (filter == null)
            filter = new CustomFilter();
        return filter;
    }

    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Sach> filList = new ArrayList<>();
            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0) {
                filList.addAll(listSort);
                results.count = listSort.size();
            } else {
                ArrayList<Sach> findItem = new ArrayList<>();
                //Set up tìm kiếm theo sản phẩm
                for (Sach p : arraySach) {
                    if (p.getTenSach().toUpperCase().contains(constraint.toString().toUpperCase()))
                        findItem.add(p);
                }
                results.values = findItem;
                results.count = findItem.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count == 0){
//                notifyDataSetInvalidated();
            }
            else {
                arraySach = (ArrayList<Sach>) results.values;
                notifyDataSetChanged();
            }
        }
    }
}
