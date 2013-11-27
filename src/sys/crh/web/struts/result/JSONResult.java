package sys.crh.web.struts.result;

import java.io.PrintWriter;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import com.opensymphony.xwork2.util.ValueStack;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

@Deprecated
public class JSONResult implements Result {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7954923661674291941L;


	public static final String DEFAULT_PARAM = "classAlias";
    
   
    // Define the param of the result
    String classAlias;

    public String getClassAlias(){
               return classAlias;
    }
    public void setClassAlias(String classAlias){
               this.classAlias = classAlias;
    }

    public void execute(ActionInvocation invocation) throws Exception{
    			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");

               ServletActionContext.getResponse().setContentType("application/json-rpc");

               PrintWriter responseStream = ServletActionContext.getResponse().getWriter();

               ValueStack valueStack = invocation.getStack();
               Object jsonModel =  valueStack.findValue("jsonModel");

               XStream xstream = new XStream(new JettisonMappedXmlDriver());
              
               responseStream.println(xstream.toXML(jsonModel));
    }
}