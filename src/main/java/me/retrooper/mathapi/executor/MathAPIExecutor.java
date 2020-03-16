package me.retrooper.mathapi.executor;

import javax.script.CompiledScript;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import me.retrooper.mathapi.MathHelper;
import me.retrooper.mathapi.exceptions.InvalidNumberException;
import me.retrooper.mathapi.utils.MathUtils;

/**
 * 
 * @author Retrooper
 *
 */
public class MathAPIExecutor {
	private ScriptEngine engine;

	private String text;

	/**
	 * Initiate the executor
	 * 
	 * @param text
	 */
	public MathAPIExecutor(String text) {
		this.text = text;
		engine = new ScriptEngineManager().getEngineByName("js");
		MathHelper.debug("Calculating... " + text);
	}

	/**
	 * This calculates the problem
	 * 
	 * @return
	 */
	public double execute() {

		if (!MathUtils.isExecution(text)) {
			// IT IS NOT AN EXECUTION
			try {
				// CLEAR PARENTHESIS
				if (text.contains("(") && text.contains(")")) {
					text = text.replace("(", "").replace(")", "").trim();
				}
				return Double.parseDouble(text);
			} catch (NumberFormatException ex) {
				throw new InvalidNumberException("Invalid number given for an evaluation!", ex);
			} catch (NullPointerException ex) {
				throw new InvalidNumberException("Empty Evaluation!", ex);
			}
		}
		CompiledScript script = new CompiledScript() {
			@Override
			public Object eval(ScriptContext context) throws ScriptException {
				return getEngine().eval(text, context);
			}

			@Override
			public ScriptEngine getEngine() {
				return engine;
			}

		};
		try {
			return Double.parseDouble(script.eval().toString());
		} catch (NumberFormatException e) {
			throw new InvalidNumberException("Invalid number given for an evaluation!", e);
		} catch (ScriptException e) {
			throw new IllegalStateException("Something went wrong while trying to execute " + text);
		}
	}

	/**
	 * Can the number be replaced with an integer?(no decimal values)
	 * 
	 * @param val
	 * @return
	 */
	public boolean containsDecimals(double val) {
		return Math.round(val) != val;
	}
}
