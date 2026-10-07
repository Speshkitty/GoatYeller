package com.speshkitty;

import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.coords.WorldArea;
import net.runelite.api.events.AnimationChanged;
import net.runelite.api.gameval.AnimationID;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import org.apache.commons.lang3.RandomUtils;

@Slf4j
@PluginDescriptor(
	name = "Goat Yeller"
)
public class GoatYellerPlugin extends Plugin
{
	@Inject
	private Client client;

	WorldArea goatArea = new WorldArea(2550, 2180, 50, 30, 0);

	String[] messagesToYell = new String[] {
			"GOATS FOR THE GOAT PIT",
			"GOATS FOR THE GOAT HOLE",
			"WE MUST FEED THE PIT",
			"PRAISE THE PIT",
			"THE PIT MUST FEED",
			"HORNS FOR THE HORN THRONE",
			"THIS. IS. WYRMSCRAIG!!!!",
			"WATCH OUT MR GOATIDENT",
			"Billy? You down there? Better check...",
			"Watch your step",
			"Look out below!",
			"The hole must be made whole",
			"So a goat walks into a pit and the pitman says-",
			"Long Live The Goat",
			"FILL THE GOAT HOLE",
			"HAIL TO THE HOLE",
			"Yippie-ki-yay, mother goat"
	};

	@Override
	protected void startUp() throws Exception
	{
		log.debug("Goat Yeller started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.debug("Goat Yeller stopped!");
	}

	@Subscribe
	public void onAnimationChanged(AnimationChanged animationChanged)
	{
		Actor player = animationChanged.getActor();
		if(player.getAnimation() != AnimationID.CATTLEPROD_WALKMERGE && player.getAnimation() != AnimationID.HUMAN_CASTTELEGRAB) { return;	}
		if(!player.getWorldLocation().isInArea(goatArea)) { return; }

		String selectedLine = messagesToYell[RandomUtils.nextInt(0, messagesToYell.length)];

		if(player.getOverheadCycle() == 0)
		{
			player.setOverheadText(selectedLine);
			player.setOverheadCycle(100);
		}


        //log.debug("New animation: {} did anim {}", animationChanged.getActor().getName(), animationChanged.getActor().getAnimation());
	}
}
