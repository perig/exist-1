/*
 *  eXist Open Source Native XML Database
 *  Copyright (C) 2001-07 The eXist Project
 *  http://exist-db.org
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
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 *  $Id$
 */

package org.exist.validation.resolver;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.xerces.util.XMLCatalogResolver;
import org.apache.xerces.xni.XMLResourceIdentifier;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.parser.XMLInputSource;

import org.w3c.dom.ls.LSInput;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *  Wrapper around xerces2's
 *  <a href="http://xerces.apache.org/xerces2-j/javadocs/xerces2/org/apache/xerces/util/XMLCatalogResolver.html"
 *                                                      >XMLCatalogresolver</a>
 * @author Dannes Wessels (dizzzz@exist-db.org)
 */
public class eXistXMLCatalogResolver extends XMLCatalogResolver {
    
    private final static Logger LOG = Logger.getLogger(eXistXMLCatalogResolver.class);
    
    /**
     *  Constructs a catalog resolver with the given list of entry files.
     *
     * @param catalogs List of Strings
     *
     *  TODO: check for non-String and NULL values.
     */
    public void setCatalogs(List catalogs){

        if(catalogs!=null && catalogs.size()>0){
            String[] allCatalogs = new String[catalogs.size()];
            int counter=0;
            for (Iterator it=catalogs.iterator(); it.hasNext(); ) {
                String element = (String) it.next();
                allCatalogs[counter]=element;
                counter++;
            }
            super.setCatalogList(allCatalogs);
        }
    }
    

    
    /**
     * @see org.apache.xerces.util.XMLCatalogResolver#resolveEntity(String, String)
     */
    public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
        LOG.debug("Resolving publicId='"+publicId+"', systemId='"+systemId+"'");
        InputSource retValue = super.resolveEntity(publicId, systemId);
        LOG.debug("Resolved " + (retValue!=null));
        if(retValue!=null){
            LOG.debug("PublicId='" + retValue.getPublicId() + "' SystemId=" + retValue.getSystemId());
        }
        return retValue;
    }
    
    /**
     * @see org.apache.xerces.util.XMLCatalogResolver#resolveResource(String, String, String, String, String)
     */
    public LSInput resolveResource(String type, String namespaceURI, String publicId, String systemId, String baseURI) {
        LOG.debug("Resolving type='"+type+"', namespaceURI='"+namespaceURI+"', publicId='"+publicId+"', systemId='"+systemId+"', baseURI='"+baseURI+"'");
        LSInput retValue= super.resolveResource(type, namespaceURI, publicId, systemId, baseURI);
        
        LOG.debug("Resolved " + (retValue!=null));
        if(retValue!=null){
            LOG.debug("PublicId='" + retValue.getPublicId() + "' SystemId='" 
                + retValue.getSystemId() + "' BaseURI='" + retValue.getBaseURI() +"'");
        }
        
        return retValue;
    }
    
    /**
     * @see org.apache.xerces.util.XMLCatalogResolver#resolveEntity(String, String, String, String)
     */
    public InputSource resolveEntity(String name, String publicId, String baseURI, String systemId) throws SAXException, IOException {
        LOG.debug("Resolving name='"+name+"', publicId='"+publicId+"', baseURI='"+baseURI+"', systemId='"+systemId+"'");
        InputSource retValue = super.resolveEntity(name, publicId, baseURI, systemId);
        
        LOG.debug("Resolved " + (retValue!=null));
        if(retValue!=null){
             LOG.debug("PublicId='" + retValue.getPublicId() + "' SystemId='" 
                + retValue.getSystemId() +"'");
        }
        
        return retValue;
    }
    
    /**
     * @see org.apache.xerces.util.XMLCatalogResolver#resolveIdentifier(XMLResourceIdentifier)
     */
    public String resolveIdentifier(XMLResourceIdentifier xri) throws IOException, XNIException {
        
        if(xri.getBaseSystemId()==null && xri.getExpandedSystemId()==null &&
           xri.getLiteralSystemId()==null && xri.getNamespace()==null && xri.getPublicId()==null){
            
            // quick fail
            return null;
        }
        LOG.debug("Resolving XMLResourceIdentifier='"+xri.toString()+"'");
        
        String retValue = super.resolveIdentifier(xri);
        LOG.debug("Resolved " + (retValue!=null));
        if(retValue!=null){
            LOG.debug("Identifier='" + retValue +"'");
        }
        return retValue;
    }
    
    /**
     * @see org.apache.xerces.util.XMLCatalogResolver#resolveEntity(XMLResourceIdentifier)
     */
    public XMLInputSource resolveEntity(XMLResourceIdentifier xri) throws XNIException, IOException {
        if(xri.getBaseSystemId()==null && xri.getExpandedSystemId()==null &&
           xri.getLiteralSystemId()==null && xri.getNamespace()==null && xri.getPublicId()==null){
            
            // quick fail
            return null;
        }
        
        LOG.debug("Resolving XMLResourceIdentifier='"+xri.toString()+"'");
        XMLInputSource retValue = super.resolveEntity(xri);
        
        
        
        LOG.debug("Resolved " + (retValue!=null));
        if(retValue!=null){
             LOG.debug("PublicId='" + retValue.getPublicId() + "' SystemId='" 
                + retValue.getSystemId() +"' BaseSystemId=" + retValue.getBaseSystemId());
        }
        
        return retValue;
    }
    
    
}
