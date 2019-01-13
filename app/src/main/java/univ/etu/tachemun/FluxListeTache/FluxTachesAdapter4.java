package univ.etu.tachemun.FluxListeTache;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import univ.etu.tachemun.R;
import univ.etu.tachemun.db.tableclass.RealiseTache;
import univ.etu.tachemun.db.tableclass.Tache;
import univ.etu.tachemun.db.tablemanagers.RealiseTacheManager;
import univ.etu.tachemun.db.tablemanagers.TacheManager;

public class FluxTachesAdapter4 extends BaseAdapter implements ListAdapter {
    private ArrayList<Tache> list = new ArrayList<Tache>();
    private Context context;
    private int idListe;
    private ListView LN;
    private ListView LR;
    private TextView TN;
    private TextView TR;

    public FluxTachesAdapter4(ArrayList<Tache> list, Context context, int idListe, ListView LN, ListView LR, TextView TN, TextView TR) {
        this.list = list;
        this.context = context;
        this.idListe = idListe;
        this.LN = LN;
        this.LR = LR;
        this.TN = TN;
        this.TR = TR;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).getID();
    }

    @Override
    public View getView(final int i, View convertview, ViewGroup viewGroup) {
        View view = convertview;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.tache_view_check, null);
        }

        TextView textView = (TextView) view.findViewById(R.id.TEXT_TACHE);
        textView.setText(list.get(i).getLibelle());

        ImageView imageView = (ImageView) view.findViewById(R.id.check);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "click " + i,
                        Toast.LENGTH_LONG).show();
                supprTache(list.get(i));
                rswitchTache(i);

                LN.setAdapter(new FluxTachesAdapter3(getTacheOfListe(), context, idListe, LN, LR, TN, TR));
                LR.setAdapter(new FluxTachesAdapter4(getTachesRealOfListe(), context, idListe, LN, LR, TN, TR));
                if (getTacheOfListe().size() == 0) {
                    TN.setVisibility(View.VISIBLE);
                } else {
                    TN.setVisibility(View.INVISIBLE);
                }
                if (getTachesRealOfListe().size() == 0) {
                    TR.setVisibility(View.VISIBLE);
                } else {
                    TR.setVisibility(View.INVISIBLE);
                }

            }
        });

        TextView date = (TextView) view.findViewById(R.id.DATE_TACHE);
        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy\nHH:mm:ss");
        if (list.get(i).getDateHeureEcheance() == null) {
            date.setText("");
        } else {
            date.setText(df.format(list.get(i).getDateHeureEcheance()));
        }
        return view;
    }

    private void supprTache(Tache t) {
        TacheManager tacheManager = new TacheManager(context);
        tacheManager.delete(t);
    }

    private void switchTache(Tache t, int i) {
        RealiseTacheManager realiseTacheManager = new RealiseTacheManager(context);
        realiseTacheManager.insert(new RealiseTache(list.get(i).getCreateur(), list.get(i).getID(), ""));
    }

    private ArrayList<Tache> getTacheOfListe() {
        TacheManager t = new TacheManager(context);
        return t.getTachesNonRealiseesFromListe(idListe);
    }

    private ArrayList<Tache> getTachesRealOfListe() {
        TacheManager t = new TacheManager(context);
        return t.getTachesRealFromListe(idListe);
    }

    private void rswitchTache(int i) {
        TacheManager tacheManager = new TacheManager(context);
        tacheManager.insert(list.get(i));
        //list.get(i).setEcheance(list.get(i).getDateHeureEcheance() != null);
        //tacheManager.update(list.get(i));
    }
}
