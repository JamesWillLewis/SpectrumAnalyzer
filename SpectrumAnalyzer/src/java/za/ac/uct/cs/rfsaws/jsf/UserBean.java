/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.uct.cs.rfsaws.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

/**
 *
 * @author James
 */
@ManagedBean
@SessionScoped
public class UserBean {

    private String username;

    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        username = request.getRemoteUser();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
