package victorgf87.euskalmap.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nabe on 24/07/15.
 */
public class Group
{
    private String groupName;
    private List<User> users;

    public Group()
    {
        users=new ArrayList<User>();
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user)
    {
        if(users==null)users=new ArrayList<User>();
        users.add(user);
    }



    public Group(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public String resultText()
    {
        return groupName;
    }
}
