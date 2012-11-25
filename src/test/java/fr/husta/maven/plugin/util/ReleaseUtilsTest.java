package fr.husta.maven.plugin.util;

import static org.junit.Assert.*;

import org.apache.maven.plugin.MojoExecutionException;
import org.junit.Test;

public class ReleaseUtilsTest {

	@Test
	public void testGetReleaseVersion() throws MojoExecutionException {

		String pomVersion = null;
		String releaseVersion = null;

		// TEST #1
		pomVersion = "0.1-SNAPSHOT";
		releaseVersion = ReleaseUtils.getReleaseVersion(pomVersion);

		assertEquals("0.1", releaseVersion);

		// TEST #2
		pomVersion = "0.2";
		releaseVersion = ReleaseUtils.getReleaseVersion(pomVersion);

		assertEquals("0.2", releaseVersion);

		// TEST #3
		pomVersion = "";
		releaseVersion = ReleaseUtils.getReleaseVersion(pomVersion);

		assertEquals("", releaseVersion);

	}

}
