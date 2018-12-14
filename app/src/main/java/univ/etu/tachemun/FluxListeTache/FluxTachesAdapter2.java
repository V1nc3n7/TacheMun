package univ.etu.tachemun.FluxListeTache;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import univ.etu.tachemun.R;

public class FluxTachesAdapter2 extends ArrayAdapter<FluxTaches> {

    public FluxTachesAdapter2(Context context, List<FluxTaches> list) {
        super(context, 0, list);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.tache_view_check, parent, false);
        }
        FluxTachesViewHolder fluxTachesViewHolder = (FluxTachesViewHolder) convertView.getTag();

        if (fluxTachesViewHolder == null) {
            fluxTachesViewHolder = new FluxTachesViewHolder();
            //fluxTachesViewHolder.isCheck = (CheckBox) convertView.findViewById(R.id.checkBox);
            fluxTachesViewHolder.Text = (TextView) convertView.findViewById(R.id.TEXT_TACHE);
            convertView.setTag(fluxTachesViewHolder);
        }

        FluxTaches fluxTaches = getItem(position);
        fluxTachesViewHolder.Text.setText(fluxTaches.getText());
        /*
        fluxTachesViewHolder.isCheck.setChecked(list.get (position).getCheck());
        fluxTachesViewHolder.isCheck.setClickable(false);
        fluxTachesViewHolder.isCheck.setEnabled(true);
        fluxTachesViewHolder.isCheck.setTag(fluxTachesViewHolder);
        */
        convertView.setTag (fluxTachesViewHolder);
        return convertView;
    }

    private class FluxTachesViewHolder {
        TextView Text;
        //CheckBox isCheck;
    }
}