package me.avo.jugen.audio

import me.avo.jugen.Language
import me.avo.jugen.Language.*

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
    YunxiaoMultilingual("zh-CN-YunxiaoMultilingualNeural"), // 不错
    YunyiDragonHDFlashLatest("zh-CN-Yunyi:DragonHDFlashLatestNeural"),
    XiaochenDragonHDLatest("zh-CN-Xiaochen:DragonHDLatestNeural"),
    YunfanDragonHDLatest("zh-CN-Yunfan:DragonHDLatestNeural"),
    
    // Japanese voices
    Nanami("ja-JP-NanamiNeural"),
    Keita("ja-JP-KeitaNeural"),
    Aoi("ja-JP-AoiNeural"),
    Daichi("ja-JP-DaichiNeural"),
    Mayu("ja-JP-MayuNeural"),
    Naoki("ja-JP-NaokiNeural"),
    Shiori("ja-JP-ShioriNeural"),
    MasaruMultilingual("ja-JP-MasaruMultilingualNeural"),
    MasaruDragonHDLatest("ja-JP-Masaru:DragonHDLatestNeural"),
    NanamiDragonHDLatest("ja-JP-Nanami:DragonHDLatestNeural");
    
    val language: Language get() = when (id.take(5)) {
        "zh-CN" -> Chinese
        "en-US" -> English
        "ja-JP" -> Japanese
        "ko-KR" -> Korean
        "es-ES" -> Spanish
        "fr-FR" -> French
        "de-DE" -> German
        "it-IT" -> Italian
        "pt-PT" -> Portuguese
        "ru-RU" -> Russian
        "ar-SA" -> Arabic
        "hi-IN" -> Hindi
        else -> throw IllegalArgumentException("Unsupported language prefix: ${id.take(5)}")
    }
    
    companion object {
        fun random() = entries.random()
        fun random(language: Language) = entries.filter { it.language == language }.random()
    }
}