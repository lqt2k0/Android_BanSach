package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.baitapandroid.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

import activity.BookDetailsActivity;
import model.Sach;
import ulti.CheckConnection;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.ItemHolder> {
    Context context;


    public SachAdapter(Context context, ArrayList<Sach> arraySach) {
        this.context = context;
        this.arraySach = arraySach;
    }

    ArrayList<Sach> arraySach;
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
}
