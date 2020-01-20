package com.sksamuel.kotest.extensions

import io.kotest.core.spec.SpecConfiguration
import io.kotest.core.extensions.SpecExtension
import io.kotest.shouldBe
import io.kotest.core.spec.style.StringSpec
import kotlin.reflect.KClass

@Suppress("OverridingDeprecatedMember", "DEPRECATION")
class LateinitSpecInterceptorStringSpecTest : StringSpec() {

   private lateinit var string: String

   inner class Interceptor : SpecExtension {
      override suspend fun intercept(spec: KClass<out SpecConfiguration>, process: suspend () -> Unit) {
         this@LateinitSpecInterceptorStringSpecTest.string = "Hello"
         process()
      }
   }

   override fun extensions() = listOf(Interceptor())

   init {
      "Hello should equal to Hello" {
         string shouldBe "Hello"
      }
   }
}
