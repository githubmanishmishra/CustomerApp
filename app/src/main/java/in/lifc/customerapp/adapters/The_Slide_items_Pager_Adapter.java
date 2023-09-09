package in.lifc.customerapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.List;

import in.lifc.customerapp.R;
import in.lifc.customerapp.model.The_Slide_Items_Model_Class;

public class The_Slide_items_Pager_Adapter extends PagerAdapter {

    private final Context mContext;
    private final List<The_Slide_Items_Model_Class.Datum> theSlideItemsModelClassList;

    public The_Slide_items_Pager_Adapter(Context Mcontext, List<The_Slide_Items_Model_Class.Datum> theSlideItemsModelClassList) {
        this.mContext = Mcontext;
        this.theSlideItemsModelClassList = theSlideItemsModelClassList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View sliderLayout = inflater.inflate(R.layout.items_layout, null);

        ImageView featured_image = sliderLayout.findViewById(R.id.my_featured_image);
        TextView caption_title = sliderLayout.findViewById(R.id.my_caption_title);

        Glide.with(mContext)
                .load(theSlideItemsModelClassList.get(position).getOfferUrl())


                .placeholder(R.drawable.logo)
                .into(featured_image);

        caption_title.setText(theSlideItemsModelClassList.get(position).getOfferName());
        container.addView(sliderLayout);
        return sliderLayout;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return theSlideItemsModelClassList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }
}