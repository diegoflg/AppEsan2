package pe.edu.esan.appesan2;

/**
 * Created by Diegoflg on 7/16/2015.
 */
public class Team {
    private String dia;
    private String entrada;
    private String plato;
    private String postre;
    private String refresco;
    private String sabado;

    public Team(String dia,String entrada, String plato, String postre, String refresco,String sabado)
    {
        this.setDia(dia);
        this.setEntrada(entrada);
        this.setPlato(plato);
        this.setPostre(postre);
        this.setRefresco(refresco);
        this.setSabado(sabado);

    }

    public Team(String dia){

        this.setDia(dia);


    }

    public String getdDia() {
        return dia;
    }

    public void setDia(String dia) {

        this.dia = dia;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getPlato() {
        return plato;
    }

    public void setPlato(String plato) {
        this.plato = plato;
    }

    public String getPostre() {
        return postre;
    }

    public void setPostre(String postre) {
        this.postre = postre;
    }

    public String getRefresco() {
        return refresco;
    }

    public void setRefresco(String refresco) {
        this.refresco = refresco;
    }


    public String getSabado() {
        return sabado;
    }

    public void setSabado(String sabado) {
        this.sabado = sabado;
    }

}
