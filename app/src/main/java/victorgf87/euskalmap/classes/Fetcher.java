package victorgf87.euskalmap.classes;

import android.text.Html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class retrieves information from map url
 */
public class Fetcher
{

    //Map url
    private static final String URL="https://www.euskal.org/eps/reservas/mapa.php?IdParty=27&lang=0&izenak=1";
    public static final Integer COLUMNS =128;//How many COLUMNS
    public static final Integer ROWS=34;//How many ROWS


    /**
     * Populates lists from source code from URL
     * @throws IOException - happens when there is a problem reading source code
     */
    public void fetchFromUrl()throws IOException
    {
        this.clearCollections();//Gotta clear collections so data is not duplicated.

        //Generates row names, format [A,B][A..P]
        List<String> rowNames=new ArrayList<String>();
        for(char a='A'; a<='B'; a++)
        {
            for(char b='A'; b<='P'; b++)
            {
                rowNames.add(""+a+b);
            }
        }


        Document doc = Jsoup.connect(Fetcher.URL).get();        //Gets source code
        Elements userElems=doc.select("td:containsOwn(.)");     //All user tags, and only them, are <td>.</td>

        int i=0;    //Used to get column and row of the place.

        for(Element elem: userElems)
        {
            //Getting received data
            String cssClass=elem.attr("class");
            String title=elem.attr("title");
            String[] titleParts=title.split("/");
            String userName= Html.fromHtml(titleParts[0]).toString();
            String groupName=(titleParts.length>1)?titleParts[1]:null;


            //Calculate row and column of place
            int iDivided=i/COLUMNS;
            int iResto=i%COLUMNS;
            String rowName=null;
            rowName=rowNames.get(iDivided);

            i++;

            //Generating objects
            Group newGroup = createOrUseExistingGroup(groupName);
            Place newPlace = new Place(rowName,""+(iResto+1),cssClass);
            User newUser=new User(userName,newGroup,newPlace);

            newPlace.setUser(newUser);
            newGroup.addUser(newUser);

            Places.getInstance().addPlace(newPlace);
            Users.getInstance().addUser(newUser);

        }
    }

    /**
     * Creates a new group or uses an existing one that has provided name
     * @param groupName name of user group
     * @return an existing group with provided name, or a new one if it doesn't exist yet.
     */
    private Group createOrUseExistingGroup(String groupName)
    {
        Groups groupsInstance=Groups.getInstance();
        Group ret=groupsInstance.findGroupByName(groupName);    //Retrieves a group with provided name

        //If group doesn't exist yet, creates a new one and adds it to the collection.
        if(ret==null)
        {
            String parsed=null;
            if(groupName!=null)
            {
                //Data uses &acute and similar HTML codes, so we want a readable string
                parsed= Html.fromHtml(groupName).toString();
            }
            ret=new Group(parsed);
            Groups.getInstance().addGroup(ret);
        }
        return ret;
    }

    /**
     * Clears all data so we can reload it.
     */
    private void clearCollections()
    {
        Places.getInstance().reset();
        Users.getInstance().reset();
        Groups.getInstance().reset();
    }
}

