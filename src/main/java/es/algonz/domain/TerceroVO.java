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
import javax.persistence.Transient;

@Entity
@Table(name = "tercero")
public class TerceroVO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5775657510173650040L;
	private Integer cnTercero;
	private String teNombre;
	private String teApellido1;
	private String teApellido2;
	private String caNif;
	private String teTlfFijo;
	private String teTlfMovil1;
	private String teTlfMovil2;
	private Long cnBanco;
	private String nuCuentaCorriente;
	private String teDireccion;
	private String teDireccionSecundaria;
	private String teEmail;
	private Set<PredioVO> prediosForCnPropietario = new HashSet<PredioVO>(0);
	private Set<PredioVO> prediosForCnConyuge = new HashSet<PredioVO>(0);
	private Set<PredioVO> prediosForCnInquilino = new HashSet<PredioVO>(0);

	public TerceroVO() {
	}

	public TerceroVO(Integer cnTercero) {
		this.cnTercero = cnTercero;
	}

	public TerceroVO(Integer cnTercero, String teNombre, String teApellido1,
			String teApellido2, String caNif, String teTlfFijo,
			String teTlfMovil1, String teTlfMovil2, Long cnBanco,
			String nuCuentaCorriente, String teDireccion,
			String teDireccionSecundaria, String teEmail,
			Set<PredioVO> prediosForCnPropietario,
			Set<PredioVO> prediosForCnConyuge, Set<PredioVO> prediosForCnInquilino) {
		this.cnTercero = cnTercero;
		this.teNombre = teNombre;
		this.teApellido1 = teApellido1;
		this.teApellido2 = teApellido2;
		this.caNif = caNif;
		this.teTlfFijo = teTlfFijo;
		this.teTlfMovil1 = teTlfMovil1;
		this.teTlfMovil2 = teTlfMovil2;
		this.cnBanco = cnBanco;
		this.nuCuentaCorriente = nuCuentaCorriente;
		this.teDireccion = teDireccion;
		this.teDireccionSecundaria = teDireccionSecundaria;
		this.teEmail = teEmail;
		this.prediosForCnPropietario = prediosForCnPropietario;
		this.prediosForCnConyuge = prediosForCnConyuge;
		this.prediosForCnInquilino = prediosForCnInquilino;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CN_TERCERO", unique = true, nullable = false)
	public Integer getCnTercero() {
		return this.cnTercero;
	}

	public void setCnTercero(Integer cnTercero) {
		this.cnTercero = cnTercero;
	}

	@Column(name = "TE_NOMBRE", length = 100)
	public String getTeNombre() {
		return this.teNombre;
	}

	public void setTeNombre(String teNombre) {
		this.teNombre = teNombre;
	}

	@Column(name = "TE_APELLIDO1", length = 50)
	public String getTeApellido1() {
		return this.teApellido1;
	}

	public void setTeApellido1(String teApellido1) {
		this.teApellido1 = teApellido1;
	}

	@Column(name = "TE_APELLIDO2", length = 50)
	public String getTeApellido2() {
		return this.teApellido2;
	}

	public void setTeApellido2(String teApellido2) {
		this.teApellido2 = teApellido2;
	}

	@Column(name = "CA_NIF", length = 10)
	public String getCaNif() {
		return this.caNif;
	}

	public void setCaNif(String caNif) {
		this.caNif = caNif;
	}

	@Column(name = "TE_TLF_FIJO", length = 50)
	public String getTeTlfFijo() {
		return this.teTlfFijo;
	}

	public void setTeTlfFijo(String teTlfFijo) {
		this.teTlfFijo = teTlfFijo;
	}

	@Column(name = "TE_TLF_MOVIL1", length = 50)
	public String getTeTlfMovil1() {
		return this.teTlfMovil1;
	}

	public void setTeTlfMovil1(String teTlfMovil1) {
		this.teTlfMovil1 = teTlfMovil1;
	}

	@Column(name = "TE_TLF_MOVIL2", length = 50)
	public String getTeTlfMovil2() {
		return this.teTlfMovil2;
	}

	public void setTeTlfMovil2(String teTlfMovil2) {
		this.teTlfMovil2 = teTlfMovil2;
	}

	@Column(name = "CN_BANCO", precision = 10, scale = 0)
	public Long getCnBanco() {
		return this.cnBanco;
	}

	public void setCnBanco(Long cnBanco) {
		this.cnBanco = cnBanco;
	}

	@Column(name = "NU_CUENTA_CORRIENTE", length = 25)
	public String getNuCuentaCorriente() {
		return this.nuCuentaCorriente;
	}

	public void setNuCuentaCorriente(String nuCuentaCorriente) {
		this.nuCuentaCorriente = nuCuentaCorriente;
	}

	@Column(name = "TE_DIRECCION", length = 65535)
	public String getTeDireccion() {
		return this.teDireccion;
	}

	public void setTeDireccion(String teDireccion) {
		this.teDireccion = teDireccion;
	}

	@Column(name = "TE_DIRECCION_SECUNDARIA", length = 65535)
	public String getTeDireccionSecundaria() {
		return this.teDireccionSecundaria;
	}

	public void setTeDireccionSecundaria(String teDireccionSecundaria) {
		this.teDireccionSecundaria = teDireccionSecundaria;
	}

	@Column(name = "TE_EMAIL", length = 50)
	public String getTeEmail() {
		return this.teEmail;
	}

	public void setTeEmail(String teEmail) {
		this.teEmail = teEmail;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "terceroByCnPropietario")
	public Set<PredioVO> getPrediosForCnPropietario() {
		return this.prediosForCnPropietario;
	}

	public void setPrediosForCnPropietario(Set<PredioVO> prediosForCnPropietario) {
		this.prediosForCnPropietario = prediosForCnPropietario;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "terceroByCnConyuge")
	public Set<PredioVO> getPrediosForCnConyuge() {
		return this.prediosForCnConyuge;
	}

	public void setPrediosForCnConyuge(Set<PredioVO> prediosForCnConyuge) {
		this.prediosForCnConyuge = prediosForCnConyuge;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "terceroByCnInquilino")
	public Set<PredioVO> getPrediosForCnInquilino() {
		return this.prediosForCnInquilino;
	}

	public void setPrediosForCnInquilino(Set<PredioVO> prediosForCnInquilino) {
		this.prediosForCnInquilino = prediosForCnInquilino;
	}
	
	@Transient
	public String getNombreCompleto(){
		return this.teNombre + " " + this.teApellido1 + " " + this.teApellido2;
	}
	

}
