package victorgf87.euskalmap.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor Gonzalez on 24/07/15.
 * This class represents an user group
 */
public class Group
{
    private String groupName;   //Name of the group
    private List<User> users;   //List of users that belong to this group



    /**
     * Returns list of users.
     * @return
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Adds a new user to the list
     * @param user user to be added
     */
    public void addUser(User user)
    {
        users.add(user);
    }


    /**
     * Constructor. Creates a new group with given name
     * @param groupName given name
     */
    public Group(String groupName)
    {
        users=new ArrayList<User>();
        this.groupName = groupName;
    }

    /**
     * Returns name of the group.
     * @return name of the group
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Method used to build a description text about this object when searching
     * @return
     */
    public String resultText()
    {
        return groupName;
    }
}
