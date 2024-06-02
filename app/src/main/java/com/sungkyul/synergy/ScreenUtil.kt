import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext

object ScreenUtil {
    var screenWidth = 0f
        private set
    var screenHeight = 0f
        private set

    @Composable
    fun initialize() {
        val configuration = LocalConfiguration.current
        val density = LocalContext.current.resources.displayMetrics.density
        screenWidth = configuration.screenWidthDp * density
        screenHeight = configuration.screenHeightDp * density
    }

    fun setWidth(value: Float): Float = value * (screenWidth / 375)
    fun setHeight(value: Float): Float = value * (screenHeight / 812)
}
