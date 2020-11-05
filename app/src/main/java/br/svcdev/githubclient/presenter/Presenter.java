package br.svcdev.githubclient.presenter;

import br.svcdev.githubclient.model.Model;
import br.svcdev.githubclient.view.MainView;

public class Presenter {

	private MainView view;
	private Model model = new Model();

	public Presenter(MainView view) {
		this.view = view;
	}

	public void onCounterClick(int index) {
		view.setButtonText(index, String.valueOf(model.incrementValue(index)));
	}

}
