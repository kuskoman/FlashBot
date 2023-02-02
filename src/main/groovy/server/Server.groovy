package server

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

import bot.FlashBotFactory

@SpringBootApplication
@RestController
class Application {
    @GetMapping("/")
    String home() {
        return "Hello World!"
    }

    @GetMapping("/startBot")
    String startBot() {
        FlashBotFactory.getStartedBot()
        return "Bot started"
    }

    @GetMapping("/stopBot")
    String stopBot() {
        FlashBotFactory.stopBot()
        return "Bot stopped"
    }

    static void start(String[] args) {
        SpringApplication.run(Application, args)
    }
}
