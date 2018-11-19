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
                startActivity(intent);
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });

        ArrayList<Tache> Taches = getTacheOfListe();
        TextView textView;
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layoutafflistetache);

        if (Taches == null || Taches.size() == 0) {
            textView = new TextView(this);
            textView.setText(R.string.liste_listetache_no_taches);
//            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
//            textView.setLayoutParams(lp);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            linearLayout.addView(textView);
        }
        else{
            listView = new ListView(this);
            List<FluxTaches> list = gene(Taches);
            FluxTachesAdapter adapter = new FluxTachesAdapter(this, list);
            listView.setAdapter(adapter);
            //TODO faire les clicks(si besoin)
            //les interractions
            linearLayout.addView(listView);
        }
    }

    private List<FluxTaches> gene(ArrayList<Tache> tacheArrayList) {
        List<FluxTaches> list = new ArrayList<FluxTaches>();
        for (int i = 0; i < tacheArrayList.size(); i++) {
            //TODO manque d'une colonne dans la bdd pour un boolean !
            FluxTaches fluxTaches = new FluxTaches(tacheArrayList.get(i).getDescription(), false);
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
