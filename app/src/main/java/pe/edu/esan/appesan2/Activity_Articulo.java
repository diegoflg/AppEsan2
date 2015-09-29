package pe.edu.esan.appesan2;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class Activity_Articulo extends Fragment{

	Noticias articulo;
	WebView webview;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceSt0ate){
		View v = inflater.inflate(R.layout.activity_articulo, container, false);
		webview = (WebView) v.findViewById(R.id.articulo_Webview);
		recogerparametro();
		populateWebView();

		v.setOnKeyListener(new View.OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (event.getAction() == KeyEvent.ACTION_DOWN) {

					switch (keyCode) {
						case KeyEvent.KEYCODE_BACK:
							FragmentManager fragmentManager = getActivity().
									getSupportFragmentManager();
							Noticia fragment;
							fragment = new Noticia();
							fragmentManager.beginTransaction().
									replace(R.id.container, fragment).commit();
							break;
					}
				}
				return false;
			}
		});



		return  v;
	}

	private void recogerparametro() {
		Bundle bundle = this.getArguments();
		articulo = (Noticias) bundle.getSerializable("parametro");

	}

	private void populateWebView() {

		webview.loadDataWithBaseURL(null, "<!DOCTYPE HTML>"
				+ populateHTML(R.raw.htmlnoticia), "text/html", "UTF-8", null);
	}

	private String populateHTML(int resourceID) {
		/*
		En esta parte obtiene los datos Título, fecha de publicación y contenido con los
		mismos nombres que se encuentran en el xml del rss.
		 */
		String html;
		html = readTextFromResource(resourceID);
		//Obtiene el título:
		html = html.replace("_TITLE_", articulo.getTitulo());

		//Obtiene la fecha de publicación:
		html = html.replace("_PUBDATE_", "" + articulo.getFecha());

		//Obtiene el contenido:
		html = html.replace("_CONTENT_", articulo.getContenido());
		return html;
	}

	private String readTextFromResource(int resourceID) {
		InputStream raw = getResources().openRawResource(resourceID);
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		int i;
		try {
			i = raw.read();
			while (i != -1) {
				stream.write(i);
				i = raw.read();
			}
			raw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stream.toString();
	}


}





