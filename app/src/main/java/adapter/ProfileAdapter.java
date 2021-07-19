package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.baitapandroid.R;

import java.util.ArrayList;

import model.Khach;

public class ProfileAdapter extends BaseAdapter {
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<Khach> getKhachArrayList() {
        return khachArrayList;
    }

    public void setKhachArrayList(ArrayList<Khach> khachArrayList) {
        this.khachArrayList = khachArrayList;
    }

    Context context;

    public ProfileAdapter(Context context, ArrayList<Khach> khachArrayList) {
        this.context = context;
        this.khachArrayList = khachArrayList;
    }

    ArrayList<Khach> khachArrayList;
    @Override
    public int getCount() {
        return khachArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return khachArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder
    {
        public EditText txtEmailKH, txtTenKH, txtDTKH, txtGioiTinhKH, txtDiaChiKH;
        public ImageView imgHinhAnhKH;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_profile, null);
            viewHolder.txtEmailKH = convertView.findViewById(R.id.edtEmailProfile);
            viewHolder.txtTenKH = convertView.findViewById(R.id.edtUserProfile);
            viewHolder.txtDTKH = convertView.findViewById(R.id.edtPhoneProfile);
            viewHolder.txtGioiTinhKH = convertView.findViewById(R.id.edtGenderProfile);
            viewHolder.txtDiaChiKH = convertView.findViewById(R.id.edtAddressProfile);
            viewHolder.imgHinhAnhKH = convertView.findViewById(R.id.imgUserProfile);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
       Khach khach = (Khach) getItem(position);
        viewHolder.txtEmailKH.setText(khach.getEmailKhach());
        viewHolder.txtTenKH.setText(khach.getTenKhach());
        viewHolder.txtDTKH.setText(khach.getSdtKhach());
        viewHolder.txtGioiTinhKH.setText(khach.getGioitinhKhach());
        viewHolder.txtDiaChiKH.setText(khach.getDiaChiKhach());

        Glide.with(context).load(khach.getHinhKhach()).placeholder(R.drawable.no_image).error(R.drawable.error).into(viewHolder.imgHinhAnhKH);
        return convertView;
    }
}
