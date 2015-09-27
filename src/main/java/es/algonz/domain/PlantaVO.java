package es.algonz.domain;


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
@Table(name = "planta")
public class PlantaVO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8994257646229544139L;
	private Integer cnPlanta;
	private String tePlanta;
	private Set<PredioVO> predios = new HashSet<PredioVO>(0);

	public PlantaVO() {
	}

	public PlantaVO(Integer cnPlanta, String tePlanta) {
		this.cnPlanta = cnPlanta;
		this.tePlanta = tePlanta;
	}

	public PlantaVO(Integer cnPlanta, String tePlanta, Set<PredioVO> predios) {
		this.cnPlanta = cnPlanta;
		this.tePlanta = tePlanta;
		this.predios = predios;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CN_PLANTA", unique = true, nullable = false)
	public Integer getCnPlanta() {
		return this.cnPlanta;
	}

	public void setCnPlanta(Integer cnPlanta) {
		this.cnPlanta = cnPlanta;
	}

	@Column(name = "TE_PLANTA", nullable = false, length = 50)
	public String getTePlanta() {
		return this.tePlanta;
	}

	public void setTePlanta(String tePlanta) {
		this.tePlanta = tePlanta;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "planta")
	public Set<PredioVO> getPredios() {
		return this.predios;
	}

	public void setPredios(Set<PredioVO> predios) {
		this.predios = predios;
	}

}
