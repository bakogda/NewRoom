package clients.removeEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveEventController implements ActionListener {
	
	RemoveEventModel rmModel;
	RemoveEventView rmView;

	public void addModel(RemoveEventModel m) {
		System.out.println("Adding removeEvent Model");
		this.rmModel = m;
	}

	public void addView(RemoveEventView v) {
		System.out.println("Adding removeEvent View");
		this.rmView = v;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
