/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipselabs.mlang.models.soy;

import org.eclipselabs.mlang.models.common.Location;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>If Tag</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipselabs.mlang.models.soy.IfTag#getElseLocation <em>Else Location</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipselabs.mlang.models.soy.SoyPackage#getIfTag()
 * @model
 * @generated
 */
public interface IfTag extends Tag {
	/**
	 * Returns the value of the '<em><b>Else Location</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Else Location</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Else Location</em>' containment reference.
	 * @see #setElseLocation(Location)
	 * @see org.eclipselabs.mlang.models.soy.SoyPackage#getIfTag_ElseLocation()
	 * @model containment="true"
	 * @generated
	 */
	Location getElseLocation();

	/**
	 * Sets the value of the '{@link org.eclipselabs.mlang.models.soy.IfTag#getElseLocation <em>Else Location</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Else Location</em>' containment reference.
	 * @see #getElseLocation()
	 * @generated
	 */
	void setElseLocation(Location value);

} // IfTag
