/**
 * 
 */
package maxim.express.format.reader;

import java.io.IOException;

import maxim.express.format.Element;
import maxim.express.format.ExpressionReader;
import maxim.express.format.FormatException;

/**
 * @author maxim，卓诗垚
 * @version 2.0 
 * Oct 9, 2008
 */
public interface ElementReader {
	Element read(ExpressionReader sr) throws FormatException, IOException;
}
