package pe.edu.esan.appesan2;

/**
 * Created by Diegoflg on 8/4/2015.
 */
public class Datah {
    private int data=0;
    private int lang=0;
    private String user="";
    private String pass="";


    public int getData() {
        return data;
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