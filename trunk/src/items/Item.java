package items;

import java.util.TimerTask;
import java.util.Timer;
/**
 * 
 * @author bache
 *
 */
public abstract class Item extends TimerTask {

	private long cooldown;
	private Timer cdTimer;
	private boolean possible;
	public Item(int cd)
	{
		cooldown=cd;
		cdTimer=new Timer();
		cdTimer.schedule(this,cooldown);
		possible = true;
		
	}
	public boolean isCooldown()
	{
		if (!possible)
			return true;
		return false;
		
	}
	
	public boolean use()
	{
		if (!isCooldown())
		{
			possible=false;
			return true;
		}
		return false;
	}
	
	public void run()
	{
		possible=true;
	}
	
}
