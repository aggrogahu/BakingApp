package leonard.bakingapp.data;

import ckm.simple.sql_provider.annotation.SimpleSQLColumn;
import ckm.simple.sql_provider.annotation.SimpleSQLTable;
import leonard.bakingapp.classes.Ingredient;

/*
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

//    @SimpleSQLColumn(value = "col_int", primary = true)
//    public int anInt;

    @SimpleSQLColumn("col_recipe")
    public String recipeName;

    @SimpleSQLColumn("col_ingredients")
    public String ingredients;

//    @SimpleSQLColumn("col_str")
//    public String myString;
//
//    @SimpleSQLColumn(value = "col_int", primary = true)
//    public int anInt;
//
//    @SimpleSQLColumn("col_integer")
//    public int myinteger;
//
//    @SimpleSQLColumn("col_short")
//    public int myshort;
//
//    @SimpleSQLColumn("col_short2")
//    public int myShort;
//
//    @SimpleSQLColumn("col_long")
//    public long mylong;
//
//    @SimpleSQLColumn("col_long2")
//    public int myLong;
//
//    @SimpleSQLColumn("col_double")
//    public long mydouble;
//
//    @SimpleSQLColumn("col_double2")
//    public int myDouble;
//
//    @SimpleSQLColumn("col_float")
//    public long myfloat;
//
//    @SimpleSQLColumn("col_float2")
//    public int myFloat;
//
//    @SimpleSQLColumn("col_bool")
//    public boolean mybool;
//
//    @SimpleSQLColumn("col_bool2")
//    public boolean myBool;
}