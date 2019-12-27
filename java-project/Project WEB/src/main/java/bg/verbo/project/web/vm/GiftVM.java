package bg.verbo.project.web.vm;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import bg.verbo.project.db.entity.Christmas;

@VariableResolver(DelegatingVariableResolver.class)
public class GiftVM {
	private Christmas gift;
	
	@Init
	public void init() {}
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view, @ExecutionArgParam("gift") Christmas gift) {
		Selectors.wireComponents(view, this, false);
		this.gift = gift;
	}

	public Christmas getGift() {
		return gift;
	}
}
