/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: XSDEnumerationFacetImpl.java,v 1.10 2006/08/17 19:53:09 emerks Exp $
 */
package org.eclipse.xsd.impl;


import java.util.Collection;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDEnumerationFacet;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.util.XSDConstants;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Enumeration Facet</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xsd.impl.XSDEnumerationFacetImpl#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XSDEnumerationFacetImpl 
  extends XSDRepeatableFacetImpl 
  implements XSDEnumerationFacet
{
  /**
   * The cached value of the '{@link #getValue() <em>Value</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected EList value = null;

  public static XSDEnumerationFacet createEnumerationFacet(Node node)
  {
    if (XSDConstants.nodeType(node) == XSDConstants.ENUMERATION_ELEMENT)
    {
      XSDEnumerationFacet xsdEnumerationFacet = XSDFactory.eINSTANCE.createXSDEnumerationFacet();
      xsdEnumerationFacet.setElement((Element)node);
      return xsdEnumerationFacet;
    }

    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDEnumerationFacetImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EClass eStaticClass()
  {
    return XSDPackage.Literals.XSD_ENUMERATION_FACET;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getValue()
  {
    if (value == null)
    {
      value = new EDataTypeUniqueEList(Object.class, this, XSDPackage.XSD_ENUMERATION_FACET__VALUE);
    }
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case XSDPackage.XSD_ENUMERATION_FACET__VALUE:
        return getValue();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case XSDPackage.XSD_ENUMERATION_FACET__VALUE:
        getValue().clear();
        getValue().addAll((Collection)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case XSDPackage.XSD_ENUMERATION_FACET__VALUE:
        getValue().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case XSDPackage.XSD_ENUMERATION_FACET__VALUE:
        return value != null && !value.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (value: ");
    result.append(value);
    result.append(')');
    return result.toString();
  }

  public Element createElement()
  {
    Element newElement = createElement(XSDConstants.ENUMERATION_ELEMENT);
    setElement(newElement);
    return newElement;
  }

  public void validateValue()
  {
    XSDSimpleTypeDefinition xsdSimpleTypeDefinition = (XSDSimpleTypeDefinition)getContainer();
    XSDSimpleTypeDefinition baseTypeDefinition = xsdSimpleTypeDefinition.getBaseTypeDefinition();
    if (baseTypeDefinition != null)
    {
      XSDSimpleTypeDefinitionImpl.AssessmentImpl assessment = 
        (XSDSimpleTypeDefinitionImpl.AssessmentImpl)baseTypeDefinition.assess(getLexicalValue());
      if (!assessment.getDiagnostics().isEmpty())
      {
        assessment.assignDiagnostics(this, getElement(), XSDConstants.VALUE_ATTRIBUTE);
        getDiagnostics().addAll(assessment.getDiagnostics());
      }
      else if (assessment.getValue() == null)
      {
        createRequiredAttributeDiagnostic(XSDConstants.PART2, "element-enumeration", getElement(), XSDConstants.VALUE_ATTRIBUTE);
      }
    }
  }

  protected boolean analyze()
  {
    super.analyze();
    String theLexicalValue = getLexicalValue();
    Object newValue = null;
    if (theLexicalValue != null)
    {
      XSDSimpleTypeDefinition xsdSimpleTypeDefinition = (XSDSimpleTypeDefinition)getContainer();
      if (xsdSimpleTypeDefinition != null)
      {
        try
        {
          newValue = xsdSimpleTypeDefinition.getValue(theLexicalValue);
        }
        catch (RuntimeException exception)
        {
        }
      }
    }
    if (!getValue().contains(newValue))
    {
      getValue().clear();
      if (newValue != null)
      {
        getValue().add(newValue);
      }
    }

    return true;
  }

  protected void changeAttribute(EAttribute eAttribute)
  {
    super.changeAttribute(eAttribute);
    if (eAttribute == XSDPackage.Literals.XSD_FACET__LEXICAL_VALUE)
    {
      traverseToRootForAnalysis();
    }
  }

  public boolean isConstraintSatisfied(Object value)
  {
    EList values = getValue();
    XSDSimpleTypeDefinition xsdSimpleTypeDefinition = getSimpleTypeDefinition();
    for (int i = 0, size = values.size(); i < size; ++i)
    {
      Object object = values.get(i);
      if (xsdSimpleTypeDefinition.equalValues(object, value))
      {
        return true;
      }
    }

    return false;
  }

  public Object getEffectiveValue()
  {
    return getValue();
  }

  public XSDConcreteComponent cloneConcreteComponent(boolean deep, boolean shareDOM)
  {
    XSDEnumerationFacetImpl clonedEnumerationFacet =
      (XSDEnumerationFacetImpl)getXSDFactory().createXSDEnumerationFacet();
    clonedEnumerationFacet.isReconciling = true;

    if (getLexicalValue() != null)
    {
      clonedEnumerationFacet.setLexicalValue(getLexicalValue());
    }

    if (deep)
    {
      if (getAnnotation() != null)
      {
        clonedEnumerationFacet.setAnnotation((XSDAnnotation)getAnnotation().cloneConcreteComponent(deep, shareDOM));
      }
    }

    if (shareDOM && getElement() != null)
    {
      clonedEnumerationFacet.setElement(getElement());
    }

    clonedEnumerationFacet.isReconciling = shareDOM;
    return clonedEnumerationFacet;
  }
} 
