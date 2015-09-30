package pe.edu.esan.appesan2;

/**
 * Clase en donde se guardan datos para ser usados entre dos o mas clases.
 * Se guara el el user y el pass, idioma del app en lang, un entero en menu que sirve para saber si has hecho click en impresiones
        */
public class Datah {
    private int data=0;
    private int lang=0;
    private int menu=0;
    private String user="";
    private String pass="";


    public int getData() {
        return data;
    }
    public int getMenu() {
        return menu;
    }

    public int getLang() {
        return lang;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public void setData(int data) {
        this.data = data;
    }
    public void setMenu(int  menu) {
        this.menu = menu;
    }

    public void setLang(int lang) {
        this.lang = lang;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    private static final Datah holder = new Datah();
    public static Datah getInstance() {return holder;}
}