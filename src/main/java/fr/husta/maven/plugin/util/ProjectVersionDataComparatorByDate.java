package fr.husta.maven.plugin.util;

import java.util.Calendar;
import java.util.Comparator;

import biz.futureware.mantis.rpc.soap.client.ProjectVersionData;

/**
 * Comparator for {@link ProjectVersionData}. <br/>
 * First by released, second by obsolete, third by date (descending).
 */
public class ProjectVersionDataComparatorByDate implements
		Comparator<ProjectVersionData> {

	@Override
	public int compare(ProjectVersionData o1, ProjectVersionData o2) {
		// sort #1
		// first by released
		Boolean released1 = o1.getReleased();
		Boolean released2 = o2.getReleased();
		if (released1 == released2) {
			// sort #2
			// second by obsolete
			Boolean obsolete1 = o1.getObsolete();
			Boolean obsolete2 = o2.getObsolete();
			if (obsolete1 == obsolete2) {
				// sort #3
				// third by date (descending)
				Calendar dateOrder1 = o1.getDate_order();
				Calendar dateOrder2 = o2.getDate_order();

				if (dateOrder1 == null) {
					if (dateOrder2 == null) {
						return 0;
					} else {
						// dateOrder2 != null => after
						return 1;
					}
				} else {
					return -(dateOrder1.compareTo(dateOrder2));
				}
			} else {
				// non obsolete first
				if (obsolete1 == Boolean.FALSE) {
					return -1;
				} else {
					return 1;
				}
			}
		} else {
			// unreleased first
			if (released1 == Boolean.FALSE) {
				return -1;
			} else {
				return 1;
			}
		}
	}

}
