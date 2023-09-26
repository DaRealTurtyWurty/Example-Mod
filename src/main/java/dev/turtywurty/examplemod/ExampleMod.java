package dev.turtywurty.examplemod;

import dev.turtywurty.examplemod.init.ItemInit;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

/**
 * This class is the "main" class of your mod. Think of it as an entrypoint - the place forge starts when it loads your mod.
 * <br><br>
 * This class must be annotated with {@link Mod} that contains your modid.
 */
@Mod(ExampleMod.MOD_ID)
public class ExampleMod {
    /**
     * This field contains the modid of your mod. Think of it as a unique identifier for your mod. For example,
     * the modid for Minecraft is {@code "minecraft"}.
     * <br><br>
     * To keep your modid unique, you should always try and use the name of your mod as your modid. For example,
     * if your mod is called "Example Mod", your modid should be "examplemod".
     * <br><br>
     * It is highly recommended that you do not use the name of another mod as your modid as this will cause conflicts.
     * <br><br>
     * When coming up with a modid, remember that it will be used in various places such as in-game when performing commands.
     * <br><br>
     * You may be tempted to use short modids such as 3 letter abbreviations, but this is not recommended as it can be confusing to users,
     * and conflicts are more likely to occur.
     * <br><br>
     * This modid must follow some rules:<br>
     * 1. It must be all lowercase.<br>
     * 2. It must not contain any spaces.<br>
     * 3. It must not contain any special characters.<br>
     * 4. It (should) be unique.
     * <br><br>
     * Additionally, your modid should be the same as the modid in your {@code gradle.properties} defined by the {@code mod_id} property.
     * <br><br>
     * To ensure that you are always using the same modid, you can create a constant in your main class that contains your modid,
     * like so.
     */
    public static final String MOD_ID = "examplemod";

    /**
     * In this example, I have also included a {@link Logger} constant that is used to log messages to the console.
     * <br><br>
     * This is not required, but is recommended as it can be useful for debugging.
     * <br><br>
     * There are several different logging levels which are used to determine the severity of the message:<br>
     * 1. {@link Level#TRACE} - Used for very detailed messages that are only useful for debugging.<br>
     * 2. {@link Level#DEBUG} - Used for detailed messages that are useful for debugging.<br>
     * 3. {@link Level#INFO} - Used for messages that are useful for the user.<br>
     * 4. {@link Level#WARN} - Used for messages that are concerning, but not critical.<br>
     * 5. {@link Level#ERROR} - Used for messages that are critical.
     * <br><br>
     * To log a message, you can use:<br>
     * {@link Logger#trace(String)} method for {@link Level#TRACE} messages.<br>
     * {@link Logger#debug(String)} method for {@link Level#DEBUG} messages.<br>
     * {@link Logger#info(String)} method for {@link Level#INFO} messages.<br>
     * {@link Logger#warn(String)} method for {@link Level#WARN} messages.<br>
     * {@link Logger#error(String)} method for {@link Level#ERROR} messages.
     * <br><br>
     * To learn more about logging, see <a href="https://www.slf4j.org/manual.html">the slf4j manual</a>.
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    /**
     * In your main class's constructor, you should add any event listeners such as registries to the mod {@link IEventBus}.
     * We obtain the mod {@link IEventBus} by using {@link FMLJavaModLoadingContext#getModEventBus()}.
     * The mod {@link IEventBus} is used to register event listeners for events that occur during the mod's lifecycle.
     * There is also a forge {@link IEventBus} that is used to register event listeners for events that occur during the game's lifecycle.
     * <br><br>
     * The {@link IEventBus} is used to register event listeners for various events that occur during the game's lifecycle.
     * For example, we are adding the {@link ItemInit#ITEMS} registry to the {@link IEventBus}, which will tell forge
     * that our items should be registered when the respective registry event is fired.
     * <br><br>
     * To learn more about events, see <a href="https://forge.gemwire.uk/wiki/Events">the forge community wiki</a>.
     */
    public ExampleMod() {
        LOGGER.info("Hello from Example Mod!");

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ItemInit.ITEMS.register(bus);
    }
}
