package maxim.express.opera.define;

import maxim.express.IllegalExpressionException;
import maxim.express.datameta.BaseDataMeta;
import maxim.express.datameta.Constant;
import maxim.express.opera.IOperatorExecution;
import maxim.express.opera.Operator;

/**
 * @author maxim
 */
public class Op_COLON implements IOperatorExecution {

    public static final Operator THIS_OPERATOR = Operator.COLON;

    @Override
    public Constant execute(Constant[] args) {
        throw new UnsupportedOperationException("操作符\"" + THIS_OPERATOR.getToken() + "不支持该方法");
    }

    @Override
    public Constant verify(int opPositin, BaseDataMeta[] args)
            throws IllegalExpressionException {
        throw new UnsupportedOperationException("操作符\"" + THIS_OPERATOR.getToken() + "不支持该方法");
    }

}
