package xyz.dgz48.redman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * メインクラス.
 *
 * @author win2cot
 */
@SpringBootApplication
public class Application {

	/**
	 * コンストラクタ.<BR>
	 * <BR>
	 * checkstyle - HideUtilityClassConstructorを回避<BR>
	 * http://checkstyle.sourceforge.net/config_design.html#HideUtilityClassConstructor#HideUtilityClassConstructor
	 */
	protected Application() {
		// nop
	}

	/**
	 * 実行エントリポイント.
	 *
	 * @param args 実行時引数
	 */
	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
