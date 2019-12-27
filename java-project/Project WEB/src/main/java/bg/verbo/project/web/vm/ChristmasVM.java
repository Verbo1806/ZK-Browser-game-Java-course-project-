package bg.verbo.project.web.vm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

import bg.verbo.project.db.entity.Christmas;
import bg.verbo.project.web.service.ChristmasService;

@VariableResolver(DelegatingVariableResolver.class)
public class ChristmasVM {
	@WireVariable private ChristmasService christmasService;
	
	private List<Christmas> list = new ArrayList<>();
	@Init
	public void init() {
		list = christmasService.findAllPresents();
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}
	
	@Command
	@NotifyChange("list")
	public void openGift() {
		Random rand = new Random();
	    Christmas element = list.get(rand.nextInt(list.size()));
	    element.setIsValid(false);
	    christmasService.save(element);
	    list.remove(element);
	    
	    Map<String, Christmas> arguments = new HashMap<String, Christmas>();
        arguments.put("gift", element);
	    Window window = (Window)Executions.createComponents("/UI/zuls/gift.zul", null, arguments);
        window.doModal();
	}
	
	@Command
	public void openGiftBig() {
		
	}

	public List<Christmas> getList() {
		return list;
	}
}
