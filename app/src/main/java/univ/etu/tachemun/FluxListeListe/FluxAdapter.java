package univ.etu.tachemun.FluxListeListe;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import univ.etu.tachemun.R;
import univ.etu.tachemun.db.tableclass.ListeTache;
import univ.etu.tachemun.db.tableclass.Tache;
import univ.etu.tachemun.db.tablemanagers.TacheManager;

public class FluxAdapter extends ArrayAdapter<Flux> {
    private ArrayList<ListeTache> list;
    private Context context;

    //flux est la liste des models à afficher
    public FluxAdapter(Context context, List<Flux> flux, ArrayList<ListeTache> arrayList) {
        super(context, 0, flux);
        this.list = arrayList;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.flux_view, parent, false);
        }

        FluxViewHolder viewHolder = (FluxViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new FluxViewHolder();
            viewHolder.pseudo = (TextView) convertView.findViewById(R.id.pseudo);
            viewHolder.text = (TextView) convertView.findViewById(R.id.text);
            viewHolder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            viewHolder.progressBar = (ProgressBar) convertView.findViewById(R.id.progressBar);
            viewHolder.pourcent = (TextView) convertView.findViewById(R.id.pourcent);
            viewHolder.date = (TextView) convertView.findViewById(R.id.DATE_LISTE);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Flux flux = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        double a = getTachesOfListe(position).size();
        double b = getTachesRealOfListe(position).size();
        viewHolder.pseudo.setText(flux.getPseudo());
        viewHolder.text.setText(flux.getText());

        Date d = new Date();
        d.setMonth(d.getMonth() + 1);
        Date dactu = new Date();


        if (list.get(position).getDateHeureEcheance() != null) {
            if (list.get(position).getDateHeureEcheance().before(d) && list.get(position).getDateHeureEcheance().after(dactu)) {
                if (a != 0) {
                    if (((b / a) * 100) != 100) {
                        viewHolder.pseudo.setTextColor(Color.argb(200, 255, 106, 0));
                    }
                }
            } else {
                if (list.get(position).getDateHeureEcheance().before(dactu)) {
                    if (a != 0) {
                        if (((b / a) * 100) != 100) {
                            viewHolder.pseudo.setTextColor(Color.argb(200, 255, 0, 0));
                        }
                    }
                }
            }
        }


        viewHolder.avatar.setImageDrawable(new ColorDrawable(flux.getColor()));
        viewHolder.date.setText(flux.getDate());

        if (flux.getDate() == "") {
            viewHolder.date.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.date.setVisibility(View.VISIBLE);
        }

        if (a == 0) {
            viewHolder.pourcent.setText(100 + "%");
            viewHolder.progressBar.setProgress(100);
        } else {
            double c = (b / a) * 100;
            viewHolder.pourcent.setText((int) c + "%");
            viewHolder.progressBar.setProgress((int) c);
        }

        return convertView;
    }

    private ArrayList<Tache> getTachesOfListe(int i) {
        TacheManager t = new TacheManager(context);
        return t.getAllTachesFromListe(list.get(i).getID());
    }

    private ArrayList<Tache> getTachesRealOfListe(int i) {
        TacheManager t = new TacheManager(context);
        return t.getTachesRealFromListe(list.get(i).getID());
    }

    private class FluxViewHolder {
        TextView pseudo;
        TextView text;
        ImageView avatar;
        ProgressBar progressBar;
        TextView pourcent;
        TextView date;
    }

}
