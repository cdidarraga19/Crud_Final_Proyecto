package com.sena.disocc.app.controlador;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.sena.disocc.app.facadeImp.RolImp;
import com.sena.disocc.app.facadeImp.UsuarioImp;
import com.sena.disocc.app.modelo.Usuario;
import com.sena.disocc.app.utilities.SessionUtils;

@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean {
	
	UsuarioImp usuarioImp = new UsuarioImp();
	RolImp rolImp = new RolImp();
	
	Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String iniciarSesion() {
		String path="";
		String usuarioRol ="";
		usuarioRol = this.usuarioImp.validarUsuario(usuario);
		System.out.print("Rol usuario EN EL BEAN "+ usuarioRol);
		HttpSession session = SessionUtils.getSession();
		
		switch (usuarioRol) {
		case "ADMIN":
			session.setAttribute("username", usuario.getPrimerNombre());
			path ="/faces/Menu/menuAdmin.xhtml?faces-redirect=true";			
			break;
			case "NO ADMIN":
				session.setAttribute("username", usuario.getPrimerNombre());
				path ="/faces/Menu/menuUsuario.xhtml?faces-redirect=true";
				break;
		default:
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"SOMOS MONOS",
							"escriba bien mono"));
			path ="/faces/html/login.xhtml?faces-redirect=true";
			break;	
		}
		return path;
	}
	

}
