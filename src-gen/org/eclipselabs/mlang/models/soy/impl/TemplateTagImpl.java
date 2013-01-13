/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipselabs.mlang.models.soy.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipselabs.mlang.models.soy.Doclet;
import org.eclipselabs.mlang.models.soy.NamedTag;
import org.eclipselabs.mlang.models.soy.SoyPackage;
import org.eclipselabs.mlang.models.soy.TemplateTag;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Template Tag</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipselabs.mlang.models.soy.impl.TemplateTagImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipselabs.mlang.models.soy.impl.TemplateTagImpl#getDoclet <em>Doclet</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TemplateTagImpl extends TagImpl implements TemplateTag {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDoclet() <em>Doclet</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDoclet()
	 * @generated
	 * @ordered
	 */
	protected Doclet doclet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TemplateTagImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SoyPackage.Literals.TEMPLATE_TAG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoyPackage.TEMPLATE_TAG__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Doclet getDoclet() {
		if (doclet != null && doclet.eIsProxy()) {
			InternalEObject oldDoclet = (InternalEObject)doclet;
			doclet = (Doclet)eResolveProxy(oldDoclet);
			if (doclet != oldDoclet) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SoyPackage.TEMPLATE_TAG__DOCLET, oldDoclet, doclet));
			}
		}
		return doclet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Doclet basicGetDoclet() {
		return doclet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDoclet(Doclet newDoclet, NotificationChain msgs) {
		Doclet oldDoclet = doclet;
		doclet = newDoclet;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SoyPackage.TEMPLATE_TAG__DOCLET, oldDoclet, newDoclet);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDoclet(Doclet newDoclet) {
		if (newDoclet != doclet) {
			NotificationChain msgs = null;
			if (doclet != null)
				msgs = ((InternalEObject)doclet).eInverseRemove(this, SoyPackage.DOCLET__TEMPLATE, Doclet.class, msgs);
			if (newDoclet != null)
				msgs = ((InternalEObject)newDoclet).eInverseAdd(this, SoyPackage.DOCLET__TEMPLATE, Doclet.class, msgs);
			msgs = basicSetDoclet(newDoclet, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoyPackage.TEMPLATE_TAG__DOCLET, newDoclet, newDoclet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SoyPackage.TEMPLATE_TAG__DOCLET:
				if (doclet != null)
					msgs = ((InternalEObject)doclet).eInverseRemove(this, SoyPackage.DOCLET__TEMPLATE, Doclet.class, msgs);
				return basicSetDoclet((Doclet)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SoyPackage.TEMPLATE_TAG__DOCLET:
				return basicSetDoclet(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SoyPackage.TEMPLATE_TAG__NAME:
				return getName();
			case SoyPackage.TEMPLATE_TAG__DOCLET:
				if (resolve) return getDoclet();
				return basicGetDoclet();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SoyPackage.TEMPLATE_TAG__NAME:
				setName((String)newValue);
				return;
			case SoyPackage.TEMPLATE_TAG__DOCLET:
				setDoclet((Doclet)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case SoyPackage.TEMPLATE_TAG__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SoyPackage.TEMPLATE_TAG__DOCLET:
				setDoclet((Doclet)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case SoyPackage.TEMPLATE_TAG__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SoyPackage.TEMPLATE_TAG__DOCLET:
				return doclet != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == NamedTag.class) {
			switch (derivedFeatureID) {
				case SoyPackage.TEMPLATE_TAG__NAME: return SoyPackage.NAMED_TAG__NAME;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == NamedTag.class) {
			switch (baseFeatureID) {
				case SoyPackage.NAMED_TAG__NAME: return SoyPackage.TEMPLATE_TAG__NAME;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //TemplateTagImpl
