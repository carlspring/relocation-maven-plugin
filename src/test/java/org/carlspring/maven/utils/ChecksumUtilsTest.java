package org.carlspring.maven.utils;

import org.carlspring.maven.util.ChecksumUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author mtodorov
 */
public class ChecksumUtilsTest
{

    public static final String FILE_NAME = System.getProperty("basedir") + "/target/test-classes/checksum-test-file";

    @Test
    public void testMD5Sum()
            throws Exception
    {
        final String md5Checksum = ChecksumUtils.getMD5Checksum(FILE_NAME);

        assertNotNull(md5Checksum);

        System.out.println("MD5:  " + md5Checksum);

        assertEquals("Incorrect MD5 sum!", "413fc0b59a067bf4620cc827c0027c5d", md5Checksum);
    }

    @Test
    public void testSHA1Sum()
            throws Exception
    {
        final String sha1Checksum = ChecksumUtils.getSHA1Checksum(FILE_NAME);

        assertNotNull(sha1Checksum);

        System.out.println("SHA1: " + sha1Checksum);

        assertEquals("Incorrect SHA1 sum!", "70e3646109a8a89e892fa07bbb2becf3780b1386", sha1Checksum);
    }

}
