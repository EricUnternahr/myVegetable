package Groupe3.myvegetable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.row_produit);

        // Étape 2 : Préparer les données
        List<String> numbers = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            numbers.add(String.valueOf(i));
        }

        // Étape 3 : Créer un ArrayAdapter en utilisant un layout de spinner simple et la liste des nombres
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, numbers);

        // Spécifier le layout à utiliser lorsque la liste des choix apparaît
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Étape 4 : Appliquer l'adaptateur au spinner
        Spinner spinner = findViewById(R.id.spinquantite);
        spinner.setAdapter(adapter);

        // Étape 5 : Définir un écouteur pour le spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Un élément a été sélectionné, vous pouvez le traiter ici
                String selectedItem = parent.getItemAtPosition(position).toString();
                // Traiter la sélection
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Aucune action requise ici
            }
        });
    }
}