package client.historyEvent;

public class HistoryEventController {

	HistoryEventModel heModel;
	HistoryEventView heView;
	
	public HistoryEventController()
	{
		System.out.println("HistoryEvent Controller:");
	}
	
	public void addModel(HistoryEventModel m)
	{
		System.out.println("Adding HistoryEvent model");
		this.heModel = m;
	}
	
	public void addView(HistoryEventView v)
	{
		System.out.println("Adding HistoryEvent View");
	}
}
