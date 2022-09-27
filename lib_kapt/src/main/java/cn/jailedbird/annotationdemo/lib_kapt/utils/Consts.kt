package cn.jailedbird.annotationdemo.lib_kapt.utils

/**
 * Some consts used in processors
 *
 * @author Alex [Contact me.](mailto:zhilong.liu@aliyun.com)
 * @version 1.0
 * @since 16/8/24 20:18
 */
object Consts {
    // Generate
    const val SEPARATOR = "$$"
    const val PROJECT = "DemoAnnotation"
    const val TAG = PROJECT + "::"
    const val WARNING_TIPS = "DO NOT EDIT THIS FILE!!! IT WAS GENERATED BY AROUTER."
    const val METHOD_LOAD_INTO = "loadInto"
    const val METHOD_INJECT = "inject"
    const val NAME_OF_ROOT = PROJECT + SEPARATOR + "Root"
    const val NAME_OF_PROVIDER = PROJECT + SEPARATOR + "Providers"
    const val NAME_OF_GROUP = PROJECT + SEPARATOR + "Group" + SEPARATOR
    const val NAME_OF_INTERCEPTOR = PROJECT + SEPARATOR + "Interceptors"
    const val NAME_OF_AUTOWIRED = SEPARATOR + PROJECT + SEPARATOR + "Autowired"
    const val PACKAGE_OF_GENERATE_FILE = "cn.jailedbird.annotationdemo"
    const val PACKAGE_OF_GENERATE_END_FIX = "\$ViewBinding"
    const val PACKAGE_OF_GENERATE_DOCS = "com.alibaba.android.arouter.docs"

    // System interface

    const val ACTIVITY = "androidx.appcompat.app.AppCompatActivity"
    const val FRAGMENT = "androidx.fragment.app.Fragment"
    const val PARCELABLE = "android.os.Parcelable"

    // Java type
    private const val LANG = "java.lang"
    const val BYTE = LANG + ".Byte"
    const val SHORT = LANG + ".Short"
    const val INTEGER = LANG + ".Integer"
    const val LONG = LANG + ".Long"
    const val FLOAT = LANG + ".Float"
    const val DOUBEL = LANG + ".Double"
    const val BOOLEAN = LANG + ".Boolean"
    const val CHAR = LANG + ".Character"
    const val STRING = LANG + ".String"
    const val SERIALIZABLE = "java.io.Serializable"

    // Custom interface
    private const val FACADE_PACKAGE = "com.alibaba.android.arouter.facade"
    private const val TEMPLATE_PACKAGE = ".template"
    private const val SERVICE_PACKAGE = ".service"
    private const val MODEL_PACKAGE = ".model"
    const val IPROVIDER = FACADE_PACKAGE + TEMPLATE_PACKAGE + ".IProvider"
    const val IPROVIDER_GROUP = FACADE_PACKAGE + TEMPLATE_PACKAGE + ".IProviderGroup"
    const val IINTERCEPTOR = FACADE_PACKAGE + TEMPLATE_PACKAGE + ".IInterceptor"
    const val IINTERCEPTOR_GROUP = FACADE_PACKAGE + TEMPLATE_PACKAGE + ".IInterceptorGroup"
    const val ITROUTE_ROOT = FACADE_PACKAGE + TEMPLATE_PACKAGE + ".IRouteRoot"
    const val IROUTE_GROUP = FACADE_PACKAGE + TEMPLATE_PACKAGE + ".IRouteGroup"
    const val ISYRINGE = FACADE_PACKAGE + TEMPLATE_PACKAGE + ".ISyringe"
    const val JSON_SERVICE = FACADE_PACKAGE + SERVICE_PACKAGE + ".SerializationService"
    const val TYPE_WRAPPER = FACADE_PACKAGE + MODEL_PACKAGE + ".TypeWrapper"

    // Log
    const val PREFIX_OF_LOGGER = PROJECT + "::Compiler "
    const val NO_MODULE_NAME_TIPS = "These no module name, at 'build.gradle', like :\n" +
            "android {\n" +
            "    defaultConfig {\n" +
            "        ...\n" +
            "        javaCompileOptions {\n" +
            "            annotationProcessorOptions {\n" +
            "                arguments = [AROUTER_MODULE_NAME: project.getName()]\n" +
            "            }\n" +
            "        }\n" +
            "    }\n" +
            "}\n"

    // Options of processor
    const val KEY_MODULE_NAME = "AROUTER_MODULE_NAME"
    const val KEY_GENERATE_DOC_NAME = "AROUTER_GENERATE_DOC"
    const val VALUE_ENABLE = "enable"

    // Annotation type
    const val ANNOTATION_TYPE_INTECEPTOR = FACADE_PACKAGE + ".annotation.Interceptor"
    const val ANNOTATION_TYPE_ROUTE = FACADE_PACKAGE + ".annotation.Route"
    const val ANNOTATION_TYPE_AUTOWIRED = FACADE_PACKAGE + ".annotation.Autowired"
}