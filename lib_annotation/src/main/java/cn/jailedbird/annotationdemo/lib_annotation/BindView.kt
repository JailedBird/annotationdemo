package cn.jailedbird.annotationdemo.lib_annotation

import androidx.annotation.IdRes
import androidx.annotation.Keep

@Keep
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FIELD)
annotation class BindView(@IdRes val value: Int = 0)
