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
		// TODO �Զ����ɵĹ��캯�����
	}
	//�ص��ӿ�
	public interface ImageCallback{
		public void imageLoaded(Drawable imageDrawable);
	}

	private Map<String, SoftReference<Drawable>> imageCache = 
			new HashMap<String ,SoftReference<Drawable>>();
	
	public Drawable loadDrawable(final String imageUrl, final ImageCallback callback){
		//��ѯ���棬�鿴Ҫ���ص�ͼƬ�Ƿ�����ڻ��浱�У�
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
		//�¿���һ���̣߳������߳���������ͼƬ��
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
	
	
	//�÷����Ǹ���ͼƬ��URL������������ͼƬ
	protected Drawable loadImageFromUrl(String imageUrl){
		try {
			//����URL����ͼƬ��������һ��Drawable����
			return Drawable.createFromStream(new URL(imageUrl).openStream(), "src");
		} catch (Exception e) {
			// TODO: handle exception
			Log.d("test", e.getMessage());
			throw new RuntimeException();
			
		}
	}
	
	
}
