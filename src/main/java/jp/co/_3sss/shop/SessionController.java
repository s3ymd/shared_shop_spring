package jp.co._3sss.shop;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class SessionController {

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login() {
        return "session/login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String doLogin(LoginForm form, HttpSession session) {
        if ("test".equals(form.getUserId())) {
            session.setAttribute("userId", form.getUserId());
            return "redirect:/";
        } else {
            return "session/login";
        }
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
