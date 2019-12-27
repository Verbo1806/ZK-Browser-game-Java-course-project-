package bg.verbo.project.web.utils;

import org.zkoss.util.resource.Labels;

public final class ZulUtil {
	public static String getPlayerType(String type) {
		switch(type) {
		case "D01":
			return "icon icon-d01";
		
		case "T01":
			return "icon icon-t01";
			
		case "T02":
			return "icon icon-t02";
		
		case "T012":
			return "icon icon-t012";
			
		case "T013":
			return "icon icon-t013";
			
		default:
			return "icon icon-ns";
		
		}
	}
	
	public static String getPlayerTooltiptext(String type) {
		switch(type) {
		case "D01":
			return Labels.getLabel("playerType.d01");
		
		case "T01":
			return Labels.getLabel("playerType.t01");
			
		case "T02":
			return Labels.getLabel("playerType.t02");
		
		case "T012":
			return Labels.getLabel("playerType.t012");
			
		case "T013":
			return Labels.getLabel("playerType.t013");
			
		default:
			return Labels.getLabel("");
		
		}
	}
}
