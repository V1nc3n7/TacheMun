package univ.etu.tachemun;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import univ.etu.tachemun.FluxListeListe.Flux;
import univ.etu.tachemun.FluxListeListe.FluxAdapter;
import univ.etu.tachemun.TimeDate.DatePickerFragment;
import univ.etu.tachemun.TimeDate.TimePickerFragment;
import univ.etu.tachemun.db.tableclass.ActionUser;
import univ.etu.tachemun.db.tableclass.ListeTache;
import univ.etu.tachemun.db.tableclass.Tache;
import univ.etu.tachemun.db.tablemanagers.ActionUserManager;
import univ.etu.tachemun.db.tablemanagers.ListeTacheManager;
import univ.etu.tachemun.db.tablemanagers.TacheManager;

import static univ.etu.tachemun.Appnoti.CHANNEL_1_ID;
import static univ.etu.tachemun.Appnoti.CHANNEL_2_ID;

public class ListeListeTaches extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView listView;
    private TextView textView;
    private LinearLayout linearLayout;
    private ArrayList<ListeTache> listeTaches;

    private NotificationManagerCompat notificationManager;
    private int choixCouleur;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listeliste);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        notificationManager = NotificationManagerCompat.from(this);
        sendOnChannel1();

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
    protected void onResume() {
        super.onResume();
        textView = (TextView) findViewById(R.id.textmessagepasdelisteliste);


        listView = (ListView) findViewById(R.id.listeListeView);
        listeTaches = recupListeListe();
        if (listeTaches.isEmpty()) {
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.INVISIBLE);
        }

        List<Flux> listflux = geneListe(listeTaches);
        FluxAdapter adapter = new FluxAdapter(this, listflux, listeTaches);
        listView.setAdapter(adapter);
        list(listView);
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
            intent.putExtra("deco",1);
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
        Intent go;
        switch (item.getItemId()) {

            case R.id.nav_accueil:
                //Log.i(this.getClass().getName(), "Go accueil");
                go = new Intent(ListeListeTaches.this, ListeListeTaches.class);
                go.putExtra("ID_UTILISATEUR", getIntent().getStringExtra("ID_UTILISATEUR"));
                startActivity(go);
                finish();
                break;
            case R.id.nav_rech_groupe:
                //Log.i(this.getClass().getName(), "Go rechGroupe");
                break;
            case R.id.nav_groupe:
                break;
            case R.id.nav_new_groupe:
                //Log.i(this.getClass().getName(), "Go new Groupe");
                go = new Intent(ListeListeTaches.this, CreationGroupe.class);
                go.putExtra("ID_UTILISATEUR", getIntent().getStringExtra("ID_UTILISATEUR"));
                startActivity(go);
                break;


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
                    color = Color.argb(150, 255, 255, 0);
                    break;
                case 5:
                    color = Color.argb(150, 0, 255, 0);
                    break;
                case 6:
                    color = Color.argb(150, 0, 255, 255);
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
                    color = Color.argb(150, 255, 0, 255);
                    break;
            }

            DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy\nHH:mm:ss");
            Date date = listeTaches.get(i).getDateHeureEcheance();
            String d = "";
            if (date == null) {
                d = "";
            } else {
                d = df.format(date.getTime());
            }
            listflux.add(new Flux(color, listeTaches.get(i).getNom(), listeTaches.get(i).getDescription(), d));
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
                final View v = view;
                AlertDialog.Builder builder = new AlertDialog.Builder(ListeListeTaches.this);
                View viewInflated = LayoutInflater.from(ListeListeTaches.this).inflate(R.layout.listetache_view_compl, (ViewGroup) view, false);
                Button button = (Button) viewInflated.findViewById(R.id.modifliste);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        modiflist(v,position);
                    }
                });

                builder.setView(viewInflated);
                builder.setCancelable(true);//ajouter un bouton partage
                builder.setPositiveButton("Supprimer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Supprime",
                                Toast.LENGTH_LONG).show();
                        deletElementListeListe(listeTaches.get(position));
                        listeTaches = recupListeListe();
                        ArrayList<ListeTache> listeTaches2 = recupListeListe();
                        List<Flux> listflux2 = geneListe(listeTaches2);
                        FluxAdapter adapter2 = new FluxAdapter(ListeListeTaches.this, listflux2,listeTaches);
                        listView.setAdapter(adapter2);

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
                builder.setNeutralButton("Partager", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        partageListe(listeTaches.get(position));
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });
    }

    private void partageListe(ListeTache listeTache) {
        Intent i = new Intent(ListeListeTaches.this, PartageListeActivity.class);
        i.putExtra("ID_UTILISATEUR", getIntent().getStringExtra("ID_UTILISATEUR"));
        i.putExtra("ID_LISTETACHE", listeTache.getID());
        startActivity(i);
    }

    private void supprTache(Tache t) {
        TacheManager tacheManager = new TacheManager(ListeListeTaches.this);
        tacheManager.delete(t);
    }

    public void sendOnChannel1(){
        Intent intent = new Intent(this,ListeListeTaches.class);

        PendingIntent intent1 = PendingIntent.getBroadcast(this,0,intent,0);


        listeTaches = recupListeListe();
        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy\nHH:mm:ss");
        //ArrayList<String> message = new ArrayList<>();
        String message = "";
        String message2 = "";
        String messagef = "";
        Date d = new Date();
        d.setMonth(d.getMonth()+1);
        Date dactu = new Date();

        message += "Echéance(s) dépassée(s) : \n";
        for(int i = 0 ; i<listeTaches.size();i++){
            if(listeTaches.get(i).getDateHeureEcheance() != null) {
                if (listeTaches.get(i).getDateHeureEcheance().before(dactu)) {
                    double a = getTachesOfListe(i).size();
                    double b = getTachesRealOfListe(i).size();
                    double c = 0;

                    if(a == 0){
                        c = 100;
                    }
                    else{
                        c = (b/a)*100;
                    }
                    if(c != 100){
                        message += "" + listeTaches.get(i).getNom() + "\n" + "Se terminait le : " + df.format(listeTaches.get(i).getDateHeureEcheance()) + "\n\n";
                    }

                }
            }
        }

        if(message.equals("Echéance(s) dépassée(s) : \n")){
            message = "";
        }

        message2 += "Echéance(s) dans un mois : \n";
        for(int i = 0 ; i<listeTaches.size();i++){
            if(listeTaches.get(i).getDateHeureEcheance() != null) {
                if (listeTaches.get(i).getDateHeureEcheance().before(d) && listeTaches.get(i).getDateHeureEcheance().after(dactu)) {
                    double a = getTachesOfListe(i).size();
                    double b = getTachesRealOfListe(i).size();
                    double c = 0;

                    if(a == 0){
                        c = 100;
                    }
                    else{
                        c = (b/a)*100;
                    }
                    if(c != 100) {
                        message2 += "" + listeTaches.get(i).getNom() + "\n" + "Se termine le : " + df.format(listeTaches.get(i).getDateHeureEcheance()) + "\n\n";
                    }
                }
            }
        }

        if(message2.equals("Echéance(s) dans un mois : \n")){
            message2 = "";
        }

        /*
        double a = getTachesOfListe(position).size();
        double b = getTachesRealOfListe(position).size();
        double c = 0;

        if(a == 0){
            c = 100;
        }
        else{
            c = (b/a)*100;
        }*/

        if(message.equals("") == false){
            Notification notification = new NotificationCompat.Builder(this,CHANNEL_1_ID)
                    .setSmallIcon(R.drawable.ic_rappelle)
                    .setContentTitle("Vos échéances de date sont dépasées ")
                    .setContentText("Echéance ...")
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setCategory(NotificationCompat.CATEGORY_ALARM)
                    .setContentIntent(intent1)
                    /*.setStyle(new NotificationCompat.MessagingStyle("...")
                        .addMessage(message))*/
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(message))
                    .build();
            notificationManager.notify(1,notification);
        }
        if(message2.equals("") == false){
            Notification notification = new NotificationCompat.Builder(this,CHANNEL_2_ID)
                    .setSmallIcon(R.drawable.ic_rappelle)
                    .setContentTitle("Vos listes de tâches arrivent à échéance : ")
                    .setContentText("Echéance ...")
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_ALARM)
                    .setContentIntent(intent1)
                    /*.setStyle(new NotificationCompat.MessagingStyle("...")
                        .addMessage(message))*/
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(message2))
                    .build();
            notificationManager.notify(2,notification);
        }
    }

    private ArrayList<Tache> getTachesOfListe(int i) {
        TacheManager t = new TacheManager(this);
        return t.getAllTachesFromListe(listeTaches.get(i).getID());
    }

    private ArrayList<Tache> getTachesRealOfListe(int i) {
        TacheManager t = new TacheManager(this);
        return t.getTachesRealFromListe(listeTaches.get(i).getID());
    }

    private void modiflist(final View view, int i){
        listeTaches = recupListeListe();
        final int position = i;
        AlertDialog.Builder builder = new AlertDialog.Builder(ListeListeTaches.this);
        final View viewInflated = LayoutInflater.from(ListeListeTaches.this).inflate(R.layout.listetache_view_modif, (ViewGroup) view, false);
        creationImage(viewInflated,listeTaches.get(i).getCouleur());
        choixCouleur = listeTaches.get(i).getCouleur();

        final EditText nt = (EditText) viewInflated.findViewById(R.id.nomListTache);
        final EditText dest = (EditText) viewInflated.findViewById(R.id.nouvListeTache_descr_input);
        final Button date = (Button) viewInflated.findViewById(R.id.setdate);
        Button time = (Button) viewInflated.findViewById(R.id.settime);
        nt.setText(listeTaches.get(i).getNom());
        dest.setText(listeTaches.get(i).getDescription());

        if(listeTaches.get(i).getDateHeureCreation() != null){
            Date d = listeTaches.get(i).getDateHeureCreation();
            DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy");
            DateFormat dt = new SimpleDateFormat("HH:mm:ss");

            String da= df.format(d);
            String ti = dt.format(d);

            date.setText(da);
            time.setText(ti);
        }

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(viewInflated);
            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(viewInflated);
            }
        });


        builder.setView(viewInflated);
        builder.setPositiveButton("Modifier", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                TextView date = (TextView) viewInflated.findViewById(R.id.date);
                TextView heure = (TextView) viewInflated.findViewById(R.id.heure);
                Date e = new Date();
                if(date.getText().equals("0") == false){
                    e.setTime(Long.parseLong("" + date.getText()));
                    String a = (String) heure.getText();
                    String[] z = a.split(":");
                    String h = z[0];
                    String m = z[1];
                    e.setHours(Integer.parseInt(h));
                    e.setMinutes(Integer.parseInt(m));
                    e.setSeconds(0);
                }

                ListeTache listeTache = listeTaches.get(position);
                listeTache.setNom(nt.getText().toString());
                listeTache.setDescription(dest.getText().toString());
                listeTache.setCouleur(choixCouleur);
                if(date.getText().equals("0") == false){
                    listeTache.setDateHeureEcheance(e.getTime());
                    listeTache.setHasEcheance(true);
                }
                else{
                    listeTache.setHasEcheance(false);
                }

                ListeTacheManager listeTacheManager = new ListeTacheManager(ListeListeTaches.this);
                listeTacheManager.update(listeTache);
                onResume();

            }
        });
        builder.setNegativeButton("retour", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showTimePickerDialog(View v) {
        TextView heure = (TextView) v.findViewById(R.id.heure);
        Button timePicker = (Button) v.findViewById(R.id.settime);
        DialogFragment newFragment = new TimePickerFragment(timePicker, heure);
        newFragment.show(getSupportFragmentManager(), "timePicker");

    }

    public void showDatePickerDialog(View v) {
        TextView date = (TextView) v.findViewById(R.id.date);
        Button datePicker = (Button) v.findViewById(R.id.setdate);
        DialogFragment newFragment = new DatePickerFragment(datePicker, date);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private void creationImage(View v,int i) {
        LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.couleurs);
        //création image
        //noir
        final ImageView imageView0 = new ImageView(this);
        imageView0.setImageResource(R.drawable.carreblanc);
        imageView0.setClickable(true);
        imageView0.setColorFilter(Color.argb(255, 0, 0, 0));


        //gris
        final ImageView imageView1 = new ImageView(this);
        imageView1.setImageResource(R.drawable.carreblanc);
        imageView1.setClickable(true);
        imageView1.setColorFilter(Color.argb(255, 64, 64, 64));


        //rouge
        final ImageView imageView2 = new ImageView(this);
        imageView2.setImageResource(R.drawable.carreblanc);
        imageView2.setClickable(true);
        imageView2.setColorFilter(Color.argb(255, 255, 0, 0));


        //orange
        final ImageView imageView3 = new ImageView(this);
        imageView3.setImageResource(R.drawable.carreblanc);
        imageView3.setClickable(true);
        imageView3.setColorFilter(Color.argb(255, 255, 106, 0));


        //jaune
        final ImageView imageView4 = new ImageView(this);
        imageView4.setImageResource(R.drawable.carreblanc);
        imageView4.setClickable(true);
        imageView4.setColorFilter(Color.argb(50, 255, 255, 0));


        //vert
        final ImageView imageView5 = new ImageView(this);
        imageView5.setImageResource(R.drawable.carreblanc);
        imageView5.setClickable(true);
        imageView5.setColorFilter(Color.argb(50, 0, 255, 0));


        //tur
        final ImageView imageView6 = new ImageView(this);
        imageView6.setImageResource(R.drawable.carreblanc);
        imageView6.setClickable(true);
        imageView6.setColorFilter(Color.argb(50, 0, 255, 255));


        //bleu cobalte
        final ImageView imageView7 = new ImageView(this);
        imageView7.setImageResource(R.drawable.carreblanc);
        imageView7.setClickable(true);
        imageView7.setColorFilter(Color.argb(255, 0, 148, 255));


        //bleu
        final ImageView imageView8 = new ImageView(this);
        imageView8.setImageResource(R.drawable.carreblanc);
        imageView8.setClickable(true);
        imageView8.setColorFilter(Color.argb(255, 0, 0, 255));


        //violet
        final ImageView imageView9 = new ImageView(this);
        imageView9.setImageResource(R.drawable.carreblanc);
        imageView9.setClickable(true);
        imageView9.setColorFilter(Color.argb(255, 178, 0, 255));


        //magenta
        final ImageView imageView10 = new ImageView(this);
        imageView10.setImageResource(R.drawable.carreblanc);
        imageView10.setClickable(true);
        imageView10.setColorFilter(Color.argb(50, 255, 0, 255));

        //click image

        imageView0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choixCouleur = 0;
                //deselection autre image
                imageView0.setPadding(0, 0, 0, 0);
                imageView1.setPadding(0, 0, 0, 0);
                imageView2.setPadding(0, 0, 0, 0);
                imageView3.setPadding(0, 0, 0, 0);
                imageView4.setPadding(0, 0, 0, 0);
                imageView5.setPadding(0, 0, 0, 0);
                imageView6.setPadding(0, 0, 0, 0);
                imageView7.setPadding(0, 0, 0, 0);
                imageView8.setPadding(0, 0, 0, 0);
                imageView9.setPadding(0, 0, 0, 0);
                imageView10.setPadding(0, 0, 0, 0);
                //selection de l'image
                imageView0.setPadding(10, 10, 10, 10);

            }
        });

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choixCouleur = 1;
                //deselection autre image
                imageView0.setPadding(0, 0, 0, 0);
                imageView1.setPadding(0, 0, 0, 0);
                imageView2.setPadding(0, 0, 0, 0);
                imageView3.setPadding(0, 0, 0, 0);
                imageView4.setPadding(0, 0, 0, 0);
                imageView5.setPadding(0, 0, 0, 0);
                imageView6.setPadding(0, 0, 0, 0);
                imageView7.setPadding(0, 0, 0, 0);
                imageView8.setPadding(0, 0, 0, 0);
                imageView9.setPadding(0, 0, 0, 0);
                imageView10.setPadding(0, 0, 0, 0);
                //selection de l'image
                imageView1.setPadding(10, 10, 10, 10);
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choixCouleur = 2;
                //deselection autre image
                imageView0.setPadding(0, 0, 0, 0);
                imageView1.setPadding(0, 0, 0, 0);
                imageView2.setPadding(0, 0, 0, 0);
                imageView3.setPadding(0, 0, 0, 0);
                imageView4.setPadding(0, 0, 0, 0);
                imageView5.setPadding(0, 0, 0, 0);
                imageView6.setPadding(0, 0, 0, 0);
                imageView7.setPadding(0, 0, 0, 0);
                imageView8.setPadding(0, 0, 0, 0);
                imageView9.setPadding(0, 0, 0, 0);
                imageView10.setPadding(0, 0, 0, 0);
                //selection de l'image
                imageView2.setPadding(10, 10, 10, 10);
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choixCouleur = 3;
                //deselection autre image
                imageView0.setPadding(0, 0, 0, 0);
                imageView1.setPadding(0, 0, 0, 0);
                imageView2.setPadding(0, 0, 0, 0);
                imageView3.setPadding(0, 0, 0, 0);
                imageView4.setPadding(0, 0, 0, 0);
                imageView5.setPadding(0, 0, 0, 0);
                imageView6.setPadding(0, 0, 0, 0);
                imageView7.setPadding(0, 0, 0, 0);
                imageView8.setPadding(0, 0, 0, 0);
                imageView9.setPadding(0, 0, 0, 0);
                imageView10.setPadding(0, 0, 0, 0);
                //selection de l'image
                imageView3.setPadding(10, 10, 10, 10);
            }
        });

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choixCouleur = 4;
                //deselection autre image
                imageView0.setPadding(0, 0, 0, 0);
                imageView1.setPadding(0, 0, 0, 0);
                imageView2.setPadding(0, 0, 0, 0);
                imageView3.setPadding(0, 0, 0, 0);
                imageView4.setPadding(0, 0, 0, 0);
                imageView5.setPadding(0, 0, 0, 0);
                imageView6.setPadding(0, 0, 0, 0);
                imageView7.setPadding(0, 0, 0, 0);
                imageView8.setPadding(0, 0, 0, 0);
                imageView9.setPadding(0, 0, 0, 0);
                imageView10.setPadding(0, 0, 0, 0);
                //selection de l'image
                imageView4.setPadding(10, 10, 10, 10);
            }
        });

        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choixCouleur = 5;
                //deselection autre image
                imageView0.setPadding(0, 0, 0, 0);
                imageView1.setPadding(0, 0, 0, 0);
                imageView2.setPadding(0, 0, 0, 0);
                imageView3.setPadding(0, 0, 0, 0);
                imageView4.setPadding(0, 0, 0, 0);
                imageView5.setPadding(0, 0, 0, 0);
                imageView6.setPadding(0, 0, 0, 0);
                imageView7.setPadding(0, 0, 0, 0);
                imageView8.setPadding(0, 0, 0, 0);
                imageView9.setPadding(0, 0, 0, 0);
                imageView10.setPadding(0, 0, 0, 0);
                //selection de l'image
                imageView5.setPadding(10, 10, 10, 10);
            }
        });

        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choixCouleur = 6;
                //deselection autre image
                imageView0.setPadding(0, 0, 0, 0);
                imageView1.setPadding(0, 0, 0, 0);
                imageView2.setPadding(0, 0, 0, 0);
                imageView3.setPadding(0, 0, 0, 0);
                imageView4.setPadding(0, 0, 0, 0);
                imageView5.setPadding(0, 0, 0, 0);
                imageView6.setPadding(0, 0, 0, 0);
                imageView7.setPadding(0, 0, 0, 0);
                imageView8.setPadding(0, 0, 0, 0);
                imageView9.setPadding(0, 0, 0, 0);
                imageView10.setPadding(0, 0, 0, 0);
                //selection de l'image
                imageView6.setPadding(10, 10, 10, 10);
            }
        });

        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choixCouleur = 7;
                //deselection autre image
                imageView0.setPadding(0, 0, 0, 0);
                imageView1.setPadding(0, 0, 0, 0);
                imageView2.setPadding(0, 0, 0, 0);
                imageView3.setPadding(0, 0, 0, 0);
                imageView4.setPadding(0, 0, 0, 0);
                imageView5.setPadding(0, 0, 0, 0);
                imageView6.setPadding(0, 0, 0, 0);
                imageView7.setPadding(0, 0, 0, 0);
                imageView8.setPadding(0, 0, 0, 0);
                imageView9.setPadding(0, 0, 0, 0);
                imageView10.setPadding(0, 0, 0, 0);
                //selection de l'image
                imageView7.setPadding(10, 10, 10, 10);
            }
        });

        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choixCouleur = 8;
                //deselection autre image
                imageView0.setPadding(0, 0, 0, 0);
                imageView1.setPadding(0, 0, 0, 0);
                imageView2.setPadding(0, 0, 0, 0);
                imageView3.setPadding(0, 0, 0, 0);
                imageView4.setPadding(0, 0, 0, 0);
                imageView5.setPadding(0, 0, 0, 0);
                imageView6.setPadding(0, 0, 0, 0);
                imageView7.setPadding(0, 0, 0, 0);
                imageView8.setPadding(0, 0, 0, 0);
                imageView9.setPadding(0, 0, 0, 0);
                imageView10.setPadding(0, 0, 0, 0);
                //selection de l'image
                imageView8.setPadding(10, 10, 10, 10);
            }
        });

        imageView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choixCouleur = 9;
                //deselection autre image
                imageView0.setPadding(0, 0, 0, 0);
                imageView1.setPadding(0, 0, 0, 0);
                imageView2.setPadding(0, 0, 0, 0);
                imageView3.setPadding(0, 0, 0, 0);
                imageView4.setPadding(0, 0, 0, 0);
                imageView5.setPadding(0, 0, 0, 0);
                imageView6.setPadding(0, 0, 0, 0);
                imageView7.setPadding(0, 0, 0, 0);
                imageView8.setPadding(0, 0, 0, 0);
                imageView9.setPadding(0, 0, 0, 0);
                imageView10.setPadding(0, 0, 0, 0);
                //selection de l'image
                imageView9.setPadding(10, 10, 10, 10);
            }
        });

        imageView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choixCouleur = 10;
                //deselection autre image
                imageView0.setPadding(0, 0, 0, 0);
                imageView1.setPadding(0, 0, 0, 0);
                imageView2.setPadding(0, 0, 0, 0);
                imageView3.setPadding(0, 0, 0, 0);
                imageView4.setPadding(0, 0, 0, 0);
                imageView5.setPadding(0, 0, 0, 0);
                imageView6.setPadding(0, 0, 0, 0);
                imageView7.setPadding(0, 0, 0, 0);
                imageView8.setPadding(0, 0, 0, 0);
                imageView9.setPadding(0, 0, 0, 0);
                imageView10.setPadding(0, 0, 0, 0);
                //selection de l'image
                imageView10.setPadding(10, 10, 10, 10);
            }
        });

        switch (i){
            case 0:
                imageView0.setPadding(10, 10, 10, 10);
                break;
            case 1:
                imageView1.setPadding(10, 10, 10, 10);
                break;
            case 2:
                imageView2.setPadding(10, 10, 10, 10);
                break;
            case 3:
                imageView3.setPadding(10, 10, 10, 10);
                break;
            case 4:
                imageView4.setPadding(10, 10, 10, 10);
                break;
            case 5:
                imageView5.setPadding(10, 10, 10, 10);
                break;
            case 6:
                imageView6.setPadding(10, 10, 10, 10);
                break;
            case 7:
                imageView7.setPadding(10, 10, 10, 10);
                break;
            case 8:
                imageView8.setPadding(10, 10, 10, 10);
                break;
            case 9:
                imageView9.setPadding(10, 10, 10, 10);
                break;
            case 10:
                imageView10.setPadding(10, 10, 10, 10);
                break;


        }

        imageView0.setAdjustViewBounds(true);
        linearLayout.addView(imageView0);
        imageView1.setAdjustViewBounds(true);
        linearLayout.addView(imageView1);
        imageView2.setAdjustViewBounds(true);
        linearLayout.addView(imageView2);
        imageView3.setAdjustViewBounds(true);
        linearLayout.addView(imageView3);
        imageView4.setAdjustViewBounds(true);
        linearLayout.addView(imageView4);
        imageView5.setAdjustViewBounds(true);
        linearLayout.addView(imageView5);
        imageView6.setAdjustViewBounds(true);
        linearLayout.addView(imageView6);
        imageView7.setAdjustViewBounds(true);
        linearLayout.addView(imageView7);
        imageView8.setAdjustViewBounds(true);
        linearLayout.addView(imageView8);
        imageView9.setAdjustViewBounds(true);
        linearLayout.addView(imageView9);
        imageView10.setAdjustViewBounds(true);
        linearLayout.addView(imageView10);


    }


}
