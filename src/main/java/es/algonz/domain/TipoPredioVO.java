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
@Table(name = "tipo_predio")
public class TipoPredioVO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -189279622747431766L;
	private Integer cnTipoPredio;
	private String teTipoPredio;
	private Set<PredioVO> predios = new HashSet<PredioVO>(0);

	public TipoPredioVO() {
	}

	public TipoPredioVO(Integer cnTipoPredio, String teTipoPredio) {
		this.cnTipoPredio = cnTipoPredio;
		this.teTipoPredio = teTipoPredio;
	}

	public TipoPredioVO(Integer cnTipoPredio, String teTipoPredio, Set<PredioVO> predios) {
		this.cnTipoPredio = cnTipoPredio;
		this.teTipoPredio = teTipoPredio;
		this.predios = predios;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CN_TIPO_PREDIO", unique = true, nullable = false)
	public Integer getCnTipoPredio() {
		return this.cnTipoPredio;
	}

	public void setCnTipoPredio(Integer cnTipoPredio) {
		this.cnTipoPredio = cnTipoPredio;
	}

	@Column(name = "TE_TIPO_PREDIO", nullable = false, length = 50)
	public String getTeTipoPredio() {
		return this.teTipoPredio;
	}

	public void setTeTipoPredio(String teTipoPredio) {
		this.teTipoPredio = teTipoPredio;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoPredio")
	public Set<PredioVO> getPredios() {
		return this.predios;
	}

	public void setPredios(Set<PredioVO> predios) {
		this.predios = predios;
	}

}
