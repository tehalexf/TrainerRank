// package org.tktong.controllers;

// // import org.springframework.security.authentication.AnonymousAuthenticationToken;
// // import org.springframework.security.core.Authentication;
// // import org.springframework.security.core.context.SecurityContextHolder;
// // import org.springframework.security.core.userdetails.UserDetails;
// // import org.springframework.stereotype.Controller;
// // import org.springframework.web.bind.annotation.RequestMapping;
// // import org.springframework.web.bind.annotation.RequestMethod;
// // import org.springframework.web.bind.annotation.RequestParam;
// // import org.springframework.ui.Model;
// // import org.springframework.web.servlet.ModelAndView;

// // import POGOProtos.Enums.PokemonIdOuterClass;
// // import POGOProtos.Networking.Responses.ReleasePokemonResponseOuterClass;
// // import com.pokegoapi.api.PokemonGo;
// // import com.pokegoapi.api.pokemon.Pokemon;
// // import com.pokegoapi.auth.GoogleUserCredentialProvider;
// // import com.pokegoapi.auth.PtcCredentialProvider;
// // import com.pokegoapi.exceptions.LoginFailedException;
// // import com.pokegoapi.exceptions.RemoteServerException;
// // import com.pokegoapi.util.Log;
// // import okhttp3.OkHttpClient;

// // @Controller
// // public class LoginController {

// // 	@RequestMapping(value = "/api/loginGoogle" , method = RequestMethod.GET)
// // 	public ModelAndView clientLoginGoogle(@RequestParam(value = "token", required = true) String token) {
// //      	OkHttpClient httpClient;
// //      	PokemonGo go;

// //       ModelAndView mav = new ModelAndView("hello");
     
// // 	  mav.addObject("title", "Spring Security Login Form - Cuck Meeeeeeeee");
// // 	  mav.addObject("message", "This is default page!");

// // 		try {
// // 			httpClient = new OkHttpClient();
// // 			GoogleUserCredentialProvider provider = new GoogleUserCredentialProvider(httpClient);
// // 			provider.login(token);
// // 			go = new PokemonGo(provider, httpClient);
// // 		} catch (LoginFailedException e) {
// // 			System.out.println(e);
// // 		} catch (RemoteServerException e) {
// // 			System.out.println(e);
// // 		}

     
// // 	  mav.addObject("title", "Spring Security Login Form - Cuck Meeeeeeeee");
// // 	  mav.addObject("message", "This is default page!");
// // 	  return mav;
// // 	}

// // }