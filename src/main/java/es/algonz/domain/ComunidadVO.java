package es.algonz.domain;

import java.util.HashSet;
import java.util.List;
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
@Table(name = "comunidad")
public class ComunidadVO extends AuditableBaseEntity implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6863367484403948483L;
	private Integer cnComunidad;
	private String caCif;
	private String teNombre;
	private String teCp;
	private String teObservaciones;
	private Set<EmpresaComunidadVO> empresasComunidad = new HashSet<EmpresaComunidadVO>(
			0);
	private Set<PortalVO> portals = new HashSet<PortalVO>(0);
	private Set<DocumentoVO> documentos = new HashSet<DocumentoVO>(0);
	// Transient: Para mostrarlos en el detalle de la comunidad
	private List<PredioVO> representantes;
	
	
	private UsuarioVO gestor;
	

	public ComunidadVO() {
	}

	public ComunidadVO(Integer cnComunidad) {
		this.cnComunidad = cnComunidad;
	}

	public ComunidadVO(Integer cnComunidad, String caCif, String teNombre, String teCp,
			String teObservaciones, Set<EmpresaComunidadVO> empresasComunidad,
			Set<PortalVO> portals, Set<DocumentoVO> documentos) {
		this.cnComunidad = cnComunidad;
		this.caCif = caCif;
		this.teNombre = teNombre;
		this.teCp = teCp;
		this.teObservaciones = teObservaciones;
		this.empresasComunidad = empresasComunidad;
		this.portals = portals;
		this.documentos = documentos;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CN_COMUNIDAD", unique = true, nullable = false)
	public Integer getCnComunidad() {
		return this.cnComunidad;
	}

	public void setCnComunidad(Integer cnComunidad) {
		this.cnComunidad = cnComunidad;
	}

	@Column(name = "CA_CIF", length = 10)
	public String getCaCif() {
		return this.caCif;
	}

	public void setCaCif(String caCif) {
		this.caCif = caCif;
	}
	
	@Column(name = "TE_NOMBRE", length = 100)
	public String getTeNombre() {
		return this.teNombre;
	}

	public void setTeNombre(String teNombre) {
		this.teNombre = teNombre;
	}

	@Column(name = "TE_CP", length = 50)
	public String getTeCp() {
		return this.teCp;
	}

	public void setTeCp(String teCp) {
		this.teCp = teCp;
	}

	@Column(name = "TE_OBSERVACIONES", length = 65535)
	public String getTeObservaciones() {
		return this.teObservaciones;
	}

	public void setTeObservaciones(String teObservaciones) {
		this.teObservaciones = teObservaciones;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "comunidad")
	@OrderBy ("empresa ASC")
	public Set<EmpresaComunidadVO> getEmpresasComunidad() {
		return this.empresasComunidad;
	}

	public void setEmpresasComunidad(Set<EmpresaComunidadVO> empresasComunidad) {
		this.empresasComunidad = empresasComunidad;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "comunidad")
	@OrderBy ("teCalle teNombre ASC")
	public Set<PortalVO> getPortals() {
		return this.portals;
	}

	public void setPortals(Set<PortalVO> portals) {
		this.portals = portals;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "comunidad")
	@OrderBy ("teNombre ASC")
	public Set<DocumentoVO> getDocumentos() {
		return this.documentos;
	}

	public void setDocumentos(Set<DocumentoVO> documentos) {
		this.documentos = documentos;
	}

	@Transient
	public List<PredioVO> getRepresentantes() {
		return representantes;
	}

	public void setRepresentantes(List<PredioVO> representantes) {
		this.representantes = representantes;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IdUsuario", nullable = false)
	public UsuarioVO getGestor() {
		return gestor;
	}

	public void setGestor(UsuarioVO gestor) {
		this.gestor = gestor;
	}

	
	
	
	

}
