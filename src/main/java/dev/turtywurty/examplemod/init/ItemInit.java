package dev.turtywurty.examplemod.init;

import dev.turtywurty.examplemod.ExampleMod;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

import java.util.Map;
import java.util.function.Supplier;

/**
 * This class will be used to register all of your mod's items.
 */
public class ItemInit {
    /**
     * The ITEMS {@link DeferredRegister} will act as a list of all of your mod's items.
     * <br><br>
     * In more technical terms, it acts as a {@link Map}<{@link RegistryObject}<{@link Item}>, {@link Supplier}<{@link Item}>>.
     * <br><br>
     * This same concept will be used for used for most other registry types such as blocks, entities, etc.
     * <br><br>
     * When constructing a {@link DeferredRegister} you must provide it with two things:<br>
     * 1. The {@link IForgeRegistry} that the objects will be registered to.<br>
     * 2. The mod id that the objects will be registered to.
     * <br><br>
     * For the {@link IForgeRegistry} you can can generally use the {@link ForgeRegistries} class to get the registry you want.
     * In certain circumstances, you may need to use a different source of registries, but this is rare.
     * We will be using {@link ForgeRegistries#ITEMS} for this example.
     * <br><br>
     * For the mod id, you can use your modid. We will be using the {@link ExampleMod#MOD_ID} constant for this example.
     */
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ExampleMod.MOD_ID);

    /**
     * The {@link RegistryObject}<{@link Item}> should be thought of like a {@link Supplier}<{@link Item}> with a {@link String} name.
     * The object should be accessed using the {@link RegistryObject#get()} method. This, however, will not be present
     * until the item has been registered - which occurs during the loading stage.
     * <br>
     * -
     * <br>
     * Accessing the object too early will result in a: <br>
     * {@link NullPointerException} - Registry object not present.
     * <br><br>
     * To create the {@link RegistryObject} we use the {@link DeferredRegister#register(String, Supplier)} method.
     * <br>
     * -
     * <br>
     * The first parameter is the name of the object.<br>
     * The second parameter is a {@link Supplier} that will create the object.
     * <br><br>
     * The {@link Supplier} should return the object that you want to register. In this case, we are returning a new {@link Item}.
     * You can, however, return any object that extends the type of object that you are registering. For example,
     * we could return a new {@link SwordItem} or a new {@link SignItem} instead of a new {@link Item}.
     * <br><br>
     * The {@link Item.Properties} is used to set properties for the item.
     * <br>
     * -
     * <br>
     * For example:<br>
     * {@link Item.Properties#stacksTo(int)} can be used to set the max stack size of the item. <br>
     * {@link Item.Properties#durability(int)} can be used to set the max durability of the item.
     * <br><br>
     * There are many more properties that can be set, but we will not be going over them in this example.
     */
    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item",
            () -> new Item(
                    new Item.Properties()
                            .stacksTo(16)
                            .durability(100)
            ));
}
