package parking.utils.listener;

import parking.utils.common.console.BreadcrumbPrompt;

public interface ActionListener {
	void service(BreadcrumbPrompt prompt);
}
