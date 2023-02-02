// package server

// import org.springframework.boot.SpringApplication
// import org.springframework.boot.autoconfigure.SpringBootApplication
// import org.springframework.web.bind.annotation.GetMapping
// import org.springframework.web.bind.annotation.RestController

// @SpringBootApplication
// @RestController
// class Application {
//     static JDABuilder jda

//     @GetMapping("/")
//     String home() {
//         return "Hello World!"
//     }

//     @GetMapping("/startBot")
//     String startBot() {
//         if (jda == null) {
//             def discordToken = System.getenv("DISCORD_BOT_TOKEN")
//             jda = new JDABuilder(discordToken)
//                 .addEventListeners(new PingBot())
//                 .build()
//             return "Bot started"
//         } else {
//             return "Bot already started"
//         }
//     }

//     @GetMapping("/stopBot")
//     String stopBot() {
//         if (jda != null) {
//             jda.shutdown()
//             jda = null
//             return "Bot stopped"
//         } else {
//             return "Bot already stopped"
//         }
//     }
// }
