/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipselabs.mlang.models.soy;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Template Tag</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipselabs.mlang.models.soy.TemplateTag#getDoclet <em>Doclet</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipselabs.mlang.models.soy.SoyPackage#getTemplateTag()
 * @model
 * @generated
 */
public interface TemplateTag extends Tag, NamedTag {
	/**
	 * Returns the value of the '<em><b>Doclet</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.mlang.models.soy.Doclet#getTemplate <em>Template</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Doclet</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Doclet</em>' reference.
	 * @see #setDoclet(Doclet)
	 * @see org.eclipselabs.mlang.models.soy.SoyPackage#getTemplateTag_Doclet()
	 * @see org.eclipselabs.mlang.models.soy.Doclet#getTemplate
	 * @model opposite="template"
	 * @generated
	 */
	Doclet getDoclet();

	/**
	 * Sets the value of the '{@link org.eclipselabs.mlang.models.soy.TemplateTag#getDoclet <em>Doclet</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Doclet</em>' reference.
	 * @see #getDoclet()
	 * @generated
	 */
	void setDoclet(Doclet value);

} // TemplateTag
