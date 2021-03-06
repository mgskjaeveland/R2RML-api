/*******************************************************************************
 * Copyright 2013, the Optique Consortium
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * This first version of the R2RML API was developed jointly at the University of Oslo, 
 * the University of Bolzano, La Sapienza University of Rome, and fluid Operations AG, 
 * as part of the Optique project, www.optique-project.eu
 ******************************************************************************/
package owlapiTest;

import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Ignore;
import org.semanticweb.owlapi.io.RDFTriple;
import org.junit.Test;

import eu.optique.api.mapping.R2RMLMappingManager;
import eu.optique.api.mapping.impl.owlapi.OWLAPIR2RMLMappingManagerFactory;
import eu.optique.api.mapping.TriplesMap;
import eu.optique.api.mapping.impl.owlapi.OWLAPIUtil;

/**
 * JUnit Test Cases
 * 
 * @author Riccardo Mancini
 */
public class SerilizationMapping_Test
{
    @Ignore("RDF/XML is not officially supported by W3C R2RML mapping specification")
	@Test
	public void test1() throws Exception {

		try{			
			InputStream fis = getClass().getResourceAsStream("../mappingFiles/artist.ttl");
			
			R2RMLMappingManager mm = new OWLAPIR2RMLMappingManagerFactory().getR2RMLMappingManager();
			Set<RDFTriple> triples = OWLAPIUtil.readTurtle(fis);
			Collection<TriplesMap> coll = mm.importMappings(triples);
			
                        File fout = File.createTempFile("artistNew", "ttl");
                        fout.deleteOnExit();
			FileOutputStream fos = new FileOutputStream(fout);
			@SuppressWarnings("unchecked")
			Set<RDFTriple> out = mm.exportMappings(coll, Set.class);
			OWLAPIUtil.writeTurtle(fos, out);
			
			FileInputStream fis1 = new FileInputStream(fout);
			Set<RDFTriple> triples1 = OWLAPIUtil.readTurtle(fis1);
			Collection<TriplesMap> coll1 = mm.importMappings(triples1);
			
			Assert.assertTrue(fis1!=null);
			Assert.assertTrue(coll1 != null && !coll1.isEmpty());
			
			Assert.assertTrue(true);

			
		}catch (NullPointerException e){
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	
	
}
