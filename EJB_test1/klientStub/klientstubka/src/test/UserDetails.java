package test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;




public class UserDetails implements Serializable {
	/*
	 @EmbeddedId
	 private UserID iD; - ak PK je vlastne ïalší objekt*/

	private int iD;

	private String name;
	/*@Column (name = "password")
	private String password;
	@Column (name = "created_date")
	@Temporal (TemporalType.DATE)
	private Date createdDate;
	@Column (name = "comment")
	/*@Transient - tento atribút nepôjde do databázy*/
	//@Lob //large object - a lot of text
	/*private String comment;
	@Embedded
	private FullName realName;*/
	/*@Embedded
	@AttributeOverrides({
		@AttributeOverride (name = "firstName",	column = @Column(name="married_name")),
		@AttributeOverride (name = "second_Name", column = @Column(name="married_surname"))
	})
	private FullName marriedName;*/
	//@ElementCollection /*(fetch=FetchType.EAGER)*/
	/*@JoinTable (name = "name_history",
				joinColumns = @JoinColumn (name="user_id"))
	private Set<FullName> nameHistory = new HashSet();*/
	/*@GenericGenerator (name = "hilo-gen", strategy = "hilo")
	@CollectionId (columns = {@Column(name = "id")}, generator = "hilo-gen", type = @Type (type="long"))
	private Collection<FullName> nameHistory = new ArrayList<FullName>();*/
	
	public UserDetails() {}
	public UserDetails(String name) {
		this.name = name;
	}
	public int getID() {
		return iD;
	}
	public void setID(int iD) {
		this.iD = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
