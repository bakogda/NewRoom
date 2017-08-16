package admin;

public class RunMVC {
	/*
	 * This class starts off all of the displays beginning with the login GUI
	 * The model, view and controller are connected appropriately
	 */

	public RunMVC() {
		Model myModel = new Model();
		View myView = new View();

		myModel.addObserver(myView);

		// create Controller. tell it about Model and View, initialise model
		Controller myController = new Controller();
		myController.addModel(myModel);
		myController.addView(myView);

		// tell View about controller
		myView.addController(myController);

	}
}
