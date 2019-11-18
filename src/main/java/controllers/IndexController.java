package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import beans.Job;
import beans.JobDB;
import services.IMainService;

import java.util.List;

@Controller
public class IndexController {

	@Autowired
	private IMainService mainSerice;

	@GetMapping("/allJobs")
	public String alljobs(Model model) {

		List<Job> jobsList = mainSerice.getAllJobs();
		model.addAttribute("jobsList", jobsList);

		return "allJobs";
	}

	@RequestMapping(value = { "/allJobs/" }, method = RequestMethod.POST)
	public String alljobs(@RequestParam(required = false) String id_job, Model model) {

		Job job = mainSerice.getJob(id_job);
		
		String result = mainSerice.saveJobToDatabase(job);
				
		List<Job> jobsList = mainSerice.getAllJobs();
		
		model.addAttribute("status", result);
		model.addAttribute("jobsList", jobsList);

		return "allJobs";
	}

	@GetMapping(value = "/job")
	public String edit(@RequestParam String id, Model model) {

		Job job = mainSerice.getJob(id);
		model.addAttribute("job", job);

		return "job";
	}

	@GetMapping(value = "/deleteJob")
	public RedirectView deleteJob(@RequestParam String id, Model model ) {
		
		String result = mainSerice.deleteJob(id);
		
		
		return new RedirectView("savedJobs");
	}
	
	
	@GetMapping(value = "/savedJobs")
	public String allSavedJobs(Model model) {

		List<JobDB> jobDBList = mainSerice.getAllDBJobs();
		
		model.addAttribute("jobList", jobDBList);
				
		return "savedJobs";
	}

}