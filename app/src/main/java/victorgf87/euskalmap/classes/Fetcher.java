package victorgf87.euskalmap.classes;

import android.text.Html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class retrieves information from map url
 */
public class Fetcher
{
    /*
    <th class="g">.</th><td>Sin asignar</td>
<th class="r">.</th><td>Asignado</td>
<th class="b">.</th><td>En la party</td>
     */

    private static final String URL="https://www.euskal.org/eps/reservas/mapa.php?IdParty=27&lang=0&izenak=1";
    private static final Integer COLUMNS =128;//How many COLUMNS
    private static final Integer ROWS=34;



    public Fetcher()
    {

    }

    /**
     * Populates lists
     * @throws IOException
     */
    public void fetchFromUrl()throws IOException
    {
        List<String> rowNames=new ArrayList<String>();

        for(char a='A'; a<='B'; a++)
        {
            for(char b='A'; b<='P'; b++)
            {
                rowNames.add(""+a+b);
            }
        }


        Document doc = Jsoup.connect(Fetcher.URL).get();
        //Elements newsHeadlines = doc.select("#mapa-sitios tr td");
        //Elements elems=doc.select("td:containsOwn(.)");
        Elements userElems=doc.select("td:containsOwn(.)");

        int i=0;

        for(Element elem: userElems)
        {
            String cssClass=elem.attr("class");
            String title=elem.attr("title");
            String[] titleParts=title.split("/");
            String userName= Html.fromHtml(titleParts[0]).toString();
            String groupName=null;
            if(titleParts.length>1)
            {
                groupName=titleParts[1];
            }

            int iDivided=i/COLUMNS;
            int iResto=i%COLUMNS;
            String rowName=null;
                rowName=rowNames.get(iDivided);

            i++;

            Group newGroup = createOrUseExistingGroup(groupName);
            Place newPlace = new Place(rowName,""+(iResto+1),cssClass);
            User newUser=new User(userName,newGroup,newPlace);

            newPlace.setUser(newUser);
            newGroup.addUser(newUser);

            Places.getInstance().addPlace(newPlace);
            Users.getInstance().addUser(newUser);


            //User user=new User(userName, new Group(groupName));
        }

        Places places=Places.getInstance();
        Users users=Users.getInstance();
        Groups groups=Groups.getInstance();

    }

    private Group createOrUseExistingGroup(String groupName)
    {
        Groups groupsInstance=Groups.getInstance();
        Group ret=groupsInstance.findGroupByName(groupName);
        if(ret==null)
        {
            String parsed=null;
            if(groupName!=null)
            {
                parsed= Html.fromHtml(groupName).toString();
            }
            ret=new Group(parsed);
            Groups.getInstance().addGroup(ret);
        }
        return ret;
    }



    /*private List<Group> groupCache;




    public Fetcher()
    {
        groupCache=new ArrayList<Group>();
    }

    public void fetchFromUrl() throws IOException
    {
        List<String> rowNames=new ArrayList<String>();
        List<Place> places=new ArrayList<Place>();

        for(char a='A'; a<='B'; a++)
        {
            for(char b='A'; b<='P'; b++)
            {
                rowNames.add(""+a+b);
            }
        }

        Document doc = Jsoup.connect(Fetcher.URL).get();
        //Elements newsHeadlines = doc.select("#mapa-sitios tr td");
        //Elements elems=doc.select("td:containsOwn(.)");
        Elements userElems=doc.select("td:containsOwn(.)");

        int i=0;

        for(Element elem: userElems)
        {
            String cssClass=elem.attr("class");
            String title=elem.attr("class");
            String[] titleParts=title.split("/");
            String userName=titleParts[0];
            String groupName=null;
            if(titleParts.length>1)
            {
                groupName=titleParts[1];
            }

            User user=new User(userName, new Group(groupName));
        }


        int a=3;
        int b=a;

    }*/


}

