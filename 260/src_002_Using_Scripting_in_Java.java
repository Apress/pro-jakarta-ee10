import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
...
    ScriptEngine engine = new ScriptEngineManager().
        getEngineByName("groovy");

    // read in Groovy code
    StringReader f = new StringReader(
      "class Multiplier {\n"
      + "  def multiply(def x, def y) {\n"
      + "    x * y\n"
      + "  }\n"
      + "}\n"
      + "x = new Multiplier().multiply(par1, par2)\n"
    );
    // <- could also use a FileReader here

    try {
        // set input parameters
        engine.put("par1", 5);
        engine.put("par2", 8);
        engine.eval(f);
    } catch (ScriptException e) {
        // ... handle exception
    }
    // fetch output variable
    Object x = engine.get("x"); // -> 40
		  
