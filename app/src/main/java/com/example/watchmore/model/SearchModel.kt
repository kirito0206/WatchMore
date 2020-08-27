package com.example.watchmore.model

import com.example.watchmore.R

class SearchModel {
    var imageList = arrayListOf<Int>(
        R.drawable.banner_image_one,
        R.drawable.banner_image_two,
        R.drawable.banner_image_three)

    var styleTagMap = mapOf(
        "10010" to  "原创",
        "10011" to  "漫画改",
        "10012" to  "小说改",
        "10013" to  "游戏改",
        "10014" to  "布袋戏",
        "10016" to  "热血",
        "10017" to  "穿越",
        "10018" to  "奇幻",
        "10020" to  "战斗",
        "10021" to  "搞笑",
        "10022" to  "日常",
        "10023" to  "科幻",
        "10024" to  "萌系",
        "10025" to  "治愈",
        "10026" to  "校园",
        "10027" to  "少儿",
        "10028" to  "泡面",
        "10029" to  "恋爱",
        "10030" to  "少女",
        "10031" to  "魔法",
        "10032" to  "冒险",
        "10033" to  "历史",
        "10034" to  "架空",
        "10035" to  "机战",
        "10036" to  "神魔",
        "10037" to  "声控",
        "10038" to  "运动",
        "10039" to  "励志",
        "10040" to  "音乐",
        "10041" to  "推理",
        "10042" to  "社团",
        "10043" to  "智斗",
        "10044" to  "催泪",
        "10045" to  "美食",
        "10046" to  "偶像",
        "10047" to  "乙女",
        "10048" to  "职场"
    )

    var timeTagMap = linkedMapOf(
        "全部" to "10010",
        "2020" to "2020",
        "2019" to "2019",
        "2018" to "2018",
        "2017" to "2017",
        "2016" to "2016",
        "2015" to "2015",
        "2014-2010" to "2014-2010",
        "2009-2005" to "2009-2005",
        "2004-2000" to "2004-2000",
        "90年代" to "90年代",
        "80年代" to "80年代",
        "更早" to "更早")
}