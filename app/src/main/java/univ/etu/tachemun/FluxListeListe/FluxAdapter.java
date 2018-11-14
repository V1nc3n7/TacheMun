package univ.etu.tachemun.FluxListeListe;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import univ.etu.tachemun.R;

public class FluxAdapter extends ArrayAdapter<Flux> {
    //flux est la liste des models à afficher
    public FluxAdapter(Context context, List<Flux> flux) {
        super(context, 0, flux);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.flux_view,parent, false);
        }

        FluxViewHolder viewHolder = (FluxViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new FluxViewHolder();
            viewHolder.pseudo = (TextView) convertView.findViewById(R.id.pseudo);
            viewHolder.text = (TextView) convertView.findViewById(R.id.text);
            viewHolder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Flux flux = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.pseudo.setText(flux.getPseudo());
        viewHolder.text.setText(flux.getText());
        viewHolder.avatar.setImageDrawable(new ColorDrawable(flux.getColor()));

        return convertView;
    }

    private class FluxViewHolder{
        TextView pseudo;
        TextView text;
        ImageView avatar;
    }

}
