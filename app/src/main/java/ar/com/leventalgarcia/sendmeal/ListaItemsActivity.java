package ar.com.leventalgarcia.sendmeal;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ar.com.leventalgarcia.sendmeal.Recycler.RecyclerViewPlatosAdapter;

public class ListaItemsActivity extends AppCompatActivity {

    public static final String actividad = "ListaItemsActivity"; //Log.d(HomeActivity.TAG_CICLO_VIDA,actividad+".OnCreate()");
    private RecyclerView recyclerView;
    //private RecyclerView.Adapter instanciaDeMiRecyclerViewAdapter;
    //private RecyclerView.LayoutManager instanciaDelLinearLayoutManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(HomeActivity.TAG_CICLO_VIDA,actividad+".OnCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_recycler);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewPlatos);
        recyclerView.setHasFixedSize(true); //Optimizacion: le decimos que todas las filas tendran el mismo alto para que no tenga que hacer calculos al respecto y ahorrar ciclos de CPU

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerViewPlatosAdapter(HomeActivity.listaPlatos,this));

    }
}

        /*
        instanciaDelLinearLayoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(instanciaDelLinearLayoutManager);
        instanciaDeMiRecyclerViewAdapter = new RecyclerViewPlatosAdapter(HomeActivity.listaPlatos,this);
        recyclerView.setAdapter(instanciaDeMiRecyclerViewAdapter);
         */