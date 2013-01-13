/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipselabs.mlang.models.soy;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Param</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipselabs.mlang.models.soy.Param#isOptional <em>Optional</em>}</li>
 *   <li>{@link org.eclipselabs.mlang.models.soy.Param#getDoclet <em>Doclet</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipselabs.mlang.models.soy.SoyPackage#getParam()
 * @model
 * @generated
 */
public interface Param extends Variable {
	/**
	 * Returns the value of the '<em><b>Optional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Optional</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Optional</em>' attribute.
	 * @see #setOptional(boolean)
	 * @see org.eclipselabs.mlang.models.soy.SoyPackage#getParam_Optional()
	 * @model
	 * @generated
	 */
	boolean isOptional();

	/**
	 * Sets the value of the '{@link org.eclipselabs.mlang.models.soy.Param#isOptional <em>Optional</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Optional</em>' attribute.
	 * @see #isOptional()
	 * @generated
	 */
	void setOptional(boolean value);

	/**
	 * Returns the value of the '<em><b>Doclet</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.mlang.models.soy.Doclet#getParams <em>Params</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Doclet</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Doclet</em>' reference.
	 * @see #setDoclet(Doclet)
	 * @see org.eclipselabs.mlang.models.soy.SoyPackage#getParam_Doclet()
	 * @see org.eclipselabs.mlang.models.soy.Doclet#getParams
	 * @model opposite="params"
	 * @generated
	 */
	Doclet getDoclet();

	/**
	 * Sets the value of the '{@link org.eclipselabs.mlang.models.soy.Param#getDoclet <em>Doclet</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Doclet</em>' reference.
	 * @see #getDoclet()
	 * @generated
	 */
	void setDoclet(Doclet value);

} // Param
