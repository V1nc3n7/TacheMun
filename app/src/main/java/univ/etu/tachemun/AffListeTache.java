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

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;

    private ArrayList<Tache> allTaches;
    private ArrayList<Tache> TachesN;
    private ArrayList<Tache> TachesR;

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
                startActivityForResult(intent, 0);
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });

        geneListe();

        /*
        ArrayList<Tache> Taches = getTacheOfListe();
        System.out.println(Taches.toString());
        TextView textView;
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layoutafflistetache);

        if (Taches == null || Taches.size() == 0) {
            textView = new TextView(this);
            textView.setText(R.string.liste_listetache_no_taches);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            linearLayout.addView(textView);
        } else {
            listView = new ListView(this);
            List<FluxTaches> list = gene(Taches);
            FluxTachesAdapter adapter = new FluxTachesAdapter(this, list);
            listView.setAdapter(adapter);
            //les interractions
            linearLayout.addView(listView);
        }
        */
    }

    private List<FluxTaches> gene(ArrayList<Tache> tacheArrayList, int val) {
        List<FluxTaches> list = new ArrayList<FluxTaches>();
        for (int i = 0; i < tacheArrayList.size(); i++) {
            FluxTaches fluxTaches = null;
            if (val == 0) {
                fluxTaches = new FluxTaches(tacheArrayList.get(i).getLibelle(), false);
            } else {
                fluxTaches = new FluxTaches(tacheArrayList.get(i).getLibelle(), true);
            }

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

    //retours de nouvelle tache
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == 0) {
                //rien ce passe
            } else {
                //actualisation
                textView1 = (TextView) findViewById(R.id.textmessagepasdelistet1);
                textView2 = (TextView) findViewById(R.id.textmessagepasdelistet2);
                textView3 = (TextView) findViewById(R.id.textmessagepasdelistet3);
                textView4 = (TextView) findViewById(R.id.textmessagepasdelistet4);


                listView = (ListView) findViewById(R.id.listetacheAF);
                listView2 = (ListView) findViewById(R.id.listetacheF);
                TachesN = getTacheOfListe();
                TachesR = getTachesRealOfListe();

                if (TachesN.size() == 0) {
                    textView2.setVisibility(View.VISIBLE);
                } else {
                    textView2.setVisibility(View.INVISIBLE);
                }

                if (TachesR.size() == 0) {
                    textView4.setVisibility(View.VISIBLE);
                } else {
                    textView4.setVisibility(View.INVISIBLE);
                }

                List<FluxTaches> listflux = gene(TachesN, 0);
                FluxTachesAdapter adapter = new FluxTachesAdapter(this, listflux);
                listView.setAdapter(adapter);

                List<FluxTaches> listflux2 = gene(TachesR, 1);
                FluxTachesAdapter adapter2 = new FluxTachesAdapter(this, listflux2);
                listView2.setAdapter(adapter2);

            }
        }
    }

    private void geneListe() {
        allTaches = getTachesOfListe();
        final int c = allTaches.size();
        TachesN = getTacheOfListe();
        final int a = TachesN.size();
        TachesR = getTachesRealOfListe();
        final int d = TachesR.size();

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layoutafflistetache);

        textView1 = new TextView(this);
        textView1.setText("Taches a faire");
        textView1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView1.setTextSize(25);
        textView1.setId(R.id.textmessagepasdelistet1);
        linearLayout.addView(textView1);

        textView2 = new TextView(this);
        textView2.setText(R.string.liste_listetache_no_taches);
        textView2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView2.setId(R.id.textmessagepasdelistet2);
        linearLayout.addView(textView2);

        listView = new ListView(this);
        listView.setId(R.id.listetacheAF);
        linearLayout.addView(listView);

        textView3 = new TextView(this);
        textView3.setText("Taches effectuees");
        textView3.setTextSize(25);
        textView3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView3.setId(R.id.textmessagepasdelistet3);
        linearLayout.addView(textView3);


        textView4 = new TextView(this);
        textView4.setText("Aucune tache effectuee");
        textView4.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView4.setId(R.id.textmessagepasdelistet4);
        linearLayout.addView(textView4);

        listView2 = new ListView(this);
        listView2.setId(R.id.listetacheF);
        linearLayout.addView(listView2);



        if (TachesN == null || TachesN.size() == 0) {
            textView2.setVisibility(View.VISIBLE);
        } else {
            textView2.setVisibility(View.INVISIBLE);

            List<FluxTaches> list = gene(TachesN, 0);
            FluxTachesAdapter adapter = new FluxTachesAdapter(this, list);
            listView.setAdapter(adapter);



            if (TachesR == null || TachesR.size() == 0) {
                textView4.setVisibility(View.VISIBLE);
            } else {
                textView4.setVisibility(View.INVISIBLE);

                List<FluxTaches> list1 = gene(TachesR, 1);
                FluxTachesAdapter adapter1 = new FluxTachesAdapter(this, list1);
                listView2.setAdapter(adapter1);
            }
        }
    }

    private void supprTache(Tache t) {
        TacheManager tacheManager = new TacheManager(AffListeTache.this);
        tacheManager.delete(t);
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
