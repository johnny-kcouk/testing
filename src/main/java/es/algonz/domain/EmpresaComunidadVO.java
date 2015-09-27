package es.algonz.domain;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "empresa_comunidad")
public class EmpresaComunidadVO extends AuditableBaseEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 559666202781947169L;
	private Integer cnEmpresaComunidad;
	private ComunidadVO comunidad;
	private EmpresaVO empresa;
	private Date feInicio;
	private Date feFin;
	private String teObservaciones;
	private Set<DocumentoVO> documentos = new HashSet<DocumentoVO>(0);
	private Set<AvisoEmpresaVO> avisosEmpresa = new HashSet<AvisoEmpresaVO>(0);
	private Set<SiniestroVO> siniestros = new HashSet<SiniestroVO>(0);

	public EmpresaComunidadVO() {
	}

	public EmpresaComunidadVO(Integer cnEmpresaComunidad, ComunidadVO comunidad,
			EmpresaVO empresa) {
		this.cnEmpresaComunidad = cnEmpresaComunidad;
		this.comunidad = comunidad;
		this.empresa = empresa;
	}

	public EmpresaComunidadVO(Integer cnEmpresaComunidad, ComunidadVO comunidad,
			EmpresaVO empresa, Date feInicio, Date feFin, String teObservaciones,
			Set<DocumentoVO> documentos, Set<AvisoEmpresaVO> avisosEmpresa,
			Set<SiniestroVO> siniestros) {
		this.cnEmpresaComunidad = cnEmpresaComunidad;
		this.comunidad = comunidad;
		this.empresa = empresa;
		this.feInicio = feInicio;
		this.feFin = feFin;
		this.teObservaciones = teObservaciones;
		this.documentos = documentos;
		this.avisosEmpresa = avisosEmpresa;
		this.siniestros = siniestros;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CN_EMPRESA_COMUNIDAD", unique = true, nullable = false)
	public Integer getCnEmpresaComunidad() {
		return this.cnEmpresaComunidad;
	}

	public void setCnEmpresaComunidad(Integer cnEmpresaComunidad) {
		this.cnEmpresaComunidad = cnEmpresaComunidad;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CN_COMUNIDAD", nullable = false)
	public ComunidadVO getComunidad() {
		return this.comunidad;
	}

	public void setComunidad(ComunidadVO comunidad) {
		this.comunidad = comunidad;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CN_EMPRESA", nullable = false)
	public EmpresaVO getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(EmpresaVO empresa) {
		this.empresa = empresa;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FE_INICIO", length = 10)
	public Date getFeInicio() {
		return this.feInicio;
	}

	public void setFeInicio(Date feInicio) {
		this.feInicio = feInicio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FE_FIN", length = 10)
	public Date getFeFin() {
		return this.feFin;
	}

	public void setFeFin(Date feFin) {
		this.feFin = feFin;
	}

	@Column(name = "TE_OBSERVACIONES", length = 65535)
	public String getTeObservaciones() {
		return this.teObservaciones;
	}

	public void setTeObservaciones(String teObservaciones) {
		this.teObservaciones = teObservaciones;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresaComunidad")
	@OrderBy ("teNombre ASC")
	public Set<DocumentoVO> getDocumentos() {
		return this.documentos;
	}

	public void setDocumentos(Set<DocumentoVO> documentos) {
		this.documentos = documentos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresaComunidad")
	@OrderBy ("feVencimiento DESC")
	public Set<AvisoEmpresaVO> getAvisosEmpresa() {
		return this.avisosEmpresa;
	}

	public void setAvisosEmpresa(Set<AvisoEmpresaVO> avisosEmpresa) {
		this.avisosEmpresa = avisosEmpresa;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresaComunidad")
	public Set<SiniestroVO> getSiniestros() {
		return this.siniestros;
	}

	public void setSiniestros(Set<SiniestroVO> siniestros) {
		this.siniestros = siniestros;
	}

}
