package es.algonz.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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

import org.apache.commons.validator.GenericValidator;

@Entity
@Table(name = "predio")
public class PredioVO extends AuditableBaseEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7420664783385864009L;
	private Integer cnPredio;
	private TipoRepresentanteVO tipoRepresentante;
	private TipoPredioVO tipoPredio;
	private PortalVO portal;
	private PlantaVO planta;
	private TerceroVO terceroByCnInquilino;
	private TerceroVO terceroByCnConyuge;
	private TerceroVO terceroByCnPropietario;
	private String tePredio;
	private String tePiso;
	private String teTrastero;
	private String tePlaza;
	private String teObservaciones;
	private Set<DocumentoVO> documentos = new HashSet<DocumentoVO>(0);

	public PredioVO() {
	}

	public PredioVO(Integer cnPredio, TipoPredioVO tipoPredio, PortalVO portal,
			PlantaVO planta, TerceroVO terceroByCnPropietario) {
		this.cnPredio = cnPredio;
		this.tipoPredio = tipoPredio;
		this.portal = portal;
		this.planta = planta;
		this.terceroByCnPropietario = terceroByCnPropietario;
	}

	public PredioVO(Integer cnPredio, TipoRepresentanteVO tipoRepresentante,
			TipoPredioVO tipoPredio, PortalVO portal, PlantaVO planta,
			TerceroVO terceroByCnInquilino, TerceroVO terceroByCnConyuge,
			TerceroVO terceroByCnPropietario, String tePredio, String tePiso,
			String teTrastero, String tePlaza, String teObservaciones,
			Set<DocumentoVO> documentos) {
		this.cnPredio = cnPredio;
		this.tipoRepresentante = tipoRepresentante;
		this.tipoPredio = tipoPredio;
		this.portal = portal;
		this.planta = planta;
		this.terceroByCnInquilino = terceroByCnInquilino;
		this.terceroByCnConyuge = terceroByCnConyuge;
		this.terceroByCnPropietario = terceroByCnPropietario;
		this.tePredio = tePredio;
		this.tePiso = tePiso;
		this.teTrastero = teTrastero;
		this.tePlaza = tePlaza;
		this.teObservaciones = teObservaciones;
		this.documentos = documentos;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CN_PREDIO", unique = true, nullable = false)
	public Integer getCnPredio() {
		return this.cnPredio;
	}

	public void setCnPredio(Integer cnPredio) {
		this.cnPredio = cnPredio;
	}

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CN_TIPO_REPRESENTANTE")
	public TipoRepresentanteVO getTipoRepresentante() {
		return this.tipoRepresentante;
	}

	public void setTipoRepresentante(TipoRepresentanteVO tipoRepresentante) {
		this.tipoRepresentante = tipoRepresentante;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CN_TIPO_PREDIO", nullable = false)
	public TipoPredioVO getTipoPredio() {
		return this.tipoPredio;
	}

	public void setTipoPredio(TipoPredioVO tipoPredio) {
		this.tipoPredio = tipoPredio;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CN_PORTAL", nullable = false)
	public PortalVO getPortal() {
		return this.portal;
	}

	public void setPortal(PortalVO portal) {
		this.portal = portal;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CN_PLANTA", nullable = false)
	public PlantaVO getPlanta() {
		return this.planta;
	}

	public void setPlanta(PlantaVO planta) {
		this.planta = planta;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name = "CN_INQUILINO")
	public TerceroVO getTerceroByCnInquilino() {
		return this.terceroByCnInquilino;
	}

	public void setTerceroByCnInquilino(TerceroVO terceroByCnInquilino) {
		this.terceroByCnInquilino = terceroByCnInquilino;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name = "CN_CONYUGE")
	public TerceroVO getTerceroByCnConyuge() {
		return this.terceroByCnConyuge;
	}

	public void setTerceroByCnConyuge(TerceroVO terceroByCnConyuge) {
		this.terceroByCnConyuge = terceroByCnConyuge;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name = "CN_PROPIETARIO", nullable = false)
	public TerceroVO getTerceroByCnPropietario() {
		return this.terceroByCnPropietario;
	}

	public void setTerceroByCnPropietario(TerceroVO terceroByCnPropietario) {
		this.terceroByCnPropietario = terceroByCnPropietario;
	}

	@Column(name = "TE_PREDIO", length = 50)
	public String getTePredio() {
		return this.tePredio;
	}

	public void setTePredio(String tePredio) {
			this.tePredio = tePredio;
	}

	@Column(name = "TE_PISO", length = 50)
	public String getTePiso() {
		return this.tePiso;
	}

	public void setTePiso(String tePiso) {
		this.tePiso = tePiso;
	}

	@Column(name = "TE_TRASTERO", length = 50)
	public String getTeTrastero() {
		return this.teTrastero;
	}

	public void setTeTrastero(String teTrastero) {
		this.teTrastero = teTrastero;
	}

	@Column(name = "TE_PLAZA", length = 50)
	public String getTePlaza() {
		return this.tePlaza;
	}

	public void setTePlaza(String tePlaza) {
		this.tePlaza = tePlaza;
	}

	@Column(name = "TE_OBSERVACIONES", length = 65535)
	public String getTeObservaciones() {
		return this.teObservaciones;
	}

	public void setTeObservaciones(String teObservaciones) {
		this.teObservaciones = teObservaciones;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "predio")
	@OrderBy ("teNombre ASC")
	public Set<DocumentoVO> getDocumentos() {
		return this.documentos;
	}

	public void setDocumentos(Set<DocumentoVO> documentos) {
		this.documentos = documentos;
	}


	@Transient
	public String getDireccionSobreInforme(){

		if (GenericValidator.isBlankOrNull(this.getTerceroByCnPropietario().getTeDireccionSecundaria())){
			// Imprimimos la dirección primaria
			
			return  this.getPlanta().getTePlanta() + " - " + this.getTePredio() ;
				
//				return  this.getPortal().getTeCalle() + " " 
//						 + this.getPortal().getTeNombre() + "     " + this.getPlanta().getTePlanta() + " - " + this.getTePredio() + " " 
//						 + this.getPortal().getComunidad().getTeCp() + " GIJÓN" + " " 
//								 + "ASTURIAS - ESPAÑA";

				  
			}else{
			// Imprimimos la dirección secundaria
			    return this.getTerceroByCnPropietario().getTeDireccionSecundaria();
			  
			}
            
	}
	
}
