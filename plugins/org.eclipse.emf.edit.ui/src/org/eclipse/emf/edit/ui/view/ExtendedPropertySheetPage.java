/**
 * <copyright> 
 *
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: ExtendedPropertySheetPage.java,v 1.1 2005/08/12 22:34:04 davidms Exp $
 */
package org.eclipse.emf.edit.ui.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.views.properties.PropertySheetEntry;
import org.eclipse.ui.views.properties.PropertySheetPage;

/**
 * This property sheet page has an additional button in its local toolbar that locates the value of the
 * selected property in the editor.
 */
public class ExtendedPropertySheetPage extends PropertySheetPage
{
  protected List objectsToSelect = new ArrayList();
  protected AdapterFactoryEditingDomain editingDomain;
  
  protected IAction locateValueAction = new LocateValueAction();

  protected class LocateValueAction extends Action
  {
    public LocateValueAction()
    {
      setText(EMFEditUIPlugin.INSTANCE.getString("_UI_LocateValue_action"));
      setToolTipText(EMFEditUIPlugin.INSTANCE.getString("_UI_LocateValue_action_tool_tip"));
      setImageDescriptor(ExtendedImageRegistry.INSTANCE.getImageDescriptor(EMFEditUIPlugin.INSTANCE.getImage("full/elcl16/LocateValue")));
      setDisabledImageDescriptor(ExtendedImageRegistry.INSTANCE.getImageDescriptor(EMFEditUIPlugin.INSTANCE.getImage("full/dlcl16/LocateValue")));
    }

    public void run()
    {
      List selection = new ArrayList();
      for (Iterator i = objectsToSelect.iterator(); i.hasNext(); )
      {
        selection.add(editingDomain.getWrapper(i.next()));
      }
      setSelectionToViewer(selection);
    }
  }

  public ExtendedPropertySheetPage(AdapterFactoryEditingDomain editingDomain)
  {
    super();
    this.editingDomain = editingDomain;
  }

  /**
   * This method should be overridden to set the selection.
   */
  protected void setSelectionToViewer(List selection)
  {
  }

  public void makeContributions(IMenuManager menuManager, IToolBarManager toolBarManager, IStatusLineManager statusLineManager)
  {
    super.makeContributions(menuManager, toolBarManager, statusLineManager);
    toolBarManager.add(locateValueAction);
  }

  public void handleEntrySelection(ISelection selection)
  {
    super.handleEntrySelection(selection);
    objectsToSelect.clear();
    if (!selection.isEmpty() && selection instanceof IStructuredSelection)
    {
      IStructuredSelection structuredSelection = (IStructuredSelection)selection;
      if (structuredSelection.size() == 1)
      {
        Object object = structuredSelection.getFirstElement();
        if (object instanceof PropertySheetEntry)
        {
          PropertySheetEntry entry = (PropertySheetEntry)object;
          Object [] values = entry.getValues();
          if (values != null)
          {
            for (int i = 0; i < values.length; ++i)
            {
              Object value = values[i];
              if (value instanceof IItemPropertySource)
              {
                Object realValue = ((IItemPropertySource)value).getEditableValue(null);
                if (realValue instanceof Collection)
                {
                  Collection c = (Collection)realValue;
                  for (Iterator iter = ((Collection)realValue).iterator(); iter.hasNext(); )
                  {
                    addObjectToSelect(iter.next());
                  }
                }
                else
                {
                  addObjectToSelect(realValue);
                }
              }
            }
          }
        }
      }
    }
    locateValueAction.setEnabled(!objectsToSelect.isEmpty());
  }

  protected void addObjectToSelect(Object object)
  {
    if (object instanceof EObject)
    {
      objectsToSelect.add(object);
    }
  }
}
