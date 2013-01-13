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
import org.eclipselabs.mlang.models.soy.Param;
import org.eclipselabs.mlang.models.soy.SoyPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Param</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipselabs.mlang.models.soy.impl.ParamImpl#isOptional <em>Optional</em>}</li>
 *   <li>{@link org.eclipselabs.mlang.models.soy.impl.ParamImpl#getDoclet <em>Doclet</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ParamImpl extends VariableImpl implements Param {
	/**
	 * The default value of the '{@link #isOptional() <em>Optional</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOptional()
	 * @generated
	 * @ordered
	 */
	protected static final boolean OPTIONAL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isOptional() <em>Optional</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOptional()
	 * @generated
	 * @ordered
	 */
	protected boolean optional = OPTIONAL_EDEFAULT;

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
	protected ParamImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SoyPackage.Literals.PARAM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isOptional() {
		return optional;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOptional(boolean newOptional) {
		boolean oldOptional = optional;
		optional = newOptional;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoyPackage.PARAM__OPTIONAL, oldOptional, optional));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SoyPackage.PARAM__DOCLET, oldDoclet, doclet));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SoyPackage.PARAM__DOCLET, oldDoclet, newDoclet);
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
				msgs = ((InternalEObject)doclet).eInverseRemove(this, SoyPackage.DOCLET__PARAMS, Doclet.class, msgs);
			if (newDoclet != null)
				msgs = ((InternalEObject)newDoclet).eInverseAdd(this, SoyPackage.DOCLET__PARAMS, Doclet.class, msgs);
			msgs = basicSetDoclet(newDoclet, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoyPackage.PARAM__DOCLET, newDoclet, newDoclet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SoyPackage.PARAM__DOCLET:
				if (doclet != null)
					msgs = ((InternalEObject)doclet).eInverseRemove(this, SoyPackage.DOCLET__PARAMS, Doclet.class, msgs);
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
			case SoyPackage.PARAM__DOCLET:
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
			case SoyPackage.PARAM__OPTIONAL:
				return isOptional();
			case SoyPackage.PARAM__DOCLET:
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
			case SoyPackage.PARAM__OPTIONAL:
				setOptional((Boolean)newValue);
				return;
			case SoyPackage.PARAM__DOCLET:
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
			case SoyPackage.PARAM__OPTIONAL:
				setOptional(OPTIONAL_EDEFAULT);
				return;
			case SoyPackage.PARAM__DOCLET:
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
			case SoyPackage.PARAM__OPTIONAL:
				return optional != OPTIONAL_EDEFAULT;
			case SoyPackage.PARAM__DOCLET:
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
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (optional: ");
		result.append(optional);
		result.append(')');
		return result.toString();
	}

} //ParamImpl
