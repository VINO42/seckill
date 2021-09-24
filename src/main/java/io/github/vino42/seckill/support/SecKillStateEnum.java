package io.github.vino42.seckill.support;

/**
 * =====================================================================================
 *
 * @Created :   2021/9/24 22:19
 * @Compiler :  jdk 11
 * @Author :    VINO
 * @Copyright : VINO
 * @Decription : 秒杀状态枚举
 * =====================================================================================
 */
public enum SecKillStateEnum {
    SUCCESS(1, "秒杀成功"), END(0, "秒杀结束"), REPEAT_KILL(-1, "重发秒杀"), INNER_ERROR(-2, "系统异常"), DATA_REWRITE(-3, "数据篡改");
    private int state;

    private String stateInfo;

    SecKillStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static SecKillStateEnum stateOf(int index) {
        for (SecKillStateEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }

}
