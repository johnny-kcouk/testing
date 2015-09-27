package es.algonz.domain;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "tipo_empresa")
public class TipoEmpresaVO implements java.io.Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 39604383599098111L;
	private Integer cnTipoEmpresa;
	private String teTipoEmpresa;
	private Set<EmpresaVO> empresas = new HashSet<EmpresaVO>(0);

	public TipoEmpresaVO() {
	}

	public TipoEmpresaVO(String teTipoEmpresa) {
		this.teTipoEmpresa = teTipoEmpresa;
	}

	public TipoEmpresaVO(String teTipoEmpresa, Set<EmpresaVO> empresas) {
		this.teTipoEmpresa = teTipoEmpresa;
		this.empresas = empresas;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CN_TIPO_EMPRESA", unique = true, nullable = false)
	public Integer getCnTipoEmpresa() {
		return this.cnTipoEmpresa;
	}

	public void setCnTipoEmpresa(Integer cnTipoEmpresa) {
		this.cnTipoEmpresa = cnTipoEmpresa;
	}

	@Column(name = "TE_TIPO_EMPRESA", nullable = false, length = 50)
	public String getTeTipoEmpresa() {
		return this.teTipoEmpresa;
	}

	public void setTeTipoEmpresa(String teTipoEmpresa) {
		this.teTipoEmpresa = teTipoEmpresa;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoEmpresa")
	public Set<EmpresaVO> getEmpresas() {
		return this.empresas;
	}

	public void setEmpresas(Set<EmpresaVO> empresas) {
		this.empresas = empresas;
	}

}
