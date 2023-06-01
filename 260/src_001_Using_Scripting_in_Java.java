import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
...
    ScriptEngine engine = new ScriptEngineManager().
        getEngineByName("python");

    // read in Python code
    StringReader f = new StringReader(
    		"class Multiplier:\n"
    		+ "\n"
    		+ "  def multiply(self, x, y):\n"
    		+ "    return x * y\n"
    		+ "\n"
    		+ "x = Multiplier().multiply(par1, par2)\n"
    );
    // <- could also use a FileReader here

    try {
        // set input parameters
        engine.put("par1", 5);
        engine.put("par2", 8);
        // evaluate the script
        engine.eval(f);
    } catch (ScriptException e) {
        // ... handle exception
    }
    // fetch output variable
    Object x = engine.get("x"); // -> 40
...
