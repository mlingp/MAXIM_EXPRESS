/**
 * 
 */
package maxim.express.datameta;

import maxim.express.Evaluator;
import maxim.express.ExpressionToken;
import maxim.express.IllegalExpressionException;
import maxim.express.ExpressionToken.ETokenType;
import maxim.express.datameta.BaseDataMeta.DataType;
import maxim.express.function.FunctionExecution;
import maxim.express.opera.Operator;



/**
 * 引用对象
 * @author maxim
 */
public class Reference {
	
	private ExpressionToken token;
	
	private Constant[] arguments;
	//引用对象实际的数据类型
	private DataType dataType;

	public Reference(ExpressionToken token , Constant[] args) throws IllegalExpressionException{
		this(token, args, true);
	}
	
	public Reference(ExpressionToken token , Constant[] args, boolean isStrict) throws IllegalExpressionException{
		this.token = token;
		this.arguments = args;
		//记录Reference实际的数据类型
		if(ExpressionToken.ETokenType.ETOKEN_TYPE_FUNCTION == token.getTokenType()){
			Constant result = FunctionExecution.varify(token.getFunctionName(), token.getStartPosition() , args);
			dataType = result.getDataType();
		}else if(ExpressionToken.ETokenType.ETOKEN_TYPE_OPERATOR == token.getTokenType()){
			if(isStrict){
				Operator op = token.getOperator();
				Constant result = op.verify(token.getStartPosition() , args);
				dataType = result.getDataType();
			}else {
				dataType = DataType.DATATYPE_OBJECT;
			}
		}
	}
	
	public DataType getDataType() {
		return dataType;
	}

	public Constant[] getArgs() {
		return arguments;
	}
	
	public void setArgs(Constant[] args) {
		this.arguments = args;
	}
	
	public ExpressionToken getToken() {
		return token;
	}

	public void setToken(ExpressionToken token) {
		this.token = token;
	} 

	/**
	 * 执行引用对象指待的表达式（操作符或者函数）
	 * @return
	 */
	public Constant execute() throws IllegalExpressionException{
		return execute(null);
	}
	
	/**
	 * 执行引用对象指待的表达式（操作符或者函数）
	 * @return
	 */
	public Constant execute(Evaluator<Constant> evaluator)throws IllegalExpressionException{
		
		if(ETokenType.ETOKEN_TYPE_OPERATOR == token.getTokenType()){
			//执行操作符
			Operator op = token.getOperator();
			Constant first = arguments[0];
			Constant second = null;
			if(arguments.length > 1){
				first = arguments[1];
				second = arguments[0];
			}
			
			if(evaluator != null && evaluator.canOperator(op, first, second)){
				return evaluator.evalutor(op, first, second);
			}else {			
				return op.execute(arguments);
			}	
		}else if(ETokenType.ETOKEN_TYPE_FUNCTION == token.getTokenType()){
			//执行函数
			return	FunctionExecution.execute(token.getFunctionName(), token.getStartPosition() , arguments);
			
		}else{
			throw new IllegalExpressionException("不支持的Reference执行异常");
		}
	}
	
}
