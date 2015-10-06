package pe.edu.esan.appesan2;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class ExpListViewAdaptercaf extends BaseExpandableListAdapter {
    // Declare Variables
    Context context;
    List<String> titulos;
    HashMap<String, List<String>> subtitulos;
    int[] imagenes;
    LayoutInflater inflater;

    public ExpListViewAdaptercaf(Context context, List<String> titulos, HashMap<String, List<String>> subtitulos, int[] imagenes) {
        this.context = context;
        this.titulos = titulos;
        this.subtitulos = subtitulos;
        this.imagenes = imagenes;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.subtitulos.get(this.titulos.get(groupPosition)).get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.lay_list_item, null);
        }
        String font_pathEEC = "font/HelveticaNeue-Light.ttf"; //ruta de la fuente
        Typeface TFEEC = Typeface.createFromAsset(context.getAssets(), font_pathEEC);

        TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);
        txtListChild.setTypeface(TFEEC);
        txtListChild.setText(childText);
        txtListChild.setBackgroundColor(Color.parseColor("#FFFFFF"));
        txtListChild.setTextColor(Color.parseColor("#000000"));
        txtListChild.setSelected(true);
        txtListChild.setHighlightColor(Color.parseColor("#C90039"));
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.subtitulos.get(this.titulos.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.titulos.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.titulos.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.lay_list_group2, null);
        }

        String font_pathEE = "font/HelveticaNeue-Roman.ttf"; //ruta de la fuente
        Typeface TFEE = Typeface.createFromAsset(context.getAssets(), font_pathEE);

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(TFEE);
        lblListHeader.setText(headerTitle);

        ImageView imageV2 = (ImageView)convertView.findViewById(R.id.imageView2);
        imageV2.setImageResource(imagenes[groupPosition]);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }



}