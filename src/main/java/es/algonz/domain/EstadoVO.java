package es.algonz.domain;

// Generate
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "estado")
public class EstadoVO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5920495086640742498L;
	private Integer cnEstado;
	private String teEstado;
	private Set<ActuacionVO> actuaciones = new HashSet<ActuacionVO>(0);
	private Set<AvisoEmpresaVO> avisosEmpresa = new HashSet<AvisoEmpresaVO>(0);

	public EstadoVO() {
	}

	public EstadoVO(Integer cnEstado, String teEstado) {
		this.cnEstado = cnEstado;
		this.teEstado = teEstado;
	}

	public EstadoVO(Integer cnEstado, String teEstado, Set<ActuacionVO> actuaciones,
			Set<AvisoEmpresaVO> avisosEmpresa) {
		this.cnEstado = cnEstado;
		this.teEstado = teEstado;
		this.actuaciones = actuaciones;
		this.avisosEmpresa = avisosEmpresa;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CN_ESTADO", unique = true, nullable = false)
	public Integer getCnEstado() {
		return this.cnEstado;
	}

	public void setCnEstado(Integer cnEstado) {
		this.cnEstado = cnEstado;
	}

	@Column(name = "TE_ESTADO", nullable = false, length = 50)
	public String getTeEstado() {
		return this.teEstado;
	}

	public void setTeEstado(String teEstado) {
		this.teEstado = teEstado;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "estado")
	public Set<ActuacionVO> getActuaciones() {
		return this.actuaciones;
	}

	public void setActuaciones(Set<ActuacionVO> actuaciones) {
		this.actuaciones = actuaciones;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "estado")
	public Set<AvisoEmpresaVO> getAvisosEmpresa() {
		return this.avisosEmpresa;
	}

	public void setAvisosEmpresa(Set<AvisoEmpresaVO> avisosEmpresa) {
		this.avisosEmpresa = avisosEmpresa;
	}

}
