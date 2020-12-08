package ar.com.leventalgarcia.sendmeal.Recycler;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import ar.com.leventalgarcia.sendmeal.Dominio.Plato;
import ar.com.leventalgarcia.sendmeal.R;

public class RecyclerViewPlatosAdapter extends RecyclerView.Adapter<RecyclerViewPlatosAdapter.PlatoRecyclerViewHolder>{

    ArrayList<Plato> listaDePlatos;
    Activity ctx;

    ///Constructor del Adapter
    public RecyclerViewPlatosAdapter(List<Plato> lista, Activity ctx){
        this.ctx = ctx;
        this.listaDePlatos = (ArrayList<Plato>) lista;
    }

    //Clase Holder (innerClass):
    public class PlatoRecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView titulo;
        TextView descripcion;
        TextView precio;
        TextView calorias;
        ImageView fotoPlato;
        Button btnFilaPlato;

        public PlatoRecyclerViewHolder(@NonNull View v) {
            super(v);
            titulo = v.findViewById(R.id.textViewTituloPlato);
            descripcion = v.findViewById(R.id.textViewDescripcion);
            precio = v.findViewById(R.id.textViewPrecio);
            calorias = v.findViewById(R.id.textViewCalorias);
            fotoPlato = v.findViewById(R.id.imageViewFoto);
            btnFilaPlato = v.findViewById(R.id.buttonFilaPlato);

            btnFilaPlato.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Accion del boton
                }
            });
        }
    }

    @NonNull
    @Override
    public RecyclerViewPlatosAdapter.PlatoRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Forma comprimida:
        // return new PlatoRecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_fila_plato,parent,false)); //El inflate toma el layout y nos devuelve una vista, con esa vista instanciamos un viewholder y eso es lo que retornamos

        //Forma completa:
        LayoutInflater inflador = LayoutInflater.from(parent.getContext());
        View v = inflador.inflate(R.layout.layout_fila_plato,parent,false);
        PlatoRecyclerViewHolder vh = new PlatoRecyclerViewHolder(v);
        return vh;
    }



    @Override
    public void onBindViewHolder(@NonNull PlatoRecyclerViewHolder vh, int position) {
        //Dejamos anotada la "position" en el Tag de los widgets del holder que recibiran eventos para luego poder consultar el array de datos en esa position y hacer los cambios correspondientes
        vh.btnFilaPlato.setTag(position);

        //Obtenemos el Plato correspondiente a "position":
        Plato plato = listaDePlatos.get(position);

        //personalizamos el plato segun la info del array:
        vh.titulo.setText(plato.getTitulo());
        vh.descripcion.setText("Descripcion: "+plato.getDescripcion());
        vh.precio.setText("$"+plato.getPrecio().toString());
        vh.calorias.setText(plato.getCalorias().toString());
    }

    @Override
    public int getItemCount() {
        return listaDePlatos.size();
    }
}
