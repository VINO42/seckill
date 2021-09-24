

package io.github.vino42.seckill.support;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

import static cn.hutool.core.date.DatePattern.NORM_DATETIME_MS_PATTERN;
import static io.github.vino42.seckill.support.ServiceResponseCodeEnum.*;

/**
 * 服务统一包装类
 *
 * @param <T>
 */
@Slf4j
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ServiceResultWrapper<T> implements Serializable {

    private static final long serialVersionUID = -1893521199702700771L;
    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = NORM_DATETIME_MS_PATTERN)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = NORM_DATETIME_MS_PATTERN)
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = NORM_DATETIME_MS_PATTERN)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = NORM_DATETIME_MS_PATTERN)
    private LocalDateTime endTime;

    /**
     * 编号.
     */
    private int status;

    /**
     * 信息.
     */
    private String message;

    /**
     * 结果数据
     */
    private T data;

    /**
     * 附加信息 如 exception
     */
    private String attache;

    /**
     * traceId
     */
    private String requestId;

    public ServiceResultWrapper() {

        this.startTime = LocalDateTime.now();
    }

    /**
     * Instantiates a new wrapper.
     *
     * @param status  the code
     * @param message the message
     */
    public ServiceResultWrapper(int status, String message) {

        this(status, message, null);
    }

    /**
     * Instantiates a new wrapper.
     *
     * @param status  the code
     * @param message the message
     * @param result  the result
     */
    public ServiceResultWrapper(int status, String message, T result) {

        super();
        this.startTime = LocalDateTime.now();
        this.status(status).message(message).result(result);
    }

    /**
     * Instantiates a new wrapper.
     *
     * @param status  the code
     * @param message the message
     * @param result  the result
     */
    public ServiceResultWrapper(int status, String message, T result, String attache) {

        super();
        this.startTime = LocalDateTime.now();
        this.status(status).message(message).result(result);
        this.attache = attache;
    }

    /**
     * Sets the 编号 , 返回自身的引用.
     *
     * @param code the new 编号
     * @return the wrapper
     */
    private ServiceResultWrapper<T> status(int code) {

        this.setStatus(code);
        this.startTime = LocalDateTime.now();
        return this;
    }

    /**
     * Sets the 信息 , 返回自身的引用.
     *
     * @param message the new 信息
     * @return the wrapper
     */
    private ServiceResultWrapper<T> message(String message) {

        this.setMessage(message);
        this.startTime = LocalDateTime.now();
        return this;
    }

    /**
     * Sets the 结果数据 , 返回自身的引用.
     *
     * @param data the new 结果数据
     * @return the wrapper
     */
    public ServiceResultWrapper<T> result(T data) {

        this.startTime = LocalDateTime.now();
        this.setData(data);
        return this;
    }

    /**
     * 判断是否成功： 依据 Wrapper.SUCCESS_CODE == this.code
     *
     * @return code =200,true;否则 false.
     */
    @JsonIgnore
    public boolean success() {

        return SUCCESS.status == this.status;
    }

    /**
     * 判断是否成功： 依据 Wrapper.SUCCESS_CODE != this.code
     *
     * @return code !=200,true;否则 false.
     */
    @JsonIgnore
    public boolean error() {

        return !success();
    }

    /**
     * 判断是否成功： 依据 Wrapper.SUCCESS_CODE != this.code
     *
     * @return code !=0,true;否则 false.
     */
    @JsonIgnore
    public boolean isSuccessThrow() throws Exception {
        if (this.status != SUCCESS.status) {
            throw new Exception(RPC_ERROR.message);
        }
        return true;
    }

    /**
     * 判断是否成功： 依据 Wrapper.SUCCESS_CODE != this.code
     *
     * @return code !=0,true;否则 false.
     */
    @JsonIgnore
    public boolean isSuccess() {
        boolean isSuc = this.status == SUCCESS.status;
        if (!isSuc) {
            log.error("[feignClient remote call error:{}]", this);
        }
        return isSuc;
    }

    /**
     * 判断获取数据
     *
     * @return code !=0,true;否则 false.
     */
    @JsonIgnore
    public T getFeignData() throws Exception {
        if (this.status != SUCCESS.status) {
            log.error("[feignClient remote call error:{}]", this);
            throw new Exception(RPC_UNKNOWN.message);
        }
        return this.getData();
    }
}
