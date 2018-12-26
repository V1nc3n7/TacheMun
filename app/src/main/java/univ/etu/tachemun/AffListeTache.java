package univ.etu.tachemun;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import univ.etu.tachemun.FluxListeTache.FluxTaches;
import univ.etu.tachemun.FluxListeTache.FluxTachesAdapter3;
import univ.etu.tachemun.FluxListeTache.FluxTachesAdapter4;
import univ.etu.tachemun.db.tableclass.Tache;
import univ.etu.tachemun.db.tablemanagers.ListeTacheManager;
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
                ListeTacheManager listeTacheManager = new ListeTacheManager(AffListeTache.this);
                int faites = listeTacheManager.getDoneAndSizeOfList(getIntent().getIntExtra("ID_LISTE", -1)).first;
                int toutestaches = listeTacheManager.getDoneAndSizeOfList(getIntent().getIntExtra("ID_LISTE", -1)).second;

                int todo = toutestaches - faites;
                Log.i(getClass().getName(), "ToDo " + todo + ", done " + faites + " toutes " + toutestaches);

                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });

        geneListe();
    }

    private List<FluxTaches> gene(ArrayList<Tache> tacheArrayList, int val) {
        List<FluxTaches> list = new ArrayList<FluxTaches>();
        for (int i = 0; i < tacheArrayList.size(); i++) {
            FluxTaches fluxTaches = null;
            if (val == 0) {
                //fluxTaches = new FluxTaches(tacheArrayList.get(i).getLibelle(), false);
                fluxTaches = new FluxTaches(tacheArrayList.get(i).getLibelle());
            } else {
                //fluxTaches = new FluxTaches(tacheArrayList.get(i).getLibelle(), true);
                fluxTaches = new FluxTaches(tacheArrayList.get(i).getLibelle());
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
                list(listView,listView2);
                TachesN = getTacheOfListe();
                TachesR = getTachesRealOfListe();

                if (TachesN.isEmpty()) {
                    textView2.setVisibility(View.VISIBLE);
                } else {
                    textView2.setVisibility(View.INVISIBLE);
                }

                if (TachesR.isEmpty()) {
                    textView4.setVisibility(View.VISIBLE);
                } else {
                    textView4.setVisibility(View.INVISIBLE);
                }

                //List<FluxTaches> listflux = gene(TachesN, 0);
                //FluxTachesAdapter adapter = new FluxTachesAdapter(this, listflux);

                FluxTachesAdapter3 adapter3 = new FluxTachesAdapter3(TachesN,this,getIntent().getIntExtra("ID_LISTE", -1),listView,listView2,textView2,textView4);

                listView.setAdapter(adapter3);

                //List<FluxTaches> listflux2 = gene(TachesR, 1);
                //FluxTachesAdapter adapter2 = new FluxTachesAdapter(this, listflux2);

                FluxTachesAdapter4 adapter4 = new FluxTachesAdapter4(TachesR,this,getIntent().getIntExtra("ID_LISTE", -1),listView,listView2,textView2,textView4);

                listView2.setAdapter(adapter4);

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

        LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.layoutafflistetache1);
        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.layoutafflistetache2);

        //DisplayMetrics metrics = new DisplayMetrics();
        //getWindowManager().getDefaultDisplay().getMetrics(metrics);

        //Log.d(TAG, "largeur : " + metrics.widthPixels);
        //Log.d(TAG, "hauteur : " + metrics.heightPixels);

        textView1 = new TextView(this);
        textView1.setText("Taches a faire");
        textView1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView1.setTextSize(25);
        textView1.setId(R.id.textmessagepasdelistet1);
        linearLayout1.addView(textView1);

        textView2 = new TextView(this);
        textView2.setText(R.string.liste_listetache_no_taches);
        textView2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView2.setId(R.id.textmessagepasdelistet2);
        linearLayout1.addView(textView2);

        listView = new ListView(this);
        listView.setId(R.id.listetacheAF);
        listView.setPadding(10,10,10,20);
        linearLayout1.addView(listView);

        textView3 = new TextView(this);
        textView3.setText("Taches effectuees");
        textView3.setTextSize(25);
        textView3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView3.setId(R.id.textmessagepasdelistet3);
        linearLayout2.addView(textView3);


        textView4 = new TextView(this);
        textView4.setText("Aucune tache effectuee");
        textView4.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView4.setId(R.id.textmessagepasdelistet4);
        linearLayout2.addView(textView4);

        listView2 = new ListView(this);
        listView2.setId(R.id.listetacheF);
        listView2.setPadding(10,10,10,10);
        linearLayout2.addView(listView2);

        //Toast.makeText(this, "listN "+ getTacheOfListe().size()+", listR "+ getTachesRealOfListe().size(), Toast.LENGTH_LONG).show();
        list(listView,listView2);


        if (TachesN.isEmpty() && TachesR.isEmpty()) {
            textView2.setVisibility(View.VISIBLE);
        } else {
            if (TachesN.isEmpty()) {
                textView2.setVisibility(View.VISIBLE);
            }
            else{
                textView2.setVisibility(View.INVISIBLE);
            }
            //final List<FluxTaches> list = gene(TachesN, 0);
            //final FluxTachesAdapter adapter = new FluxTachesAdapter(this, list);

            FluxTachesAdapter3 adapter3 = new FluxTachesAdapter3(TachesN,this,getIntent().getIntExtra("ID_LISTE", -1),listView,listView2,textView2,textView4);

            listView.setAdapter(adapter3);



            if (TachesR.isEmpty()) {
                textView4.setVisibility(View.VISIBLE);
            } else {
                textView4.setVisibility(View.INVISIBLE);

                //List<FluxTaches> list1 = gene(TachesR, 1);
                //FluxTachesAdapter adapter1 = new FluxTachesAdapter(this, list1);

                FluxTachesAdapter4 adapter4 = new FluxTachesAdapter4(TachesR,this,getIntent().getIntExtra("ID_LISTE", -1),listView,listView2,textView2,textView4);

                listView2.setAdapter(adapter4);

            }
        }
    }

    public void list(ListView l1, ListView l2){
        //l1
        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                AlertDialog.Builder builder = new AlertDialog.Builder(AffListeTache.this);
                builder.setTitle("Resumé tâche :");
                View viewInflated = LayoutInflater.from(AffListeTache.this).inflate(R.layout.tache_view_compl, (ViewGroup) view, false);
                EditText t = (EditText) viewInflated.findViewById(R.id.NOM_T);
                EditText d = (EditText) viewInflated.findViewById(R.id.DESCRIPTION_T);
                TachesN = getTacheOfListe();
                t.setText(TachesN.get(i).getLibelle());
                d.setText(TachesN.get(i).getDescription());
                builder.setView(viewInflated);
                builder.setCancelable(true);
                builder.setPositiveButton("modifié", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "modifié",
                                Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("Retour", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "retour",
                                Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        l1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AffListeTache.this);
                builder.setMessage("Voulez-vous supprimez la tâche ?");
                builder.setCancelable(true);
                builder.setPositiveButton("Supprimer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Supprime",
                                Toast.LENGTH_LONG).show();
                        TachesN = getTacheOfListe();
                        supprTache(TachesN.get(i));

                        TachesN = getTacheOfListe();
                        FluxTachesAdapter3 adapter3 = new FluxTachesAdapter3(TachesN,AffListeTache.this,getIntent().getIntExtra("ID_LISTE", -1),listView,listView2,textView2,textView4);

                        listView.setAdapter(adapter3);

                        if (TachesN.isEmpty()) {
                            textView2.setVisibility(View.VISIBLE);
                        }
                    }
                });
                builder.setNegativeButton("Retour", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "retour",
                                Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });

        //l2
        l2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                AlertDialog.Builder builder = new AlertDialog.Builder(AffListeTache.this);
                builder.setTitle("Resumé tâche :");
                View viewInflated = LayoutInflater.from(AffListeTache.this).inflate(R.layout.tache_view_compl, (ViewGroup) view, false);
                EditText t = (EditText) viewInflated.findViewById(R.id.NOM_T);
                EditText d = (EditText) viewInflated.findViewById(R.id.DESCRIPTION_T);
                TachesR = getTachesRealOfListe();
                t.setText(TachesR.get(i).getLibelle());
                d.setText(TachesR.get(i).getDescription());
                builder.setView(viewInflated);
                builder.setCancelable(true);
                builder.setPositiveButton("modifié", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "modifié",
                                Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("Retour", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "retour",
                                Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        l2.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AffListeTache.this);
                builder.setMessage("Voulez-vous supprimez la tâche ?");
                builder.setCancelable(true);
                builder.setPositiveButton("Supprimer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Supprime",
                                Toast.LENGTH_LONG).show();
                        TachesR = getTachesRealOfListe();
                        supprTache(TachesR.get(i));

                        TachesR = getTachesRealOfListe();

                        FluxTachesAdapter4 adapter4 = new FluxTachesAdapter4(TachesR,AffListeTache.this,getIntent().getIntExtra("ID_LISTE", -1),listView,listView2,textView2,textView4);

                        listView2.setAdapter(adapter4);

                        if (TachesR.isEmpty()) {
                            textView4.setVisibility(View.VISIBLE);
                        }
                    }
                });
                builder.setNegativeButton("Retour", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "retour",
                                Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });
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
