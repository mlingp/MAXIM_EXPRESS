# MAXIM_EXPRESS
表达式解析器

        String expression = "(参数A+参数B)*10/2+-3";
        List<Variable> variables = new ArrayList<>();
        variables.add(Variable.createVariable("参数A", 9));
        variables.add(Variable.createVariable("参数B", 1));
        Object result = ExpressionEvaluator.evaluate(expression, variables);
        System.out.println(result);
