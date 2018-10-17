/**
 * 打包和解析pos报文
 */
function requestMess() {
	this.PackType = '';
	this.Amount = '000000000000';
	this.Point = '000000000000';
	this.CardNo = '                   ';
	this.Expr = '    ';
	this.TraceNo = '      ';
	this.AuthId = '      ';
	this.RefNo = '            ';
	this.DateDate = '        ';
	this.DateTime = '      ';
	this.DesktopNo = '        ';
	this.Operator = '         ';
	this.TermId = '        ';
	this.tranId = '                                ';



	/* 交易类型 ，2 */
	this.setSzPackType = function(packType) {
		this.PackType = packType;
	};
	/* 交易金额，12，前补0，精确到分 */
	this.setSzAmount = function(amount) {
		if (amount != null) {
			var count = 12 - amount.length;
			for (var i = 0; i < count; i++) {
				amount = '0' + amount;
			}
			this.Amount = amount;
		}
	};
	/* 需要使用的积分数，12,前补0 */
	this.setSzPoint = function(point) {
		if (point != null) {
			var count = 12 - point.length;
			for (var i = 0; i < count; i++) {
				point = '0' + point;
			}
			this.Point = point;
		}
	};
	/* 卡号 （撤销、预授权完成时），19 */
	this.setSzCardNo = function(cardNo) {
		this.CardNo = cardNo;
	};
	/* 有效期 （撤销、预授权完成时），4 */
	this.setSzExpr = function(expr) {
		this.Expr = expr;
	};
	/* 原流水号（退货，非接退货用）or 原票据号 （重打印，撤销用），6 */
	this.setSzTraceNo = function(traceNo) {
		this.TraceNo = traceNo;
	};
	/* 原授权码（退货用），6，若为空，英文或不足6位，则如实填写 */
	this.setSzAuthId = function(authid) {
		this.AuthId = authid;
	};
	/* 原参考号（退货，非接退货用）， 12 */
	this.setSzRefNo = function(refno) {
		this.RefNo = refno;
	};
	/* 原日期（退货用）格式为 YYYYMMDD，8 */
	this.setSzDateDate = function(datedate) {
		this.DateDate = datedate;
	};
	/* 原时间（退货用）格式为 MMDDSS ，6 */
	this.setSzDateTime = function(datetime) {
		this.DateTime = datetime;
	};
	/* 款台号 ，8 */
	this.setSzDesktopNo = function(desktopno) {
		if (desktopno != null) {
			var count = 12 - desktopno.length;
			for (var i = 0; i < count; i++) {
				desktopno = '0' + desktopno;
			}
			this.DesktopNo = desktopno;
		}
	};
	/* 收款员号 ，9 */
	this.setSzOperator = function(operator) {
		if (operator != null) {
			var count = 12 - operator.length;
			for (var i = 0; i < count; i++) {
				operator = '0' + operator;
			}
			this.Operator = operator;
		}
	};
	/* 原终端号（非接退货用），8 */
	this.setSzTermId = function(termid) {
		if (termid != null)
		this.TermId = termid;
	};

	/*交易编号，32*/
    this.setSzTranId = function(tranId) {
        this.tranId = tranId;
    };

	/* 拼接报文 */
	this.packMessage = function() {
		var message = '';
		//50：积分消费  51：(积分+现金)消费  52：撤销  58:交易查询
		if (this.PackType == '50' || this.PackType == '52'|| this.PackType == '54'|| this.PackType == '58'){
			message =
				this.PackType + this.Amount + this.Point + this.CardNo
				+ this.Expr + this.TraceNo + this.AuthId + this.RefNo
				+ this.DateDate + this.DateTime + this.DesktopNo+ this.Operator
				+ this.TermId+this.tranId;
		}
		return message;
	}

}

function substr(str, start, end) {
	alert('unic编码前的字符串++|'+str+'|');
	alert('unic编码前的jsp页面字符串长度为++|'+str.length+'|');
	var esStr = escape(str);
	alert('unic编码后的字符串++|'+esStr+'|');
	var strArray = new Array();
	for (var i = 0; i < esStr.length;) {
		if (esStr[i] == '%' && esStr[i + 1] == 'u') { //中文拼做一个数组，并多添加一个空数组模拟中文两个字节
			var str = esStr[i++] + esStr[i++] + esStr[i++] + esStr[i++]
					+ esStr[i++] + esStr[i++];
			strArray.push(str);
			strArray.push('');
		} else if (esStr[i] == '%' && esStr[i + 1] == '2' //空格拼做一个数组
				&& esStr[i + 2] == '0') {
			var str = esStr[i++] + esStr[i++] + esStr[i++];
			strArray.push(str);
		} else {
			strArray.push(esStr[i++]);
		}
	}
	alert('数组编码后字符串长度为++|'+strArray.length+'|');
	var ss = strArray.slice(start, end).join('');
	return unescape(ss);
}

function responseMess(respMess) {
	/* 交易类型,2 */
	this.getSzPackType = function() {
		return respMess.substring(0, 2);
	};
	/* 交易结果返回码,2 */
	this.getSzResult = function() {
		return respMess.substring(2, 4);
	};
	/* 中文结果,40 */
	this.getSzCHNResult = function() {
		return respMess.substring(4, 44);
	};
	/* 卡号,19 */
	this.getSzCardNo = function() {
		return respMess.substring(44, 63);
	};
	/* 有效期,4 */
	this.getSzExpr = function() {
		return respMess.substring(63, 67);
	};
	/* 商户名称,40 */
	this.getSzMerchantName = function() {
		return respMess.substring(67, 107);
	};
	/* 商户号,15 */
	this.getSzMerchant = function() {
		return respMess.substring(107, 122);
	};
	/* 终端号,8 */
	this.getSzTermId = function() {
		return respMess.substring(122, 130);
	};
	/* 发卡行名称 该域和内部大小不同，直接返回中文名称,10 */
	this.getSzBankName = function() {
		return respMess.substring(130, 140);
	};
	/* 卡类型,2 */
	this.getSzCardType = function() {
		return respMess.substring(140, 142);
	};
	/* 交易时间,14 */
	this.getSzChargeDateTime = function() {
		return respMess.substring(142, 156);
	};
	/* 系统参考号,12 */
	this.getSzRefNo = function() {
		return respMess.substring(156, 168);
	};
	/* 授权号,6 */
	this.getSzAuthId = function() {
		return respMess.substring(168, 174);
	};
	/* 流水号,6 */
	this.getSzTraceNo = function() {
		return respMess.substring(174, 180);
	};
	/* 票据号,6 */
	this.getSzSeqNo = function() {
		return respMess.substring(180, 186);
	};
	/* 批次号,6 */
	this.getSzBatchNo = function() {
		return respMess.substring(186, 192);
	};
	/* 交易金额,12 */
	this.getSzAmount = function() {
		var amount = respMess.substring(192, 204);
		var i = 0;
		var subNum = 0;
		while (amount.charAt(i) == '0') {
			i++;
		}
		return amount.substring(i, amount.length);
	};
	/* 优惠金额,12 */
	this.getSzDctAmount = function() {
		var dctAmount = respMess.substring(204, 216);
		var i = 0;
		var subNum = 0;
		while (dctAmount.charAt(i) == '0') {
			i++;
		}
		return dctAmount.substring(i, dctAmount.length);
	};
	/* 款台号,8 */
	this.getSzDesktopNo = function() {
		return respMess.substring(216, 224);
	};
	/* 收款员号,9 */
	this.getSzOperator = function() {
		return respMess.substring(224, 233);
	};
	/* 交易方式,1 */
	this.getSzTranFlag = function() {
		return respMess.substring(233, 234);
	};
	/* 消耗积分对应的金额,12 */
	this.getSzPoint = function() {
		var szPoint = respMess.substring(234, 246);
		var i = 0;
		var subNum = 0;
		while (szPoint.charAt(i) == '0') {
			i++;
		}
		return szPoint.substring(i, szPoint.length);
	};
	/* 辅助信息,48 */
	this.getSzAppend1 = function() {
		return respMess.substring(246, 294);
	};
	/* 辅助信息,48 */
	this.getSzAppend2 = function() {
		return respMess.substring(294, 342);
	};
	/* 辅助信息,48 */
	this.getSzAppend3 = function() {
		return respMess.substring(342, 390);
	};
}