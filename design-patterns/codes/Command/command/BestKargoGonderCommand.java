

public class BestKargoGonderCommand implements Command {
	BestKargo bestKargo;
 
	public BestKargoGonderCommand(BestKargo bestKargo) {
		this.bestKargo = bestKargo;
	}
 
	public void execute() {
		bestKargo.gonder();
	}


}
