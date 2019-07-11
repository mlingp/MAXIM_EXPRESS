# MAXIM_EXPRESS
表达式解析器

        String expression = "(用户名+大家好)*10/2+-3";
        List<Variable> variables = new ArrayList<>();
        variables.add(Variable.createVariable("用户名", 9));
        variables.add(Variable.createVariable("大家好", 1));
        Object result = ExpressionEvaluator.evaluate(expression, variables);
        System.out.println(result);
