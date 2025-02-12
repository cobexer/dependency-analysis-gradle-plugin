package com.autonomousapps.kit.gradle.android

import com.autonomousapps.kit.render.Element
import com.autonomousapps.kit.render.Scribe

/**
 * The `android` block, for use by projects build with the Android Gradle Plugin.
 * ```
 * // build.gradle[.kts]
 * android {
 *   ...
 * }
 * ```
 */
public class AndroidBlock @JvmOverloads constructor(
  private val namespace: String? = null,
  private val compileSdkVersion: Int = 34,
  private val defaultConfig: DefaultConfig = DefaultConfig.DEFAULT_APP,
  private val compileOptions: CompileOptions = CompileOptions.DEFAULT,
  private val kotlinOptions: KotlinOptions? = null,
) : Element.Block {

  override val name: String = "android"

  override fun render(scribe: Scribe): String = scribe.block(this) { s ->
    if (namespace != null) {
      s.line {
        it.append("namespace '")
        it.append(namespace)
        it.append("'")
      }
    }
    s.line {
      it.append("compileSdkVersion ")
      it.append(compileSdkVersion)
    }
    defaultConfig.render(s)
    compileOptions.render(s)
    kotlinOptions?.render(s)
  }

  public class Builder {
    public var namespace: String? = null
    public var compileSdkVersion: Int = 34
    public var defaultConfig: DefaultConfig = DefaultConfig.DEFAULT_APP
    public var compileOptions: CompileOptions = CompileOptions.DEFAULT
    public var kotlinOptions: KotlinOptions? = null

    public fun build(): AndroidBlock {
      return AndroidBlock(
        namespace = namespace,
        compileSdkVersion = compileSdkVersion,
        defaultConfig = defaultConfig,
        compileOptions = compileOptions,
        kotlinOptions = kotlinOptions,
      )
    }
  }

  public companion object {
    @JvmOverloads
    @JvmStatic
    public fun defaultAndroidAppBlock(
      isKotlinApplied: Boolean = false,
      namespace: String? = null,
    ): AndroidBlock = AndroidBlock(
      namespace = namespace,
      defaultConfig = DefaultConfig.DEFAULT_APP,
      kotlinOptions = if (isKotlinApplied) KotlinOptions.DEFAULT else null
    )

    @JvmOverloads
    @JvmStatic
    public fun defaultAndroidLibBlock(
      isKotlinApplied: Boolean = false,
      namespace: String? = null,
    ): AndroidBlock = AndroidBlock(
      namespace = namespace,
      defaultConfig = DefaultConfig.DEFAULT_LIB,
      kotlinOptions = if (isKotlinApplied) KotlinOptions.DEFAULT else null
    )
  }
}
