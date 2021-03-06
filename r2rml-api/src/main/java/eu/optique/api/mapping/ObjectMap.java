/*******************************************************************************
 * Copyright 2013, the Optique Consortium
 * 
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
package eu.optique.api.mapping;

/**
 * R2RML Object Map
 * 
 * @author Marius Strandhaug
 */
public interface ObjectMap extends TermMap {

	/**
	 * Sets the term type of this ObjectMap if it is column-valued or
	 * template-valued. The typeURI must be an instance of the library's
	 * resource class.<br>
	 * The possible values for the term type are:<br>
	 * - rr:IRI<br>
	 * - rr:BlankNode<br>
	 * - rr:Literal
	 * 
	 * @param typeURI
	 *            The term type that will be set.
	 * @throws IllegalStateException
	 *             If the ObjectMap is not column-valued or template-valued.
	 * @throws IllegalArgumentException
	 *             If typeIRI is not a valid term type for an ObjectMap.
	 */
	public void setTermType(Object typeURI);

	/**
	 * Set the language tag of this ObjectMap if the term type is set to
	 * rr:Literal. If the ObjectMap is data typed, the data type will be
	 * removed.
	 * 
	 * @param lang
	 *            The language tag to be set. Must be a valid language tag.
	 * @throws IllegalStateException
	 *             If the term type of this ObjectMap is not rr:Literal.
	 */
	public void setLanguageTag(String lang);

	/**
	 * Set the data type for this ObjectMap. A ObjectMap can have either zero or
	 * one data types. The ObjectMap can only be data typed if the term type is
	 * rr:Literal. If the ObjectMap has a language tag, the language tag will be
	 * removed. The datatypeURI parameter must be an instance of the library's
	 * resource class.
	 * 
	 * @param datatypeURI
	 *            The data type IRI to be set.
	 * @throws IllegalStateException
	 *             If the term type of this ObjectMap is not rr:Literal.
	 */
	public void setDatatype(Object datatypeURI);

	/**
	 * Get the language tag for this ObjectMap. It will return null if the
	 * ObjectMap doesn't have a language tag.
	 * 
	 * @return The language tag of this ObjectMap.
	 */
	public String getLanguageTag();

	/**
	 * Get the data type for this ObjectMap. It will return null if the
	 * ObjectMap is not data typed.
	 * 
	 * @param resourceClass
	 *            Must be equal to (or a superclass of) the library's resource
	 *            class.
	 * @return The data type of this ObjectMap.
	 */
	public <R> R getDatatype(Class<R> resourceClass);

	/**
	 * Remove the data type associated with this ObjectMap. The ObjectMap will
	 * no longer be data typed.
	 */
	public void removeDatatype();

	/**
	 * Removes the language tag from this ObjectMap if there is one.
	 */
	public void removeLanguageTag();

}
