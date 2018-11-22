package univ.etu.tachemun;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import univ.etu.tachemun.FluxListeTache.FluxTaches;
import univ.etu.tachemun.FluxListeTache.FluxTachesAdapter;
import univ.etu.tachemun.db.tableclass.Tache;
import univ.etu.tachemun.db.tablemanagers.TacheManager;

public class AffListeTache extends AppCompatActivity {

    private ListView listView;
    private ListView listView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aff_liste_tache);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.setTitle(getIntent().getStringExtra("NOM_LISTETACHE"));
        Toolbar toolbar1 = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(couleur(getIntent().getIntExtra("COULEUR_LISTETACHE",0)));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AffListeTache.this,CreationTache.class);
                intent.putExtra("ID_LISTE", getIntent().getIntExtra("ID_LISTE", -1));
                intent.putExtra("ID_UTILISATEUR", getIntent().getStringExtra("ID_UTILISATEUR"));
                startActivity(intent);
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });

        geneListe();

    }

    private List<FluxTaches> gene(ArrayList<Tache> tacheArrayList) {
        List<FluxTaches> list = new ArrayList<FluxTaches>();
        for (int i = 0; i < tacheArrayList.size(); i++) {
            //TODO manque d'une colonne dans la bdd pour un boolean !
            FluxTaches fluxTaches = new FluxTaches(tacheArrayList.get(i).getLibelle(), false);
            //TODO reflechir commment faire pour la case a cocher.
            list.add(fluxTaches);
        }
        return list;
    }

    private ArrayList<Tache> getTacheOfListe() {
        TacheManager t = new TacheManager(AffListeTache.this);
        return t.getTachesNonRealiseesFromListe(getIntent().getIntExtra("ID_LISTE", -1));
    }

    private ArrayList<Tache> getTachesOfListe() {
        TacheManager t = new TacheManager(AffListeTache.this);
        return t.getAllTachesFromListe(getIntent().getIntExtra("ID_LISTE", -1));
    }

    private ArrayList<Tache> getTachesRealOfListe() {
        TacheManager t = new TacheManager(AffListeTache.this);
        return t.getTachesRealFromListe(getIntent().getIntExtra("ID_LISTE", -1));
    }

    private void geneListe() {
        final ArrayList<Tache> allTaches = getTachesOfListe();
        final int c = allTaches.size();
        final ArrayList<Tache> TachesN = getTacheOfListe();
        final int a = TachesN.size();
        ArrayList<Tache> TachesR = getTachesRealOfListe();
        final int d = TachesR.size();

        TextView textView;
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layoutafflistetache);

        if (TachesN == null || TachesN.size() == 0) {
            textView = new TextView(this);
            textView.setText(R.string.liste_listetache_no_taches);
//            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
//            textView.setLayoutParams(lp);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            linearLayout.addView(textView);
        } else {
            listView = new ListView(this);
            List<FluxTaches> list = gene(TachesN);
            FluxTachesAdapter adapter = new FluxTachesAdapter(this, list);
            listView.setAdapter(adapter);
            //TODO faire les clicks(si besoin)
            //les interractions

            TextView textView1 = new TextView(this);
            textView1.setText("Taches a faire");
            textView1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textView1.setTextSize(25);
            linearLayout.addView(textView1);
            linearLayout.addView(listView);

            TextView textView3 = new TextView(this);
            textView3.setText("Taches effectuees");
            textView3.setTextSize(25);
            textView3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            linearLayout.addView(textView3);
            if (TachesR == null || TachesR.size() == 0) {

                TextView textView4 = new TextView(this);
                textView4.setText("Aucune tache effectuee");
                textView4.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                linearLayout.addView(textView4);
            } else {
                listView2 = new ListView(this);
                List<FluxTaches> list1 = gene(TachesR);
                FluxTachesAdapter adapter1 = new FluxTachesAdapter(this, list1);
                listView2.setAdapter(adapter1);

                linearLayout.addView(listView2);
            }
        }
    }

    private int couleur(int i){
        int color = Color.argb(255, 255, 0, 0);
        switch (i) {
            case 0:
                color = Color.argb(255, 0, 0, 0);
                break;
            case 1:
                color = Color.argb(255, 64, 64, 64);
                break;
            case 2:
                color = Color.argb(255, 255, 0, 0);
                break;
            case 3:
                color = Color.argb(255, 255, 106, 0);
                break;
            case 4:
                color = Color.argb(255, 255, 255, 0);
                break;
            case 5:
                color = Color.argb(255, 0, 255, 0);
                break;
            case 6:
                color = Color.argb(255, 0, 255, 255);
                break;
            case 7:
                color = Color.argb(255, 0, 148, 255);
                break;
            case 8:
                color = Color.argb(255, 0, 0, 255);
                break;
            case 9:
                color = Color.argb(255, 178, 0, 255);
                break;
            case 10:
                color = Color.argb(255, 255, 0, 255);
                break;
        }
        return color;
    }

}
