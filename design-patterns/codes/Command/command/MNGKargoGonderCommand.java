

public class MNGKargoGonderCommand implements Command {
	MNGKargo mngKargo;

	public MNGKargoGonderCommand(MNGKargo garajKapisi) {
		this.mngKargo = garajKapisi;
	}

	public void execute() {
        mngKargo.ilet();
	}
}
