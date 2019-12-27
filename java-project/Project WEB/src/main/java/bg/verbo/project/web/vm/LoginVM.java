package bg.verbo.project.web.vm;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Messagebox;

import bg.verbo.project.db.entity.User;
import bg.verbo.project.web._aux.Config;
import bg.verbo.project.web.service.TeamService;
import bg.verbo.project.web.utils.ZulLocator;

@VariableResolver(DelegatingVariableResolver.class)
public class LoginVM {
	@Wire("#layout") private Component layout;
	
	@WireVariable private TeamService teamService;
	
	private String username;
	private String password;
	
	@Init
	public void init() {}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Command
	public void login() {
		User user = teamService.isUserValid(username, password);
		if (user != null) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put(Config.PARAM, user);
			Executions.createComponents(ZulLocator.zulLocationByName("index"), layout, params);
		} else {
			Messagebox.show(Labels.getLabel("validation.invalidUsernameOrPass"), Labels.getLabel("error"), Messagebox.OK, Messagebox.ERROR);
		}
	}
}
