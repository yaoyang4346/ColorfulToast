# ColorfulToast
由于Toast封装的比较完善，我们很难对Toast的样式进行更改，甚至简单的字体颜色和大小都不能直接更改，只能自定义一个view添加进去，这样又比较麻烦，这里分享一个自用的Toast封装，可以很轻松的修改背景，字体以及添加一些简单的图标或者调整Toast出现的位置。

如下：
![](https://upload-images.jianshu.io/upload_images/5111131-14ceaf6e0f04e1cc.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![](https://upload-images.jianshu.io/upload_images/5111131-a5312c8150f603d6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![](https://upload-images.jianshu.io/upload_images/5111131-9c3f633b5bed9493.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![](https://upload-images.jianshu.io/upload_images/5111131-865662293b12d83d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![](https://upload-images.jianshu.io/upload_images/5111131-cc222c31a00014e2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

代码只有一个文件，需要的话复制到自己的工程即可，注意还有1个layout文件和3个drawable文件。

基本方法介绍及示例：
```
public static ColorfulToast makeText(Context context,int msg,int duration)
public static ColorfulToast makeText(Context context,String msg,int duration)   //设置文字和时长
public static ColorfulToast makeText(Context context,int msg)
public static ColorfulToast makeText(Context context,String msg)  //设置文字，时长默认是Toast.LENGTH_SHORT
public static final int SHORT  //即Toast.LENGTH_SHORT
public static final int LONG   //即Toast.LENGTH_LONG
public ColorfulToast setText(int msg)
public ColorfulToast setText(String msg) //设置文字内容
public ColorfulToast setTextSize(int textSize) //设置文字大小
public ColorfulToast setTextColor(int textColor) //设置文字颜色
public ColorfulToast setBackgroundColor(int backgroundColor) //设置背景颜色
public ColorfulToast setBackgroundRadius(int backgroundRadius) //设置背景圆角弧度
public ColorfulToast setBackgroundStroke(int strokeWidth,int strokeColor) //设置背景边框宽度和颜色
public ColorfulToast setLeftDrawable(Drawable leftDrawable)
public ColorfulToast setLeftDrawable(int leftDrawable)  //设置文字左边图片
public ColorfulToast setRightDrawable(Drawable leftDrawable)
public ColorfulToast setRightDrawable(int rightDrawable) //设置文字右边图片
public ColorfulToast isAdjustDrawable(boolean isAdjust) //设置是否根据文字大小调整图片大小，默认为调整
public ColorfulToast setGravity(ColorfulToastGravity colorfulToastGravity) //设置Toast显示位置，该方法传入一个枚举值，分别为TOP_CENTER（顶部居中）,CENTER（屏幕正中央）,BOTTOM_CENTER（底部居中）
public ColorfulToast setGravity(int vGravity,int offset_x,int offset_y) //设置具体的位置
public void show() //设置完成后显示这个Toast
public void updateMsg(String msg)  //更新文字内容，更新后Toast显示时长会刷新
public void cancel() //隐藏这个Toast
public static void showSuccessInfo(Context context,String msg,int duration) //显示一个预置的正确提示Toast，见上面第2个示例图
public static void showErrorInfo(Context context,String msg,int duration) //显示一个预置的错误提示Toast，见上面第3个示例图
public static void showWarnInfo(Context context,String msg,int duration) //显示一个预置的警告提示Toast，见上面第4个示例图
``` 

示例代码，显示效果依次为前面的几个示例图
```
ColorfulToast.makeText(this, TAG).show();//显示一个普通的Toast，默认为灰色背景黑色字体短时长
```
```
ColorfulToast.showSuccessInfo(this, TAG, ColorfulToast.SHORT);//显示一个预置的正确提示Toast
```
```
ColorfulToast.showErrorInfo(this,TAG,ColorfulToast.SHORT);//显示一个预置的错误提示Toast
```
```
ColorfulToast.showWarnInfo(this,TAG,ColorfulToast.SHORT);//显示一个预置的警告提示Toast
```
```
ColorfulToast.makeText(this,TAG)
                .setBackgroundColor(Color.parseColor("#FC3B5E"))
                .setBackgroundStroke(2,Color.parseColor("#8CD3EC"))
                .setBackgroundRadius(10)
                .setTextColor(Color.parseColor("#8CD3EC"))
                .setGravity(ColorfulToast.ColorfulToastGravity.CENTER)
                .setLeftDrawable(R.mipmap.ic_launcher)
                .setRightDrawable(R.mipmap.ic_launcher)
                .show();  //在屏幕中间显示一个高度自定义化的Toast
```
**[简书](https://www.jianshu.com/p/fddb4b72edbf)**




