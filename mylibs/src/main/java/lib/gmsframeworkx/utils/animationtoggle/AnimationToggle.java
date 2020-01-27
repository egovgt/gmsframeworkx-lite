package lib.gmsframeworkx.utils.animationtoggle;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import java.util.Timer;
import java.util.TimerTask;

import lib.gmsframeworkx.R;


public class AnimationToggle extends RelativeLayout {
    private View current;
    private Animation inAnimation;
    private Animation outAnimation;
    private boolean isShow = false;

    public AnimationToggle(Context context) {
        super(context);
        this.outAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.animation_toggle_out);
        this.inAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.animation_toggle_in);
        this.outAnimation.setInterpolator(new FastOutSlowInInterpolator());
        this.inAnimation.setInterpolator(new FastOutSlowInInterpolator());
    }

    public AnimationToggle(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.outAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.animation_toggle_out);
        this.inAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.animation_toggle_in);
        this.outAnimation.setInterpolator(new FastOutSlowInInterpolator());
        this.inAnimation.setInterpolator(new FastOutSlowInInterpolator());
    }

    public AnimationToggle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.outAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.animation_toggle_out);
        this.inAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.animation_toggle_in);
        this.outAnimation.setInterpolator(new FastOutSlowInInterpolator());
        this.inAnimation.setInterpolator(new FastOutSlowInInterpolator());
    }

    public void setInOutAnimation(int inAnimation, int outAnimation){
        this.outAnimation = AnimationUtils.loadAnimation(getContext(), outAnimation);
        this.inAnimation = AnimationUtils.loadAnimation(getContext(), inAnimation);
        this.outAnimation.setInterpolator(new FastOutSlowInInterpolator());
        this.inAnimation.setInterpolator(new FastOutSlowInInterpolator());
    }

    public void addView(View view, int i, LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        if (getChildCount() == 1) {
            this.current = view;
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.GONE);
        }
        view.setClickable(false);
    }

    public void display(View view) {
        if (view != this.current) {
            if (this.current != null) {
                AnimUtil.animateOut(this.current, this.outAnimation);
            }
            if (view != null) {
                AnimUtil.animateIn(view, this.inAnimation);
            }
            this.current = view;
        }
    }

    public void displaying(View view) {
        AnimUtil.animateIn(view, this.inAnimation);
    }

    public void hide(View view) {
        isShow = false;
        AnimUtil.animateOut(view, this.outAnimation);
    }

    public void hide() {
        isShow = false;
        AnimUtil.animateOut(this, this.outAnimation);
    }

    public boolean isDisplaying(View view){
        return view.getVisibility() == VISIBLE ? true : false;
    }

    public void show() {
        isShow = true;
        AnimUtil.animateIn(this, this.inAnimation);
    }

    public boolean isShow(){
        return isShow;
    }

    public void showForAWhile(final Activity activity) {
        show();
        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                hide();
                            }
                        });
                    }
                },
                3000
        );
    }
    public void hideForAWhile(final Activity activity) {
        hide();
        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                show();
                            }
                        });
                    }
                },
                1000
        );
    }

    public void displayQuick(View view) {
        if (view != this.current) {
            if (this.current != null) {
                this.current.setVisibility(View.GONE);
            }
            if (view != null) {
                view.setVisibility(View.GONE);
            }
            this.current = view;
        }
    }
}
