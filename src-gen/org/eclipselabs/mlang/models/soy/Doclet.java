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
 * A representation of the model object '<em><b>Doclet</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipselabs.mlang.models.soy.Doclet#getLocation <em>Location</em>}</li>
 *   <li>{@link org.eclipselabs.mlang.models.soy.Doclet#getParams <em>Params</em>}</li>
 *   <li>{@link org.eclipselabs.mlang.models.soy.Doclet#getTemplate <em>Template</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipselabs.mlang.models.soy.SoyPackage#getDoclet()
 * @model
 * @generated
 */
public interface Doclet extends EObject {
	/**
	 * Returns the value of the '<em><b>Location</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Location</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Location</em>' containment reference.
	 * @see #setLocation(Location)
	 * @see org.eclipselabs.mlang.models.soy.SoyPackage#getDoclet_Location()
	 * @model containment="true"
	 * @generated
	 */
	Location getLocation();

	/**
	 * Sets the value of the '{@link org.eclipselabs.mlang.models.soy.Doclet#getLocation <em>Location</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Location</em>' containment reference.
	 * @see #getLocation()
	 * @generated
	 */
	void setLocation(Location value);

	/**
	 * Returns the value of the '<em><b>Params</b></em>' reference list.
	 * The list contents are of type {@link org.eclipselabs.mlang.models.soy.Param}.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.mlang.models.soy.Param#getDoclet <em>Doclet</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Params</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Params</em>' reference list.
	 * @see org.eclipselabs.mlang.models.soy.SoyPackage#getDoclet_Params()
	 * @see org.eclipselabs.mlang.models.soy.Param#getDoclet
	 * @model opposite="doclet"
	 * @generated
	 */
	EList<Param> getParams();

	/**
	 * Returns the value of the '<em><b>Template</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.mlang.models.soy.TemplateTag#getDoclet <em>Doclet</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Template</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Template</em>' reference.
	 * @see #setTemplate(TemplateTag)
	 * @see org.eclipselabs.mlang.models.soy.SoyPackage#getDoclet_Template()
	 * @see org.eclipselabs.mlang.models.soy.TemplateTag#getDoclet
	 * @model opposite="doclet"
	 * @generated
	 */
	TemplateTag getTemplate();

	/**
	 * Sets the value of the '{@link org.eclipselabs.mlang.models.soy.Doclet#getTemplate <em>Template</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Template</em>' reference.
	 * @see #getTemplate()
	 * @generated
	 */
	void setTemplate(TemplateTag value);

} // Doclet
