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
        // use import main.strings; (use Attack not ATTACK)
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
                 ArrayList<Client> targets = engine.nearbyClients(client, weapon.getRange());
                 /*
                  * Return check number two..
                  */
                 if (
                        // if there is no targets
                         targets.isEmpty()
                        // if weapon can't be used, like, there is cooldown or it's broken
                         || !weapon.use()
                        // something more?
                         )
                         return;
                 // We can attack, let's start..
                 for (Client c : targets)
                         {
                                // client ain't me or 
                                if( c != client && c.isAttackAble(client)){
                                         /*
                                          * int damage = (weapon.getStrength*gc.getDexterity*gc.getStrength
                                          *                             - gc.getDefense);
                                          * if (damage < 0 ) c.changeHealth(damage);
                                          * defense_skill (%)
                                          * strength_skill
                                          * 
                                          * strength
                                          * stamina (h�lsa)
                                          * agility (smidighet
                                          * dexterity ( skill p� vapnet ) 
                                          * intelligence (styrka p� spells / m�ngd mana )
                                          */
                                         c.changeHealth(weapon.getStrength());
                                         // 100% hit each time, we implement stuff like that later
                                         c.addStun(weapon.getStunType(),weapon.getStunTime());
                                 }
                         }
         }
}