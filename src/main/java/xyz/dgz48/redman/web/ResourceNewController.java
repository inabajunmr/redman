package xyz.dgz48.redman.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * {@link Controller} for Resource create flow.
 *
 * @author win2cot
 *
 */
@Controller
@RequestMapping("/resource/new")
@Slf4j
public class ResourceNewController {

	/**
	 * リソース登録入力画面を表示する.
	 *
	 * @param model モデル
	 * @return テンプレートパス
	 */
	@GetMapping("/input.html")
	public String viewInput(final Model model) {
		log.debug("TODO view input"); // TODO view input
		return "resource/new/input";
	}

	/**
	 * リソース登録入力画面の入力値を検証する.
	 *
	 * @param model モデル
	 * @return テンプレートパス
	 */
	@PostMapping(path = "/input.html")
	public String validate(final Model model) {
		log.debug("TODO validate"); // TODO validate
		return "redirect:/resource/new/confirm.html";
	}

	/**
	 * リソース登録確認画面を表示する.
	 *
	 * @param model モデル
	 * @return テンプレートパス
	 */
	@GetMapping(path = "/confirm.html")
	public String viewConfirm(final Model model) {
		log.debug("TODO view confirm"); // TODO view confirm
		return "resource/new/confirm";
	}

	/**
	 * リソースを登録する.
	 *
	 * @param model モデル
	 * @return テンプレートパス
	 */
	@PostMapping(path = "/confirm.html")
	public String register(final Model model) {
		log.debug("TODO register"); // TODO register
		return "redirect:/resource/new/complete.html";
	}

	/**
	 * リソース登録完了画面を表示する.
	 *
	 * @param model モデル
	 * @return テンプレートパス
	 */
	@GetMapping(path = "/complete.html")
	public String viewComplete(final Model model) {
		log.debug("TODO view complete"); // TODO view complete
		return "resource/new/complete";
	}
}
