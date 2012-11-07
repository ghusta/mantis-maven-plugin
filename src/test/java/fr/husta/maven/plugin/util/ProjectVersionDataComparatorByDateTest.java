package fr.husta.maven.plugin.util;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

import biz.futureware.mantis.rpc.soap.client.ProjectVersionData;

public class ProjectVersionDataComparatorByDateTest {

	@Test
	public void testCompare() {

		ProjectVersionData[] arrayOfProjectVersionData = null;

		// TEST #1 (empty array)
		arrayOfProjectVersionData = new ProjectVersionData[] {};

		Arrays.sort(arrayOfProjectVersionData,
				new ProjectVersionDataComparatorByDate());

		// TEST #2 (3 elts)
		// init
		ProjectVersionData obj1 = new ProjectVersionData();
		obj1.setName("1.0");
		obj1.setDate_order(new GregorianCalendar(2000, Calendar.JANUARY, 1));

		ProjectVersionData obj2 = new ProjectVersionData();
		obj2.setName("1.1");
		obj2.setDate_order(new GregorianCalendar(2005, Calendar.FEBRUARY, 1));

		ProjectVersionData obj3 = new ProjectVersionData();
		obj3.setName("1.2");
		obj3.setDate_order(new GregorianCalendar(2010, Calendar.MARCH, 1));

		arrayOfProjectVersionData = new ProjectVersionData[] { obj2, obj1, obj3 };

		Arrays.sort(arrayOfProjectVersionData,
				new ProjectVersionDataComparatorByDate());

		// order descending
		assertSame(obj3, arrayOfProjectVersionData[0]);
		assertSame(obj2, arrayOfProjectVersionData[1]);
		assertSame(obj1, arrayOfProjectVersionData[2]);

	}

}
