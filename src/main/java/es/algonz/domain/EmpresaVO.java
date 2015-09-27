package es.algonz.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;

@Entity
@Table(name = "empresa")
public class EmpresaVO extends AuditableBaseEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8701321094704797219L;
	
	public static final int SOME_NUMBER_PRIME= 31;
	
	private Integer cnEmpresa;
	private TipoEmpresaVO tipoEmpresa;
	private String teNombre;
	private String caCif;
	private String teDireccion;
	private String teTlfFijo;
	private String teTlfMovil1;
	private String teTlfMovil2;
	private String teEmail;
	private String teObservaciones;
	private Set<EmpresaComunidadVO> empresasComunidad = new HashSet<EmpresaComunidadVO>(
			0);
	private Set<DocumentoVO> documentos = new HashSet<DocumentoVO>(0);

	public EmpresaVO() {
	}

	public EmpresaVO(TipoEmpresaVO tipoEmpresa) {
		this.tipoEmpresa = tipoEmpresa;
	}

	public EmpresaVO(TipoEmpresaVO tipoEmpresa, String teNombre, String caCif,
			String teDireccion, String teTlfFijo, String teTlfMovil1,
			String teTlfMovil2, String teEmail, String teObservaciones,
			Set<EmpresaComunidadVO> empresasComunidad, Set<DocumentoVO> documentos) {
		this.tipoEmpresa = tipoEmpresa;
		this.teNombre = teNombre;
		this.caCif = caCif;
		this.teDireccion = teDireccion;
		this.teTlfFijo = teTlfFijo;
		this.teTlfMovil1 = teTlfMovil1;
		this.teTlfMovil2 = teTlfMovil2;
		this.teEmail = teEmail;
		this.teObservaciones = teObservaciones;
		this.empresasComunidad = empresasComunidad;
		this.documentos = documentos;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CN_EMPRESA", unique = true, nullable = false)
	public Integer getCnEmpresa() {
		return this.cnEmpresa;
	}

	public void setCnEmpresa(Integer cnEmpresa) {
		this.cnEmpresa = cnEmpresa;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CN_TIPO_EMPRESA", nullable = false)
	public TipoEmpresaVO getTipoEmpresa() {
		return this.tipoEmpresa;
	}

	public void setTipoEmpresa(TipoEmpresaVO tipoEmpresa) {
		this.tipoEmpresa = tipoEmpresa;
	}

	@Column(name = "TE_NOMBRE", length = 100)
	public String getTeNombre() {
		return this.teNombre;
	}

	public void setTeNombre(String teNombre) {
		this.teNombre = teNombre;
	}

	@Column(name = "CA_CIF", length = 10)
	public String getCaCif() {
		return this.caCif;
	}

	public void setCaCif(String caCif) {
		this.caCif = caCif;
	}

	@Column(name = "TE_DIRECCION", length = 65535)
	public String getTeDireccion() {
		return this.teDireccion;
	}

	public void setTeDireccion(String teDireccion) {
		this.teDireccion = teDireccion;
	}

	@Column(name = "TE_TLF_FIJO", length = 50)
	public String getTeTlfFijo() {
		return this.teTlfFijo;
	}

	public void setTeTlfFijo(String teTlfFijo) {
		this.teTlfFijo = teTlfFijo;
	}

	@Column(name = "TE_TLF_MOVIL1", length = 50)
	public String getTeTlfMovil1() {
		return this.teTlfMovil1;
	}

	public void setTeTlfMovil1(String teTlfMovil1) {
		this.teTlfMovil1 = teTlfMovil1;
	}

	@Column(name = "TE_TLF_MOVIL2", length = 50)
	public String getTeTlfMovil2() {
		return this.teTlfMovil2;
	}

	public void setTeTlfMovil2(String teTlfMovil2) {
		this.teTlfMovil2 = teTlfMovil2;
	}
	
	@Transient
	public String getTelefonos(){
		String telefonos = "";
		
		telefonos = this.teTlfFijo;
		
		if (!StringUtils.isEmpty(this.teTlfMovil1)){

			if (!StringUtils.isEmpty(telefonos)){
				telefonos += " / ";
			}

			telefonos += this.teTlfMovil1;
				
		}
		

		if (!StringUtils.isEmpty(this.teTlfMovil2)){

			if (!StringUtils.isEmpty(telefonos)){
				telefonos += " / ";
			}

			telefonos += this.teTlfMovil2;
				
		}
		
		return telefonos;
	}


	@Column(name = "TE_EMAIL", length = 50)
	public String getTeEmail() {
		return this.teEmail;
	}

	public void setTeEmail(String teEmail) {
		this.teEmail = teEmail;
	}

	@Column(name = "TE_OBSERVACIONES", length = 65535)
	public String getTeObservaciones() {
		return this.teObservaciones;
	}

	public void setTeObservaciones(String teObservaciones) {
		this.teObservaciones = teObservaciones;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
	public Set<EmpresaComunidadVO> getEmpresasComunidad() {
		return this.empresasComunidad;
	}

	public void setEmpresasComunidad(Set<EmpresaComunidadVO> empresasComunidad) {
		this.empresasComunidad = empresasComunidad;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
	@OrderBy ("teNombre ASC")
	public Set<DocumentoVO> getDocumentos() {
		return this.documentos;
	}

	public void setDocumentos(Set<DocumentoVO> documentos) {
		this.documentos = documentos;
	}

	@Override
	public int hashCode() {
		int result = 1;
		result = SOME_NUMBER_PRIME * result
				+ ((cnEmpresa == null) ? 0 : cnEmpresa.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof EmpresaVO) {
			EmpresaVO other = (EmpresaVO) obj;
			if ((this.cnEmpresa == null && other.cnEmpresa != null) || (this.cnEmpresa != null && !this.cnEmpresa.equals(other.cnEmpresa))) {
				return false;
			}
			return true;
		}
		return false;
	}
	
	

}
