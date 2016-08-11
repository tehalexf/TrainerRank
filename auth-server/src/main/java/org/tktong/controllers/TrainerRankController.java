package org.tktong.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.tktong.Result;
import javax.servlet.http.HttpSession;

import org.tktong.services.UserAuthService;
import org.tktong.datamodels.PokemonObj;

import org.springframework.beans.factory.annotation.Autowired;

import com.pokegoapi.api.pokemon.Pokemon;
import POGOProtos.Enums.PokemonIdOuterClass;
import POGOProtos.Networking.Responses.ReleasePokemonResponseOuterClass;
import com.pokegoapi.api.PokemonGo;
import com.pokegoapi.api.pokemon.Pokemon;
import com.pokegoapi.auth.GoogleUserCredentialProvider;
import com.pokegoapi.auth.PtcCredentialProvider;
import com.pokegoapi.exceptions.LoginFailedException;
import com.pokegoapi.exceptions.RemoteServerException;
import com.pokegoapi.util.Log;
import okhttp3.OkHttpClient;
import org.springframework.web.bind.annotation.*;
import org.tktong.datamodels.UserStats;
import org.tktong.datamodels.Candies;
import org.tktong.UserStatsRepository;
import org.tktong.CandiesRepository;
import org.tktong.PokemonRepository;
import java.math.BigInteger;
import POGOProtos.Data.Player.CurrencyOuterClass;
import POGOProtos.Data.Player.EquippedBadgeOuterClass.EquippedBadge;
import POGOProtos.Data.Player.PlayerStatsOuterClass;
import POGOProtos.Data.PlayerDataOuterClass.PlayerData;
import com.pokegoapi.api.player.*;
import com.pokegoapi.api.inventory.Stats;
import com.pokegoapi.api.inventory.Inventories;
import com.pokegoapi.api.inventory.PokeBank;
import com.pokegoapi.api.inventory.CandyJar;
import java.lang.reflect.*;
import java.lang.*;
import java.util.*;
import POGOProtos.Enums.PokemonFamilyIdOuterClass.PokemonFamilyId;
import POGOProtos.Enums.PokemonMoveOuterClass.PokemonMove;
import org.tktong.workers.*;
import java.util.concurrent.*;
import org.tktong.responses.LoginResponse;

@Controller
public class TrainerRankController {

	@Autowired
	PokemonWorker pokemonWorker;

	@Autowired
	UserStatsRepository  usr;

	@Autowired
	CandiesRepository cr;

	@Autowired
	PokemonRepository pr;


	private String tryLogin(PokemonGo go, ModelAndView mav) throws LoginFailedException, InterruptedException {
		String authUsername;
		PlayerProfile playerProfile = null;
		PlayerData playerData = null;
		int tries = 0;
		boolean passed = false;
		while (tries < 3 && !passed) {
			try {
				playerProfile = go.getPlayerProfile();
				passed = true;

			} catch (Exception e) {
				System.out.println("Retrying");
				Thread.sleep(500);
			}
		}

		if (!passed) {
			throw new LoginFailedException("Server Busy");
		}

		tries = 0;
		passed = false;
		while (tries < 3 && !passed) {
			try {
				playerData = playerProfile.getPlayerData();
				passed = true;
			} catch (Exception e) {
				System.out.println("Retrying");
				Thread.sleep(500);
			}
		}

		if (!passed) {
			throw new LoginFailedException("Server Busy");
		}

		authUsername = playerData.getUsername();

		return authUsername;
	}


	@RequestMapping(value = "/testloginjson", method = RequestMethod.GET)
	public @ResponseBody LoginResponse testloginjson(@RequestParam(value = "destination", required = false) String destination, @RequestParam(value = "username", required = false) String username, @RequestParam(value = "password", required = false) String password, @RequestParam(value = "token", required = false) String token, @RequestHeader("Accept") String acceptHeader, HttpSession session) {

		OkHttpClient httpClient;
		PokemonGo go = null;
		System.out.println(username);
		System.out.println(password);
		System.out.println(token);
		ModelAndView mav = new ModelAndView();
		String authUsername;
		try {
			if (token == null && username != null && password != null) {

				httpClient = new OkHttpClient();

				short tries = 0;
				boolean succeeded = false;
				while (tries < 2 && !succeeded) {
					try {
						System.out.println("Debug: Trying:");
						go = new PokemonGo(new PtcCredentialProvider(httpClient, username, password), httpClient);
						succeeded = true;
					} catch (Exception e) {
						//Empty
					}
					tries++;
				}

				if (!succeeded) {
					mav.addObject("error", "PTC Servers are busy. Try again soon.");
					mav.addObject("destination", destination);
					mav.setViewName("login");
					return new LoginResponse(-1);
				}

			} else if (username == null && password == null && token != null) {
				httpClient = new OkHttpClient();
				GoogleUserCredentialProvider provider = new GoogleUserCredentialProvider(httpClient);
				provider.login(token);
				go = new PokemonGo(provider, httpClient);
			} else {
				mav.addObject("error", "Invalid login details.");
				mav.addObject("destination", destination);
				mav.setViewName("login");
				return new LoginResponse(-2);
			}

			authUsername = tryLogin(go, mav);
			UserStats userObj = usr.findByUsername(authUsername);

			// Future<Integer> result = pokemonWorker.work();
			Future<Integer> result = pokemonWorker.populateDatabase(go, userObj);

			System.out.println("WORKER WORKING");
			if (userObj == null) {
				userObj = new UserStats(authUsername);
			}

		} catch (LoginFailedException e) {
			System.out.println(e);
			mav.setViewName("login");
			mav.addObject("error", "Invalid login details.");
			mav.addObject("destination", destination);
			return new LoginResponse(-2);
		} catch (RemoteServerException e) {
			System.out.println(e);
			mav.setViewName("login");
			mav.addObject("error", "Server is down.");
			mav.addObject("destination", destination);
			return new LoginResponse(-3);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace(System.out);
			mav.setViewName("login");
			mav.addObject("error", "Server is busy, try again in 5 seconds.");
			mav.addObject("destination", destination);
			return new LoginResponse(-4);
		}

		session.setAttribute("user", authUsername);

		if (destination != null)
			mav.setViewName(destination);
		else
			mav.setViewName("index");

		return new LoginResponse(0, authUsername);
	}


	@RequestMapping(value = "/testlogin", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView testlogin(@RequestParam(value = "destination", required = false) String destination, @RequestParam(value = "username", required = false) String username, @RequestParam(value = "password", required = false) String password, @RequestParam(value = "token", required = false) String token, @RequestHeader("Accept") String acceptHeader, HttpSession session) {

		OkHttpClient httpClient;
		PokemonGo go = null;
		System.out.println(username);
		System.out.println(password);
		System.out.println(token);
		ModelAndView mav = new ModelAndView();
		String authUsername;
		try {
			if (token == null && username != null && password != null) {

				httpClient = new OkHttpClient();

				short tries = 0;
				boolean succeeded = false;
				while (tries < 2 && !succeeded) {
					try {
						System.out.println("Debug: Trying:");
						go = new PokemonGo(new PtcCredentialProvider(httpClient, username, password), httpClient);
						succeeded = true;
					} catch (Exception e) {
						//Empty
					}
					tries++;
				}

				if (!succeeded) {
					mav.addObject("error", "PTC Servers are busy. Try again soon.");
					mav.addObject("destination", destination);
					mav.setViewName("login");
					return mav;
				}

			} else if (username == null && password == null && token != null) {
				httpClient = new OkHttpClient();
				GoogleUserCredentialProvider provider = new GoogleUserCredentialProvider(httpClient);
				provider.login(token);
				go = new PokemonGo(provider, httpClient);
			} else {
				mav.addObject("error", "Invalid login details.");
				mav.addObject("destination", destination);
				mav.setViewName("login");
				return mav;
			}

			authUsername = tryLogin(go, mav);
			UserStats userObj = usr.findByUsername(authUsername);

			// Future<Integer> result = pokemonWorker.work();
			Future<Integer> result = pokemonWorker.populateDatabase(go, userObj);

			System.out.println("WORKER WORKING");
			if (userObj == null) {
				userObj = new UserStats(authUsername);
			}

		} catch (LoginFailedException e) {
			System.out.println(e);
			mav.setViewName("login");
			mav.addObject("error", "Invalid login details.");
			mav.addObject("destination", destination);
			return mav;
		} catch (RemoteServerException e) {
			System.out.println(e);
			mav.setViewName("login");
			mav.addObject("error", "Server is down.");
			mav.addObject("destination", destination);
			return mav;
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace(System.out);
			mav.setViewName("login");
			mav.addObject("error", "Server is busy, try again in 5 seconds.");
			mav.addObject("destination", destination);
			return mav;
		}

		session.setAttribute("user", authUsername);


		if (destination != null)
			mav.setViewName(destination);
		else
			mav.setViewName("index");

		return mav;
	}






	@RequestMapping(value = "/loginEndpoint", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView loginEndpoint(@RequestParam(value = "destination", required = false) String destination, @RequestParam(value = "username", required = false) String username, @RequestParam(value = "password", required = false) String password, @RequestParam(value = "token", required = false) String token, @RequestHeader("Accept") String acceptHeader, HttpSession session) {

		OkHttpClient httpClient;
		PokemonGo go;
		System.out.println(username);
		System.out.println(password);
		System.out.println(token);
		ModelAndView mav = new ModelAndView();
		String authUsername;
		try {
			if (token == null && username != null && password != null) {
				httpClient = new OkHttpClient();
				go = new PokemonGo(new PtcCredentialProvider(httpClient, username, password), httpClient);
			} else if (username == null && password == null && token != null) {
				httpClient = new OkHttpClient();
				GoogleUserCredentialProvider provider = new GoogleUserCredentialProvider(httpClient);
				provider.login(token);
				go = new PokemonGo(provider, httpClient);
			} else {
				mav.addObject("error", "Invalid login details.");
				mav.addObject("destination", destination);
				mav.setViewName("login");
				return mav;
			}
			authUsername = go.getPlayerProfile().getPlayerData().getUsername();
		} catch (LoginFailedException e) {
			System.out.println(e);
			mav.setViewName("login");
			mav.addObject("error", "Invalid login details.");
			mav.addObject("destination", destination);
			return mav;
		} catch (RemoteServerException e) {
			System.out.println(e);
			mav.setViewName("login");
			mav.addObject("error", "Invalid login details.");
			mav.addObject("destination", destination);
			return mav;
		}

		session.setAttribute("user", authUsername);


		if (destination != null)
			mav.setViewName(destination);
		else
			mav.setViewName("index");

		return mav;
	}

	@RequestMapping(value = "/one" , method = RequestMethod.GET)
	public ModelAndView defaultPage() {
		ModelAndView mav = new ModelAndView("login");

		mav.addObject("title", "Spring Security Login Form - Database Authentication");
		mav.addObject("message", "This is default page!");
		return mav;

	}

	@RequestMapping(value = "/admin1**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		// model.addAttribute("title", "Spring Security Login Form - Database Authentication");
		// model.addAttribute("message", "This page is for ROLE_ADMIN only!");
		model.setViewName("admin");
		return model;

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
	                          @RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();


		if (error != null) {
			model.addObject("error", "Invalid username and password!");
			model.setViewName("login");
		} else if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
			model.setViewName("login");
		}
		return model;

	}




	// @RequestMapping(value = "/loginEndpoint", method = RequestMethod.GET, produces = "application/json")
	// @ResponseBody
	// public Result loginEndpoint(@RequestParam(value = "username", required = false) String username, @RequestParam(value = "password", required = false) String password, @RequestParam(value = "token", required = false) String token, @RequestHeader("Accept") String acceptHeader, HttpSession session) {

	// 	Result r = new Result();
	// 	r.setStatus(true);
	// 	r.setMessage("Valid user " + username + " and " + password);

	// 	session.setAttribute("user", username);

	// 	return r;
	// }



	//for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		//check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addObject("username", userDetail.getUsername());
		}

		model.setViewName("403");
		return model;

	}

}