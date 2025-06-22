package me.avo.jugen.audio

import me.avo.jugen.Language

enum class Voice(val id: String, val language: Language) {
    Xiaochen("zh-CN-XiaochenNeural", Language.Chinese), // best so far
    Xiaoxiao("zh-CN-XiaoxiaoNeural", Language.Chinese),
    Yunxi("zh-CN-YunxiNeural", Language.Chinese),
    Yunjian("zh-CN-YunjianNeural", Language.Chinese),
    Xiaoyi("zh-CN-XiaoyiNeural", Language.Chinese),
    Yunyang("zh-CN-YunyangNeural", Language.Chinese),
    XiaochenMultilingual("zh-CN-XiaochenMultilingualNeural", Language.Chinese),
    Xiaohan("zh-CN-XiaohanNeural", Language.Chinese),
    Xiaomeng("zh-CN-XiaomengNeural", Language.Chinese),
    Xiaomo("zh-CN-XiaomoNeural", Language.Chinese),
    Xiaoqiu("zh-CN-XiaoqiuNeural", Language.Chinese),
    Xiaorou("zh-CN-XiaorouNeural", Language.Chinese),
    Xiaorui("zh-CN-XiaoruiNeural", Language.Chinese),
    Xiaoshuang("zh-CN-XiaoshuangNeural", Language.Chinese),
    XiaoxiaoDialects("zh-CN-XiaoxiaoDialectsNeural", Language.Chinese),
    XiaoxiaoMultilingual("zh-CN-XiaoxiaoMultilingualNeural", Language.Chinese),
    Xiaoyan("zh-CN-XiaoyanNeural", Language.Chinese),
    Xiaoyou("zh-CN-XiaoyouNeural", Language.Chinese),
    XiaoyuMultilingual4("zh-CN-XiaoyuMultilingualNeural4", Language.Chinese),
    Xiaozhen("zh-CN-XiaozhenNeural", Language.Chinese),
    Yunfeng("zh-CN-YunfengNeural", Language.Chinese),
    Yunhao("zh-CN-YunhaoNeural", Language.Chinese),
    Yunjie("zh-CN-YunjieNeural", Language.Chinese),
    Yunxia("zh-CN-YunxiaNeural", Language.Chinese),
    Yunye("zh-CN-YunyeNeural", Language.Chinese),
    YunyiMultilingual("zh-CN-YunyiMultilingualNeural", Language.Chinese),
    Yunze("zh-CN-YunzeNeural", Language.Chinese),
    XiaochenDragonHDFlashLatest("zh-CN-Xiaochen:DragonHDFlashLatestNeural", Language.Chinese),
    XiaoxiaoDragonHDFlashLatest("zh-CN-Xiaoxiao:DragonHDFlashLatestNeural", Language.Chinese),
    Xiaoxiao2DragonHDFlashLatest("zh-CN-Xiaoxiao2:DragonHDFlashLatestNeural", Language.Chinese),
    YunfanMultilingual("zh-CN-YunfanMultilingualNeural", Language.Chinese),
    YunxiaoDragonHDFlashLatest("zh-CN-Yunxiao:DragonHDFlashLatestNeural", Language.Chinese),
    YunxiaoMultilingual("zh-CN-YunxiaoMultilingualNeural", Language.Chinese),
    YunyiDragonHDFlashLatest("zh-CN-Yunyi:DragonHDFlashLatestNeural", Language.Chinese),
    XiaochenDragonHDLatest("zh-CN-Xiaochen:DragonHDLatestNeural", Language.Chinese),
    YunfanDragonHDLatest("zh-CN-Yunfan:DragonHDLatestNeural", Language.Chinese);
    
    companion object {
        fun random() = entries.random()
        fun random(language: Language) = entries.filter { it.language == language }.random()
    }
}