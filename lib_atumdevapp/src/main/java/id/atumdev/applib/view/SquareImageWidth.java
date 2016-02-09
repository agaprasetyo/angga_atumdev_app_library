package id.atumdev.applib.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Angga.Prasetiyo on 25/09/2015.
 */
public class SquareImageWidth extends ImageView {
    private static final String TAG = SquareImageWidth.class.getSimpleName();

    public SquareImageWidth(Context context) {
        super(context);
    }

    public SquareImageWidth(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImageWidth(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }
}
