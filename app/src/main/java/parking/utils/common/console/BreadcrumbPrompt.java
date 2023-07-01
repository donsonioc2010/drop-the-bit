package parking.utils.common.console;

import java.util.Stack;

public class BreadcrumbPrompt extends Prompt {
	private Stack<String> breadCrumbs = new Stack<>();

	public void appendBreadcrumb(String title) {
		this.breadCrumbs.push(title);
	}

	public void removeBreadcrumb() {
		this.breadCrumbs.pop();
	}

	public String inputMenu() {
		StringBuilder titleBuilder = new StringBuilder();

		for (String title : breadCrumbs) {
			if (titleBuilder.length() > 0) {
				titleBuilder.append("/");
			}
			titleBuilder.append(title);
		}
		titleBuilder.append("> ");
		return this.getInputString(titleBuilder.toString());
	}
}
