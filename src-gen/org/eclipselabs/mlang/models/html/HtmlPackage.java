/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipselabs.mlang.models.html;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipselabs.mlang.models.common.CommonPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipselabs.mlang.models.html.HtmlFactory
 * @model kind="package"
 * @generated
 */
public interface HtmlPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "html";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://eclipselabs.org/mlang/soy";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.eclipselabs.mlang";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	HtmlPackage eINSTANCE = org.eclipselabs.mlang.models.html.impl.HtmlPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipselabs.mlang.models.html.impl.TagImpl <em>Tag</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipselabs.mlang.models.html.impl.TagImpl
	 * @see org.eclipselabs.mlang.models.html.impl.HtmlPackageImpl#getTag()
	 * @generated
	 */
	int TAG = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__NAME = CommonPackage.NAMED__NAME;

	/**
	 * The feature id for the '<em><b>Offset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__OFFSET = CommonPackage.NAMED_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__LENGTH = CommonPackage.NAMED_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__TYPE = CommonPackage.NAMED_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Opens Scope</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__OPENS_SCOPE = CommonPackage.NAMED_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Closes Scope</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__CLOSES_SCOPE = CommonPackage.NAMED_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Tag</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_FEATURE_COUNT = CommonPackage.NAMED_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.eclipselabs.mlang.models.html.impl.TagScopeImpl <em>Tag Scope</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipselabs.mlang.models.html.impl.TagScopeImpl
	 * @see org.eclipselabs.mlang.models.html.impl.HtmlPackageImpl#getTagScope()
	 * @generated
	 */
	int TAG_SCOPE = 1;

	/**
	 * The feature id for the '<em><b>Offset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_SCOPE__OFFSET = CommonPackage.SCOPE__OFFSET;

	/**
	 * The feature id for the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_SCOPE__LENGTH = CommonPackage.SCOPE__LENGTH;

	/**
	 * The feature id for the '<em><b>Body</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_SCOPE__BODY = CommonPackage.SCOPE__BODY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_SCOPE__PARENT = CommonPackage.SCOPE__PARENT;

	/**
	 * The feature id for the '<em><b>Opening</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_SCOPE__OPENING = CommonPackage.SCOPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Closing</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_SCOPE__CLOSING = CommonPackage.SCOPE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Tag Scope</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_SCOPE_FEATURE_COUNT = CommonPackage.SCOPE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipselabs.mlang.models.html.impl.JSScopeImpl <em>JS Scope</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipselabs.mlang.models.html.impl.JSScopeImpl
	 * @see org.eclipselabs.mlang.models.html.impl.HtmlPackageImpl#getJSScope()
	 * @generated
	 */
	int JS_SCOPE = 2;

	/**
	 * The feature id for the '<em><b>Offset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JS_SCOPE__OFFSET = TAG_SCOPE__OFFSET;

	/**
	 * The feature id for the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JS_SCOPE__LENGTH = TAG_SCOPE__LENGTH;

	/**
	 * The feature id for the '<em><b>Body</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JS_SCOPE__BODY = TAG_SCOPE__BODY;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JS_SCOPE__PARENT = TAG_SCOPE__PARENT;

	/**
	 * The feature id for the '<em><b>Opening</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JS_SCOPE__OPENING = TAG_SCOPE__OPENING;

	/**
	 * The feature id for the '<em><b>Closing</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JS_SCOPE__CLOSING = TAG_SCOPE__CLOSING;

	/**
	 * The number of structural features of the '<em>JS Scope</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JS_SCOPE_FEATURE_COUNT = TAG_SCOPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipselabs.mlang.models.html.TagType <em>Tag Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipselabs.mlang.models.html.TagType
	 * @see org.eclipselabs.mlang.models.html.impl.HtmlPackageImpl#getTagType()
	 * @generated
	 */
	int TAG_TYPE = 3;


	/**
	 * Returns the meta object for class '{@link org.eclipselabs.mlang.models.html.Tag <em>Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag</em>'.
	 * @see org.eclipselabs.mlang.models.html.Tag
	 * @generated
	 */
	EClass getTag();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipselabs.mlang.models.html.Tag#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipselabs.mlang.models.html.Tag#getType()
	 * @see #getTag()
	 * @generated
	 */
	EAttribute getTag_Type();

	/**
	 * Returns the meta object for the reference '{@link org.eclipselabs.mlang.models.html.Tag#getOpensScope <em>Opens Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Opens Scope</em>'.
	 * @see org.eclipselabs.mlang.models.html.Tag#getOpensScope()
	 * @see #getTag()
	 * @generated
	 */
	EReference getTag_OpensScope();

	/**
	 * Returns the meta object for the reference '{@link org.eclipselabs.mlang.models.html.Tag#getClosesScope <em>Closes Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Closes Scope</em>'.
	 * @see org.eclipselabs.mlang.models.html.Tag#getClosesScope()
	 * @see #getTag()
	 * @generated
	 */
	EReference getTag_ClosesScope();

	/**
	 * Returns the meta object for class '{@link org.eclipselabs.mlang.models.html.TagScope <em>Tag Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag Scope</em>'.
	 * @see org.eclipselabs.mlang.models.html.TagScope
	 * @generated
	 */
	EClass getTagScope();

	/**
	 * Returns the meta object for the reference '{@link org.eclipselabs.mlang.models.html.TagScope#getOpening <em>Opening</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Opening</em>'.
	 * @see org.eclipselabs.mlang.models.html.TagScope#getOpening()
	 * @see #getTagScope()
	 * @generated
	 */
	EReference getTagScope_Opening();

	/**
	 * Returns the meta object for the reference '{@link org.eclipselabs.mlang.models.html.TagScope#getClosing <em>Closing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Closing</em>'.
	 * @see org.eclipselabs.mlang.models.html.TagScope#getClosing()
	 * @see #getTagScope()
	 * @generated
	 */
	EReference getTagScope_Closing();

	/**
	 * Returns the meta object for class '{@link org.eclipselabs.mlang.models.html.JSScope <em>JS Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>JS Scope</em>'.
	 * @see org.eclipselabs.mlang.models.html.JSScope
	 * @generated
	 */
	EClass getJSScope();

	/**
	 * Returns the meta object for enum '{@link org.eclipselabs.mlang.models.html.TagType <em>Tag Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Tag Type</em>'.
	 * @see org.eclipselabs.mlang.models.html.TagType
	 * @generated
	 */
	EEnum getTagType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	HtmlFactory getHtmlFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipselabs.mlang.models.html.impl.TagImpl <em>Tag</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipselabs.mlang.models.html.impl.TagImpl
		 * @see org.eclipselabs.mlang.models.html.impl.HtmlPackageImpl#getTag()
		 * @generated
		 */
		EClass TAG = eINSTANCE.getTag();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG__TYPE = eINSTANCE.getTag_Type();

		/**
		 * The meta object literal for the '<em><b>Opens Scope</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAG__OPENS_SCOPE = eINSTANCE.getTag_OpensScope();

		/**
		 * The meta object literal for the '<em><b>Closes Scope</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAG__CLOSES_SCOPE = eINSTANCE.getTag_ClosesScope();

		/**
		 * The meta object literal for the '{@link org.eclipselabs.mlang.models.html.impl.TagScopeImpl <em>Tag Scope</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipselabs.mlang.models.html.impl.TagScopeImpl
		 * @see org.eclipselabs.mlang.models.html.impl.HtmlPackageImpl#getTagScope()
		 * @generated
		 */
		EClass TAG_SCOPE = eINSTANCE.getTagScope();

		/**
		 * The meta object literal for the '<em><b>Opening</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAG_SCOPE__OPENING = eINSTANCE.getTagScope_Opening();

		/**
		 * The meta object literal for the '<em><b>Closing</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAG_SCOPE__CLOSING = eINSTANCE.getTagScope_Closing();

		/**
		 * The meta object literal for the '{@link org.eclipselabs.mlang.models.html.impl.JSScopeImpl <em>JS Scope</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipselabs.mlang.models.html.impl.JSScopeImpl
		 * @see org.eclipselabs.mlang.models.html.impl.HtmlPackageImpl#getJSScope()
		 * @generated
		 */
		EClass JS_SCOPE = eINSTANCE.getJSScope();

		/**
		 * The meta object literal for the '{@link org.eclipselabs.mlang.models.html.TagType <em>Tag Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipselabs.mlang.models.html.TagType
		 * @see org.eclipselabs.mlang.models.html.impl.HtmlPackageImpl#getTagType()
		 * @generated
		 */
		EEnum TAG_TYPE = eINSTANCE.getTagType();

	}

} //HtmlPackage
