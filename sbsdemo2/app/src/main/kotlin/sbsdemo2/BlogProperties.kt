package sbsdemo2

import org.springframework.boot.context.properties.ConfigurationProperties

// 自定义 application.properties 配置，配置会在启动时被加载
// 需要 EnableConfigurationProperties
// 依赖编译时处理器  spring-boot-configuration-processor
@ConfigurationProperties("blog")
data class BlogProperties(var title: String, val banner: Banner) {
    data class Banner(val title: String? = null, val content: String)
}