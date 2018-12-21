package univ.etu.tachemun;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import univ.etu.tachemun.FluxListeListe.Flux;
import univ.etu.tachemun.FluxListeListe.FluxAdapter;
import univ.etu.tachemun.db.tableclass.ActionUser;
import univ.etu.tachemun.db.tableclass.ListeTache;
import univ.etu.tachemun.db.tableclass.Tache;
import univ.etu.tachemun.db.tablemanagers.ActionUserManager;
import univ.etu.tachemun.db.tablemanagers.ListeTacheManager;
import univ.etu.tachemun.db.tablemanagers.TacheManager;

public class ListeListeTaches extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView listView;
    private TextView textView;
    private LinearLayout linearLayout;
    private ArrayList<ListeTache> listeTaches;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listeliste);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

                Intent intent = new Intent(ListeListeTaches.this, NouvelleListeDeTache.class);
                intent.putExtra("ID_UTILISATEUR", getIntent().getStringExtra("ID_UTILISATEUR"));

                //startActivity(intent);

                startActivityForResult(intent, 0);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //View viewInflated = LayoutInflater.from(ListeListeTaches.this).inflate(R.layout.nav_header_listeliste, (ViewGroup) view, false);

        //TextView Pseudo = (TextView) findViewById(R.id.YNAME);
        //Pseudo.setText(getIntent().getStringExtra("ID_UTILISATEUR"));

        //affichage de la liste

        affichage(0);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.listeliste, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(ListeListeTaches.this,Parametres.class);
            startActivity(intent);
            return true;
        }
        if(id == R.id.action_deco){
            Intent intent = new Intent(ListeListeTaches.this,Connexion.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.nav_camera:
                break;

            /*
            case R.id.nav_gallery:
                // Handle the camera action
                break;
            case R.id.nav_slideshow:
                break;
            case R.id.nav_manage:
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_send:
                break;*/

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private ArrayList<ListeTache> recupListeListe(){
        ListeTacheManager lm = new ListeTacheManager(ListeListeTaches.this);
        return lm.getListesOfUser(getIntent().getStringExtra("ID_UTILISATEUR"));
    }

    private void deletElementListeListe(ListeTache liste) {
        ListeTacheManager lm = new ListeTacheManager(ListeListeTaches.this);
        ActionUserManager actionUserManager = new ActionUserManager(ListeListeTaches.this);
        actionUserManager.insertNew(new ActionUser(getIntent().getStringExtra("ID_UTILISATEUR"), "SUPPRESSION DE LISTE : " + liste.getNom()));
        lm.delete(liste);
    }

    private List<Flux> geneListe(ArrayList<ListeTache> listeTaches) {
        List<Flux> listflux = new ArrayList<Flux>();
        for (int i = 0; i < listeTaches.size(); i++) {
            int color = Color.argb(255, 0, 0, 0);
            switch (listeTaches.get(i).getCouleur()) {
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
            listflux.add(new Flux(color, listeTaches.get(i).getNom(), listeTaches.get(i).getDescription()));
        }
        return listflux;
    }

    //retours de nouvelle tache
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == 0) {
                //rien ce passe
            } else {
                //actualisation
                textView = (TextView) findViewById(R.id.textmessagepasdelisteliste);


                listView = (ListView) findViewById(R.id.listeListeView);
                listeTaches = recupListeListe();
                if (listeTaches.isEmpty()) {
                    textView.setVisibility(View.VISIBLE);
                } else {
                    textView.setVisibility(View.INVISIBLE);
                }

                List<Flux> listflux = geneListe(listeTaches);
                FluxAdapter adapter = new FluxAdapter(this, listflux,listeTaches);
                listView.setAdapter(adapter);
                list(listView);
            }
        }
    }


    //Menu de deconnexion + option
    public boolean OnCreateOptionMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.listeliste, menu);
        return true;
    }

    public boolean OnOptionItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_deco) {
            Intent intent = new Intent(ListeListeTaches.this, Connexion.class);
            ActionUserManager a = new ActionUserManager(ListeListeTaches.this);
            a.insertNew(new ActionUser(getIntent().getStringExtra("ID_UTILISATEUR"), "DECONEXION"));
            startActivity(intent);
            finish();
        }
        if (id == R.id.action_settings) {

        }

        Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(item);
    }

    //affichage et actualisation
    public void affichage(int a){

        //affichage listeliste
        linearLayout = (LinearLayout) findViewById(R.id.layoutprincipal);
        listeTaches = recupListeListe();

        textView =new TextView(this);
        textView.setId(R.id.textmessagepasdelisteliste);
        textView.setText(R.string.liste_listestache_no_lists);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        linearLayout.addView(textView);

        listView = new ListView(this);
        listView.setId(R.id.listeListeView);
        linearLayout.addView(listView);

        if (listeTaches.isEmpty()) {
            textView.setVisibility(View.VISIBLE);
        }
        else{
            textView.setVisibility(View.INVISIBLE);

            listView.setId(R.id.listeListeView);
            final List<Flux> listflux = geneListe(listeTaches);
            final FluxAdapter adapter = new FluxAdapter(this, listflux,listeTaches);
            listView.setAdapter(adapter);
            list(listView);
        }
    }

    private void list(ListView l){
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listeTaches = recupListeListe();
                Intent intent = new Intent(ListeListeTaches.this, AffListeTache.class);
                intent.putExtra("ID_UTILISATEUR",getIntent().getStringExtra("ID_UTILISATEUR"));
                intent.putExtra("ID_LISTE", listeTaches.get(i).getID());
                intent.putExtra("NOM_LISTETACHE",listeTaches.get(i).getNom());
                intent.putExtra("COULEUR_LISTETACHE",listeTaches.get(i).getCouleur());
                startActivity(intent);
            }
        });
        l.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListeListeTaches.this);
                builder.setMessage("Voulez-vous supprimez la liste de tâche ?");
                builder.setCancelable(true);
                builder.setPositiveButton("Supprimer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Supprime",
                                Toast.LENGTH_LONG).show();
                        deletElementListeListe(listeTaches.get(position));
                        listeTaches = recupListeListe();

                            /*if(listeTaches.size() != 0){
                                ArrayList<Tache> list = getTachesOfListe(listeTaches.get(position).getID());
                                for(int i = 0;i<list.size();i++) {
                                    supprTache(list.get(i));
                                }
                            }*/
                        ArrayList<ListeTache> listeTaches2 = recupListeListe();
                        List<Flux> listflux2 = geneListe(listeTaches2);
                        FluxAdapter adapter2 = new FluxAdapter(ListeListeTaches.this, listflux2,listeTaches);
                        listView.setAdapter(adapter2);
                        //finish();

                        if (listeTaches2.isEmpty()) {
                            textView.setVisibility(View.VISIBLE);
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
        TacheManager tacheManager = new TacheManager(ListeListeTaches.this);
        tacheManager.delete(t);
    }

}
