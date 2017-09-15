package com.revature.smp.beans;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

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
	private String email;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Location.class)
	@JoinColumn(name = "location_id")
	private Location location;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Status.class)
	@JoinColumn(name = "status_id")
	private Status status;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Role.class)
	@JoinColumn(name = "role_id")
	private Role role;
	
	@Column(name = "logged")
	private String logged;
	
	@Column(name = "use_temp")
	private String useTemp;
	
	@Column(name = "active")
	private String active;
	
	@Column(name = "created")
	private Date created;
	
	public User() {
	}
	
	public User(String firstName, String lastName, String email, int locationCode) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.location = new Location(locationCode, locationMap.get(locationCode));
		this.role = new Role(2, "Associate");
		this.status = new Status(1, "Staging");
		this.active = "0";
		this.logged = "0";
		this.useTemp = "1";
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
	
	@Override
	public String toString() {
		return "User [userId=" + userId 
				+ ", username=" + username
				+ ", password=" + password
				+ ", firstName=" + firstName
				+ ", lastName=" + lastName
				+ ", email=" + email 
				+ ", location=" + location 
				+ ", status=" + status
				+ ", role=" + role
				+ ", logged=" + logged
				+ ", useTemp=" + useTemp
				+ ", active=" + active
				+ ", created=" + created
				+ "]";
	}
}