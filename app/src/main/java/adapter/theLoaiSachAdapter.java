package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.baitapandroid.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import model.theLoaiSach;

public class theLoaiSachAdapter extends BaseAdapter
{
    public theLoaiSachAdapter(ArrayList<theLoaiSach> arraytheLoaiSach, Context context) {
        this.arraytheLoaiSach = arraytheLoaiSach;
        this.context = context;
    }

    ArrayList<theLoaiSach> arraytheLoaiSach;
    Context context;
    @Override
    public int getCount() {
        return arraytheLoaiSach.size();
    }

    @Override
    public Object getItem(int position) {
        return arraytheLoaiSach.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder
    {
        TextView txtTenTheLoaiSach;
        ImageView imgTheLoaiSach;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_listview_theloaisach, null);
            viewHolder.txtTenTheLoaiSach = (TextView) convertView.findViewById(R.id.tvTheLoaiSach);
            viewHolder.imgTheLoaiSach = convertView.findViewById(R.id.imageViewTheLoaiSach);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        theLoaiSach theloaisach = (theLoaiSach) getItem(position);
        viewHolder.txtTenTheLoaiSach.setText(theloaisach.getTenTheLoai());
        Picasso.with(context).load(theloaisach.getHinhAnhTheLoai()).placeholder(R.drawable.no_image).error(R.drawable.error).into(viewHolder.imgTheLoaiSach);
        return convertView;
    }
}
