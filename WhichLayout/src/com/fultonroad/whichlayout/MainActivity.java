package com.fultonroad.whichlayout;

import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends Activity {

	int widthPixels = 0;;
	int heightPixels = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		screenDimensions();
		
		TextView tvDensity = (TextView) findViewById(R.id.density);
		TextView tvDimensions = (TextView) findViewById(R.id.dimensions);
		
		int dens = getResources().getDisplayMetrics().densityDpi;
		double density = getResources().getDisplayMetrics().density;
		
		String str = "";
		
		switch (dens) {
		case DisplayMetrics.DENSITY_LOW:
			str = "Density: LOW";
			break;
		case DisplayMetrics.DENSITY_MEDIUM:
			str = "Density: MEDIUM";
			break;
		case DisplayMetrics.DENSITY_HIGH:
			str = "Density: HIGH";
			break;
		case DisplayMetrics.DENSITY_XHIGH:
			str = "Density: XHIGH";
		    break;
		case DisplayMetrics.DENSITY_XXHIGH:
			str = "Density: XXHIGH";
		    break; 
		case DisplayMetrics.DENSITY_XXXHIGH:
			str = "Density: XXXHIGH";
		    break;
		case DisplayMetrics.DENSITY_TV: 
			str = "Density: TV";
		    break;
		default: 
			str = "Density: UNDEFINED";
		    break;
		}
		
		str += " (";
		str += Integer.toString(dens);
		str += ")";
		str += " (";
		str += Double.toString(density);
		str += ")";
		
		tvDensity.setText(str);
		
		String strDim = "(";
		strDim += Integer.toString(widthPixels);
		strDim += "x";
		strDim += Integer.toString(heightPixels);
		strDim += ")";
		tvDimensions.setText(strDim);
		
		
	}

	private void screenDimensions() {
		WindowManager w = getWindowManager();
		Display d = w.getDefaultDisplay();
		DisplayMetrics metrics = new DisplayMetrics();
		d.getMetrics(metrics);
		// since SDK_INT = 1;
		widthPixels = metrics.widthPixels;
		heightPixels = metrics.heightPixels;
		// includes window decorations (statusbar bar/menu bar)
		if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17)
		try {
		    widthPixels = (Integer) Display.class.getMethod("getRawWidth").invoke(d);
		    heightPixels = (Integer) Display.class.getMethod("getRawHeight").invoke(d);
		} catch (Exception ignored) {
		}
		// includes window decorations (statusbar bar/menu bar)
		if (Build.VERSION.SDK_INT >= 17)
		try {
		    Point realSize = new Point();
		    Display.class.getMethod("getRealSize", Point.class).invoke(d, realSize);
		    widthPixels = realSize.x;
		    heightPixels = realSize.y;
		} catch (Exception ignored) {
		}
	}
	
	
	
}
