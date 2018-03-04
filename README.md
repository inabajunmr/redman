# Redman
Infrared signal manager.

# このリポジトリの大雑把な目標
 * 赤外線シグナルの組み合わせを定義してIFTTTにデプロイしたい
 * テレビつけつつ3チャンネルにする、とかエアコンつけつつ加湿する、とか。

# Design
## Sequence Diagram
![sequence]("https://cacoo.com/diagrams/9lm4vRTa7oxogelN-E7D7C.png")
https://cacoo.com/diagrams/9lm4vRTa7oxogelN

## Sitemap
![sitemap]("https://cacoo.com/diagrams/9lm4vRTa7oxogelN-3F631.png?")
https://cacoo.com/diagrams/9lm4vRTa7oxogelN#3F631

# References
https://developer.nature.global/

https://home.nature.global

https://platform.ifttt.com/docs

# Definition of terms
## リソース
エンドポイントをどう叩くか？の定義。このURLをこのパラメータで、このメソッドで、このヘッダで・・・のイメージ。

## アクティビティ
APIを叩くフローの定義。リソースをこの順番で、このタイミングでスリープを挟んで、ここで失敗したらXXXして・・・・のイメージ

## アクション
ない。
