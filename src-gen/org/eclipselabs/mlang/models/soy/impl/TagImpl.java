/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipselabs.mlang.models.soy.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipselabs.mlang.models.common.Location;

import org.eclipselabs.mlang.models.soy.SoyPackage;
import org.eclipselabs.mlang.models.soy.Tag;
import org.eclipselabs.mlang.models.soy.Variable;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tag</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipselabs.mlang.models.soy.impl.TagImpl#getVariables <em>Variables</em>}</li>
 *   <li>{@link org.eclipselabs.mlang.models.soy.impl.TagImpl#getTagName <em>Tag Name</em>}</li>
 *   <li>{@link org.eclipselabs.mlang.models.soy.impl.TagImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link org.eclipselabs.mlang.models.soy.impl.TagImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link org.eclipselabs.mlang.models.soy.impl.TagImpl#getOpenLocation <em>Open Location</em>}</li>
 *   <li>{@link org.eclipselabs.mlang.models.soy.impl.TagImpl#getCloseLocation <em>Close Location</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TagImpl extends EObjectImpl implements Tag {
	/**
	 * The cached value of the '{@link #getVariables() <em>Variables</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariables()
	 * @generated
	 * @ordered
	 */
	protected EList<Variable> variables;

	/**
	 * The default value of the '{@link #getTagName() <em>Tag Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTagName()
	 * @generated
	 * @ordered
	 */
	protected static final String TAG_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTagName() <em>Tag Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTagName()
	 * @generated
	 * @ordered
	 */
	protected String tagName = TAG_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getParent() <em>Parent</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParent()
	 * @generated
	 * @ordered
	 */
	protected Tag parent;

	/**
	 * The cached value of the '{@link #getChildren() <em>Children</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChildren()
	 * @generated
	 * @ordered
	 */
	protected EList<Tag> children;

	/**
	 * The cached value of the '{@link #getOpenLocation() <em>Open Location</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOpenLocation()
	 * @generated
	 * @ordered
	 */
	protected Location openLocation;

	/**
	 * The cached value of the '{@link #getCloseLocation() <em>Close Location</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCloseLocation()
	 * @generated
	 * @ordered
	 */
	protected Location closeLocation;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TagImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SoyPackage.Literals.TAG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Variable> getVariables() {
		if (variables == null) {
			variables = new EObjectResolvingEList<Variable>(Variable.class, this, SoyPackage.TAG__VARIABLES);
		}
		return variables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTagName() {
		return tagName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTagName(String newTagName) {
		String oldTagName = tagName;
		tagName = newTagName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoyPackage.TAG__TAG_NAME, oldTagName, tagName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Tag getParent() {
		if (parent != null && parent.eIsProxy()) {
			InternalEObject oldParent = (InternalEObject)parent;
			parent = (Tag)eResolveProxy(oldParent);
			if (parent != oldParent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SoyPackage.TAG__PARENT, oldParent, parent));
			}
		}
		return parent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Tag basicGetParent() {
		return parent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParent(Tag newParent, NotificationChain msgs) {
		Tag oldParent = parent;
		parent = newParent;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SoyPackage.TAG__PARENT, oldParent, newParent);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParent(Tag newParent) {
		if (newParent != parent) {
			NotificationChain msgs = null;
			if (parent != null)
				msgs = ((InternalEObject)parent).eInverseRemove(this, SoyPackage.TAG__CHILDREN, Tag.class, msgs);
			if (newParent != null)
				msgs = ((InternalEObject)newParent).eInverseAdd(this, SoyPackage.TAG__CHILDREN, Tag.class, msgs);
			msgs = basicSetParent(newParent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoyPackage.TAG__PARENT, newParent, newParent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Tag> getChildren() {
		if (children == null) {
			children = new EObjectWithInverseResolvingEList<Tag>(Tag.class, this, SoyPackage.TAG__CHILDREN, SoyPackage.TAG__PARENT);
		}
		return children;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Location getOpenLocation() {
		return openLocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOpenLocation(Location newOpenLocation, NotificationChain msgs) {
		Location oldOpenLocation = openLocation;
		openLocation = newOpenLocation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SoyPackage.TAG__OPEN_LOCATION, oldOpenLocation, newOpenLocation);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOpenLocation(Location newOpenLocation) {
		if (newOpenLocation != openLocation) {
			NotificationChain msgs = null;
			if (openLocation != null)
				msgs = ((InternalEObject)openLocation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SoyPackage.TAG__OPEN_LOCATION, null, msgs);
			if (newOpenLocation != null)
				msgs = ((InternalEObject)newOpenLocation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SoyPackage.TAG__OPEN_LOCATION, null, msgs);
			msgs = basicSetOpenLocation(newOpenLocation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoyPackage.TAG__OPEN_LOCATION, newOpenLocation, newOpenLocation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Location getCloseLocation() {
		return closeLocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCloseLocation(Location newCloseLocation, NotificationChain msgs) {
		Location oldCloseLocation = closeLocation;
		closeLocation = newCloseLocation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SoyPackage.TAG__CLOSE_LOCATION, oldCloseLocation, newCloseLocation);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCloseLocation(Location newCloseLocation) {
		if (newCloseLocation != closeLocation) {
			NotificationChain msgs = null;
			if (closeLocation != null)
				msgs = ((InternalEObject)closeLocation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SoyPackage.TAG__CLOSE_LOCATION, null, msgs);
			if (newCloseLocation != null)
				msgs = ((InternalEObject)newCloseLocation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SoyPackage.TAG__CLOSE_LOCATION, null, msgs);
			msgs = basicSetCloseLocation(newCloseLocation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoyPackage.TAG__CLOSE_LOCATION, newCloseLocation, newCloseLocation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SoyPackage.TAG__PARENT:
				if (parent != null)
					msgs = ((InternalEObject)parent).eInverseRemove(this, SoyPackage.TAG__CHILDREN, Tag.class, msgs);
				return basicSetParent((Tag)otherEnd, msgs);
			case SoyPackage.TAG__CHILDREN:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getChildren()).basicAdd(otherEnd, msgs);
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
			case SoyPackage.TAG__PARENT:
				return basicSetParent(null, msgs);
			case SoyPackage.TAG__CHILDREN:
				return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
			case SoyPackage.TAG__OPEN_LOCATION:
				return basicSetOpenLocation(null, msgs);
			case SoyPackage.TAG__CLOSE_LOCATION:
				return basicSetCloseLocation(null, msgs);
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
			case SoyPackage.TAG__VARIABLES:
				return getVariables();
			case SoyPackage.TAG__TAG_NAME:
				return getTagName();
			case SoyPackage.TAG__PARENT:
				if (resolve) return getParent();
				return basicGetParent();
			case SoyPackage.TAG__CHILDREN:
				return getChildren();
			case SoyPackage.TAG__OPEN_LOCATION:
				return getOpenLocation();
			case SoyPackage.TAG__CLOSE_LOCATION:
				return getCloseLocation();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SoyPackage.TAG__VARIABLES:
				getVariables().clear();
				getVariables().addAll((Collection<? extends Variable>)newValue);
				return;
			case SoyPackage.TAG__TAG_NAME:
				setTagName((String)newValue);
				return;
			case SoyPackage.TAG__PARENT:
				setParent((Tag)newValue);
				return;
			case SoyPackage.TAG__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection<? extends Tag>)newValue);
				return;
			case SoyPackage.TAG__OPEN_LOCATION:
				setOpenLocation((Location)newValue);
				return;
			case SoyPackage.TAG__CLOSE_LOCATION:
				setCloseLocation((Location)newValue);
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
			case SoyPackage.TAG__VARIABLES:
				getVariables().clear();
				return;
			case SoyPackage.TAG__TAG_NAME:
				setTagName(TAG_NAME_EDEFAULT);
				return;
			case SoyPackage.TAG__PARENT:
				setParent((Tag)null);
				return;
			case SoyPackage.TAG__CHILDREN:
				getChildren().clear();
				return;
			case SoyPackage.TAG__OPEN_LOCATION:
				setOpenLocation((Location)null);
				return;
			case SoyPackage.TAG__CLOSE_LOCATION:
				setCloseLocation((Location)null);
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
			case SoyPackage.TAG__VARIABLES:
				return variables != null && !variables.isEmpty();
			case SoyPackage.TAG__TAG_NAME:
				return TAG_NAME_EDEFAULT == null ? tagName != null : !TAG_NAME_EDEFAULT.equals(tagName);
			case SoyPackage.TAG__PARENT:
				return parent != null;
			case SoyPackage.TAG__CHILDREN:
				return children != null && !children.isEmpty();
			case SoyPackage.TAG__OPEN_LOCATION:
				return openLocation != null;
			case SoyPackage.TAG__CLOSE_LOCATION:
				return closeLocation != null;
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
		result.append(" (tagName: ");
		result.append(tagName);
		result.append(')');
		return result.toString();
	}

} //TagImpl
