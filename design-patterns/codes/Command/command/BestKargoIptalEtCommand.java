

public class BestKargoIptalEtCommand implements Command {
	BestKargo bestKargo;
  
	public BestKargoIptalEtCommand(BestKargo bestKargo) {
		this.bestKargo = bestKargo;
	}
 
	public void execute() {
		bestKargo.iptalEt();
	}
}
