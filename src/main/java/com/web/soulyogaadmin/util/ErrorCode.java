package com.web.soulyogaadmin.util;

/**
 * Error code list refered by restful WebService(mobile) . 
 */
public interface ErrorCode {
	String Success = "0", SuccessMsg = "success";
	String Error = "001", ErrorMsg = "系统故障, 请联系客服";
	String SessionInvalid = "002", SessionInvalidMsg = "token失效";
	String ParamIllegal = "003", ParamIllegalMsg = "请求参数有误";
	String ClientError = "004", ClientErrorMsg = "客户端错误";
	String ServerError = "005", ServerErrorMsg = "服务端错误";
	String SessionExpire = "110", SessionExpireMsg = "会话过期";
	String SuggestException = "200", SuggestExceptionMsg = "操作不被全部接受";
	
	String UNBIND001="手机号码未找到，请重新输入";
	
}


