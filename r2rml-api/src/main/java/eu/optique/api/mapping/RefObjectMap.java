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

import java.util.List;

/**
 * R2RML Referencing Object Map
 * 
 * @author Marius Strandhaug
 */
public interface RefObjectMap extends SerializeR2RML, ManageResource {

	/**
	 * Sets the resource for the parent triples map of this RefObjectMap. A
	 * RefObjectMap must have exactly one parent triples map resource. This
	 * method will overwrite any previously set parent triples map resources.
	 * The tm parameter must be an instance of the library's resource class.
	 * 
	 * @param tm
	 *            The resource for the triples map that will be set as the
	 *            parent triples map.
	 * @throws NullPointerException
	 *             If tm is null.
	 */
	public void setParentMap(Object tm);

	/**
	 * Adds the logical table of the parent triples map to this RefObjectMap.
	 * This needs to be set to be able to use the getParentQuery and
	 * getJointQuery methods.
	 * 
	 * @param lt
	 *            The logical table of this RefObjectMap's parent triples map.
	 */
	public void setParentLogicalTable(LogicalTable lt);

	/**
	 * Adds the logical table of the triples map that this RefObjectMap is
	 * contained in. This needs to be set to be able to use the getChildQuery
	 * and getJointQuery methods.
	 * 
	 * @param lt
	 *            The logical table of the triples map that contains this
	 *            RefObjectMap.
	 */
	public void setChildLogicalTable(LogicalTable lt);

	/**
	 * Returns the child query for this RefObjectMap. The child query is the
	 * effective SQL query of the LogicalTable of the TriplesMap that contains
	 * the RefObjectMap.
	 * 
	 * @return The child query for this RefObjectMap.
	 * @throws NullPointerException
	 *             If the child logical table is null.
	 */
	public String getChildQuery();

	/**
	 * Returns the parent query for this RefObjectMap. The parent query is the
	 * effective SQL query of the LogicalTable of the RefObjectMap's parent
	 * TriplesMap.
	 * 
	 * @return The parent query for this RefObjectMap.
	 * @throws NullPointerException
	 *             If the parent logical table is null.
	 */
	public String getParentQuery();

	/**
	 * Returns the joint query for this RefObjectMap. The specification on what
	 * this query will look like can be found here:
	 * http://www.w3.org/TR/r2rml/#dfn-joint-sql-query
	 * 
	 * @return The joint query for this RefObjectMap.
	 */
	public String getJointQuery();

	/**
	 * Add a join condition to this RefObjectMap. The Join will be added to the
	 * end of the Join list. A RefObjectMap can have any number of join
	 * conditions.
	 * 
	 * @param j
	 *            The join condition to be added.
	 */
	public void addJoinCondition(Join j);

	/**
	 * Get the Join located at the given index.
	 * 
	 * @param index
	 *            The index of the Join.
	 * @return The Join located at the given index.
	 * @throws IndexOutOfBoundsException
	 *             If the given index is out of range.
	 */
	public Join getJoinCondition(int index);

	/**
	 * Returns an unmodifiable view of the list of Joins that have been added to
	 * this RefObjectMap.
	 * 
	 * @return An unmodifiable list of Joins.
	 */
	public List<Join> getJoinConditions();

	/**
	 * Get the resource for the parent triples map of this RefObjectMap.
	 * 
	 * @param resourceClass
	 *            Must be equal to (or a superclass of) the library's resource
	 *            class.
	 * @return The parent triples map.
	 */
	public <R> R getParentMap(Class<R> resourceClass);

	/**
	 * Removes the given join condition from this RefObjectMap. The subsequent
	 * Joins in the list will be shifted left.
	 * 
	 * @param j
	 *            The join condition that will be removed.
	 */
	public void removeJoinCondition(Join j);

}
