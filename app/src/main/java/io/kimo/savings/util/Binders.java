package io.kimo.savings.util;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

public class Binders {
    @BindingAdapter("android:visibility")
    public static void setVisibility(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Injection.picasso(view.getContext())
                .load(imageUrl)
                .placeholder(android.R.drawable.screen_background_dark_transparent)
                .error(android.R.drawable.screen_background_dark_transparent)
                .into(view);
    }
}
