package ar.com.leventalgarcia.sendmeal.Dominio;

import android.app.Activity;
import android.widget.Toast;

import ar.com.leventalgarcia.sendmeal.HomeActivity;

public class Plato {
    String titulo;
    String descripcion;
    Double precio;
    Double calorias;

    //Constructores:
    public Plato (String titulo, String descripcion, Double precio, Double calorias){
        this.titulo=titulo;
        this.descripcion=descripcion;
        this.precio=precio;
        this.calorias=calorias;
    }

    public Plato (String titulo, String descripcion, Integer precio, Integer calorias){
        this.titulo=titulo;
        this.descripcion=descripcion;
        this.precio=new Double(precio);
        this.calorias=new Double(calorias);;
    }

    @Override
    public String toString() {
        return titulo +"("+ precio +","+calorias+")";
    }

    //Simular carga de platos
    public static void cargarPlatosDePrueba(Activity homeActivity){
        if(HomeActivity.listaPlatos.isEmpty()) {
            HomeActivity.listaPlatos.add(new Plato("Carré de cerdo", "Muy rico", 350.0, 8500.0));
            HomeActivity.listaPlatos.add(new Plato("Suprema", "Muy sabroso", 250.0, 6500.0));
            HomeActivity.listaPlatos.add(new Plato("Lasagna vegana", "Muy vegana", 400.0, 4500.0));
            HomeActivity.listaPlatos.add(new Plato("Pollo relleno", "Muy relleno", 450.0, 7500.0));
            HomeActivity.listaPlatos.add(new Plato("Carré de cerdo", "Muy rico", 350.0, 8500.0));
            HomeActivity.listaPlatos.add(new Plato("Suprema", "Muy sabroso", 250.0, 6500.0));
            HomeActivity.listaPlatos.add(new Plato("Lasagna vegana", "Muy vegana", 400.0, 4500.0));
            HomeActivity.listaPlatos.add(new Plato("Pollo relleno", "Muy relleno", 450.0, 7500.0));
            HomeActivity.listaPlatos.add(new Plato("Carré de cerdo", "Muy rico", 350.0, 8500.0));
            HomeActivity.listaPlatos.add(new Plato("Suprema", "Muy sabroso", 250.0, 6500.0));
            HomeActivity.listaPlatos.add(new Plato("Lasagna vegana", "Muy vegana", 400.0, 4500.0));
            HomeActivity.listaPlatos.add(new Plato("Pollo relleno", "Muy relleno", 450.0, 7500.0));
            Toast.makeText(homeActivity, "Platos de prueba cargados con exito...", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(homeActivity, "Para evitar duplicados solo se pueden cargar Platos si la lista esta vacia...", Toast.LENGTH_SHORT).show();
        }
    }

    //Getters:
    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public Double getCalorias() {
        return calorias;
    }


    //Setters:
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setCalorias(Double calorias) {
        this.calorias = calorias;
    }
}
