package nedis.study.jee.controllers;

import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.Test;
import nedis.study.jee.forms.util.StringId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nedis
 * @version 1.0
 */
@Controller
@RequestMapping("/advanced_tutor")
public class AdvancedTutorController extends AbstractTutorController {

	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home(){
		return "advanced_tutor/home";
	}

	@RequestMapping(value="test", method=RequestMethod.GET)
	public String showTutorTests(Model model){
		List<Test> list = advancedTutorService.getAllTests();
		List<StringId> tests = new ArrayList<StringId>();
		for (Test test : list){
			tests.add(new StringId(test.getIdTest(),test.getName()));
		}

		model.addAttribute("tests",tests);


		return "tutor/test";
	}
}
