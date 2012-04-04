package org.carlspring.maven.model;

/**
 * Copyright 2012 Martin Todorov.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author mtodorov
 */
public class ModelWriter
{

    private Model model;

    private File pomFile;


    public ModelWriter(Model model, String pomFileName)
    {
        this(model, new File(pomFileName).getAbsoluteFile());
    }

    public ModelWriter(Model model, File pomFile)
    {
        this.model = model;
        this.pomFile = pomFile;
    }

    public void write()
            throws IOException, XmlPullParserException
    {
        FileWriter fileWriter = null;

        try
        {
            if (pomFile.getAbsoluteFile().exists())
            {
                System.out.println("Deleting " + pomFile.getAbsolutePath());
                //noinspection ResultOfMethodCallIgnored
                pomFile.delete();
            }

            fileWriter = new FileWriter(pomFile);

            MavenXpp3Writer xpp3Writer = new MavenXpp3Writer();
            xpp3Writer.write(fileWriter, model);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (fileWriter != null)
            {
                fileWriter.close();
            }
        }
    }

    public Model getModel()
    {
        return model;
    }

    public void setModel(Model model)
    {
        this.model = model;
    }

    public File getPomFile()
    {
        return pomFile;
    }

    public void setPomFile(File pomFile)
    {
        this.pomFile = pomFile;
    }

}
