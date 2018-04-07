import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ColorfulToast {
    public static final int SHORT = Toast.LENGTH_SHORT;
    public static final int LONG = Toast.LENGTH_LONG;
    private static final int TOAST_GREEN = Color.parseColor("#6FC57F");
    private static final int TOAST_RED = Color.parseColor("#FF5E65");
    private static final int TOAST_YELLOW = Color.parseColor("#F7D600");
    private static final int DEF_OFFSET_Y = 51;

    public enum ColorfulToastGravity {
        TOP_CENTER,CENTER,BOTTOM_CENTER,NULL
    }

    private Context context;
    private String msg;
    private int duration;
    private Toast toast = null;
    private float textSize = -1;
    private int textColor = -1;
    private View rootView;
    private TextView tv;
    private int backgroundColor = Color.parseColor("#E6EEEEEE");
    private float backgroundRadius = 22;
    private int strokeWidth = -1;
    private int strokeColor ;
    private Drawable leftDrawable = null;
    private Drawable rightDrawable = null;
    private boolean isAdjust = true;
    private ColorfulToastGravity colorfulToastGravity = ColorfulToastGravity.NULL;
    private int vGravity = Gravity.BOTTOM;
    private int offset_x = 0;
    private int offset_y = DEF_OFFSET_Y;

    public static ColorfulToast makeText(Context context,String msg,int duration){
        return new ColorfulToast(context,msg,duration);
    }

    public static ColorfulToast makeText(Context context,int msg,int duration){
        return makeText(context,context.getText(msg).toString(),duration);
    }

    public static ColorfulToast makeText(Context context,String msg){
        return makeText(context,msg,SHORT);
    }

    public static ColorfulToast makeText(Context context,int msg){
        return makeText(context,context.getText(msg).toString(),SHORT);
    }

    public ColorfulToast(Context context,String msg,int duration){
        this.context = context;
        this.msg = msg;
        this.duration = duration;
        LayoutInflater inflate = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rootView = inflate.inflate(R.layout.toast_layout,null);
        tv = rootView.findViewById(R.id.tv);
        toast = new Toast(context);
        toast.setView(rootView);
    }

    public ColorfulToast setText(String msg){
        tv.setText(msg);
        return this;
    }

    public ColorfulToast setText(int msg){
        return setText(context.getString(msg).toString());
    }

    public ColorfulToast setTextSize(int textSize){
        this.textSize = textSize;
        tv.setTextSize(this.textSize);
        return this;
    }

    public ColorfulToast setTextColor(int textColor){
        tv.setTextColor(textColor);
        return this;
    }

    public ColorfulToast setBackgroundColor(int backgroundColor){
        this.backgroundColor = backgroundColor;
        return this;
    }

    public ColorfulToast setBackgroundRadius(int backgroundRadius){
        this.backgroundRadius = backgroundRadius;
        return this;
    }

    public ColorfulToast setBackgroundStroke(int strokeWidth,int strokeColor){
        this.strokeWidth = strokeWidth;
        this.strokeColor = strokeColor;
        return this;
    }

    public ColorfulToast setLeftDrawable(int leftDrawable){
        return setLeftDrawable(context.getDrawable(leftDrawable));
    }

    public ColorfulToast setLeftDrawable(Drawable leftDrawable){
        this.leftDrawable = leftDrawable;
        return this;
    }

    public ColorfulToast setRightDrawable(int rightDrawable){
        return setRightDrawable(context.getDrawable(rightDrawable));
    }

    public ColorfulToast setRightDrawable(Drawable rightDrawable){
        this.rightDrawable = rightDrawable;
        return this;
    }

    public ColorfulToast isAdjustDrawable(boolean isAdjust){
        this.isAdjust = isAdjust;
        return this;
    }

    public ColorfulToast setGravity(ColorfulToastGravity colorfulToastGravity){
        this.colorfulToastGravity = colorfulToastGravity;
        return this;
    }

    public ColorfulToast setGravity(int vGravity,int offset_x,int offset_y){
        this.vGravity = vGravity;
        this.offset_x = offset_x;
        this.offset_y = offset_y;
        return this;
    }

    public void show(){
        GradientDrawable gd = new GradientDrawable();
        gd.setShape(GradientDrawable.RECTANGLE);
        gd.setColor(backgroundColor);
        gd.setCornerRadius(dp2px(context,backgroundRadius));
        if(strokeWidth > 0)
            gd.setStroke((int) dp2px(context,strokeWidth),strokeColor);
        rootView.setBackground(gd);

        if(isAdjust){
            int size  = (int) (tv.getTextSize()*1.3f);
            if(leftDrawable!=null)
                leftDrawable.setBounds(0,0,size,size);
            if(rightDrawable!=null)
                rightDrawable.setBounds(0,0,size,size);
            tv.setCompoundDrawables(leftDrawable,null,rightDrawable,null);
        }else{
            tv.setCompoundDrawablesWithIntrinsicBounds(leftDrawable,null,rightDrawable,null);
        }

        tv.setText(msg);

        if(colorfulToastGravity != ColorfulToastGravity.NULL){
            switch (colorfulToastGravity){
                case CENTER:
                    toast.setGravity(Gravity.CENTER,0,0);
                    break;
                case TOP_CENTER:
                    toast.setGravity(Gravity.TOP,0,DEF_OFFSET_Y);
                    break;
                case BOTTOM_CENTER:
                    toast.setGravity(Gravity.BOTTOM,0,DEF_OFFSET_Y);
                    break;
            }
        }else {
            toast.setGravity(vGravity,offset_x,offset_y);
        }

        toast.setDuration(duration == LONG ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
        toast.show();
}

    public void updateMsg(String msg){
        if(toast != null){
            tv.setText(msg);
            toast.show();
        }
    }

    public void cancel(){
        if(toast != null)
            toast.cancel();
    }

    public static void showSuccessInfo(Context context,String msg,int duration){
        new ColorfulToast(context,msg,duration)
                .setTextColor(Color.WHITE)
                .setBackgroundColor(TOAST_GREEN)
                .setLeftDrawable(R.drawable.toast_success)
                .show();
    }

    public static void showSuccessInfo(Context context,int msg,int duration){
        showSuccessInfo(context,context.getText(msg).toString(),duration);
    }

    public static void showErrorInfo(Context context,String msg,int duration){
        new ColorfulToast(context,msg,duration)
                .setTextColor(Color.WHITE)
                .setBackgroundColor(TOAST_RED)
                .setLeftDrawable(R.drawable.toast_error)
                .show();
    }

    public static void showErrorInfo(Context context,int msg,int duration){
        showErrorInfo(context,context.getText(msg).toString(),duration);
    }

    public static void showWarnInfo(Context context,String msg,int duration){
        new ColorfulToast(context,msg,duration)
                .setTextColor(Color.WHITE)
                .setBackgroundColor(TOAST_YELLOW)
                .setLeftDrawable(R.drawable.toast_warring)
                .show();
    }

    public static void showWarnInfo(Context context,int msg,int duration){
        showWarnInfo(context,context.getText(msg).toString(),duration);
    }

    private static float dp2px(Context context, float dpVal){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, context.getResources().getDisplayMetrics());
    }
}
