package xyz.dgz48.redman.web;

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
public class ResourceNewController {

	/**
	 * リソース登録入力画面を表示する.
	 *
	 * @param model モデル
	 * @return テンプレートパス
	 */
	@GetMapping("/input.html")
	public String viewInput(final Model model) {
		return "/resource/new/input";
	}

	/**
	 * リソース登録入力画面の入力値を検証する.
	 *
	 * @param model モデル
	 * @return テンプレートパス
	 */
	@PostMapping(path = "/input.html")
	public String validate(final Model model) {
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
		return "/resource/new/confirm";
	}

	/**
	 * リソースを登録する.
	 *
	 * @param model モデル
	 * @return テンプレートパス
	 */
	@PostMapping(path = "/confirm.html")
	public String register(final Model model) {
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
		return "/resource/new/complete";
	}
}
