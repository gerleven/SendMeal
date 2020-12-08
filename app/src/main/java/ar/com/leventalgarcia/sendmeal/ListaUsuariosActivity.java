package ar.com.leventalgarcia.sendmeal;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ListaUsuariosActivity extends AppCompatActivity {
    public static final String actividad = "ListaUsuariosActivity"; //Log.d(HomeActivity.TAG_CICLO_VIDA,actividad+".OnCreate()");
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(HomeActivity.TAG_CICLO_VIDA,actividad+".OnCreate()");
        super.onCreate(savedInstanceState);

    }
}


