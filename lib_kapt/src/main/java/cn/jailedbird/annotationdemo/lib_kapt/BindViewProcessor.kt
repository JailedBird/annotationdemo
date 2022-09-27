@file:Suppress("UNUSED_VARIABLE")

package cn.jailedbird.annotationdemo.lib_kapt

import cn.jailedbird.annotationdemo.lib_annotation.BindView
import cn.jailedbird.demo.lib_compiler.apt.BaseProcessor
import cn.jailedbird.annotationdemo.lib_kapt.utils.Consts
import cn.jailedbird.annotationdemo.lib_kapt.utils.Consts.ACTIVITY
import cn.jailedbird.annotationdemo.lib_kapt.utils.Consts.PACKAGE_OF_GENERATE_END_FIX
import cn.jailedbird.annotationdemo.lib_kapt.utils.Consts.PACKAGE_OF_GENERATE_FILE
import com.google.auto.service.AutoService
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeSpec
import javax.annotation.processing.*
import javax.lang.model.element.ElementKind
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement

@AutoService(Processor::class)
class BindViewProcessor : BaseProcessor() {

    @Suppress("LocalVariableName")
    override fun process(
        set: MutableSet<out TypeElement>?, roundEnvironment: RoundEnvironment?
    ): Boolean {
        /* Avoid JavaFile write many times*/
        if (set.isNullOrEmpty() || roundEnvironment == null) {
            return false
        }
        logger.info("start kapt")

        // logger.info(set.toString())
        // logger.info(roundEnvironment.toString())

        val type_Activity = elementUtils.getTypeElement(Consts.ACTIVITY).asType()
        val type_Fragment = elementUtils.getTypeElement(Consts.FRAGMENT).asType()

        // logger.info("this times is ${count++}")
        for (element in roundEnvironment.rootElements) {
            if ((types.isSubtype(element.asType(), type_Activity) ||
                        types.isSubtype(element.asType(), type_Fragment))
            ) {
                // logger.info("@BindView can only apply in Androidx Activity ot Fragment : now is $element")
                val packageStr = element.enclosingElement.toString()
                val classStr = element.simpleName.toString()
                val classGenName = ClassName.get(packageStr, classStr + PACKAGE_OF_GENERATE_END_FIX)
                val className = ClassName.get(packageStr, classStr)
                val methodConstructor =
                    MethodSpec.constructorBuilder().addModifiers(Modifier.PUBLIC)
                        .addParameter(className, "activity")

                var hasViewBindAnnotation = false
                for (encloseElement in element.enclosedElements) {
                    if (encloseElement.kind == ElementKind.FIELD) {
                        encloseElement.getAnnotation(BindView::class.java)?.let { bindView ->
                            hasViewBindAnnotation = true
                            methodConstructor.addStatement(
                                "activity.\$N = activity.findViewById(\$L)",
                                encloseElement.simpleName,
                                bindView.value
                            )
                        }
                    }
                }
                val classBuilt = TypeSpec.classBuilder(classGenName).addModifiers(Modifier.PUBLIC)
                    .addMethod(methodConstructor.build()).build()
                if (hasViewBindAnnotation) {
                    try {
                        JavaFile.builder(packageStr, classBuilt).build().writeTo(filer)
                    } catch (e: java.lang.Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
        return false
    }

    private fun parseActivityName(simpleActivityName: String? = "MainActivity") {
        if (simpleActivityName.isNullOrBlank()) {
            return
        }
        val packageName = PACKAGE_OF_GENERATE_FILE
        val endFix = PACKAGE_OF_GENERATE_END_FIX
        val className: ClassName = ClassName.get(packageName, simpleActivityName + endFix)
        // We need build this class after we fill it!
        val buildClass = TypeSpec.classBuilder(className)
        // Add modify
        buildClass.addModifiers(Modifier.PUBLIC)
        val activityClass = ClassName.get(Class.forName(ACTIVITY))


        val methodConstructor = MethodSpec.constructorBuilder().addModifiers(Modifier.PUBLIC)
            .addParameter(activityClass, "activity")
            .addStatement("((MainActivity) activity).setTextView(activity.findViewById(R.id.textView));") // TODO 通过源码注解获取 textView变量名和R.id.xxx注解值
            .build()

        buildClass.addMethod(methodConstructor)

        try {
            JavaFile.builder(packageName, buildClass.build()).build().writeTo(filer)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }

    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(BindView::class.java.canonicalName)
    }

}