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
        //Obtiene el dia lunes y lo retorna
        return dia;
    }


    public void setDia(String dia) {
        //Manda el dia lunes como dato
        this.dia = dia;
    }

    public String getEntrada() {
        //Obtiene el dia martes y lo retorna
        return entrada;
    }

    public void setEntrada(String entrada) {
        //Manda el dia martes como dato
        this.entrada = entrada;
    }

    public String getPlato() {
        //Obtiene el dia miercoles y lo retorna
        return plato;
    }

    public void setPlato(String plato) {
        //Manda el dia miercoles como dato
        this.plato = plato;
    }

    public String getPostre() {
        //Obtiene el dia jueves y lo retorna
        return postre;
    }

    public void setPostre(String postre) {
        //Manda el dia jueves como dato
        this.postre = postre;
    }

    public String getRefresco() {
        //Obtiene el dia viernes y lo retorna
        return refresco;
    }

    public void setRefresco(String refresco) {
        //Manda el dia viernes como dato
        this.refresco = refresco;
    }


    public String getSabado() {
        //Obtiene el dia sabado y lo retorna
        return sabado;
    }

    public void setSabado(String sabado) {
        //Manda el dia sabado como dato
        this.sabado = sabado;
    }

}



