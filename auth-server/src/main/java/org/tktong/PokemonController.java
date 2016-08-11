package org.tktong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pokemon")
public class PokemonController {
	@Autowired
	private PokemonRepository repository;

	@RequestMapping
	public ModelAndView getPokemon() {
		// System.out.println(repository.findAll());
		return new ModelAndView("pokemon", "pokemons", repository.findAll());
	}
}