package com.example.amitrai.demofacebooklikeanimation;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private TextView txt_like = null;
    private PopupWindow popWindow;
    private int mDeviceHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    /**
     * initialize view elements.
     */
    private void init(){
        txt_like = (TextView) findViewById(R.id.txt_like);

        txt_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLikeWindow();
            }
        });
    }


    /**
     * shows like window
     */
    private void showLikeWindow(){
        onShowPopup(txt_like);
    }


    // call this method when required to show popup
    public void onShowPopup(View v){

        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // inflate the custom popup layout
        final View inflatedView = layoutInflater.inflate(R.layout.popupview, null,false);

        inflatedView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.popupanimation));

        ImageView img_haha = (ImageView) inflatedView.findViewById(R.id.img_haha);
        Glide.with(this)
                .load(R.drawable.haha)
                .asGif()
                .placeholder(R.drawable.haha)
                .into(img_haha);

        SlideToAbove(inflatedView);


                inflatedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow.dismiss();
            }
        });
        // find the ListView in the popup layout

        // get device size
        Display display = getWindowManager().getDefaultDisplay();
        final Point size = new Point();
        display.getSize(size);
        mDeviceHeight = size.y;


        // set height depends on the device size
        popWindow = new PopupWindow(inflatedView, 300, 200, true );
        // set a background drawable with rounders corners
//        popWindow.setBackgroundDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        // make it focusable to show the keyboard to enter in `EditText`
        popWindow.setFocusable(true);
        // make it outside touchable to dismiss the popup window
        popWindow.setOutsideTouchable(true);

        // show the popup at bottom of the screen and set some margin at bottom ie,
        if (popWindow.isShowing())
            popWindow.dismiss();
        else
            popWindow.showAtLocation(v, Gravity.NO_GRAVITY, 200,300);

    }



    /**
     * animation to slide the view above.
     */
    public void SlideToAbove(final View view) {
        Animation slide = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 100f);

        slide.setDuration(600);
        slide.setFillAfter(true);
        slide.setFillEnabled(true);
        view.startAnimation(slide);

        slide.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

        });

    }
}
