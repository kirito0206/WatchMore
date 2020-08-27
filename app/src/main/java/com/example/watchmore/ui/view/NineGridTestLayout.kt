package com.example.watchmore.ui.view

import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.watchmore.R
import com.example.watchmore.ui.adapter.MyPagerAdapter
import com.example.watchmore.util.ImageLoaderUtil
import com.nostra13.universalimageloader.core.assist.FailReason
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener
import kotlinx.android.synthetic.main.dialog_top.view.*


class NineGridTestLayout : NineGridLayout {
    private var dialog: Dialog? = null
    private var myPagerAdapter : MyPagerAdapter? = null
    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!,
        attrs
    ) {
    }

    override fun displayOneImage(
        imageView: RatioImageView?,
        url: String?,
        parentWidth: Int
    ): Boolean {
        ImageLoaderUtil.displayImage(
            mContext,
            imageView,
            url,
            ImageLoaderUtil.getPhotoImageOption(),
            object : ImageLoadingListener {
                override fun onLoadingStarted(imageUri: String?, view: View?) {}
                override fun onLoadingFailed(
                    imageUri: String?,
                    view: View?,
                    failReason: FailReason?
                ) {
                }

                override fun onLoadingComplete(
                    imageUri: String?,
                    view: View?,
                    bitmap: Bitmap
                ) {
                    val w = bitmap.width
                    val h = bitmap.height
                    val newW: Int
                    val newH: Int
                    if (h > w * MAX_W_H_RATIO) { //h:w = 5:3
                        newW = parentWidth / 2
                        newH = newW * 5 / 3
                    } else if (h < w) { //h:w = 2:3
                        newW = parentWidth * 2 / 3
                        newH = newW * 2 / 3
                    } else { //newH:h = newW :w
                        newW = parentWidth / 2
                        newH = h * newW / w
                    }
                    setOneImageLayoutParams(imageView!!, newW, newH)
                }

                override fun onLoadingCancelled(imageUri: String?, view: View?) {}
            })
        return false
    }

    override fun displayImage(
        imageView: RatioImageView?,
        url: String?
    ) {
        //ImageLoaderUtil.getImageLoader(mContext)
            //.displayImage(url, imageView, ImageLoaderUtil.getPhotoImageOption())
        if (imageView != null) {
            Glide.with(context).load(url)
                .into(imageView)
        }
    }

    override fun onClickImage(
        i: Int,
        url: String?,
        urlList: List<String>?
    ) {

        Toast.makeText(mContext, "点击了图片$url", Toast.LENGTH_SHORT).show()
    }

    companion object {
        protected const val MAX_W_H_RATIO = 3
    }

    /*private fun showBigPicture(photos : ArrayList<String>,index : Int) {
        dialog = Dialog(context, R.style.ThemeOverlay_AppCompat_Dark_ActionBar)
        var contentView = View.inflate(context, R.layout.dialog_top, null)
        myPagerAdapter = MyPagerAdapter(context, photos, dialog!!);
        contentView.vp.adapter = myPagerAdapter
        vp.currentItem = index
        ivDelete.setOnClickListener(View.OnClickListener() {
             fun onClick(v : View) {
                var currentItem = vp.currentItem
                Iterator<String> sListIterator = photos.iterator()
                while (sListIterator.hasNext()) {
                    String e = sListIterator.next();
                    if (e.equals(photos.get(currentItem))) {
                        sListIterator.remove();
                        mAdapter1.notifyDataSetChanged();
                        break;
                    }
                }
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                ngiv.setAdapter(MainActivity.this.mAdapter);
                ngiv.setImagesData(photos);
            }
        });
        dialog.setContentView(contentView);
        dialog.setCancelable(true);
        dialog.getWindow().setLayout(
            getScreenWidth(),
            getScreenHeight());
        dialog.show();
    }
    public int getScreenWidth() {
        Resources resources = getResources();
        return resources.getDisplayMetrics().widthPixels;
    }
    public int getScreenHeight() {
        Resources resources = getResources();
        return resources.getDisplayMetrics().heightPixels;
    }
}*/
}