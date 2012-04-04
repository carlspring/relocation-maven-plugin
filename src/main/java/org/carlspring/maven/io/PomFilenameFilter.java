package org.carlspring.maven.io;

import java.io.File;
import java.io.FilenameFilter;

/**
 * @author mtodorov
 */
public class PomFilenameFilter implements FilenameFilter
{

    @Override
    public boolean accept(File dir, String name)
    {
        return name.endsWith(".pom");
    }

}
