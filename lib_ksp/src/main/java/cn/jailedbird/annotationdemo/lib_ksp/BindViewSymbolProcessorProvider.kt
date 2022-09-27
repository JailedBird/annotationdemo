package cn.jailedbird.annotationdemo.lib_ksp

import cn.jailedbird.annotationdemo.lib_annotation.BindView
import cn.jailedbird.annotationdemo.lib_ksp.BindViewSymbolProcessorProvider.Companion.PROCESS
import com.google.auto.service.AutoService
import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.*
import com.google.devtools.ksp.validate
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.toTypeName
import com.squareup.kotlinpoet.ksp.writeTo

@AutoService(SymbolProcessorProvider::class)
class BindViewSymbolProcessorProvider : SymbolProcessorProvider {
    companion object {
        val PROCESS: String = BindView::class.qualifiedName!!
    }

    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return BindViewSymbolProcessor(environment.logger, environment.codeGenerator)
    }
}

// KSP https://kotlinlang.org/docs/images/ksp-class-diagram.svg
class BindViewSymbolProcessor(
    private val logger: KSPLogger,
    private val codeGenerator: CodeGenerator
) : SymbolProcessor {

    /* Now I can not find a good log print method, logging.warn will print log,
     @see this https://github.com/google/ksp/issues/1111 */

    // About ksp debug https://github.com/google/ksp/issues/31

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = resolver.getSymbolsWithAnnotation(PROCESS)
        // return can not be resolve elements in this turn
        val ret = symbols.filter { !it.validate() }.toList()

        // Filter first
        @Suppress("UNCHECKED_CAST")
        val bindViewElement: List<KSPropertyDeclaration> =
            symbols.filter { it.validate() && it is KSPropertyDeclaration && it.parent is KSClassDeclaration }
                .toList() as List<KSPropertyDeclaration>

        CodeGen.gen(bindViewElement, codeGenerator, logger)
        return ret
    }

    object CodeGen {
        fun gen(
            elements: List<KSPropertyDeclaration>,
            codeGenerator: CodeGenerator,
            logger: KSPLogger
        ) {
            // Group by class those @BindView belongs, Notice nest class condition
            val map = elements.groupBy {
                val parent = it.parent as KSClassDeclaration
                /*val key = "${parent.packageName.asString()}.${parent.toClassName().simpleName}"*/
                val key = parent.qualifiedName?.asString() ?: ""
                key
            }
            logger.warn(map.toString())
            for (item in map) {
                if (item.key.isEmpty() || item.value.isEmpty()) {
                    continue
                }
                val classItem = (item.value[0].parent as KSClassDeclaration).toClassName()
                // TODO check classItem is the subclass Activity
                val packageName = classItem.packageName
                val fileSimpleName = classItem.simpleNames.joinToString("$") + "\$ViewBinding"

                val fileSpecBuilder = FileSpec.builder(
                    packageName,
                    fileSimpleName
                )
                // likes: Class MainActivity$ViewBinding
                val typeSpec = TypeSpec.classBuilder(fileSimpleName).addModifiers(KModifier.PUBLIC)
                val activityParamName = "activity"
                // constructor init(activity:MainActivity)
                val functionBuilder = FunSpec.constructorBuilder()
                    .addParameter(activityParamName, ClassName(packageName, classItem.simpleNames))

                // @BindView val textView TextView
                for (i in item.value) {
                    // textView
                    val symbolName = i.simpleName.asString()
                    // TODO select @BindView by firstOrNull, perhaps can optimize
                    val annotationValue = (i.annotations.firstOrNull {
                        it.annotationType.toTypeName().toString() == PROCESS
                    }?.arguments?.firstOrNull()?.value as? Int) ?: 0

                    if (annotationValue == 0) {
                        logger.error("@BindView's value need assign a not-zer0 value")
                    }
                    // activity.textView = activity.findViewById(xxx)
                    functionBuilder.addStatement("${activityParamName}.$symbolName = ${activityParamName}.findViewById(${annotationValue})")
                }

                typeSpec.addFunction(functionBuilder.build())

                fileSpecBuilder.addType(typeSpec.build())
                    .build()
                    .writeTo(codeGenerator, false)
            }
        }
    }
}
