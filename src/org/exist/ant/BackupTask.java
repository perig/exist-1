/*
 *  eXist Open Source Native XML Database
 *  Copyright (C) 2001-03 Wolfgang M. Meier
 *  wolfgang@exist-db.org
 *  http://exist.sourceforge.net
 *  
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public License
 *  as published by the Free Software Foundation; either version 2
 *  of the License, or (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *  
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 *  
 *  $Id$
 */
package org.exist.ant;

import java.io.IOException;

import org.apache.tools.ant.BuildException;
import org.exist.backup.Backup;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

/**
 * @author wolf
 */
public class BackupTask extends AbstractXMLDBTask {

	private String dir = null;
	
	/* (non-Javadoc)
	 * @see org.apache.tools.ant.Task#execute()
	 */
	public void execute() throws BuildException {
		if (uri == null)
			throw new BuildException("you have to specify an XMLDB collection URI");
		if(dir == null)
			throw new BuildException("missing required parameter: dir");
		registerDatabase();
		Backup backup = new Backup(user, password, dir);
		log("Creating backup of collection: " + uri);
		log("Backup directory: " + dir);
		try {
			backup.backup(false, null);
		} catch (Exception e) {
			throw new BuildException("Exception during backup: " + e.getMessage(), e);
		}
	}

	/**
	 * @param dir
	 */
	public void setDir(String dir) {
		this.dir = dir;
	}

}
