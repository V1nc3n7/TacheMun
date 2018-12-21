package univ.etu.tachemun.FluxListeListe;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import univ.etu.tachemun.ListeListeTaches;
import univ.etu.tachemun.R;
import univ.etu.tachemun.db.tableclass.ListeTache;
import univ.etu.tachemun.db.tableclass.Tache;
import univ.etu.tachemun.db.tablemanagers.TacheManager;

public class FluxAdapter extends ArrayAdapter<Flux> {
    private ArrayList<ListeTache> list;
    private Context context;

    //flux est la liste des models à afficher
    public FluxAdapter(Context context, List<Flux> flux,ArrayList<ListeTache> arrayList) {
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
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Flux flux = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        double a = getTachesOfListe(position).size();
        double b = getTachesRealOfListe(position).size();
        viewHolder.pseudo.setText(flux.getPseudo());
        viewHolder.text.setText(flux.getText());
        viewHolder.avatar.setImageDrawable(new ColorDrawable(flux.getColor()));

        if(a == 0){
            viewHolder.pourcent.setText(100+"%");
            viewHolder.progressBar.setProgress(100);
        }
        else{
            double c = (b/a)*100;
            viewHolder.pourcent.setText((int)c+"%");
            viewHolder.progressBar.setProgress((int)c);
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

    private int pourcent(int i){
        ArrayList<Tache> arrayList = getTachesRealOfListe(i);
        ArrayList<Tache> arrayList2 = getTachesOfListe(i);
        if(arrayList2.size() == 0){
            return 100;
        }
        int a = arrayList.size();
        int b = arrayList2.size();
        return (a/b)*100;
    }

    private class FluxViewHolder {
        TextView pseudo;
        TextView text;
        ImageView avatar;
        ProgressBar progressBar;
        TextView pourcent;
    }

}
