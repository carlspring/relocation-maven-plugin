package org.carlspring.maven.relocation;

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
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import java.io.File;

/**
 * @goal            single
 * @requiresProject false
 * @author          mtodorov
 */
public class RelocateSingleMojo
        extends AbstractRelocationMojo
{


    @Override
    public void execute()
            throws MojoExecutionException, MojoFailureException
    {
        relocate();
    }

    public void relocate()
            throws MojoFailureException
    {
        try
        {
            File artifactBasedir = getOriginalArtifactBasedir().getAbsoluteFile();

            File pomFile = new File(artifactBasedir, getPomFile(artifactBasedir));

            backupFiles(artifactBasedir);

            Model originalModel = getOriginalModel(pomFile);

            generateRelocationModel(originalModel, pomFile);

            relocateArtifacts(artifactBasedir);

            generateModelForRelocatedArtifacts(originalModel, new File(getRelocatedArtifactBasedir(),
                                                                       getRelocationArtifactId() + "-" +
                                                                       getRelocationVersion() +".pom"));
            // Note: At this point, the originalModel is changed with the relocation info.

            removeOriginalArtifacts();
        }
        catch (Exception e)
        {
             throw new MojoFailureException(e.getMessage(), e);
        }
    }

}
