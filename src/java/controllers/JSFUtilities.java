/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javax.faces.context.FacesContext;

/**
 *
 * @author I320736
 */
public class JSFUtilities {
    
    static String refreshWithParameters() {
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return redirectWithParameters(viewId);
    }
    
    static String redirectWithParameters(String view) {
        return view + "?faces-redirect=true&includeViewParams=true";
    }
}
