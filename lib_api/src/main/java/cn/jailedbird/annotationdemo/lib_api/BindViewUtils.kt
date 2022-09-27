package cn.jailedbird.annotationdemo.lib_api

import android.app.Activity
import android.util.Log
import android.widget.Toast
import kotlin.math.log

// 这段代码已经可自动运行(需要call下) 生成的代码
// 这段代码的思路是直通过Activity名称推到Activity$ViewBinding 然后反射获取
// 另外的一种思路 使用KSP生层的代码 加入SourceSet 直接在代码中调用、 避免反射 但是这种我感觉就很麻烦
// 需要配置的地方过于多了 还需要依赖编译才能找到对应源码 直接使用反射（only 1） 性能消耗不大 还可以屏蔽生成代码的细节
object BindViewUtils {
    // 使用多态 传递父类 反射获取子类的类型, 代码生成时直接传递子类Activity作为构造参数
    fun bind(activity: Activity) {
        try {
            val activityCanonicalName = activity.javaClass.canonicalName ?: return
            // 根据Activity的名称寻找对应的Binding文件 然后绑定 执行其中生成构造函数的绑定方法
            val viewBindingClassName = "$activityCanonicalName\$ViewBinding"
            val viewBindingClass = Class.forName(viewBindingClassName)
            val activityClass = Class.forName(activityCanonicalName)
            val constructor = viewBindingClass.getConstructor(activityClass)
            constructor.newInstance(activity)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            Toast.makeText(activity.applicationContext, "${e.message}", Toast.LENGTH_SHORT).show()
            Log.d("FUCK", e.message ?: "")
        }
        /*catch (e: ClassNotFoundException) {
                e.printStackTrace()
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
            } catch (e: InstantiationException) {
                e.printStackTrace()
            } catch (e: InvocationTargetException) {
                e.printStackTrace()
            } catch (e: NoSuchFieldException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }*/

    }
}