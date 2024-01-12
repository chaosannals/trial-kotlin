package sbsdemo2

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScans

@ComponentScan(
    "sbsdemo2",
    "sbsdemo2.controller"
) // 指定 组件 扫描名字空间， 默认只有 sbsdemo2
@SpringBootApplication
class App {
    
}

fun main(args: Array<String>) {
    runApplication<App>(*args)
}
