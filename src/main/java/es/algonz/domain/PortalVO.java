package es.algonz.domain;


import java.util.HashSet;
import java.util.Iterator;
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
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;

@Entity
@Table(name = "portal")
public class PortalVO extends AuditableBaseEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5581050922016280646L;
	private Integer cnPortal;
	private ComunidadVO comunidad;
	private String teCalle;
	private String teNombre;
	private String teObservaciones;
	private Set<PredioVO> predios = new HashSet<PredioVO>(0);
	private Set<SiniestroVO> siniestros = new HashSet<SiniestroVO>(0);

	public PortalVO() {
	}

	public PortalVO(Integer cnPortal, ComunidadVO comunidad, String teNombre) {
		this.cnPortal = cnPortal;
		this.comunidad = comunidad;
		this.teNombre = teNombre;
	}

	public PortalVO(Integer cnPortal, ComunidadVO comunidad, String teCalle,
			String teNombre, String teObservaciones, Set<PredioVO> predios,
			Set<SiniestroVO> siniestros) {
		this.cnPortal = cnPortal;
		this.comunidad = comunidad;
		this.teCalle = teCalle;
		this.teNombre = teNombre;
		this.teObservaciones = teObservaciones;
		this.predios = predios;
		this.siniestros = siniestros;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CN_PORTAL", unique = true, nullable = false)
	public Integer getCnPortal() {
		return this.cnPortal;
	}

	public void setCnPortal(Integer cnPortal) {
		this.cnPortal = cnPortal;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CN_COMUNIDAD", nullable = false)
	public ComunidadVO getComunidad() {
		return this.comunidad;
	}

	public void setComunidad(ComunidadVO comunidad) {
		this.comunidad = comunidad;
	}

	@Column(name = "TE_CALLE", length = 100)
	public String getTeCalle() {
		return this.teCalle;
	}

	public void setTeCalle(String teCalle) {
		this.teCalle = teCalle;
	}

	@Column(name = "TE_NOMBRE", nullable = false, length = 100)
	public String getTeNombre() {
		return this.teNombre;
	}

	public void setTeNombre(String teNombre) {
		this.teNombre = teNombre;
	}

	@Column(name = "TE_OBSERVACIONES", length = 65535)
	public String getTeObservaciones() {
		return this.teObservaciones;
	}

	public void setTeObservaciones(String teObservaciones) {
		this.teObservaciones = teObservaciones;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "portal")
	@OrderBy ("planta tePredio ASC")
	public Set<PredioVO> getPredios() {
		return this.predios;
	}

	public void setPredios(Set<PredioVO> predios) {
		this.predios = predios;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "portal")
	public Set<SiniestroVO> getSiniestros() {
		return this.siniestros;
	}

	public void setSiniestros(Set<SiniestroVO> siniestros) {
		this.siniestros = siniestros;
	}
	
	@Transient
	public String getEmailsPortal(){
		String listadoEmails = "";
		if (predios != null && predios.size() > 0){
			Iterator<PredioVO> itr = predios.iterator();
			
			while (itr.hasNext()){
				PredioVO predio = itr.next();

				if (predio.getTerceroByCnPropietario() != null && StringUtils.isNotBlank(predio.getTerceroByCnPropietario().getTeEmail())){
					listadoEmails += predio.getTerceroByCnPropietario().getTeEmail() + ", ";
				}
				
				if (predio.getTerceroByCnConyuge() != null && StringUtils.isNotBlank(predio.getTerceroByCnConyuge().getTeEmail())){
					listadoEmails += predio.getTerceroByCnConyuge().getTeEmail() + ", ";
				}
			}
			
		}
		
		return listadoEmails;
	}

}
