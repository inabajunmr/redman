package xyz.dgz48.redman.web;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ResourceViewController {

	/**
	 * リソース一覧画面を表示する.
	 *
	 * @param model モデル
	 * @return テンプレートパス
	 */
	@GetMapping("/resource/list.html")
	public String viewList(final Model model) {
		log.debug("TODO view list"); // TODO view list
		return "resource/list";
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
		log.debug("TODO view detail"); // TODO view detail
		return "resource/detail";
	}
}
