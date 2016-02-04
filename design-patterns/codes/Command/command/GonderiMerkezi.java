

//
// This is the invoker
//
public class GonderiMerkezi {
	Command command;
 
	public GonderiMerkezi() {}
 
	public void setCommand(Command command) {
        this.command = command;
	}
 
	public void calis() {
        command.execute();
	}
}
