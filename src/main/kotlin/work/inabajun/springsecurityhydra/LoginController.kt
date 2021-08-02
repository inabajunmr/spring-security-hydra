package work.inabajun.springsecurityhydra

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import javax.servlet.http.HttpSession

import org.springframework.beans.factory.annotation.Autowired

@Controller
class LoginController (){

    @GetMapping("/login")
    fun login(): String {
        return "login"
    }

}