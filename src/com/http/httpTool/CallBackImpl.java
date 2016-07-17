package com.http.httpTool;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class CallBackImpl implements AsyncImageLoader.ImageCallback{

	private ImageView imageView;
	public CallBackImpl(ImageView imageView)  {
		this.imageView = imageView;
	}

	@Override
	public void imageLoaded(Drawable imageDrawable) {
		imageView.setImageDrawable(imageDrawable);
		
	}

}
