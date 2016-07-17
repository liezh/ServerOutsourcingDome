package com.http.httpTool;

import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class AsyncImageLoader {

	public AsyncImageLoader() {
		// TODO 自动生成的构造函数存根
	}
	//回调接口
	public interface ImageCallback{
		public void imageLoaded(Drawable imageDrawable);
	}

	private Map<String, SoftReference<Drawable>> imageCache = 
			new HashMap<String ,SoftReference<Drawable>>();
	
	public Drawable loadDrawable(final String imageUrl, final ImageCallback callback){
		//查询缓存，查看要加载的图片是否存在于缓存当中；
		if(imageCache.containsKey(imageUrl)){
			SoftReference<Drawable> softReference = imageCache.get(imageUrl);
			if(softReference.get() != null){
				return softReference.get();
			}
		}
		
		final Handler handler = new Handler(){
			@Override
			public void handleMessage(android.os.Message msg) {
				callback.imageLoaded((Drawable)msg.obj);
			};
		};
		//新开辟一个线程，，该线程用于下载图片；
		new Thread() {
			@Override
			public void run() {
				Drawable drawable = loadImageFromUrl(imageUrl);
				imageCache.put(imageUrl, new SoftReference<Drawable>(drawable));
				Message message = handler.obtainMessage(0,drawable);
				handler.sendMessage(message);
			}
		}.start();
		
		return null;
	}
	
	
	//该方法是根据图片的URL从网络上下载图片
	protected Drawable loadImageFromUrl(String imageUrl){
		try {
			//根据URL下载图片，并生成一个Drawable对象
			return Drawable.createFromStream(new URL(imageUrl).openStream(), "src");
		} catch (Exception e) {
			// TODO: handle exception
			Log.d("test", e.getMessage());
			throw new RuntimeException();
			
		}
	}
	
	
}
