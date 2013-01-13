/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipselabs.mlang.models.html;

import org.eclipselabs.mlang.models.common.Scope;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tag Scope</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipselabs.mlang.models.html.TagScope#getOpening <em>Opening</em>}</li>
 *   <li>{@link org.eclipselabs.mlang.models.html.TagScope#getClosing <em>Closing</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipselabs.mlang.models.html.HtmlPackage#getTagScope()
 * @model
 * @generated
 */
public interface TagScope extends Scope {
	/**
	 * Returns the value of the '<em><b>Opening</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.mlang.models.html.Tag#getOpensScope <em>Opens Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Opening</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Opening</em>' reference.
	 * @see #setOpening(Tag)
	 * @see org.eclipselabs.mlang.models.html.HtmlPackage#getTagScope_Opening()
	 * @see org.eclipselabs.mlang.models.html.Tag#getOpensScope
	 * @model opposite="opensScope"
	 * @generated
	 */
	Tag getOpening();

	/**
	 * Sets the value of the '{@link org.eclipselabs.mlang.models.html.TagScope#getOpening <em>Opening</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Opening</em>' reference.
	 * @see #getOpening()
	 * @generated
	 */
	void setOpening(Tag value);

	/**
	 * Returns the value of the '<em><b>Closing</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipselabs.mlang.models.html.Tag#getClosesScope <em>Closes Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Closing</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Closing</em>' reference.
	 * @see #setClosing(Tag)
	 * @see org.eclipselabs.mlang.models.html.HtmlPackage#getTagScope_Closing()
	 * @see org.eclipselabs.mlang.models.html.Tag#getClosesScope
	 * @model opposite="closesScope"
	 * @generated
	 */
	Tag getClosing();

	/**
	 * Sets the value of the '{@link org.eclipselabs.mlang.models.html.TagScope#getClosing <em>Closing</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Closing</em>' reference.
	 * @see #getClosing()
	 * @generated
	 */
	void setClosing(Tag value);

} // TagScope
