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
import javax.persistence.Transient;

import org.apache.commons.lang.time.DateUtils;

@Entity
@Table(name = "aviso_empresa")
public class AvisoEmpresaVO extends AuditableBaseEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3395653999886270027L;
	private Integer cnAvisoEmpresa;
	private EstadoVO estado;
	private EmpresaComunidadVO empresaComunidad;
	private String teDescripcion;
	private Date feInicio;
	private Date feVencimiento;
	private Date feCierre;
	private String teObservaciones;
	private Integer nuDiasAviso;
	
	private Set<DocumentoVO> documentos = new HashSet<DocumentoVO>(0);

	public AvisoEmpresaVO() {
	}

	public AvisoEmpresaVO(Integer cnAvisoEmpresa, EstadoVO estado,
			EmpresaComunidadVO empresaComunidad) {
		this.cnAvisoEmpresa = cnAvisoEmpresa;
		this.estado = estado;
		this.empresaComunidad = empresaComunidad;
	}

	public AvisoEmpresaVO(Integer cnAvisoEmpresa, EstadoVO estado,
			EmpresaComunidadVO empresaComunidad, String teDescripcion,
			Date feInicio, Date feVencimiento, Date feCierre,
			String teObservaciones, Set<DocumentoVO> documentos, Integer nuDiasAviso) {
		this.cnAvisoEmpresa = cnAvisoEmpresa;
		this.estado = estado;
		this.empresaComunidad = empresaComunidad;
		this.teDescripcion = teDescripcion;
		this.feInicio = feInicio;
		this.feVencimiento = feVencimiento;
		this.feCierre = feCierre;
		this.teObservaciones = teObservaciones;
		this.documentos = documentos;
		this.nuDiasAviso = nuDiasAviso;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CN_AVISO_EMPRESA", unique = true, nullable = false)
	public Integer getCnAvisoEmpresa() {
		return this.cnAvisoEmpresa;
	}

	public void setCnAvisoEmpresa(Integer cnAvisoEmpresa) {
		this.cnAvisoEmpresa = cnAvisoEmpresa;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CN_ESTADO", nullable = false)
	public EstadoVO getEstado() {
		return this.estado;
	}

	public void setEstado(EstadoVO estado) {
		this.estado = estado;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CN_EMPRESA_COMUNIDAD", nullable = false)
	public EmpresaComunidadVO getEmpresaComunidad() {
		return this.empresaComunidad;
	}

	public void setEmpresaComunidad(EmpresaComunidadVO empresaComunidad) {
		this.empresaComunidad = empresaComunidad;
	}

	@Column(name = "TE_DESCRIPCION", length = 100)
	public String getTeDescripcion() {
		return this.teDescripcion;
	}

	public void setTeDescripcion(String teDescripcion) {
		this.teDescripcion = teDescripcion;
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
	@Column(name = "FE_VENCIMIENTO", length = 10)
	public Date getFeVencimiento() {
		return this.feVencimiento;
	}

	public void setFeVencimiento(Date feVencimiento) {
		this.feVencimiento = feVencimiento;
	}
	
	
	@Transient
	public Date getFeLimite() {
		// A la fecha de vencimiento le quito 
		return DateUtils.addDays(this.feVencimiento, -getNuDiasAviso());
	}


	@Temporal(TemporalType.DATE)
	@Column(name = "FE_CIERRE", length = 10)
	public Date getFeCierre() {
		return this.feCierre;
	}

	public void setFeCierre(Date feCierre) {
		this.feCierre = feCierre;
	}

	@Column(name = "TE_OBSERVACIONES", length = 65535)
	public String getTeObservaciones() {
		return this.teObservaciones;
	}

	public void setTeObservaciones(String teObservaciones) {
		this.teObservaciones = teObservaciones;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "avisoEmpresa")
	@OrderBy ("teNombre ASC")
	public Set<DocumentoVO> getDocumentos() {
		return this.documentos;
	}

	public void setDocumentos(Set<DocumentoVO> documentos) {
		this.documentos = documentos;
	}


	@Column(name = "NU_DIAS_AVISO", length = 3)
	public Integer getNuDiasAviso() {
		if (nuDiasAviso==null)
			return 0;
		
		return nuDiasAviso;
	}

	public void setNuDiasAviso(Integer nuDiasAviso) {
		this.nuDiasAviso = nuDiasAviso;
	}

}
