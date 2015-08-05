package pe.edu.esan.appesan2;

/**
 * Created by Diegoflg on 8/4/2015.
 */
public class Datah {
    private int data=0;
    private int lang=0;


    public int getData() {
        return data;
    }

    public int getLang() {
        return lang;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setLang(int lang) {
        this.lang = lang;
    }

    private static final Datah holder = new Datah();
    public static Datah getInstance() {return holder;}
}