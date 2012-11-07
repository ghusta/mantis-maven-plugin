package fr.husta.maven.plugin.util;

import java.util.Comparator;

import biz.futureware.mantis.rpc.soap.client.ProjectVersionData;

/**
 * Comparator for {@link ProjectVersionData}.
 * <br/>
 * First by released, second by obsolete, third by name (descending).
 */
public class ProjectVersionDataComparatorByVersionName implements Comparator<ProjectVersionData>
{

    public int compare(ProjectVersionData o1, ProjectVersionData o2)
    {
        // sort #1
        // first by released
        Boolean released1 = o1.getReleased();
        Boolean released2 = o2.getReleased();
        if (released1 == released2)
        {
            // sort #2
            // second by obsolete
            Boolean obsolete1 = o1.getObsolete();
            Boolean obsolete2 = o2.getObsolete();
            if (obsolete1 == obsolete2)
            {
                // sort #3
                // third by name (descending)
                String name1 = o1.getName();
                String name2 = o2.getName();
                return -(name1.compareTo(name2));
            }
            else
            {
                // non obsolete first
                if (obsolete1 == Boolean.FALSE)
                {
                    return -1;
                }
                else
                {
                    return 1;
                }
            }
        }
        else
        {
            // unreleased first
            if (released1 == Boolean.FALSE)
            {
                return -1;
            }
            else
            {
                return 1;
            }
        }
    }

}
