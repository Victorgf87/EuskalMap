package victorgf87.euskalmap.classes;

/**
 * Created by Nabe on 24/07/15.
 */
public class User implements Comparable<User>
{
    private String userName;
    private Group group;
    private Place place;

    public User(String userName, Group group, Place place)
    {
        this.userName = userName;
        this.group = group;
        this.place = place;
    }

    public String getUserName() {
        return userName;
    }

    public Group getGroup() {
        return group;
    }

    public Place getPlace() {
        return place;
    }

    public String resultText()
    {
        return ((userName==null||userName.length()==0)?"Nombre no disponible ":userName)+
                ((group==null || group.getGroupName()==null || group.getGroupName().length()==0)?" sin grupo o grupo no disponible ":" grupo "+group.resultText())+" en "+place.resultText();
    }


    @Override
    public int compareTo(User another) {
        return this.userName.compareTo(another.userName);
    }
}
