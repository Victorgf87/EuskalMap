package victorgf87.euskalmap.classes;

import android.graphics.Color;
import android.support.annotation.NonNull;

import java.util.HashMap;

/**
 * This class represents a place in the party.
 * Created by Nabe on 24/07/15.
 */
public class Place
{
    //TODO maybe create an interface to work with these hashmaps.

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


    private String row;         //Row where the place is located
    private String column;      //Column where the place is located
    private String cssClass;    //Css class that tells what color to use
    private User user;          //User that is/will be in this place


    /**
     *
     * Constructor that gets data that will always be known
     * @param row
     * @param column
     * @param cssClass
     */
    public Place(@NonNull String row, @NonNull String column, @NonNull String cssClass)
    {
        this.row = row;
        this.column = column;
        this.cssClass = cssClass;
    }

    /**
     * Sometimes we need to add user after creating the place
     * @param user
     */
    public void setUser(User user)
    {
        this.user=user;
    }


    /**
     * Gets user
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     * Gets row of place
     * @return
     */
    public String getRow() {
        return row;
    }

    /**
     * Gets column of place
     * @return
     */
    public String getColumn() {
        return column;
    }


    /**
     * Gets css class
     * @return
     */
    public String getCssClass() {
        return cssClass;
    }


    /**
     * Gets readable location string
     * @return
     */
    public String onlyPlaceUbication()
    {
        return row+"-"+column;
    }

    public String getDataInfo()
    {
        String ret=resultText()+": ";
        ret+=(user==null || user.getUserName().equals("") || user.getUserName().length()==0)?
                "Usuario desconocido":user.getUserName();

        return ret;
    }

    /**
     * Gets readable string with data for searches.
     * @return
     */
    public String resultText()
    {
        return row+"-"+column+" ("+cssClassToName.get(cssClass)+")";
    }
}
