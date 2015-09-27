package es.algonz.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "siniestro")
public class SiniestroVO extends AuditableBaseEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4599809304135887221L;
	private Integer cnSiniestro;
	private PortalVO portal;
	private EmpresaComunidadVO empresaComunidad;
	private String teNombre;
	private String teObservaciones;
	private Set<ActuacionVO> actuaciones = new HashSet<ActuacionVO>(0);
	private Set<DocumentoVO> documentos = new HashSet<DocumentoVO>(0);

	private Integer cnTipoSiniestro;
	
	
	public SiniestroVO() {
	}

	public SiniestroVO(Integer cnSiniestro, PortalVO portal,
			EmpresaComunidadVO empresaComunidad) {
		this.cnSiniestro = cnSiniestro;
		this.portal = portal;
		this.empresaComunidad = empresaComunidad;
	}

	public SiniestroVO(Integer cnSiniestro, PortalVO portal,
			EmpresaComunidadVO empresaComunidad, String teNombre,
			String teObservaciones, Set<ActuacionVO> actuaciones,
			Set<DocumentoVO> documentos) {
		this.cnSiniestro = cnSiniestro;
		this.portal = portal;
		this.empresaComunidad = empresaComunidad;
		this.teNombre = teNombre;
		this.teObservaciones = teObservaciones;
		this.actuaciones = actuaciones;
		this.documentos = documentos;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CN_SINIESTRO", unique = true, nullable = false)
	public Integer getCnSiniestro() {
		return this.cnSiniestro;
	}

	public void setCnSiniestro(Integer cnSiniestro) {
		this.cnSiniestro = cnSiniestro;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CN_PORTAL", nullable = false)
	public PortalVO getPortal() {
		return this.portal;
	}

	public void setPortal(PortalVO portal) {
		this.portal = portal;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CN_EMPRESA_COMUNIDAD", nullable = false)
	public EmpresaComunidadVO getEmpresaComunidad() {
		return this.empresaComunidad;
	}

	public void setEmpresaComunidad(EmpresaComunidadVO empresaComunidad) {
		this.empresaComunidad = empresaComunidad;
	}

	@Column(name = "TE_NOMBRE", length = 100)
	public String getTeNombre() {
		return this.teNombre;
	}

	public void setTeNombre(String teNombre) {
		this.teNombre = teNombre;
	}

	@Column(name = "TE_OBSERVACIONES", length = 65535)
	public String getTeObservaciones() {
		return this.teObservaciones;
	}

	public void setTeObservaciones(String teObservaciones) {
		this.teObservaciones = teObservaciones;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "siniestro")
	@OrderBy ("feVencimiento DESC")
	public Set<ActuacionVO> getActuaciones() {
		return this.actuaciones;
	}

	public void setActuaciones(Set<ActuacionVO> actuaciones) {
		this.actuaciones = actuaciones;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "siniestro")
	@OrderBy ("teNombre ASC")
	public Set<DocumentoVO> getDocumentos() {
		return this.documentos;
	}

	public void setDocumentos(Set<DocumentoVO> documentos) {
		this.documentos = documentos;
	}

	
	// Transient
	@Transient
	public Integer getCnTipoSiniestro() {
		return cnTipoSiniestro;
	}

	public void setCnTipoSiniestro(Integer cnTipoSiniestro) {
		this.cnTipoSiniestro = cnTipoSiniestro;
	}
	
	

}
