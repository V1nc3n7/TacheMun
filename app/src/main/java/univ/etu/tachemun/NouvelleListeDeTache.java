package univ.etu.tachemun;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class NouvelleListeDeTache extends AppCompatActivity {

    private LinearLayout linearLayout;
    private EditText NomListeTache;
    private Button cree;
    private Button c;
    private String choixc;
    private ColorFilter coul;
    private int cc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouvelle_liste_de_tache);

        linearLayout = (LinearLayout) findViewById(R.id.couleurs);
        for(int i = 0;i<10;i++){
            final ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.carreblanc);
            imageView.setClickable(true);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //savoir quelle méthode utiliser pour pouvoir réutiliser les filtres de couleurs.
                    choixc = imageView.getColorFilter().toString();
                    coul = imageView.getColorFilter();
                    cc = imageView.getColorFilter().hashCode();
                }
            });
            switch(i){
                case 0:
                    imageView.setColorFilter(Color.BLACK);
                    break;
                case 1:
                    imageView.setColorFilter(Color.BLUE);
                    break;
                case 2:
                    imageView.setColorFilter(Color.RED);
                    break;
                case 3:
                    imageView.setColorFilter(Color.YELLOW);
                    break;
                case 4:
                    imageView.setColorFilter(Color.GREEN);
                    break;
                case 5:
                    imageView.setColorFilter(Color.GRAY);
                    break;
                case 6:
                    imageView.setColorFilter(Color.MAGENTA);
                    break;
                case 7:
                    imageView.setColorFilter(Color.CYAN);
                    break;
                case 8:
                    imageView.setColorFilter(Color.DKGRAY);
                    break;
                case 9:
                    imageView.setColorFilter(Color.LTGRAY);
                    break;
            }
            imageView.setAdjustViewBounds(true);
            linearLayout.addView(imageView);


            NomListeTache = (EditText) findViewById(R.id.nomListTache);

            cree = (Button) findViewById(R.id.cree);
            cree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //ajouter l'ajout à la base de donnee
                    if(NomListeTache.getText().toString().equals("")){
                        /*Snackbar.make(v, "Le nom de votre tache est invalide", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();*/

                        AlertDialog.Builder builder = new AlertDialog.Builder(NouvelleListeDeTache.this);
                        builder.setMessage("Le nom de votre liste de tache est incorrect.");
                        builder.setCancelable(true);
                        builder.setPositiveButton("Suivant", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "Activity will continue",
                                        Toast.LENGTH_LONG).show();
                            }
                        });
                        /*builder.setNegativeButton("No, no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });*/
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                    else{
                        finish();
                    }
                }
            });
        }


        c = (Button) findViewById(R.id.check);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "info : "+NomListeTache.getText().toString()+" et "+choixc+"......"+cc, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
