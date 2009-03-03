package command;

import game.Client;
import game.Engine;
import gamecharacter.GameCharacter;
import items.Weapon;

import java.util.ArrayList;
/**
 * In this class we check if we can attack nearby clients and if it's possible with the current weapon
 * there is comments inside to use more functions like dexterity and intelligence.. now it's just affected by weapon.strength
 * we check for stun and we add stun depending on weapon type
 * @file CommandAttack.java
 * @version 0.3
 * @author Josef Johansson
 */
public class CommandAttack extends Command 
{
        // client use this to send commands... good way?
        // use import main.Strings; (use Attack not ATTACK)
        // public static final String ATTACK = "attack";
        
        public CommandAttack(Engine engine)
        {
                 super(engine);
        }

        /**
         * If any clients are within attack range while attacking they will decrease in health points
         * @param client the client that this is executed on
         */
         public void execute(Client client)
         {
                 GameCharacter gc = client.getGameCharacter();
                 Weapon weapon = gc.getPrimary();
                 /*
                  * return check number one..
                  * Stupid checks, these are checked in Client.. just for failsafe
                  */
                 if( 
                  //A client can't attack while stunned
                  gc.isStunned()
                  // something more ?
                  )
                         return;
                 //
                 ArrayList<GameCharacter> targets = engine.nearbyClients(client, weapon.getRange());
                 /*
                  * Return check number two..
                  */
                 if (
                        // if there is no targets
                         targets.isEmpty()
                        // if weapon can't be used, like, there is cooldown or it's broken
                         || gc.hasCoolDown( gc.getPrimary() )
                        // something more?
                        ){
                	 return;
                 }
                 
                 System.out.println("******************************************* nu attackerar " +gc.getName());    
                 // We can attack, let's start..
                 for (GameCharacter gamechar : targets)
                         {
                                // client ain't me or 
                	 			// Attack the player
                                if( gamechar != gc &&  gamechar.isPlayer() && gamechar.isAlive() ) {
                                         /*
                                          * 
                                          * int damage = (weapon.getStrength*gc.getDexterity*gc.getStrength
                                          *                             - gc.getDefense);
                                          * if (damage < 0 ) c.changeHealth(damage);
                                          * defense_skill (%)
                                          * strength_skill
                                          * 
                                          * strength
                                          * stamina (hälsa)
                                          * agility (smidighet
                                          * dexterity ( skill på vapnet ) 
                                          * intelligence (styrka på spells / mängd mana )
                                          */
                                         gamechar.changeHealth( -weapon.getStrength() );
                                         gc.addCoolDown(gc.getPrimary(),gc.getPrimary().getCoolDown()) ;
                                         // 100% hit each time, we implement stuff like that later
                                         gamechar.addStun(weapon.getStunType(),weapon.getStunTime());
                                         System.out.println("\nPlayer blir attackerad");
                                 }
                                // om den som attackerar är player och gamecharacter är levande och gamechar inte är player
                                else if( gc.isPlayer() && gamechar.isAlive() && gc != gamechar )
                                {
                                	/*
                                     * 
                                     * int damage = (weapon.getStrength*gc.getDexterity*gc.getStrength
                                     *                             - gc.getDefense);
                                     * if (damage < 0 ) c.changeHealth(damage);
                                     * defense_skill (%)
                                     * strength_skill
                                     * 
                                     * strength
                                     * stamina (hälsa)
                                     * agility (smidighet
                                     * dexterity ( skill på vapnet ) 
                                     * intelligence (styrka på spells / mängd mana )
                                     */
                                    gamechar.changeHealth( -weapon.getStrength() );
                                    gc.addCoolDown(gc.getPrimary(),gc.getPrimary().getCoolDown()) ;
                                    // 100% hit each time, we implement stuff like that later
                                    gamechar.addStun(weapon.getStunType(),weapon.getStunTime());
                                    System.out.println("\nMonster blir attackerad");
                                }
                                
                             // client ain't me or 
                                
                         }
         }
}