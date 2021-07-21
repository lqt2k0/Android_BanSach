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

public class LifeSkillAdapter extends BaseAdapter {
    Context context;

    public LifeSkillAdapter(Context context, ArrayList<Sach> arraySachLifeSkills) {
        this.context = context;
        this.arraySachLifeSkills = arraySachLifeSkills;
    }

    ArrayList<Sach> arraySachLifeSkills;

    @Override
    public int getCount() {
        return arraySachLifeSkills.size();
    }

    @Override
    public Object getItem(int position) {
        return arraySachLifeSkills.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder
    {
        public TextView txtTenLifeSkills, txtGiaLifeSkills, txtTenTGLifeSkills;
        public ImageView imgHinhSachLifeSkills;
        public RatingBar rateLifeSkills;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_lifeskills, null);
            viewHolder.txtTenLifeSkills = convertView.findViewById(R.id.tvTenSachLifeSkills);
            viewHolder.txtTenTGLifeSkills = convertView.findViewById(R.id.tvTenTacGiaLifeSkills);
            viewHolder.txtGiaLifeSkills = convertView.findViewById(R.id.tvGiaBanLifeSkills);
            viewHolder.imgHinhSachLifeSkills = convertView.findViewById(R.id.imgSachLifeSkills);
            viewHolder.rateLifeSkills = convertView.findViewById(R.id.ratingBarLifeSkills);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Sach sach = (Sach) getItem(position);
        viewHolder.txtTenLifeSkills.setText(sach.getTenSach());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtGiaLifeSkills.setText("Price : " + decimalFormat.format(sach.getGiaBan()) + " VNĐ");
        //viewHolder.txtTenTG.setText(sach.getMaTacGia());
        Glide.with(context).load(sach.getHinhAnh()).placeholder(R.drawable.no_image).error(R.drawable.error).into(viewHolder.imgHinhSachLifeSkills);
        viewHolder.rateLifeSkills.setRating((float) 4.5);
        return convertView;
    }

}
