package cn.jailedbird.demo.lib_compiler.apt

import cn.jailedbird.annotationdemo.lib_kapt.utils.Logger
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Filer
import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.util.Elements
import javax.lang.model.util.Types

/**
 * Base Processor
 *
 * @author zhilong [Contact me.](mailto:zhilong.lzl@alibaba-inc.com)
 * @version 1.0
 * @since 2019-03-01 12:31
 */
abstract class BaseProcessor : AbstractProcessor() {
    lateinit var filer: Filer
    lateinit var logger: Logger
    lateinit var types: Types
    lateinit var elementUtils: Elements

    @Synchronized
    override fun init(processingEnv: ProcessingEnvironment) {
        super.init(processingEnv)
        filer = processingEnv.filer
        types = processingEnv.typeUtils
        elementUtils = processingEnv.elementUtils
        logger = Logger(processingEnv.messager)

    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }
}