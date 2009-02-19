/**
 * 
 */
package items;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
/**
 * @author bache
 *
 */
public abstract class item {

	private boolean possible;
	private int cooldown;
	private Timer cdTimer;
	
	public item(int cd)
	{
		cooldown=cd;
		cdTimer=new Timer(cooldown,startCooldown);
		
	}
	public boolean isCooldown()
	{
		if (cdTimer.isRunning())
			return true;
		return false;
		
	}
	
	public boolean use()
	{
		if (!isCooldown())
		{
			cdTimer.start();
			possible=false;
			return true;
		}
		return false;
	}
	
			
	ActionListener startCooldown=new ActionListener	(){
		 public void actionPerformed(ActionEvent evt) {
			 possible=true;
			 cdTimer.stop();			
		 }
	};
	
}
