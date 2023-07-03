package parking.utils.menu;

import java.util.ArrayList;

import lombok.Getter;
import parking.utils.common.console.BreadcrumbPrompt;
import parking.utils.listener.ActionListener;

@Getter
public class Menu {
	private String title;
	private ArrayList<ActionListener> listeners = new ArrayList<>();

	public Menu(String title) {
		this.title = title;
	}

	public Menu(String title, ActionListener listener) {
		this(title);
		this.addActionListener(listener);
	}

	public void addActionListener(ActionListener listener) {
		this.listeners.add(listener);
	}

	public void removeActionListener(ActionListener listener) {
		this.listeners.remove(listener);
	}

	public void execute(BreadcrumbPrompt prompt) {
		for (ActionListener listener : listeners) {
			listener.service(prompt);
		}
	}

}
