package es.algonz.controller.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.algonz.domain.ComunidadVO;
import es.algonz.domain.EmpresaComunidadVO;
import es.algonz.domain.EmpresaVO;
import es.algonz.domain.EstadoVO;
import es.algonz.domain.PlantaVO;
import es.algonz.domain.PropertyBean;
import es.algonz.domain.TipoEmpresaVO;
import es.algonz.domain.TipoRepresentanteVO;
import es.algonz.domain.UsuarioVO;
import es.algonz.service.EmpresaComunidadManager;
import es.algonz.service.EmpresaManager;
import es.algonz.service.EstadoManager;
import es.algonz.service.PlantaManager;
import es.algonz.service.TipoEmpresaManager;
import es.algonz.service.TipoRepresentanteManager;
import es.algonz.service.UsuarioManager;

@Service("combosUtils")
public class CombosUtils
{
	@Autowired
	private EmpresaManager empresaManager;
	@Autowired
	private EmpresaComunidadManager empresaComunidadManager;
	@Autowired
	private PlantaManager plantaManager;
	@Autowired
	private TipoRepresentanteManager tipoRepresentanteManager;
	@Autowired
	private TipoEmpresaManager tipoEmpresaManager;
	@Autowired
	private EstadoManager estadoManager;
	@Autowired
	private UsuarioManager usuarioManager;
	

	public List<EmpresaVO> loadEmpresas()
	{
		return empresaManager.getEmpresas(new EmpresaVO());
		
	}
	public List<PropertyBean> loadEmpresasByTipo(Integer idTipo)
	{
		EmpresaVO empresa = new EmpresaVO();
		TipoEmpresaVO tipoEmpresa = new TipoEmpresaVO();
		tipoEmpresa.setCnTipoEmpresa(idTipo);
		empresa.setTipoEmpresa(tipoEmpresa);
		
		List<PropertyBean> listaPB = new ArrayList<PropertyBean>();
		
		Iterator<EmpresaVO> itr = empresaManager.getEmpresas(empresa).iterator();
		
		while (itr.hasNext()){
			EmpresaVO e = itr.next();
			PropertyBean pb = new PropertyBean();
			
			pb.setId(e.getCnEmpresa());
			pb.setValue(e.getTeNombre());
			
			listaPB.add(pb);
		}
		
		return listaPB;
		
	}
	

	public List<EmpresaComunidadVO> loadEmpresasComunidad(Integer cnComunidad) {

		EmpresaComunidadVO empresaComunidad = new EmpresaComunidadVO();
		ComunidadVO comunidad = new ComunidadVO();
		comunidad.setCnComunidad(cnComunidad);
		empresaComunidad.setComunidad(comunidad);
		
		return empresaComunidadManager.getEmpresasComunidad(empresaComunidad);
	}
	

	public List<PropertyBean> loadEmpresasComunidadByTipo(Integer cnComunidad, Integer idTipo) {

		EmpresaComunidadVO empresaComunidad = new EmpresaComunidadVO();
		ComunidadVO comunidad = new ComunidadVO();
		comunidad.setCnComunidad(cnComunidad);
		empresaComunidad.setComunidad(comunidad);
		
		
		EmpresaVO empresa = new EmpresaVO();
		TipoEmpresaVO tipoEmpresa = new TipoEmpresaVO();
		tipoEmpresa.setCnTipoEmpresa(idTipo);
		empresa.setTipoEmpresa(tipoEmpresa);	
		empresaComunidad.setEmpresa(empresa);
		
		
		

		List<PropertyBean> listaPB = new ArrayList<PropertyBean>();
		
		Iterator<EmpresaComunidadVO> itr = empresaComunidadManager.getEmpresasComunidad(empresaComunidad).iterator();
		
		while (itr.hasNext()){
			EmpresaComunidadVO ec = itr.next();
			PropertyBean pb = new PropertyBean();
			
			pb.setId(ec.getCnEmpresaComunidad());
			pb.setValue(ec.getEmpresa().getTeNombre());
			
			listaPB.add(pb);
		}
		
		return listaPB;
	}
	

	public List<PlantaVO> loadPlantas()
	{
		return plantaManager.getPlantas(new PlantaVO());
		
	}
	

	public List<TipoRepresentanteVO> loadTiposRrepresentante()
	{
		return tipoRepresentanteManager.getTipoRepresentantes(new TipoRepresentanteVO());
		
	}
	
	public List<EstadoVO> loadEstados()
	{
		return estadoManager.getEstados(new EstadoVO());
		
	}


	public List<TipoEmpresaVO> loadTiposEmpresa()
	{
		return tipoEmpresaManager.getTipoEmpresas(new TipoEmpresaVO());
		
	}
	
	

	public List<UsuarioVO> loadUsuarios()
	{
		return usuarioManager.getUsuarios(new UsuarioVO());
	}
	
}
