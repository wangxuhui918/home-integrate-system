

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package cn.bigcore.micro.plugin.i18n;

public enum I18n {

    ZH("zh", "中国"), EN("en", "英国"), CORE("core", "核心"), AT("at", "奥地利"), AD("ad", "安道尔"), AE("ae", "阿联酋"),
    AF("af", "阿富汗"), AI("ai", "安奎拉"), AL("al", "阿尔巴尼亚"), AM("am", "亚美尼亚"), AO("ao", "安哥拉"), AQ("aq", "南极洲"),
    AR("ar", "阿根廷"), AS("as", "美属萨摩亚群岛"), AU("au", "澳大利亚"), AZ("az", "阿塞拜疆"), BA("ba", "波斯尼亚和黑塞哥维那"), BB("bb", "巴巴多斯"),
    BD("bd", "孟加拉"), BE("be", "比利时"), BF("bf", "布基那法索"), BG("bg", "保加利亚"), BH("bh", "巴林"), BI("bi", "布隆迪"),
    BJ("bj", "贝宁"), BM("bm", "百慕大"), BN("bn", "文莱"), BO("bo", "玻利维亚"), BR("br", "巴西"), BS("bs", "巴哈马"), BT("bt", "不丹"),
    BW("bw", "博茨瓦纳"), BZ("bz", "伯里兹"), CA("ca", "加拿大"), CF("cf", "中非共和国"), CG("cg", "刚果"), CH("ch", "瑞士"),
    CI("ci", "象牙海岸"), CK("ck", "库克群岛"), CL("cl", "智利"), CM("cm", "喀麦隆"), CN("cn", "中国"), CO("co", "哥伦比亚"),
    CR("cr", "哥斯达黎加"), CS("cs", "捷克斯洛伐克（前）"), CU("cu", "古巴"), CV("cv", "佛得角群岛"), CY("cy", "塞浦路斯"), CZ("cz", "捷克共和国"),
    DE("de", "德国"), DJ("dj", "吉布提"), DK("dk", "丹麦"), DM("dm", "多米尼加"), DO("do", "多米尼加共和国"), DZ("dz", "阿尔及利亚"),
    EC("ec", "厄瓜多尔"), EE("ee", "爱沙尼亚"), EG("eg", "埃及"), EH("eh", "西撒哈拉"), ER("er", "厄立特利亚"), ES("es", "西班牙"),
    ET("et", "埃塞俄比亚"), FI("fi", "芬兰"), FJ("fj", "斐济"), FK("fk", "马尔维那斯群岛"), FM("fm", "密克罗尼西亚"), FR("fr", "法国"),
    GA("ga", "加蓬"), GB("gb", "英国"), GD("gd", "格林纳达"), GE("ge", "乔治亚"), GF("gf", "法属圭亚那"), GH("gh", "加纳"),
    GI("gi", "直布罗陀"), GL("gl", "格陵兰（岛）"), GM("gm", "赞比亚"), GN("gn", "几内亚"), GP("gp", "瓜德罗普"), GQ("gq", "赤道几内亚"),
    GR("gr", "希腊"), GT("gt", "危地马拉"), GU("gu", "关岛"), GW("gw", "几内亚比绍"), GY("gy", "圭亚那"), HK("hk", "香港"),
    HN("hn", "洪都拉斯"), HR("hr", "克罗地亚"), HT("ht", "海地"), HU("hu", "匈牙利"), ID("id", "印度尼西亚"), IE("ie", "爱尔兰"),
    IL("il", "以色列"), IN("in", "印度"), IQ("iq", "伊拉克"), IR("ir", "伊朗"), IS("is", "冰岛"), IT("it", "意大利"), JM("jm", "牙买加"),
    JO("jo", "约旦"), JP("jp", "日本"), KE("ke", "肯尼亚"), KH("kh", "柬埔寨"), KM("km", "科摩罗群岛"), KP("kp", "韩国"),
    KR("kr", "北朝鲜"), KW("kw", "科威特"), KY("ky", "开曼群岛"), KZ("kz", "哈萨克斯坦"), LA("la", "老挝"), LB("lb", "黎巴嫩"),
    LC("lc", "圣路西亚"), LI("li", "列支敦士登"), LK("lk", "斯里兰卡"), LR("lr", "利比里亚"), LS("ls", "莱索托"), LT("lt", "立陶宛"),
    LU("lu", "卢森堡"), LV("lv", "拉托维亚"), LY("ly", "利比亚"), MA("ma", "摩洛哥"), MC("mc", "摩纳哥"), MD("md", "摩尔多瓦"),
    MG("mg", "马达加斯加"), MH("mh", "马绍尔群岛"), ML("ml", "马里"), MN("mn", "蒙古"), MO("mo", "澳门"), MP("mp", "南马利亚那群岛"),
    MQ("mq", "马提尼克岛"), MR("mr", "毛里塔尼亚"), MS("ms", "蒙特塞拉特克岛"), MT("mt", "马耳他"), MU("mu", "毛里求斯"), MV("mv", "马尔代夫"),
    MW("mw", "马拉维"), MX("mx", "墨西哥"), MY("my", "马来西亚"), MZ("mz", "莫桑比克"), NA("na", "纳米比亚"), NC("nc", "新喀里多尼亚岛"),
    NE("ne", "尼日尔"), NG("ng", "尼日利亚"), NI("ni", "尼加拉瓜"), NL("nl", "荷兰"), NO("no", "挪威"), NP("np", "尼泊尔"),
    NR("nr", "瑙鲁"), NU("nu", "纽埃岛"), NZ("nz", "新西兰"), OM("om", "阿曼"), PA("pa", "巴拿马"), PE("pe", "秘鲁"),
    PF("pf", "法属玻利尼西亚"), PG("pg", "巴布亚新几内亚"), PH("ph", "菲律宾"), PK("pk", "巴基斯坦"), PL("pl", "波兰"), PR("pr", "波多黎哥"),
    PT("pt", "葡萄牙"), PY("py", "巴拉圭"), QA("qa", "卡塔尔"), RE("re", "留尼汪岛"), RO("ro", "罗马尼亚"), RU("ru", "俄罗斯"),
    RW("rw", "卢旺达"), SA("sa", "沙特阿拉伯"), Sb("sb", "所罗门群岛"), SC("sc", "塞舌尔"), SD("sd", "苏丹"), SE("se", "瑞典"),
    SG("sg", "新加坡"), SH("sh", "圣赫勒拿岛"), SI("si", "斯洛文尼亚"), SJ("sj", "斯瓦巴德群岛"), SK("sk", "斯洛伐克"), SL("sl", "塞拉利昂"),
    SM("sm", "圣马力诺"), SN("sn", "塞内加尔"), SO("so", "索马里"), SR("sr", "苏里南"), ST("st", "圣多美岛和普林西比岛"), SU("su", "苏联（前）"),
    SV("sv", "萨尔瓦多"), SY("sy", "叙利亚"), SZ("sz", "斯威士兰"), TD("td", "乍得"), TG("tg", "多哥"), TH("th", "泰国"),
    TJ("tj", "塔吉克斯坦"), TK("tk", "托克劳群岛"), TM("tm", "土库曼斯坦TN"), TO("to", "汤加"), TP("tp", "东帝汶岛"), TT("tt", "特立尼达和多巴哥"),
    TW("tw", "中国台湾"), TZ("tz", "坦桑尼亚"), UA("ua", "乌克兰"), UG("ug", "乌干达"), UK("uk", "英国"), US("us", "美国"),
    UY("uy", "乌拉圭"), UZ("uz", "乌兹别克斯坦"), VA("va", "梵蒂冈"), VE("ve", "委内瑞拉"), VG("vg", "维京岛（英）"), VI("vi", "维京岛（美）"),
    VN("vn", "越南"), WF("wf", "瓦利斯群岛"), WS("ws", "萨摩亚群岛"), YE("ye", "也门"), YU("yu", "南斯拉夫"), ZA("za", "南非"),
    ZM("zm", "赞比亚"), ZR("zr", "扎伊尔"), ZW("zw", "津巴布韦"), NOT_FOUNT(null, null);

    public final static I18n defaultI18n = I18n.CN;

    private final String i18nCode;

    private final String country;

    I18n(String i18nCode, String country) {
        this.i18nCode = i18nCode;
        this.country = country;
    }

    public String getI18nCode() {
        return i18nCode;
    }

    public String getCountry() {
        return country;
    }

}
