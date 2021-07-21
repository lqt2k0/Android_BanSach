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

public class StoryAdapter extends BaseAdapter {
    Context context;

    public StoryAdapter(Context context, ArrayList<Sach> arraySachStory) {
        this.context = context;
        this.arraySachStory = arraySachStory;
    }

    ArrayList<Sach> arraySachStory;

    @Override
    public int getCount() {
        return arraySachStory.size();
    }

    @Override
    public Object getItem(int position) {
        return arraySachStory.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder
    {
        public TextView txtTenStory, txtGiaStory, txtTenTGStory;
        public ImageView imgHinhSachStory;
        public RatingBar rateStory;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_story, null);
            viewHolder.txtTenStory = convertView.findViewById(R.id.tvTenSachStory);
            viewHolder.txtTenTGStory = convertView.findViewById(R.id.tvTenTacGiaStory);
            viewHolder.txtGiaStory = convertView.findViewById(R.id.tvGiaBanStory);
            viewHolder.imgHinhSachStory = convertView.findViewById(R.id.imgSachStory);
            viewHolder.rateStory = convertView.findViewById(R.id.ratingBarStory);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Sach sach = (Sach) getItem(position);
        viewHolder.txtTenStory.setText(sach.getTenSach());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtGiaStory.setText("Price : " + decimalFormat.format(sach.getGiaBan()) + " VNĐ");
        //viewHolder.txtTenTG.setText(sach.getMaTacGia());
        Glide.with(context).load(sach.getHinhAnh()).placeholder(R.drawable.no_image).error(R.drawable.error).into(viewHolder.imgHinhSachStory);
        viewHolder.rateStory.setRating((float) 4.5);
        return convertView;
    }

}
