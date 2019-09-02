package com.mycompany.KotlinFile

import android.util.Log
import kotlin.properties.Delegates

/**
 * Created by Qingweid on 2017/8/25.
 */

open class Person {
    private val children = listOf<ArrayList<Person>>()
    private val TAG: String = "Person"
    private val person = Person()

//    constructor(parent: Person) {
////        Log.i(TAG, "constructor")
//    }
}

open class Foo {
    open fun f() { Log.i("Foo","Foo.f()") }
    open val x: Int get() = 1
}

open class Bar : Foo() {
    override fun f() {
        super.f()
        Log.i("BAR",("Bar.f()"))
        Baz().g()
    }

    override val x: Int get() = super.x + 1


    inner class Baz : Foo() {
        fun g() {
            super@Bar.f() // 调用 Foo 实现的 f()
            Log.i("BAZ","" + (super@Bar.x))// 使用 Foo 实现的 x 的 getter

            super.f()
            Log.i("BAZ","" + (super.x))// 使用 Foo 实现的 x 的 getter
        }
    }
}

open class A {
    open fun f() {
        Log.i("A",("A"))
    }

    fun a() {
        Log.i("A",("a"))
    }

    var allByDefault: Int? = 1 // 错误：需要显式初始化器，隐含默认 getter 和 setter
    var initialized = 1 // 类型 Int、默认 getter 和 setter
}

interface B {
    // 接口成员默认就是“open”的
    fun f() {
        Log.i("B",("B"))
    }
    fun b() {
        Log.i("B",("b"))
    }
}

open class C : A(), B {
    // 编译器要求覆盖 f()：
    override fun f() {
        super<A>.f() // 调用 A.f()
        super<B>.f() // 调用 B.f()
    }
}

class F {
    fun foo() {
        Log.i("Class F",("member"))
    }
}

//fun F.foo() {
//    Log.i("Class F", ("extension"))
//}


class MyClass {
    companion object { }  // 将被称为 "Companion"
}

fun MyClass.Companion.foo() {
    // ……
}


sealed class Expr
data class Const(val number: Double) : Expr()
data class Sum(val e1: Expr, val e2: Expr) : Expr()
object NotANumber : Expr()

enum class ProtocolState {
    WAITING {
        override fun signal() = TALKING
    },

    TALKING {
        override fun signal() = WAITING
    };

    abstract fun signal(): ProtocolState
}

enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF),
}

enum class RGB { RED, GREEN, BLUE }

interface Factory<T> {
    fun create(): T
}

class MyNewClass {
    companion object : Factory<MyClass> {
        override fun create(): MyClass = MyClass()
    }
}

//class User(val map: Map<String, Any?>) {
//    val name: String by Delegates.mapVal(map)
//    val age: Int     by Delegates.mapVal(map)
//}





