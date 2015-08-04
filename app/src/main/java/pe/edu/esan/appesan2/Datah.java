package pe.edu.esan.appesan2;

/**
 * Created by Diegoflg on 8/4/2015.
 */
public class Datah {
    private int data=0;
    public int getData() {return data;}
    public void setData(int data) {this.data = data;}

    private static final Datah holder = new Datah();
    public static Datah getInstance() {return holder;}
}