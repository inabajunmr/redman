package xyz.dgz48.redman.web;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 開発者目的の{@link Controller}.
 *
 * @author win2cot
 *
 */
@Controller
@Profile("debug")
public class DevelopmentController {

	/**
	 * path:/starter.html.
	 *
	 * @param model モデル
	 * @return テンプレートパス
	 */
	@GetMapping("/starter.html")
	public String viewStarter(final Model model) {
		return "/starter";
	}

}