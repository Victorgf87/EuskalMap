package victorgf87.euskalmap.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Nabe on 24/07/15.
 * This is a class with operations for groups.
 * It's implemented as a singleton so we can access same data from different parts of the application.
 */
public class Groups
{
    private List<Group> groups;         //Collection of groups
    private static Groups instance;     //Only possible instance of this class

    /**
     * Clears collection
     */
    public void reset()
    {
        groups.clear();
    }

    /**
     * Returns the instance for this class. If it doesn't exist, it is created.
     * @return instance of this class.
     */
    public static Groups getInstance()
    {
        if(instance==null)
        {
            instance=new Groups();
        }
        return instance;
    }

    /**
     * Empty constructor. Initializes collection of groups.
     */
    private Groups()
    {
        groups= new ArrayList<Group>();
    }


    /**
     * Returns group whose name matches the given name
     * @param groupName - searched group name
     * @return - searched group. null if it doesn't exist
     */
    public Group findGroupByName(String groupName)
    {

        if(groupName==null)return null;
        Group group=new Group(groupName);
        int index=Collections.binarySearch(groups,group);
        return (index>=0)?groups.get(index) : null;

    }

    /**
     * Adds a new group. It grants that the collection will be sorted.
     * @param group
     */
    public void addGroup(Group group)
    {
        if(group.getGroupName()==null)return;
        if(findGroupByName(group.getGroupName())!=null)return;
        groups.add(group);
        Collections.sort(groups);
    }
}
