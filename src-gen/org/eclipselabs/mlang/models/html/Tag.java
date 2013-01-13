/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipselabs.mlang.models.html;

import org.eclipselabs.mlang.models.common.Located;
import org.eclipselabs.mlang.models.common.Named;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tag</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipselabs.mlang.models.html.Tag#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipselabs.mlang.models.html.Tag#getOpensScope <em>Opens Scope</em>}</li>
 *   <li>{@link org.eclipselabs.mlang.models.html.Tag#getClosesScope <em>Closes Scope</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipselabs.mlang.models.html.HtmlPackage#getTag()
 * @model
 * @generated
 */
public interface Tag extends Named, Located {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipselabs.mlang.models.html.TagType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see org.eclipselabs.mlang.models.html.TagType
	 * @see #setType(TagType)
	 * @see org.eclipselabs.mlang.models.html.HtmlPackage#getTag_Type()
	 * @model
	 * @generated
	 */
	TagType getType();

	/**
	 * Sets the value of the '{@link org.eclipselabs.mlang.models.html.Tag#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see org.eclipselabs.mlang.models.html.TagType
	 * @see #getType()
	 * @generated
	 */
	void setType(TagType value);

	/**
	 * Returns the value of the '<em><b>Opens Scope</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.mlang.models.html.TagScope#getOpening <em>Opening</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Opens Scope</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Opens Scope</em>' reference.
	 * @see #setOpensScope(TagScope)
	 * @see org.eclipselabs.mlang.models.html.HtmlPackage#getTag_OpensScope()
	 * @see org.eclipselabs.mlang.models.html.TagScope#getOpening
	 * @model opposite="opening"
	 * @generated
	 */
	TagScope getOpensScope();

	/**
	 * Sets the value of the '{@link org.eclipselabs.mlang.models.html.Tag#getOpensScope <em>Opens Scope</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Opens Scope</em>' reference.
	 * @see #getOpensScope()
	 * @generated
	 */
	void setOpensScope(TagScope value);

	/**
	 * Returns the value of the '<em><b>Closes Scope</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.mlang.models.html.TagScope#getClosing <em>Closing</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Closes Scope</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Closes Scope</em>' reference.
	 * @see #setClosesScope(TagScope)
	 * @see org.eclipselabs.mlang.models.html.HtmlPackage#getTag_ClosesScope()
	 * @see org.eclipselabs.mlang.models.html.TagScope#getClosing
	 * @model opposite="closing"
	 * @generated
	 */
	TagScope getClosesScope();

	/**
	 * Sets the value of the '{@link org.eclipselabs.mlang.models.html.Tag#getClosesScope <em>Closes Scope</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Closes Scope</em>' reference.
	 * @see #getClosesScope()
	 * @generated
	 */
	void setClosesScope(TagScope value);

} // Tag
