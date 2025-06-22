package me.avo.jugen.audio

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
    
    companion object {
        fun random() = entries.random()
    }
}