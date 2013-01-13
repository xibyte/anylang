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
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipselabs.mlang.models.common.CommonPackage;
import org.eclipselabs.mlang.models.common.Located;
import org.eclipselabs.mlang.models.common.Location;

import org.eclipselabs.mlang.models.html.HtmlPackage;
import org.eclipselabs.mlang.models.html.Tag;
import org.eclipselabs.mlang.models.html.TagScope;
import org.eclipselabs.mlang.models.html.TagType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tag</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipselabs.mlang.models.html.impl.TagImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipselabs.mlang.models.html.impl.TagImpl#getOffset <em>Offset</em>}</li>
 *   <li>{@link org.eclipselabs.mlang.models.html.impl.TagImpl#getLength <em>Length</em>}</li>
 *   <li>{@link org.eclipselabs.mlang.models.html.impl.TagImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipselabs.mlang.models.html.impl.TagImpl#getOpensScope <em>Opens Scope</em>}</li>
 *   <li>{@link org.eclipselabs.mlang.models.html.impl.TagImpl#getClosesScope <em>Closes Scope</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TagImpl extends EObjectImpl implements Tag {
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
	 * The default value of the '{@link #getOffset() <em>Offset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOffset()
	 * @generated
	 * @ordered
	 */
	protected static final int OFFSET_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getOffset() <em>Offset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOffset()
	 * @generated
	 * @ordered
	 */
	protected int offset = OFFSET_EDEFAULT;

	/**
	 * The default value of the '{@link #getLength() <em>Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLength()
	 * @generated
	 * @ordered
	 */
	protected static final int LENGTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getLength() <em>Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLength()
	 * @generated
	 * @ordered
	 */
	protected int length = LENGTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final TagType TYPE_EDEFAULT = TagType.OPENING;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected TagType type = TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOpensScope() <em>Opens Scope</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOpensScope()
	 * @generated
	 * @ordered
	 */
	protected TagScope opensScope;

	/**
	 * The cached value of the '{@link #getClosesScope() <em>Closes Scope</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClosesScope()
	 * @generated
	 * @ordered
	 */
	protected TagScope closesScope;

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
		return HtmlPackage.Literals.TAG;
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
			eNotify(new ENotificationImpl(this, Notification.SET, HtmlPackage.TAG__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOffset(int newOffset) {
		int oldOffset = offset;
		offset = newOffset;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HtmlPackage.TAG__OFFSET, oldOffset, offset));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getLength() {
		return length;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLength(int newLength) {
		int oldLength = length;
		length = newLength;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HtmlPackage.TAG__LENGTH, oldLength, length));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TagType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(TagType newType) {
		TagType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HtmlPackage.TAG__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TagScope getOpensScope() {
		if (opensScope != null && opensScope.eIsProxy()) {
			InternalEObject oldOpensScope = (InternalEObject)opensScope;
			opensScope = (TagScope)eResolveProxy(oldOpensScope);
			if (opensScope != oldOpensScope) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, HtmlPackage.TAG__OPENS_SCOPE, oldOpensScope, opensScope));
			}
		}
		return opensScope;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TagScope basicGetOpensScope() {
		return opensScope;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOpensScope(TagScope newOpensScope, NotificationChain msgs) {
		TagScope oldOpensScope = opensScope;
		opensScope = newOpensScope;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, HtmlPackage.TAG__OPENS_SCOPE, oldOpensScope, newOpensScope);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOpensScope(TagScope newOpensScope) {
		if (newOpensScope != opensScope) {
			NotificationChain msgs = null;
			if (opensScope != null)
				msgs = ((InternalEObject)opensScope).eInverseRemove(this, HtmlPackage.TAG_SCOPE__OPENING, TagScope.class, msgs);
			if (newOpensScope != null)
				msgs = ((InternalEObject)newOpensScope).eInverseAdd(this, HtmlPackage.TAG_SCOPE__OPENING, TagScope.class, msgs);
			msgs = basicSetOpensScope(newOpensScope, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HtmlPackage.TAG__OPENS_SCOPE, newOpensScope, newOpensScope));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TagScope getClosesScope() {
		if (closesScope != null && closesScope.eIsProxy()) {
			InternalEObject oldClosesScope = (InternalEObject)closesScope;
			closesScope = (TagScope)eResolveProxy(oldClosesScope);
			if (closesScope != oldClosesScope) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, HtmlPackage.TAG__CLOSES_SCOPE, oldClosesScope, closesScope));
			}
		}
		return closesScope;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TagScope basicGetClosesScope() {
		return closesScope;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetClosesScope(TagScope newClosesScope, NotificationChain msgs) {
		TagScope oldClosesScope = closesScope;
		closesScope = newClosesScope;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, HtmlPackage.TAG__CLOSES_SCOPE, oldClosesScope, newClosesScope);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClosesScope(TagScope newClosesScope) {
		if (newClosesScope != closesScope) {
			NotificationChain msgs = null;
			if (closesScope != null)
				msgs = ((InternalEObject)closesScope).eInverseRemove(this, HtmlPackage.TAG_SCOPE__CLOSING, TagScope.class, msgs);
			if (newClosesScope != null)
				msgs = ((InternalEObject)newClosesScope).eInverseAdd(this, HtmlPackage.TAG_SCOPE__CLOSING, TagScope.class, msgs);
			msgs = basicSetClosesScope(newClosesScope, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HtmlPackage.TAG__CLOSES_SCOPE, newClosesScope, newClosesScope));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case HtmlPackage.TAG__OPENS_SCOPE:
				if (opensScope != null)
					msgs = ((InternalEObject)opensScope).eInverseRemove(this, HtmlPackage.TAG_SCOPE__OPENING, TagScope.class, msgs);
				return basicSetOpensScope((TagScope)otherEnd, msgs);
			case HtmlPackage.TAG__CLOSES_SCOPE:
				if (closesScope != null)
					msgs = ((InternalEObject)closesScope).eInverseRemove(this, HtmlPackage.TAG_SCOPE__CLOSING, TagScope.class, msgs);
				return basicSetClosesScope((TagScope)otherEnd, msgs);
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
			case HtmlPackage.TAG__OPENS_SCOPE:
				return basicSetOpensScope(null, msgs);
			case HtmlPackage.TAG__CLOSES_SCOPE:
				return basicSetClosesScope(null, msgs);
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
			case HtmlPackage.TAG__NAME:
				return getName();
			case HtmlPackage.TAG__OFFSET:
				return getOffset();
			case HtmlPackage.TAG__LENGTH:
				return getLength();
			case HtmlPackage.TAG__TYPE:
				return getType();
			case HtmlPackage.TAG__OPENS_SCOPE:
				if (resolve) return getOpensScope();
				return basicGetOpensScope();
			case HtmlPackage.TAG__CLOSES_SCOPE:
				if (resolve) return getClosesScope();
				return basicGetClosesScope();
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
			case HtmlPackage.TAG__NAME:
				setName((String)newValue);
				return;
			case HtmlPackage.TAG__OFFSET:
				setOffset((Integer)newValue);
				return;
			case HtmlPackage.TAG__LENGTH:
				setLength((Integer)newValue);
				return;
			case HtmlPackage.TAG__TYPE:
				setType((TagType)newValue);
				return;
			case HtmlPackage.TAG__OPENS_SCOPE:
				setOpensScope((TagScope)newValue);
				return;
			case HtmlPackage.TAG__CLOSES_SCOPE:
				setClosesScope((TagScope)newValue);
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
			case HtmlPackage.TAG__NAME:
				setName(NAME_EDEFAULT);
				return;
			case HtmlPackage.TAG__OFFSET:
				setOffset(OFFSET_EDEFAULT);
				return;
			case HtmlPackage.TAG__LENGTH:
				setLength(LENGTH_EDEFAULT);
				return;
			case HtmlPackage.TAG__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case HtmlPackage.TAG__OPENS_SCOPE:
				setOpensScope((TagScope)null);
				return;
			case HtmlPackage.TAG__CLOSES_SCOPE:
				setClosesScope((TagScope)null);
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
			case HtmlPackage.TAG__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case HtmlPackage.TAG__OFFSET:
				return offset != OFFSET_EDEFAULT;
			case HtmlPackage.TAG__LENGTH:
				return length != LENGTH_EDEFAULT;
			case HtmlPackage.TAG__TYPE:
				return type != TYPE_EDEFAULT;
			case HtmlPackage.TAG__OPENS_SCOPE:
				return opensScope != null;
			case HtmlPackage.TAG__CLOSES_SCOPE:
				return closesScope != null;
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
		if (baseClass == Location.class) {
			switch (derivedFeatureID) {
				case HtmlPackage.TAG__OFFSET: return CommonPackage.LOCATION__OFFSET;
				case HtmlPackage.TAG__LENGTH: return CommonPackage.LOCATION__LENGTH;
				default: return -1;
			}
		}
		if (baseClass == Located.class) {
			switch (derivedFeatureID) {
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
		if (baseClass == Location.class) {
			switch (baseFeatureID) {
				case CommonPackage.LOCATION__OFFSET: return HtmlPackage.TAG__OFFSET;
				case CommonPackage.LOCATION__LENGTH: return HtmlPackage.TAG__LENGTH;
				default: return -1;
			}
		}
		if (baseClass == Located.class) {
			switch (baseFeatureID) {
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
		result.append(", offset: ");
		result.append(offset);
		result.append(", length: ");
		result.append(length);
		result.append(", type: ");
		result.append(type);
		result.append(')');
		return result.toString();
	}

} //TagImpl
