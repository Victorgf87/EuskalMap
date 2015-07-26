package victorgf87.euskalmap.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nabe on 24/07/15.
 */
public class Groups
{
    private List<Group> groups;
    private static Groups instance;

    /**
     * Clears collection
     */
    public void reset()
    {
        groups.clear();
    }

    public static Groups getInstance()
    {
        if(instance==null)
        {
            instance=new Groups();
        }
        return instance;
    }

    private Groups() {
        groups= new ArrayList<Group>();
    }

    public Group findGroupByName(String groupName)
    {
        if(groupName==null)return null;
        for(Group group: groups)
        {
            if(group.getGroupName().equalsIgnoreCase(groupName))
                return group;
        }
        return null;
    }

    public void addGroup(Group group)
    {
        if(group.getGroupName()==null)return;
        if(findGroupByName(group.getGroupName())!=null)return;
        groups.add(group);
    }
}
