package com.mycompany.mymaintestactivity

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.mycompany.KotlinFile.Bar
import com.mycompany.KotlinFile.C
import com.mycompany.KotlinFile.F
import com.mycompany.KotlinFile.RGB
import kotlinx.android.synthetic.main.kotlin_test.*

/**
 * Created by Qingweid on 2017/8/22.
 */

class KotlinTest : Activity() {

    private val TAG: String = "KotlinTest"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kotlin_test)

        Log.i(TAG, "onCreate()" )

        tv.setText("Hello, world!")

        val a: Int = 10000
        Log.i(TAG, "a === a:" + ( a === a) ) // Prints 'true'
        val boxedA: Int? = a
        val anotherBoxedA: Int? = a
        Log.i(TAG, "boxedA === anotherBoxedA:" + (boxedA === anotherBoxedA) ) // Prints 'true'

        val c: Int = 1 //一个装箱过的 Int (java.lang.Integer)
        val d: Long = c.toLong() // 一个隐式装箱的 Long (java.lang.Long)
        Log.i(TAG,"c.toLong() == d:" + ( c.toLong() == d ))// 很惊讶吧　这次打印出的是 'false' 这是由于 Long 类型的 equals() 只有和 Long 比较才会相同


        Log.i(TAG, " " + decimalDigitValue('2'))

        val x: Int = 10
        val validNumbers = listOf<Int>(0,1,2,3,4,5,6,7,8,9,10)
        when (x) {
            in 1..10 -> Log.i(TAG, ("x is in the range"))
            in validNumbers -> Log.i(TAG, ("x is valid"))
            !in 10..20 -> Log.i(TAG, ("x is outside the range"))
            else -> Log.i(TAG, ("none of the above"))
        }

        when {
            isOdd(x) ->  Log.i(TAG,("x is odd"))
            isEven(x) ->  Log.i(TAG,("x is even"))
            else ->  Log.i(TAG,("x is funny"))
        }


        loop@ for (i in 1..100) {
            for (j in i..100) {
                if (j % 10 == 0) {
                    Log.i(TAG,("i:$i j:$j"))
                    continue@loop
                }
            }
        }


        val test = Test("nihao")

        val bar: Bar = Bar()
        bar.f()
        Log.i(TAG, "" + bar.x)

        val cval: C = C()
        cval.f()

        val f: F = F()
        f.foo()

        printAllValues<RGB>() // 输出 RED, GREEN, BLUE

    }

    inline fun <reified T : Enum<T>> printAllValues() {
        Log.i("Values", enumValues<T>().joinToString { it.name + " " + it.ordinal })
    }


    fun isOdd(x: Int): Boolean {
        return (x % 2 == 1)
    }

    fun isEven(x: Int): Boolean {
        return (x % 2 == 0)
    }


    fun decimalDigitValue(c: Char): Int {
        if (c !in '0'..'9')
            throw IllegalArgumentException("Out of range")
        return c.toInt() - '0'.toInt() //显示转换为数值类型
    }

    class Test (name: String) {
        private val TAG: String = "Test"
        init {
            Log.i(TAG, name)
        }
    }
}