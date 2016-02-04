import java.util.ArrayList;
import java.util.List;

public class RemoteControlTest {
	public static void main(String[] args) {
		GonderiMerkezi remote = new GonderiMerkezi();

        BestKargo bestKargo = new BestKargo();
		MNGKargo mngKargo = new MNGKargo();

        BestKargoGonderCommand bestKargoGonderCommand = new BestKargoGonderCommand(bestKargo);
		BestKargoIptalEtCommand bestKargoIptalEtCommand = new BestKargoIptalEtCommand(bestKargo);
		MNGKargoGonderCommand mngKargoGonderCommand =  new MNGKargoGonderCommand(mngKargo);

        // sarmalanmis yontem
        remote.setCommand(bestKargoGonderCommand);
        remote.calis();

		remote.setCommand(bestKargoIptalEtCommand);
        remote.calis();

		remote.setCommand(mngKargoGonderCommand);
		remote.calis();

        System.out.println("------Toplu calistirma-------");

        // toplu islem
        List<Command> commandList = new ArrayList<Command>();
        commandList.add(bestKargoGonderCommand);
        commandList.add(bestKargoIptalEtCommand);
        commandList.add(mngKargoGonderCommand);

        for(Command command  : commandList) {
            command.execute();
        }


    }
	
}
