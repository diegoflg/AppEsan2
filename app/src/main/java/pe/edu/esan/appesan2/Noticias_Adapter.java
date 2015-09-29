package pe.edu.esan.appesan2;

import java.util.ArrayList;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Noticias_Adapter extends ArrayAdapter<Object> {
	Context context;
	private ArrayList<Noticias> noticias;
	RequestQueue requestQueue;
	ImageLoader imageLoader;

	//FUENTE Y COLOR PARA LOS TiTULOS :
	String font_pathT = "font/HelveticaNeue-Bold.ttf"; //ruta de la fuente
	Typeface TFT = Typeface.createFromAsset(getContext().getAssets(), font_pathT);//llamanos a la CLASS TYPEFACE y la definimos con un CREATE desde ASSETS con la ruta STRING

	//FUENTE Y COLOR PARA LA DESCRIPCIoN :
	String font_pathD = "font/HelveticaNeue-Light.ttf"; //ruta de la fuente
	Typeface TFD = Typeface.createFromAsset(getContext().getAssets(), font_pathD);//llamanos a la CLASS TYPEFACE y la definimos con un CREATE desde ASSETS con la ruta STRING

	//FUENTE Y COLOR PARA LAS FECHAS :
	String font_pathF = "font/HelveticaNeue-CondensedBold.ttf"; //ruta de la fuente
	Typeface TFF = Typeface.createFromAsset(getContext().getAssets(), font_pathF);//llamanos a la CLASS TYPEFACE y la definimos con un CREATE desde ASSETS con la ruta STRING

	public Noticias_Adapter(Context context, ArrayList<Noticias> noticias) {
		super(context, R.layout.item_noticias);
		this.context = context;
		this.noticias = noticias;

		requestQueue = Volley.newRequestQueue(context);
		imageLoader = new ImageLoader(requestQueue, new BitmapCache(10));
	}

	@Override
	public int getCount() {
		return noticias.size();
	}

	private static class PlaceHolder {

		TextView title;
		TextView time;
		TextView content;

		ImageView picture;

		public static PlaceHolder generate(View convertView) {
			PlaceHolder placeHolder = new PlaceHolder();
			placeHolder.title = (TextView) convertView.findViewById(R.id.noticia_textview_title);
			placeHolder.time = (TextView) convertView.findViewById(R.id.noticia_textview_time);
			placeHolder.content = (TextView) convertView.findViewById(R.id.noticia_textview_content);

			placeHolder.picture = (ImageView) convertView.findViewById(R.id.noticia_imageView);
			return placeHolder;
		}
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		PlaceHolder placeHolder;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.item_noticias, null);
			placeHolder = PlaceHolder.generate(convertView);
			convertView.setTag(placeHolder);
		} else {
			placeHolder = (PlaceHolder) convertView.getTag();
		}
		placeHolder.title.setText(noticias.get(position).getTitulo());
		placeHolder.time.setText(""
				+ noticias.get(position).getFecha().getDate() + "/"
				+ noticias.get(position).getFecha().getMonth());
		placeHolder.content.setText(noticias.get(position).getResumen());

		placeHolder.title.setTypeface(TFT);
		placeHolder.title.setTextColor(Color.parseColor("#1A171B"));
		placeHolder.time.setTypeface(TFF);
		placeHolder.time.setTextColor(Color.parseColor("#1A171B"));
		placeHolder.content.setTypeface(TFD);
		placeHolder.content.setTextColor(Color.parseColor("#1A171B"));

		imageLoader.get(noticias.get(position).getImagen(), ImageLoader.getImageListener(placeHolder.picture, R.drawable.esan, R.drawable.esan));
		return (convertView);
	}

}
