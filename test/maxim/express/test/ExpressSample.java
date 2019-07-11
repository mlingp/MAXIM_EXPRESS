package maxim.express.test;

import java.util.ArrayList;
import java.util.List;
import maxim.express.ExpressionEvaluator;
import maxim.express.datameta.Variable;

/**
 *
 * @author maxim
 */
public class ExpressSample {

    public static void main(String[] args) {
        String expression = "(用户名+大家好)*10/2+-3";
        List<Variable> variables = new ArrayList<>();
        variables.add(Variable.createVariable("用户名", 9));
        variables.add(Variable.createVariable("大家好", 1));
        Object result = ExpressionEvaluator.evaluate(expression, variables);
        System.out.println(result);
    }
}
