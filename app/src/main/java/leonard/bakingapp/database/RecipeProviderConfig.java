package leonard.bakingapp.database;

import ckm.simple.sql_provider.UpgradeScript;
import ckm.simple.sql_provider.annotation.ProviderConfig;
import ckm.simple.sql_provider.annotation.SimpleSQLConfig;

@SimpleSQLConfig(
        name = "RecipeContentProvider",
        authority = "recipe_provider.authority",
        database = "recipe.db",
        version = 1)

public class RecipeProviderConfig implements ProviderConfig {
    @Override
    public UpgradeScript[] getUpdateScripts() {
        return new UpgradeScript[0];
    }
}
