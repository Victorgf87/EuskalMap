package victorgf87.euskalmap.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nabe on 24/07/15.
 */
public class Places
{

    private List<Place> places;
    private static Places instance;

    /**
     * Clears places collection.
     */
    public void reset()
    {
        places.clear();
    }

    private Places()
    {
        places=new ArrayList<Place>();
    }

    public Place searchPlace(String row, String column)
    {
        for(Place place:places)
        {
            if(place.getRow().equalsIgnoreCase(row) && place.getColumn().equalsIgnoreCase(column))
                return place;
        }
        return null;
    }

    public static Places getInstance()
    {
        if(instance==null)
        {
            instance=new Places();
        }
        return instance;
    }

    public void addPlace(Place place)
    {
        places.add(place);
    }

    public List<Place> getPlaces() {
        return places;
    }


    public int getHowManyUnassigned()
    {
        return getHowManyOfCssClass("g");
    }

    public double getPercentageofUnassigned()
    {
        return calculatePercentage(getHowManyUnassigned());
    }

    private double calculatePercentage(int quantity)
    {
        return 100*quantity/places.size();
    }
    /*4000*10/100=400
    4000*x/100=y

    x=100*y/4000*/

    public int getHowManyReserved()
    {
        return getHowManyOfCssClass("o");
    }
    public double getPercentageOfReserved()
    {
        return calculatePercentage(getHowManyReserved());
    }

    public int getHowManyConfirmed()
    {
        return getHowManyOfCssClass("r");
    }

    public double getPercentageOfConfirmed()
    {
        return calculatePercentage(getHowManyConfirmed());
    }

    public int getHowManyGranted()
    {
        return getHowManyOfCssClass("b");
    }

    public double getPercentageOfGranted()
    {
        return calculatePercentage(getHowManyGranted());
    }

    private int getHowManyOfCssClass(String cssClass)
    {
        int ret=0;
        for(Place place: places)
        {
            if(place.getCssClass().equals(cssClass))ret++;
        }
        return ret;
    }
}
