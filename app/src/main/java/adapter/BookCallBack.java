package adapter;

import android.widget.ImageView;
import android.widget.TextView;

public interface BookCallBack {
    void onBookItemClick(int position, ImageView imgBook, TextView title, TextView price);
}
