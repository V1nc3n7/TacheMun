package univ.etu.tachemun;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class NouvelleListeDeTache extends AppCompatActivity {

    private LinearLayout linearLayout;
    private EditText NomListeTache;
    private Button cree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouvelle_liste_de_tache);

        linearLayout = (LinearLayout) findViewById(R.id.couleurs);
        for(int i = 0;i<10;i++){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.carreblanc);
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
                    if(NomListeTache.getText().equals("")){
                        Snackbar.make(v, "Le nom de votre tache est invalide", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                    else{
                        finish();
                    }
                }
            });


        }
    }
}
