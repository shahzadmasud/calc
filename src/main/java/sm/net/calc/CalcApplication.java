package sm.net.calc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;

@Controller
@EnableAsync
@SpringBootApplication
public class CalcApplication {

//    @Autowired
//    private SmtpAdapter smtpAdapter;

    @GetMapping("/")
    String home() {
        return "redirect:/swagger-ui.html";
    }

//    @GetMapping("/sendmail")
//    String sendmail() {
//        smtpAdapter.sendMail("shahzad.masud@gmail.com", "AWS SES Test", "Testing");
//        return "sent";
//    }

    public static void main(String[] args) {
        SpringApplication.run(CalcApplication.class, args);
    }

}
