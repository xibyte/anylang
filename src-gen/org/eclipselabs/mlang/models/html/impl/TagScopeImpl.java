/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipselabs.mlang.models.html.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipselabs.mlang.models.common.impl.ScopeImpl;

import org.eclipselabs.mlang.models.html.HtmlPackage;
import org.eclipselabs.mlang.models.html.Tag;
import org.eclipselabs.mlang.models.html.TagScope;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tag Scope</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipselabs.mlang.models.html.impl.TagScopeImpl#getOpening <em>Opening</em>}</li>
 *   <li>{@link org.eclipselabs.mlang.models.html.impl.TagScopeImpl#getClosing <em>Closing</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TagScopeImpl extends ScopeImpl implements TagScope {
	/**
	 * The cached value of the '{@link #getOpening() <em>Opening</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOpening()
	 * @generated
	 * @ordered
	 */
	protected Tag opening;

	/**
	 * The cached value of the '{@link #getClosing() <em>Closing</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClosing()
	 * @generated
	 * @ordered
	 */
	protected Tag closing;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TagScopeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HtmlPackage.Literals.TAG_SCOPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Tag getOpening() {
		if (opening != null && opening.eIsProxy()) {
			InternalEObject oldOpening = (InternalEObject)opening;
			opening = (Tag)eResolveProxy(oldOpening);
			if (opening != oldOpening) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, HtmlPackage.TAG_SCOPE__OPENING, oldOpening, opening));
			}
		}
		return opening;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Tag basicGetOpening() {
		return opening;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOpening(Tag newOpening, NotificationChain msgs) {
		Tag oldOpening = opening;
		opening = newOpening;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, HtmlPackage.TAG_SCOPE__OPENING, oldOpening, newOpening);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOpening(Tag newOpening) {
		if (newOpening != opening) {
			NotificationChain msgs = null;
			if (opening != null)
				msgs = ((InternalEObject)opening).eInverseRemove(this, HtmlPackage.TAG__OPENS_SCOPE, Tag.class, msgs);
			if (newOpening != null)
				msgs = ((InternalEObject)newOpening).eInverseAdd(this, HtmlPackage.TAG__OPENS_SCOPE, Tag.class, msgs);
			msgs = basicSetOpening(newOpening, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HtmlPackage.TAG_SCOPE__OPENING, newOpening, newOpening));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Tag getClosing() {
		if (closing != null && closing.eIsProxy()) {
			InternalEObject oldClosing = (InternalEObject)closing;
			closing = (Tag)eResolveProxy(oldClosing);
			if (closing != oldClosing) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, HtmlPackage.TAG_SCOPE__CLOSING, oldClosing, closing));
			}
		}
		return closing;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Tag basicGetClosing() {
		return closing;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetClosing(Tag newClosing, NotificationChain msgs) {
		Tag oldClosing = closing;
		closing = newClosing;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, HtmlPackage.TAG_SCOPE__CLOSING, oldClosing, newClosing);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClosing(Tag newClosing) {
		if (newClosing != closing) {
			NotificationChain msgs = null;
			if (closing != null)
				msgs = ((InternalEObject)closing).eInverseRemove(this, HtmlPackage.TAG__CLOSES_SCOPE, Tag.class, msgs);
			if (newClosing != null)
				msgs = ((InternalEObject)newClosing).eInverseAdd(this, HtmlPackage.TAG__CLOSES_SCOPE, Tag.class, msgs);
			msgs = basicSetClosing(newClosing, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HtmlPackage.TAG_SCOPE__CLOSING, newClosing, newClosing));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case HtmlPackage.TAG_SCOPE__OPENING:
				if (opening != null)
					msgs = ((InternalEObject)opening).eInverseRemove(this, HtmlPackage.TAG__OPENS_SCOPE, Tag.class, msgs);
				return basicSetOpening((Tag)otherEnd, msgs);
			case HtmlPackage.TAG_SCOPE__CLOSING:
				if (closing != null)
					msgs = ((InternalEObject)closing).eInverseRemove(this, HtmlPackage.TAG__CLOSES_SCOPE, Tag.class, msgs);
				return basicSetClosing((Tag)otherEnd, msgs);
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
			case HtmlPackage.TAG_SCOPE__OPENING:
				return basicSetOpening(null, msgs);
			case HtmlPackage.TAG_SCOPE__CLOSING:
				return basicSetClosing(null, msgs);
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
			case HtmlPackage.TAG_SCOPE__OPENING:
				if (resolve) return getOpening();
				return basicGetOpening();
			case HtmlPackage.TAG_SCOPE__CLOSING:
				if (resolve) return getClosing();
				return basicGetClosing();
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
			case HtmlPackage.TAG_SCOPE__OPENING:
				setOpening((Tag)newValue);
				return;
			case HtmlPackage.TAG_SCOPE__CLOSING:
				setClosing((Tag)newValue);
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
			case HtmlPackage.TAG_SCOPE__OPENING:
				setOpening((Tag)null);
				return;
			case HtmlPackage.TAG_SCOPE__CLOSING:
				setClosing((Tag)null);
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
			case HtmlPackage.TAG_SCOPE__OPENING:
				return opening != null;
			case HtmlPackage.TAG_SCOPE__CLOSING:
				return closing != null;
		}
		return super.eIsSet(featureID);
	}

} //TagScopeImpl
