package cn.jailedbird.annotationdemo.lib_kapt.utils

import org.apache.commons.lang3.StringUtils
import java.lang.StringBuilder
import javax.annotation.processing.Messager
import javax.tools.Diagnostic

/**
 * Simplify the message print.
 *
 * @author Alex [Contact me.](mailto:zhilong.liu@aliyun.com)
 * @version 1.0
 * @since 16/8/22 上午11:48
 */
class Logger(private val msg: Messager) {
    /**
     * Print info log.
     */
    fun info(info: CharSequence) {
        if (StringUtils.isNotEmpty(info)) {
            msg.printMessage(Diagnostic.Kind.NOTE, Consts.PREFIX_OF_LOGGER + info)
        }
    }

    fun error(error: CharSequence) {
        if (StringUtils.isNotEmpty(error)) {
            msg.printMessage(
                Diagnostic.Kind.ERROR,
                Consts.PREFIX_OF_LOGGER + "An exception is encountered, [" + error + "]"
            )
        }
    }

    fun error(error: Throwable?) {
        if (null != error) {
            msg.printMessage(
                Diagnostic.Kind.ERROR, """
     ${Consts.PREFIX_OF_LOGGER}An exception is encountered, [${error.message}]
     ${formatStackTrace(error.stackTrace)}
     """.trimIndent()
            )
        }
    }

    fun warning(warning: CharSequence) {
        if (StringUtils.isNotEmpty(warning)) {
            msg.printMessage(Diagnostic.Kind.WARNING, Consts.PREFIX_OF_LOGGER + warning)
        }
    }

    private fun formatStackTrace(stackTrace: Array<StackTraceElement>): String {
        val sb = StringBuilder()
        for (element in stackTrace) {
            sb.append("    at ").append(element.toString())
            sb.append("\n")
        }
        return sb.toString()
    }
}