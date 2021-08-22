package guru.springframework.springrecipeapp.bootstrap;

import guru.springframework.springrecipeapp.domain.*;
import guru.springframework.springrecipeapp.repositories.CategoryRepository;
import guru.springframework.springrecipeapp.repositories.IngredientRepository;
import guru.springframework.springrecipeapp.repositories.RecipeRepository;
import guru.springframework.springrecipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class DataLoader implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final IngredientRepository ingredientRepository;

    public DataLoader(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository, IngredientRepository ingredientRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientRepository = ingredientRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        loadData();
    }





    private void loadData() {
        Recipe guackRecipe = new Recipe();
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

            Recipe savedGuackRecipe = recipeRepository.save(guackRecipe);


            Ingredient avocados = new Ingredient("ripe avocados", new BigDecimal(2), each, savedGuackRecipe);


            /*
            Ingredient salt = new Ingredient("salt", new BigDecimal("0.25"), teaspoon, guackRecipe);
            Ingredient freshLimeJuice = new Ingredient("fresh lime or lemon juice", new BigDecimal(1), tablespoon, guackRecipe);
            Ingredient mincedRedOnion = new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(3), tablespoon, guackRecipe);
            Ingredient serranoChilis = new Ingredient("serrano (or jalapeño) chilis, stems and seeds removed, minced", new BigDecimal(1), each, guackRecipe);
            Ingredient cilantro = new Ingredient("cilantro (leaves and tender stems), finely chopped", new BigDecimal(2), tablespoon, guackRecipe);
            Ingredient pepper = new Ingredient("freshly ground black pepper", new BigDecimal(1), pinch, guackRecipe);
            Ingredient tomato = new Ingredient("ripe tomato, chopped (optional)", new BigDecimal("0.5"), each, guackRecipe);
            Ingredient radish = new Ingredient("Red radish or jicama slices for garnish (optional)", new BigDecimal("0"), some, guackRecipe);
            Ingredient tortilla = new Ingredient("Tortilla chips, to serve", new BigDecimal("0"), some, guackRecipe);
            */

            Ingredient savedAvocados = ingredientRepository.save(avocados);


            /*
            Ingredient savedSalt = ingredientRepository.save(salt);
            Ingredient savedFreshLimeJuice = ingredientRepository.save(freshLimeJuice);
            Ingredient savedMincedRedOnion = ingredientRepository.save(mincedRedOnion);
            Ingredient savedSerranoChilis = ingredientRepository.save(serranoChilis);
            Ingredient savedCilantro = ingredientRepository.save(cilantro);
            Ingredient savedPepper = ingredientRepository.save(pepper);
            Ingredient savedTomato = ingredientRepository.save(tomato);
            Ingredient savedRadish = ingredientRepository.save(radish);
            Ingredient savedTortilla = ingredientRepository.save(tortilla);
*

             */
            american.getRecipes().add(savedGuackRecipe);
            guackRecipe.getCategories().add(american);
            System.out.println("asdasd");

            mexican.getRecipes().add(savedGuackRecipe);
            guackRecipe.getCategories().add(mexican);
            guackRecipe.getIngredients().add(savedAvocados);



 /*
            guackRecipe.getIngredients().add(savedFreshLimeJuice);
            guackRecipe.getIngredients().add(savedMincedRedOnion);
            guackRecipe.getIngredients().add(savedSerranoChilis);
            guackRecipe.getIngredients().add(savedCilantro);
            guackRecipe.getIngredients().add(savedPepper);
            guackRecipe.getIngredients().add(savedTomato);
            guackRecipe.getIngredients().add(savedRadish);
            guackRecipe.getIngredients().add(savedTortilla);
*/


            guackRecipe.setDescription("Guacamole! Did you know that over 2 billion pounds of avocados are consumed " +
                    "each year in the U.S.? (Google it.) That's over 7 pounds per person. I'm guessing that most of " +
                    "those avocados go into what has become America's favorite dip: guacamole.\n" +
                    "\n" +
                    "Guacamole: A Classic Mexican Dish\n" +
                    "The word \"guacamole\" and the dip, are both originally from Mexico, where avocados have been " +
                    "cultivated for thousands of years. The name is derived from two Aztec Nahuatl words—ahuacatl (avocado) " +
                    "and molli (sauce).");

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
            guackNotes.setRecipe(guackRecipe);
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

            guackRecipe = recipeRepository.save(guackRecipe);


        } catch (NullPointerException ex) {
            System.out.println("Error loading categories or unit of measures!");
        }




    }

    private void loadData1() {
        Recipe guackRecipe = new Recipe();
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

            Recipe savedGuackRecipe = recipeRepository.save(guackRecipe);


            Ingredient avocados = new Ingredient("ripe avocados", new BigDecimal(2), each, savedGuackRecipe);


            /*
            Ingredient salt = new Ingredient("salt", new BigDecimal("0.25"), teaspoon, guackRecipe);
            Ingredient freshLimeJuice = new Ingredient("fresh lime or lemon juice", new BigDecimal(1), tablespoon, guackRecipe);
            Ingredient mincedRedOnion = new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(3), tablespoon, guackRecipe);
            Ingredient serranoChilis = new Ingredient("serrano (or jalapeño) chilis, stems and seeds removed, minced", new BigDecimal(1), each, guackRecipe);
            Ingredient cilantro = new Ingredient("cilantro (leaves and tender stems), finely chopped", new BigDecimal(2), tablespoon, guackRecipe);
            Ingredient pepper = new Ingredient("freshly ground black pepper", new BigDecimal(1), pinch, guackRecipe);
            Ingredient tomato = new Ingredient("ripe tomato, chopped (optional)", new BigDecimal("0.5"), each, guackRecipe);
            Ingredient radish = new Ingredient("Red radish or jicama slices for garnish (optional)", new BigDecimal("0"), some, guackRecipe);
            Ingredient tortilla = new Ingredient("Tortilla chips, to serve", new BigDecimal("0"), some, guackRecipe);
            */

            Ingredient savedAvocados = ingredientRepository.save(avocados);


            /*
            Ingredient savedSalt = ingredientRepository.save(salt);
            Ingredient savedFreshLimeJuice = ingredientRepository.save(freshLimeJuice);
            Ingredient savedMincedRedOnion = ingredientRepository.save(mincedRedOnion);
            Ingredient savedSerranoChilis = ingredientRepository.save(serranoChilis);
            Ingredient savedCilantro = ingredientRepository.save(cilantro);
            Ingredient savedPepper = ingredientRepository.save(pepper);
            Ingredient savedTomato = ingredientRepository.save(tomato);
            Ingredient savedRadish = ingredientRepository.save(radish);
            Ingredient savedTortilla = ingredientRepository.save(tortilla);
*

             */
            american.getRecipes().add(savedGuackRecipe);
            guackRecipe.getCategories().add(american);
            System.out.println("asdasd");

            mexican.getRecipes().add(savedGuackRecipe);
            guackRecipe.getCategories().add(mexican);
            guackRecipe.getIngredients().add(savedAvocados);



 /*
            guackRecipe.getIngredients().add(savedFreshLimeJuice);
            guackRecipe.getIngredients().add(savedMincedRedOnion);
            guackRecipe.getIngredients().add(savedSerranoChilis);
            guackRecipe.getIngredients().add(savedCilantro);
            guackRecipe.getIngredients().add(savedPepper);
            guackRecipe.getIngredients().add(savedTomato);
            guackRecipe.getIngredients().add(savedRadish);
            guackRecipe.getIngredients().add(savedTortilla);
*/


            guackRecipe.setDescription("Guacamole! Did you know that over 2 billion pounds of avocados are consumed " +
                    "each year in the U.S.? (Google it.) That's over 7 pounds per person. I'm guessing that most of " +
                    "those avocados go into what has become America's favorite dip: guacamole.\n" +
                    "\n" +
                    "Guacamole: A Classic Mexican Dish\n" +
                    "The word \"guacamole\" and the dip, are both originally from Mexico, where avocados have been " +
                    "cultivated for thousands of years. The name is derived from two Aztec Nahuatl words—ahuacatl (avocado) " +
                    "and molli (sauce).");

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
            guackNotes.setRecipe(guackRecipe);
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

            guackRecipe = recipeRepository.save(guackRecipe);


        } catch (NullPointerException ex) {
            System.out.println("Error loading categories or unit of measures!");
        }




    }

}
