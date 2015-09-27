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
@Table(name = "actuacion")
public class ActuacionVO extends AuditableBaseEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3759976506223254992L;
	private Integer cnActuacion;
	private SiniestroVO siniestro;
	private EstadoVO estado;
	private Date feInicio;
	private Date feVencimiento;
	private Date feCierre;
	private String teNumeroExp;
	private String teDescripcion;
	private String teObservaciones;
	private Set<DocumentoVO> documentos = new HashSet<DocumentoVO>(0);

	public ActuacionVO() {
	}

	public ActuacionVO(Integer cnActuacion, SiniestroVO siniestro, EstadoVO estado) {
		this.cnActuacion = cnActuacion;
		this.siniestro = siniestro;
		this.estado = estado;
	}

	public ActuacionVO(Integer cnActuacion, SiniestroVO siniestro, EstadoVO estado,
			Date feInicio, Date feVencimiento, Date feCierre, String teDescripcion, String teNumeroExp,
			String teObservaciones, Set<DocumentoVO> documentos) {
		this.cnActuacion = cnActuacion;
		this.siniestro = siniestro;
		this.estado = estado;
		this.feInicio = feInicio;
		this.feVencimiento = feVencimiento;
		this.feCierre = feCierre;
		this.teNumeroExp = teNumeroExp;
		this.teDescripcion = teDescripcion;
		this.teObservaciones = teObservaciones;
		this.documentos = documentos;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CN_ACTUACION", unique = true, nullable = false)
	public Integer getCnActuacion() {
		return this.cnActuacion;
	}

	public void setCnActuacion(Integer cnActuacion) {
		this.cnActuacion = cnActuacion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CN_SINIESTRO", nullable = false)
	public SiniestroVO getSiniestro() {
		return this.siniestro;
	}

	public void setSiniestro(SiniestroVO siniestro) {
		this.siniestro = siniestro;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CN_ESTADO", nullable = false)
	public EstadoVO getEstado() {
		return this.estado;
	}

	public void setEstado(EstadoVO estado) {
		this.estado = estado;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "FE_CIERRE", length = 10)
	public Date getFeCierre() {
		return this.feCierre;
	}

	public void setFeCierre(Date feCierre) {
		this.feCierre = feCierre;
	}

	@Column(name = "TE_NUMERO_EXP", length = 50)
	public String getTeNumeroExp() {
		return teNumeroExp;
	}

	public void setTeNumeroExp(String teNumeroExp) {
		this.teNumeroExp = teNumeroExp;
	}
	
	@Column(name = "TE_DESCRIPCION", length = 50)
	public String getTeDescripcion() {
		return teDescripcion;
	}

	public void setTeDescripcion(String teDescripcion) {
		this.teDescripcion = teDescripcion;
	}

	@Column(name = "TE_OBSERVACIONES", length = 65535)
	public String getTeObservaciones() {
		return this.teObservaciones;
	}

	public void setTeObservaciones(String teObservaciones) {
		this.teObservaciones = teObservaciones;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "actuacion")
	@OrderBy ("teNombre ASC")
	public Set<DocumentoVO> getDocumentos() {
		return this.documentos;
	}

	public void setDocumentos(Set<DocumentoVO> documentos) {
		this.documentos = documentos;
	}

}
