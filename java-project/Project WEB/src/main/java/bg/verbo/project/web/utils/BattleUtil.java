package bg.verbo.project.web.utils;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.zkoss.bind.BindUtils;

import bg.verbo.project.db.entity.Arenas;
import bg.verbo.project.db.entity.Player;
import bg.verbo.project.db.entity.Team;
import bg.verbo.project.web._aux.Config;

public class BattleUtil {
	private static Random r = new Random();

	public static void startBattle(Team attacker, Team defender, List<String> battleLog, Arenas arena, Object ctx) throws InterruptedException {
		printTeams(attacker, defender, battleLog, arena, ctx);
		battle(attacker, defender, battleLog, ctx);
	}

	private static void battle(Team attacker, Team defender, List<String> battleLog, Object ctx) throws InterruptedException {
		int roundCount = 1;
		while(true) {
			//TimeUnit.SECONDS.sleep(1);
			round(roundCount, battleLog, attacker, defender);
			if (attacker.isBeaten() || defender.isBeaten()) {
				printWinner(attacker, defender, battleLog);
				break;
			}
			roundCount++;
			BindUtils.postNotifyChange(null, null, ctx, "battleLog");
		}
	}

	private static void printWinner(Team attacker, Team defender, List<String> battleLog) {
		if (attacker.isBeaten()) {
			defender.setPoints((short)(defender.getPoints() + (attacker.getPoints() / 10)));
			defender.setFunds(defender.getFunds() + (attacker.getPoints() / 10));
			
			attacker.setPoints((short)(attacker.getPoints() - (defender.getPoints() / 10)));
			
			battleLog.add(defender.getName() + " won the battle!");
		} else {
			attacker.setPoints((short)(attacker.getPoints() + (defender.getPoints() / 10)));
			attacker.setFunds(attacker.getFunds() + (defender.getPoints() / 10));
			
			defender.setPoints((short)(defender.getPoints() - (attacker.getPoints() / 10)));
			
			battleLog.add(attacker.getName() + " won the battle!");
		}
	}

	private static void round(int roundCount, List<String> battleLog, Team attacker, Team defender) {
		battleLog.add("Round " + roundCount);
		System.out.println("Round " + roundCount);
		int attackerNum = r.nextInt(attacker.getAlivePlayers().size());
		int defenderNum = r.nextInt(defender.getAlivePlayers().size());
		if (getRandomBoolean()) {
			hit(attacker.getAlivePlayers().get(attackerNum), defender.getAlivePlayers().get(defenderNum), battleLog);
		} else {
			hit(defender.getAlivePlayers().get(defenderNum), attacker.getAlivePlayers().get(attackerNum), battleLog);
		}
	}

	private static void hit(Player playerA, Player playerD, List<String> battleLog) {
		battleLog.add("\t\t\t---> " + playerA.getName() + " attacked " + playerD.getName() + "!");
		System.out.println("\t\t\t---> " + playerA.getName() + " attacked " + playerD.getName() + "!");
		int hitPower = playerA.getPower() / 2;
		if (r.nextInt(6) > 1) {
			r.nextInt(hitPower);
			playerD.setHealth(playerD.getHealth() - hitPower);
			battleLog.add("\t\t\t---> " + playerD.getName() + " lost " + hitPower + " health!");
			battleLog.add("\t\t\t---> " + playerD.getName() + " has " + playerD.getAbsoluteHealth() + " left!");
			System.out.println("\t\t\t---> " + playerD.getName() + " lost " + hitPower + " health!");
			System.out.println("\t\t\t---> " + playerD.getName() + " has " + playerD.getAbsoluteHealth() + " left!");
		} else {
			battleLog.add("\t\t\t---> " + playerD.getName() + " blocked " + playerA.getName() + "!");
			System.out.println("\t\t\t---> " + playerD.getName() + " blocked " + playerA.getName() + "!");
		}
	}

	private static boolean getRandomBoolean() {
		return r.nextBoolean();
	}

	public static void printTeams(Team attacker, Team defender, List<String> battleLog, Arenas arena, Object ctx) {
		battleLog.add("Starting battle between " + attacker.getName() + " and " + defender.getName() + " !");
		battleLog.add("Arena: " + arena.getName() + " , Date: " + Config.DTF.format(new Date()));
		BindUtils.postNotifyChange(null, null, ctx, "battleLog");
	}
}
