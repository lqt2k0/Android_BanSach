package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.baitapandroid.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

import activity.CartActivity;
import activity.MainActivity;
import model.GioHang;

public class CartAdapter extends BaseAdapter {
    public CartAdapter(Context context, ArrayList<GioHang> arrayGioHang) {
        this.context = context;
        this.arrayGioHang = arrayGioHang;
    }

    Context context;
    ArrayList<GioHang> arrayGioHang;

    @Override
    public int getCount() {
        return arrayGioHang.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayGioHang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder
    {
        public TextView txtTenGioHang, txtGiaGioHang;
        ImageView imgGioHang;
        Button btnminus, btnvalues, btnplus;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_giohang, null);
            viewHolder.txtTenGioHang = (TextView) convertView.findViewById(R.id.tvTenGioHang);
            viewHolder.txtGiaGioHang = (TextView) convertView.findViewById(R.id.tvGiaGioHang);
            viewHolder.imgGioHang = (ImageView) convertView.findViewById(R.id.imgSachGioHang);
            viewHolder.btnminus = (Button) convertView.findViewById(R.id.btnMinus);
            viewHolder.btnvalues = (Button) convertView.findViewById(R.id.btnValues);
            viewHolder.btnplus = (Button) convertView.findViewById(R.id.btnPlus);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        GioHang gioHang = (GioHang) getItem(position);
        viewHolder.txtTenGioHang.setText(gioHang.getTenSach());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtGiaGioHang.setText("Price : " + decimalFormat.format(gioHang.getGiaSach()) + " VNĐ");
        Glide.with(context).load(gioHang.getHinhSach()).placeholder(R.drawable.no_image).error(R.drawable.error).into(viewHolder.imgGioHang);
        viewHolder.btnvalues.setText(gioHang.getSoLuong() + "");
        int sl = Integer.parseInt(viewHolder.btnvalues.getText().toString());
        if(sl >= 10)
        {
            viewHolder.btnplus.setVisibility(View.INVISIBLE);
            viewHolder.btnminus.setVisibility(View.VISIBLE);
        }
        else if (sl <= 1)
        {
            viewHolder.btnminus.setVisibility(View.INVISIBLE);
        }
        else if(sl >= 1)
        {
            viewHolder.btnplus.setVisibility(View.VISIBLE);
            viewHolder.btnminus.setVisibility(View.VISIBLE);
        }
        ViewHolder holder = viewHolder;
        viewHolder.btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmnt = Integer.parseInt(holder.btnvalues.getText().toString()) + 1;
                int slht = MainActivity.mangGioHang.get(position).getSoLuong();
                long giaht = MainActivity.mangGioHang.get(position).getGiaSach();
                MainActivity.mangGioHang.get(position).setSoLuong(slmnt);
                long giamn = (giaht * slmnt) / slht;
                MainActivity.mangGioHang.get(position).setGiaSach(giamn);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                holder.txtGiaGioHang.setText("Price : " + decimalFormat.format(giamn) + " VNĐ");
                CartActivity.EventUltil();
                if(slmnt > 9)
                {
                    holder.btnplus.setVisibility(View.INVISIBLE);
                    holder.btnminus.setVisibility(View.VISIBLE);
                    holder.btnvalues.setText(String.valueOf(slmnt));
                }
                else
                {
                    holder.btnplus.setVisibility(View.VISIBLE);
                    holder.btnminus.setVisibility(View.VISIBLE);
                    holder.btnvalues.setText(String.valueOf(slmnt));
                }
            }
        });



        viewHolder.btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmnt = Integer.parseInt(holder.btnvalues.getText().toString()) - 1;
                int slht = MainActivity.mangGioHang.get(position).getSoLuong();
                long giaht = MainActivity.mangGioHang.get(position).getGiaSach();
                MainActivity.mangGioHang.get(position).setSoLuong(slmnt);
                long giamn = (giaht * slmnt) / slht;
                MainActivity.mangGioHang.get(position).setGiaSach(giamn);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                holder.txtGiaGioHang.setText("Price : " + decimalFormat.format(giamn) + " VNĐ");
                CartActivity.EventUltil();
                if(slmnt < 2)
                {
                    holder.btnplus.setVisibility(View.VISIBLE);
                    holder.btnminus.setVisibility(View.INVISIBLE);
                    holder.btnvalues.setText(String.valueOf(slmnt));
                }
                else
                {
                    holder.btnplus.setVisibility(View.VISIBLE);
                    holder.btnminus.setVisibility(View.VISIBLE);
                    holder.btnvalues.setText(String.valueOf(slmnt));
                }
            }
        });
        return convertView;
    }
}
