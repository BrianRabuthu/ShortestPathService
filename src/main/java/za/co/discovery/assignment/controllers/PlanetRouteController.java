package za.co.discovery.assignment.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import za.co.discovery.assignment.entity.Planet;
import za.co.discovery.assignment.entity.PlanetRoute;
import za.co.discovery.assignment.entity.ShortestPath;
import za.co.discovery.assignment.repository.IPlanetRepository;
import za.co.discovery.assignment.repository.IPlanetRouteRepository;
import za.co.discovery.assignment.repository.IShortestPathRepository;
import za.co.discovery.assignment.service.ShortestPathService;

@Slf4j
@Controller
public class PlanetRouteController {
	@Autowired
	private IPlanetRouteRepository planetRouteRepository;
	
	@Autowired
	private IPlanetRepository planetRepository;

	@Autowired
	private IShortestPathRepository shortestPathRepository;

	@Autowired
	private ShortestPathService shortestPathService;

	@GetMapping({ "/", "/index" })
	public String home(Model model) {
		List<PlanetRoute> listCustomer = (List<PlanetRoute>) planetRouteRepository.findAll();
		model.addAttribute("listPlanetRoutes", listCustomer);
		return "index";
	}

	@RequestMapping("/new")
	public String newCustomerForm(Map<String, Object> model) {
		PlanetRoute planetRoute = new PlanetRoute();
		model.put("planetRoute", planetRoute);
		return "add";
	}

	@PostMapping("/save")
	public String addUser(@Valid PlanetRoute planetRoute, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "index";
		}

		planetRouteRepository.save(planetRoute);
		model.addAttribute("listPlanetRoutes", planetRouteRepository.findAll());
		return "redirect:/";
	}

	@GetMapping("/edit")
	public String showUpdateForm(@RequestParam long id, Model model) {
		PlanetRoute planetRoute = planetRouteRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		model.addAttribute("planetRoute", planetRoute);
		return "edit_route";
	}

	@GetMapping("/delete")
	public String deleteUser(@RequestParam("id") long id, Model model) {
		PlanetRoute planetRoute = planetRouteRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		planetRouteRepository.delete(planetRoute);
		model.addAttribute("listPlanetRoutes", planetRouteRepository.findAll());
		return "index";
	}

	@GetMapping("/search")
	public String search(@RequestParam String keyword, Model model) {
		List<PlanetRoute> listCustomer = planetRouteRepository.search(keyword);
		model.addAttribute("result", listCustomer);
		return "search";
	}

	@GetMapping("/findshortestpath")
	public String findshortestpath(Model model, @RequestParam String origin, @RequestParam String destination) {
		List<Planet> planets = (List<Planet>) planetRepository.findAll();
				
		ShortestPath shortestDistance = new ShortestPath();
		List<ShortestPath> listShortestDistance = (List<ShortestPath>) shortestPathRepository.findAll();
		StringBuilder sb = new StringBuilder();

		log.info("###### start :: findshortestpath ######## with sourceNode " + origin + "and distination Name "
				+ destination);

		listShortestDistance.forEach(l -> {
			if (l.getPlanetNode().equalsIgnoreCase(origin)) {
				shortestDistance.setPath(l.getPath());
			}
		});

		String shortestPath = shortestPathService.shortestPath(origin, destination);
		sb.append(shortestPath);
		sb.append("\n");

		log.info("###### end :: findshortestpath ######## ");

		model.addAttribute("shortestPaths", sb.toString());
		
		return "success";
	}
}
