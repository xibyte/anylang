/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipselabs.mlang.models.common;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Scope</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipselabs.mlang.models.common.Scope#getBody <em>Body</em>}</li>
 *   <li>{@link org.eclipselabs.mlang.models.common.Scope#getParent <em>Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipselabs.mlang.models.common.CommonPackage#getScope()
 * @model
 * @generated
 */
public interface Scope extends Located {
	/**
	 * Returns the value of the '<em><b>Body</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Body</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Body</em>' containment reference list.
	 * @see org.eclipselabs.mlang.models.common.CommonPackage#getScope_Body()
	 * @model containment="true"
	 * @generated
	 */
	EList<EObject> getBody();

	/**
	 * Returns the value of the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' reference.
	 * @see #setParent(Scope)
	 * @see org.eclipselabs.mlang.models.common.CommonPackage#getScope_Parent()
	 * @model
	 * @generated
	 */
	Scope getParent();

	/**
	 * Sets the value of the '{@link org.eclipselabs.mlang.models.common.Scope#getParent <em>Parent</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(Scope value);

} // Scope
