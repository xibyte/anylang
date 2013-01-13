/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipselabs.mlang.models.soy;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see org.eclipselabs.mlang.models.soy.SoyFactory
 * @model kind="package"
 * @generated
 */
public interface SoyPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "soy";

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
	SoyPackage eINSTANCE = org.eclipselabs.mlang.models.soy.impl.SoyPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipselabs.mlang.models.soy.impl.TagImpl <em>Tag</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipselabs.mlang.models.soy.impl.TagImpl
	 * @see org.eclipselabs.mlang.models.soy.impl.SoyPackageImpl#getTag()
	 * @generated
	 */
	int TAG = 0;

	/**
	 * The feature id for the '<em><b>Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__VARIABLES = 0;

	/**
	 * The feature id for the '<em><b>Tag Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__TAG_NAME = 1;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__PARENT = 2;

	/**
	 * The feature id for the '<em><b>Children</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__CHILDREN = 3;

	/**
	 * The feature id for the '<em><b>Open Location</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__OPEN_LOCATION = 4;

	/**
	 * The feature id for the '<em><b>Close Location</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__CLOSE_LOCATION = 5;

	/**
	 * The number of structural features of the '<em>Tag</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link org.eclipselabs.mlang.models.soy.impl.VariableImpl <em>Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipselabs.mlang.models.soy.impl.VariableImpl
	 * @see org.eclipselabs.mlang.models.soy.impl.SoyPackageImpl#getVariable()
	 * @generated
	 */
	int VARIABLE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Location</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__LOCATION = 1;

	/**
	 * The number of structural features of the '<em>Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipselabs.mlang.models.soy.impl.TemplateTagImpl <em>Template Tag</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipselabs.mlang.models.soy.impl.TemplateTagImpl
	 * @see org.eclipselabs.mlang.models.soy.impl.SoyPackageImpl#getTemplateTag()
	 * @generated
	 */
	int TEMPLATE_TAG = 2;

	/**
	 * The feature id for the '<em><b>Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_TAG__VARIABLES = TAG__VARIABLES;

	/**
	 * The feature id for the '<em><b>Tag Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_TAG__TAG_NAME = TAG__TAG_NAME;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_TAG__PARENT = TAG__PARENT;

	/**
	 * The feature id for the '<em><b>Children</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_TAG__CHILDREN = TAG__CHILDREN;

	/**
	 * The feature id for the '<em><b>Open Location</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_TAG__OPEN_LOCATION = TAG__OPEN_LOCATION;

	/**
	 * The feature id for the '<em><b>Close Location</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_TAG__CLOSE_LOCATION = TAG__CLOSE_LOCATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_TAG__NAME = TAG_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Doclet</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_TAG__DOCLET = TAG_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Template Tag</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_TAG_FEATURE_COUNT = TAG_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipselabs.mlang.models.soy.impl.IfTagImpl <em>If Tag</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipselabs.mlang.models.soy.impl.IfTagImpl
	 * @see org.eclipselabs.mlang.models.soy.impl.SoyPackageImpl#getIfTag()
	 * @generated
	 */
	int IF_TAG = 3;

	/**
	 * The feature id for the '<em><b>Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_TAG__VARIABLES = TAG__VARIABLES;

	/**
	 * The feature id for the '<em><b>Tag Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_TAG__TAG_NAME = TAG__TAG_NAME;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_TAG__PARENT = TAG__PARENT;

	/**
	 * The feature id for the '<em><b>Children</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_TAG__CHILDREN = TAG__CHILDREN;

	/**
	 * The feature id for the '<em><b>Open Location</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_TAG__OPEN_LOCATION = TAG__OPEN_LOCATION;

	/**
	 * The feature id for the '<em><b>Close Location</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_TAG__CLOSE_LOCATION = TAG__CLOSE_LOCATION;

	/**
	 * The feature id for the '<em><b>Else Location</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_TAG__ELSE_LOCATION = TAG_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>If Tag</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_TAG_FEATURE_COUNT = TAG_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipselabs.mlang.models.soy.impl.DocletImpl <em>Doclet</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipselabs.mlang.models.soy.impl.DocletImpl
	 * @see org.eclipselabs.mlang.models.soy.impl.SoyPackageImpl#getDoclet()
	 * @generated
	 */
	int DOCLET = 4;

	/**
	 * The feature id for the '<em><b>Location</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCLET__LOCATION = 0;

	/**
	 * The feature id for the '<em><b>Params</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCLET__PARAMS = 1;

	/**
	 * The feature id for the '<em><b>Template</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCLET__TEMPLATE = 2;

	/**
	 * The number of structural features of the '<em>Doclet</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCLET_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipselabs.mlang.models.soy.impl.ParamImpl <em>Param</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipselabs.mlang.models.soy.impl.ParamImpl
	 * @see org.eclipselabs.mlang.models.soy.impl.SoyPackageImpl#getParam()
	 * @generated
	 */
	int PARAM = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAM__NAME = VARIABLE__NAME;

	/**
	 * The feature id for the '<em><b>Location</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAM__LOCATION = VARIABLE__LOCATION;

	/**
	 * The feature id for the '<em><b>Optional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAM__OPTIONAL = VARIABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Doclet</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAM__DOCLET = VARIABLE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Param</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAM_FEATURE_COUNT = VARIABLE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipselabs.mlang.models.soy.NamedTag <em>Named Tag</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipselabs.mlang.models.soy.NamedTag
	 * @see org.eclipselabs.mlang.models.soy.impl.SoyPackageImpl#getNamedTag()
	 * @generated
	 */
	int NAMED_TAG = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_TAG__NAME = 0;

	/**
	 * The number of structural features of the '<em>Named Tag</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_TAG_FEATURE_COUNT = 1;


	/**
	 * Returns the meta object for class '{@link org.eclipselabs.mlang.models.soy.Tag <em>Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag</em>'.
	 * @see org.eclipselabs.mlang.models.soy.Tag
	 * @generated
	 */
	EClass getTag();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipselabs.mlang.models.soy.Tag#getVariables <em>Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Variables</em>'.
	 * @see org.eclipselabs.mlang.models.soy.Tag#getVariables()
	 * @see #getTag()
	 * @generated
	 */
	EReference getTag_Variables();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipselabs.mlang.models.soy.Tag#getTagName <em>Tag Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tag Name</em>'.
	 * @see org.eclipselabs.mlang.models.soy.Tag#getTagName()
	 * @see #getTag()
	 * @generated
	 */
	EAttribute getTag_TagName();

	/**
	 * Returns the meta object for the reference '{@link org.eclipselabs.mlang.models.soy.Tag#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parent</em>'.
	 * @see org.eclipselabs.mlang.models.soy.Tag#getParent()
	 * @see #getTag()
	 * @generated
	 */
	EReference getTag_Parent();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipselabs.mlang.models.soy.Tag#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Children</em>'.
	 * @see org.eclipselabs.mlang.models.soy.Tag#getChildren()
	 * @see #getTag()
	 * @generated
	 */
	EReference getTag_Children();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipselabs.mlang.models.soy.Tag#getOpenLocation <em>Open Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Open Location</em>'.
	 * @see org.eclipselabs.mlang.models.soy.Tag#getOpenLocation()
	 * @see #getTag()
	 * @generated
	 */
	EReference getTag_OpenLocation();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipselabs.mlang.models.soy.Tag#getCloseLocation <em>Close Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Close Location</em>'.
	 * @see org.eclipselabs.mlang.models.soy.Tag#getCloseLocation()
	 * @see #getTag()
	 * @generated
	 */
	EReference getTag_CloseLocation();

	/**
	 * Returns the meta object for class '{@link org.eclipselabs.mlang.models.soy.Variable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable</em>'.
	 * @see org.eclipselabs.mlang.models.soy.Variable
	 * @generated
	 */
	EClass getVariable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipselabs.mlang.models.soy.Variable#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipselabs.mlang.models.soy.Variable#getName()
	 * @see #getVariable()
	 * @generated
	 */
	EAttribute getVariable_Name();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipselabs.mlang.models.soy.Variable#getLocation <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Location</em>'.
	 * @see org.eclipselabs.mlang.models.soy.Variable#getLocation()
	 * @see #getVariable()
	 * @generated
	 */
	EReference getVariable_Location();

	/**
	 * Returns the meta object for class '{@link org.eclipselabs.mlang.models.soy.TemplateTag <em>Template Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Template Tag</em>'.
	 * @see org.eclipselabs.mlang.models.soy.TemplateTag
	 * @generated
	 */
	EClass getTemplateTag();

	/**
	 * Returns the meta object for the reference '{@link org.eclipselabs.mlang.models.soy.TemplateTag#getDoclet <em>Doclet</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Doclet</em>'.
	 * @see org.eclipselabs.mlang.models.soy.TemplateTag#getDoclet()
	 * @see #getTemplateTag()
	 * @generated
	 */
	EReference getTemplateTag_Doclet();

	/**
	 * Returns the meta object for class '{@link org.eclipselabs.mlang.models.soy.IfTag <em>If Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>If Tag</em>'.
	 * @see org.eclipselabs.mlang.models.soy.IfTag
	 * @generated
	 */
	EClass getIfTag();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipselabs.mlang.models.soy.IfTag#getElseLocation <em>Else Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Else Location</em>'.
	 * @see org.eclipselabs.mlang.models.soy.IfTag#getElseLocation()
	 * @see #getIfTag()
	 * @generated
	 */
	EReference getIfTag_ElseLocation();

	/**
	 * Returns the meta object for class '{@link org.eclipselabs.mlang.models.soy.Doclet <em>Doclet</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Doclet</em>'.
	 * @see org.eclipselabs.mlang.models.soy.Doclet
	 * @generated
	 */
	EClass getDoclet();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipselabs.mlang.models.soy.Doclet#getLocation <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Location</em>'.
	 * @see org.eclipselabs.mlang.models.soy.Doclet#getLocation()
	 * @see #getDoclet()
	 * @generated
	 */
	EReference getDoclet_Location();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipselabs.mlang.models.soy.Doclet#getParams <em>Params</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Params</em>'.
	 * @see org.eclipselabs.mlang.models.soy.Doclet#getParams()
	 * @see #getDoclet()
	 * @generated
	 */
	EReference getDoclet_Params();

	/**
	 * Returns the meta object for the reference '{@link org.eclipselabs.mlang.models.soy.Doclet#getTemplate <em>Template</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Template</em>'.
	 * @see org.eclipselabs.mlang.models.soy.Doclet#getTemplate()
	 * @see #getDoclet()
	 * @generated
	 */
	EReference getDoclet_Template();

	/**
	 * Returns the meta object for class '{@link org.eclipselabs.mlang.models.soy.Param <em>Param</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Param</em>'.
	 * @see org.eclipselabs.mlang.models.soy.Param
	 * @generated
	 */
	EClass getParam();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipselabs.mlang.models.soy.Param#isOptional <em>Optional</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Optional</em>'.
	 * @see org.eclipselabs.mlang.models.soy.Param#isOptional()
	 * @see #getParam()
	 * @generated
	 */
	EAttribute getParam_Optional();

	/**
	 * Returns the meta object for the reference '{@link org.eclipselabs.mlang.models.soy.Param#getDoclet <em>Doclet</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Doclet</em>'.
	 * @see org.eclipselabs.mlang.models.soy.Param#getDoclet()
	 * @see #getParam()
	 * @generated
	 */
	EReference getParam_Doclet();

	/**
	 * Returns the meta object for class '{@link org.eclipselabs.mlang.models.soy.NamedTag <em>Named Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Named Tag</em>'.
	 * @see org.eclipselabs.mlang.models.soy.NamedTag
	 * @generated
	 */
	EClass getNamedTag();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipselabs.mlang.models.soy.NamedTag#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipselabs.mlang.models.soy.NamedTag#getName()
	 * @see #getNamedTag()
	 * @generated
	 */
	EAttribute getNamedTag_Name();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SoyFactory getSoyFactory();

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
		 * The meta object literal for the '{@link org.eclipselabs.mlang.models.soy.impl.TagImpl <em>Tag</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipselabs.mlang.models.soy.impl.TagImpl
		 * @see org.eclipselabs.mlang.models.soy.impl.SoyPackageImpl#getTag()
		 * @generated
		 */
		EClass TAG = eINSTANCE.getTag();

		/**
		 * The meta object literal for the '<em><b>Variables</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAG__VARIABLES = eINSTANCE.getTag_Variables();

		/**
		 * The meta object literal for the '<em><b>Tag Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG__TAG_NAME = eINSTANCE.getTag_TagName();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAG__PARENT = eINSTANCE.getTag_Parent();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAG__CHILDREN = eINSTANCE.getTag_Children();

		/**
		 * The meta object literal for the '<em><b>Open Location</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAG__OPEN_LOCATION = eINSTANCE.getTag_OpenLocation();

		/**
		 * The meta object literal for the '<em><b>Close Location</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAG__CLOSE_LOCATION = eINSTANCE.getTag_CloseLocation();

		/**
		 * The meta object literal for the '{@link org.eclipselabs.mlang.models.soy.impl.VariableImpl <em>Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipselabs.mlang.models.soy.impl.VariableImpl
		 * @see org.eclipselabs.mlang.models.soy.impl.SoyPackageImpl#getVariable()
		 * @generated
		 */
		EClass VARIABLE = eINSTANCE.getVariable();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE__NAME = eINSTANCE.getVariable_Name();

		/**
		 * The meta object literal for the '<em><b>Location</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE__LOCATION = eINSTANCE.getVariable_Location();

		/**
		 * The meta object literal for the '{@link org.eclipselabs.mlang.models.soy.impl.TemplateTagImpl <em>Template Tag</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipselabs.mlang.models.soy.impl.TemplateTagImpl
		 * @see org.eclipselabs.mlang.models.soy.impl.SoyPackageImpl#getTemplateTag()
		 * @generated
		 */
		EClass TEMPLATE_TAG = eINSTANCE.getTemplateTag();

		/**
		 * The meta object literal for the '<em><b>Doclet</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEMPLATE_TAG__DOCLET = eINSTANCE.getTemplateTag_Doclet();

		/**
		 * The meta object literal for the '{@link org.eclipselabs.mlang.models.soy.impl.IfTagImpl <em>If Tag</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipselabs.mlang.models.soy.impl.IfTagImpl
		 * @see org.eclipselabs.mlang.models.soy.impl.SoyPackageImpl#getIfTag()
		 * @generated
		 */
		EClass IF_TAG = eINSTANCE.getIfTag();

		/**
		 * The meta object literal for the '<em><b>Else Location</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IF_TAG__ELSE_LOCATION = eINSTANCE.getIfTag_ElseLocation();

		/**
		 * The meta object literal for the '{@link org.eclipselabs.mlang.models.soy.impl.DocletImpl <em>Doclet</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipselabs.mlang.models.soy.impl.DocletImpl
		 * @see org.eclipselabs.mlang.models.soy.impl.SoyPackageImpl#getDoclet()
		 * @generated
		 */
		EClass DOCLET = eINSTANCE.getDoclet();

		/**
		 * The meta object literal for the '<em><b>Location</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCLET__LOCATION = eINSTANCE.getDoclet_Location();

		/**
		 * The meta object literal for the '<em><b>Params</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCLET__PARAMS = eINSTANCE.getDoclet_Params();

		/**
		 * The meta object literal for the '<em><b>Template</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCLET__TEMPLATE = eINSTANCE.getDoclet_Template();

		/**
		 * The meta object literal for the '{@link org.eclipselabs.mlang.models.soy.impl.ParamImpl <em>Param</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipselabs.mlang.models.soy.impl.ParamImpl
		 * @see org.eclipselabs.mlang.models.soy.impl.SoyPackageImpl#getParam()
		 * @generated
		 */
		EClass PARAM = eINSTANCE.getParam();

		/**
		 * The meta object literal for the '<em><b>Optional</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAM__OPTIONAL = eINSTANCE.getParam_Optional();

		/**
		 * The meta object literal for the '<em><b>Doclet</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARAM__DOCLET = eINSTANCE.getParam_Doclet();

		/**
		 * The meta object literal for the '{@link org.eclipselabs.mlang.models.soy.NamedTag <em>Named Tag</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipselabs.mlang.models.soy.NamedTag
		 * @see org.eclipselabs.mlang.models.soy.impl.SoyPackageImpl#getNamedTag()
		 * @generated
		 */
		EClass NAMED_TAG = eINSTANCE.getNamedTag();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED_TAG__NAME = eINSTANCE.getNamedTag_Name();

	}

} //SoyPackage
