package javatermproject.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Account {
	private static Account instance;

	private Account() {
		this(null, null, null, 0);
	}

	// 전역적으로 단 한개만 존재한다!
	static {
		try {
			System.out.println("[0] create Account instance");
			instance = new Account();
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("[x] an error occured!");
		}
	}

	/* 인스턴스 리턴! */
	public static Account getInstance() {
		return instance;
	}

	private StringProperty	balance;	/* 계좌번호 */
	private StringProperty	name;		/* 이름 */
	private StringProperty	password;	/* 비밀번호 */
	private IntegerProperty	money;		/* 금액 */


	public Account(String balance, String name,
			String password, int money) {
		this.balance	= new SimpleStringProperty(balance);
		this.name		= new SimpleStringProperty(name);
		this.password	= new SimpleStringProperty(password);
		this.money		= new SimpleIntegerProperty(money);
	}

	/**
	 * getters, setters and property for Account class
	 * @return
	 */
	public String getBalance() 					{ return balance.get(); }
	public void setBalance(String balance) 		{ this.balance.set(balance); }
	public StringProperty BalanceProperty() 	{ return balance; }

	public String getName()						{ return name.get(); }
	public void setName(String name)			{ this.name.set(name); }
	public StringProperty nameProperty()		{ return name; }

	public String getPassword()					{ return password.get(); }
	public void setPassword(String password) 	{ this.password.set(password); }
	public StringProperty passwordProperty()	{ return password; }

	public int getMoney()						{ return money.get(); }
	public void setMoney(int money)				{ this.money.set(money); }
	public IntegerProperty moneyProperty()		{ return money; }

}