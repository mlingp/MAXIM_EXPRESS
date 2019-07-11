package maxim.express.opera.define;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import maxim.express.IllegalExpressionException;
import maxim.express.datameta.BaseDataMeta;
import maxim.express.datameta.Constant;
import maxim.express.datameta.Reference;
import maxim.express.datameta.BaseDataMeta.DataType;
import maxim.express.opera.IOperatorExecution;
import maxim.express.opera.Operator;

/**
 * 集合添加操作
 *
 * @author maxim
 */
public class Op_APPEND implements IOperatorExecution {

    public static final Operator THIS_OPERATOR = Operator.APPEND;

    @Override
    public Constant execute(Constant[] args) throws IllegalExpressionException {

        if (args == null || args.length != 2) {
            throw new IllegalArgumentException("操作符\"" + THIS_OPERATOR.getToken() + "参数个数不匹配");
        }
        Constant first = args[1];
        Constant second = args[0];
        if (first == null || second == null) {
            throw new NullPointerException("操作符\"" + THIS_OPERATOR.getToken() + "\"参数为空");
        }
        //如果第一参数为引用，则执行引用
        if (first.isReference()) {
            Reference firstRef = (Reference) first.getDataValue();
            first = firstRef.execute();
        }
        //如果第二参数为引用，则执行引用
        if (second.isReference()) {
            Reference secondRef = (Reference) second.getDataValue();
            second = secondRef.execute();
        }
        return append(first, second);
    }


    @Override
    public Constant verify(int opPositin, BaseDataMeta[] args)
            throws IllegalExpressionException {

        if (args == null) {
            throw new IllegalArgumentException("运算操作符参数为空");
        }
        if (args.length != 2) {
            //抛异常
            throw new IllegalExpressionException("操作符\"" + THIS_OPERATOR.getToken() + "\"参数个数不匹配", THIS_OPERATOR.getToken(), opPositin
            );
        }
        BaseDataMeta first = args[1];
        BaseDataMeta second = args[0];

        if (first == null || second == null) {
            throw new NullPointerException("操作符\"" + THIS_OPERATOR.getToken() + "\"参数为空");
        }
        //APPEND接受任何类型的参数，总是返回Collection类型的常量
        return new Constant(BaseDataMeta.DataType.DATATYPE_LIST, null);

    }

    /**
     * 合并两个常量对象
     *
     * @param object
     */
    private Constant append(Constant arg1, Constant arg2) {
        if (arg1 == null || arg2 == null) {
            throw new IllegalArgumentException("操作符\"" + THIS_OPERATOR.getToken() + "\"参数丢失");
        }

        List<Object> resultCollection = new ArrayList<Object>();
        //合并参数一
        if (DataType.DATATYPE_LIST == arg1.getDataType()) {
            if (arg1.getCollection() != null) {
                resultCollection.addAll(arg1.getCollection());
            }
        } else {
            try {
                Object object = arg1.toJavaObject();
                resultCollection.add(object);
            } catch (ParseException e) {
                e.printStackTrace();

            }
        }
        //合并参数二
        if (DataType.DATATYPE_LIST == arg2.getDataType()) {
            if (arg2.getCollection() != null) {
                resultCollection.addAll(arg2.getCollection());
            }
        } else {
            try {
                Object object = arg2.toJavaObject();
                resultCollection.add(object);
            } catch (ParseException e) {
                e.printStackTrace();

            }
        }

        //构造新的collection 常量
        Constant result = new Constant(BaseDataMeta.DataType.DATATYPE_LIST, resultCollection);
        return result;
    }

}
