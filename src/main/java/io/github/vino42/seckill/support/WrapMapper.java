

package io.github.vino42.seckill.support;


import org.apache.commons.lang3.StringUtils;

import static io.github.vino42.seckill.support.ServiceResponseCodeEnum.*;

/** The class Wrap mapper. */
public final class WrapMapper {
    
    /** Instantiates a new wrap mapper. */
    private WrapMapper() {
        // no arg constructor
    }
    
    /**
     * Wrap.
     *
     * @param <E> the element type
     * @param status the status
     * @param message the message
     * @param o the o
     * @return the wrapper
     */
    public static <E> ServiceResultWrapper<E> wrap(int status, String message, E o) {
        
        return wrap(status, message, o, "");
    }
    
    /**
     * Wrap.
     *
     * @param <E> the element type
     * @param status the status
     * @param message the message
     * @return the wrapper
     */
    public static <E> ServiceResultWrapper<E> wrap(int status, String message, String attache) {
        
        return wrap(status, message, null, attache);
    }
    
    /**
     * Wrap.
     *
     * @param attache attache msg
     * @param <E> the element type
     * @param status the status
     * @param message the message
     * @param o the o
     * @return the wrapper
     */
    public static <E> ServiceResultWrapper<E> wrap(int status, String message, E o, String attache) {
        
        return new ServiceResultWrapper<>(status, message, o, attache);
    }
    
    /**
     * Wrap.
     *
     * @param <E> the element type
     * @param status the status
     * @param message the message
     * @return the wrapper
     */
    public static <E> ServiceResultWrapper<E> wrap(int status, String message) {
        
        return wrap(status, message, null, "");
    }
    
    /**
     * Wrap.
     *
     * @param <E> the element type
     * @param status the status
     * @return the wrapper
     */
    public static <E> ServiceResultWrapper<E> wrap(int status) {
        
        return wrap(status, null);
    }
    
    /**
     * Wrap.
     *
     * @param <E> the element type
     * @param e the e
     * @return the wrapper
     */
    public static <E> ServiceResultWrapper<E> wrap(Exception e) {
        
        return new ServiceResultWrapper<>(SYSTEM_INTERNAL_ERROR.getStatus(), e.getMessage());
    }
    
    /**
     * Un wrapper.
     *
     * @param <E> the element type
     * @param wrapper the wrapper
     * @return the e
     */
    public static <E> E unWrap(ServiceResultWrapper<E> wrapper) {
        
        return wrapper.getData();
    }
    
    /**
     * Wrap ERROR. status=100
     *
     * @param <E> the element type
     * @return the wrapper
     */
    public static <E> ServiceResultWrapper<E> illegalArgument() {
        
        return wrap(ILLEGAL_ARGUMENT_ERROR.status, ILLEGAL_ARGUMENT_ERROR.message);
    }
    /**
     * Wrap ERROR. status=401001
     *
     * @param <E> the element type
     * @return the wrapper
     */
    public static <E> ServiceResultWrapper<E> pleaseLogin() {
        
        return wrap(AUTH_401_NEED_AUTH.status, AUTH_401_NEED_AUTH.message);
    }
    /**
     * Wrap ERROR. status=100
     *
     * @param <E> the element type
     * @return the wrapper
     */
    public static <E> ServiceResultWrapper<E> illegalArgument(String msg) {
        
        return wrap(ILLEGAL_ARGUMENT_ERROR.status, msg);
    }
    
    /**
     * Wrap ERROR. status=500
     *
     * @param <E> the element type
     * @return the wrapper
     */
    public static <E> ServiceResultWrapper<E> error() {
        
        return wrap(SYSTEM_INTERNAL_ERROR.getStatus(), SYSTEM_INTERNAL_ERROR.getMessage());
    }
    
    /**
     * Wrap ERROR. status=500
     *
     * @param <E> the element type
     * @return the wrapper
     */
    public static <E> ServiceResultWrapper<E> error(ServiceResponseCodeEnum responseEnum) {
        
        return wrap(responseEnum.status, responseEnum.message, responseEnum.messageEn);
    }
    
    /**
     * Error wrapper.
     *
     * @param <E> the type parameter
     * @param message the message
     * @return the wrapper
     */
    public static <E> ServiceResultWrapper<E> error(String message, String attache) {
        
        return wrap(SYSTEM_INTERNAL_ERROR.getStatus(), StringUtils.isBlank(message) ? SYSTEM_INTERNAL_ERROR.getMessage() : message, attache);
    }
    
    /**
     * Error wrapper.
     *
     * @param <E> the type parameter
     * @param message the message
     * @return the wrapper
     */
    public static <E> ServiceResultWrapper<E> error(String message) {
        
        return wrap(SYSTEM_INTERNAL_ERROR.getStatus(), StringUtils.isBlank(message) ? SYSTEM_INTERNAL_ERROR.getMessage() : message);
    }
    
    /**
     * Error wrapper.
     *
     * @param <E> the type parameter
     * @param message the message
     * @return the wrapper
     */
    public static <E> ServiceResultWrapper<E> error(int status, String message) {
        
        return wrap(status, StringUtils.isBlank(message) ? SYSTEM_INTERNAL_ERROR.getMessage() : message);
    }
    
    /**
     * Error wrapper.
     *
     * @param <E> the type parameter
     * @param message the message
     * @return the wrapper
     */
    public static <E> ServiceResultWrapper<E> error(int status, String message, String attache) {
        
        return wrap(status, StringUtils.isBlank(message) ? SYSTEM_INTERNAL_ERROR.getMessage() : message, null, attache);
    }
    
    /**
     * Error wrapper.
     *
     * @param <E> the type parameter
     * @param message the message
     * @return the wrapper
     */
    public static <E> ServiceResultWrapper<E> error(int status, String message, String attache, E o) {
        
        return wrap(status, StringUtils.isBlank(message) ? SYSTEM_INTERNAL_ERROR.getMessage() : message, o, attache);
    }
    
    /**
     * Wrap SUCCESS. status=200
     *
     * @param <E> the element type
     * @return the wrapper
     */
    public static <E> ServiceResultWrapper<E> ok() {
        
        return new ServiceResultWrapper(SUCCESS.status, "ok", "");
    }
    
    
    /**
     * Ok wrapper.
     *
     * @param <E> the type parameter
     * @param o the o
     * @return the wrapper
     */
    public static <E> ServiceResultWrapper<E> ok(E o, String msg, String attache) {
        
        return new ServiceResultWrapper<>(SUCCESS.status, msg, o, attache);
    }
    
    /**
     * Wrap SUCCESS. status=200
     *
     * @param <E> the element type
     * @return the wrapper
     */
    public static <E> ServiceResultWrapper<E> ok(E o, String msg) {
        
        return new ServiceResultWrapper(SUCCESS.status, msg, o);
    }
    
    
    /**
     * Ok wrapper.
     *
     * @param <E> the type parameter
     * @param o the o
     * @return the wrapper
     */
    public static <E> ServiceResultWrapper<E> ok(E o) {
        return new ServiceResultWrapper<>(SUCCESS.status, SUCCESS.message, o);
    }
    
    
}
