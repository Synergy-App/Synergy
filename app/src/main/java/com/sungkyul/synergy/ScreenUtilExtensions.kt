import kotlin.math.max
import kotlin.math.min

val Float.w: Float
    get() = ScreenUtil.setWidth(this)

val Float.h: Float
    get() = ScreenUtil.setHeight(this)

val Float.spMin: Float
    get() = min(this, this.sp)

val Float.spMax: Float
    get() = max(this, this.sp)

val Float.sp: Float
    get() = (this * ScreenUtil.screenWidth / 375)

val Float.sw: Float
    get() = ScreenUtil.screenWidth * this

val Float.sh: Float
    get() = ScreenUtil.screenHeight * this
