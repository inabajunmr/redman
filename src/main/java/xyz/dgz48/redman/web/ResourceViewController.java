package xyz.dgz48.redman.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * {@link Controller} for Resource list page.
 *
 * @author win2cot
 *
 */
@Controller
public class ResourceViewController {

	/**
	 * リソース一覧画面を表示する.
	 *
	 * @param model モデル
	 * @return テンプレートパス
	 */
	@GetMapping("/resource/list.html")
	public String viewList(final Model model) {
		return "/resource/list";
	}

	/**
	 * リソース詳細画面を表示する.
	 *
	 * @param model モデル
	 * @param id ID
	 * @return テンプレートパス
	 */
	@GetMapping(path = "/resource/{id}/detail.html")
	public String viewDetail(final Model model, @PathVariable("id") final String id) {
		return "/resource/detail";
	}
}
