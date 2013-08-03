package com.badmintonbuddy;

import java.io.File;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.weboapps.badmintonbuddy.R;

public class TipsViewPagerAdapter extends PagerAdapter {

	private LayoutInflater mInflater;
	DisplayImageOptions options;
	ImageLoader imageLoader;
	TipsActivity mContext;
	String[] strText;

	int[] images = { R.drawable.a1, R.drawable.a2, R.drawable.a3,
			R.drawable.a4, R.drawable.a5, R.drawable.a6, R.drawable.a7,
			R.drawable.a9, R.drawable.a10, R.drawable.a11,
			R.drawable.a12, R.drawable.a13, R.drawable.a14, R.drawable.a15,
			R.drawable.a16, R.drawable.a17, R.drawable.a18, R.drawable.a19,
			R.drawable.a20 };

	public TipsViewPagerAdapter(TipsActivity context) {
		mContext = context;
		mInflater = LayoutInflater.from(mContext);
		imageLoader = ImageLoader.getInstance();
		
		File cacheDir = StorageUtils.getCacheDirectory(mContext);
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
        .memoryCacheExtraOptions(480, 800) // default = device screen dimensions
        .discCacheExtraOptions(480, 800, CompressFormat.JPEG, 75)
        .threadPriority(Thread.NORM_PRIORITY - 1) // default
        .tasksProcessingOrder(QueueProcessingType.FIFO) // default
        .denyCacheImageMultipleSizesInMemory()
        .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // default
        .memoryCacheSize(2 * 1024 * 1024)
        .discCache(new UnlimitedDiscCache(cacheDir)) // default
        .discCacheSize(50 * 1024 * 1024)
        .discCacheFileCount(100)
        .discCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
        .imageDownloader(new BaseImageDownloader(context)) // default
        .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
        .enableLogging()
        .build();
		
		imageLoader.init(config);
		strText = mContext.getResources().getStringArray(R.array.tips);

		options = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.no_image)
				.showImageOnFail(R.drawable.no_image).resetViewBeforeLoading()
				.cacheOnDisc().imageScaleType(ImageScaleType.IN_SAMPLE_INT)
				.bitmapConfig(Bitmap.Config.RGB_565)
				.displayer(new FadeInBitmapDisplayer(300)).build();
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager) container).removeView((View) object);
	}

	@Override
	public void finishUpdate(View container) {
	}

	@Override
	public int getCount() {
		return images.length;
	}

	@Override
	public Object instantiateItem(ViewGroup view, int position) {
		View imageLayout = mInflater.inflate(R.layout.item_pager_image, view,
				false);
		ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image);
		TextView textViewTips = (TextView) imageLayout
				.findViewById(R.id.textViewTips);
		final ProgressBar spinner = (ProgressBar) imageLayout
				.findViewById(R.id.loading);
		String imageUrl = "drawable://"+images[position];
		textViewTips.setText(strText[position]);
		imageLoader.displayImage(imageUrl, imageView, options,
				new SimpleImageLoadingListener() {
					@Override
					public void onLoadingStarted(String imageUri, View view) {
						spinner.setVisibility(View.VISIBLE);
					}

					@Override
					public void onLoadingFailed(String imageUri, View view,
							FailReason failReason) {
						String message = null;
						switch (failReason.getType()) {
						case IO_ERROR:
							message = "Image cannot be downloaded";
							break;
						case DECODING_ERROR:
							message = "Image can't be decoded";
							break;
						case NETWORK_DENIED:
							message = "Downloads are denied";
							break;
						case OUT_OF_MEMORY:
							message = "Out Of memory error";
							break;
						case UNKNOWN:
							message = "Unknown error";
							break;
						}
						Toast.makeText(mContext, message, Toast.LENGTH_SHORT)
								.show();
						spinner.setVisibility(View.GONE);
					}

					@Override
					public void onLoadingComplete(String imageUri, View view,
							Bitmap loadedImage) {
						spinner.setVisibility(View.GONE);
					}
				});
		((ViewPager) view).addView(imageLayout, 0);
		return imageLayout;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view.equals(object);
	}

	@Override
	public void restoreState(Parcelable state, ClassLoader loader) {
	}

	@Override
	public Parcelable saveState() {
		return null;
	}

	@Override
	public void startUpdate(View container) {
	}
}