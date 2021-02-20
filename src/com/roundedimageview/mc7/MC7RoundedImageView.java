package com.roundedimageview.mc7;


import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.support.annotation.DimenRes;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.keywords.Common.DesignerCustomView;
import anywheresoftware.b4a.BA.Author;
import anywheresoftware.b4a.BA.DependsOn;
import anywheresoftware.b4a.BA.DesignerProperties;
import anywheresoftware.b4a.BA.ShortName;
import anywheresoftware.b4a.BA.Version;
import anywheresoftware.b4a.objects.CustomViewWrapper;
import anywheresoftware.b4a.objects.LabelWrapper;
import anywheresoftware.b4a.objects.PanelWrapper;
import anywheresoftware.b4a.objects.ViewWrapper;
import anywheresoftware.b4a.objects.collections.Map;
import anywheresoftware.b4a.objects.drawable.BitmapDrawable;
import anywheresoftware.b4a.objects.drawable.CanvasWrapper;
import anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper;
import anywheresoftware.b4a.BA.ActivityObject;
import anywheresoftware.b4a.BA.Events;
import anywheresoftware.b4a.BA.Hide;
import anywheresoftware.b4a.BA.Property;
import anywheresoftware.b4a.keywords.Common;

import com.makeramen.roundedimageview.RoundedImageView;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

@Author("MC7 -  M-CAP7AIN ")
@ShortName("MC7RoundedImageView")
@Version(1.9f)
@ActivityObject
@Events(values={"onClick","onLongClick"})
//@DependsOn(values={"support-annotations-27.1.1"})
@DesignerProperties(values={
		@Property(key="CornerRadius",   displayName="Corner Radius", fieldType="float", defaultValue="0"),
		@Property(key="BorderWidth", displayName="Border Width", fieldType="int", defaultValue="0"),
		@Property(key="BorderColor", displayName="Border Color", fieldType="color", defaultValue="0xFFFFFFFF"),
		@Property(key="Oval",  displayName="Oval", fieldType="boolean", defaultValue="false"),
		@Property(key="ScaleType",  displayName="Scale Type", fieldType="string", defaultValue="SCALETYPE_DEFAULT" ,
		list="SCALETYPE_DEFAULT|SCALETYPE_CENTER|SCALETYPE_CENTER_CROP|SCALETYPE_CENTER_INSIDE|SCALETYPE_FIT_CENTER|SCALETYPE_FIT_END|SCALETYPE_FIT_START|SCALETYPE_FIT_XY|SCALETYPE_MATRIX"),
		@Property(key="TITLEMODE",  displayName="TITLE MODE", fieldType="string", defaultValue="TITLE_MODE_UNDEFINED" ,list="TITLE_MODE_UNDEFINED|TITLE_MODE_CLAMP|TITLE_MODE_REPEAT|TITLE_MODE_MIRROR")	
	})


public class MC7RoundedImageView extends ViewWrapper<RoundedImageView> implements DesignerCustomView{
	private RoundedImageView mRoundedImageView;
    private BA ba;
    private String eventName;
    
	public static final ImageView.ScaleType SCALETYPE_CENTER=ScaleType.CENTER; 
	public static final ImageView.ScaleType SCALETYPE_CENTER_CROP=ScaleType.CENTER_CROP;
	public static final ImageView.ScaleType SCALETYPE_CENTER_INSIDE=ScaleType.CENTER_INSIDE;
	public static final ImageView.ScaleType SCALETYPE_FIT_CENTER=ScaleType.FIT_CENTER;
	public static final ImageView.ScaleType SCALETYPE_FIT_END=ScaleType.FIT_END;
	public static final ImageView.ScaleType SCALETYPE_FIT_START=ScaleType.FIT_START;
	public static final ImageView.ScaleType SCALETYPE_FIT_XY=ScaleType.FIT_XY;
	public static final ImageView.ScaleType SCALETYPE_MATRIX=ScaleType.MATRIX;

	public static final int DRAWING_CACHE_QUALITY_AUTO = RoundedImageView.DRAWING_CACHE_QUALITY_AUTO;
	public static final int DRAWING_CACHE_QUALITY_LOW  = RoundedImageView.DRAWING_CACHE_QUALITY_LOW;
	public static final int DRAWING_CACHE_QUALITY_HIGH = RoundedImageView.DRAWING_CACHE_QUALITY_HIGH;
	
	public static final int TITLE_MODE_UNDEFINED = -2;
	public static final Shader.TileMode TITLE_MODE_CLAMP = Shader.TileMode.CLAMP;
	public static final Shader.TileMode TITLE_MODE_MIRROR = Shader.TileMode.MIRROR;
	public static final Shader.TileMode TITLE_MODE_REPEAT = Shader.TileMode.REPEAT;

	@Override
	public void Initialize(BA pBA, String EventName) {
		 this._initialize(pBA, null, EventName);
	}
	
	
	@BA.Hide
	public void _initialize(BA bA, Object object, String string) {
		// TODO Auto-generated method stub
        this.eventName = string.toLowerCase(BA.cul);
        this.ba = bA;
        this.mRoundedImageView = new RoundedImageView(ba.context);
        super.Initialize(ba,string);
        setObject(mRoundedImageView);
        
        mRoundedImageView.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				if (ba.subExists(eventName + "_onclick")) {
					 ba.raiseEvent(mRoundedImageView, eventName + "_onclick" );
				}			
			}
		});
        
        mRoundedImageView.setOnLongClickListener(new OnLongClickListener() {		
			@Override
			public boolean onLongClick(View arg0) {
				if (ba.subExists(eventName + "_onlongclick")) {
					 ba.raiseEvent(mRoundedImageView, eventName + "_onlongclick" );
				}	
				return true;
			}
		});
          

	}
        

	@BA.Hide
	public void innerInitialize(BA pBA, String pEventName, boolean pKeepOldObject) {
		if (!pKeepOldObject){
			setObject(this.mRoundedImageView);
		}
		super.innerInitialize(pBA, pEventName, true);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void DesignerCreateView(PanelWrapper panelWrapper, LabelWrapper labelWrapper, Map map) {
        CustomViewWrapper.replaceBaseWithView(panelWrapper, mRoundedImageView);
        
        int Dip = Common.DipToCurrent((int)map.Get("BorderWidth"));
        mRoundedImageView.setCornerRadius((float)map.Get("CornerRadius"));
        mRoundedImageView.setBorderWidth((float) Dip );
        mRoundedImageView.setBorderColor((int)map.Get("BorderColor"));
        mRoundedImageView.setOval((boolean)map.Get("Oval"));
        
        String scaltype = (String) map.Get("ScaleType");
        switch (scaltype) {
		case "SCALETYPE_DEFAULT":
			
			break;
		case "SCALETYPE_CENTER":
			mRoundedImageView.setScaleType(SCALETYPE_CENTER);
			break;
		case "SCALETYPE_CENTER_CROP":
			mRoundedImageView.setScaleType(SCALETYPE_CENTER_CROP);
			break;
		case "SCALETYPE_CENTER_INSIDE":
			mRoundedImageView.setScaleType(SCALETYPE_CENTER_INSIDE);
			break;
		case "SCALETYPE_FIT_CENTER":
			mRoundedImageView.setScaleType(SCALETYPE_FIT_CENTER);
			break;
		case "SCALETYPE_FIT_END":
			mRoundedImageView.setScaleType(SCALETYPE_FIT_END);
			break;
		case "SCALETYPE_FIT_START":
			mRoundedImageView.setScaleType(SCALETYPE_FIT_START);
			break;
		case "SCALETYPE_FIT_XY":
			mRoundedImageView.setScaleType(SCALETYPE_FIT_XY);
			break;
		case "SCALETYPE_MATRIX":
			mRoundedImageView.setScaleType(SCALETYPE_MATRIX);
			break;
        }

        String TITLEMODE = (String) map.Get("TITLEMODE");
        switch (TITLEMODE) {
        case "TITLE_MODE_UNDEFINED":
        
        	break;
		case "TITLE_MODE_CLAMP":
			mRoundedImageView.setTileModeX(TITLE_MODE_CLAMP);
			mRoundedImageView.setTileModeY(TITLE_MODE_CLAMP);
			break;
		case "TITLE_MODE_REAPEAT":
			mRoundedImageView.setTileModeX(TITLE_MODE_REPEAT);
			mRoundedImageView.setTileModeY(TITLE_MODE_REPEAT);
			break;
		case "TITLE_MODE_MIRROR":
			mRoundedImageView.setTileModeX(TITLE_MODE_MIRROR);
			mRoundedImageView.setTileModeY(TITLE_MODE_MIRROR);
			break;

        }
	}
	

	public void setImageBitmap(BitmapWrapper bm) {		
		getObject().setImageBitmap(bm.getObject());
	}
	public Bitmap getImageBitmap() {	
	    Drawable dd = getObject().getDrawable();
	    return ((android.graphics.drawable.BitmapDrawable)dd).getBitmap();
	}
	
	public void setImageDrawable(Drawable drw) {
		getObject().setImageDrawable(drw);
	}
	public Drawable getImageDrawable() {		
		return getObject().getDrawable();
	}
	
	public float getCornerRadius() {
		return getObject().getCornerRadius();
	}

	public void setCornerRadius(float radius) {
		getObject().setCornerRadius(radius);
    }

	public void setOval(boolean oval) {
		getObject().setOval(oval);
	}

	public float getBorderWidth() {
		return getObject().getBorderWidth();
	}

    public void setBorderWidth(float width) {
    	getObject().setBorderWidth(width);
	}

	public int getBorderColor() {
		return getObject().getBorderColor();
	}

	public void setBorderColors(int color) {
		getObject().setBorderColor(color);	
	}
	
	public boolean getMutateBackground() {
		return getObject().mutatesBackground();
	}

	public void setMutateBackground(boolean mutate) {
		getObject().mutateBackground(mutate);
	}
	
	public void setScaleType(ScaleType scaleType) {
		getObject().setScaleType(scaleType);
	}

	public ScaleType getScaleType() {
		return getObject().getScaleType();
	}
	
	public Boolean isOval(){
		return getObject().isOval();
	}
	public Boolean isClickable(){
		return getObject().isClickable();
	}
	
	public Bitmap getDrawingCache(){
		return getObject().getDrawingCache();
	}
	
	public void setAlpha(int alpha){
		getObject().setAlpha(alpha);
	}
	public void setDrawingCacheEnabled(Boolean enabled){
		getObject().setDrawingCacheEnabled(enabled);
	}
	
	public void setColorFilter(int color){
		getObject().setColorFilter(color);
	}
	
	public void setDrawingCacheBackgroundColor(int color){
		getObject().setDrawingCacheBackgroundColor(color);
	}
	public void setDrawingCacheQuality(int quality){
		getObject().setDrawingCacheQuality(quality);
	}
	
	public void setTileModeX(TileMode tileModeX){
		getObject().setTileModeX(tileModeX);
	}
	public void setTileModeY(TileMode tileModeY){
		getObject().setTileModeY(tileModeY);
	}
	
	
	
	public Bitmap ConvertToBitmap(Drawable drawable, int widthPixels, int heightPixels) {
	    Bitmap mutableBitmap = Bitmap.createBitmap(widthPixels, heightPixels, Bitmap.Config.ARGB_8888);
	    Canvas canvas = new Canvas(mutableBitmap);
	    drawable.setBounds(0, 0, widthPixels, heightPixels);
	    drawable.draw(canvas);

	    return mutableBitmap;
	}

}