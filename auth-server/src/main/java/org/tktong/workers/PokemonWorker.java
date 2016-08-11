package org.tktong.workers;
import org.springframework.stereotype.*;
import org.springframework.scheduling.annotation.*;
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
import java.util.concurrent.Future;
import java.util.concurrent.*;

@Service
public class PokemonWorker {

	@Autowired
	UserStatsRepository  usr;

	@Autowired
	CandiesRepository cr;

	@Autowired
	PokemonRepository pr;

	@Async
	public Future<Integer> work() {
		String threadName = Thread.currentThread().getName();
		System.out.println("   " + threadName + " beginning work");
		try {
			Thread.sleep(10000); // simulates work
		} catch (InterruptedException e) {
			System.out.println("I stopped");
		}
		System.out.println("   " + threadName + " completed work");
		return new AsyncResult<>(0);
	}

	@Async
	public Future<Integer> populateDatabase(PokemonGo go, UserStats userObj) {
		String threadName = Thread.currentThread().getName();
		System.out.println("   " + threadName + " beginning work");
		try {
			PlayerProfile playerProfile = go.getPlayerProfile();
			PlayerData playerData = playerProfile.getPlayerData();


			int stardust = playerProfile.getCurrency(PlayerProfile.Currency.STARDUST);
			int pokecoin = playerProfile.getCurrency(PlayerProfile.Currency.POKECOIN);

			String authUsername = playerData.getUsername();
			PlayerAvatar playerAvatar = playerProfile.getAvatar();

			userObj.setPoke_storage(playerData.getMaxPokemonStorage());
			userObj.setItem_storage(playerData.getMaxItemStorage());
			userObj.setCreation_timestamp_ms(playerData.getCreationTimestampMs());
			userObj.setTeam(playerData.getTeamValue());
			userObj.setGender(playerAvatar.getGenderValue());
			userObj.setShoes(playerAvatar.getShoes());
			userObj.setBackpack(playerAvatar.getBackpack());
			userObj.setPants(playerAvatar.getPants());
			userObj.setPants(playerAvatar.getPants());
			userObj.setSkin(playerAvatar.getSkin());
			userObj.setShirt(playerAvatar.getShirt());
			userObj.setHat(playerAvatar.getHat());
			userObj.setEyes(playerAvatar.getEyes());
			userObj.setEyes(playerAvatar.getEyes());
			userObj.setHair(playerAvatar.getHair());
			userObj.setCustom_icon(null);
			userObj.setCoins(pokecoin);
			userObj.setStardust(stardust);

			Stats stats = playerProfile.getStats();


			userObj.setExp(stats.getExperience());
			userObj.setKm_walked(stats.getKmWalked());
			userObj.setPokemon_encountered(stats.getPokemonsEncountered());
			userObj.setPokedex_entries(stats.getUniquePokedexEntries());
			userObj.setLevel(stats.getLevel());
			userObj.setPokemons_captured(stats.getPokemonsCaptured());
			userObj.setEvolutions(stats.getEvolutions());
			userObj.setPoke_stop_visits(stats.getPokeStopVisits());
			userObj.setPokeballs_thrown(stats.getPokeballsThrown());
			userObj.setEggs_hatched(stats.getEggsHatched());
			userObj.setBig_magikarp_caught(stats.getBigMagikarpCaught());
			userObj.setBattle_attack_won(stats.getBattleAttackWon());
			userObj.setBattle_attack_total(stats.getBattleAttackTotal());
			userObj.setBattle_defended_won(stats.getBattleDefendedWon());
			userObj.setBattle_training_won(stats.getBattleTrainingWon());
			userObj.setBattle_training_total(stats.getBattleTrainingTotal());
			userObj.setPrestige_raised_total(stats.getPrestigeRaisedTotal());
			userObj.setPrestige_dropped_total(stats.getPrestigeDroppedTotal());
			userObj.setPokemon_deployed(stats.getPokemonDeployed());
			userObj.setSmall_rattata_caught(stats.getSmallRattataCaught());



			DailyBonus dailyBonus = playerProfile.getDailyBonus();

			userObj.setNext_collect_timestamp_ms(dailyBonus.getNextCollectedTimestampMs());
			userObj.setNext_defender_bonus_collect_timestamp_ms(dailyBonus.getNextDefenderBonusCollectTimestampMs());

			usr.save(userObj);
			int savedId = userObj.getUser_id();
			Set<PokemonObj> srt = pr.findByUser(savedId);

			Inventories inventories = go.getInventories();
			CandyJar candyjar = inventories.getCandyjar();
			PokeBank pokeBank = inventories.getPokebank();
			List<Pokemon> bank = pokeBank.getPokemons();

			HashMap<Long, PokemonObj> pokeMapping = new HashMap<Long, PokemonObj>();

			for (PokemonObj p : srt) {
				p.setUpdated(false);
				pokeMapping.put(p.getGame_id(), p);
			}



			ArrayList<PokemonObj> pokemonToUpdate = new ArrayList<PokemonObj>();
			for (Pokemon p : bank) {
				PokemonObj corresponding = pokeMapping.get(p.getId());

				if (corresponding == null)
					corresponding = new PokemonObj(savedId, p.getId());

				corresponding.setEgg(p.getIsEgg());
				corresponding.setId(p.getMeta().getNumber());
				corresponding.setCp(p.getCp());
				corresponding.setFavorite(p.isFavorite());
				corresponding.setMove_one(p.getMove1().getNumber());
				corresponding.setMove_two(p.getMove2().getNumber());
				corresponding.setIv_atk(p.getIndividualAttack());
				corresponding.setIv_def(p.getIndividualDefense());
				corresponding.setIv_sta(p.getIndividualStamina());
				corresponding.setCp_multiplier(p.getCpMultiplier());
				corresponding.setUpdated(true);
				corresponding.setAdditional_cp_multiplier(p.getAdditionalCpMultiplier());
				corresponding.setStamina(p.getStamina());
				corresponding.setStamina_max(p.getMaxStamina());
				corresponding.setDeployed_fort_id(p.getDeployedFortId());
				corresponding.setOrigin(p.getOrigin());
				corresponding.setHeight_m(p.getHeightM());
				corresponding.setWeight_kg(p.getWeightKg());
				corresponding.setEgg_km_walked_target(p.getEggKmWalkedTarget());
				corresponding.setEgg_km_walked_start(p.getEggKmWalkedStart());
				corresponding.setPokeball(p.getPokeball().getNumber());
				corresponding.setCaptured_cell_id(BigInteger.valueOf(p.getCapturedS2CellId()));
				corresponding.setBattles_attacked(p.getBattlesAttacked());
				corresponding.setBattles_defended(p.getBattlesDefended());
				corresponding.setEgg_incubator_id(p.getEggIncubatorId());
				corresponding.setCreation_time_ms(BigInteger.valueOf(p.getCreationTimeMs()));
				corresponding.setNum_upgrades(p.getNumerOfPowerupsDone());
				corresponding.setNickname(p.getNickname());
				corresponding.setFrom_fort(p.getFromFort());
				pokemonToUpdate.add(corresponding);
			}

			pr.save(pokemonToUpdate);

			pokemonToUpdate.clear();

			for (PokemonObj p : srt) {
				if (!(p.getUpdated()))
					pokemonToUpdate.add(p);
			}

			pr.deleteInBatch(pokemonToUpdate);

			List<Candies> candySet = cr.find(userObj.getUser_id());
			HashMap<Integer, Candies> jar = new HashMap<Integer, Candies>();

			for (Candies c : candySet) {
				jar.put(c.getPokemon_family(), c);
			}

			ArrayList<Candies> toUpdate = new ArrayList<Candies>();

			for (PokemonFamilyId i : PokemonFamilyId.values()) {
				// System.out.println();
				if (i == PokemonFamilyId.UNRECOGNIZED)
					continue;

				int count = candyjar.getCandies(i);
				int ordinal = i.getNumber();

				Candies cn = jar.get(ordinal);

				if (count == 0 && !jar.containsKey(ordinal))
					continue;

				if (cn == null)
					cn = new Candies(savedId, ordinal, candyjar.getCandies(i));

				toUpdate.add(cn);

			}
			cr.save(toUpdate);
			System.out.println("Got Candy!");

		} catch (LoginFailedException e) {

		} catch (RemoteServerException e) {

		} catch (Exception e) {

		}
		System.out.println("   " + threadName + " completed work");
		return new AsyncResult<>(0);
	}

	public void cancel() { Thread.currentThread().interrupt(); }



	// public void cancel() { Thread.currentThread().interrupt(); }
}