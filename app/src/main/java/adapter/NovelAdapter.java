package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.baitapandroid.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

import model.Sach;

public class NovelAdapter extends BaseAdapter {
    Context context;

    public NovelAdapter(Context context, ArrayList<Sach> arraySachNovel) {
        this.context = context;
        this.arraySachNovel = arraySachNovel;
    }

    ArrayList<Sach> arraySachNovel;

    @Override
    public int getCount() {
        return arraySachNovel.size();
    }

    @Override
    public Object getItem(int position) {
        return arraySachNovel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder
    {
        public TextView txtTenNovel, txtGiaNovel, txtTenTG;
        public ImageView imgHinhSachNovel;
        public RatingBar rate;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_novel, null);
            viewHolder.txtTenNovel = convertView.findViewById(R.id.tvTenSachNovel);
            viewHolder.txtTenTG = convertView.findViewById(R.id.tvTenTacGiaNovel);
            viewHolder.txtGiaNovel = convertView.findViewById(R.id.tvGiaBanNovel);
            viewHolder.imgHinhSachNovel = convertView.findViewById(R.id.imgSachNovel);
            viewHolder.rate = convertView.findViewById(R.id.ratingBarNovel);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Sach sach = (Sach) getItem(position);
        viewHolder.txtTenNovel.setText(sach.getTenSach());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtGiaNovel.setText("Price : " + decimalFormat.format(sach.getGiaBan()) + " VNĐ");
        //viewHolder.txtTenTG.setText(sach.getMaTacGia());
        Glide.with(context).load(sach.getHinhAnh()).placeholder(R.drawable.no_image).error(R.drawable.error).into(viewHolder.imgHinhSachNovel);
        viewHolder.rate.setRating((float) 4.5);
        return convertView;
    }

}
