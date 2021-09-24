

package io.github.vino42.seckill.support;

import org.slf4j.event.Level;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Optional;

/**
 * =====================================================================================
 *
 * @Created :2020/9/28 13:11
 * @Compiler : jdk 11
 * @Author : VINO
 * @Description : 响应码定义 首位为HTTPSTATUS首位, 前三位为http status 后三位为自定义异常编码 400013 对应 400 BAD_REQUEST 13为此大类小码
 *
 *
 * <p>=====================================================================================
 */
public enum ServiceResponseCodeEnum {
    /**
     * 成功
     */
    SUCCESS(0, "ok", "成功", Level.INFO, HttpStatus.OK),
    /**
     * 系统内部异常
     */
    SYSTEM_INTERNAL_ERROR(500000, "System internal error", "服务开小差啦", Level.ERROR, HttpStatus.INTERNAL_SERVER_ERROR),

    /**
     * 未知异常
     */
    UNKNOWN(500001, "Unknown error.", "未知错误", Level.ERROR, HttpStatus.INTERNAL_SERVER_ERROR),
    /**
     * RPC 未知错误
     */
    RPC_UNKNOWN(500002, "RPC error with none error code or msg.", "服务通信 未知错误", Level.ERROR,
            HttpStatus.INTERNAL_SERVER_ERROR),
    /**
     * RPC 通信异常
     */
    RPC_ERROR(500003, "RPC error with error code '%s'.", "服务通信异常", Level.ERROR, HttpStatus.INTERNAL_SERVER_ERROR),
    /**
     * 持久化异常
     */
    PERSISTENCE_TO_DB_FAIL(500004, "Persistent fail!", "数据持久化异常", Level.ERROR, HttpStatus.INTERNAL_SERVER_ERROR),
    /**
     * 中间件异常
     */
    MID_WARE_CONNECT_FAIL(500005, "Connect ", "中间件异常", Level.ERROR, HttpStatus.INTERNAL_SERVER_ERROR),
    /**
     * 需要授权
     */
    AUTH_401_NEED_AUTH(401001, "Need Authentication.", "登录过期,请重新登录", Level.INFO, HttpStatus.UNAUTHORIZED),
    /**
     * 授权失败
     */
    AUTH_401_UNAUTHORIZED(401002, "Authentication failed.", "授权失败,请重新登录", Level.INFO, HttpStatus.UNAUTHORIZED),
    /**
     * token过期失效 需要授权
     */
    AUTH_401_EXPIRED(
            401003, "Certification expired. Re-auth please.", "登录过期,请重新登录", Level.INFO, HttpStatus.UNAUTHORIZED),
    /**
     * 签名错误
     */
    SIGN_INVALID(401004, "Security session invalid.", "签名错误", Level.INFO, HttpStatus.UNAUTHORIZED),
    /**
     * SECURITY_SESSION_INVALID context 传递 session失效
     */
    SECURITY_SESSION_INVALID(
            401005, "Security session invalid.", "请登录", Level.INFO, HttpStatus.UNAUTHORIZED),
    /**
     *
     */
    AUTH_401_BAD_CLIENT_CREDENTIALS(
            401006, "invalid_client. Re-auth please.", "请登录", Level.ERROR, HttpStatus.UNAUTHORIZED),
    /**
     * INVALID_GRANT
     */
    AUTH_401_INVALID_GRANT(
            401007, "invalid_grant. Re-auth please.", "非法授权方式,请登录", Level.ERROR, HttpStatus.UNAUTHORIZED),
    /**
     * UNAUTHORIZED_CLIENT
     */
    AUTH_401_UNAUTHORIZED_CLIENT(
            401008, "unauthorized_client.", "请登录", Level.ERROR, HttpStatus.UNAUTHORIZED),
    /**
     * 无效SCOPE
     */
    AUTH_401_INVALID_SCOPE(401009, "invalid_scope.", "请登录", Level.ERROR, HttpStatus.UNAUTHORIZED),
    /**
     * unsupported_grant_type
     */
    AUTH_401_UN_SUPPORTEDGRANT_TYPE(
            401010, "unsupported_grant_type.", "请登录", Level.ERROR, HttpStatus.UNAUTHORIZED),
    /**
     * unauthorized_user
     */
    AUTH_401_UNAUTHORIZED_USER(401012, "unauthorized_user.", "未授权,请登录", Level.ERROR, HttpStatus.UNAUTHORIZED),

    /**
     * token 无效
     */
    AUTH_401_TOKEN_INVALID(401013, "Invalid token.", "请登录", Level.INFO, HttpStatus.FORBIDDEN),

    /**
     * token 无效
     */
    AUTH_401_UNSUPPORT_AUTH_TYPE(401014, "Unsupport auth type.", "请登录", Level.INFO, HttpStatus.FORBIDDEN),
    /**
     * 服务响应超时
     */
    SERVICE_RESPONSE_TIMEOUT(408000, "Service response timeout.", "服务响应超时", HttpStatus.REQUEST_TIMEOUT),
    /**
     * 没有权限
     */
    AUTH_403_FORBIDDEN(403000, "Permission deny.", "没有权限", Level.INFO, HttpStatus.FORBIDDEN),


    /**
     * insufficient_scope
     */
    AUTH_403_INSUFFICIENT_SCOPE(403002, "insufficient_scope", "无效的域", Level.INFO, HttpStatus.FORBIDDEN),
    /**
     * app患者端登录后台管理 非租户用户登录租户后台管理
     */
    NO_AUTHORITY_TO_LOGIN(403003, "no authority to login", "无权登录", Level.INFO, HttpStatus.OK),

    ILLEGAL_ACCESS(403004, "ILLEGAL ACCESS", "非法访问"),

    /**
     * 404
     */
    NOT_FOUND_404(404000, "NOT FOUND", "服务器未找到", Level.INFO, HttpStatus.NOT_FOUND),

    /**
     * contenttype 不支持
     */
    CONTENT_TYPE_INVALID(
            415000,
            "HttpMediaTypeNotSupported. ContentType(%s) is not acceptable.",
            "报文类型不支持",
            Level.INFO,
            HttpStatus.BAD_REQUEST),
    /**
     * 不支持的请求体
     */
    REQUEST_BODY_INCORRECT(
            422000, "Entity format not supported。", "请求体类型不支持", Level.ERROR, HttpStatus.UNPROCESSABLE_ENTITY),
    /**
     * 请求超时
     */
    REQUEST_TIMEOUT(408001, "Request timeout.", "请求超时", Level.ERROR, HttpStatus.REQUEST_TIMEOUT),

    ILLEGAL_ARGUMENT_ERROR(400000, "Illegal argument", "参数错误", Level.ERROR, HttpStatus.BAD_REQUEST),
    /**
     * 参数校验不通过
     */
    PARAM_NOT_VALID(400004, "Parameter not valid. for %s", "参数校验不通过", Level.INFO, HttpStatus.BAD_REQUEST),
    /**
     * 参数校验不通过 为空
     */
    PARAM_BLANK(400005, "The required parameter %s is blank.", "参数校验不通过,参数存在空参数", Level.INFO, HttpStatus.BAD_REQUEST),
    /**
     * 参数校验 超界限
     */
    PARAM_OUT_RANGE(
            400006,
            "The value of parameter %s is not in the right range.",
            "参数校验不通过,超界限",
            Level.INFO,
            HttpStatus.BAD_REQUEST),
    /**
     * 表单异常
     */
    PARAM_FORMAT_INVALID(
            400007, "The format of parameter %s is not correct.", "表单提交异常", Level.INFO, HttpStatus.BAD_REQUEST),
    /**
     * 分页请求数据过大
     */
    PARAM_PAGE_SETTING_INVALID(
            400008, "Return message is too long, please resize page and retry.", "分页请求数据过大", Level.INFO,
            HttpStatus.BAD_REQUEST),
    /**
     * 请求方法不支持
     */
    METHOD_NOT_ALLOWED(405000, "Method Not Allowed", "请求方法不支持", Level.INFO, HttpStatus.METHOD_NOT_ALLOWED),
    /**
     * 参数不支持
     */
    PARAM_NOT_SUPPORT(400009, "The parameter(%s) not supported.", "参数不支持", Level.INFO, HttpStatus.BAD_REQUEST),
    /**
     * 请求内容过长
     */
    PARAM_CONTENT_TOO_LONG(
            400010, "The parameter(%s) content is out of limit.", "请求内容过长", Level.INFO, HttpStatus.BAD_REQUEST),
    /**
     * 请求不可读
     */
    PARAM_BODY_NOT_READABLE(400011, "HttpMessageNotReadable", "请求不可读", Level.INFO, HttpStatus.BAD_REQUEST),
    /**
     * 参数类型不匹配
     */
    PARAM_TYPE_NOT_MATCH(
            400012,
            "MethodArgumentTypeMismatch. The value of %s(%s) resolved to %s fail.",
            "参数类型不匹配",
            Level.INFO,
            HttpStatus.BAD_REQUEST),
    /**
     * 非multipart request
     */
    MULTIPART_INVALID(
            400013,
            "Request is not a validate multipart request, please check request or file size.",
            "非multipart 请求",
            Level.INFO,
            HttpStatus.BAD_REQUEST),
    /**
     * 无效的账号或密码
     */
    ACCOUNT_OR_PWD_INVALID(
            400014, "invalid account or password .", "无效的账号或密码", Level.ERROR, HttpStatus.BAD_REQUEST),
    /**
     * 无效的短信验证码
     */
    SMS_CODE_INVALID(400015, "invalid sms code .", "无效短信验证码", Level.ERROR, HttpStatus.BAD_REQUEST),
    /**
     * 无效的图形验证码
     */
    CAPTCHA_CODE_INVALID(400016, "invalid captcha code .", "无效的图形验证码", Level.ERROR, HttpStatus.BAD_REQUEST),

    /**
     * 限流
     */
    REQUEST_RAIT_LIMIT(429000, "Too Many Requests", "请稍后重试", Level.INFO, HttpStatus.TOO_MANY_REQUESTS),
    /**
     * 重复提交
     */
    DUMPLICATE_REQUEST_LIMIT(429001, "Dumplicate requests", "重复提交,请稍后重试", Level.INFO, HttpStatus.TOO_MANY_REQUESTS),
    /**
     * 获取锁失败,访问的资源暂时被锁定
     */
    GET_RESOURCE_LOCK_FAILED_429002(429002, "Request  get resource lock failed may be locking, plz try later ", "请稍后重试", Level.ERROR, HttpStatus.TOO_MANY_REQUESTS),

    /**
     * Upgrade Required app版本过低 需要升级
     */
    UPGRADE_REQUIRED(426000, "Upgrade Required", "app版本过低 需要升级"),
    /**
     * Upgrade Required app版本过低 需要升级
     */
    GATEWAY_ERROR(502000, "BAD GATEWAY", "网关异常"),
    /**
     * 服务暂时不可用
     */
    SERVICE_UNAVALIABLE_ERROR(
            503000, "Service Unavailable Just a moment", "服务暂时不可用,请稍后重试", HttpStatus.SERVICE_UNAVAILABLE),
    /**
     * 服务降级
     */
    DEGRADE_ERROR(
            503001, "Service Unavailable Just a moment", "服务暂时不可用,请稍后重试", HttpStatus.SERVICE_UNAVAILABLE),
    /**
     * 网关超时
     */
    GATEWAY_CONNECT_TIME_OUT(504000, "GATEWAY CONNECT TIME OUT", "网关超时"),

    ILLEGAL_CAPCHA(600001, "illegal capcha", "验证码错误"),

    ACTIVITY_ALREADY_END(600002, "ACTIVITY ALREADY END", "活动已结束"),
    ACTIVITY_NOT_BEGIN(600003, "ACTIVITY ALREADY END", "活动尚未开始"),
    NO_GOODS_STORE(600004, "NO STORE", "库存不足"),
    ACTIVITY_ALREADY_BUYED_OVER_LIMIT(600005, "SEC KILL OVER LIMIT ", "已超出当前活动允许每人秒杀的数量"),
    SKU_SEC_KILL_OVER_LIMIT(600006, "ALREADY BUYED OVER LIMIT", "已超出当前活动每件商品允许每人秒杀的数量"),
    ERROR_SKU_GODDS_SET(600007, "ERROR SKU SET", "商品设置错误");

    public int status;
    public String message;
    public String messageEn;
    public Level logLevel;
    public HttpStatus httpStatus;

    ServiceResponseCodeEnum(int status, String messageEn, Level logLevel, HttpStatus httpStatus) {
        this.status = status;
        this.messageEn = messageEn;
        this.logLevel = logLevel;
        this.httpStatus = httpStatus;
    }

    ServiceResponseCodeEnum(int status, String messageEn, String message, HttpStatus httpStatus) {
        this.status = status;
        this.message = message;
        this.messageEn = messageEn;
        this.httpStatus = httpStatus;
    }

    ServiceResponseCodeEnum(int status, String messageEn, String message, Level logLevel, HttpStatus httpStatus) {
        this.status = status;
        this.message = message;
        this.messageEn = messageEn;
        this.httpStatus = httpStatus;
        this.logLevel = logLevel;
    }

    ServiceResponseCodeEnum(int status, String messageEn, String message) {
        this.status = status;
        this.message = message;
        this.messageEn = messageEn;
    }

    ServiceResponseCodeEnum(int status, String messageEn, Level logLevel) {
        this.status = status;
        this.messageEn = messageEn;
        this.logLevel = logLevel;
    }

    ServiceResponseCodeEnum(int status, String messageEn) {
        this.status = status;
        this.messageEn = messageEn;
    }

    ServiceResponseCodeEnum(int status, String messageEn, HttpStatus httpStatus) {
        this.status = status;
        this.messageEn = messageEn;
        this.httpStatus = httpStatus;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageEn() {
        return messageEn;
    }

    public Level getLogLevel() {
        return logLevel;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public static ServiceResponseCodeEnum getByCode(int code) {
        Optional<ServiceResponseCodeEnum> first = Arrays.asList(ServiceResponseCodeEnum.values()).stream().filter(d -> d.getStatus() == code).findFirst();
        return first.orElse(null);
    }
}
