package me.avo.jugen.audio

import me.avo.jugen.Language

enum class Voice(val id: String) {
    Xiaochen("zh-CN-XiaochenNeural"), // best so far
    Xiaoxiao("zh-CN-XiaoxiaoNeural"),
    Yunxi("zh-CN-YunxiNeural"),
    Yunjian("zh-CN-YunjianNeural"),
    Xiaoyi("zh-CN-XiaoyiNeural"),
    Yunyang("zh-CN-YunyangNeural"),
    XiaochenMultilingual("zh-CN-XiaochenMultilingualNeural"),
    Xiaohan("zh-CN-XiaohanNeural"),
    Xiaomeng("zh-CN-XiaomengNeural"),
    Xiaomo("zh-CN-XiaomoNeural"),
    Xiaoqiu("zh-CN-XiaoqiuNeural"),
    Xiaorou("zh-CN-XiaorouNeural"),
    Xiaorui("zh-CN-XiaoruiNeural"),
    Xiaoshuang("zh-CN-XiaoshuangNeural"),
    XiaoxiaoDialects("zh-CN-XiaoxiaoDialectsNeural"),
    XiaoxiaoMultilingual("zh-CN-XiaoxiaoMultilingualNeural"),
    Xiaoyan("zh-CN-XiaoyanNeural"),
    Xiaoyou("zh-CN-XiaoyouNeural"),
    XiaoyuMultilingual4("zh-CN-XiaoyuMultilingualNeural4"),
    Xiaozhen("zh-CN-XiaozhenNeural"),
    Yunfeng("zh-CN-YunfengNeural"),
    Yunhao("zh-CN-YunhaoNeural"),
    Yunjie("zh-CN-YunjieNeural"),
    Yunxia("zh-CN-YunxiaNeural"),
    Yunye("zh-CN-YunyeNeural"),
    YunyiMultilingual("zh-CN-YunyiMultilingualNeural"),
    Yunze("zh-CN-YunzeNeural"),
    XiaochenDragonHDFlashLatest("zh-CN-Xiaochen:DragonHDFlashLatestNeural"),
    XiaoxiaoDragonHDFlashLatest("zh-CN-Xiaoxiao:DragonHDFlashLatestNeural"),
    Xiaoxiao2DragonHDFlashLatest("zh-CN-Xiaoxiao2:DragonHDFlashLatestNeural"),
    YunfanMultilingual("zh-CN-YunfanMultilingualNeural"),
    YunxiaoDragonHDFlashLatest("zh-CN-Yunxiao:DragonHDFlashLatestNeural"),
    YunxiaoMultilingual("zh-CN-YunxiaoMultilingualNeural"),
    YunyiDragonHDFlashLatest("zh-CN-Yunyi:DragonHDFlashLatestNeural"),
    XiaochenDragonHDLatest("zh-CN-Xiaochen:DragonHDLatestNeural"),
    YunfanDragonHDLatest("zh-CN-Yunfan:DragonHDLatestNeural");
    
    val language: Language get() = when {
        id.startsWith("zh-CN") -> Language.Chinese
        id.startsWith("en-US") -> Language.English
        id.startsWith("ja-JP") -> Language.Japanese
        id.startsWith("ko-KR") -> Language.Korean
        id.startsWith("es-ES") -> Language.Spanish
        id.startsWith("fr-FR") -> Language.French
        id.startsWith("de-DE") -> Language.German
        id.startsWith("it-IT") -> Language.Italian
        id.startsWith("pt-PT") -> Language.Portuguese
        id.startsWith("ru-RU") -> Language.Russian
        id.startsWith("ar-SA") -> Language.Arabic
        id.startsWith("hi-IN") -> Language.Hindi
        else -> Language.Chinese // default fallback
    }
    
    companion object {
        fun random() = entries.random()
        fun random(language: Language) = entries.filter { it.language == language }.random()
    }
}