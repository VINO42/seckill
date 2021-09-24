package io.github.vino42.seckill.support;

import java.util.Arrays;
import java.util.Optional;

import static io.github.vino42.seckill.support.ServiceResponseCodeEnum.*;

/**
 * =====================================================================================
 *
 * @Created :   2021/9/24 22:54
 * @Compiler :  jdk 11
 * @Author :    VINO
 * @Email :
 * @Copyright : VINO
 * @Decription :
 * =====================================================================================
 */
public interface Constant {

    String SECKILL_PATH_KEY = "SECKILL_PATH:%s";

    String REDIS_CAPCHA_KEY = "CAPCHA_KEY:%s";

    String DEFAULT_CAPCHA_FLAG = "42";

    String LUA_SCRIPT_FILE_NAME = "seckill.lua";


    enum LUA_EXCUTE_RESULT {
        NO_STORE("0", "库存不足", NO_GOODS_STORE),
        TOTAL_ACTIVITY_BUYED_LIMITED_COUNT("-1", "已超出当前活动每件商品允许每人秒杀的数量", SKU_SEC_KILL_OVER_LIMIT),
        SKU_LIMITED_COUNT("-2", "已超出当前活动允许每人秒杀的数量", ACTIVITY_ALREADY_BUYED_OVER_LIMIT),
        ERROR_SKU_SET("-3", "商品设置有误", ERROR_SKU_GODDS_SET);

        public String code;

        public String desc;

        public ServiceResponseCodeEnum codeEnum;

        LUA_EXCUTE_RESULT(String code, String desc, ServiceResponseCodeEnum codeEnum) {
            this.code = code;
            this.desc = desc;
            this.codeEnum = codeEnum;
        }

        public static LUA_EXCUTE_RESULT getByCode(String code) {
            Optional<LUA_EXCUTE_RESULT> first = Arrays.stream(LUA_EXCUTE_RESULT.values()).filter(d -> d.code.equals(code)).findFirst();
            return first.isPresent() ? first.get() : null;
        }
    }


}
