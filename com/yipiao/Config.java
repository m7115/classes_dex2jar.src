package com.yipiao;

import com.yipiao.bean.Note;
import com.yipiao.bean.NoteList;
import com.yipiao.bean.Train;
import com.yipiao.bean.TrainSort;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Config
{
  private static Config cfg = null;
  private NoteList areaCodes = null;
  public NoteList cardTypes = new NoteList();
  private String codes = "本市##bs;北京市#010#bji;天津市#022#tji;上海市#021#sha;重庆市#023#cqi;石家庄市#0311#sjz;邯郸市#0310#hda;邢台市#0319#xta;保定市#0312#bdi;张家口市#0313#zjk;承德市#0314#cde;唐山市#0315#tsh;秦皇岛市#0335#qhd;沧州市#0317#czh;廊坊市#0316#lfa;衡水市#0318#hsh;福州市#0591#fzh;厦门市#0592#xme;三明市#0598#smi;莆田市#0594#pti;泉州市#0595#qzh;漳州市#0596#zzh;南平市#0599#npi;宁德市#0593#nde;龙岩市#0597#lya;南昌市#0791#nch;景德镇市#0798#jdz;新余市#0790#xyu;九江市#0792#jji;鹰潭市#0701#yta;上饶市#0793#sra;宜春市#0795#ych;临川市#0794#lch;吉安市#0796#jan;赣州市#0797#gzh;济南市#0531#jna;青岛市#0532#qda;淄博市#0533#zbo;潍坊市#0536#wfa;烟台市#0535#yta;威海市#0631#whi;兖州市#0537#yzh;日照市#0633#rzh;德州市#0534#dzh;郓城县#0530#yc;太原市#0351#tyu;大同市#0352#dto;阳泉市#0353#yqu;长治市#0355#czh;朔州市#0349#szh;榆次市#0354#yci;孝义市#0358#xy;临汾市#0357#lfe;运城市#0359#ych;呼和浩特#0471#hht;包头市#0472#bto;巴林左旗#0476#blzq;二连浩特市#0479#rlht;满洲里市#0470#mzl;通辽市#0475#tli;准格尔旗#0477#zgl;乌兰浩特市#0482#wlt;郑州市#0371#zzh;开封市#0378#kfe;洛阳市#0379#lya;新乡市#0373#xxi;濮阳市#0393#ly;商丘市#0370#sqi;南阳市#0377#nya;周口市#0394#zko;汝南县#0396#rn;沈阳市#024#sya;大连市#0411#dli;鞍山市#0412#ash;抚顺市#0413#fsh;本溪市#0414#bxi;丹东市#0415#ddo;锦州市#0416#jzh;营口市#0417#yko;阜新市#0418#fxi;辽阳市#0419#lya;铁岭市#0410#tli;武汉市#027#wha;黄石市#0714#hsh;襄樊市#0710#xp;十堰市#0719#sya;宜昌市#0717#ych;荆门市#0714#jme;孝感市#0712#xga;黄冈市#0713#hg;恩施市#0718#esh;荆沙#0716#js;长春市#0431#cch;吉林市#0423#jli;四平市#0434#spi;辽源市#0437#lyu;通化市#0435#thu;临江市#0439#lji;大安市#0436#da;敦化市#0433#dhu;珲春市#0440#hc;长沙市#0731#csh;株州市#0733#zz;湘潭市#0732#xta;衡阳市#0734#hya;岳阳市#0730#yya;常德市#0736#cde;郴州市#0735#czh;益阳市#0737#yya;冷水滩#0746#lst;怀化市#0745#hhu;张家界#0744#zjj;哈尔滨市#0451#heb;齐齐哈尔市#0452#qqe;大庆市#0459#dqi;伊春市#0458#ych;牡丹江市#0453#mdj;佳木斯市#0454#jms;缓化市#0455#yh;漠河县#0457#mh;黑河市#0456#hhe;广州市#020#gzh;深圳市#0755#szh;珠海市#0756#zha;汕头市#0754#sto;韶关市#0751#sgu;惠州市#0752#hzh;东莞市#0769#dgu;中山市#0760#zsh;佛山市#0757#fsh;湛江市#0759#zji;南京市#025#nji;徐州市#0516#xzh;连云港市#0518#lyg;淮安市#0517#han;宿迁市#0527#sq;盐城市#0515#ych;扬州市#0514#yzh;南通市#0513#nto;镇江市#0511#zji;常州市#0519#czh;无锡市#0510#wxi;苏州市#0512#szh;常熟市#0520#cs;南宁市#0771#nni;柳州市#0772#lzh;桂林市#0773#gli;梧州市#0774#wzh;北海市#0779#bha;钦州市#0777#qzh;海口市#0898#hko;三亚市#0899#sya;儋州市#0890#zz;成都市#028#cdu;攀枝花市#0812#pzh;德阳市#0838#dya;绵阳市#0816#mya;自贡市#0813#zgo;内江市#0832#nji;乐山市#0833#lsh;泸州市#0830#lz;宜宾市#0831#ybi;杭州市#0571#hzh;宁波市#0574#nb;嘉兴市#0573#jxi;湖州市#0572#hz;绍兴市#0575#sxi;金华市#0579#jh;衢州市#0570#qzh;舟山市#0580#zs;温州市#0577#wzh;台州市#0576#tzh;贵阳市#0851#gya;遵义市#0852#zyi;安顺市#0853#ash;六盘水市#0858#lps;合肥市#0551#hfe;淮南市#0554#hna;蚌埠市#0552#bbu;马鞍山市#0555#mas;安庆市#0556#aqi;黄山市#0559#hsh;滁州市#0550#czh;宿州市#0557#szh;巢湖市#0565#chu;宣州市#0563#xz;昆明市#0871#kmi;昭通市#0870#zto;曲靖市#0874#qji;江川市#0877#jq;思茅市#0879#sm;丽江县#0888#lj;开远市#0873#ky;楚雄市#0878#cxi;西安市#029#xan;铜川市#0919#tc;宝鸡市#0917#bji;渭南市#0913#wna;商州市#0914#sz;拉萨市#0891#lsa;日喀则市#0892#rgz;仁布县#08018#rb;丁青县#08059#dq;阿里地区#0897#alsq;兰州市#0931#lzh;金昌市#0935#jch;天水市#0938#tsh;平凉市#0933#pli;玉门市#0937#ym;敦煌市#0937#dhu;西宁市#0971#xn;平安县#0972#pax;格尔木市#0979#gem;玛沁县#0975#mxx;银川市#0951#ych;石嘴山市#0952#szs;青铜峡市#0953#qtx;海原县#0954#hyx;乌鲁木齐市#0991#wlq;克拉玛依市#0990#kly;吐鲁番市#0995#tlf;喀什市#0998#ksh;阿图什市#0908#ats;库尔勒市#0996#kel";
  public Comparator<Train>[] comparators = new Comparator[4];
  public NoteList engines = new NoteList();
  public NoteList enginesDetail = new NoteList();
  public NoteList enterYears = new NoteList();
  public Map<String, String> http_header_hc = new HashMap();
  public NoteList monitorSeeds = new NoteList();
  public NoteList monitorSeeds2 = new NoteList();
  public NoteList numSelect = new NoteList();
  public NoteList passengerTypes = new NoteList();
  public NoteList province = new NoteList();
  public NoteList schoolSystems = new NoteList();
  public Map<String, Integer> seatCodeIndex = new HashMap();
  public String[] seatName;
  public NoteList seatTypeMobile = new NoteList();
  public NoteList seatTypeRateMobile = new NoteList();
  public NoteList seatTypes = new NoteList();
  public NoteList seatTypesAll = new NoteList();
  public NoteList seatTypesH = new NoteList();
  public NoteList seatTypesL = new NoteList();
  public NoteList sexTypes = new NoteList();
  public NoteList tickTypes = new NoteList();
  public NoteList tickTypesHint = new NoteList();
  public String[] timeRang;
  public NoteList trainTypeSimples = new NoteList();
  public NoteList userStatus = new NoteList();

  public Config()
  {
    this.comparators[0] = TrainSort.defaut;
    this.comparators[1] = TrainSort.fromTime;
    this.comparators[2] = TrainSort.toTime;
    this.comparators[3] = TrainSort.speed;
    this.engines.add(new Note("1", "12306旧版"));
    this.engines.add(new Note("2", "12306新版"));
    this.engines.add(new Note("3", "12306手机版"));
    this.enginesDetail.add(new Note("1", "速度更快更稳定"));
    this.enginesDetail.add(new Note("2", "体验12306新版"));
    this.enginesDetail.add(new Note("3", "登录免验证码，极速体验"));
    this.seatName = new String[] { "商务座", "特等座", "一等座", "二等座", "高级软卧", "软卧", "硬卧", "软座", "硬座", "无座", "其他" };
    this.seatCodeIndex.put("9", Integer.valueOf(0));
    this.seatCodeIndex.put("P", Integer.valueOf(1));
    this.seatCodeIndex.put("M", Integer.valueOf(2));
    this.seatCodeIndex.put("O", Integer.valueOf(3));
    this.seatCodeIndex.put("6", Integer.valueOf(4));
    this.seatCodeIndex.put("4", Integer.valueOf(5));
    this.seatCodeIndex.put("3", Integer.valueOf(6));
    this.seatCodeIndex.put("2", Integer.valueOf(7));
    this.seatCodeIndex.put("1", Integer.valueOf(8));
    this.seatCodeIndex.put("0", Integer.valueOf(9));
    this.http_header_hc.put("User-Agent", "Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_0 like Mac OS X; en-us) AppleWebKit/532.9 (KHTML, like Gecko) Version/4.0.5 Mobile/8A293 Safari/6531.22.7");
    this.http_header_hc.put("Accept", "application/json, text/javascript, */*");
    this.http_header_hc.put("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
    this.http_header_hc.put("Connection", "keep-alive");
    this.http_header_hc.put("Accept-Encoding", "gzip, deflate");
    this.sexTypes.add(new Note("M", "男"));
    this.sexTypes.add(new Note("F", "女"));
    this.monitorSeeds.add(new Note("200", "高速"));
    this.monitorSeeds.add(new Note("100", "正常速度"));
    this.monitorSeeds.add(new Note("50", "低速(省流量)"));
    this.monitorSeeds.add(new Note("10", "超低速(省流量)"));
    this.monitorSeeds2.add(new Note("400", "超高速"));
    this.monitorSeeds2.add(new Note("200", "高速"));
    this.monitorSeeds2.add(new Note("100", "正常速度"));
    this.monitorSeeds2.add(new Note("50", "低速(省流量)"));
    this.monitorSeeds2.add(new Note("10", "超低速(省流量)"));
    this.seatTypes.add(new Note("1", "硬座"));
    this.seatTypes.add(new Note("3", "硬卧"));
    this.seatTypes.add(new Note("O", "二等座"));
    this.seatTypes.add(new Note("8", "二等软座"));
    this.seatTypes.add(new Note("M", "一等座"));
    this.seatTypes.add(new Note("7", "一等软座"));
    this.seatTypes.add(new Note("9", "商务座"));
    this.seatTypes.add(new Note("P", "特等座"));
    this.seatTypes.add(new Note("4", "软卧"));
    this.seatTypes.add(new Note("6", "高级软卧"));
    this.seatTypes.add(new Note("2", "软座"));
    this.seatTypesAll.addAll(this.seatTypes);
    this.seatTypesAll.add(new Note("0", "无座"));
    this.seatTypeMobile.add(new Note("0", "棚车"));
    this.seatTypeMobile.add(new Note("1", "硬座"));
    this.seatTypeMobile.add(new Note("2", "软座"));
    this.seatTypeMobile.add(new Note("3", "硬卧"));
    this.seatTypeMobile.add(new Note("4", "软卧"));
    this.seatTypeMobile.add(new Note("5", "包厢硬卧"));
    this.seatTypeMobile.add(new Note("6", "高级软卧"));
    this.seatTypeMobile.add(new Note("7", "一等软座"));
    this.seatTypeMobile.add(new Note("8", "二等软座"));
    this.seatTypeMobile.add(new Note("9", "商务座"));
    this.seatTypeMobile.add(new Note("A", "鸳鸯软卧"));
    this.seatTypeMobile.add(new Note("B", "混编硬座"));
    this.seatTypeMobile.add(new Note("C", "混编硬卧"));
    this.seatTypeMobile.add(new Note("D", "包厢软座"));
    this.seatTypeMobile.add(new Note("E", "特等软座"));
    this.seatTypeMobile.add(new Note("F", "四人软包"));
    this.seatTypeMobile.add(new Note("G", "二人软包"));
    this.seatTypeMobile.add(new Note("H", "一人软包"));
    this.seatTypeMobile.add(new Note("I", "一等双软"));
    this.seatTypeMobile.add(new Note("J", "二等双软"));
    this.seatTypeMobile.add(new Note("K", "混编软座"));
    this.seatTypeMobile.add(new Note("L", "混编软卧"));
    this.seatTypeMobile.add(new Note("M", "一等座"));
    this.seatTypeMobile.add(new Note("O", "二等座"));
    this.seatTypeMobile.add(new Note("P", "特等座"));
    this.seatTypeMobile.add(new Note("Q", "观光座"));
    this.seatTypeMobile.add(new Note("S", "一等包座"));
    this.seatTypeRateMobile.add(new Note("0", "41"));
    this.seatTypeRateMobile.add(new Note("1", "23"));
    this.seatTypeRateMobile.add(new Note("2", "22"));
    this.seatTypeRateMobile.add(new Note("3", "21"));
    this.seatTypeRateMobile.add(new Note("4", "20"));
    this.seatTypeRateMobile.add(new Note("5", "40"));
    this.seatTypeRateMobile.add(new Note("6", "18"));
    this.seatTypeRateMobile.add(new Note("7", "24"));
    this.seatTypeRateMobile.add(new Note("8", "25"));
    this.seatTypeRateMobile.add(new Note("9", "10"));
    this.seatTypeRateMobile.add(new Note("A", "26"));
    this.seatTypeRateMobile.add(new Note("B", "27"));
    this.seatTypeRateMobile.add(new Note("C", "28"));
    this.seatTypeRateMobile.add(new Note("D", "29"));
    this.seatTypeRateMobile.add(new Note("E", "30"));
    this.seatTypeRateMobile.add(new Note("F", "31"));
    this.seatTypeRateMobile.add(new Note("G", "32"));
    this.seatTypeRateMobile.add(new Note("H", "33"));
    this.seatTypeRateMobile.add(new Note("I", "34"));
    this.seatTypeRateMobile.add(new Note("J", "35"));
    this.seatTypeRateMobile.add(new Note("K", "36"));
    this.seatTypeRateMobile.add(new Note("L", "37"));
    this.seatTypeRateMobile.add(new Note("M", "14"));
    this.seatTypeRateMobile.add(new Note("O", "16"));
    this.seatTypeRateMobile.add(new Note("P", "12"));
    this.seatTypeRateMobile.add(new Note("Q", "38"));
    this.seatTypeRateMobile.add(new Note("S", "39"));
    this.seatTypesH.add(new Note("M", "一等座"));
    this.seatTypesH.add(new Note("7", "一等软座"));
    this.seatTypesH.add(new Note("O", "二等座"));
    this.seatTypesH.add(new Note("8", "二等软座"));
    this.seatTypesH.add(new Note("9", "商务座"));
    this.seatTypesH.add(new Note("P", "特等座"));
    this.seatTypesL.add(new Note("6", "高级软卧"));
    this.seatTypesL.add(new Note("4", "软卧"));
    this.seatTypesL.add(new Note("3", "硬卧"));
    this.seatTypesL.add(new Note("2", "软座"));
    this.seatTypesL.add(new Note("1", "硬座"));
    this.cardTypes.add(new Note("1", "二代身份证"));
    this.cardTypes.add(new Note("C", "港澳通行证"));
    this.cardTypes.add(new Note("G", "台湾通行证"));
    this.cardTypes.add(new Note("B", "护照"));
    this.tickTypes.add(new Note("1", "成人票"));
    this.tickTypes.add(new Note("2", "儿童票"));
    this.tickTypes.add(new Note("3", "学生票"));
    this.tickTypes.add(new Note("4", "残军票"));
    this.tickTypesHint.add(new Note("1", "成"));
    this.tickTypesHint.add(new Note("2", "儿"));
    this.tickTypesHint.add(new Note("3", "学"));
    this.tickTypesHint.add(new Note("4", "军"));
    this.passengerTypes.add(new Note("1", "成人"));
    this.passengerTypes.add(new Note("2", "儿童"));
    this.passengerTypes.add(new Note("3", "学生"));
    this.passengerTypes.add(new Note("4", "伤残军人"));
    this.numSelect.add(new Note("1", "1"));
    this.numSelect.add(new Note("2", "2"));
    this.numSelect.add(new Note("3", "3"));
    this.numSelect.add(new Note("4", "4"));
    this.numSelect.add(new Note("5", "5"));
    this.numSelect.add(new Note("6", "6"));
    this.numSelect.add(new Note("7", "7"));
    this.numSelect.add(new Note("8", "8"));
    this.timeRang = new String[] { "00:00--24:00", "00:00--06:00", "06:00--12:00", "12:00--18:00", "18:00--24:00" };
    this.trainTypeSimples.add(new Note("G", "高铁"));
    this.trainTypeSimples.add(new Note("D,C", "动车/城际"));
    this.trainTypeSimples.add(new Note("QT", "其他"));
    this.province.add(new Note("11", "北京"));
    this.province.add(new Note("12", "天津"));
    this.province.add(new Note("13", "河北"));
    this.province.add(new Note("14", "山西"));
    this.province.add(new Note("15", "内蒙古"));
    this.province.add(new Note("21", "辽宁"));
    this.province.add(new Note("22", "吉林"));
    this.province.add(new Note("23", "黑龙江"));
    this.province.add(new Note("31", "上海"));
    this.province.add(new Note("32", "江苏"));
    this.province.add(new Note("33", "浙江"));
    this.province.add(new Note("34", "安徽"));
    this.province.add(new Note("35", "福建"));
    this.province.add(new Note("36", "江西"));
    this.province.add(new Note("37", "山东"));
    this.province.add(new Note("41", "河南"));
    this.province.add(new Note("42", "湖北"));
    this.province.add(new Note("43", "湖南"));
    this.province.add(new Note("44", "广东"));
    this.province.add(new Note("45", "广西"));
    this.province.add(new Note("46", "海南"));
    this.province.add(new Note("50", "重庆"));
    this.province.add(new Note("51", "四川"));
    this.province.add(new Note("52", "贵州"));
    this.province.add(new Note("53", "云南"));
    this.province.add(new Note("54", "西藏"));
    this.province.add(new Note("61", "陕西"));
    this.province.add(new Note("62", "甘肃"));
    this.province.add(new Note("63", "青海"));
    this.province.add(new Note("64", "宁夏"));
    this.province.add(new Note("65", "新疆"));
    this.province.add(new Note("71", "台湾"));
    this.province.add(new Note("81", "香港"));
    this.province.add(new Note("82", "澳门"));
    this.schoolSystems.add(new Note("1", "1年制"));
    this.schoolSystems.add(new Note("2", "2年制"));
    this.schoolSystems.add(new Note("3", "3年制"));
    this.schoolSystems.add(new Note("4", "4年制"));
    this.schoolSystems.add(new Note("5", "5年制"));
    this.schoolSystems.add(new Note("6", "6年制"));
    this.schoolSystems.add(new Note("7", "7年制"));
    this.schoolSystems.add(new Note("8", "8年制"));
    this.schoolSystems.add(new Note("9", "9年制"));
    this.enterYears.add(new Note("2014", "2014年入学"));
    this.enterYears.add(new Note("2013", "2013年入学"));
    this.enterYears.add(new Note("2012", "2012年入学"));
    this.enterYears.add(new Note("2011", "2011年入学"));
    this.enterYears.add(new Note("2010", "2010年入学"));
    this.enterYears.add(new Note("2009", "2009年入学"));
    this.enterYears.add(new Note("2008", "2008年入学"));
    this.enterYears.add(new Note("2007", "2007年入学"));
    this.enterYears.add(new Note("2006", "2006年入学"));
    this.enterYears.add(new Note("2005", "2005年入学"));
    this.enterYears.add(new Note("2004", "2004年入学"));
    this.enterYears.add(new Note("2003", "2003年入学"));
    this.userStatus.add(new Note("0", "新添加"));
    this.userStatus.add(new Note("1", "已通过"));
    this.userStatus.add(new Note("2", "未通过"));
    this.userStatus.add(new Note("3", "待核验"));
    this.userStatus.add(new Note("4", "预通过"));
    this.userStatus.add(new Note("5", "请报验"));
  }

  public static Config getInstance()
  {
    if (cfg == null)
      cfg = new Config();
    return cfg;
  }

  public NoteList getAllAreaCodes()
  {
    if (this.areaCodes != null)
      return this.areaCodes;
    this.areaCodes = new NoteList();
    String[] arrayOfString1 = this.codes.split(";");
    for (int i = 0; i < arrayOfString1.length; i++)
    {
      String[] arrayOfString2 = arrayOfString1[i].split("#");
      this.areaCodes.add(new Note(arrayOfString2[1], arrayOfString2[0], arrayOfString2[2]));
    }
    return this.areaCodes;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.Config
 * JD-Core Version:    0.6.0
 */