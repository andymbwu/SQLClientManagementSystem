/**
 * This class is responsible for creating the View, the Model, and the Controller that runs
 * the program.
 */
public class MVCUser {

	public static void main(String[] args) {

		UserView view = new UserView();
		UserModel model = new UserModel();

		UserController controller = new UserController(view, model);
	}
}
