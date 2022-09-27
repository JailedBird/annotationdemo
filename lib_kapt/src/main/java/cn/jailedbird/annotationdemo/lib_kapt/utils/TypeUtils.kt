package cn.jailedbird.annotationdemo.lib_kapt.utils

import cn.jailedbird.annotationdemo.lib_kapt.utils.Consts.BOOLEAN
import cn.jailedbird.annotationdemo.lib_kapt.utils.Consts.BYTE
import cn.jailedbird.annotationdemo.lib_kapt.utils.Consts.CHAR
import cn.jailedbird.annotationdemo.lib_kapt.utils.Consts.DOUBEL
import cn.jailedbird.annotationdemo.lib_kapt.utils.Consts.FLOAT
import cn.jailedbird.annotationdemo.lib_kapt.utils.Consts.INTEGER
import cn.jailedbird.annotationdemo.lib_kapt.utils.Consts.LONG
import cn.jailedbird.annotationdemo.lib_kapt.utils.Consts.PARCELABLE
import cn.jailedbird.annotationdemo.lib_kapt.utils.Consts.SERIALIZABLE
import cn.jailedbird.annotationdemo.lib_kapt.utils.Consts.SHORT
import cn.jailedbird.annotationdemo.lib_kapt.utils.Consts.STRING
import javax.lang.model.element.Element
import javax.lang.model.type.TypeMirror
import javax.lang.model.util.Elements
import javax.lang.model.util.Types

/**
 * Utils for type exchange
 *
 * @author zhilong [Contact me.](mailto:zhilong.lzl@alibaba-inc.com)
 * @version 1.0
 * @since 2017/2/21 下午1:06
 */
class TypeUtils(private val types: Types, elements: Elements) {
    private val parcelableType: TypeMirror
    private val serializableType: TypeMirror

    init {
        parcelableType = elements.getTypeElement(PARCELABLE).asType()
        serializableType = elements.getTypeElement(SERIALIZABLE).asType()
    }

    /**
     * Diagnostics out the true java type
     *
     * @param element Raw type
     * @return Type class of java
     */
    fun typeExchange(element: Element): Int {
        val typeMirror = element.asType()

        // Primitive
        return if (typeMirror.kind.isPrimitive) {
            element.asType().kind.ordinal
        } else when (typeMirror.toString()) {
            BYTE -> TypeKind.BYTE.ordinal
            SHORT -> TypeKind.SHORT.ordinal
            INTEGER -> TypeKind.INT.ordinal
            LONG -> TypeKind.LONG.ordinal
            FLOAT -> TypeKind.FLOAT.ordinal
            DOUBEL -> TypeKind.DOUBLE.ordinal
            BOOLEAN -> TypeKind.BOOLEAN.ordinal
            CHAR -> TypeKind.CHAR.ordinal
            STRING -> TypeKind.STRING.ordinal
            else ->                 // Other side, maybe the PARCELABLE or SERIALIZABLE or OBJECT.
                if (types.isSubtype(typeMirror, parcelableType)) {
                    // PARCELABLE
                    TypeKind.PARCELABLE.ordinal
                } else if (types.isSubtype(typeMirror, serializableType)) {
                    // SERIALIZABLE
                    TypeKind.SERIALIZABLE.ordinal
                } else {
                    TypeKind.OBJECT.ordinal
                }
        }
    }
}