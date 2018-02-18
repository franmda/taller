package modeloSiu;

public class Log {
	private String pass;
    private String usrName;
    
    
	public Log() {
		super();
	}
	
	public Log(String pass, String usrName) {
		super();
		this.pass = pass;
		this.usrName = usrName;
	}
	
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getUsrName() {
		return usrName;
	}
	public void setUsrName(String usrName) {
		this.usrName = usrName;
	}
}
