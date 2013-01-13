/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipselabs.mlang.models.soy;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipselabs.mlang.models.common.Location;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tag</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipselabs.mlang.models.soy.Tag#getVariables <em>Variables</em>}</li>
 *   <li>{@link org.eclipselabs.mlang.models.soy.Tag#getTagName <em>Tag Name</em>}</li>
 *   <li>{@link org.eclipselabs.mlang.models.soy.Tag#getParent <em>Parent</em>}</li>
 *   <li>{@link org.eclipselabs.mlang.models.soy.Tag#getChildren <em>Children</em>}</li>
 *   <li>{@link org.eclipselabs.mlang.models.soy.Tag#getOpenLocation <em>Open Location</em>}</li>
 *   <li>{@link org.eclipselabs.mlang.models.soy.Tag#getCloseLocation <em>Close Location</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipselabs.mlang.models.soy.SoyPackage#getTag()
 * @model
 * @generated
 */
public interface Tag extends EObject {
	/**
	 * Returns the value of the '<em><b>Variables</b></em>' reference list.
	 * The list contents are of type {@link org.eclipselabs.mlang.models.soy.Variable}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variables</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variables</em>' reference list.
	 * @see org.eclipselabs.mlang.models.soy.SoyPackage#getTag_Variables()
	 * @model
	 * @generated
	 */
	EList<Variable> getVariables();

	/**
	 * Returns the value of the '<em><b>Tag Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tag Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tag Name</em>' attribute.
	 * @see #setTagName(String)
	 * @see org.eclipselabs.mlang.models.soy.SoyPackage#getTag_TagName()
	 * @model
	 * @generated
	 */
	String getTagName();

	/**
	 * Sets the value of the '{@link org.eclipselabs.mlang.models.soy.Tag#getTagName <em>Tag Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tag Name</em>' attribute.
	 * @see #getTagName()
	 * @generated
	 */
	void setTagName(String value);

	/**
	 * Returns the value of the '<em><b>Parent</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.mlang.models.soy.Tag#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' reference.
	 * @see #setParent(Tag)
	 * @see org.eclipselabs.mlang.models.soy.SoyPackage#getTag_Parent()
	 * @see org.eclipselabs.mlang.models.soy.Tag#getChildren
	 * @model opposite="children"
	 * @generated
	 */
	Tag getParent();

	/**
	 * Sets the value of the '{@link org.eclipselabs.mlang.models.soy.Tag#getParent <em>Parent</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(Tag value);

	/**
	 * Returns the value of the '<em><b>Children</b></em>' reference list.
	 * The list contents are of type {@link org.eclipselabs.mlang.models.soy.Tag}.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.mlang.models.soy.Tag#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' reference list.
	 * @see org.eclipselabs.mlang.models.soy.SoyPackage#getTag_Children()
	 * @see org.eclipselabs.mlang.models.soy.Tag#getParent
	 * @model opposite="parent"
	 * @generated
	 */
	EList<Tag> getChildren();

	/**
	 * Returns the value of the '<em><b>Open Location</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Open Location</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Open Location</em>' containment reference.
	 * @see #setOpenLocation(Location)
	 * @see org.eclipselabs.mlang.models.soy.SoyPackage#getTag_OpenLocation()
	 * @model containment="true"
	 * @generated
	 */
	Location getOpenLocation();

	/**
	 * Sets the value of the '{@link org.eclipselabs.mlang.models.soy.Tag#getOpenLocation <em>Open Location</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Open Location</em>' containment reference.
	 * @see #getOpenLocation()
	 * @generated
	 */
	void setOpenLocation(Location value);

	/**
	 * Returns the value of the '<em><b>Close Location</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Close Location</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Close Location</em>' containment reference.
	 * @see #setCloseLocation(Location)
	 * @see org.eclipselabs.mlang.models.soy.SoyPackage#getTag_CloseLocation()
	 * @model containment="true"
	 * @generated
	 */
	Location getCloseLocation();

	/**
	 * Sets the value of the '{@link org.eclipselabs.mlang.models.soy.Tag#getCloseLocation <em>Close Location</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Close Location</em>' containment reference.
	 * @see #getCloseLocation()
	 * @generated
	 */
	void setCloseLocation(Location value);

} // Tag
