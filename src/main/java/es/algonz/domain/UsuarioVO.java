package es.algonz.domain;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Entity
@Table(name = "USUARIO")
public class UsuarioVO implements Serializable {

	private static final long serialVersionUID = 2412879844301530763L;
	
	public static final int SOME_NUMBER_PRIME= 31;

	private static final Log LOGGER = LogFactory.getLog(UsuarioVO.class);
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdUsuario")
	private String idUsuario;
	@Column(name = "IdSistema")
	private String idSistema;
	@Column(name = "PwdSistema")
	private String pwdSistema;
	@Column(name = "Enabled")
	private boolean enabled;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "USUARIO_ROL", joinColumns = { @JoinColumn(name = "IdUsuario", referencedColumnName = "IdUsuario") }, inverseJoinColumns = { @JoinColumn(name = "IdRol", referencedColumnName = "IdRol") })
	private List<RolVO> roles;

	

	@Column(name = "teNombre")
	private String teNombre;
	@Column(name = "teApellido1")
	private String teApellido1;
	@Column(name = "teApellido2")
	private String teApellido2;
	@Column(name = "teEmail")
	private String teEmail;


	@Transient
	private String pwdSistemaOld;
	@Transient
	private String pwdSistemaNew;
	@Transient
	private String pwdSistemaConfirm;
	
	@Transient
	public String getNombreCompleto(){
		return this.teNombre + " " + this.teApellido1 + " " + this.teApellido2;
	}
	
	
	
	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getIdSistema() {
		return idSistema;
	}

	public void setIdSistema(String idSistema) {
		this.idSistema = idSistema;
	}

	public String getPwdSistema() {
		return pwdSistema;
	}

	public void setPwdSistema(String pwdSistema) {
		this.pwdSistema = pwdSistema;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public List<RolVO> getRoles() {
		return roles;
	}

	public void setRoles(List<RolVO> roles) {
		this.roles = roles;
	}
	
	

	@OneToMany(fetch = FetchType.LAZY, mappedBy="gestor")
	private List<ComunidadVO> comunidades;
	

	public List<ComunidadVO> getComunidades() {
		return comunidades;
	}

	public void setComunidades(List<ComunidadVO> comunidades) {
		this.comunidades = comunidades;
	}
	
	
	
	

	public String getTeNombre() {
		return teNombre;
	}

	public void setTeNombre(String teNombre) {
		this.teNombre = teNombre;
	}

	public String getTeApellido1() {
		return teApellido1;
	}

	public void setTeApellido1(String teApellido1) {
		this.teApellido1 = teApellido1;
	}

	public String getTeApellido2() {
		return teApellido2;
	}

	public void setTeApellido2(String teApellido2) {
		this.teApellido2 = teApellido2;
	}

	public String getTeEmail() {
		return teEmail;
	}

	public void setTeEmail(String teEmail) {
		this.teEmail = teEmail;
	}


	@Transient
	public String getPwdSistemaOld() {
		return getSecurePasswordSHA1(pwdSistemaOld);
	}

	public void setPwdSistemaOld(String pwdSistemaOld) {
		this.pwdSistemaOld = pwdSistemaOld;
	}

	public String getPwdSistemaNew() {
		return getSecurePasswordSHA1(pwdSistemaNew);
	}

	public void setPwdSistemaNew(String pwdSistemaNew) {
		this.pwdSistemaNew = pwdSistemaNew;
	}

	public String getPwdSistemaConfirm() {
		return getSecurePasswordSHA1(pwdSistemaConfirm);
	}

	public void setPwdSistemaConfirm(String pwdSistemaConfirm) {
		this.pwdSistemaConfirm = pwdSistemaConfirm;
	}

	@Override
	public int hashCode() {
		int result = 1;
		result = SOME_NUMBER_PRIME * result
				+ ((idUsuario == null) ? 0 : idUsuario.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (obj instanceof UsuarioVO) {
			UsuarioVO other = (UsuarioVO) obj;
			if ((this.idUsuario == null && other.idUsuario != null)
					|| (this.idUsuario != null && !this.idUsuario
							.equals(other.idUsuario))) {
				return false;
			}
			return true;
		}
		return false;
	}
	

	private static String getSecurePasswordSHA1(String passwordToHash) {
		String generatedPassword = null;
		
		if (passwordToHash == null)
			return null;
		if (StringUtils.isEmpty(passwordToHash))
			return "";
	
		try {
			 
	        MessageDigest md = MessageDigest.getInstance("SHA-1");
	        md.update(passwordToHash.getBytes());
	 
	        byte byteData[] = md.digest();
	 
	        //convert the byte to hex format method 1
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }

			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error(e.getMessage(),e);
		}
		
		return generatedPassword;
	}

}
