package bg.verbo.project.web.vm.arenas;

import java.util.List;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import bg.verbo.project.db.entity.Arenas;
import bg.verbo.project.web.service.NomenclatureService;

@VariableResolver(DelegatingVariableResolver.class)
public class ArenasVM {
	@WireVariable private NomenclatureService nomenclatureService;
	
	private List<Arenas> arenas;
	
	@Init
	public void init() {
		arenas = nomenclatureService.findAllArenas();
	}

	public List<Arenas> getArenas() {
		return arenas;
	}
}
