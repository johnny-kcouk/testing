package es.algonz.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "documento")
public class DocumentoVO extends AuditableBaseEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6268061948302642933L;
	private Integer cnDocumento;
	private SiniestroVO siniestro;
	private AvisoEmpresaVO avisoEmpresa;
	private ActuacionVO actuacion;
	private EmpresaComunidadVO empresaComunidad;
	private PredioVO predio;
	private ComunidadVO comunidad;
	private EmpresaVO empresa;
	private String teNombre;
	private String tePath;
	private Date feGuardado;
	private String fileType;
	private Integer fileSize;

	public DocumentoVO() {
	}

	public DocumentoVO(String teNombre, String tePath, Date feGuardado) {
		this.teNombre = teNombre;
		this.tePath = tePath;
		this.feGuardado = feGuardado;
	}

	public DocumentoVO(SiniestroVO siniestro, AvisoEmpresaVO avisoEmpresa,
			ActuacionVO actuacion, EmpresaComunidadVO empresaComunidad,
			PredioVO predio, ComunidadVO comunidad, EmpresaVO empresa,
			String teNombre, String tePath, Date feGuardado) {
		this.siniestro = siniestro;
		this.avisoEmpresa = avisoEmpresa;
		this.actuacion = actuacion;
		this.empresaComunidad = empresaComunidad;
		this.predio = predio;
		this.comunidad = comunidad;
		this.empresa = empresa;
		this.teNombre = teNombre;
		this.tePath = tePath;
		this.feGuardado = feGuardado;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CN_DOCUMENTO", unique = true, nullable = false)
	public Integer getCnDocumento() {
		return this.cnDocumento;
	}

	public void setCnDocumento(Integer cnDocumento) {
		this.cnDocumento = cnDocumento;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CN_SINIESTRO")
	public SiniestroVO getSiniestro() {
		return this.siniestro;
	}

	public void setSiniestro(SiniestroVO siniestro) {
		this.siniestro = siniestro;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CN_AVISO_EMPRESA")
	public AvisoEmpresaVO getAvisoEmpresa() {
		return this.avisoEmpresa;
	}

	public void setAvisoEmpresa(AvisoEmpresaVO avisoEmpresa) {
		this.avisoEmpresa = avisoEmpresa;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CN_ACTUACION")
	public ActuacionVO getActuacion() {
		return this.actuacion;
	}

	public void setActuacion(ActuacionVO actuacion) {
		this.actuacion = actuacion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CN_EMPRESA_COMUNIDAD")
	public EmpresaComunidadVO getEmpresaComunidad() {
		return this.empresaComunidad;
	}

	public void setEmpresaComunidad(EmpresaComunidadVO empresaComunidad) {
		this.empresaComunidad = empresaComunidad;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CN_PREDIO")
	public PredioVO getPredio() {
		return this.predio;
	}

	public void setPredio(PredioVO predio) {
		this.predio = predio;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CN_COMUNIDAD")
	public ComunidadVO getComunidad() {
		return this.comunidad;
	}

	public void setComunidad(ComunidadVO comunidad) {
		this.comunidad = comunidad;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CN_EMPRESA")
	public EmpresaVO getEmpresa() {
		return this.empresa;
	}

	public void setEmpresa(EmpresaVO empresa) {
		this.empresa = empresa;
	}

	@Column(name = "TE_NOMBRE", nullable = false, length = 100)
	public String getTeNombre() {
		return this.teNombre;
	}

	public void setTeNombre(String teNombre) {
		this.teNombre = teNombre;
	}

	@Column(name = "TE_PATH", nullable = false, length = 500)
	public String getTePath() {
		return this.tePath;
	}

	public void setTePath(String tePath) {
		this.tePath = tePath;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FE_GUARDADO", nullable = false, length = 19)
	public Date getFeGuardado() {
		return this.feGuardado;
	}

	public void setFeGuardado(Date feGuardado) {
		this.feGuardado = feGuardado;
	}

	@Column(name = "TE_FILE_TYPE", length = 50)
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	

	@Column(name = "NU_FILE_SIZE")
	public Integer getFileSize() {
		return fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}


	

}
