package Gaufrette;

import Gaufrette.Vue.Application;
import Gaufrette.Vue.Console.ApplicationConsole;

public class Main {

	public static void main(String[] args) {
        Application app = new ApplicationConsole();
        app.run();
	}

}
