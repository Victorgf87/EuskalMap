package victorgf87.euskalmap.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nabe on 24/07/15.
 */
public class Users
{
    private static Users instance;
    private List<User> users;

    /**
     * Clears collection
     */
    public void reset()
    {
        users.clear();
    }

    public static Users getInstance()
    {
        if(instance==null)
        {
            instance=new Users();
        }
        return instance;
    }

    private Users()
    {
        users=new ArrayList<User>();
    }

    public void addUser(User user)
    {
        users.add(user);
    }

    public List<User>usersByName(String name)
    {
        List<User>ret=new ArrayList<User>();
        for(User user:users)
        {
            if(user.getUserName().toLowerCase().contains(name.toLowerCase()))ret.add(user);
        }
        return ret;
    }
}
