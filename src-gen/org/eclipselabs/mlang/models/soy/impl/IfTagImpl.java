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

import org.eclipselabs.mlang.models.common.Location;

import org.eclipselabs.mlang.models.soy.IfTag;
import org.eclipselabs.mlang.models.soy.SoyPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>If Tag</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipselabs.mlang.models.soy.impl.IfTagImpl#getElseLocation <em>Else Location</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IfTagImpl extends TagImpl implements IfTag {
	/**
	 * The cached value of the '{@link #getElseLocation() <em>Else Location</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElseLocation()
	 * @generated
	 * @ordered
	 */
	protected Location elseLocation;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IfTagImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SoyPackage.Literals.IF_TAG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Location getElseLocation() {
		return elseLocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetElseLocation(Location newElseLocation, NotificationChain msgs) {
		Location oldElseLocation = elseLocation;
		elseLocation = newElseLocation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SoyPackage.IF_TAG__ELSE_LOCATION, oldElseLocation, newElseLocation);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElseLocation(Location newElseLocation) {
		if (newElseLocation != elseLocation) {
			NotificationChain msgs = null;
			if (elseLocation != null)
				msgs = ((InternalEObject)elseLocation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SoyPackage.IF_TAG__ELSE_LOCATION, null, msgs);
			if (newElseLocation != null)
				msgs = ((InternalEObject)newElseLocation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SoyPackage.IF_TAG__ELSE_LOCATION, null, msgs);
			msgs = basicSetElseLocation(newElseLocation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoyPackage.IF_TAG__ELSE_LOCATION, newElseLocation, newElseLocation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SoyPackage.IF_TAG__ELSE_LOCATION:
				return basicSetElseLocation(null, msgs);
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
			case SoyPackage.IF_TAG__ELSE_LOCATION:
				return getElseLocation();
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
			case SoyPackage.IF_TAG__ELSE_LOCATION:
				setElseLocation((Location)newValue);
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
			case SoyPackage.IF_TAG__ELSE_LOCATION:
				setElseLocation((Location)null);
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
			case SoyPackage.IF_TAG__ELSE_LOCATION:
				return elseLocation != null;
		}
		return super.eIsSet(featureID);
	}

} //IfTagImpl
