package pe.edu.esan.appesan2;

/**
 * La clase Team es en donde se almacenan los menus de la cafeteria, en dia se almacenan todos los platos del dia lunes,
 * en entrada todos los platos del dia martes y asi sucesivamente
 * Esta clase ayuda a obtener el menu por dia de la semana
 */
public class Team {
    private String dia;//lunes
    private String entrada;//martes
    private String plato;//miercoles
    private String postre;//jueves
    private String refresco;//viernes
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

    public Team(String dia, String entrada){
        this.setDia(dia);
        this.setEntrada(entrada);
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



