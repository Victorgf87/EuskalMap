package victorgf87.euskalmap.classes;

import android.graphics.Color;

import java.util.HashMap;

/**
 * Created by Nabe on 24/07/15.
 */
public class Place
{
    public static final HashMap<String, Integer> cssClassToColor=new HashMap<String,Integer>(){
        {
            put("g", Color.GREEN);
            put("o",Color.YELLOW);
            put("r",Color.RED);
            put("b",Color.BLUE);
        }};
    public static final HashMap<String, String>cssClassToName=new HashMap<String,String>(){
        {
            put("g","Sin asignar");
            put("o","Reservado");
            put("r","Reserva confirmada");
            put("b","Acreditado");
        }};


    private String row, column, cssClass;
    private User user;

    public Place(String row, String column, String cssClass) {
        this.row = row;
        this.column = column;
        this.cssClass = cssClass;
    }

    public void setUser(User user)
    {
        this.user=user;
    }

    public User getUser() {
        return user;
    }

    public String getRow() {
        return row;
    }

    public String getColumn() {
        return column;
    }

    public String getCssClass() {
        return cssClass;
    }

    public String getStateName()
    {
        return cssClassToName.get(this.cssClass);
    }

    public String resultText()
    {
        return row+"-"+column+" ("+cssClassToName.get(cssClass)+")";
    }
}
