package org.carlspring.maven.relocation;

import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.repository.DefaultArtifactRepository;
import org.apache.maven.artifact.repository.layout.ArtifactRepositoryLayout;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;

import java.io.File;

/**
 * @author mtodorov
 */
public class RelocateSingleMojoTest
        extends AbstractMojoTestCase
{

    public static final String DIR_TEST_CLASSES = new File(System.getProperty("basedir") + "/target/test-classes").getAbsolutePath();

    private static final String POM_PLUGIN = DIR_TEST_CLASSES + "/poms/pom-relocate-single.xml";

    public static final String ORIGINAL_GAV = "com.foo.bar:zoo:1.1";

    public static final String RELOCATION_GAV = "com.foo.bars:zoo:1.1";

    RelocateSingleMojo mojo;


    @Override
    public void setUp()
            throws Exception
    {
        super.setUp();

        mojo = (RelocateSingleMojo) lookupMojo("relocate-single", POM_PLUGIN);
        mojo.setRepositoryBaseDir(DIR_TEST_CLASSES + "/local-repo");
        mojo.setOriginalGAV(ORIGINAL_GAV);
        mojo.setRelocationGAV(RELOCATION_GAV);
        mojo.setRelocationMessage("The artifact has been relocated from com.foo.bar:zoo:1.1 to com.foo.bars:zoo:1.1.");
    }

    public void testSingleRelocation()
            throws MojoExecutionException, MojoFailureException
    {
        mojo.execute();

        assertTrue("Failed to backup the artifacts!", new File(DIR_TEST_CLASSES + "/local-repo/com/foo/bar/zoo/1.1/backup").exists());

        assertTrue("Failed to preserve the artifacts which should not be removed!", new File(DIR_TEST_CLASSES + "/local-repo/com/foo/bar/zoo/1.1/zoo-1.1.pom").exists());
        assertTrue("Failed to preserve the artifacts which should not be removed!", new File(DIR_TEST_CLASSES + "/local-repo/com/foo/bar/zoo/1.1/zoo-1.1.pom.md5").exists());
        assertTrue("Failed to preserve the artifacts which should not be removed!", new File(DIR_TEST_CLASSES + "/local-repo/com/foo/bar/zoo/1.1/zoo-1.1.pom.sha1").exists());

        assertFalse("Failed to delete the artifacts!", new File(DIR_TEST_CLASSES + "/local-repo/com/foo/bar/zoo/1.1/zoo-1.1.jar").exists());
        assertFalse("Failed to delete the artifacts!", new File(DIR_TEST_CLASSES + "/local-repo/com/foo/bar/zoo/1.1/zoo-1.1.jar.md5").exists());
        assertFalse("Failed to delete the artifacts!", new File(DIR_TEST_CLASSES + "/local-repo/com/foo/bar/zoo/1.1/zoo-1.1.jar.sha1").exists());

        // TODO:
        // TODO: Check that the newly generated .pom file for the relocated artifact contains the proper GAV
        // TODO:
    }

}
