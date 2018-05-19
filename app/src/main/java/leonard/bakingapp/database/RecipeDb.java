package leonard.bakingapp.database;

import ckm.simple.sql_provider.annotation.SimpleSQLColumn;
import ckm.simple.sql_provider.annotation.SimpleSQLTable;

/**
uses https://github.com/ckurtm/simple-sql-provider
Apache License (Version 2.0)

You may not use this file except in compliance with the License.
You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

@SimpleSQLTable(table = "recipe", provider = "RecipeContentProvider")
public class RecipeDb {

    @SimpleSQLColumn("col_recipe")
    public String recipeName;

    @SimpleSQLColumn("col_ingredients")
    public String ingredients;
}