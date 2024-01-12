package sbsdemo2

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScans

@ComponentScan("sbsdemo2.controller") // 指定控制器扫描名字空间
@SpringBootApplication
class App {
    
}

fun main(args: Array<String>) {
    runApplication<App>(*args)
}
