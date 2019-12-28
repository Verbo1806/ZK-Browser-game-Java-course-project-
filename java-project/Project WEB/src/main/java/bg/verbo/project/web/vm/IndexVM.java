package bg.verbo.project.web.vm;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import bg.verbo.project.db.entity.User;
import bg.verbo.project.web._aux.Config;
import bg.verbo.project.web.utils.ZulLocator;

@VariableResolver(DelegatingVariableResolver.class)
public class IndexVM {
	@Wire("#centerContent") private Component centerContent;
	
	private User user;
	
	@Init
	public void init() {
		user = (User) Sessions.getCurrent().getAttribute(Config.USER);
		if (user == null) {
			Executions.sendRedirect("/login.zul");
		}
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}
	
	@GlobalCommand
	public void changeContent(@BindingParam("zulName") String zulName, @BindingParam("param") Object param) {
		detachCenterContent();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(Config.PARAM, param);
		
		Executions.createComponents(ZulLocator.zulLocationByName(zulName), centerContent, params);
	}
	
	@Command
	public void logout() {
		
	}

	public User getUser() {
		return user;
	}

	private void detachCenterContent() {
		if (centerContent != null) {
			centerContent.getChildren().clear();
		}
	}
}
