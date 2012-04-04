package org.carlspring.maven.model;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author mtodorov
 */
public class ModelParser
{

    private Model model;

    private File pomFile;


    public ModelParser(String pomFileName)
            throws IOException, XmlPullParserException
    {
        this(new File(pomFileName).getAbsoluteFile());
    }

    public ModelParser(File pomFile)
            throws IOException, XmlPullParserException
    {
        this.pomFile = pomFile;

        parse();
    }

    public void parse()
            throws IOException, XmlPullParserException
    {
        FileReader fileReader = null;

        try
        {
            fileReader = new FileReader(pomFile);
            MavenXpp3Reader xppReader = new MavenXpp3Reader();
            model = xppReader.read(fileReader);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (XmlPullParserException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (fileReader != null)
            {
                fileReader.close();
            }
        }
    }

    public Model getModel()
    {
        return model;
    }

}
