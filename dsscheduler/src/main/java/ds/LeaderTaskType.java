package ds;

import java.lang.annotation.*;

/**
 * Created by super13 on 8/18/17.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface LeaderTaskType {
    /**
     * type 有两种,平行执行和leader执行
     * @return
     */
    LeaderTaskTypeEnum type() default LeaderTaskTypeEnum.PARALLEL;

    /**
     * 指定机子执行，无视type最高优先级
     * @return
     */
    String specificHost() default  "";
}
