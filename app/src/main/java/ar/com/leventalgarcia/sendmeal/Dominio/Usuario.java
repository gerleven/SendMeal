package ar.com.leventalgarcia.sendmeal.Dominio;

import android.app.Activity;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ar.com.leventalgarcia.sendmeal.HomeActivity;

public class Usuario {
    String nombreAprellido;
    String contrasenia;
    String mail;
    Boolean deCredito;
    String numeroTarjeta;
    Integer ccv;
    String vencimientoMes;
    String vencimientoAnio;
    String cbu;
    String aliasCbu;
    Integer creditoTarjeta;

    public Usuario(String nombre, String contrasenia, String mail, Boolean deCredito, String numeroTarjeta, Integer ccv, String vencimientoMes, String vencimientoAnio, String cbu, String aliasCbu, Integer creditoTarjeta){
        this.nombreAprellido=nombre;
        this.contrasenia=contrasenia;
        this.mail = mail;
        this.deCredito=deCredito;
        this.numeroTarjeta=numeroTarjeta;
        this.ccv=ccv;
        this.vencimientoMes=vencimientoMes;
        this.vencimientoAnio=vencimientoAnio;
        this.cbu=cbu;
        this.aliasCbu = aliasCbu;
        this.creditoTarjeta = creditoTarjeta;

    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombreAprellido='" + nombreAprellido + '\'' +
                ", mail='" + mail + '\'' +
                ", numeroTarjeta='" + numeroTarjeta + '\'' +
                '}';
    }

    public static void cargarUsuariosDePrueba(Activity homeActivity){
        if(HomeActivity.listaUsuarios.isEmpty()) {
            //public Usuario(String nombre, String contrasenia, String mail, Boolean deCredito, String numeroTarjeta, Integer ccv, Integer vencimientoMes, Integer vencimientoAnio, String cbu, String aliasCbu, Integer creditoTarjeta);
            HomeActivity.listaUsuarios.add(new Usuario("Juan Perez", "juancitoP82", "juancho@gmail.com", true, "4645795468542365", 468, "Agosto", "2021", "46859486648948535682", "pincel.barbijo.sopapa", 150));
            HomeActivity.listaUsuarios.add(new Usuario("Richard Gonzalez", "richardGon72", "richardGon72@gmail.com", true, "4685795468842365", 468, "Febrero", "2023", "46859486648946584682", "pincel.casa.sopapa", 0));
            HomeActivity.listaUsuarios.add(new Usuario("Micke Amigorena", "Mamigorena_7", "amigorenaMicke@gmail.com", true, "4685695468542365", 468, "Junio", "2022", "46856486648946535682", "chocolate.barbijo.sopapa", 999));
            HomeActivity.listaUsuarios.add(new Usuario("Tincho DobleApellido", "TichoBro", "TinchoBro@gmail.com", true, "4685795548542365", 468, "Noviembre", "2024", "46894486648946535682", "pincel.barbijo.serpiente", 378));
            Toast.makeText(homeActivity, "Usuarios de prueba cargados con exito", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(homeActivity, "Para evitar duplicados solo se pueden cargar Usuarios si la lista esta vacia...", Toast.LENGTH_SHORT).show();
        }

    }

    //getters
    public String getNombreAprellido() {
        return nombreAprellido;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getMail() {
        return mail;
    }

    public Boolean getDeCredito() {
        return deCredito;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public Integer getCcv() {
        return ccv;
    }

    public String getVencimientoMes() {
        return vencimientoMes;
    }

    public String getVencimientoAnio() {
        return vencimientoAnio;
    }

    public String getCbu() {
        return cbu;
    }

    public String getAliasCbu() {
        return aliasCbu;
    }

    public Integer getCreditoTarjeta() {
        return creditoTarjeta;
    }

    //Setters

    public void setNombreAprellido(String nombreAprellido) {
        this.nombreAprellido = nombreAprellido;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setDeCredito(Boolean deCredito) {
        this.deCredito = deCredito;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public void setCcv(Integer ccv) {
        this.ccv = ccv;
    }

    public void setVencimientoMes(String vencimientoMes) {
        this.vencimientoMes = vencimientoMes;
    }

    public void setVencimientoAnio(String vencimientoAnio) {
        this.vencimientoAnio = vencimientoAnio;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public void setAliasCbu(String aliasCbu) {
        this.aliasCbu = aliasCbu;
    }

    public void setCreditoTarjeta(Integer creditoTarjeta) {
        this.creditoTarjeta = creditoTarjeta;
    }
}
