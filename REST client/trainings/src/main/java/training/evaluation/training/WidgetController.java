package training.evaluation.training;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RequestMapping("/api")
@RestController
public class WidgetController {

    @RequestMapping(method=RequestMethod.GET)
    public String create(){
        return "Hello World";
    }
}
