package guru.springframework.springrecipeapp.bootstrap;

import guru.springframework.springrecipeapp.domain.*;
import guru.springframework.springrecipeapp.repositories.CategoryRepository;
import guru.springframework.springrecipeapp.repositories.IngredientRepository;
import guru.springframework.springrecipeapp.repositories.RecipeRepository;
import guru.springframework.springrecipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Component
@Profile("default")
public class BootstrapDefault implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final IngredientRepository ingredientRepository;

    public BootstrapDefault(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository, IngredientRepository ingredientRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientRepository = ingredientRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        log.debug("Loading bootstrap data");
        loadData();
    }





    private void loadData() {
        Recipe guackRecipe = new Recipe();
        Recipe tacosRecipe = new Recipe();

        //              Loading categories              
        Optional<Category> americanOptional = categoryRepository.findByDescription("American");
        Optional<Category> mexicanOptional = categoryRepository.findByDescription("Mexican");

        //              Loading unit of measures
        Optional<UnitOfMeasure> teaspoonOptional = unitOfMeasureRepository.findByDescription("teaspoon");
        Optional<UnitOfMeasure> tablespoonOptional = unitOfMeasureRepository.findByDescription("tablespoon");
        Optional<UnitOfMeasure> cupOptional = unitOfMeasureRepository.findByDescription("cup");
        Optional<UnitOfMeasure> pinchOptional = unitOfMeasureRepository.findByDescription("pinch");
        Optional<UnitOfMeasure> ounceOptional = unitOfMeasureRepository.findByDescription("ounce");
        Optional<UnitOfMeasure> eachOptional = unitOfMeasureRepository.findByDescription("each");
        Optional<UnitOfMeasure> someOptional = unitOfMeasureRepository.findByDescription("some");
        Category american=null, mexican=null;
        UnitOfMeasure teaspoon=null, tablespoon=null, cup=null, pinch=null, ounce=null, each=null, some=null;

        try {
            american = americanOptional.orElse(null);
            mexican = mexicanOptional.orElse(null);
            teaspoon = teaspoonOptional.orElse(null);
            tablespoon = tablespoonOptional.orElse(null);
            cup = cupOptional.orElse(null);
            pinch = pinchOptional.orElse(null);
            ounce = ounceOptional.orElse(null);
            each = eachOptional.orElse(null);
            some = someOptional.orElse(null);

            Ingredient avocados = new Ingredient("ripe avocados", new BigDecimal(2), each);
            Ingredient salt = new Ingredient("salt", new BigDecimal("0.25"), teaspoon);
            Ingredient freshLimeJuice = new Ingredient("fresh lime or lemon juice", new BigDecimal(1), tablespoon);
            Ingredient mincedRedOnion = new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(3), tablespoon);
            Ingredient serranoChilis = new Ingredient("serrano (or jalapeño) chilis, stems and seeds removed, minced", new BigDecimal(1), each);
            Ingredient cilantro = new Ingredient("cilantro (leaves and tender stems), finely chopped", new BigDecimal(2), tablespoon);
            Ingredient pepper = new Ingredient("freshly ground black pepper", new BigDecimal(1), pinch);
            Ingredient tomato = new Ingredient("ripe tomato, chopped (optional)", new BigDecimal("0.5"), each);
            Ingredient radish = new Ingredient("Red radish or jicama slices for garnish (optional)", new BigDecimal("0"), some);
            Ingredient tortilla = new Ingredient("Tortilla chips, to serve", new BigDecimal("0"), some);


            american.getRecipes().add(guackRecipe);
            guackRecipe.getCategories().add(american);

            mexican.getRecipes().add(guackRecipe);
            guackRecipe.getCategories().add(mexican);

            guackRecipe.addIngredient(avocados);
            guackRecipe.addIngredient(salt);
            guackRecipe.addIngredient(freshLimeJuice);
            guackRecipe.addIngredient(mincedRedOnion);
            guackRecipe.addIngredient(serranoChilis);
            guackRecipe.addIngredient(cilantro);
            guackRecipe.addIngredient(pepper);
            guackRecipe.addIngredient(tomato);
            guackRecipe.addIngredient(radish);
            guackRecipe.addIngredient(tortilla);

            guackRecipe.setDescription("Perfect guacamole");

            guackRecipe.setCookTime(0);
            guackRecipe.setDirections("Cut the avocado:\n" +
                    "Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and " +
                    "scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl." + "\n" +
                    "Mash the avocado flesh:\n" +
                    "Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" + "\n" +
                    "Add remaining ingredients to taste:\n" +
                    "Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance " +
                    "to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                    "\n" +
                    "Add the chopped onion, cilantro, black pepper, and chilis. Chili peppers vary individually in their " +
                    "spiciness. So, start with a half of one chili pepper and add more to the guacamole to your desired degree of heat.\n" +
                    "\n" +
                    "Remember that much of this is done to taste because of the variability in the fresh ingredients. " +
                    "Start with this recipe and adjust to your taste.\n" +
                    "\n" +
                    "Serve immediately:\n" +
                    "If making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to " +
                    "cover it to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.)\n" +
                    "\n" +
                    "Garnish with slices of red radish or jigama strips. Serve with your choice of store-bought tortilla " +
                    "chips or make your own homemade tortilla chips.\n" +
                    "\n" +
                    "Refrigerate leftover guacamole up to 3 days.\n" +
                    "\n" +
                    "Note: Chilling tomatoes hurts their flavor. So, if you want to add chopped tomato to your guacamole, " +
                    "add it just before serving.");
            guackRecipe.setDifficulty(Difficulty.EASY);
            guackRecipe.setImage(new Byte[0]);
            Notes guackNotes = new Notes();
            //guackNotes.setRecipe(guackRecipe);
            guackNotes.setNotes("How to Pick Perfectly Ripe Avocados\n" +
                    "The trick to making perfect guacamole is using avocados that are just the right amount of ripeness. " +
                    "Not ripe enough and the avocado will be hard and flavorless. Too ripe and the taste will be off.\n" +
                    "\n" +
                    "Check for ripeness by gently pressing the outside of the avocado. If there is no give, the avocado is " +
                    "not ripe yet. If there is a little give, the avocado is ripe. If there is a lot of give, the avocado " +
                    "may be too ripe and not good. In this case, taste test first before using.\n" +
                    "\n" +
                    "Authentic guacamole in a bowl with chips\n" +
                    "Elise Bauer\n" +
                    "How to Cut an Avocado\n" +
                    "To slice open an avocado, cut it in half lengthwise with a sharp chef's knife and twist apart. One " +
                    "side will have the pit. To remove it, you can carefully tap your chef’s knife against the pit and " +
                    "twist to dislodge it (protecting your hand with a towel), or you can cut the avocado into quarters " +
                    "and remove the pit with your fingers or a spoon.\n" +
                    "\n" +
                    "Still curious? Read more about How to Cut and Peel an Avocado\n" +
                    "\n" +
                    "Other Ways to Use Guacamole\n" +
                    "Remove the pit from the avocado with a chef knife\n" +
                    "Elise Bauer\n" +
                    "Guacamole has a role in the kitchen beyond a party dip. It's great scooped on top of nachos and also " +
                    "makes an excellent topping or side for enchiladas, tacos, grilled salmon, or oven-baked chicken.\n" +
                    "\n" +
                    "Guacamole is great in foods, as well. Try mixing some into a tuna sandwich or your next batch of " +
                    "deviled eggs.\n" +
                    "\n" +
                    "How to Store Guacamole\n" +
                    "Guacamole is best eaten right after it's made. Like apples, avocados start to oxidize and turn brown " +
                    "once they've been cut. That said, the acid in the lime juice you add to guacamole can help slow down " +
                    "that process. And if you store the guacamole properly, you can easily make it a few hours ahead if " +
                    "you are preparing for a party.\n" +
                    "\n" +
                    "The trick to keeping guacamole green is to make sure air doesn't touch it! Transfer it to a container, " +
                    "cover with plastic wrap, and press down on the plastic wrap to squeeze out any air pockets. Make sure " +
                    "any exposed surface of the guacamole is touching the plastic wrap, not air. This will keep the amount " +
                    "of browning to a minimum.\n" +
                    "\n" +
                    "You can store the guacamole in the fridge this way for up to three days. If the guacamole develops " +
                    "discoloration, you can either scrape off the brown parts and discard, or stir into the rest of the " +
                    "guacamole before serving.\n" +
                    "\n" +
                    "Homemade guacamole on a chip\n" +
                    "Elise Bauer\n" +
                    "Guacamole Variations\n" +
                    "Once you have basic guacamole down, feel free to experiment with variations by adding strawberries, " +
                    "peaches, pineapple, mangoes, or even watermelon. One classic Mexican guacamole has pomegranate seeds " +
                    "and chunks of peaches in it (a Diana Kennedy favorite). You can get creative with your homemade guacamole!\n" +
                    "\n" +
                    "Simple Guacamole: The simplest version of guacamole is just mashed avocados with salt. Don't let the " +
                    "lack of other ingredients stop you from making guacamole.\n" +
                    "Quick guacamole: For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed " +
                    "avocados.\n" +
                    "Don't have enough avocados? To extend a limited supply of avocados, add either sour cream or cottage " +
                    "cheese to your guacamole dip. Purists may be horrified, but so what? It still tastes great.");
            guackRecipe.setNotes(guackNotes);
            guackRecipe.setServings(4);
            guackRecipe.setPrepTime(10);
            guackRecipe.setSource("Simply recipes");
            guackRecipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");

            Recipe savedGuackRecipe = recipeRepository.save(guackRecipe);



            Category healtyDinner = new Category();
            healtyDinner.setDescription("Healty dinner");
            Category tacos = new Category();
            tacos.setDescription("Tacos");

            tacos.getRecipes().add(tacosRecipe);
            american.getRecipes().add(tacosRecipe);
            healtyDinner.getRecipes().add(tacosRecipe);

            Category savedHealtyDinner = categoryRepository.save(healtyDinner);
            Category savedTacos = categoryRepository.save(tacos);

            tacosRecipe.getCategories().add(healtyDinner);
            tacosRecipe.getCategories().add(tacos);
            tacosRecipe.getCategories().add(american);

            Ingredient anchoChiliPowder = new Ingredient("ancho chili powder", new BigDecimal(2), each);
            Ingredient driedOregano = new Ingredient("dried oregano", new BigDecimal(1), teaspoon);
            Ingredient driedCumin = new Ingredient("dried cumin", new BigDecimal(1), teaspoon);
            Ingredient sugar = new Ingredient("sugar", new BigDecimal(1), teaspoon);
            salt = new Ingredient("salt", new BigDecimal("0.5"), teaspoon);
            Ingredient garlic = new Ingredient("clove garlic, finely choped", new BigDecimal(1), each);
            Ingredient orangeZest = new Ingredient("finely grated orange zest", new BigDecimal(1), tablespoon);
            Ingredient orangeJuice = new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tablespoon);
            Ingredient oliveOil = new Ingredient("olive oil", new BigDecimal(2), tablespoon);
            Ingredient chickenThighs = new Ingredient("skinless, boneless chicken thighs (1 1/4 pounds)", new BigDecimal(5), each);

            tacosRecipe.addIngredient(anchoChiliPowder);
            tacosRecipe.addIngredient(driedOregano);
            tacosRecipe.addIngredient(driedCumin);
            tacosRecipe.addIngredient(sugar);
            tacosRecipe.addIngredient(salt);
            tacosRecipe.addIngredient(garlic);
            tacosRecipe.addIngredient(orangeZest);
            tacosRecipe.addIngredient(orangeJuice);
            tacosRecipe.addIngredient(oliveOil);
            tacosRecipe.addIngredient(chickenThighs);

            tacosRecipe.setDescription("Spicy grilled chicken tacos");

            tacosRecipe.setCookTime(15);
            tacosRecipe.setDirections("1. Prepare a gas or charcoal grill for medium-high, direct heat\n" +
                    "2. Make the marinade and coat the chicken:\n" +
                    "In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. " +
                    "Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss " +
                    "to coat all over.\n" +
                    "\n" +
                    "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                    "\n" +
                    "3. Grill the chicken:\n" +
                    "Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest " +
                    "part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                    "\n" +
                    "4. Warm the tortillas:\n" +
                    "Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see " +
                    "pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds " +
                    "on the other side.\n" +
                    "\n" +
                    "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                    "\n" +
                    "5. Assemble the tacos:\n" +
                    "Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken " +
                    "slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. " +
                    "Serve with lime wedges.");

            tacosRecipe.setDifficulty(Difficulty.MODERATE);
            tacosRecipe.setImage(new Byte[0]);
            Notes tacosNotes = new Notes();
            //tacosNotes.setRecipe(tacosRecipe);
            tacosNotes.setNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                    "\n" +
                    "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of " +
                    "pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating " +
                    "in a hot pan on the stove comes wafting through the house.\n" +
                    "\n" +
                    "Today's tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                    "\n" +
                    "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and " +
                    "sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                    "\n" +
                    "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the " +
                    "tacos and dig in. The whole meal comes together in about 30 minutes! \n" +
                    "The ancho chiles I use in the marinade are named for their wide shape. They are large, have a deep " +
                    "reddish brown color when dried, and are mild in flavor with just a hint of heat. You can find ancho " +
                    "chile powder at any markets that sell Mexican ingredients, or online.\n" +
                    "\n" +
                    "I like to put all the toppings in little bowls on a big platter at the center of the table: avocados, " +
                    "radishes, tomatoes, red onions, wedges of lime, and a sour cream sauce. I add arugula, as well – this " +
                    "green isn't traditional for tacos, but we always seem to have some in the fridge and I think it adds " +
                    "a nice green crunch to the tacos.\n" +
                    "\n" +
                    "Everyone can grab a warm tortilla from the pile and make their own tacos just they way they like them.\n" +
                    "\n" +
                    "You could also easily double or even triple this recipe for a larger party. A taco and a cold beer on " +
                    "a warm day? Now that's living!");

            tacosRecipe.setNotes(tacosNotes);
            tacosRecipe.setServings(5);
            tacosRecipe.setPrepTime(20);
            tacosRecipe.setSource("Simply recipes");
            tacosRecipe.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");

            Recipe savedTacosRecipe = recipeRepository.save(tacosRecipe);

        } catch (NullPointerException ex) {
            System.out.println("Error loading categories or unit of measures!");
        }

    }



}
