package com.revature.smp.beans;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
@Entity
@Table(name = "USER_TABLE")
public class User implements Serializable {
	
	private static final long serialVersionUID = 6104022944061620088L;
	
	private static final Map<Integer, String> locationMap = createMap();
	
	private static Map<Integer, String> createMap(){
		Map<Integer, String> locationMap = new HashMap<Integer, String>();
        locationMap.put(1, "Virginia");
        locationMap.put(2, "New York");
        locationMap.put(3, "Florida");
        return Collections.unmodifiableMap(locationMap);
	}

	@Id
	@SequenceGenerator(name="SEQ_USR", sequenceName="SEQ_USER", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_USR")
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "passwd")
	private String password;
	
	@Column(name = "firstname")
	private String firstName;
	
	@Column(name = "lastname")
	private String lastName;
	
	@Column(name = "email")
	@JsonIgnore
	private String email;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Location.class)
	@JoinColumn(name = "location_id")
	@JsonIgnore
	private Location location;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Status.class)
	@JoinColumn(name = "status_id")
	@JsonIgnore
	private Status status;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Role.class)
	@JoinColumn(name = "role_id")
	@JsonIgnore
	private Role role;
	
	@Column(name = "loggedin")
	@JsonIgnore
	private String logged;
	
	@Column(name = "use_temp")
	@JsonIgnore
	private String useTemp;
	
	@Column(name = "active")
	@JsonIgnore
	private String active;
	
	@Column(name = "created")
	@Temporal(javax.persistence.TemporalType.DATE)
	@JsonIgnore
	private Date created;
	
	@OneToMany(fetch = FetchType.EAGER, targetEntity = MessageRoom.class)
	@JoinTable(
		name = "USER_MSGROOM_JUNC",
		joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "room_id", referencedColumnName = "room_id")
	)
	private List<MessageRoom> messageRooms;
	
	public User() { }
	
	public User(String username)
	{
		super();
		this.username = username;
	}

	public User(int userId, String username, String password, String firstName, String lastName, String email,
			Location location, Status status, Role role, String logged, String useTemp, String active, Date created,
			List<MessageRoom> messageRooms) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.location = location;
		this.status = status;
		this.role = role;
		this.logged = logged;
		this.useTemp = useTemp;
		this.active = active;
		this.created = created;
		this.messageRooms = messageRooms;
	}

	public User(String firstName, String lastName, String email, int locationCode) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.location = new Location(locationCode, locationMap.get(locationCode));
		this.role = new Role(2, "Associate");
		this.status = new Status(1, "Staging");
		this.active = "n";
		this.logged = "n";
		this.useTemp = "y";
		this.created = new Date();
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	public String getLogged() {
		return logged;
	}
	
	public void setLogged(String logged) {
		this.logged = logged;
	}
	
	public String getUseTemp() {
		return useTemp;
	}
	
	public void setUseTemp(String useTemp) {
		this.useTemp = useTemp;
	}
	
	public String getActive() {
		return active;
	}
	
	public void setActive(String active) {
		this.active = active;
	}
	
	public Date getCreated() {
		return created;
	}
	
	public void setCreated(Date created) {
		this.created = created;
	}
	
	public List<MessageRoom> getMessageRooms() {
		return messageRooms;
	}

	public void setMessageRooms(List<MessageRoom> messageRooms) {
		this.messageRooms = messageRooms;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", location=" + location + ", status="
				+ status + ", role=" + role + ", logged=" + logged + ", useTemp=" + useTemp + ", active=" + active
				+ ", created=" + created + ", messageRooms=" + messageRooms + "]";
	}

}